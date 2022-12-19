package mg.imwa.tenant.controller.restController;

import mg.imwa.tenant.model.entityEnum.DefaultPage;
import mg.imwa.tenant.model.tenantEntityBeans.Fonction;
import mg.imwa.tenant.repository.FonctionRepository;
import mg.imwa.tenant.repository.UserRepository;
import mg.imwa.tenant.service.FonctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class FonctionRessource{
    @Autowired
    private FonctionRepository fonctionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FonctionService fonctionService;

    @GetMapping("/fonctions")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(fonctionRepository.joinFetchfindAll(), HttpStatus.OK);
    }

    @PostMapping("/fonctions")
    public ResponseEntity<Object> create(@RequestBody Fonction fonction){
        return new ResponseEntity<>(fonctionService.save(fonction),HttpStatus.OK);
    }

    @GetMapping(value = "/fonctions/{id}/users")
    public ResponseEntity<Object> getUserByFonctionId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userRepository.getUserByFontionId(id), HttpStatus.OK);
    }

    @PutMapping("/fonctions/{id}")
    public ResponseEntity<Object> update(@RequestBody Fonction fonction, @PathVariable("id") Long id) {
        Optional<Fonction> optional = fonctionRepository.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        fonction.setId(id);
        fonctionRepository.save(fonction);
        return new ResponseEntity<>(fonction, HttpStatus.OK);
    }

    @PutMapping("/fonctions/{id}/welcome-page/{page-index}")
    public ResponseEntity<Object> updateWelcomePage(@PathVariable("id") Long id, @PathVariable("page-index") int index) {
        Optional<Fonction> optional = fonctionRepository.findById(id);
        if (optional.isPresent()) {
            Fonction fonction = optional.get();
            fonction.setDefaultPage(DefaultPage.values()[index]);
            return new ResponseEntity<>(fonctionRepository.save(fonction), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(" The fonction with the id = " + id + " doesn't exist", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/fonctions/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            fonctionRepository.deleteById(id);
            return new ResponseEntity<>(" The feature with the id =" + id + " is deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @GetMapping("/fonctions/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(fonctionRepository.findById(id), HttpStatus.OK);
    }
}
