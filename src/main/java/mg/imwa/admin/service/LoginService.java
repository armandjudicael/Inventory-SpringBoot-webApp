package mg.imwa.admin.service;

import com.zaxxer.hikari.HikariDataSource;

import mg.imwa.admin.model.Company;
import mg.imwa.admin.model.SocieteStatus;
import mg.imwa.config.MapMultiTenantConnectionProvider;
import mg.imwa.config.TenantContext;
import mg.imwa.admin.model.TenantUser;
import mg.imwa.admin.repository.CompanyRepository;
import mg.imwa.admin.repository.TenantUserRepository;
import mg.imwa.tenant.model.tenantEntityBeans.ClientFournisseur;
import mg.imwa.tenant.model.tenantEntityBeans.User;
import mg.imwa.tenant.repository.CategorieRepository;
import mg.imwa.tenant.repository.ClientFournisseurRepository;
import mg.imwa.tenant.repository.UserRepository;
import mg.imwa.tenant.service.CashService;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired private CashService cashService;

    @Autowired private ClientFournisseurRepository clientFournisseurRepository;

    private final String CONNECTED_USER = "connectedUser";
    private final String DASHBOARD_VIEW = "dashboard";
    private final String TENANT_LOGIN_VIEW = "login/tenant-user-login";
    private final String CATEGORIES = "categories";

    private ModelAndView initDashboardView(String username,String password,String subsidiaryName){
        ModelAndView modelAndView = new ModelAndView();
        Optional<User> optionalUser = userRepository.checkUser(username,password,subsidiaryName);
        optionalUser.ifPresentOrElse(user -> {
            if (user.getEnabled()) {
                modelAndView.addObject(CONNECTED_USER, user);
                Long filialeId = user.getFiliale().getId();
                modelAndView.addObject(CATEGORIES, categorieRepository.findAll());
                Map<String, Double> cashInfo = cashService.getCashInfo(filialeId);
                modelAndView.addObject("espece", cashInfo.get(CashService.getSommeEspece()));
                modelAndView.addObject("credit", cashInfo.get(CashService.getSommeCredit()));
                modelAndView.addObject("depense", cashInfo.get(CashService.getSommeDepense()));
                modelAndView.addObject("encaissement", cashInfo.get(CashService.getSommeEncaissement()));

                List<ClientFournisseur> clientList = clientFournisseurRepository.getAllExternalEntities(0, filialeId)
                        .stream()
                        .filter(cf -> !cf.getTotalTrosa().equals(0.0)).collect(Collectors.toList());

                List<ClientFournisseur> frsList = clientFournisseurRepository.getAllExternalEntities(1, filialeId)
                        .stream()
                        .filter(cf -> !cf.getTotalTrosa().equals(0.0)).collect(Collectors.toList());

                modelAndView.addObject("client_list", clientList);
                modelAndView.addObject("fournisseur_list", frsList);
                modelAndView.setViewName(user.getFonction().getDefaultPage().getViewUrl());

            } else {
                modelAndView.addObject("DISABLED_USER", "UTILISATEUR DESACTIVE");
                modelAndView.addObject(CONNECTED_USER, null);
                modelAndView.setViewName(TENANT_LOGIN_VIEW);
            }
        },() -> {
            modelAndView.addObject(CONNECTED_USER, null);
        });
        return modelAndView;
    }

    private ModelAndView initDashboardView(String username,String password,String subsidiaryName,ModelAndView modelAndView){
        Optional<User> optionalUser = userRepository.checkUser(username,password,subsidiaryName);
        optionalUser.ifPresentOrElse(user -> {
            if (user.getEnabled()) {
                modelAndView.addObject(CONNECTED_USER, user);
                Long filialeId = user.getFiliale().getId();
                modelAndView.addObject(CATEGORIES, categorieRepository.findAll());
                Map<String, Double> cashInfo = cashService.getCashInfo(filialeId);
                modelAndView.addObject("espece", cashInfo.get(CashService.getSommeEspece()));
                modelAndView.addObject("credit", cashInfo.get(CashService.getSommeCredit()));
                modelAndView.addObject("depense", cashInfo.get(CashService.getSommeDepense()));
                modelAndView.addObject("encaissement", cashInfo.get(CashService.getSommeEncaissement()));

                List<ClientFournisseur> clientList = clientFournisseurRepository.getAllExternalEntities(0, filialeId)
                        .stream()
                        .filter(cf -> !cf.getTotalTrosa().equals(0.0)).collect(Collectors.toList());

                List<ClientFournisseur> frsList = clientFournisseurRepository.getAllExternalEntities(1, filialeId)
                        .stream()
                        .filter(cf -> !cf.getTotalTrosa().equals(0.0)).collect(Collectors.toList());

                modelAndView.addObject("client_list", clientList);
                modelAndView.addObject("fournisseur_list", frsList);
                modelAndView.setViewName(user.getFonction().getDefaultPage().getViewUrl());

            } else {
                modelAndView.addObject("DISABLED_USER", "UTILISATEUR DESACTIVE");
                modelAndView.addObject(CONNECTED_USER, null);
                modelAndView.setViewName(TENANT_LOGIN_VIEW);
            }
        },() -> {
            modelAndView.addObject(CONNECTED_USER, null);
        });
        return modelAndView;
    }

    public ModelAndView checkTenantandSubsidiary(String username,String password,String key){
            ModelAndView modelAndView = new ModelAndView(TENANT_LOGIN_VIEW);
            Optional<TenantUser> optionalTenantUser = tenantUserRepository.findByUsernameAndPasswordAndKey(username,password,key);
            if (optionalTenantUser.isPresent()){
                String[] split = key.split("-");
                String companyName = split[0].toLowerCase();
                String subsidiaryName = split[1];
                Optional<Company> optionalCompany = companyRepository.findByName(companyName);
                if (optionalCompany.isPresent()){
                    Company company = optionalCompany.get();
                    SocieteStatus societeStatus = company.getSocieteStatus();
                    if (societeStatus.equals(SocieteStatus.ENABLED)){
                        initCurrentDatasourceAndTenantContext(companyName);
                        return initDashboardView(username,password,subsidiaryName,modelAndView);
                    }else modelAndView.addObject("COMPANY_DISABLED"," Votre filiale n'est pas disponible pour le moment ! Veuillez contacter les responsables du site pour plus d'information ");
                }
            }
        return modelAndView;
    }

    public void initCurrentDatasourceAndTenantContext(String companyName){
        Map<String, ConnectionProvider> connectionProviderMap = mapMultiTenantConnectionProvider.getConnectionProviderMap();
        if (!connectionProviderMap.isEmpty() && connectionProviderMap.containsKey(companyName)) {
            TenantContext.setTenantId(companyName);
        } else findOnCompanyTable(companyName,connectionProviderMap);
    }

    private void findOnCompanyTable(String companyName,Map<String, ConnectionProvider> connectionProviderMap){
        companyRepository.findByName(companyName).ifPresent(company -> {
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
        });
    }

}
