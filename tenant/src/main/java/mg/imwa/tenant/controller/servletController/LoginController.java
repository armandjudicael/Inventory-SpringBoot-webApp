package mg.imwa.tenant.controller.servletController;

import mg.imwa.tenant.model.tenantEntityBeans.ClientFournisseur;
import mg.imwa.tenant.model.tenantEntityBeans.User;
import mg.imwa.tenant.repository.CategorieRepository;
import mg.imwa.tenant.repository.ClientFournisseurRepository;
import mg.imwa.tenant.repository.UserRepository;
import mg.imwa.tenant.service.CashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@SessionAttributes(names = "connectedUser")
public class LoginController {
    private UserRepository userRepository;
    private CategorieRepository categorieRepository;
    private final String CONNECTED_USER = "connectedUser";
    private final String DASHBOARD_VIEW = "dashboard";
    private final String LOGIN_VIEW = "login";
    private final String CATEGORIES = "categories";

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return LOGIN_VIEW;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    private String getLogin() {
        return LOGIN_VIEW;
    }

    @Autowired
    private CashService cashService;
    @Autowired
    private ClientFournisseurRepository clientFournisseurRepository;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    private ModelAndView signUp(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Optional<User> optionalUser = userRepository.checkUser(username, password);
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
                modelAndView.setViewName(LOGIN_VIEW);
            }
        }, () -> {
            modelAndView.addObject(CONNECTED_USER, null);
            modelAndView.setViewName(LOGIN_VIEW);
        });
        return modelAndView;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCategorieRepository(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }
}
