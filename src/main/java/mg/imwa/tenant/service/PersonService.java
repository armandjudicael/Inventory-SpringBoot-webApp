package mg.imwa.tenant.service;

import mg.imwa.tenant.model.entityEnum.DefaultPage;
import mg.imwa.tenant.model.tenantEntityBeans.Fonction;
import mg.imwa.tenant.model.tenantEntityBeans.PersonnePhysique;
import mg.imwa.tenant.repository.FonctionRepository;
import mg.imwa.tenant.repository.PersonnePhysiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonnePhysiqueRepository phRepository;

    @Autowired
    private FonctionRepository fonctionRepository;

    public PersonnePhysique persist(PersonnePhysique newPerson, String fonctionName){

        fonctionRepository.findByName(fonctionName).ifPresentOrElse(newPerson::setFonction,()->{
            // ENREGISTREMENT DU NOUVEAU FONCTION
            Fonction newFonction = new Fonction();
            newFonction.setNomFonction(fonctionName);
            newFonction.setDefaultPage(DefaultPage.MENU_TRANSPORT);

            Fonction savedFunction = fonctionRepository.save(newFonction);
            newPerson.setFonction(savedFunction);

        });

       return phRepository.save(newPerson);
    }

}
