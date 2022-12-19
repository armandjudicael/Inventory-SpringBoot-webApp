package mg.imwa.tenant.model.tenantEntityBeans;

import lombok.Data;
import mg.imwa.tenant.model.entityEnum.ModePayement;

import javax.persistence.*;

@Entity
@Data
public class Caisse{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ModePayement modePayement;

    @ManyToOne
    @JoinColumn(name = "filiale_id")
    private Filiale filiale;

    private Double value;
}
