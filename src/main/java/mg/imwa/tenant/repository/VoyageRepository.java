package mg.imwa.tenant.repository;

import mg.imwa.tenant.model.tenantEntityBeans.Voyage;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(transactionManager = "defaultTenantTxManager")
public interface VoyageRepository extends JpaRepository<Voyage,Long>{
    @Query(value = "select v.id+1 from voyage v  order by v.id desc limit 1",nativeQuery = true)
    Long getLastValue();

    @Query(value = "from voyage v where lower(v.reference)=lower(:ref) ")
    List<Voyage> findByReference(@Param("ref") String ref);

    @Query(value = "from voyage v where v.dateCreation between :begin and :end")
    List<Voyage> findByAllByDateCreation(@Param("begin")LocalDate begin,@Param("end")LocalDate end);

    @Query(value = "from voyage v where v.dateArrive between :begin and :end")
    List<Voyage> findByAllByDateArrive(@Param("begin")LocalDate begin,@Param("end")LocalDate end);

    @Query(value = "from voyage v where v.dateDepart between :begin and :end")
    List<Voyage> findByAllByDateDepart(@Param("begin")LocalDate begin,@Param("end")LocalDate end);
}
