package mg.imwa.admin.service;

import com.zaxxer.hikari.HikariDataSource;

import mg.imwa.admin.model.UserType;
import mg.imwa.config.MapMultiTenantConnectionProvider;
import mg.imwa.config.TenantContext;
import mg.imwa.admin.model.Company;
import mg.imwa.admin.model.TenantUser;
import mg.imwa.admin.repository.CompanyRepository;
import mg.imwa.admin.repository.TenantUserRepository;
import mg.imwa.tenant.model.tenantEntityBeans.User;
import mg.imwa.tenant.repository.CategorieRepository;
import mg.imwa.tenant.repository.UserRepository;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginService{

    @Autowired private CompanyRepository companyRepository;
    @Autowired private MapMultiTenantConnectionProvider mapMultiTenantConnectionProvider;
    @Autowired private TenantUserRepository tenantUserRepository;

    public ModelAndView checkTenantandSubsidiary(ModelAndView modelAndView,String companyName,String subisdiaryName){
        initCurrentDatasourceAndTenantContext(companyName);
        return modelAndView;
    }


    @Autowired private UserRepository userRepository;

    @Autowired private CategorieRepository categorieRepository;

    private final String CONNECTED_USER = "connectedUser";
    private final String DASHBOARD_VIEW = "dashboard";
    private final String LOGIN_VIEW = "tenant-user-login";
    private final String CATEGORIES = "categories";

    private ModelAndView initDashboardView(String username,String password,String subsidiaryName){
        ModelAndView modelAndView = new ModelAndView();
        Optional<User> optionalUser = userRepository.checkUser(username,password,subsidiaryName);
        optionalUser.ifPresentOrElse(user -> {
            if (user.getEnabled()) {
                modelAndView.addObject(CONNECTED_USER, user);
                modelAndView.setViewName(user.getFonction().getDefaultPage().getViewUrl());
            } else {
                modelAndView.addObject("DISABLED_USER", "UTILISATEUR DESACTIVE");
                modelAndView.addObject(CONNECTED_USER, null);
                modelAndView.setViewName(LOGIN_VIEW);
            }
        },() -> {
            modelAndView.addObject(CONNECTED_USER, null);
            modelAndView.setViewName(LOGIN_VIEW);
        });
        return modelAndView;
    }

    public ModelAndView checkTenantandSubsidiary(String username,String password,String key){
        ModelAndView modelAndView = new ModelAndView("tenant-user-login");
        Optional<TenantUser> optionalTenantUser = tenantUserRepository.findByUsernameAndPasswordAndKey(username,password,key,UserType.SIMPLE_USER);
        if (optionalTenantUser.isPresent()){
            String[] split = key.split("-");
            String companyName = split[0].toLowerCase();
            String subsidiaryName = split[1];
            initCurrentDatasourceAndTenantContext(companyName);
            return initDashboardView(username,password,subsidiaryName);
        }
        return modelAndView;
    }

    public void initCurrentDatasourceAndTenantContext(String companyName) {
        Map<String, ConnectionProvider> connectionProviderMap = mapMultiTenantConnectionProvider.getConnectionProviderMap();
        if (!connectionProviderMap.isEmpty() && connectionProviderMap.containsKey(companyName)) {
            TenantContext.setTenantId(companyName);
        } else findOnCompanyTable(companyName,connectionProviderMap);
    }

    private void findOnCompanyTable(String companyName, Map<String, ConnectionProvider> connectionProviderMap){
        Optional<Company> companyOptional = companyRepository.findByName(companyName);
        if (companyOptional.isPresent()){
            Company company = companyOptional.get();
            HikariDataSource hikariDataSource = company.getCompanyDataSourceConfig().initDatasource();
            connectionProviderMap.put(companyName,new ConnectionProvider() {
                @Override
                public Connection getConnection() throws SQLException {
                    return hikariDataSource.getConnection();
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
        }
    }
}
