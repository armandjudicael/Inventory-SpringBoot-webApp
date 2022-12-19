package mg.imwa.tenant.controller.restController;

import mg.imwa.admin.model.TenantUser;
import mg.imwa.admin.model.UserType;
import mg.imwa.admin.repository.TenantUserRepository;
import mg.imwa.config.TenantContext;
import mg.imwa.tenant.model.tenantEntityBeans.Magasin;
import mg.imwa.tenant.model.tenantEntityBeans.User;
import mg.imwa.tenant.repository.FonctionRepository;
import mg.imwa.tenant.repository.StoreRepository;
import mg.imwa.tenant.repository.SubsidiaryRepository;
import mg.imwa.tenant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserRessource {

    private UserRepository userRepository;

    @Autowired
    public UserRessource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> all = userRepository.findAll();
        return ResponseEntity.of(Optional.of(all));
    }

    @Autowired
    private StoreRepository storeRepository;

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> update(@RequestBody User newUser, @PathVariable(value = "id") Long id) {
        Optional<User> uniteOptional = userRepository.findById(id);
        if (uniteOptional.isPresent()) {
            User user = uniteOptional.get();

            /* initialisation des nouveaux nom , addresse et numero telephone */
            user.setNom(newUser.getNom());
            user.setAdresse(newUser.getAdresse());
            user.setNumTel(newUser.getNumTel());
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            user.setEnabled(newUser.getEnabled());
            /* affectation du  nouveau fonction */
            Long fonctionId = newUser.getFonction().getId();
            fonctionRepository.findById(fonctionId).ifPresent(user::setFonction);
            /* Affectation du nouveau magasin */
            List<Magasin> newStores = newUser.getMagasin();
            List<Magasin> currentStores = user.getMagasin();
            currentStores.clear();
            newStores.forEach(magasin -> {
                Long storeId = magasin.getId();
                storeRepository.findById(storeId).ifPresent(currentStores::add);
            });
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
        }

        return new ResponseEntity<>("The user with the id = " + id + " doesn't exist ", HttpStatus.NOT_FOUND);
    }

    @Autowired
    private FonctionRepository fonctionRepository;

    @Autowired
    private TenantUserRepository tenantUserRepository;

    @Autowired
    private SubsidiaryRepository subsidiaryRepository;

    @PostMapping(value = "/users")
    public ResponseEntity<Object> create(@RequestBody User user){

        User save = userRepository.save(user);

        TenantUser tenantUser = new TenantUser();
        tenantUser.setUserName(user.getUsername());
        tenantUser.setPassword(user.getPassword());
        tenantUser.setUserType(UserType.SIMPLE_USER);

        Long filialeId = user.getFiliale().getId();
        subsidiaryRepository.findById(filialeId).ifPresent(filiale -> {
            String companyName = TenantContext.getTenantId().split("_")[0];
            String key = companyName+"-"+filiale.getNom();
            tenantUser.setKey(key);
            tenantUserRepository.save(tenantUser);
        });

        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @GetMapping(value = "/users/{username}/{password}")
    public ResponseEntity<Object> checkUser(@PathVariable(name = "username") String username, @PathVariable(name = "password") String password) {
        Optional<User> optionalUser = userRepository.checkUser(username, password);
        if (optionalUser.isPresent()) return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        return new ResponseEntity<>(" Unable to find user with username = " + username + " and password =" + password, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(" The feature with the id =" + id + " is deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(500));
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.<ResponseEntity<Object>>map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>("The user with the id = " + id + " doesn't exist !", HttpStatus.NOT_FOUND));
    }

    @GetMapping("/users/name/{filiale-id}/{value}")
    public ResponseEntity<Object> findByName(@PathVariable("filiale-id") Long filialeId, @PathVariable("value") String name) {
        return new ResponseEntity<>(userRepository.findByName(name, filialeId), HttpStatus.OK);
    }

}
