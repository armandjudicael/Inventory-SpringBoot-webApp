package mg.imwa.tenant.model.wrapper;
import lombok.Data;

@Data
public class IfcWrapper {
    private String modePayement;
    private String description;
    private Double montant;
    private Long userId;
}
