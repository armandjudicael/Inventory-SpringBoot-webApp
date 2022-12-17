package mg.imwa.admin.controller;
import mg.imwa.admin.model.UserType;
import mg.imwa.admin.repository.AdminUserRepository;
import mg.imwa.admin.repository.CompanyRepository;
import mg.imwa.admin.repository.TenantUserRepository;
import mg.imwa.admin.service.LoginService;
import mg.imwa.tenant.repository.SubsidiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes(names = {"tenantAdmin","imwaAdmin","connectedUser"})
public class LoginController{

    private final String COMPANY_LIST="companies";
    @Autowired private LoginService loginService;
    @Autowired private CompanyRepository companyRepository;
    @Autowired private TenantUserRepository tenantUserRepository;
    @Autowired private AdminUserRepository adminUserRepository;

    @RequestMapping(method = RequestMethod.GET,value = {"/index","/user-login"})
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("login/tenant-user-login");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET,value = {"/company-admin-login","/company-admin-log-out"})
    public ModelAndView getCompanyAdminLogin(){
        ModelAndView modelAndView = new ModelAndView("login/company-admin-login");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET,value = {"/imwa-admin-login","/imwa-admin-log-out"})
    public ModelAndView getImwaAdminLogin(){
        ModelAndView modelAndView = new ModelAndView("login/admin-login");
        return modelAndView;
    }

    @PostMapping("/imwa-admin-signup")
    public ModelAndView adminSignup(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView(        "login/admin-login");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        adminUserRepository.findByUserNameAndPassword(username,password).ifPresent(admin -> {
            modelAndView.addObject("imwaAdmin",admin);
            modelAndView.addObject(COMPANY_LIST,companyRepository.findAll());
            modelAndView.setViewName("administrateur/dashboard");
        });
        return modelAndView;
    }

    @Autowired
    private SubsidiaryRepository subsidiaryRepository;

    @PostMapping("/company-admin-signup")
    public ModelAndView companyAdminSignup(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("login/company-admin-login");
        String subsidiaryName = request.getParameter("subsidiary-name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        tenantUserRepository.findByUsernameAndPasswordAndKey(username,password,subsidiaryName,UserType.COMPANY_ADMIN).ifPresent(tenantUser ->{
            HttpSession session = request.getSession();
            if (session.getAttribute("tenantAdmin")==null) modelAndView.addObject("tenantAdmin",tenantUser);
            String databaseName = tenantUser.getKey();
            loginService.initCurrentDatasourceAndTenantContext(databaseName);
            modelAndView.addObject("subsdiaries",subsidiaryRepository.findAll());
            modelAndView.setViewName("admin-client/dashboard");
        });
        return modelAndView;
    }

    @PostMapping("/imwa-user-signup")
    public ModelAndView signUp(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String key = request.getParameter("key");
        return loginService.checkTenantandSubsidiary(username,password,key);
    }
}
