package mg.imwa.admin.repository;
import mg.imwa.admin.model.TenantUser;
import mg.imwa.admin.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface TenantUserRepository extends JpaRepository<TenantUser,Long>{

    @Query(value = "from TenantUser t where t.userName=:username and t.password=:password and t.key=:key and t.userType=:type")
    Optional<TenantUser> findByUsernameAndPasswordAndKey(@Param("username") String username,
                                                         @Param("password") String password,
                                                         @Param("key") String key,
                                                         @Param("type")UserType type);

    @Query(value = "from TenantUser t where t.userName=:username and t.password=:password and t.key=:key and (t.userType = 1 OR t.userType = 2)")
    Optional<TenantUser> findByUsernameAndPasswordAndKey(@Param("username") String username,
                                                         @Param("password") String password,
                                                         @Param("key") String key);

}
