package mg.imwa.tenant.repository;

import mg.imwa.tenant.model.entityEmbededId.InventoryAlertId;
import mg.imwa.tenant.model.tenantEntityBeans.InventoryAlert;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(transactionManager = "defaultTenantTxManager")
public interface InventoryAlertRepository extends CrudRepository<InventoryAlert, InventoryAlertId>{
//    @Query(value = "from InventoryAlert ia join ia.unite u join ia.article a join ia.magasin m where m.id=:magasinId and a.id=:articleId and u.id=:uniteId")
//    List<InventoryAlert> getAllByArticleAndUniteAndMagasin(@Param("magasinId") Long magasinId
//                                                          ,@Param("articleId") Long articleId ,
//                                                           @Param("uniteId") Long uniteId );

      @Modifying(clearAutomatically = true)
      @Query(value = "update inventory_alert set quantite =:qt where article_id=:articleId and filiale_id=:filialeId",nativeQuery = true)
      void updateInventoryAlert(@Param("filialeId")Long filialeId,@Param("articleId")Long articleId,@Param("qt")Double quantite);

      @Query(value = "select quantite from inventory_alert where article_id=:articleId and filiale_id=:filialeId",nativeQuery = true)
      String getInventoryAlertBy(@Param("filialeId")Long filialeId,@Param("articleId")Long articleId);
}
