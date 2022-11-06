package mg.imwa.tenant.repository;
import mg.imwa.tenant.model.tenantEntityBeans.Fonctionnalite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(transactionManager = "defaultTenantTxManager")
@Repository
public interface FeatureRepository extends JpaRepository<Fonctionnalite,Long> {
    @Modifying(clearAutomatically = false)
    @Query(value = "update fonction_autorisation set status=:value where fonction_id=:fonctionId and autorisation_map_key =:featureId",nativeQuery = true)
    public void updateAutorisation(@Param("fonctionId")Long fonctionId,@Param("featureId")Long featureId,@Param("value") Long newValue);
}
