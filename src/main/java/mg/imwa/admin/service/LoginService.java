package mg.imwa.admin.service;

import com.zaxxer.hikari.HikariDataSource;

import mg.imwa.admin.config.MapMultiTenantConnectionProvider;
import mg.imwa.admin.config.TenantContext;
import mg.imwa.admin.model.Company;
import mg.imwa.admin.model.CompanyDataSourceConfig;
import mg.imwa.admin.model.TenantUser;
import mg.imwa.admin.repository.CompanyRepository;
import mg.imwa.admin.repository.TenantUserRepository;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginService{

    @Autowired private CompanyRepository companyRepository;
//    @Autowired private ItemService itemService;
    @Autowired private MapMultiTenantConnectionProvider mapMultiTenantConnectionProvider;
    @Autowired private TenantUserRepository tenantUserRepository;

    public ModelAndView checkTenantandSubsidiary(ModelAndView modelAndView,String companyName,String subisdiaryName){
        Map<String,ConnectionProvider> connectionProviderMap = mapMultiTenantConnectionProvider.getConnectionProviderMap();
        String dbName = "";
        if (!connectionProviderMap.isEmpty() && connectionProviderMap.containsKey(companyName)){
            TenantContext.setTenantId(companyName);
            dbName = companyName;
        } else dbName = findOnCompanyTable(companyName, connectionProviderMap);
        modelAndView.addObject("base",dbName);
//        modelAndView.addObject("items", itemService.findAll());
        return modelAndView;
    }

    public ModelAndView checkTenantandSubsidiary(String username,String password,String key){
        ModelAndView modelAndView = new ModelAndView("iol");
        Optional<TenantUser> optionalTenantUser = tenantUserRepository.findByUsernameAndPasswordAndKey(username,password, key);
        if (optionalTenantUser.isPresent()){
            Map<String,ConnectionProvider> connectionProviderMap = mapMultiTenantConnectionProvider.getConnectionProviderMap();
            String[] split = key.split("-");
            String companyName = split[0].toLowerCase();
            String subsidiaryName = split[1];
            String dbName = "";
            if (!connectionProviderMap.isEmpty() && connectionProviderMap.containsKey(companyName)){
                TenantContext.setTenantId(companyName);
                dbName = companyName;
            } else dbName = findOnCompanyTable(companyName, connectionProviderMap);
//            initSubsidiaryView(modelAndView, username, password, subsidiaryName, dbName);
        }
        return modelAndView;
    }

//    private void initSubsidiaryView(ModelAndView modelAndView, String username, String password, String subsidiaryName, String dbName) {
//        Optional<User> optionalUser = TenantUserRepository.findUsernameAndPasswordAndSubsidiaryName(username,password,subsidiaryName);
//        if (optionalUser.isPresent()){
//            User user = optionalUser.get();
//            modelAndView.addObject("base", dbName);
//            modelAndView.addObject("subsidiary",subsidiaryName);
//            modelAndView.addObject("Utilisateur",user);
//            modelAndView.addObject("items", itemService.findAll());
//        }
//    }


    private String findOnCompanyTable(String companyName, Map<String, ConnectionProvider> connectionProviderMap) {
        Optional<Company> companyOptional = companyRepository.findByName(companyName);
        if (companyOptional.isPresent()){
            Company company = companyOptional.get();
            CompanyDataSourceConfig cdc = company.getCompanyDataSourceConfig();
            String  url = "jdbc:"+cdc.getDatabaseType().dbType2String()+"://"+cdc.getHost()+":"+cdc.getPort()+"/"+cdc.getDatabaseName();
            DataSourceProperties dsp = new DataSourceProperties();
            dsp.setUrl(url);
            dsp.setUsername(cdc.getUsername());
            dsp.setPassword(cdc.getPassword());
            dsp.setDriverClassName(cdc.getDriverClassName());
            HikariDataSource dataSource = dsp.initializeDataSourceBuilder().type(HikariDataSource.class).build();
            connectionProviderMap.put(companyName, new ConnectionProvider() {
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
            TenantContext.setTenantId(companyName);
            return companyName;
        }
        return "";
    }

}
