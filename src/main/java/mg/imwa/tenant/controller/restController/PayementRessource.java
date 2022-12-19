package mg.imwa.tenant.controller.restController;

import mg.imwa.tenant.model.tenantEntityBeans.Payement;
import mg.imwa.tenant.service.PayementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1")
public class PayementRessource{

    @Autowired
    private PayementService payementService;

    @PostMapping("/payments")
    public ResponseEntity<Object> create(@RequestBody Payement payement){
        return new ResponseEntity<>(payementService.save(payement),HttpStatus.OK);
    }

    @PutMapping("/payments/{id}/status/{value}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id,@PathVariable("value") int status){
        payementService.updatePayement(id,status);
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @DeleteMapping("/payments/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id){
        payementService.delete(id);
        return new ResponseEntity<>("",HttpStatus.OK);
    }

}
