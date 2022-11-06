package mg.imwa.tenant.service;
import mg.imwa.tenant.model.tenantEntityBeans.Payement;
import mg.imwa.tenant.repository.PayementRepository;
import mg.imwa.tenant.repository.VenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayementService {

    @Autowired
    private PayementRepository payementRepository;

    @Autowired
    private VenteRepository venteRepository;

    public Payement save(Payement payement){
        return payementRepository.save(payement);
    }

    public void updatePayement(Long id,int status){
        payementRepository.findById(id).ifPresent(pm -> {
            // ANNULATION DU PAYEMENT
            pm.setIsValid(status != 0);
            payementRepository.save(pm);
        });
    }

    public void delete(Long id){
        payementRepository.deleteById(id);
    }
}
