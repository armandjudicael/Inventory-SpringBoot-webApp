package mg.imwa.admin.controller;

import mg.imwa.admin.model.Company;
import mg.imwa.admin.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/imwa/v1")
public class CompanyController{

    @Autowired private CompanyService companyService;

    @PostMapping("/companies")
    public Company create(@RequestBody Company company){
        return companyService.create(company);
    }

    @GetMapping("/companies")
    public ResponseEntity<Object> getAllCompany(){
        return new ResponseEntity<Object>(companyService.getAll(),HttpStatus.OK);
    }

}
