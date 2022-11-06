package mg.imwa.tenant.repository;
import mg.imwa.tenant.model.tenantEntityBeans.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Transactional(transactionManager = "defaultTenantTxManager")
@Repository
public interface VenteRepository extends JpaRepository<Vente,Long>{
}