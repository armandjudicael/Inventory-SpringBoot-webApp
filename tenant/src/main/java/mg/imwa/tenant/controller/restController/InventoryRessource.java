package mg.imwa.tenant.controller.restController;

import mg.imwa.tenant.model.wrapper.InventoryWrapper;
import mg.imwa.tenant.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class InventoryRessource {

    @Autowired
    private ItemService itemService;

    @GetMapping("/inventories/stores/{id}")
    public ResponseEntity<Object> getInventories(@PathVariable("id") Long id) {
        return new ResponseEntity<>(itemService.getInventoryByStore(id), HttpStatus.OK);
    }

    @GetMapping("/inventories")
    public ResponseEntity<Object> getInventories() {
        return new ResponseEntity<>(itemService.getAllInventories(), HttpStatus.OK);
    }

    @GetMapping("/inventories/{filiale-id}")
    public ResponseEntity<Object> getInventoriesTotal(@PathVariable("filiale-id") Long filialeId) {
        return new ResponseEntity<>(itemService.getTotalInventory(filialeId), HttpStatus.OK);
    }

    @GetMapping("/inventories/{filiale-id}/total-values")
    public ResponseEntity<Object> getInventoriesValueTotal(@PathVariable("filiale-id") Long filialeId) {
        return new ResponseEntity<>(itemService.getTotalInventoryValues(filialeId, null), HttpStatus.OK);
    }

    @GetMapping("/inventories/{filiale-id}/store/{store-id}/total-values")
    public ResponseEntity<Object> getInventoriesValueTotalByStoreId(
            @PathVariable("filiale-id") Long filialeId,
            @PathVariable("store-id") Long storeId) {
        return new ResponseEntity<>(itemService.getTotalInventoryValues(filialeId, storeId), HttpStatus.OK);
    }


    @PostMapping("/inventories")
    public ResponseEntity<Object> createInventories(@RequestBody InventoryWrapper inventoryWrapper) {
        return new ResponseEntity<>(itemService.getAllInventories(), HttpStatus.OK);
    }
}
