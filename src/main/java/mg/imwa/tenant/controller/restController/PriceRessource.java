package mg.imwa.tenant.controller.restController;

import mg.imwa.tenant.model.tenantEntityBeans.PrixArticleFiliale;
import mg.imwa.tenant.repository.PricesRepository;
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
public class PriceRessource{

    @Autowired
    private PricesRepository repository;

    @PostMapping("/prices")
    public ResponseEntity<Object> create(@RequestBody List<PrixArticleFiliale> prixArticleFilialeList) {
        return new ResponseEntity<>(repository.saveAll(prixArticleFilialeList), HttpStatus.OK);
    }

}
