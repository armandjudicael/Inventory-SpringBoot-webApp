package mg.imwa.tenant.repository;

import mg.imwa.tenant.model.tenantEntityBeans.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(transactionManager = "defaultTenantTxManager")
public interface LivraisonRepository extends JpaRepository<Livraison,Long> {
}
