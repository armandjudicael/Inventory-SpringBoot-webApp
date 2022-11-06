package mg.imwa.tenant.controller.restController;

import mg.imwa.tenant.model.tenantEntityBeans.Article;
import mg.imwa.tenant.model.tenantEntityBeans.ArticleUnite;
import mg.imwa.tenant.repository.ArticleUniteRepository;
import mg.imwa.tenant.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ItemRessource {

    private ItemRepository itemRepository;

    @Autowired
    public ItemRessource(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping(value = "/articles")
    public ResponseEntity<List<Article>> getArticles() {
        List<Article> all = itemRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping(value = "/articles/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable(name = "id") Long id) {
        Optional<Article> byId = itemRepository.findById(id);
        return ResponseEntity.ok(byId.get());
    }


    @GetMapping(value = "/articles/{id}/unites")
    public ResponseEntity<Object> getAllUnites(@PathVariable("id") Long id) {
        List<ArticleUnite> allUnite = itemRepository.getAllUnite(id);
        return new ResponseEntity<>(allUnite, HttpStatus.CREATED);
    }

    @GetMapping(value = "/articles/{id}/{filialeId}")
    public ResponseEntity<Object> getAllUnites(@PathVariable("id") Long id, @PathVariable("filialeId") Long filialeId) {
        List<ArticleUnite> articleUnite = itemRepository.getAllUnite(id);
        return new ResponseEntity<>(articleUnite, HttpStatus.CREATED);
    }

    @GetMapping(value = "/articles/{articleId}/unites/{uniteId}/filiales/{filialeId}/prices")
    public ResponseEntity<Object> getPrix(@PathVariable("articleId") Long articleId
            , @PathVariable("uniteId") Long uniteId
            , @PathVariable("filialeId") Long filialeId) {
        return new ResponseEntity<>(itemRepository.getPrix(articleId, uniteId, filialeId), HttpStatus.OK);
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            itemRepository.deleteById(id);
            return new ResponseEntity<>(" The item with the id =" + id + " is deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @Autowired
    private ArticleUniteRepository articleUniteRepository;

    @DeleteMapping("/articles/item-unite/{id}")
    public ResponseEntity<Object> deleteItemUnity(@PathVariable("id") Long id) {
        try {
            articleUniteRepository.deleteById(id);
            return new ResponseEntity<>(" The item with the id =" + id + " is deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @PutMapping("/articles/{id}/{status}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @PathVariable("status") String status) {
        Optional<Article> articleOptional = itemRepository.findById(id);
        if (!articleOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Article article = articleOptional.get();
        article.setStatus(status);
        itemRepository.save(article);
        return new ResponseEntity<>(" item status set to " + status, HttpStatus.OK);
    }

    @PutMapping("/items/{id}/{item-name}")
    public ResponseEntity<Object> updateItem(@PathVariable("item-name") String itemName, @PathVariable("id") Long id) {
        Optional<Article> byId = itemRepository.findById(id);
        if (byId.isPresent()) {
            Article article = byId.get();
            article.setDesignation(itemName);
            return new ResponseEntity<>(itemRepository.save(article), HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping("/articles")
    public ResponseEntity<Object> create(@RequestBody Article article) {
        Article savedArticle = itemRepository.save(article);
        return new ResponseEntity<>(savedArticle, HttpStatus.CREATED);
    }
}
