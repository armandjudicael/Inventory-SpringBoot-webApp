package mg.imwa.tenant.controller.restController;

import mg.imwa.tenant.model.tenantEntityBeans.PersonnePhysique;
import mg.imwa.tenant.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PersonnePhysiqueController {

    @Autowired
    private PersonService personService;

    @PostMapping("/persons/fonction/{fonctionName}")
    public ResponseEntity<Object> create(@RequestBody PersonnePhysique ph, @PathVariable("fonctionName")String fonctionName){
        return new ResponseEntity<>(personService.persist(ph,fonctionName), HttpStatus.OK);
    }

}
