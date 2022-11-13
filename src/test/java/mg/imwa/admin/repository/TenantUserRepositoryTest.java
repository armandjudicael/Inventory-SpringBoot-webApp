package mg.imwa.admin.repository;

import mg.imwa.admin.model.TenantUser;
import mg.imwa.admin.model.UserType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TenantUserRepositoryTest {

    @Autowired
    private TenantUserRepository tenantUserRepository;

    @Test
    void findByUsernameAndPasswordAndKey() {
        String companyName = "esokia";
        TenantUser tenantUser = tenantUserRepository.findByUsernameAndPasswordAndKey(companyName, companyName, companyName).get();
        Assertions.assertThat(tenantUser).isNotNull();
    }

    @Test
    void testFindByUsernameAndPasswordAndKey() {
//        String companyName = "ambatovy";
//        TenantUser tenantUser = tenantUserRepository.findByUsernameAndPasswordAndKey(companyName,companyName,companyName,UserType.COMPANY_ADMIN).get();
//        Assertions.assertThat(tenantUser).isNotNull();
//        System.out.println(tenantUser);
    }
}