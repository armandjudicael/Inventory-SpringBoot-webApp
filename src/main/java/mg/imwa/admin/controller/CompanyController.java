package mg.imwa.admin.controller;
import mg.imwa.admin.model.Company;
import mg.imwa.admin.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/imwa/v1")
public class CompanyController{
    @Autowired private CompanyService companyService;

    @PostMapping("/companies")
    public ResponseEntity<Object> create(@RequestBody Company company){
        Company company1 = companyService.create(company);
        if (company1!=null) new ResponseEntity<>(company1,HttpStatus.CREATED);
        return new ResponseEntity<>( " The company with the name "+company.getNom().toLowerCase()+" already exist !" ,HttpStatus.EXPECTATION_FAILED);
    }
    @GetMapping("/companies")
    public ResponseEntity<Object> getAllCompany(){
        return new ResponseEntity<Object>(companyService.getAll(),HttpStatus.OK);
    }
    @PutMapping("/companies/{id}")
    public  ResponseEntity<Object> update(@RequestBody Company company,@PathVariable Long id){
        return new ResponseEntity<>(companyService.update(company,id),HttpStatus.OK);
    }
    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        return new ResponseEntity<>(companyService.delete(id),HttpStatus.OK);
    }
}
