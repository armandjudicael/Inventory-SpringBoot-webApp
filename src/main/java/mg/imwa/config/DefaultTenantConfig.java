package mg.imwa.config;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = {"mg.imwa.tenant.model","mg.imwa.tenant.repository","mg.imwa.tenant.service"}
        ,entityManagerFactoryRef = "defaultTenantEmf",transactionManagerRef = "defaultTenantTxManager")
@EnableTransactionManagement
public class DefaultTenantConfig{
    /* DEFAULT DATASOURCE */
    @Bean
    @ConfigurationProperties("default.datasource")
    public DataSourceProperties defaultTenantDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("defaultDatasource")
    @ConfigurationProperties("default.datasource.configuration")
    public HikariDataSource defaultTenantDataSource(){
        return defaultTenantDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean("defaultTenantEmf")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("defaultDatasource") DataSource dataSource,
                                                                       MapMultiTenantConnectionProvider multiTenantConnectionProvider,
                                                                       TenantIdentifierResolver tenantIdentifierResolver){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        Map<String, ConnectionProvider> connectionProviderMap = multiTenantConnectionProvider.getConnectionProviderMap();
        connectionProviderMap.put("default_tenant",new ConnectionProvider() {
            @Override
            public Connection getConnection() throws SQLException {
                return dataSource.getConnection();
            }

            @Override
            public void closeConnection(Connection conn) throws SQLException {
                conn.close();
            }

            @Override
            public boolean supportsAggressiveRelease() {
                return false;
            }

            @Override
            public boolean isUnwrappableAs(Class unwrapType) {
                return false;
            }

            @Override
            public <T> T unwrap(Class<T> unwrapType) {
                return null;
            }
        });
        em.setPersistenceUnitName("default-tenant-unit");
        em.setPackagesToScan("mg.imwa.tenant.model");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> jpaPropertiesMap = new HashMap<>();
        jpaPropertiesMap.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        jpaPropertiesMap.put("hibernate.physical_naming_strategy",
                "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
        jpaPropertiesMap.put("hibernate.implicit_naming_strategy","org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
        jpaPropertiesMap.put("hibernate.hbm2ddl.auto","update");
        jpaPropertiesMap.put("hibernate.show_sql","true");
        jpaPropertiesMap.put("hibernate.enable_lazy_load_no_trans","true");
        jpaPropertiesMap.put(Environment.MULTI_TENANT,MultiTenancyStrategy.DATABASE);
        jpaPropertiesMap.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER,multiTenantConnectionProvider);
        jpaPropertiesMap.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER,tenantIdentifierResolver);
        em.setJpaPropertyMap(jpaPropertiesMap);
        return em;
    }

    @Bean(name = "defaultTenantTxManager")
    public PlatformTransactionManager defaultTenantTransactionManager(
            final @Qualifier("defaultTenantEmf") LocalContainerEntityManagerFactoryBean memberEntityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(memberEntityManagerFactory.getObject());
        jpaTransactionManager.afterPropertiesSet();
        return jpaTransactionManager;
    }
}
