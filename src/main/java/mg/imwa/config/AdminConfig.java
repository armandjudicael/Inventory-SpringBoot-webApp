package mg.imwa.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(basePackages = {"mg.imwa.admin.model","mg.imwa.admin.repository","mg.imwa.admin.service"}
,entityManagerFactoryRef = "adminEntityManagerFactory",transactionManagerRef = "adminTransactionManager")
@EnableTransactionManagement
public class AdminConfig{
    /*  ADMIN DATASOURCE */
    @Bean
    @Primary
    @ConfigurationProperties("admin.datasource")
    public DataSourceProperties adminDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("admin.datasource.configuration")
    public HikariDataSource adminDataSource(){
        return adminDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "adminEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean memberEntityManagerFactory(){
        HashMap<String, Object> jpaPropertiesMap = new HashMap<>();
        jpaPropertiesMap.put("hibernate.dialect",
                "org.hibernate.dialect.PostgreSQLDialect");
        jpaPropertiesMap.put("hibernate.physical_naming_strategy",
                "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
        jpaPropertiesMap.put("hibernate.implicit_naming_strategy","org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
        jpaPropertiesMap.put("hibernate.hbm2ddl.auto", "update");
        jpaPropertiesMap.put("hibernate.show_sql","true");
        jpaPropertiesMap.put("hibernate.enable_lazy_load_no_trans","true");
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        em.setJpaPropertyMap(jpaPropertiesMap);
        em.setDataSource(adminDataSource());
        em.setPackagesToScan("mg.imwa.admin.model");
        em.setPersistenceUnitName("admin-unit");
        return em;
    }

    @Primary
    @Bean(name = "adminTransactionManager")
    public PlatformTransactionManager memberTransactionManager(
            final @Qualifier("adminEntityManagerFactory") LocalContainerEntityManagerFactoryBean memberEntityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(memberEntityManagerFactory.getObject());
        return jpaTransactionManager;
    }
}