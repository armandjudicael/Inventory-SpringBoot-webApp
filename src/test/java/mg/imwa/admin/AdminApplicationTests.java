package mg.imwa.admin;

import mg.imwa.admin.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminApplicationTests{

	@Autowired
	private CompanyRepository companyRepository;

	@Test
	void contextLoads() {
	}

}
