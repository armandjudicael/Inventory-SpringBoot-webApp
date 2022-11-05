package mg.imwa.tenant.repository;
import mg.imwa.tenant.model.entityEnum.TypeMateriel;
import mg.imwa.tenant.model.tenantEntityBeans.MaterielTransport;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(transactionManager = "defaultTenantTxManager")
public interface MaterielTransportRepository extends JpaRepository<MaterielTransport,Long> {
    @Query(value = "from MaterielTransport  mt where mt.typeMateriel=:type")
    List<MaterielTransport> findAllByType(@Param("type") TypeMateriel typeMateriel);
}
