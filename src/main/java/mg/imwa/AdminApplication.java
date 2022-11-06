package mg.imwa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
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
public class AdminApplication{
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
}
