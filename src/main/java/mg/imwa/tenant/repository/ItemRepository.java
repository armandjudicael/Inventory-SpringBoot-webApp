package mg.imwa.tenant.repository;

import mg.imwa.tenant.model.tenantEntityBeans.Article;
import mg.imwa.tenant.model.tenantEntityBeans.ArticleUnite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(transactionManager = "defaultTenantTxManager")
public interface ItemRepository extends JpaRepository<Article, Long> {

    @Query(value = "SELECT au.quantite_niveau FROM  article_unite au WHERE au.article_id =:articleId AND au.unite_id=:uniteId", nativeQuery = true)
    Double getQuantiteNiveau(@Param("uniteId") Long uniteId, @Param("articleId") Long articleId);

    @Query(value = "from ArticleUnite au join au.article a  WHERE a.id=:articleId")
    List<ArticleUnite> getAllUnite(@Param("articleId") Long articleId);

    @Query(value = "from ArticleUnite au join au.article a where a.status =:status")
    List<ArticleUnite> getAllNotDeletedAndNotHidden(@Param("status") String status);

    @Query(value = "from ArticleUnite au")
    List<ArticleUnite> getAll();

    @Query(value = "from ArticleUnite au join au.article a where lower(a.designation) like concat('%',lower(:name),'%') ")
    List<ArticleUnite> getAllByItemName(@Param("name") String name);


    @Query(value = "select a.article_id,u.id,s.magasin_id,a.designation as ad,c.libelle,u.designation," +
            "(s.count/(SELECT au.quantite_niveau FROM  article_unite au WHERE au.article_id = a.article_id AND au.unite_id = u.id)) as nb , m.nom_magasin " +
            "FROM unite u ,Stock s,article_unite au , article a ,categorie c , magasin m " +
            "WHERE au.article_id = a.article_id and au.unite_id = u.id " +
            "and a.categorie_id = c.id " +
            "and s.article_id = au.article_id and m.id_magasin = s.magasin_id ", nativeQuery = true)
    List<String> getStockWithPriceAndExpirationDateByItemName();

    @Query(value = "select a.article_id,u.id,s.magasin_id,a.designation as ad,c.libelle, u.designation," +
            "(s.count/(SELECT au.quantite_niveau FROM  article_unite au WHERE au.article_id = a.article_id AND au.unite_id = u.id)) as nb , m.nom_magasin " +
            "FROM unite u , Stock s , article_unite as au , article a , categorie  c , magasin m " +
            "WHERE au.article_id = a.article_id and au.unite_id = u.id " +
            "and a.categorie_id = c.id " +
            "and s.article_id = au.article_id and s.magasin_id=:magasinId and m.id_magasin = s.magasin_id", nativeQuery = true)
    List<String> getStockWithPriceAndExpirationDateByItemName(@Param("magasinId") Long magasinId);

    @Query(value = "select s.article_id,s.unite_id,0," +
            "      (select a1.designation from article a1 where a1.article_id=s.article_id) as ad, " +
            "      (select c.libelle from categorie c where c.id = (select a2.categorie_id from article a2 where a2.article_id=s.article_id ) ),  " +
            "      (select u1.designation from unite u1  where u1.id= s.unite_id ) as ud," +
            "      sum(s.count/(SELECT au.quantite_niveau FROM  article_unite au WHERE au.article_id = s.article_id AND au.unite_id = s.unite_id))" +
            "      from stock s join magasin m on s.magasin_id = m.id_magasin where m.filiale_id =:filialeId  group by s.unite_id,s.article_id", nativeQuery = true)
    List<String> getTotalInventory(@Param("filialeId") Long filialeId);

    @Query(value = "select sum(p.prix) from (select sum(s.count)*(select prix_vente from prix_article_filiale paf where " +
            "        paf.article_id = s.article_id and" +
            "        paf.unite_id = s.unite_id and" +
            "        paf.filiale_id=:filialeId order by date_enregistrement desc limit 1) prix" +
            " from stock s join magasin m on s.magasin_id = m.id_magasin where m.filiale_id =:filialeId  group by s.unite_id,s.article_id) p", nativeQuery = true)
    Double getInventoryValueTotal(@Param("filialeId") Long filialeId);

    //
    @Query(value = "select sum(p.prix) from (select sum(s.count)*(select prix_vente from prix_article_filiale paf where " +
            "        paf.article_id = s.article_id and" +
            "        paf.unite_id = s.unite_id and" +
            "        paf.filiale_id=:filialeId order by date_enregistrement desc limit 1) prix" +
            " from stock s join magasin m on s.magasin_id = m.id_magasin where m.filiale_id =:filialeId and m.id_magasin=:magasinId group by s.unite_id,s.article_id) p ", nativeQuery = true)
    Double getInventoryValueTotal(@Param("filialeId") Long filialeId, @Param("magasinId") Long magasinId);

    @Query(value = "select a.article_id,u.id,s.magasin_id,a.designation as ad,c.libelle, u.designation," +
            "(s.count/(SELECT au.quantite_niveau FROM  article_unite au WHERE au.article_id = a.article_id AND au.unite_id = u.id)) as nb , m.nom_magasin " +
            "FROM unite u , Stock s , article_unite as au , article a , categorie  c , magasin m " +
            "WHERE au.article_id = a.article_id and au.unite_id = u.id " +
            "and a.categorie_id = c.id " +
            "and s.article_id = au.article_id and s.magasin_id=:magasinId and m.id_magasin = s.magasin_id and lower(trim(a.designation)) like concat('%',lower(trim(:name)),'%') ", nativeQuery = true)
    List<String> getStockWithPriceAndExpirationDateByItemName(@Param("magasinId") Long magasinId, @Param("name") String name);

    @Query(value = "select a.article_id,u.id,s.magasin_id,a.designation as ad,c.libelle, u.designation," +
            "(s.count/(SELECT au.quantite_niveau FROM  article_unite au WHERE au.article_id = a.article_id AND au.unite_id = u.id)) as nb , m.nom_magasin " +
            "FROM unite u , Stock s , article_unite as au , article a , categorie  c , magasin m " +
            "WHERE au.article_id = a.article_id and au.unite_id = u.id " +
            "and a.categorie_id = c.id " +
            "and s.article_id = au.article_id and m.filiale_id=:filialeId and m.id_magasin = s.magasin_id and lower(trim(a.designation)) like concat('%',lower(trim(:name)),'%')", nativeQuery = true)
    List<String> getSubsidiaryInventoryWithPriceAndExpirationDateByItemName(@Param("filialeId") Long filialeId, @Param("name") String name);

    @Query(value = "select a.article_id,u.id,s.magasin_id,a.designation as ad,c.libelle, u.designation," +
            "(s.count/(SELECT au.quantite_niveau FROM  article_unite au WHERE au.article_id = a.article_id AND au.unite_id = u.id)) as nb , m.nom_magasin " +
            "FROM unite u ,Stock s ,article_unite as au ,article a , categorie  c,magasin m " +
            "WHERE au.article_id = a.article_id and au.unite_id = u.id " +
            "and a.categorie_id = c.id " +
            "and s.article_id = au.article_id and m.filiale_id=:filialeId and m.id_magasin = s.magasin_id", nativeQuery = true)
    List<String> getSubsidiaryInventoryWithPriceAndExpirationDate(@Param("filialeId") Long filialeId);

    @Query(value = "select " +
            "(s.count/(SELECT au.quantite_niveau FROM  article_unite au WHERE au.article_id = a.article_id AND au.unite_id = u.id)) as nb " +
            "FROM unite u ,Stock s ,article_unite as au ,article a , categorie  c,magasin m " +
            "WHERE au.article_id = a.article_id and au.unite_id = u.id " +
            "and a.categorie_id = c.id " +
            "and s.article_id = au.article_id and m.filiale_id=:filialeId group by s.article_id,s.unite_id", nativeQuery = true)
    List<String> getSubsidiaryTotalInventory(@Param("filialeId") Long filialeId);

    @Query(value = "select " +
            "a.article_id" +
            ",u.id" +
            ",a.designation as designation_article," +
            "u.designation," +
            "(s.count/(SELECT au.quantite_niveau FROM  article_unite au WHERE au.article_id = a.article_id AND au.unite_id = u.id)) as _stock," +
            "(select paf2.prix_vente from prix_article_filiale paf2 where paf2.filiale_id =:filialeId and paf2.unite_id=u.id and paf2.article_id = a.article_id order by date_enregistrement desc limit 1) as prix " +
            " ,au.poids, au.quantite_niveau" +
            " FROM unite u,Stock s,article_unite as au,article a " +
            "WHERE au.article_id = a.article_id and au.unite_id = u.id  " +
            "and s.article_id = au.article_id and a.status ='USED' and  s.magasin_id =:magasinId", nativeQuery = true)
    List<String> getSubsidiaryItemInfo(@Param("filialeId") Long filialeId, @Param("magasinId") Long magasinId);

    @Query(value = "select " +
            "a.article_id" +
            ",u.id" +
            ",a.designation as designation_article," +
            "u.designation," +
            "(s.count/(SELECT au.quantite_niveau FROM  article_unite au WHERE au.article_id = a.article_id AND au.unite_id = u.id)) as _stock," +
            "(select paf2.prix_vente from prix_article_filiale paf2 where paf2.filiale_id =:filialeId and paf2.unite_id=u.id and paf2.article_id = a.article_id order by date_enregistrement desc limit 1) as prix ,au.poids, au.quantite_niveau" +
            " FROM unite u,Stock s,article_unite as au,article a " +
            "WHERE au.article_id = a.article_id and au.unite_id = u.id " +
            "and s.article_id = au.article_id and a.status ='USED' and trim(lower(a.designation)) like concat('%',trim(lower(:name)),'%')", nativeQuery = true)
    List<String> getSubsidiaryItemInfoByName(@Param("filialeId") Long filialeId, @Param("name") String name);

    @Query(value = "select " +
            "a.article_id" +
            ",u.id" +
            ",a.designation as designation_article," +
            "u.designation," +
            "(s.count/(SELECT au.quantite_niveau FROM  article_unite au WHERE au.article_id = a.article_id AND au.unite_id = u.id)) as _stock," +
            "(select paf2.prix_vente from prix_article_filiale paf2 where paf2.filiale_id =:filialeId and paf2.unite_id=u.id and paf2.article_id = a.article_id order by date_enregistrement desc limit 1) as prix ,au.poids, au.quantite_niveau " +
            "FROM unite u,Stock s,article_unite as au,article a " +
            "WHERE au.article_id = a.article_id and au.unite_id = u.id " +
            "and s.article_id = au.article_id and a.status ='USED';", nativeQuery = true)
    List<String> getSubsidiaryItemInfo(@Param("filialeId") Long filialeId);

    @Query(value = "select " +
            "a.article_id" +
            ",u.id" +
            ",a.designation as designation_article," +
            "u.designation," +
            "(s.count/(SELECT au.quantite_niveau FROM  article_unite au WHERE au.article_id = a.article_id AND au.unite_id = u.id)) as _stock," +
            "(select paf2.prix_vente from prix_article_filiale paf2 where paf2.filiale_id =:filialeId and paf2.unite_id=u.id and paf2.article_id = a.article_id order by date_enregistrement desc limit 1) as prix ,au.poids, au.quantite_niveau " +
            "FROM unite u,Stock s,article_unite as au,article a " +
            "WHERE au.article_id = a.article_id and au.unite_id = u.id  " +
            "and s.article_id = au.article_id and s.magasin_id =:magasinId and a.status ='USED' and trim(lower(u.designation)) like concat('%',trim(lower(:name)),'%')", nativeQuery = true)
    List<String> getSubsidiaryItemInfoByUniteName(@Param("filialeId") Long filialeId, @Param("magasinId") Long magasinId, @Param("name") String name);

    @Query(value = "select a.article_id,u.id,s.magasin_id,a.designation as ad,c.libelle, u.designation," +
            "(s.count/(SELECT au.quantite_niveau FROM  article_unite au WHERE au.article_id = a.article_id AND au.unite_id = u.id)) as nb , m.nom_magasin " +
            "FROM unite u , Stock s , article_unite as au , article a , categorie  c , magasin m " +
            "WHERE au.article_id = a.article_id and au.unite_id = u.id " +
            "and a.categorie_id = c.id " +
            "and s.article_id = au.article_id and m.filiale_id=:filialeId and m.id_magasin = s.magasin_id " +
            "and s.count<=(select ia.quantite from inventory_alert ia  where a.article_id=ia.article_id and a.status ='USED' and  ia.filiale_id=m.filiale_id) ", nativeQuery = true)
    List<String> getSubsidiaryInventoryAlert(@Param("filialeId") Long filialeId);

    @Query(value = "SELECT prix_vente from prix_article_filiale where article_id =:artId and unite_id =:uId and filiale_id =:fId order by date_enregistrement desc limit 1", nativeQuery = true)
    String getPrix(@Param("artId") Long artId, @Param("uId") Long uId, @Param("fId") Long fId);

    @Modifying(clearAutomatically = true)
    @Query(value = "update stock set " +
            " count = :newVal + stock.count" +
            " where unite_id =:uniteId and magasin_id=:magasinId and article_id=:articleId", nativeQuery = true)
    void updateStock(@Param("newVal") Double value, @Param("uniteId") Long uniteId, @Param("magasinId") Long magasinId, @Param("articleId") Long articleId);

    @Query(value = "SELECT au.unite_id FROM article_unite au where article_id =:articleId and au.niveau = 1", nativeQuery = true)
    Long getPrimaryUniteId(@Param("articleId") Long articleId);

    @Query(value = "SELECT u.id ,au.niveau,au.quantite_niveau FROM unite u inner join article_unite au ON u.id = au.unite_id where article_id =:articleId", nativeQuery = true)
    List<String> getAllUniteAndNiveau(@Param("articleId") Long articleId);

    @Query(value = "SELECT count(*) from stock s WHERE s.article_id =:articleId AND s.unite_id=:uniteId and s.magasin_id=:magasinId", nativeQuery = true)
    int getStockCount(@Param("uniteId") Long uniteId, @Param("magasinId") Long magasinId, @Param("articleId") Long articleId);


    @Query(value = "SELECT s.count/au.quantite_niveau from stock s join article_unite au on s.article_id = au.article_id and au.unite_id=s.unite_id " +
            "WHERE s.article_id =:articleId AND s.unite_id=:uniteId and s.magasin_id=:magasinId", nativeQuery = true)
    Double getStock(@Param("uniteId") Long uniteId, @Param("magasinId") Long magasinId, @Param("articleId") Long articleId);

    @Modifying
    @Query(value = "INSERT into stock (article_id,unite_id,magasin_id,count) values (:articleId,:uniteId,:magasinId,:count);", nativeQuery = true)
    int saveInventory(@Param("uniteId") Long uniteId
            , @Param("magasinId") Long magasinId,
                      @Param("articleId") Long articleId,
                      @Param("count") Double count);

    @Query(value = " select a.designation ad,u.designation ud,gp.date_pr,gp.sum_quantite,gp.magasin_id,gp.article_id,gp.unite_id ,gp.day_count from article a join (select iam.magasin_id,iam.article_id,iam.unite_id,ap.date_peremption date_pr ,sum(ap.quantite_peremption) sum_quantite , date_part('day',(date_peremption-now()))  day_count   from approv ap join info_article_magasin iam on iam.id = ap.info_article_magasin_id " +
            " join magasin m on m.id_magasin = iam.magasin_id " +
            " where m.filiale_id = :filialeId and ap.quantite_peremption > 0 group by date_peremption ,iam.article_id,iam.unite_id,iam.magasin_id order by date_peremption) as gp on a.article_id = gp.article_id join unite u on u.id = gp.unite_id ", nativeQuery = true)
    List<String> getProductExpiration(@Param("filialeId") Long filialeId);

    @Query(value = " select a.designation ad,u.designation ud,gp.date_pr,gp.sum_quantite,gp.magasin_id,gp.article_id,gp.unite_id,gp.day_count from article a join (select iam.magasin_id,iam.article_id,iam.unite_id,ap.date_peremption date_pr ,sum(ap.quantite_peremption) sum_quantite, date_part('day',(date_peremption-now()))  day_count   from approv ap join info_article_magasin iam on iam.id = ap.info_article_magasin_id " +
            " join magasin m on m.id_magasin = iam.magasin_id" +
            " where m.filiale_id = :filialeId and ap.quantite_peremption > 0 and date_part('day',(date_peremption - now())) between :begin and :end  group by date_peremption ,iam.article_id,iam.unite_id,iam.magasin_id order by date_peremption) as gp on a.article_id = gp.article_id join unite u on u.id = gp.unite_id ", nativeQuery = true)
    List<String> getProductExpirationByStatus(@Param("filialeId") Long filialeId, @PathVariable("begin") int begin, @PathVariable("end") int end);

    @Query(value = " select a.designation ad,u.designation ud,gp.date_pr,gp.sum_quantite,gp.magasin_id,gp.article_id,gp.unite_id,gp.day_count from article a join (select iam.magasin_id,iam.article_id,iam.unite_id,ap.date_peremption date_pr ,sum(ap.quantite_peremption) sum_quantite, date_part('day',(date_peremption-now()))  day_count  from approv ap join info_article_magasin iam on iam.id = ap.info_article_magasin_id " +
            " join magasin m on m.id_magasin = iam.magasin_id" +
            " where m.filiale_id = :filialeId and ap.quantite_peremption > 0 and date_part('day',(date_peremption - now())) > :value  group by date_peremption ,iam.article_id,iam.unite_id,iam.magasin_id order by date_peremption) as gp on a.article_id = gp.article_id join unite u on u.id = gp.unite_id ", nativeQuery = true)
    List<String> getProductExpirationByStatusStrong(@Param("filialeId") Long filialeId, @PathVariable("value") int value);


    @Query(value = " select a.designation ad,u.designation ud,gp.date_pr,gp.sum_quantite,gp.magasin_id,gp.article_id,gp.unite_id,gp.day_count  from article a join (select iam.magasin_id,iam.article_id,iam.unite_id,ap.date_peremption date_pr ,sum(ap.quantite_peremption) sum_quantite, date_part('day',(date_peremption-now()))  day_count from approv ap join info_article_magasin iam on iam.id = ap.info_article_magasin_id " +
            " join magasin m on m.id_magasin = iam.magasin_id" +
            " where m.filiale_id = :filialeId and ap.quantite_peremption > 0 and date_part('day',(date_peremption - now())) <= :value  group by date_peremption ,iam.article_id,iam.unite_id,iam.magasin_id order by date_peremption) as gp on a.article_id = gp.article_id join unite u on u.id = gp.unite_id ", nativeQuery = true)
    List<String> getProductExpirationByStatusExpired(@Param("filialeId") Long filialeId, @PathVariable("value") int value);


    @Query(value = " select a.designation ad,u.designation ud,gp.date_pr,gp.sum_quantite,gp.magasin_id,gp.article_id,gp.unite_id,gp.day_count from article a join (select iam.magasin_id,iam.article_id,iam.unite_id,ap.date_peremption date_pr ,sum(ap.quantite_peremption) sum_quantite, date_part('day',(date_peremption-now()))  day_count  from approv ap join info_article_magasin iam on iam.id = ap.info_article_magasin_id " +
            " join magasin m on m.id_magasin = iam.magasin_id" +
            " where m.id_magasin = :magasinId and ap.quantite_peremption > 0 group by date_peremption ,iam.article_id,iam.unite_id,iam.magasin_id order by date_peremption) as gp on a.article_id = gp.article_id join unite u on u.id = gp.unite_id ", nativeQuery = true)
    List<String> getProductExpirationByStore(@Param("magasinId") Long magasinId);

    @Query(value = "select a.designation ad,u.designation ud,gp.date_pr,gp.sum_quantite,gp.magasin_id,gp.article_id,gp.unite_id,gp.day_count from article a " +
            " join (select iam.magasin_id,iam.article_id,iam.unite_id,ap.date_peremption date_pr ,sum(ap.quantite_peremption) sum_quantite,date_part('day',(date_peremption - now()))  day_count from approv ap join info_article_magasin iam on iam.id = ap.info_article_magasin_id " +
            " join magasin m on m.id_magasin = iam.magasin_id where m.filiale_id = :filialeId and ap.quantite_peremption > 0 group by date_peremption ,iam.article_id,iam.unite_id,iam.magasin_id order by date_peremption) as gp on a.article_id = gp.article_id join unite u on u.id = gp.unite_id" +
            " where trim(lower(a.designation)) like concat('%',trim(lower(:name)),'%')  ", nativeQuery = true)
    List<String> getProductExpirationByProductName(@Param("name") String productName, @Param("filialeId") Long filialeId);


    @Modifying(clearAutomatically = true)
    @Query(value = "CALL mettre_a_jour_date_peremption(:id_magasin,:id_article,:id_unite,:new_date,:old_date)", nativeQuery = true)
    void updateExpirationDate(@Param("id_magasin") Long magasinId,
                              @Param("id_article") Long articleId,
                              @Param("id_unite") Long uniteId,
                              @Param("new_date") LocalDate newDate,
                              @Param("old_date") LocalDate oldDate);

    @Modifying(clearAutomatically = true)
    @Query(value = "update inventory_alert set quantite=:newValue where filiale_id=:id_filiale and article_id=:id_article", nativeQuery = true)
    void updateQuantiteAlert(@Param("id_filiale") Long filialeId,
                             @Param("id_article") Long articleId,
                             @Param("newValue") Double quantite);
}


