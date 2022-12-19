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
import java.util.Optional;
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

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    public List<Company> getAll(){
        return companyRepository.findAll();
    }

    public Company create(Company company){
            if(databaseDontExist(company.getNom().toLowerCase())){
                CompanyDataSourceConfig cdc = company.getCompanyDataSourceConfig();
                String databaseName = cdc.getDatabaseName().toLowerCase();
                createNewDatabase(databaseName,cdc);
                String validationKey = generateValidationKey(company.getNom());
                company.setValidationKey(validationKey);
                return companyRepository.save(company);
            }
            return null;
    }

    public Boolean delete(Long id){
        return true;
    }


    public Company update(Company company,Long id){
        Optional<Company> byId = companyRepository.findById(id);
        if (byId.isPresent()){
            company.setId(id);
            return companyRepository.save(company);
        }
        return company;
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
        dataSource.close();
    }

    private Void createNewDatabase(String databaseName,CompanyDataSourceConfig cdc){
        try {
                Connection connection = Objects.requireNonNull(entityManagerFactory.getDataSource()).getConnection();
                connection.createStatement().execute(" CREATE DATABASE "+ databaseName +";");
                HikariDataSource hikariDataSource = cdc.initDatasource();
                executeFlywayMigration(hikariDataSource);
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    private Boolean databaseDontExist(String dbname){
        return companyDatasourceConfigRepo.findAll().stream().noneMatch(companyDataSourceConfig -> companyDataSourceConfig.getDatabaseName().equals(dbname));
    }
}