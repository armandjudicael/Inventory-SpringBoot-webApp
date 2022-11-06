package mg.imwa.admin.service;

import com.zaxxer.hikari.HikariDataSource;
import mg.imwa.admin.model.Company;
import mg.imwa.admin.model.CompanyDataSourceConfig;
import mg.imwa.admin.repository.CompanyDatasourceConfigRepo;
import mg.imwa.admin.repository.CompanyRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.output.MigrateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;
import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

@Service
public class CompanyService{

    private final String[] emailTab = {
            "judicael.ratombotiana@gmail.com",
            "embony.rodibrian@gmail.com",
            "g.manager.bis@gmail.com"};

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyDatasourceConfigRepo companyDatasourceConfigRepo;

//    @Autowired
//    private EmailService emailService;

    @Autowired
    private Executor executor;

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    public List<Company> getAll(){
        return companyRepository.findAll();
    }

    public Company create(Company company){
        executor.execute(() -> {
            CompanyDataSourceConfig cdc = company.getCompanyDataSourceConfig();
            HikariDataSource hikariDataSource = cdc.initDatasource();
            String databaseName = cdc.getDatabaseName().toLowerCase();
            createNewDatabase(databaseName,hikariDataSource);
        });
        String validationKey = generateValidationKey(company.getNom());
        company.setValidationKey(validationKey);
        return companyRepository.save(company);
    }

    private String generateValidationKey(String companyName){
        String key = RandomStringUtils.randomAlphanumeric(30);
        String kaelKey = key.substring(0,9);
        String brianKey = key.substring(10,19);
        String ddsKey = key.substring(20,30);
        sendEmail(new String[]{kaelKey,brianKey,ddsKey},companyName);
        return key;
    }

    private void sendEmail(String[] keyTab , String companyName){
        for (int i = 0; i < keyTab.length; i++) {
        //   emailService.sendEmail(emailTab[i],"Clé d'activation de la societé "+companyName+" : "+keyTab[i]," Activation de la societé "+companyName);
        }
    }

    private void executeFlywayMigration(HikariDataSource dataSource){
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        MigrateResult migrate = flyway.migrate();
    }

    private Void createNewDatabase(String databaseName,HikariDataSource dataSource){
        try {
            if (databaseDontExist(databaseName)){
                Connection connection = Objects.requireNonNull(entityManagerFactory.getDataSource()).getConnection();
                connection.createStatement().execute(" CREATE DATABASE "+ databaseName +";");
                executeFlywayMigration(dataSource);
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    private Boolean databaseDontExist(String dbname){
        return companyDatasourceConfigRepo.findAll().stream().noneMatch(companyDataSourceConfig -> companyDataSourceConfig.getDatabaseName().equals(dbname));
    }
}