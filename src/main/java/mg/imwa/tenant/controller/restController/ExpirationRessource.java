package mg.imwa.tenant.controller.restController;


import mg.imwa.tenant.model.wrapper.ExpirationDateWrapper;
import mg.imwa.tenant.repository.ItemRepository;
import mg.imwa.tenant.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ExpirationRessource {

    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping(value = "/expirations/{filiale-id}/{product-name}")
    public ResponseEntity<Object> getExpirationByProductName(@PathVariable("filiale-id") Long filialeId,
                                                             @PathVariable("product-name") String name) {
        return new ResponseEntity<>(itemService.getProductByExpirationByProductName(name, filialeId), HttpStatus.OK);
    }

    @GetMapping(value = "/expirations/{filiale-id}/status/{status}")
    public ResponseEntity<Object> getExpirationByStatus(@PathVariable("filiale-id") Long filialeId,
                                                        @PathVariable("status") String name) {
        return new ResponseEntity<>(itemService.getProductByExpirationByStatus(name, filialeId), HttpStatus.OK);
    }

    @GetMapping(value = "/expirations/{magasin-id}")
    public ResponseEntity<Object> getExpirationByStore(@PathVariable("magasin-id") Long magasinId) {
        return new ResponseEntity<>(itemService.getProductByExpirationByStore(magasinId), HttpStatus.OK);
    }

    @PutMapping(value = "/expirations/{magasinId}/{articleId}/{uniteId}")
    public ResponseEntity<Object> getExpirationByStatus(@PathVariable("magasinId") Long magasinId,
                                                        @PathVariable("articleId") Long articleId,
                                                        @PathVariable("uniteId") Long uniteId,
                                                        @RequestBody ExpirationDateWrapper dateWrapper) {
        itemRepository.updateExpirationDate(magasinId, articleId, uniteId, dateWrapper.getNewDate(), dateWrapper.getOldDate());
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @Autowired
    public void setArticleService(ItemService itemService) {
        this.itemService = itemService;
    }
}
