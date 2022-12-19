package mg.imwa.tenant.repository;

import mg.imwa.tenant.model.tenantEntityBeans.Fonction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(transactionManager = "defaultTenantTxManager")
public interface FonctionRepository extends JpaRepository<Fonction,Long>{
    @Query(value = "from fonction f join f.filiale fl where fl.id =:filialeId ")
    List<Fonction> getAllByFiliale(@Param("filialeId") Long id );

    @Query(value = "from fonction f where trim(lower(f.nomFonction))=trim(lower(:name))")
    Optional<Fonction> findByName(@Param("name") String name);

    @Query(" from fonction f join fetch f.autorisationMap")
    List<Fonction> joinFetchfindAll();
}
