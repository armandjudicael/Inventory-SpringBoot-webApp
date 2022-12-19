package mg.imwa.tenant.controller.restController;

import mg.imwa.tenant.model.tenantEntityBeans.MaterielTransport;
import mg.imwa.tenant.repository.MaterielTransportRepository;
import mg.imwa.tenant.repository.PersonnePhysiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class MaterielTransportRessource {
    @Autowired
    private PersonnePhysiqueRepository psRepository;
    @Autowired
    private MaterielTransportRepository mtRepository;

    @GetMapping(value = "/materieltransport")
    public ResponseEntity<Object> getAllMaterielDeTransport() {
        return new ResponseEntity<>(mtRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping("/materieltransport/{id}")
    public ResponseEntity<Object> update(@RequestBody MaterielTransport materielTransport, @PathVariable(value = "id") Long id) {
        Optional<MaterielTransport> byId = mtRepository.findById(id);
        if (byId.isPresent()) {
            MaterielTransport newMt = byId.get();
            /* RESPONSABLE */
            Long respId = materielTransport.getResponsable().getId();
            psRepository.findById(respId).ifPresent(newMt::setResponsable);

            /* reference */
            newMt.setReference(materielTransport.getReference());

            /* type */
            newMt.setTypeMateriel(materielTransport.getTypeMateriel());

            newMt.setIsLocation(materielTransport.getIsLocation());

            return new ResponseEntity<>(mtRepository.save(newMt), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/materieltransport/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            mtRepository.deleteById(id);
            return new ResponseEntity<>(" The materiel de transport with the id =" + id + " is deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @PostMapping(value = "/materieltransport")
    public ResponseEntity<Object> create(@RequestBody MaterielTransport materielTransport) {
        MaterielTransport save = mtRepository.save(materielTransport);
        Long id = save.getResponsable().getId();
        psRepository.findById(id).ifPresent(save::setResponsable);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    ;

}
