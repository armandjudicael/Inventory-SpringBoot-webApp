package mg.imwa.tenant.repository;

import mg.imwa.tenant.model.tenantEntityBeans.Sortie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(transactionManager = "defaultTenantTxManager")
public interface SortieRepository extends JpaRepository<Sortie,Long> {
    @Query(value = "select s.id+1 from sortie s  order by s.id desc limit 1",nativeQuery = true)
    Long getLastValue();
}
