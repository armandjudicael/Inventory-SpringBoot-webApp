package mg.imwa.tenant.repository;

import mg.imwa.tenant.model.tenantEntityBeans.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(transactionManager = "defaultTenantTxManager")
@Repository
public interface SalesRepository extends JpaRepository<Vente,Long>{

    @Query(value = "select vente.id+1 from vente  order by vente.id desc limit 1",nativeQuery = true)
    Long getLastValue();

    @Query(value ="from vente v join v.filiale f where f.id=:filialeId")
    List<Vente> findAllByFilialeId(@Param("filialeId") Long filialeId);

    @Query(value = "from vente v join v.infoArticleMagasin info where trim(lower(info.article.designation)) like concat('%',trim(lower(:name)),'%') and info.magasin.id=:magasinId")
    List<Vente> getVentesByProductName(@Param("magasinId") Long magasinId, @Param("name")String name);

    @Query(value = "from vente v join v.infoArticleMagasin info where trim(lower(v.client.nom)) like concat('%',trim(lower(:name)),'%') and info.magasin.id=:magasinId ")
    List<Vente> getVentesByClientName(@Param("magasinId") Long magasinId, @Param("name")String name);

    @Query(value = "select distinct v from vente v join v.infoArticleMagasin info join v.filiale f where "+
            " trim(lower(v.client.nom)) like concat('%',trim(lower(:name)),'%') "+
            " and f.id =:filialeId")
    List<Vente> getSalesByCustomerName(@Param("filialeId") Long filialeId,@Param("name")String name);

    @Query(value = "from vente v join v.filiale f join v.infoArticleMagasin iam join iam.article a where " +
            "trim(lower(a.designation)) like concat('%',trim(lower(:name)),'%')  and f.id=:filialeId")
    List<Vente> getSalesByItemName(@Param("filialeId") Long filialeId,String name);

    @Query(value = "from vente v join v.filiale f  where " +
            " trim(lower(v.refVente)) =trim(lower(:ref))  and f.id=:filialeId ")
    List<Vente> getSalesByRef(@Param("filialeId") Long filialeId,@Param("ref") String ref);

    @Query(value = "from vente v where v.refVente=:ref")
    Optional<Vente> getInvoiceBySaleRef(@Param("ref")String reference);

    @Query(value = "from vente v join v.infoArticleMagasin info where info.magasin.id=:magasinId and (v.date between :begin and :end)")
    List<Vente> getSalesByBetweenDate(@Param("magasinId") Long magasinId,
                                      @Param("begin") LocalDate beginDate,
                                      @Param("end")LocalDate endDate);

    @Query(value = "select distinct v from vente v join v.filiale f join v.infoArticleMagasin  info where info.magasin.id=:magasinId and f.id=:filialeId and (v.date between :begin and :end)")
    List<Vente> getSalesByStoreAndBetweenDate(@Param("magasinId") Long magasinId,
                                              @Param("filialeId") Long filialeId,
                                              @Param("begin") LocalDate beginDate,
                                              @Param("end")LocalDate endDate);
    @Query(value = "select distinct v from vente v join v.filiale f join v.infoArticleMagasin  info where f.id=:filialeId and (v.date between :begin and :end)")
    List<Vente> getSalesBySubsidiaryAndBetweenDate(@Param("filialeId") Long filialeId,
                                              @Param("begin") LocalDate beginDate,
                                              @Param("end")LocalDate endDate);

    @Query(value = "from vente v join v.filiale f where f.id=:filialeId and (v.date between :begin and :end)")
    List<Vente>  getInvoiceBetweenDate(@Param("filialeId") Long filialeId,
                                    @Param("begin") LocalDate beginDate,
                                    @Param("end")LocalDate endDate);
    @Query(value = "select  v.ref_vente,"+
            "v.montant_vente," +
            "tmp.date date," +
            "(select p.nom from personne p join client_fournisseur cf on p.id = cf.id  where cf.id = v.client_id) client," +
            "(select p.nom from personne p join _user u on u.id = p.id where u.id = tmp.user_id) operateur " +
            "from vente v "+
            "join (select viam.vente_id,iam.user_id,iam.date from vente_info_article_magasin viam join info_article_magasin iam on viam.info_article_magasin_id = iam.id where viam.vente_id = v.id and magasin_id =:magasinId limit 1) tmp on tmp.vente_id = v.id",nativeQuery = true)
    List<String> getFactureGroupByRef(@Param("magasinId") Long magasinId);

    @Query(value = "select refgroup.reference reference,"+
            "refgroup.somme montantTotal,"+
            "iam1.date date,"+
            "(select p.nom from personne p join client_fournisseur cf on p.id = cf.id  where cf.id = v.client_id) client," +
            "(select p.nom from personne p join _user u on u.id = p.id where u.id = iam1.user_id) operateur " +
            "from info_article_magasin iam1 "+
            "      join (select iam.reference,sum(montant_vente) somme from info_article_magasin iam join vente_info_article_magasin viam on iam.id = viam.info_article_magasin_id join vente v2 on viam.vente_id = v2.id join magasin m on iam.magasin_id = m.id_magasin where m.filiale_id=:filialeId and iam.magasin_id=:magasinId group by iam.reference) refgroup on refgroup.reference = iam1.reference " +
            "join vente_info_article_magasin viam2 on iam1.id = viam2.info_article_magasin_id join vente v on v.id = viam2.vente_id ",nativeQuery = true)
    List<String> getFactureGroupByRefAndFilialeAndMagasin(@Param("magasinId") Long magasinId, @Param("filialeId")Long filialeId);

    @Query(value = "select refgroup.reference reference,"+
            "refgroup.somme montantTotal," +
            "iam2.date date," +
            "(select p.nom from personne p join client_fournisseur cf on p.id = cf.id  where cf.id = (select v.client_id from vente v join vente_info_article_magasin viam2 on v.id = viam2.vente_id " +
            " where viam2.info_article_magasin_id= vim.info_article_magasin_id)) client," +
            "(select p.nom from personne p join _user u on u.id = p.id where u.id = iam2.user_id ) operateur "+
            "from vente v join  vente_info_article_magasin vim join  info_article_magasin iam2 "+
            "join (select iam.reference,sum(montant_vente) somme from info_article_magasin iam join vente_info_article_magasin viam on iam.id = viam.info_article_magasin_id join vente v2 on viam.vente_id = v2.id  join magasin m on iam.magasin_id = m.id_magasin where m.filiale_id=:filialeId group by iam.reference) refgroup  ",nativeQuery = true)
    List<String> getFactureGroupByRefAndFiliale(@Param("filialeId")Long filialeId);

    @Query(value = "from vente v join v.filiale f where f.id=:filialeId")
    List<Vente> getFacture(@Param("filialeId")Long filialeId);

    @Query(value = "from vente v join v.infoArticleMagasin iam join iam.magasin m where m.id=:magasinId")
    List<Vente> findByStoreId(@Param("magasinId")Long magasinId);

    @Query(value = "from vente v join  v.client c where trim(lower(c.nom)) like concat('%',trim(lower(:name)),'%') or trim(lower(v.refVente)) like concat('%',trim(lower(:name)),'%') ")
    List<Vente> getSalesByClientName(String name);
    @Query(value = "from vente v join  v.client c join v.infoArticleMagasin iam join iam.article a where " +
            "trim(lower(c.nom)) like concat('%',trim(lower(:text)),'%') " +
            "or trim(lower(v.refVente)) like concat('%',trim(lower(:text)),'%') " +
            "or trim(lower(a.designation)) like concat('%',trim(lower(:text)),'%')  ")
    List<Vente> searchSalesBy(String text);

}
