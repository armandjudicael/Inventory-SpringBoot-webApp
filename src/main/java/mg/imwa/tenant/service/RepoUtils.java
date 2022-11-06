package mg.imwa.tenant.service;

import mg.imwa.tenant.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class RepoUtils{

    @Autowired private SalesRepository salesRepository;

    @Autowired private SortieRepository sortieRepository;

    @Autowired private SupplyRepository supplyRepository;

    @Autowired private InvoiceRegulationRepository invoiceRegulationRepository;

    @Autowired private TransfertRepository transfertRepository;

    @Autowired private IamRepository iamRepository;

    @Autowired private VoyageRepository voyageRepository;

    public String generateRef(String type) {
        switch (type) {
            case "VENTE": {
                Long value = salesRepository.getLastValue();
                return "VT-" + (value == null ? 1L : value);
            }
            case "INVENTAIRE": {
                Long iamValue = iamRepository.getLastValue();
                return "INV-" + (iamValue == null ? 1L : iamValue);
            }
            case "SORTIE": {
                Long value = sortieRepository.getLastValue();
                return "SORT-" + (value == null ? 1L : value);
            }
            case "ENTRE": {
                Long value = supplyRepository.getLastValue();
                return "ENT-" + (value == null ? 1L : value);
            }
            case "AVOIR": {
                Long value = invoiceRegulationRepository.getLastValue();
                return "AV-" + (value == null ? 1L : value);
            }
            case "TRANSFERT": {
                Long value = transfertRepository.getLastValue();
                return "TF-" + (value == null ? 1L : value);
            }
            case "ENCAISSEMENT": {
                Long iamValue = iamRepository.getLastValue();
                return "ENC-" + (iamValue == null ? 1L : iamValue);
            }
            case "VOYAGE": {
                Long iamValue = voyageRepository.getLastValue();
                return "EMB-" + (iamValue == null ? 1L : iamValue);
            }
            case "DECAISSEMENT": {
                Long iamValue = iamRepository.getLastValue();
                return "DEC-" + (iamValue == null ? 1L : iamValue);
            }
            default: return String.valueOf(LocalDate.now().getDayOfMonth()) + LocalTime.now();
        }
    }

}
