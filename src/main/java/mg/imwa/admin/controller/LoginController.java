package mg.imwa.admin.controller;
import mg.imwa.admin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController{

    @Autowired
    private LoginService loginService;

//    @RequestMapping(method = RequestMethod.GET,value = "/company-admin-login.html")
//    public String getCompanyAdminLogin(){
//        return "company-admin-login";
//    }
//
//    @RequestMapping(method = RequestMethod.GET,value = "/imwa-admin-login.html")
//    public String getImwaAdminLogin(){
//        return "imwa-admin-login";
//    }

    @RequestMapping(method = RequestMethod.GET,value = {"/index.jsp","/"})
    public String index(){
        return "login/tenant-user-login";
    }

    @PostMapping("/signup")
    public ModelAndView signUp(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String key = request.getParameter("filialeKey");
        System.out.println(" Username = "+username+" - " + " Password = "+password);
        ModelAndView modelAndView = loginService.checkTenantandSubsidiary(username, password, key);
        return modelAndView;
    }

}
