package mg.imwa.tenant.repository;

import mg.imwa.tenant.model.tenantEntityBeans.ArticleUnite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(transactionManager = "defaultTenantTxManager")
public interface ArticleUniteRepository extends JpaRepository<ArticleUnite,Long> {
}
