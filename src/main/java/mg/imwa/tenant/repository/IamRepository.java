package mg.imwa.tenant.repository;

import mg.imwa.tenant.model.tenantEntityBeans.InfoArticleMagasin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(transactionManager = "defaultTenantTxManager")
@Repository
public interface IamRepository extends JpaRepository<InfoArticleMagasin, Long> {
    @Query(value = "select iam.id+1 from info_article_magasin iam  order by iam.id desc limit 1", nativeQuery = true)
    Long getLastValue();

    @Query(value = "select count(unite_id) from info_article_magasin iam where unite_id =:id", nativeQuery = true)
    Long checkUnite(@Param(value = "id") Long uniteId);
}