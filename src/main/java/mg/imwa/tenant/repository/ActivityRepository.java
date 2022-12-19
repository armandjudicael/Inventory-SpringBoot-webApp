package mg.imwa.tenant.repository;
import mg.imwa.tenant.model.tenantEntityBeans.InfoArticleMagasin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(transactionManager = "defaultTenantTxManager")
public interface ActivityRepository extends JpaRepository<InfoArticleMagasin,Long>{
    @Query(value = "from InfoArticleMagasin iam join iam.magasin m where iam.date=:date and m.id =:magasinId")
    List<InfoArticleMagasin> findAllByDate(@Param("magasinId") Long id,@Param("date")LocalDate localDate);

    @Query(value = "from InfoArticleMagasin iam join iam.magasin m join m.filiale f where f.id=:filialeId")
    List<InfoArticleMagasin> findAllByFilialeId(@Param("filialeId") Long filialeId);

    @Query(value = "from InfoArticleMagasin iam join iam.magasin m join m.filiale f where f.id=:filialeId and iam.date between :beginDate and :endDate")
    List<InfoArticleMagasin> findAllByFilialeIdBetweenDate(@Param("filialeId") Long filialeId,
                                                           @Param("beginDate")LocalDate beginDate,
                                                           @Param("endDate")LocalDate endDate);

    @Query(value = "from InfoArticleMagasin iam join iam.magasin m where m.id =:magasinId and iam.date between :beginDate and :endDate")
    List<InfoArticleMagasin> findAllBetweenDate(@Param("magasinId") Long magasinId,
                                                @Param("beginDate")LocalDate beginDate,
                                                @Param("endDate")LocalDate endDate);

    @Query(value = "from InfoArticleMagasin iam join iam.magasin m where m.id =:magasinId")
    List<InfoArticleMagasin> findAllByStoreId(@Param("magasinId")Long magasinId);

    @Query(value = "from InfoArticleMagasin iam join iam.magasin m join iam.article a where m.id =:magasinId and a.id=:articleId ")
    List<InfoArticleMagasin> findAllByStoreAndItem(@Param("magasinId")Long magasinId,@Param("articleId") Long articleId);

    @Query(value = "from InfoArticleMagasin iam join iam.magasin m join iam.article a join iam.unite u where m.id =:magasinId and a.id=:articleId  and u.id=:uniteId")
    List<InfoArticleMagasin> findAllByStoreAndItemAndUnit(@Param("magasinId")Long magasinId
                                                         ,@Param("articleId") Long articleId
                                                         ,@Param("uniteId") Long uniteId);
}
