package mg.imwa.tenant.service;

import mg.imwa.tenant.model.entityEnum.ModePayement;
import mg.imwa.tenant.model.entityEnum.TypeOperationCaisse;
import mg.imwa.tenant.model.tenantEntityBeans.Filiale;
import mg.imwa.tenant.model.tenantEntityBeans.InfoFilialeCaisse;
import mg.imwa.tenant.model.wrapper.CashWrapper;
import mg.imwa.tenant.repository.CashRepository;
import mg.imwa.tenant.repository.IfcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.*;

@Service
public class CashService {

    private final static String SOMME_VENTE = "VENTE";
    private final static String SOMME_ESPECE = "ESPECE";
    private final static String SOMME_VIREMENT = "VIREMENT";
    private final static String SOMME_CHEQUE = "CHEQUE";
    private final static String SOMME_CREDIT = "CREDIT";
    private final static String RECETTE = "RECETTE";
    private final static String SOMME_AVOIR = "AVOIR";
    private final static String SOMME_MOBILE_MONEY = "MOBILE_MONEY";
    private final static String SOMME_DEPENSE = "DEPENSE";
    private final static String SOMME_ENCAISSEMENT = "ENCAISSEMENT";
    private final static String SOMME_CONSOMMATION = "CONSOMMATION";

    @Autowired private IfcRepository ifcRepository;

    @Autowired private RepoUtils repoUtils;

    public CashWrapper findAllByTypePayement(Long filialeId, String type, String filterType, LocalDate date) {
        List<InfoFilialeCaisse> allByTypePayement = new ArrayList<>();
        if (filterType.equals("MODE-PAYEMENT"))
            allByTypePayement = ifcRepository.findAllByTypePayement(filialeId, ModePayement.string2TypePayement(type), date);
        else
            allByTypePayement = ifcRepository.findAllByTypeOperation(filialeId, TypeOperationCaisse.string2Operation(type), date);
        return new CashWrapper(allByTypePayement);
    }

    public CashWrapper findAllByUserIdAndDate(Long filialeId, Long userId, LocalDate date) {
        List<InfoFilialeCaisse> allByUserIdAndDate = ifcRepository.findAllByUserIdAndDate(filialeId, userId, date);
        Map<String, Double> map = getCashInfoByUserId(filialeId, userId, date, date);
        return new CashWrapper(allByUserIdAndDate, map);
    }

    public CashWrapper getCashInfoBetweenDate(Long filialeId, String begin, String end) {
        LocalDate beginDate = parse(begin);
        LocalDate endDate = parse(end);
        List<InfoFilialeCaisse> allBetweenDate = ifcRepository.findAllBetweenDate(filialeId, beginDate, endDate);
        Map<String, Double> cashInfoMap = getCashInfo(filialeId, beginDate, endDate);
        return new CashWrapper(allBetweenDate, cashInfoMap);
    }

    public CashWrapper getCashInfoByStoreId(Long filialeId, Long storeId) {
        LocalDate now = LocalDate.now(Clock.systemDefaultZone());
        List<InfoFilialeCaisse> cashInfoByStoreId = ifcRepository.getCashInfoByStoreId(filialeId, storeId, now);
        Map<String, Double> cashInfoByStoreMap = getCashInfoByStore(filialeId, storeId);
        return new CashWrapper(cashInfoByStoreId, cashInfoByStoreMap);
    }

    private LocalDate parse(String date) {
        String[] split = date.replace("'", "").split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        return LocalDate.of(year, month, day);
    }

    public Map<String, Double> getCashInfoByStore(Long filialeId, Long storeId) {
        Map<String, Double> info = new HashMap<>();
        LocalDate now = LocalDate.now(Clock.systemDefaultZone());
        // PAYEMENT
        Double sumEspece = ifcRepository.findByTypePayementByStoreId(filialeId, storeId, ModePayement.ESPECE, now).orElse(0.0);
        Double sumConsommation = ifcRepository.findByTypePayementByStoreId(filialeId, storeId, ModePayement.CONSOMMATION, now).orElse(0.0);
        Double sumVirement = ifcRepository.findByTypePayementByStoreId(filialeId, storeId, ModePayement.VIREMENT, now).orElse(0.0);
        Double sumCheque = ifcRepository.findByTypePayementByStoreId(filialeId, storeId, ModePayement.CHEQUE, now).orElse(0.0);
        Double sumCredit = ifcRepository.findByTypePayementByStoreId(filialeId, storeId, ModePayement.CREDIT, now).orElse(0.0);
        Double sumMobileMoney = ifcRepository.findByTypePayementByStoreId(filialeId, storeId, ModePayement.MOBILE_MONEY, now).orElse(0.0);
        // OPERATION
        Double avoir = ifcRepository.findByOperationTypeAndStoreId(filialeId, storeId, TypeOperationCaisse.AVOIR, now).orElse(0.0);
        Double sommeDepense = ifcRepository.findByOperationTypeAndStoreId(filialeId, storeId, TypeOperationCaisse.DECAISSEMENT, now).orElse(0.0);
        Double sommeEncaissement = ifcRepository.findByOperationTypeAndStoreId(filialeId, storeId, TypeOperationCaisse.ENCAISSEMENT, now).orElse(0.0);
        Double recette = sumEspece + sumCheque + sumVirement + sumMobileMoney;
        Double sumVente = recette + sumCredit;
        info.put(SOMME_VENTE, sumVente);
        info.put(SOMME_CONSOMMATION, sumConsommation);
        info.put(SOMME_ESPECE, sumEspece);
        info.put(SOMME_VIREMENT, sumVirement);
        info.put(SOMME_CHEQUE, sumCheque);
        info.put(SOMME_CREDIT, sumCredit);
        info.put(RECETTE, recette);
        info.put(SOMME_MOBILE_MONEY, sumMobileMoney);
        info.put(SOMME_AVOIR, avoir);
        info.put(SOMME_DEPENSE, sommeDepense);
        info.put(SOMME_ENCAISSEMENT, sommeEncaissement);
        return info;
    }

    @Autowired
    private CashRepository cashRepository;

    public InfoFilialeCaisse persit(InfoFilialeCaisse ifc) {
        // CREATION DE LA REFERENCE
        TypeOperationCaisse operationCaisse = ifc.getOperationCaisse();
        String ref = repoUtils.generateRef(operationCaisse == TypeOperationCaisse.ENCAISSEMENT ? "ENCAISSEMENT" : "DECAISSEMENT");
        ifc.setReference(ref);
        if (operationCaisse.equals(TypeOperationCaisse.DECAISSEMENT)){
            Filiale filiale = ifc.getFiliale();
            Long filialeId = filiale.getId();
            cashRepository.findByFilialeIdAndPayementMode(filialeId,ifc.getModePayement()).ifPresent(caisse -> {
                if (caisse.getValue() >= ifc.getMontantOperation()) ifcRepository.save(ifc);
            });
        }else return ifcRepository.save(ifc);
        return null;
    }

    public Map<String, Double> getCashInfo(Long filialeId) {
        Map<String, Double> info = new HashMap<>();
        LocalDate now = LocalDate.now(Clock.systemDefaultZone());
        Double sumEspece = ifcRepository.findByTypePayement(filialeId, ModePayement.ESPECE, now).orElse(0.0);
        Double sumConsommation = ifcRepository.findByTypePayement(filialeId, ModePayement.CONSOMMATION, now).orElse(0.0);
        Double sumVirement = ifcRepository.findByTypePayement(filialeId, ModePayement.VIREMENT, now).orElse(0.0);
        Double sumCheque = ifcRepository.findByTypePayement(filialeId, ModePayement.CHEQUE, now).orElse(0.0);
        Double sumCredit = ifcRepository.findByTypePayement(filialeId, ModePayement.CREDIT, now).orElse(0.0);
        Double sumMobileMoney = ifcRepository.findByTypePayement(filialeId, ModePayement.MOBILE_MONEY, now).orElse(0.0);
        Double avoir = ifcRepository.findByOperationType(filialeId, TypeOperationCaisse.AVOIR, now).orElse(0.0);
        Double sommeDepense = ifcRepository.findByOperationType(filialeId, TypeOperationCaisse.DECAISSEMENT, now).orElse(0.0);
        Double sommeEncaissement = ifcRepository.findByOperationType(filialeId, TypeOperationCaisse.ENCAISSEMENT, now).orElse(0.0);
        Double recette = sumEspece + sumCheque + sumVirement + sumMobileMoney;
        Double sumVente = recette + sumCredit;
        info.put(SOMME_VENTE, sumVente);
        info.put(SOMME_CONSOMMATION, sumConsommation);
        info.put(SOMME_ESPECE, sumEspece);
        info.put(SOMME_VIREMENT, sumVirement);
        info.put(SOMME_CHEQUE, sumCheque);
        info.put(SOMME_CREDIT, sumCredit);
        info.put(RECETTE, recette);
        info.put(SOMME_MOBILE_MONEY, sumMobileMoney);
        info.put(SOMME_AVOIR, avoir);
        info.put(SOMME_DEPENSE, sommeDepense);
        info.put(SOMME_ENCAISSEMENT, sommeEncaissement);
        return info;
    }

    public Map<String, Double> getCashInfo(Long filialeId, LocalDate beginDate, LocalDate endDate) {
        Map<String, Double> info = new HashMap<>();
        Double sumEspece = ifcRepository.findByTypePayementBetweenDate(filialeId, ModePayement.ESPECE, beginDate, endDate).orElse(0.0);
        Double sumConsommation = ifcRepository.findByTypePayementBetweenDate(filialeId, ModePayement.CONSOMMATION, beginDate, endDate).orElse(0.0);
        Double sumVirement = ifcRepository.findByTypePayementBetweenDate(filialeId, ModePayement.VIREMENT, beginDate, endDate).orElse(0.0);
        Double sumCheque = ifcRepository.findByTypePayementBetweenDate(filialeId, ModePayement.CHEQUE, beginDate, endDate).orElse(0.0);
        Double sumCredit = ifcRepository.findByTypePayementBetweenDate(filialeId, ModePayement.CREDIT, beginDate, endDate).orElse(0.0);
        Double sumMobileMoney = ifcRepository.findByTypePayementBetweenDate(filialeId, ModePayement.MOBILE_MONEY, beginDate, endDate).orElse(0.0);
        Double avoir = ifcRepository.findByOperationTypeBetweenDate(filialeId, TypeOperationCaisse.AVOIR, beginDate, endDate).orElse(0.0);
        Double sommeDepense = ifcRepository.findByOperationTypeBetweenDate(filialeId, TypeOperationCaisse.DECAISSEMENT, beginDate, endDate).orElse(0.0);
        Double sommeEncaissement = ifcRepository.findByOperationTypeBetweenDate(filialeId, TypeOperationCaisse.ENCAISSEMENT, beginDate, endDate).orElse(0.0);
        Double recette = sumEspece + sumCheque + sumVirement + sumMobileMoney;
        Double sumVente = recette + sumCredit;
        info.put(SOMME_VENTE, sumVente);
        info.put(SOMME_CONSOMMATION, sumConsommation);
        info.put(SOMME_ESPECE, sumEspece);
        info.put(SOMME_VIREMENT, sumVirement);
        info.put(SOMME_CHEQUE, sumCheque);
        info.put(SOMME_CREDIT, sumCredit);
        info.put(RECETTE, recette);
        info.put(SOMME_MOBILE_MONEY, sumMobileMoney);
        info.put(SOMME_AVOIR, avoir);
        info.put(SOMME_DEPENSE, sommeDepense);
        info.put(SOMME_ENCAISSEMENT, sommeEncaissement);
        return info;
    }

    public Map<String, Double> getCashInfoByUserId(Long filialeId, Long userId, LocalDate beginDate, LocalDate endDate) {
        Map<String, Double> info = new HashMap<>();
        Double sumEspece = ifcRepository.findByTypePayementBetweenDateAndUserId(filialeId, userId, ModePayement.ESPECE, endDate, endDate).orElse(0.0);
        Double sumConsommation = ifcRepository.findByTypePayementBetweenDateAndUserId(filialeId, userId, ModePayement.CONSOMMATION, beginDate, endDate).orElse(0.0);
        Double sumVirement = ifcRepository.findByTypePayementBetweenDateAndUserId(filialeId, userId, ModePayement.VIREMENT, beginDate, endDate).orElse(0.0);
        Double sumCheque = ifcRepository.findByTypePayementBetweenDateAndUserId(filialeId, userId, ModePayement.CHEQUE, beginDate, endDate).orElse(0.0);
        Double sumCredit = ifcRepository.findByTypePayementBetweenDateAndUserId(filialeId, userId, ModePayement.CREDIT, beginDate, endDate).orElse(0.0);
        Double sumMobileMoney = ifcRepository.findByTypePayementBetweenDateAndUserId(filialeId, userId, ModePayement.MOBILE_MONEY, beginDate, endDate).orElse(0.0);
        Double avoir = ifcRepository.findByOperationTypeBetweenDateByUserId(filialeId, userId, TypeOperationCaisse.AVOIR, beginDate, endDate).orElse(0.0);
        Double sommeDepense = ifcRepository.findByOperationTypeBetweenDateByUserId(filialeId, userId, TypeOperationCaisse.DECAISSEMENT, beginDate, endDate).orElse(0.0);
        Double sommeEncaissement = ifcRepository.findByOperationTypeBetweenDateByUserId(filialeId, userId, TypeOperationCaisse.ENCAISSEMENT, beginDate, endDate).orElse(0.0);
        Double recette = sumEspece + sumCheque + sumVirement + sumMobileMoney;
        Double sumVente = recette + sumCredit;
        info.put(SOMME_VENTE, sumVente);
        info.put(SOMME_CONSOMMATION, sumConsommation);
        info.put(SOMME_ESPECE, sumEspece);
        info.put(SOMME_VIREMENT, sumVirement);
        info.put(SOMME_CHEQUE, sumCheque);
        info.put(SOMME_CREDIT, sumCredit);
        info.put(RECETTE, recette);
        info.put(SOMME_MOBILE_MONEY, sumMobileMoney);
        info.put(SOMME_AVOIR, avoir);
        info.put(SOMME_DEPENSE, sommeDepense);
        info.put(SOMME_ENCAISSEMENT, sommeEncaissement);
        return info;
    }


    public static String getSommeVente() {
        return SOMME_VENTE;
    }
    public static String getSommeEspece() {
        return SOMME_ESPECE;
    }
    public static String getSommeVirement() {
        return SOMME_VIREMENT;
    }
    public static String getSommeCheque() {
        return SOMME_CHEQUE;
    }
    public static String getSommeCredit() {
        return SOMME_CREDIT;
    }
    public static String getRecette() {
        return RECETTE;
    }
    public static String getSommeAvoir() {
        return SOMME_AVOIR;
    }
    public static String getSommeMobileMoney() {
        return SOMME_MOBILE_MONEY;
    }
    public static String getSommeDepense() {
        return SOMME_DEPENSE;
    }
    public static String getSommeEncaissement() {
        return SOMME_ENCAISSEMENT;
    }
    public static String getSommeConsommation() {
        return SOMME_CONSOMMATION;
    }
}
