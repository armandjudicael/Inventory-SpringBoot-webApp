package mg.imwa.tenant.controller.restController;

import mg.imwa.tenant.model.tenantEntityBeans.Trajet;
import mg.imwa.tenant.repository.TrajetRepository;
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
public class TrajetRessource {

    @Autowired
    private TrajetRepository trajetRepository;

    @PostMapping(value = "/trajets")
    public ResponseEntity<Object> create(@RequestBody Trajet trajet) {
        return new ResponseEntity<>(trajetRepository.save(trajet), HttpStatus.CREATED);
    }

    ;
}
