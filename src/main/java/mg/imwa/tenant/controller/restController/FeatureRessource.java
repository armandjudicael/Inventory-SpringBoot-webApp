package mg.imwa.tenant.controller.restController;

import mg.imwa.tenant.model.tenantEntityBeans.Fonctionnalite;
import mg.imwa.tenant.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class FeatureRessource {

    private FeatureRepository featureRepository;

    @Autowired

    public FeatureRessource(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    @GetMapping("/fonctionnalities")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(featureRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/fonctionnalities")
    public ResponseEntity<Object> create(@RequestBody Fonctionnalite fonctionnalite) {
        Fonctionnalite savedFonctionnality = featureRepository.save(fonctionnalite);
        return new ResponseEntity<>(savedFonctionnality, HttpStatus.OK);
    }

    @PutMapping("/fonctionnalities/{id}")
    public ResponseEntity<Object> update(@RequestBody Fonctionnalite fonctionnalite, @PathVariable("id") Long id) {
        Optional<Fonctionnalite> optional = featureRepository.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build(

            );
        }
        fonctionnalite.setId(id);
        featureRepository.save(fonctionnalite);
        return new ResponseEntity<>(fonctionnalite, HttpStatus.OK);
    }

    @PutMapping("/fonctionnalities/autorization/{feature-id}/{fonction-id}/{value}")
    public ResponseEntity<Object> updateAutorisation(@PathVariable("feature-id") Long featureId,
                                                     @PathVariable("fonction-id") Long fonctionId,
                                                     @PathVariable("value") Long value) {
        featureRepository.updateAutorisation(fonctionId, featureId, value);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @DeleteMapping("/fonctionnalities/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            featureRepository.deleteById(id);
            return new ResponseEntity<>(" The feature with the id =" + id + " is deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

}
