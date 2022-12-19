package mg.imwa.tenant.controller.restController;

import mg.imwa.tenant.model.tenantEntityBeans.InfoArticleMagasin;
import mg.imwa.tenant.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ActivityRessource {

    @Autowired
    private ActivityRepository activityRepository;

    @GetMapping(value = "/activities/store/{magasinId}/item/{articleId}/unity/{uniteId}/date/{begin}/{end}")
    public ResponseEntity<Object> getAllActivities(@PathVariable("magasinId") Long storeId,
                                                   @PathVariable("articleId") Long itemId,
                                                   @PathVariable("uniteId") Long uniteId,
                                                   @PathVariable("begin") String begin,
                                                   @PathVariable("end") String end) {
//        return  new ResponseEntity<>(activityRepository.findAllByDate(date),HttpStatus.OK);
        return null;
    }

    @GetMapping("/activities/{magasinId}/{articleId}/{uniteId}")
    public ResponseEntity<Object> getAllActivitiesByItemAndUnit(@PathVariable("magasinId") Long storeId,
                                                                @PathVariable("articleId") Long itemId,
                                                                @PathVariable("uniteId") Long uniteId) {
        List<InfoArticleMagasin> allByMagasin = activityRepository.findAllByStoreAndItemAndUnit(storeId, itemId, uniteId);
        return new ResponseEntity<>(allByMagasin, HttpStatus.OK);
    }
}
