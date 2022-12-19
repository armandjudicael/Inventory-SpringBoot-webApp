package mg.imwa.tenant.controller.restController;

import mg.imwa.tenant.model.tenantEntityBeans.ArticleUnite;
import mg.imwa.tenant.model.tenantEntityBeans.Unite;
import mg.imwa.tenant.model.wrapper.AuWrapper;
import mg.imwa.tenant.repository.ArticleUniteRepository;
import mg.imwa.tenant.repository.UniteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UnityRessource {

    @Autowired
    private UniteRepository uniteRepository;

    @Autowired
    private ArticleUniteRepository articleUniteRepository;

    @GetMapping(value = "/unites")
    public ResponseEntity<Object> getAllUnites() {
        return new ResponseEntity<>(uniteRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping("/unites/{id}")
    public ResponseEntity<Object> update(@RequestBody Unite unite, @PathVariable(value = "id") Long id) {
        Optional<Unite> uniteOptional = uniteRepository.findById(id);
        if (!uniteOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        unite.setId(id);
        uniteRepository.save(unite);
        return new ResponseEntity<>(unite, HttpStatus.OK);
    }


    @PutMapping("/unites/item-unity/{id}")
    public ResponseEntity<Object> updateItemUnity(@RequestBody AuWrapper wrapper, @PathVariable(value = "id") Long au_id) {
        Optional<ArticleUnite> auOptional = articleUniteRepository.findById(au_id);
        if (auOptional.isPresent()) {
            // article unite
            ArticleUnite au = auOptional.get();
            au.setPoids(wrapper.getPoids());
            au.setQuantiteNiveau(wrapper.getQuantiteNiveau());
            articleUniteRepository.save(au);
            // unite
            Unite unite = au.getUnite();
            unite.setDesignation(wrapper.getDesignationUnite());
            uniteRepository.save(unite);

            return new ResponseEntity<>(au, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/unites/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            uniteRepository.deleteById(id);
            return new ResponseEntity<>(" The category with the id =" + id + " is deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @PostMapping(value = "/unites")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> create(@RequestBody List<ArticleUnite> articleUnites) {
        Iterable<ArticleUnite> iterable = articleUniteRepository.saveAll(articleUnites);
        return new ResponseEntity<>(iterable, HttpStatus.CREATED);
    }

    ;

    @PostMapping(value = "/unites/one")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> create(@RequestBody ArticleUnite au) {
        ArticleUnite save = articleUniteRepository.save(au);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }
}
