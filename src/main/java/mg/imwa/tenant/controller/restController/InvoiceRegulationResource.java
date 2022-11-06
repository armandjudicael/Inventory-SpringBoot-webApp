package mg.imwa.tenant.controller.restController;

import mg.imwa.tenant.model.tenantEntityBeans.Avoir;
import mg.imwa.tenant.repository.InvoiceRegulationRepository;
import mg.imwa.tenant.repository.VenteRepository;
import mg.imwa.tenant.service.RepoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class InvoiceRegulationResource {

    private InvoiceRegulationRepository invoiceRegulationRepository;

    @Autowired
    private RepoUtils repoUtils;

    @Autowired
    private VenteRepository venteRepository;

    @PostMapping(value = "/regulations")
    public ResponseEntity<Object> create(@RequestBody Avoir avoir) {
        String ref = repoUtils.generateRef("AVOIR");
        avoir.setRefAvoir(ref);
        avoir.getInfoFilialeCaisse().setReference(ref);
        avoir.getInfoArticleMagasin().forEach(iam -> iam.setReference(ref));
        Long venteId = avoir.getVente().getId();
        venteRepository.findById(venteId).ifPresentOrElse(vente -> {
            vente.setIsConcernedByInvoiceRegulation(true);
            venteRepository.save(vente);
        }, () -> {
        });
        return new ResponseEntity<>(invoiceRegulationRepository.save(avoir), HttpStatus.CREATED);
    }

    ;

    @GetMapping(value = "/regulations/{saleId}")
    public ResponseEntity<Object> getRegulationBy(@PathVariable("saleId") Long id) {
        Long count = invoiceRegulationRepository.getInvoiceBySaleId(id);
        return new ResponseEntity<>(count == null || count == 0, HttpStatus.OK);
    }

    ;

    @Autowired
    public void setInvoiceRegulationRepository(InvoiceRegulationRepository invoiceRegulationRepository) {
        this.invoiceRegulationRepository = invoiceRegulationRepository;
    }
}
