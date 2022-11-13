package mg.imwa.tenant.controller.restController;

import mg.imwa.admin.repository.AdminSubsidiaryRepository;
import mg.imwa.tenant.model.tenantEntityBeans.ArticleUnite;
import mg.imwa.tenant.model.tenantEntityBeans.Filiale;
import mg.imwa.tenant.model.tenantEntityBeans.InfoArticleMagasin;
import mg.imwa.tenant.model.tenantEntityBeans.PrixArticleFiliale;
import mg.imwa.tenant.model.wrapper.InventoryViewWrapper;
import mg.imwa.tenant.repository.*;
import mg.imwa.tenant.service.ActivityService;
import mg.imwa.tenant.service.ItemService;
import mg.imwa.tenant.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class SubsidiaryRessource{

    @Autowired private ActivityService activityService;

    @Autowired private UserRepository userRepository;

    @Autowired private SubsidiaryRepository subsidiaryRepository;

    @Autowired private PricesRepository pricesRepository;

    @Autowired private TrosaRepository trosaRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private SalesService salesService;

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping(value = "/subsidiaries")
    public ResponseEntity<Object> getAllSubdiaries() {
        return new ResponseEntity<>(subsidiaryRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/subsidiaries/{id}/sales")
    public ResponseEntity<Object> getAllSubdiariesSales(@PathVariable("id") Long filialeId) {
        LocalDate now = LocalDate.now(Clock.systemDefaultZone());
        return new ResponseEntity<>(salesService.getAllInvoiceBetweenDate(filialeId, now, now), HttpStatus.OK);
    }

    @GetMapping(value = "/subsidiaries/{id}/trosa")
    public ResponseEntity<Object> getAllSubdiariesTrosa(@PathVariable("id") Long filialeId) {
        return new ResponseEntity<>(trosaRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/subsidiaries/{id}/stores")
    public ResponseEntity<Object> getAllSubsidiaryStore(@PathVariable("id") Long filialeId) {
        return new ResponseEntity<>(storeRepository.findAllByFiliale(filialeId), HttpStatus.OK);
    }

    @GetMapping(value = "/subsidiaries/{id}/{uniteId}/{articleId}")
    public ResponseEntity<Object> getSubdiaryProductPrice(@PathVariable("id") Long filialeId,
                                                          @PathVariable("uniteId") Long uniteId,
                                                          @PathVariable("articleId") Long articleId) {
        List<PrixArticleFiliale> all = pricesRepository.findAll(uniteId, articleId, filialeId);
        return new ResponseEntity<>(all, HttpStatus.OK);
    }


    @GetMapping(value = "/subsidiaries/{id}/items/{itemName}")
    public ResponseEntity<Object> getItemByName(@PathVariable("id") Long filialeId, @PathVariable("itemName") String itemName) {
        List<ArticleUnite> allByItemName = itemRepository.getAllByItemName(itemName);
        return new ResponseEntity<>(allByItemName, HttpStatus.OK);
    }

    @GetMapping("/subsidiaries/{id}/activities")
    public ResponseEntity<Object> getAllActivities(@PathVariable("id") Long id) {
        LocalDate now = LocalDate.now(Clock.systemDefaultZone());
        List<InfoArticleMagasin> allByMagasin = activityService.findAllByFilialeIdBetweenDate(id, now, now);
        return new ResponseEntity<>(allByMagasin, HttpStatus.OK);
    }

    @GetMapping("/subsidiaries/{id}/activities/date/{begin}/{end}")
    public ResponseEntity<Object> getAllActivitiesBetweenDate(@PathVariable("id") Long id,
                                                              @PathVariable("begin") String begin,
                                                              @PathVariable("end") String end) {
        List<InfoArticleMagasin> allByMagasin = activityService.findAllByFilialeIdBetweenDate(id, LocalDate.parse(begin), LocalDate.parse(end));
        return new ResponseEntity<>(allByMagasin, HttpStatus.OK);
    }

    @GetMapping(value = "/subsidiaries/{id}/inventories")
    public ResponseEntity<Object> getSubsidiariesInventoriesAlert(@PathVariable("id") Long filialeId) {
        List<InventoryViewWrapper> subsidiaryInventoryByStoreAndItemName = itemService.getAllInventoryAlert(filialeId);
        return new ResponseEntity<>(subsidiaryInventoryByStoreAndItemName, HttpStatus.OK);
    }

    @GetMapping(value = "/subsidiaries/{id}/itemsInfo/{name}")
    public ResponseEntity<Object> getSubsidiariesItemsInfo(@PathVariable("id") Long filialeId
            , @PathVariable("name") String name) {
        return new ResponseEntity<>(itemService.getAllItemInfoByName(filialeId, name), HttpStatus.OK);
    }

    @GetMapping(value = "/subsidiaries/{id}/itemsInfo")
    public ResponseEntity<Object> getSubsidiariesItemsInfo(@PathVariable("id") Long filialeId) {
        return new ResponseEntity<>(itemService.getAllItemInfo(filialeId), HttpStatus.OK);
    }


    @PutMapping(value = "/subsidiaries/{id}/alerts/{article-id}/{new_quantite}")
    public ResponseEntity<Object> updateQuantiteAlert(@PathVariable("id") Long filialeId,
                                                      @PathVariable("article-id") Long articleId
            , @RequestBody Double newValue) {
        itemRepository.updateQuantiteAlert(filialeId, articleId, newValue);
        return new ResponseEntity<>("", HttpStatus.OK);
    }


    @GetMapping(value = "/subsidiaries/{id}/inventories/{itemName}")
    public ResponseEntity<Object> getSubsidiariesInventories(@PathVariable("id") Long filialeId
            , @PathVariable("itemName") String itemName) {
        List<InventoryViewWrapper> subsidiaryInventoryByStoreAndItemName = itemService.getSubsidiaryInventoryByStoreAndItemName(filialeId, itemName);
        return new ResponseEntity<>(subsidiaryInventoryByStoreAndItemName, HttpStatus.OK);
    }

    @GetMapping(value = "/subsidiaries/{id}/prices/{itemName}")
    public ResponseEntity<Object> getPricesByItemName(@PathVariable("id") Long filialeId
            , @PathVariable("itemName") String itemName) {
        List<PrixArticleFiliale> all = pricesRepository.findAllByLastDateAndItemName(filialeId, itemName);
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping(value = "/subsidiaries/{id}/prices")
    public ResponseEntity<Object> getPrices(@PathVariable("id") Long filialeId) {
        return new ResponseEntity<>(pricesRepository.findAllByLastDate(filialeId), HttpStatus.OK);
    }

    @GetMapping(value = "/subsidiaries/{id}/items")
    public ResponseEntity<Object> getItem(@PathVariable("id") Long filialeId) {
        List<ArticleUnite> allByItemName = itemRepository.getAll();
        return new ResponseEntity<>(allByItemName, HttpStatus.OK);
    }


    @GetMapping(value = "/subsidiaries/{id}/users")
    public ResponseEntity<Object> getAllUser(@PathVariable("id") Long filialeId) {
        return new ResponseEntity<>(userRepository.findAllBySubsidiary(filialeId), HttpStatus.OK);
    }


    @PutMapping("/subsidiaries/{id}")
    public ResponseEntity<Object> update(@RequestBody Filiale filiale, @PathVariable(value = "id") Long id) {
        Optional<Filiale> societeOptional = subsidiaryRepository.findById(id);
        if (!societeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        filiale.setId(id);
        subsidiaryRepository.save(filiale);
        return new ResponseEntity<>(filiale, HttpStatus.OK);
    }

    @DeleteMapping("/subsidiaries/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            subsidiaryRepository.deleteById(id);
            return new ResponseEntity<>(" The subsidiary with the id =" + id + " is deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }


    @Autowired
    private AdminSubsidiaryRepository adminSubsidiaryRepository;

    @PostMapping(value = "/subsidiaries")
    public ResponseEntity<Object> create(@RequestBody final Filiale filiale) {
        Filiale filiale1 = subsidiaryRepository.save(filiale);
        return new ResponseEntity<>(filiale1, HttpStatus.CREATED);
    }

    ;
}
