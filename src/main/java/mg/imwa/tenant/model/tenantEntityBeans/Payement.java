package mg.imwa.tenant.model.tenantEntityBeans;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import mg.imwa.tenant.model.entityEnum.ModePayement;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Payement{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private String reference;

    @PostLoad
    public void initReference() {
        this.reference = "PM-"+id;
    }

    @Enumerated(EnumType.ORDINAL)
    private ModePayement modePayement;

    private Double montantPayement;

    private LocalDate datePayement;

    private Double montantRestant;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ifc_id")
    private InfoFilialeCaisse ifc;

    @JsonBackReference(value = "vente_payment_ref")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "payement_vente")
    private Vente vente;

    @JsonBackReference(value = "trosa_payment_ref")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "payement_trosa")
    private Trosa trosa;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean isValid;

    @Override public String toString() {
        return "Payement{" +
                "id=" + id +
                ", modePayement=" + modePayement +
                ", montantPayement=" + montantPayement +
                ", datePayement=" + datePayement +
                ", user=" + user +
                '}';
    }
}
