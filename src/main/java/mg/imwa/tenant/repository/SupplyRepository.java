package mg.imwa.tenant.repository;

import mg.imwa.tenant.model.tenantEntityBeans.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(transactionManager = "defaultTenantTxManager")
@Repository
public interface SupplyRepository extends JpaRepository<Supply,Long>{
    @Query(value = "from approv a " +
            "join  a.fournisseur fr " +
            "join a.infoArticleMagasin iam " +
            "join iam.article art "+
            "join fr.filiale f where f.id=:filialeId and a.quantitePeremption > 0 and art.isPerishable = true")
    List<Supply> getBySellByDate(@Param("filialeId") Long filialeId);

    @Query(value = "select a.id+1 from approv a  order by a.id desc limit 1",nativeQuery = true)
    Long getLastValue();
}
