package mg.imwa.tenant.controller.restController;

import mg.imwa.tenant.model.tenantEntityBeans.InfoArticleVoyage;
import mg.imwa.tenant.repository.IavRepository;
import mg.imwa.tenant.service.IavService;
import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmOuterJoinEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class IavRessource {

    @Autowired
    private IavService iavService;
    @Autowired
    private IavRepository iavRepository;

    @PostMapping("/iav")
    public ResponseEntity<Object> create(@RequestBody InfoArticleVoyage iav) {
        return new ResponseEntity<>(iavService.persit(iav), HttpStatus.CREATED);
    }

    @PutMapping("/iav/{id}")
    public ResponseEntity<Object> update(@RequestBody InfoArticleVoyage newIav, @PathVariable("id") Long id) {
        Optional<InfoArticleVoyage> optional = iavRepository.findById(id);
        if (optional.isPresent()) {
            InfoArticleVoyage iav = optional.get();
            Double newQuantity = iav.getQuantite() - newIav.getQuantite();
            iav.setQuantite(newQuantity);
            InfoArticleVoyage save = iavRepository.save(iav);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(" The iav with the id =" + id + " doesn't exist ", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/iav/voyage/{id}")
    public ResponseEntity<Object> getAll() {

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PutMapping("/iav/{id}/price")
    public ResponseEntity<Object> updatePrice(@RequestBody InfoArticleVoyage newIav, @PathVariable("id") Long id) {
        Optional<InfoArticleVoyage> optional = iavRepository.findById(id);
        if (optional.isPresent()) {
            InfoArticleVoyage iav = optional.get();
            Double prixVente = newIav.getPrixVente();
            Double prixAchat = newIav.getPrixAchat();
            Double prixTransport = newIav.getPrixTransport();
            iav.setPrixAchat(prixAchat);
            iav.setPrixVente(prixVente);
            iav.setPrixTransport(prixTransport);
            InfoArticleVoyage save = iavRepository.save(iav);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(" The iav with the id =" + id + " doesn't exist ", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/iav/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(iavService.deleteById(id), HttpStatus.CREATED);
    }

}
