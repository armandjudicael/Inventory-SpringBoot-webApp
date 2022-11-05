package mg.imwa.admin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages ={
		"mg.imwa.admin.config",
		"mg.imwa.admin.model",
		"mg.imwa.admin.repository",
		"mg.imwa.admin.service",
		"mg.imwa.admin.controller",

		"mg.imwa.admin.model",
		"mg.imwa.admin.repository",
		"mg.imwa.admin.controller",
		"mg.imwa.admin.service"
},exclude = {DataSourceAutoConfiguration.class})
public class AdminApplication{
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
}
