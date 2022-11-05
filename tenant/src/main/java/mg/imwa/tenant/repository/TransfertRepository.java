package mg.imwa.tenant.repository;

import mg.imwa.tenant.model.tenantEntityBeans.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(transactionManager = "defaultTenantTxManager")
@Repository
public interface TransfertRepository extends JpaRepository<Transfert,Long> {
    @Query(value = "select t.id+1 from transfert t  order by t.id desc limit 1",nativeQuery = true)
    Long getLastValue();
}
