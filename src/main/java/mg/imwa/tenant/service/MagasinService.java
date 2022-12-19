package mg.imwa.tenant.service;

import mg.imwa.tenant.model.tenantEntityBeans.InfoArticleMagasin;
import mg.imwa.tenant.model.tenantEntityBeans.Vente;
import mg.imwa.tenant.repository.ActivityRepository;
import mg.imwa.tenant.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MagasinService {
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private SalesRepository salesRepository;

    public List<InfoArticleMagasin> findAllBetweenDate(Long magasinId, String begin, String end) {
        return activityRepository.findAllBetweenDate(magasinId, parse(begin), parse(end));
    }

    public List<Vente> findAllSalesBetweenDate(Long magasinId, String begin, String end) {
        return salesRepository.getSalesByBetweenDate(magasinId, parse(begin), parse(end));
    }

    private LocalDate parse(String date) {
        String[] split = date.replace("'", "").split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        return LocalDate.of(year, month, day);
    }

}
