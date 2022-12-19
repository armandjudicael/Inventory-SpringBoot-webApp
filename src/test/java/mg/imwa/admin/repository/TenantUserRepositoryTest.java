package mg.imwa.admin.repository;
import mg.imwa.admin.model.TenantUser;
import mg.imwa.admin.model.UserType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TenantUserRepositoryTest {

    @Autowired
    private TenantUserRepository tenantUserRepository;

    @Test
    void findByUsernameAndPasswordAndKey(){
//        String companyName = "es";
//        TenantUser tenantUser = tenantUserRepository.findByUsernameAndPasswordAndKey(companyName, companyName, companyName).get();
//        Assertions.assertThat(tenantUser).isNotNull();
    }

    @Test
    void testFindByUsernameAndPasswordAndKey(){
//        String companyName = "dds";
//        String username = "jacquelin";
//        String password = username;
//        TenantUser tenantUser = tenantUserRepository.findByUsernameAndPasswordAndKey(username,password,companyName, UserType.COMPANY_ADMIN).get();
//        Assertions.assertThat(tenantUser).isNotNull();
//        System.out.println(tenantUser);
    }

}