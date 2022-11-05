package mg.imwa.tenant.controller.restController;
import mg.imwa.tenant.model.tenantEntityBeans.Payement;
import mg.imwa.tenant.model.tenantEntityBeans.Vente;
import mg.imwa.tenant.repository.SalesRepository;
import mg.imwa.tenant.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class SalesRessource {

    @Autowired private SalesRepository salesRepository;

    @Autowired private SalesService salesService;

    @GetMapping("/sales/subsdiary/{filiale-id}")
    public ResponseEntity<Object> getAllInvoice(@PathVariable("filiale-id") Long filialeId) {
        LocalDate now = LocalDate.now(Clock.systemDefaultZone());
        return new ResponseEntity<>(salesService.getAllInvoiceBetweenDate(filialeId, now, now), HttpStatus.OK);
    }

    @GetMapping("/sales/subsdiary/{filiale-id}/store/{store-id}")
    public ResponseEntity<Object> getAllInvoiceByStore(@PathVariable("filiale-id") Long filialeId, @PathVariable("store-id") Long storeId) {
        LocalDate now = LocalDate.now(Clock.systemDefaultZone());
        return new ResponseEntity<>(salesService.getAllInvoiceByStoreIdAndBetweenDate(filialeId, storeId, now, now), HttpStatus.OK);
    }

    @GetMapping("/sales/subsdiary/{filiale-id}/filter-type/{type}/{value}")
    public ResponseEntity<Object> getAllInvoiceByStoreBetweenDate(
            @PathVariable("filiale-id") Long filialeId,
            @PathVariable("type") String type,
            @PathVariable("value") String value) {
        return new ResponseEntity<>(salesService.filterSalesBy(filialeId, type, value), HttpStatus.OK);
    }

    @GetMapping("/sales/subsdiary/{filiale-id}/between-date/{begin}/{end}")
    public ResponseEntity<Object> getAllSalesBetweenDate(
            @PathVariable("filiale-id") Long filialeId,
            @PathVariable("begin") String begin,
            @PathVariable("end") String end) {
        return new ResponseEntity<>(salesService.getAllInvoiceBetweenDate(filialeId, begin, end), HttpStatus.OK);
    }

    @GetMapping("/sales/{reference}")
    public ResponseEntity<Object> getAll(@PathVariable("reference") String reference) {
        return new ResponseEntity<>(salesRepository.getInvoiceBySaleRef(reference).get(), HttpStatus.OK);
    }

    @GetMapping("/sales/store/{}")
    public ResponseEntity<Object> getSaleByClientName(@PathVariable("name") String name){
        return new ResponseEntity<>(salesService.findByClientName(name), HttpStatus.OK);
    }

    @PostMapping("/sales")
    public ResponseEntity<Object> create(@RequestBody Vente v) {
        return new ResponseEntity<>(salesService.save(v), HttpStatus.OK);
    }

    @PutMapping("/sales/{id}")
    public ResponseEntity<Object> update(@RequestBody Vente vente, @PathVariable("id") Long id) {
        Optional<Vente> optional = salesRepository.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        vente.setId(id);
        salesRepository.save(vente);
        return new ResponseEntity<>(vente, HttpStatus.OK);
    }

    @PutMapping("/sales/{id}/payements")
    public ResponseEntity<Object> updateSale(@RequestBody Payement payement, @PathVariable("id") Long id) {
        salesRepository.findById(id).ifPresent(vente -> {
            vente.getPayements().add(payement);
            salesRepository.save(vente);
        });
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @DeleteMapping("/sales/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            salesRepository.deleteById(id);
            return new ResponseEntity<>(" La vente avec l'id =" + id + " est supprimer avec succes", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

}