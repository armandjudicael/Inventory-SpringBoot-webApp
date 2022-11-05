package mg.imwa.tenant.controller.restController;

import mg.imwa.tenant.model.tenantEntityBeans.ClientFournisseur;
import mg.imwa.tenant.repository.ClientFournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ClientFournisseurRessource {

    @Autowired
    private ClientFournisseurRepository cfRepository;

    @GetMapping(value = "/externalEntities/{typeCf}/{filialeId}")
    public ResponseEntity<Object> getAllByType(@PathVariable(name = "typeCf") Integer type, @PathVariable(name = "filialeId") Long filialeId) {
        return new ResponseEntity<>(cfRepository.getAllExternalEntities(type, filialeId), HttpStatus.OK);
    }

    @GetMapping(value = "/externalEntities")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(cfRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/externalEntities/{typeCf}/{filialeId}/name/{value}")
    public ResponseEntity<Object> getAllByName(@PathVariable(name = "typeCf") Integer type, @PathVariable("filialeId") Long filialeId, @PathVariable("value") String value) {
        return new ResponseEntity<>(cfRepository.getAllCustomerByName(type, filialeId, value), HttpStatus.OK);
    }

    @GetMapping(value = "/externalEntities/{id}/dettes")
    public ResponseEntity<Object> getAllTrosa(@PathVariable(value = "id") Long id) {
        Optional<ClientFournisseur> optional = cfRepository.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(cfRepository.findAll(), HttpStatus.OK);
    }


    @PutMapping("/externalEntities/{id}")
    public ResponseEntity<Object> update(@RequestBody ClientFournisseur cf, @PathVariable(value = "id") Long id) {
        Optional<ClientFournisseur> optional = cfRepository.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cf.setId(id);
        cfRepository.save(cf);
        return new ResponseEntity<>(cf, HttpStatus.OK);
    }

    @DeleteMapping("/externalEntities/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            cfRepository.deleteById(id);
            return new ResponseEntity<>(" Le fournisseur avec l'id =" + id + " est supprimer avec succes ", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @PostMapping(value = "/externalEntities")
    public ResponseEntity<Object> create(@RequestBody ClientFournisseur cf) {
        ClientFournisseur savedCf = cfRepository.save(cf);
        return new ResponseEntity<>(savedCf, HttpStatus.CREATED);
    }

}
