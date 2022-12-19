package mg.imwa.tenant.controller.restController;

import mg.imwa.tenant.model.tenantEntityBeans.InfoArticleMagasin;
import mg.imwa.tenant.model.tenantEntityBeans.Magasin;
import mg.imwa.tenant.model.tenantEntityBeans.Vente;
import mg.imwa.tenant.model.wrapper.InventoryViewWrapper;
import mg.imwa.tenant.repository.*;
import mg.imwa.tenant.service.ItemService;
import mg.imwa.tenant.service.MagasinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class StoreRessource {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private MagasinService magasinService;
    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    public void setMagasinRepository(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @GetMapping("/magasins")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(storeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/magasins/{id}/users")
    public ResponseEntity<Object> getAllUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userRepository.getAllUserByMagasinId(id), HttpStatus.OK);
    }

    @GetMapping("/magasins/{id}/activities")
    public ResponseEntity<Object> getAllActivities(@PathVariable("id") Long id) {
        List<InfoArticleMagasin> allByMagasin = activityRepository.findAllByStoreId(id);
        return new ResponseEntity<>(allByMagasin, HttpStatus.OK);
    }


//    @GetMapping("/magasins/{id}/activities/{articleId}")
//    public ResponseEntity<Object> getAllActivitiesByItem(@PathVariable("id")Long storeId, @PathVariable("articleId")Long itemId){
//        List<InfoArticleMagasin> allByMagasin = activityRepository.findAllByStoreAndItem(storeId, itemId);
//        return new ResponseEntity<>(allByMagasin,HttpStatus.OK) ;
//    }

//    @GetMapping("/magasins/{id}/activities/{articleId}/{uniteId}")
//    public ResponseEntity<Object> getAllActivitiesByItemAndUnit(@PathVariable("id")Long storeId,
//                                                                @PathVariable("articleId")Long itemId,@PathVariable("uniteId")Long uniteId){
//        List<InfoArticleMagasin> allByMagasin = activityRepository.findAllByStoreAndItemAndUnit(storeId, itemId,uniteId);
//        return new ResponseEntity<>(allByMagasin,HttpStatus.OK) ;
//    }

    @GetMapping("/magasins/{id}/sales/{type}/{name}")
    public ResponseEntity<Object> getAllSalesByName(@PathVariable("id") Long id,
                                                    @PathVariable("type") String type,
                                                    @PathVariable("name") String name) {
        List<Vente> list = type.equals("CLIENT") ?
                salesRepository.getVentesByClientName(id, name) :
                salesRepository.getVentesByProductName(id, name);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/magasins/{id}/inventories")
    public ResponseEntity<Object> getStoreInventories(@PathVariable("id") Long id) {
        List<InventoryViewWrapper> inventory = itemService.getInventoryByStore(id);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @GetMapping("/magasins/{id}/inventories/{itemName}")
    public ResponseEntity<Object> getStoreInventoryByItemName(@PathVariable("id") Long id, @PathVariable("itemName") String name) {
        List<InventoryViewWrapper> inventory = itemService.getInventoryByStoreAndItemName(id, name);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @GetMapping("/magasins/{id}/sales/{type}/{begin}/{end}")
    public ResponseEntity<Object> getAllSalesByDate(@PathVariable("id") Long id,
                                                    @PathVariable("type") String type,
                                                    @PathVariable("begin") String begin,
                                                    @PathVariable("end") String end) {
        if (type.equals("DATE"))
            return new ResponseEntity<>(magasinService.findAllSalesBetweenDate(id, begin, end), HttpStatus.OK);

        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/stores/{id}/sales")
    public ResponseEntity<Object> getSalesByStore(@PathVariable("id") Long storeId) {
        return new ResponseEntity<>(salesRepository.findByStoreId(storeId), HttpStatus.OK);
    }


    @GetMapping("/magasins/{id}/activities/{date}")
    public ResponseEntity<Object> getAllActivitiesByDate(@PathVariable("id") Long id, @PathVariable("date") LocalDate localDate) {
        List<InfoArticleMagasin> allByMagasin = activityRepository.findAllByDate(id, localDate);
        return new ResponseEntity<>(allByMagasin, HttpStatus.OK);
    }


    @GetMapping("/magasins/{id}/activities/{beginDate}/{endDate}")
    public ResponseEntity<Object> getAllActivitiesByDate(@PathVariable("id") Long id,
                                                         @PathVariable("beginDate") String begin,
                                                         @PathVariable("endDate") String end) {
        List<InfoArticleMagasin> allBetweenDate = magasinService.findAllBetweenDate(id, begin, end);
        return new ResponseEntity<>(allBetweenDate, HttpStatus.OK);
    }


    @GetMapping("/magasins/{id}")
    public ResponseEntity<Object> getMagasin(@PathVariable("id") Long id) {
        Optional<Magasin> optionalMagasin = storeRepository.findById(id);
        if (!optionalMagasin.isPresent())
            return new ResponseEntity<>(" Le magasin avec l'id " + id + " n'existe pas dans la base de donné", HttpStatus.NOT_FOUND);
        Magasin magasin = optionalMagasin.get();
        return new ResponseEntity<>(magasin, HttpStatus.OK);
    }

    @GetMapping("/magasins/{id}/filiales")
    public ResponseEntity<Object> getMagasinBy(@PathVariable("id") Long id) {
        return new ResponseEntity<>(storeRepository.findById(id).get().getFiliale(), HttpStatus.OK);
    }

    @GetMapping("/magasins/{id}/stocks")
    public ResponseEntity<Object> getStocksBy(@PathVariable("id") Long id) {
        return new ResponseEntity<>(itemService.getInventoryByStore(id), HttpStatus.OK);
    }

    @GetMapping("/magasins/{magasinId}/inventories/{articleId}/{uniteId}")
    public ResponseEntity<Object> getStocksBy(@PathVariable("magasinId") Long magasinId
            , @PathVariable("articleId") Long articleId
            , @PathVariable("uniteId") Long uniteId) {
        return new ResponseEntity<>(itemRepository.getStock(uniteId, magasinId, articleId), HttpStatus.OK);
    }


    @GetMapping("/magasins/{id}/inventory-alerts/{articleId}/{uniteId}")
    public ResponseEntity<Object> getInventoryAlert(@PathVariable("id") Long storeId,
                                                    @PathVariable("uniteId") Long uniteId,
                                                    @PathVariable("articleId") Long articleId) {
//        List<InventoryAlert> allByArticleAndUniteAndMagasin = inventoryAlertRepository.getAllByArticleAndUniteAndMagasin(storeId, articleId, uniteId);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping("/magasins")
    public ResponseEntity<Object> create(@RequestBody Magasin magasin) {
        Magasin save = storeRepository.save(magasin);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @PutMapping("/magasins/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Magasin magasin) {
        Optional<Magasin> optionalMagasin = storeRepository.findById(id);
        if (!optionalMagasin.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        magasin.setId(id);
        storeRepository.save(magasin);
        return new ResponseEntity<>(magasin, HttpStatus.OK);
    }

    @DeleteMapping("/magasins/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            storeRepository.deleteById(id);
            return new ResponseEntity<>(" Le magasin avec l'id = " + id + " est supprimer avec succes ", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

}
