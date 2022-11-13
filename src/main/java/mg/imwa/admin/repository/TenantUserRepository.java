package mg.imwa.admin.repository;
import mg.imwa.admin.model.TenantUser;
import mg.imwa.admin.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(transactionManager = "adminTransactionManager")
public interface TenantUserRepository extends JpaRepository<TenantUser,Long>{

    @Query(value = "select t from TenantUser t,Admin  a where t.userName=:username and t.password=:password and t.key=:key and t.userType=:type and t.id=a.id")
    Optional<TenantUser> findByUsernameAndPasswordAndKey(@Param("username") String username,
                                                         @Param("password") String password,
                                                         @Param("key") String key,
                                                         @Param("type")UserType type);

    @Query(value = "from TenantUser t where t.userName=:username and t.password=:password and t.key=:key")
    Optional<TenantUser> findByUsernameAndPasswordAndKey(@Param("username") String username,
                                                         @Param("password") String password,
                                                         @Param("key") String key);

}
