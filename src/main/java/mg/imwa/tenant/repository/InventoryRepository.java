package mg.imwa.tenant.repository;

import mg.imwa.tenant.model.entityEmbededId.StockId;
import mg.imwa.tenant.model.tenantEntityBeans.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(transactionManager = "defaultTenantTxManager")
public interface InventoryRepository extends JpaRepository<Stock, StockId>{
}
