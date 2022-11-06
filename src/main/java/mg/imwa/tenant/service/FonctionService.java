package mg.imwa.tenant.service;


import mg.imwa.tenant.model.tenantEntityBeans.Fonction;
import mg.imwa.tenant.model.tenantEntityBeans.Fonctionnalite;
import mg.imwa.tenant.repository.FeatureRepository;
import mg.imwa.tenant.repository.FonctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FonctionService{

    @Autowired
    private FonctionRepository fonctionRepository;

    @Autowired
    private FeatureRepository featureRepository;

    public Fonction save(Fonction fonction) {
        fonction.setAutorisationMap(initAutorisation(fonction.getNomFonction()));
        return fonctionRepository.save(fonction);
    }

    private Map<Fonctionnalite, Long> initAutorisation(String autorisationName) {
        List<Fonctionnalite> all = featureRepository.findAll();
        Map<Fonctionnalite, Long> map = new HashMap<>();
        all.forEach(fonctionnalite -> map.put(fonctionnalite, 1L));
        return map;
    }
}
