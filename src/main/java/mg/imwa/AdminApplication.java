package mg.imwa;
import mg.imwa.admin.model.Admin;
import mg.imwa.admin.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import java.util.Optional;

@SpringBootApplication(scanBasePackages ={
		"mg.imwa.config",

		"mg.imwa.admin.model",
		"mg.imwa.admin.repository",
		"mg.imwa.admin.service",
		"mg.imwa.admin.controller",

		"mg.imwa.tenant.model",
		"mg.imwa.tenant.repository",
		"mg.imwa.tenant.controller",
		"mg.imwa.tenant.service"
},exclude = {DataSourceAutoConfiguration.class})
public class AdminApplication extends SpringBootServletInitializer implements CommandLineRunner{

	@Autowired private AdminUserRepository adminUserRepository;

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

	@Override public void run(String... args) throws Exception{
	    String adminUsername = "armand_judicael";
		Optional<Admin> byUserName = adminUserRepository.findByUserName(adminUsername);
        if (byUserName.isEmpty()){
			Admin admin = new Admin();
			admin.setUserName(adminUsername);
			admin.setPassword("Aj!30071999");
			adminUserRepository.save(admin);
		}
	}

	@Override protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AdminApplication.class);
	}
}