package mg.imwa.admin.repository;

import mg.imwa.admin.model.AdminTenantUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(transactionManager = "adminTransactionManager")
public interface AdminUserRepository extends JpaRepository<AdminTenantUser,Long> {
}
