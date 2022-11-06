package mg.imwa.tenant.service;

import mg.imwa.tenant.model.tenantEntityBeans.Vente;
import mg.imwa.tenant.model.wrapper.FactureWrapper;
import mg.imwa.tenant.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalesService {
    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private RepoUtils repoUtils;

    public List<Vente> getAllInvoiceBetweenDate(Long filialeId, LocalDate begin, LocalDate end) {
        return salesRepository.getInvoiceBetweenDate(filialeId, begin, end);
    }

    public List<Vente> filterSalesBy(Long filialeId, String type, String value) {
        switch (type) {
            case "REFERENCE":
                return salesRepository.getSalesByRef(filialeId, value);
            case "CLIENT":
                return salesRepository.getSalesByCustomerName(filialeId, value);
            case "ARTICLE": {
                List<Vente> salesByItemName = salesRepository.getSalesByItemName(filialeId, value);
                salesByItemName.forEach(vente -> vente.getInfoArticleMagasin()
                        .removeIf(iam -> !iam.getArticle().getDesignation().toLowerCase().trim().equals(value)));
                return salesByItemName;
            }
        }
        return null;
    }

    public List<Vente> getAllInvoiceBetweenDate(Long filialeId, String begin, String end) {
        return salesRepository.getInvoiceBetweenDate(filialeId, LocalDate.parse(begin), LocalDate.parse(end));
    }


    public List<Vente> getAllInvoiceByStoreIdAndBetweenDate(Long filialeId, Long magasinId, LocalDate begin, LocalDate end) {
        return salesRepository.getSalesByStoreAndBetweenDate(filialeId, magasinId, begin, end);
    }

    public List<Vente> getSalesBySubsidiaryAndBetweenDate(Long filialeId, String begin, String end) {
        return salesRepository.getSalesBySubsidiaryAndBetweenDate(filialeId, LocalDate.parse(begin), LocalDate.parse(end));
    }

    public List<Vente> findByClientName(String name) {
        return salesRepository.getSalesByClientName(name);
    }


    public List<FactureWrapper> getFactureGroupByRefAndFilialeAndMagasin(Long magasinId, Long filialeId) {
        List<String> factureGroupByRef = salesRepository.getFactureGroupByRefAndFilialeAndMagasin(magasinId, filialeId);
        List<FactureWrapper> factureWrappers = initWrapper(factureGroupByRef);
        return factureWrappers;
    }

    private List<FactureWrapper> initWrapper(List<String> factureGroupByRef) {
        List<FactureWrapper> factureWrappers = new ArrayList<>();
        factureGroupByRef.forEach(s -> {
            String[] split = s.split(",");
            if (split.length != 0) {
                String reference = split[0];
                String montantTotal = split[1];
                String date = split[2];
                String client = split[3];
                String operateur = split[4];
                factureWrappers.add(new FactureWrapper(client, operateur, montantTotal, reference, date));
            }
        });
        return factureWrappers;
    }

    public List<FactureWrapper> getFactureGroupByRefAndFiliale(Long filialeId) {
        List<String> factureGroupByRef = salesRepository.getFactureGroupByRefAndFiliale(filialeId);
        List<FactureWrapper> factureWrappers = initWrapper(factureGroupByRef);
        return factureWrappers;
    }


    public Vente save(Vente v){
        String ref = repoUtils.generateRef("VENTE");
        v.getPayements().forEach(payement -> payement.setVente(v));
        v.setRefVente(ref);
        v.getInfoArticleMagasin().forEach(iam -> iam.setReference(ref));
        return salesRepository.save(v);
    }
}
