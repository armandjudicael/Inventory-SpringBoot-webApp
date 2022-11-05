package mg.imwa.tenant.repository;
import mg.imwa.tenant.model.tenantEntityBeans.Magasin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(transactionManager = "defaultTenantTxManager")
@Controller
public interface StoreRepository extends CrudRepository<Magasin,Long>{
    @Query(value = "from magasin m join m.filiale f where f.id=:filialeId")
    List<Magasin> findAllByFiliale(@Param("filialeId")Long filialeId);
}
