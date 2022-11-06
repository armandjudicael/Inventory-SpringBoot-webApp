package mg.imwa.tenant.repository;

import mg.imwa.tenant.model.tenantEntityBeans.Filiale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(transactionManager = "defaultTenantTxManager")
@Repository
public interface SubsidiaryRepository extends JpaRepository<Filiale,Long>{
}
