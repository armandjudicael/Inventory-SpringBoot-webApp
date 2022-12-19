package mg.imwa.tenant.controller.restController;

import mg.imwa.tenant.model.tenantEntityBeans.Trosa;
import mg.imwa.tenant.repository.TrosaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TrosaRessource {

    @Autowired
    private TrosaRepository trosaRepository;

    @GetMapping(value = "/trosas")
    public ResponseEntity<Object> getAllCategories() {
        return new ResponseEntity<>(trosaRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/trosas/cf/{id}")
    public ResponseEntity<Object> getAlltrosas(@PathVariable("id") Long cfId) {
        return new ResponseEntity<>(trosaRepository.findAllByCfId(cfId), HttpStatus.OK);
    }

    @PutMapping("/trosas/{id}")
    public ResponseEntity<Object> update(@RequestBody Double newValue, @PathVariable(value = "id") Long id) {
        trosaRepository.updateReste(newValue, id);
        return new ResponseEntity<>(newValue, HttpStatus.OK);
    }

    @DeleteMapping("/trosas/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            trosaRepository.deleteById(id);
            return new ResponseEntity<>(" The Trosa with the id =" + id + " is deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @PostMapping(value = "/trosas")
    public ResponseEntity<Object> create(@RequestBody Trosa trosa) {
        Trosa save = trosaRepository.save(trosa);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

}
