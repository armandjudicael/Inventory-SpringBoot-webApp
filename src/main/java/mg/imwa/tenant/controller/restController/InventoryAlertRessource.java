package mg.imwa.tenant.controller.restController;

import mg.imwa.tenant.repository.InventoryAlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Lob;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class InventoryAlertRessource {
    @Autowired
    private InventoryAlertRepository inventoryAlertRepository;

    @PutMapping("/inventories-alert/{filialeId}/{articleId}/{quantite}")
    public ResponseEntity<Object> create(@PathVariable("filialeId") Long filialeId,
                                         @PathVariable("articleId") Long articleId,
                                         @PathVariable("quantite") Double quantite) {
//        InventoryAlertId inventoryAlertId = new InventoryAlertId();
//        inventoryAlertId.setArticleId(articleId);
//        inventoryAlertId.setFilialeId(filialeId);
//        Optional<InventoryAlert> inventoryAlert1 = inventoryAlertRepository.findById(inventoryAlertId);
//
        inventoryAlertRepository.updateInventoryAlert(filialeId, articleId, quantite);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/inventories-alert/{filialeId}/{articleId}")
    public ResponseEntity<Object> create(@PathVariable("filialeId") Long filialeId,
                                         @PathVariable("articleId") Long articleId) {
        return new ResponseEntity<>(inventoryAlertRepository.getInventoryAlertBy(filialeId, articleId), HttpStatus.CREATED);
    }


}
