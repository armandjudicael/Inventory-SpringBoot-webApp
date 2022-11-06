package mg.imwa.tenant.repository;


import mg.imwa.tenant.model.entityEnum.ModePayement;
import mg.imwa.tenant.model.tenantEntityBeans.Caisse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(transactionManager = "defaultTenantTxManager")
public interface CashRepository extends JpaRepository<Caisse, Long>{
    @Query(value = "from Caisse c join c.filiale f where f.id=:filialeId and c.modePayement=:mode")
    Optional<Caisse> findByFilialeIdAndPayementMode(@Param("filialeId") Long filialeId, @Param("mode") ModePayement modePayement);
}
