package mg.imwa.tenant.service;


import mg.imwa.tenant.model.tenantEntityBeans.InfoArticleMagasin;
import mg.imwa.tenant.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    public List<InfoArticleMagasin> findAllByFilialeIdBetweenDate(Long filialeId) {
        LocalDate now = LocalDate.now(Clock.systemDefaultZone());
        return activityRepository.findAllByFilialeIdBetweenDate(filialeId, now, now);
    }

    public List<InfoArticleMagasin> findAllByFilialeIdBetweenDate(Long filialeId, LocalDate begin, LocalDate end) {
        return activityRepository.findAllByFilialeIdBetweenDate(filialeId, begin, end);
    }
}
