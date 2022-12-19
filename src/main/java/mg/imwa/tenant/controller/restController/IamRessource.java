package mg.imwa.tenant.controller.restController;

import mg.imwa.tenant.model.tenantEntityBeans.InfoArticleMagasin;
import mg.imwa.tenant.repository.IamRepository;
import mg.imwa.tenant.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class IamRessource {
    @Autowired
    private IamRepository iamRepository;
    @Autowired
    private ItemService itemService;

    @PostMapping("/info")
    public ResponseEntity<Object> create(@RequestBody InfoArticleMagasin[] infoArticleMagasinTab) {
        InfoArticleMagasin infoArticleMagasin = itemService.updateInventory(infoArticleMagasinTab);
        return new ResponseEntity<>(iamRepository.save(infoArticleMagasin), HttpStatus.OK);
    }

    @GetMapping("/info/check/{unite-id}")
    public ResponseEntity<Object> checkUnity(@PathVariable("unite-id") Long uniteId) {
        return new ResponseEntity<>(iamRepository.checkUnite(uniteId) == 0, HttpStatus.OK);
    }

}
