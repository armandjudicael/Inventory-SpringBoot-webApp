package mg.imwa.tenant.controller.restController;


import mg.imwa.tenant.model.tenantEntityBeans.InfoFilialeCaisse;
import mg.imwa.tenant.model.wrapper.IfcWrapper;
import mg.imwa.tenant.repository.CashRepository;
import mg.imwa.tenant.repository.IfcRepository;
import mg.imwa.tenant.repository.UserRepository;
import mg.imwa.tenant.repository.VenteRepository;
import mg.imwa.tenant.service.CashService;
import mg.imwa.tenant.service.RepoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ifcRessource {

    @Autowired
    private VenteRepository venteRepository;

    @Autowired
    private IfcRepository ifcRepository;
    @Autowired
    private CashService cashService;

    @PostMapping("/ifc")
    public ResponseEntity<Object> create(@RequestBody InfoFilialeCaisse infoFilialeCaisse) {
        InfoFilialeCaisse persistedIfc = cashService.persit(infoFilialeCaisse);
        return new ResponseEntity<>(persistedIfc, HttpStatus.CREATED);
    }

    @GetMapping("/ifc/{filiale-id}/{filter-type}/{type}")
    public ResponseEntity<Object> getIfcByFilialeAndType(@PathVariable("filter-type") String filterType,
                                                         @PathVariable("type") String type,
                                                         @PathVariable("filiale-id") Long filialeId) {
        return new ResponseEntity<>(cashService.findAllByTypePayement(filialeId, type, filterType, LocalDate.now(Clock.systemDefaultZone())), HttpStatus.OK);
    }

    @GetMapping("/ifc/{filiale-id}/user/{user-id}")
    public ResponseEntity<Object> getIfcByUserId(@PathVariable("user-id") Long userId,
                                                 @PathVariable("filiale-id") Long filialeId) {
        return new ResponseEntity<>(cashService.findAllByUserIdAndDate(filialeId, userId, LocalDate.now(Clock.systemDefaultZone())), HttpStatus.OK);
    }

    @GetMapping("/ifc/{filiale-id}/magasin/{magasin-id}")
    public ResponseEntity<Object> getCashInfoByStoreId(@PathVariable("magasin-id") Long storeId,
                                                       @PathVariable("filiale-id") Long filialeId) {
        return new ResponseEntity<>(cashService.getCashInfoByStoreId(filialeId, storeId), HttpStatus.OK);
    }

    @GetMapping("/ifc/{filiale-id}/date/{begin}/{end}")
    public ResponseEntity<Object> getCashInfoBetweenDate(@PathVariable("filiale-id") Long filialeId,
                                                         @PathVariable("begin") String begin,
                                                         @PathVariable("end") String end) {
        return new ResponseEntity<>(cashService.getCashInfoBetweenDate(filialeId, begin, end), HttpStatus.OK);
    }

    @Autowired
    private RepoUtils repoUtils;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CashRepository cashRepository;

    @PutMapping("/ifc/{vente-id}")
    public ResponseEntity<Object> update(@RequestBody IfcWrapper ifcWrapper, @PathVariable("vente-id") Long venteId) {

        venteRepository.findById(venteId).ifPresent(vente -> {

//            Filiale filiale = vente.getFiliale();
//            Double nouveauMontant = ifcWrapper.getMontant();
//
//            vente.setIsPayementModeChanged(true);
//
//            venteRepository.save(vente);
//            /*  NOUVEAU IFC */
//            InfoFilialeCaisse nouveauIfc = new InfoFilialeCaisse();
//            // Mode de paiement
//            ModePayement modePayement = ModePayement.string2TypePayement(ifcWrapper.getModePayement());
//
//            if (modePayement.equals(ModePayement.CREDIT)) {
//                cashRepository.findByFilialeIdAndPayementMode(filiale.getId(), modePayement).ifPresent(caisse -> {
//                    Double value = caisse.getValue();
//                    caisse.setValue(value - nouveauMontant);
//                    cashRepository.save(caisse);
//                });
//            }
//
//            nouveauIfc.setModePayement(modePayement);
//            // Montant operation
//            nouveauIfc.setMontantOperation(nouveauMontant);
//            // date operation
//            nouveauIfc.setDate(LocalDate.now(Clock.systemDefaultZone()));
//            // type operation
//            TypeOperationCaisse typeOperationCaisse = modePayement.equals(ModePayement.CREDIT) ? TypeOperationCaisse.DECAISSEMENT : TypeOperationCaisse.ENCAISSEMENT;
//            nouveauIfc.setOperationCaisse(typeOperationCaisse);
//            // reference
//            nouveauIfc.setReference(repoUtils.generateRef(typeOperationCaisse.name().toLowerCase()));
//            // Description
//            nouveauIfc.setDescription(ifcWrapper.getDescription());
//            // filiale
//            nouveauIfc.setFiliale(filiale);
//            // magasin
//            nouveauIfc.setMagasin(vente.getInfoFilialeCaisse().getMagasin());
//            // utilisateur
//            userRepository.findById(ifcWrapper.getUserId()).ifPresent(nouveauIfc::setUser);
//
//            ifcRepository.save(nouveauIfc);

        });

        return new ResponseEntity<>("", HttpStatus.OK);
    }
}


