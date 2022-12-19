package mg.imwa.tenant.service;

import mg.imwa.tenant.model.tenantEntityBeans.InfoArticleMagasin;
import mg.imwa.tenant.model.wrapper.ExpirationWrapper;
import mg.imwa.tenant.model.wrapper.InventoryViewWrapper;
import mg.imwa.tenant.model.wrapper.InventoryWrapper;
import mg.imwa.tenant.model.wrapper.ItemWrapper;
import mg.imwa.tenant.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    private final int MONTH = 30;
    private final int THREE_MONTH = 3 * MONTH;
    private final int TWO_MONTH = 2 * MONTH;

    public List<InventoryViewWrapper> getAllInventories() {
        List<String> stockWithPriceAndExpirationDate = itemRepository.getStockWithPriceAndExpirationDateByItemName();
        List<InventoryViewWrapper> list = createInventoryViewWrappers(stockWithPriceAndExpirationDate);
        return list;
    }

    public List<InventoryViewWrapper> getAllInventories(Long filialeId) {
        List<String> stockWithPriceAndExpirationDate = itemRepository.getSubsidiaryInventoryWithPriceAndExpirationDate(filialeId);
        List<InventoryViewWrapper> list = createInventoryViewWrappers(stockWithPriceAndExpirationDate);
        return list;
    }

    public List<InventoryViewWrapper> getTotalInventory(Long filialeId) {
        List<String> stockWithPriceAndExpirationDate = itemRepository.getTotalInventory(filialeId);
        List<InventoryViewWrapper> list = createInventoryViewWrappers(stockWithPriceAndExpirationDate);
        return list;
    }

    public List<ItemWrapper> getAllItemInfo(Long filialeId, Long magasinId) {
        List<String> strings = itemRepository.getSubsidiaryItemInfo(filialeId, magasinId);
        return createItemWrapper(strings);
    }

    public List<ItemWrapper> getAllItemInfo(Long filialeId) {
        List<String> strings = itemRepository.getSubsidiaryItemInfo(filialeId);
        return createItemWrapper(strings);
    }

    public List<ItemWrapper> getAllItemInfoByName(Long filialeId, String name) {
        List<String> strings = itemRepository.getSubsidiaryItemInfoByName(filialeId, name);
        return createItemWrapper(strings);
    }

    public List<ItemWrapper> getAllItemInfoByUniteName(Long filialeId, Long magasinId, String name) {
        List<String> strings = itemRepository.getSubsidiaryItemInfoByUniteName(filialeId, magasinId, name);
        return createItemWrapper(strings);
    }

    private ArrayList<ItemWrapper> createItemWrapper(List<String> strings) {
        List<String[]> collect = strings.stream().map(s -> s.split(",")).collect(Collectors.toList());
        ArrayList<ItemWrapper> wrappers = new ArrayList<>();
        collect.forEach(t -> {
            ItemWrapper itemWrapper = new ItemWrapper();
            itemWrapper.setItemId(t[0]);
            itemWrapper.setUniteId(t[1]);
            itemWrapper.setItemName(t[2]);
            itemWrapper.setUniteName(t[3]);
            itemWrapper.setStock(t[4]);
            itemWrapper.setPrice(t[5]);
            itemWrapper.setPoids(t[6]);
            itemWrapper.setQuantiteNiveau(t[7]);
            wrappers.add(itemWrapper);
        });
        return wrappers;
    }

    public List<InventoryViewWrapper> getAllInventoryAlert(Long filialeId) {
        List<String> stockWithPriceAndExpirationDate = itemRepository.getSubsidiaryInventoryAlert(filialeId);
        List<InventoryViewWrapper> list = createInventoryViewWrappers(stockWithPriceAndExpirationDate);
        return list;
    }

    public List<InventoryViewWrapper> getInventoryByStore(Long magasinId) {
        List<String> stockWithPriceAndExpirationDate = itemRepository.getStockWithPriceAndExpirationDateByItemName(magasinId);
        List<InventoryViewWrapper> list = createInventoryViewWrappers(stockWithPriceAndExpirationDate);
        return list;
    }

    public List<InventoryViewWrapper> getInventoryByStoreAndItemName(Long magasinId, String name) {
        List<String> stockWithPriceAndExpirationDate = itemRepository.getStockWithPriceAndExpirationDateByItemName(magasinId, name);
        List<InventoryViewWrapper> list = createInventoryViewWrappers(stockWithPriceAndExpirationDate);
        return list;
    }

    public Double getTotalInventoryValues(Long filialeId, Long magasinId) {
        if (magasinId != null) {
            Double inventoryValueTotal = itemRepository.getInventoryValueTotal(filialeId, magasinId);
            return inventoryValueTotal != null ? inventoryValueTotal : 0.0;
        }
        return itemRepository.getInventoryValueTotal(filialeId);
    }

    public List<InventoryViewWrapper> getSubsidiaryInventoryByStoreAndItemName(Long filialeId, String name) {
        List<String> stockWithPriceAndExpirationDate = itemRepository.getSubsidiaryInventoryWithPriceAndExpirationDateByItemName(filialeId, name);
        List<InventoryViewWrapper> list = createInventoryViewWrappers(stockWithPriceAndExpirationDate);
        return list;
    }

    public InfoArticleMagasin updateInventory(InfoArticleMagasin[] infoArticleMagasinTab) {
        Double sum = 0D;
        for (InfoArticleMagasin iam : infoArticleMagasinTab) {
            Long articleId = iam.getArticle().getId();
            Long uniteId = iam.getUnite().getId();
            Double quantiteNiveau = itemRepository.getQuantiteNiveau(uniteId, articleId);
            Double quantiteAjout = iam.getQuantiteAjout();
            sum += (quantiteAjout * quantiteNiveau);
        }
        InfoArticleMagasin infoArticleMagasin = infoArticleMagasinTab[0];
        infoArticleMagasin.setQuantiteAjout(sum);
        return infoArticleMagasin;
    }

    public List<ExpirationWrapper> getProductByExpiration(Long filialeId) {
        List<String[]> collect = itemRepository.getProductExpiration(filialeId).stream().map(s -> s.split(",")).collect(Collectors.toList());
        List<ExpirationWrapper> expirationWrapper = createExpirationWrapper(collect);
        return expirationWrapper;
    }

    public List<ExpirationWrapper> getProductByExpirationByStore(Long storeId) {
        List<String[]> collect = itemRepository.getProductExpirationByStore(storeId).stream().map(s -> s.split(",")).collect(Collectors.toList());
        List<ExpirationWrapper> list = createExpirationWrapper(collect);
        return list;
    }

    public List<ExpirationWrapper> getProductByExpirationByProductName(String name, Long filialeId) {
        List<String[]> collect = itemRepository.getProductExpirationByProductName(name, filialeId).stream().map(s -> s.split(",")).collect(Collectors.toList());
        List<ExpirationWrapper> list = createExpirationWrapper(collect);
        return list;
    }

    public List<ExpirationWrapper> getProductByExpirationByStatus(String status, Long filialeId) {
        var productExpirationByStatus = createExpirationDataByStatus(status, filialeId);
        List<String[]> collect = productExpirationByStatus.stream().map(s -> s.split(",")).collect(Collectors.toList());
        return createExpirationWrapper(collect);
    }

    private List<String> createExpirationDataByStatus(String status, Long filialeId) {
        switch (status) {
            case "Forte":
                return itemRepository.getProductExpirationByStatusStrong(filialeId, THREE_MONTH);
            case "Moyenne":
                return itemRepository.getProductExpirationByStatus(filialeId, MONTH, TWO_MONTH);
            case "Faible":
                return itemRepository.getProductExpirationByStatus(filialeId, 0, MONTH);
            case "Périmé":
                return itemRepository.getProductExpirationByStatusExpired(filialeId, 0);
            default:
                return itemRepository.getProductExpiration(filialeId);
        }
    }

    private List<ExpirationWrapper> createExpirationWrapper(List<String[]> collect) {
        List<ExpirationWrapper> list = new ArrayList<>();
        collect.forEach(strings -> {
            ExpirationWrapper expirationWrapper = new ExpirationWrapper();
            expirationWrapper.setNomArticle(strings[0]);
            expirationWrapper.setNomUnite(strings[1]);
            expirationWrapper.setDatePeremption(strings[2]);
            expirationWrapper.setQuantitePeremetion(strings[3]);
            expirationWrapper.setMagasinId(strings[4]);
            expirationWrapper.setArticleId(strings[5]);
            expirationWrapper.setUniteId(strings[6]);
            expirationWrapper.setExpirationStatus(dayCount2ExpirationStatus(Double.valueOf(strings[7])));
            list.add(expirationWrapper);
        });
        return list;
    }

    private String dayCount2ExpirationStatus(Double count) {
        if (count >= THREE_MONTH) return "forte";
        if (count <= 0) return "périmé";
        if (count > 0 && count <= MONTH) return "faible";
        if (count > MONTH && count <= TWO_MONTH) return "moyenne";
        return "";
    }

    private List<InventoryViewWrapper> createInventoryViewWrappers(List<String> stockWithPriceAndExpirationDate) {
        List<InventoryViewWrapper> list = new ArrayList<>();
        List<String[]> collect = stockWithPriceAndExpirationDate.stream().map(s -> s.split(",")).collect(Collectors.toList());
        collect.forEach(strings -> {
            InventoryViewWrapper inventoryViewWrapper = new InventoryViewWrapper();
            inventoryViewWrapper.setArticleId(Long.valueOf(strings[0]));
            inventoryViewWrapper.setUniteId(Long.valueOf(strings[1]));
            inventoryViewWrapper.setMagasinId(Long.valueOf(strings[2]));
            inventoryViewWrapper.setArticle(strings[3]);
            inventoryViewWrapper.setCategorie(strings[4]);
            inventoryViewWrapper.setUnite(strings[5]);
            inventoryViewWrapper.setQuantite(Double.valueOf(strings[6]));
            if (strings.length == 8) inventoryViewWrapper.setNomMagasin(strings[7]);
            else inventoryViewWrapper.setNomMagasin(" - ");
            list.add(inventoryViewWrapper);
        });
        return list;
    }

    public List<String[]> processUniteAndNiveau(Long articleId) {
        return itemRepository.getAllUniteAndNiveau(articleId).stream().map(s -> s.split(",")).collect(Collectors.toList());
    }

    public void persistInventorieData(InventoryWrapper wrapper) {
        Long uniteId = wrapper.getUniteId();
        Long articleId = wrapper.getArticleId();
        Long magasinId = wrapper.getMagasinId();
        Double supplyQuantite = wrapper.getQuantite();
        Double quantiteNiveau = 0D;
        List<String[]> strings = processUniteAndNiveau(articleId);
        for (String[] value : strings) {
            Long uId = Long.getLong(value[0]);
            if (uniteId == uId) {
                quantiteNiveau = Double.valueOf(value[1]);
                int niveau = Integer.parseInt(value[1]);
                if (niveau == 1) {
                    // CONVERTIR LA QUANTITE A LA NIVEAU
                    Double stockQuantite = supplyQuantite * quantiteNiveau;
                    int stockCount = itemRepository.getStockCount(uId, magasinId, articleId);
                    if (stockCount == 0) {
                        itemRepository.saveInventory(uId, magasinId, articleId, stockQuantite);
                    } else itemRepository.updateStock(stockQuantite, uId, magasinId, articleId);
                }
            }
        }
    }

}
