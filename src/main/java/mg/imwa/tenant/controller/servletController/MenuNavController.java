package mg.imwa.tenant.controller.servletController;

import mg.imwa.tenant.model.entityEnum.TypeMateriel;
import mg.imwa.tenant.model.tenantEntityBeans.*;
import mg.imwa.tenant.repository.*;
import mg.imwa.tenant.service.ActivityService;
import mg.imwa.tenant.service.CashService;
import mg.imwa.tenant.service.ItemService;
import mg.imwa.tenant.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Clock;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@SessionAttributes(names = {"connectedUser", "articles"})
public class MenuNavController{
    private final String VOYAGE_LIST = "voyages";
    private final String CATEGORIE_LIST = "categories";
    private final String ARTICLE_LIST = "articles";
    private final String MAGASIN_LIST = "magasins";
    private final String FONCTION_LIST = "fonctions";
    private final String USER_LIST = "users";
    private final String CLIENT_FOURNISSEUR_LIST = "cfList";
    private final String STOCKS = "stocks";
    private final String SALES_LIST = "sales";
    private final String CONNECTED_USER = "connectedUser";
    private final String MATERIEL_TRANSPORT_LIST = "materiel_transportList";
    private final int CLIENT = 0;
    private final int FOURNISSEUR = 1;
    private final String SUBSDIARIES = "subsdiaries";
    private final String PRICES_LIST = "prices";
    private final String OPERATION_LIST = "operations";
    private final String FACTURE_LIST = "factures";
    private final String PEREMPTION_LIST = "expirations";
    private final String MAGASIN_ID = "MAGASIN_ID";
    private final String FILIALE_ID = "FILIALE_ID";
    private final String TRAJET_LIST = "trajets";

    public MenuNavController() {
    }

    @RequestMapping(value = "/admin-client/gerer-societe", method = RequestMethod.GET)
    public String getAdminClientGererSociete() {
        return "admin-client/gerer-societe";
    }

    @RequestMapping(value = "/admin-client/apropos", method = RequestMethod.GET)
    public String getAdminClientApropos() {
        return "admin-client/apropos";
    }

    @RequestMapping(value = "/admin/gerer-societe", method = RequestMethod.GET)
    public String getAdminGererSociete() {
        return "admin-client/gerer-societe";
    }

    @RequestMapping(value = "/admin/apropos", method = RequestMethod.GET)
    public String getAdminApropos() {
        return "admin-client/apropos";
    }

    @RequestMapping(value = "/admin/", method = RequestMethod.GET)
    public String getAdminLogin() {
        return "login-admin";
    }

    @RequestMapping(value = "/admin-client/", method = RequestMethod.GET)
    public String getAdminSocieteLogin() {
        return "login-societe";
    }

    @RequestMapping(value = "/gerer-utilisateur", method = RequestMethod.GET)
    public String getGererUtilisateur() {
        return "gerer-utilisateur";
    }

    @RequestMapping(value = "/archivage", method = RequestMethod.GET)
    public String getMenuArchivage() {
        return "menu-archivage";
    }

    @RequestMapping(value = "/livraison", method = RequestMethod.GET)
    public String getMenuLivraison() {
        return "menu-livraison";
    }

    @RequestMapping(value = "/paiement", method = RequestMethod.GET)
    public String getMenuPaiement() {
        return "menu-paiement";
    }

    @RequestMapping(value = "/voyage", method = RequestMethod.GET)
    public String getMenuVoyage() {
        return "menu-voyage";
    }


    @RequestMapping(value = "/embarquement", method = RequestMethod.GET)
    public ModelAndView getMenuEmbarquement(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("embarquement/menu-embarquement");
        Map<String, Long> connectedUserMagasinId = getConnectedUserInfo(request);
        Long filialeId = connectedUserMagasinId.get(FILIALE_ID);
        LocalDate now = LocalDate.now(Clock.systemDefaultZone());
        modelAndView.addObject(VOYAGE_LIST, voyageRepository.findAll());
        modelAndView.addObject("cfList_embarquement", clientFournisseurRepository.getAllExternalEntities(FOURNISSEUR, filialeId));
        List<MaterielTransport> transportList = materielTransportRepository.findAll();

        // BATEAU
        List<MaterielTransport> boatList = transportList.stream()
                .filter(materielTransport -> materielTransport.getTypeMateriel().equals(TypeMateriel.BATEAU))
                .collect(Collectors.toList());
        // CAMION
        List<MaterielTransport> carList = transportList.stream()
                .filter(materielTransport -> materielTransport.getTypeMateriel().equals(TypeMateriel.CAMION) || materielTransport.getTypeMateriel().equals(TypeMateriel.CAMIONNETTE))
                .collect(Collectors.toList());

        modelAndView.addObject("boats", transportList);
        modelAndView.addObject("cars", carList);
        modelAndView.addObject(ARTICLE_LIST, itemRepository.getAll());
        modelAndView.addObject(TRAJET_LIST, trajetRepository.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/embarquement-nouveau", method = RequestMethod.GET)
    public ModelAndView getNouveauEmbarquement(HttpServletRequest request) {
        Map<String, Long> connectedUserMagasinId = getConnectedUserInfo(request);
        Long filialeId = connectedUserMagasinId.get(FILIALE_ID);
        ModelAndView modelAndView = new ModelAndView("embarquement/nouveau-embarquement");
        modelAndView.addObject("cfList_embarquement", clientFournisseurRepository.getAllExternalEntities(FOURNISSEUR, filialeId));
        modelAndView.addObject(MATERIEL_TRANSPORT_LIST, materielTransportRepository.findAll());
        modelAndView.addObject(ARTICLE_LIST, itemRepository.getAll());
        modelAndView.addObject(TRAJET_LIST, trajetRepository.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/caisse", method = RequestMethod.GET)
    public ModelAndView getMenuCaisse(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("menu-caisse");
        Map<String, Long> connectedUserMagasinId = getConnectedUserInfo(request);
        Long filialeId = connectedUserMagasinId.get(FILIALE_ID);
        List<InfoFilialeCaisse> all = ifcRepository.findAllByDate(filialeId, LocalDate.now(Clock.systemDefaultZone()));
        modelAndView.addObject(MAGASIN_LIST, storeRepository.findAllByFiliale(filialeId));
        Map<String, Double> cashInfo = cashService.getCashInfo(filialeId);
        modelAndView.addObject(USER_LIST, userRepository.getAllByFiliale(filialeId));
        modelAndView.addObject("avoir", cashInfo.get(CashService.getSommeAvoir()));
        modelAndView.addObject("vente", cashInfo.get(CashService.getSommeVente()));
        modelAndView.addObject("cheque", cashInfo.get(CashService.getSommeCheque()));
        modelAndView.addObject("virement", cashInfo.get(CashService.getSommeVirement()));
        modelAndView.addObject("espece", cashInfo.get(CashService.getSommeEspece()));
        modelAndView.addObject("credit", cashInfo.get(CashService.getSommeCredit()));
        modelAndView.addObject("recette", cashInfo.get(CashService.getRecette()));
        modelAndView.addObject("mobileMoney", cashInfo.get(CashService.getSommeMobileMoney()));
        modelAndView.addObject("depense", cashInfo.get(CashService.getSommeDepense()));
        modelAndView.addObject("consommation", cashInfo.get(CashService.getSommeConsommation()));
        modelAndView.addObject("caisse", all);
        return modelAndView;
    }

    @RequestMapping(value = "/autorisation", method = RequestMethod.GET)
    public ModelAndView getMenuAutorisation(HttpServletRequest request) {
        Map<String, Long> connectedUserMagasinId = getConnectedUserInfo(request);
        Long filialeId = connectedUserMagasinId.get(FILIALE_ID);
        ModelAndView modelAndView = new ModelAndView("menu-autorisation");
        modelAndView.addObject(FONCTION_LIST, fonctionRepository.getAllByFiliale(filialeId));
        return modelAndView;
    }

    @RequestMapping(value = "/peremption", method = RequestMethod.GET)
    public ModelAndView getMenuPeremption(HttpServletRequest request) {
        Map<String, Long> connectedUserMagasinId = getConnectedUserInfo(request);
        Long filialeId = connectedUserMagasinId.get(FILIALE_ID);
        ModelAndView modelAndView = new ModelAndView("menu-peremption");
        modelAndView.addObject(PEREMPTION_LIST, itemService.getProductByExpiration(filialeId));
        modelAndView.addObject(MAGASIN_LIST, storeRepository.findAllByFiliale(filialeId));
        return modelAndView;
    }

    @RequestMapping(value = "/facture", method = RequestMethod.GET)
    public ModelAndView getMenuFacture(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("menu-facture");
        Map<String, Long> connectedUserMagasinId = getConnectedUserInfo(request);
        Long filialeId = connectedUserMagasinId.get(FILIALE_ID);
        Long magasinId = connectedUserMagasinId.get(MAGASIN_ID);
        LocalDate now = LocalDate.now(Clock.systemDefaultZone());
        modelAndView.addObject(FACTURE_LIST, salesRepository.getInvoiceBetweenDate(filialeId, now, now));
        modelAndView.addObject(MAGASIN_LIST, storeRepository.findAllByFiliale(filialeId));
        return modelAndView;
    }

    @RequestMapping(value = "/prix", method = RequestMethod.GET)
    public ModelAndView getMenuPrix(HttpServletRequest request) {
        Map<String, Long> connectedUserMagasinId = getConnectedUserInfo(request);
        Long filialeId = connectedUserMagasinId.get(FILIALE_ID);
        ModelAndView modelAndView = new ModelAndView("menu-prix");
        modelAndView.addObject(PRICES_LIST, pricesRepository.findAllByLastDate(filialeId));
        return modelAndView;
    }

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public ModelAndView getClient(HttpServletRequest request) {
        Map<String, Long> connectedUserMagasinId = getConnectedUserInfo(request);
        Long filialeId = connectedUserMagasinId.get(FILIALE_ID);
        ModelAndView modelAndView = new ModelAndView("menu-client");
        modelAndView.addObject(CLIENT_FOURNISSEUR_LIST, clientFournisseurRepository.getAllExternalEntities(CLIENT, filialeId));
        return modelAndView;
    }

    @RequestMapping(value = "/fournisseur", method = RequestMethod.GET)
    public ModelAndView getMenuFournisseur(HttpServletRequest request) {
        Map<String, Long> connectedUserMagasinId = getConnectedUserInfo(request);
        Long filialeId = connectedUserMagasinId.get(FILIALE_ID);
        ModelAndView modelAndView = new ModelAndView("menu-fournisseur");
        modelAndView.addObject(CLIENT_FOURNISSEUR_LIST, clientFournisseurRepository.getAllExternalEntities(FOURNISSEUR, filialeId));
        return modelAndView;
    }

    @RequestMapping(value = "/utilisateur", method = RequestMethod.GET)
    public ModelAndView getMenuUtilisateur(HttpServletRequest request) {
        Map<String, Long> connectedUserMagasinId = getConnectedUserInfo(request);
        Long filialeId = connectedUserMagasinId.get(FILIALE_ID);
        ModelAndView modelAndView = new ModelAndView("menu-utilisateur");
        modelAndView.addObject(FONCTION_LIST, fonctionRepository.getAllByFiliale(filialeId));
        modelAndView.addObject(USER_LIST, userRepository.getAllByFiliale(filialeId));
        modelAndView.addObject(MAGASIN_LIST, storeRepository.findAllByFiliale(filialeId));
        return modelAndView;
    }

    @RequestMapping(value = "/operation-entree", method = RequestMethod.GET)
    public ModelAndView getOperationEntree(HttpServletRequest request) {
        Map<String, Long> connectedUserMagasinId = getConnectedUserInfo(request);
        Long filialeId = connectedUserMagasinId.get(FILIALE_ID);
        ModelAndView modelAndView = new ModelAndView("operation/entree");
        modelAndView.addObject(ARTICLE_LIST, itemRepository.getAllNotDeletedAndNotHidden("USED"));
        modelAndView.addObject(MAGASIN_LIST, storeRepository.findAllByFiliale(filialeId));
        modelAndView.addObject(CLIENT_FOURNISSEUR_LIST, clientFournisseurRepository.getAllExternalEntities(FOURNISSEUR, filialeId));
        return modelAndView;
    }

    @RequestMapping(value = "/operation-sortie", method = RequestMethod.GET)
    public ModelAndView getOperationSortie(HttpServletRequest request) {
        Map<String, Long> connectedUserMagasinId = getConnectedUserInfo(request);
        Long filialeId = connectedUserMagasinId.get(FILIALE_ID);
        ModelAndView modelAndView = new ModelAndView("operation/sortie");
        modelAndView.addObject(ARTICLE_LIST, itemRepository.getAllNotDeletedAndNotHidden("USED"));
        modelAndView.addObject(MAGASIN_LIST, storeRepository.findAllByFiliale(filialeId));
        return modelAndView;
    }

    @RequestMapping(value = "/operation-transfert", method = RequestMethod.GET)
    public ModelAndView getOperationTransfert(HttpServletRequest request) {
        Map<String, Long> connectedUserMagasinId = getConnectedUserInfo(request);
        Long filialeId = connectedUserMagasinId.get(FILIALE_ID);
        ModelAndView modelAndView = new ModelAndView("operation/transfert");
        modelAndView.addObject(MAGASIN_LIST, storeRepository.findAllByFiliale(filialeId));
        modelAndView.addObject(ARTICLE_LIST, itemRepository.getAllNotDeletedAndNotHidden("USED"));
        return modelAndView;
    }

    /* Administration */
    @RequestMapping(value = "/admin-client/dashboard", method = RequestMethod.GET)
    public ModelAndView getAdministrationClientHome() {
        ModelAndView modelAndView = new ModelAndView("admin-client/dashboard");
        modelAndView.addObject(SUBSDIARIES, subsidiaryRepository.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
    public ModelAndView getAdministration() {
        ModelAndView modelAndView = new ModelAndView("administrateur/dashboard");
        modelAndView.addObject(SUBSDIARIES, subsidiaryRepository.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView getDashboard(HttpServletRequest request){
        Map<String, Long> connectedUserMagasinId = getConnectedUserInfo(request);
        Long filialeId = connectedUserMagasinId.get(FILIALE_ID);
        ModelAndView modelAndView = new ModelAndView("dashboard");
        modelAndView.addObject("client_list", clientFournisseurRepository.getAllExternalEntities(CLIENT, filialeId));
        modelAndView.addObject("fournisseur_list", clientFournisseurRepository.getAllExternalEntities(FOURNISSEUR, filialeId));
        Map<String, Double> cashInfo = cashService.getCashInfo(filialeId);
        modelAndView.addObject("espece",cashInfo.get(CashService.getSommeEspece()));
        modelAndView.addObject("credit",cashInfo.get(CashService.getSommeCredit()));
        modelAndView.addObject("depense",cashInfo.get(CashService.getSommeDepense()));
        modelAndView.addObject("encaissement",cashInfo.get(CashService.getSommeEncaissement()));
        modelAndView.addObject("client_list",clientFournisseurRepository.getAllExternalEntities(0, filialeId));
        modelAndView.addObject("fournisseur_list",clientFournisseurRepository.getAllExternalEntities(1, filialeId));
        return modelAndView;
    }

    // menu des opérations
    @RequestMapping(value = "/operation-liste", method = RequestMethod.GET)
    public ModelAndView getOperationListe(HttpServletRequest request) {
        Map<String, Long> connectedUserMagasinId = getConnectedUserInfo(request);
        Long filialeId = connectedUserMagasinId.get(FILIALE_ID);
        ModelAndView modelAndView = new ModelAndView("operation/liste");
        modelAndView.addObject(OPERATION_LIST, activityService.findAllByFilialeIdBetweenDate(filialeId));
        modelAndView.addObject(MAGASIN_LIST, storeRepository.findAllByFiliale(filialeId));
        return modelAndView;
    }


    @RequestMapping(value = {"/articles"}, method = {RequestMethod.GET})
    public ModelAndView getArticles(HttpServletRequest request) {
        Map<String, Long> connectedUserMap = getConnectedUserInfo(request);
        Long filiale_id = connectedUserMap.get(FILIALE_ID);
        ModelAndView modelAndView = new ModelAndView("menu-article");
        modelAndView.addObject(CATEGORIE_LIST, this.categorieRepository.findAll());
        modelAndView.addObject(ARTICLE_LIST, itemRepository.getAllNotDeletedAndNotHidden("USED"));
        return modelAndView;
    }

    @RequestMapping(value = "/ventes", method = RequestMethod.GET)
    public ModelAndView getVentes(HttpServletRequest request) {
        Map<String, Long> map = getConnectedUserInfo(request);
        Long filialeId = map.get(FILIALE_ID);
        Long magasinId = map.get(MAGASIN_ID);
        ModelAndView modelAndView = new ModelAndView("menu-vente");
        modelAndView.addObject(MAGASIN_LIST, storeRepository.findAllByFiliale(filialeId));
        modelAndView.addObject(ARTICLE_LIST, itemService.getAllItemInfo(filialeId, magasinId));
        modelAndView.addObject(CLIENT_FOURNISSEUR_LIST, clientFournisseurRepository.getAllExternalEntities(CLIENT, filialeId));
        return modelAndView;
    }

    private Map<String, Long> getConnectedUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User connectedUser = (User) session.getAttribute(CONNECTED_USER);
        Filiale filiale = connectedUser.getFiliale();
        List<Magasin> magasins = connectedUser.getMagasin();
        Magasin magasin = magasins.get(0);
        Long magasinId = magasin.getId();
        Long filialeId = filiale.getId();
        Map<String, Long> map = new HashMap<>();
        map.put(FILIALE_ID, filialeId);
        map.put(MAGASIN_ID, magasinId);
        return map;
    }

    @RequestMapping(value = "/detail-ventes", method = RequestMethod.GET)
    public ModelAndView getDetailVentes(HttpServletRequest request) {
        Map<String, Long> connectedUserMagasinId = getConnectedUserInfo(request);
        Long filialeId = connectedUserMagasinId.get(FILIALE_ID);
        ModelAndView modelAndView = new ModelAndView("menu-detail-vente");
        LocalDate now = LocalDate.now(Clock.systemDefaultZone());
        modelAndView.addObject(SALES_LIST, salesService.getAllInvoiceBetweenDate(filialeId, now, now));
        modelAndView.addObject(MAGASIN_LIST, storeRepository.findAllByFiliale(filialeId));
        return modelAndView;
    }

    @RequestMapping(value = "/magasin", method = RequestMethod.GET)
    public ModelAndView getMenuMagasin(HttpServletRequest request) {
        Map<String, Long> connectedUserMagasinId = getConnectedUserInfo(request);
        Long filialeId = connectedUserMagasinId.get(FILIALE_ID);
        List<Magasin> magasins = storeRepository.findAllByFiliale(filialeId);
        ModelAndView modelAndView = new ModelAndView("menu-magasin");
        modelAndView.addObject(MAGASIN_LIST, magasins);
        return modelAndView;
    }

    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    public ModelAndView getMenuStock(HttpServletRequest request){
        Map<String, Long> connectedUserMagasinId = getConnectedUserInfo(request);
        Long filialeId = connectedUserMagasinId.get(FILIALE_ID);
        ModelAndView modelAndView = new ModelAndView("menu-stock");
        modelAndView.addObject(MAGASIN_LIST, storeRepository.findAllByFiliale(filialeId));
        modelAndView.addObject(STOCKS, itemService.getTotalInventory(filialeId));
        return modelAndView;
    }

    @RequestMapping(value = "/transports", method = RequestMethod.GET)
    public ModelAndView getMenuTransports() {
        ModelAndView modelAndView = new ModelAndView("menu-transport");
        List<MaterielTransport> transportList = materielTransportRepository.findAll();
        modelAndView.addObject("transports", transportList);
        modelAndView.addObject("responsables", psRepository.findAllByFunction("chauffeur"));
        return modelAndView;
    }

    @Autowired private PersonnePhysiqueRepository psRepository;
    @Autowired private MaterielTransportRepository materielTransportRepository;
    @Autowired private TrajetRepository trajetRepository;
    @Autowired private SalesService salesService;
    @Autowired private ActivityService activityService;
    @Autowired private CategorieRepository categorieRepository;
    @Autowired private StoreRepository storeRepository;
    @Autowired private FonctionRepository fonctionRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ItemRepository itemRepository;
    @Autowired private ClientFournisseurRepository clientFournisseurRepository;
    @Autowired private ItemService itemService;
    @Autowired private SubsidiaryRepository subsidiaryRepository;
    @Autowired private SalesRepository salesRepository;
    @Autowired private IfcRepository ifcRepository;
    @Autowired private CashService cashService;
    @Autowired private PricesRepository pricesRepository;
    @Autowired private VoyageRepository voyageRepository;
}