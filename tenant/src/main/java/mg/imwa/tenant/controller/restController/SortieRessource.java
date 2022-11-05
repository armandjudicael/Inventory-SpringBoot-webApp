package mg.imwa.tenant.controller.restController;

import mg.imwa.tenant.model.tenantEntityBeans.Sortie;
import mg.imwa.tenant.repository.SortieRepository;
import mg.imwa.tenant.service.RepoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SortieRessource {
    @Autowired
    private SortieRepository sortieRepository;

    @Autowired
    private RepoUtils repoUtils;

    @PostMapping(value = "/sorties")
    public ResponseEntity<Object> create(@RequestBody List<Sortie> sortieList) {
        String ref = repoUtils.generateRef("SORTIE");
        sortieList.forEach(sortie -> sortie.getInfoArticleMagasin().setReference(ref));
        return new ResponseEntity<>(sortieRepository.saveAll(sortieList), HttpStatus.CREATED);
    }

    ;

}
