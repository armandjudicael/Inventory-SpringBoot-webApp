package mg.imwa.tenant.controller.restController;

import mg.imwa.tenant.model.tenantEntityBeans.Voyage;
import mg.imwa.tenant.repository.IavRepository;
import mg.imwa.tenant.repository.MaterielTransportRepository;
import mg.imwa.tenant.repository.TrajetRepository;
import mg.imwa.tenant.repository.VoyageRepository;
import mg.imwa.tenant.service.RepoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class VoyageRessource {

    @Autowired
    private VoyageRepository voyageRepository;

    @Autowired
    private RepoUtils repoUtils;

    @PostMapping(value = "/voyages")
    public ResponseEntity<Object> create(@RequestBody Voyage newVoyage) {

        newVoyage.setReference(repoUtils.generateRef("VOYAGE"));

        Voyage voyage = voyageRepository.save(newVoyage);
        // MATERIEL DE TRANSPORT
        Long newMatId = newVoyage.getMaterielTransport().getId();
        mtRepository.findById(newMatId).ifPresent(voyage::setMaterielTransport);

        // TRAJET
        Long trajetId = newVoyage.getTrajet().getId();
        trajetRepository.findById(trajetId).ifPresent(voyage::setTrajet);

        // STATUS
        voyage.setStatutVoyage(newVoyage.getStatutVoyage());
        return new ResponseEntity<>(voyageRepository.save(voyage), HttpStatus.CREATED);
    }

    ;

    @GetMapping("/voyages/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        Optional<Voyage> voyageOptional = voyageRepository.findById(id);
        return voyageOptional.<ResponseEntity<Object>>map(voyage -> new ResponseEntity<>(voyage, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>("The travel with the id = " + id + " doesn't exist !", HttpStatus.NOT_FOUND));
    }

    @Autowired
    private IavRepository iavRepository;

    @GetMapping("/voyages/{id}/iavs/{value}")
    public ResponseEntity<Object> getAllIav(@PathVariable("id") Long id, @PathVariable("value") String value) {
        return new ResponseEntity<>(iavRepository.findAll(id, value), HttpStatus.OK);
    }

    @GetMapping("/voyages/reference/{reference}")
    public ResponseEntity<Object> findByReference(@PathVariable("reference") String ref) {
        return new ResponseEntity<>(voyageRepository.findByReference(ref), HttpStatus.CREATED);
    }

    @GetMapping("/voyages")
    public ResponseEntity<Object> findAll() {
        LocalDate now = LocalDate.now(Clock.systemDefaultZone());
        return new ResponseEntity<>(voyageRepository.findByAllByDateCreation(now, now), HttpStatus.OK);
    }

    @GetMapping("/voyages/date-creation/{begin}/{end}")
    public ResponseEntity<Object> findAllByDateCreation(@PathVariable("begin") String begin, @PathVariable("end") String end) {
        return new ResponseEntity<>(voyageRepository.findByAllByDateCreation(LocalDate.parse(begin), LocalDate.parse(end)), HttpStatus.OK);
    }

    @GetMapping("/voyages/date-depart/{begin}/{end}")
    public ResponseEntity<Object> findAllByDateDepart(@PathVariable("begin") String begin, @PathVariable("end") String end) {
        return new ResponseEntity<>(voyageRepository.findByAllByDateDepart(LocalDate.parse(begin), LocalDate.parse(end)), HttpStatus.OK);
    }

    @GetMapping("/voyages/date-arrive/{begin}/{end}")
    public ResponseEntity<Object> findAllByDateArrive(@PathVariable("begin") String begin, @PathVariable("end") String end) {
        return new ResponseEntity<>(voyageRepository.findByAllByDateArrive(LocalDate.parse(begin), LocalDate.parse(end)), HttpStatus.OK);
    }


    @DeleteMapping("/voyages/{id}")
    public ResponseEntity<Object> detele(@PathVariable("id") Long id) {
        Optional<Voyage> optionalVoyage = voyageRepository.findById(id);
        if (optionalVoyage.isPresent()) {
            voyageRepository.deleteById(id);
            return new ResponseEntity<>("The travel with the id = " + id + " is deleted with success ", HttpStatus.OK);
        }
        return new ResponseEntity<>("The travel with the id = " + id + " doesn't exist ", HttpStatus.NOT_FOUND);
    }

    @Autowired
    private MaterielTransportRepository mtRepository;

    @Autowired
    private TrajetRepository trajetRepository;

    @PutMapping("/voyages/{id}")
    public ResponseEntity<Object> update(@RequestBody Voyage newVoyage, @PathVariable("id") Long id) {
        Optional<Voyage> optionalVoyage = voyageRepository.findById(id);
        if (optionalVoyage.isPresent()) {

            Voyage voyage = optionalVoyage.get();

            // MATERIEL DE TRANSPORT
            Long newMatId = newVoyage.getMaterielTransport().getId();
            mtRepository.findById(newMatId).ifPresent(voyage::setMaterielTransport);

            // TRAJET
            Long trajetId = newVoyage.getTrajet().getId();
            trajetRepository.findById(trajetId).ifPresent(voyage::setTrajet);

            // STATUS
            voyage.setStatutVoyage(newVoyage.getStatutVoyage());

            return new ResponseEntity<>(voyageRepository.save(voyage), HttpStatus.OK);
        }
        return new ResponseEntity<>("The travel with the id = " + id + " doesn't exist ", HttpStatus.NOT_FOUND);
    }

}
