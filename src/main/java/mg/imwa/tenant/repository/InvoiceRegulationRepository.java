package mg.imwa.tenant.repository;

import mg.imwa.tenant.model.tenantEntityBeans.Avoir;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(transactionManager = "defaultTenantTxManager")
public interface InvoiceRegulationRepository extends CrudRepository<Avoir,Long>{
    @Query(value ="select COUNT (av) from Avoir av join av.vente v where v.refVente =:ref")
    Long getInvoiceBySalesReference(@Param("ref")String ref);

    @Query(value ="select COUNT (av) from Avoir av join av.vente v where v.id =:id")
    Long getInvoiceBySaleId(@Param("id")Long id);

    @Query(value = "select a.id+1 from avoir a  order by a.id desc limit 1",nativeQuery = true)
    Long getLastValue();
}
