package mg.imwa.tenant.service;

import mg.imwa.tenant.model.tenantEntityBeans.InfoArticleVoyage;
import mg.imwa.tenant.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IavService {
    @Autowired
    private IavRepository iavRepository;
    @Autowired
    private ClientFournisseurRepository cfRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UniteRepository uniteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MaterielTransportRepository mtRepository;

    public InfoArticleVoyage persit(InfoArticleVoyage iav) {

        InfoArticleVoyage newIav = iavRepository.save(iav);

        // FOURNISSEUR ET USER
        Long frId = newIav.getFournisseur().getId();
        Long userId = iav.getUser().getId();
        userRepository.findById(userId).ifPresent(user -> {
            Long filialeId = user.getFiliale().getId();
            // USER
            newIav.setUser(user);

            // FOURNISSEUR
            cfRepository.getOne(frId, filialeId, 1).ifPresent(newIav::setFournisseur);
        });
        //  ARTICLE
        Long itemId = iav.getArticle().getId();
        itemRepository.findById(itemId).ifPresent(newIav::setArticle);

        //  UNITE
        Long uniteId = iav.getUnite().getId();
        uniteRepository.findById(uniteId).ifPresent(newIav::setUnite);

        // Materiel de transport
        Long mtId = newIav.getMaterielTransport().getId();
        mtRepository.findById(mtId).ifPresent(newIav::setMaterielTransport);

        return newIav;
    }

    public Boolean deleteById(Long id) {
        iavRepository.deleteIavById(id);
        return true;
    }

}
