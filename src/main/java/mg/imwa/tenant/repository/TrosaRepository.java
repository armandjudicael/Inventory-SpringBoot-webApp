package mg.imwa.tenant.repository;

import mg.imwa.tenant.model.tenantEntityBeans.Trosa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.Transient;
import java.util.List;
import java.util.Set;

@Repository
@Transactional(transactionManager = "defaultTenantTxManager")
public interface TrosaRepository extends JpaRepository<Trosa,Long> {
    @Query(value = "from trosa t join t.clientFournisseur cf where cf.id=:cfId")
    Set<Trosa> findAllByCfId(@Param("cfId") Long cfId );

    @Query(value = "from trosa t join t.clientFournisseur cf join cf.filiale f where f.id=:filiale_id")
    Set<Trosa> findAllByFilialeId(@Param("filiale_id") Long filialeId);

    @Modifying(clearAutomatically = true)
    @Query(value = " update trosa set reste =:value where id=:trosa_id ",nativeQuery = true)
    void updateReste(@Param("value") Double value,@Param("trosa_id") Long id);
}
