package mg.imwa.tenant.model.tenantEntityBeans;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import mg.imwa.tenant.model.entityEnum.ModePayement;
import mg.imwa.tenant.model.entityEnum.TypeTrosa;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "trosa")
@Table(name = "trosa")
@Data
public class Trosa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double montant;

    private Double reste;

    @Enumerated(EnumType.ORDINAL)
    private TypeTrosa typeTrosa;

    @ManyToOne
    @JoinColumn(name = "cf_id")
    private ClientFournisseur clientFournisseur;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private ModePayement modePayement;

    private LocalDate dateEcheance;

    private LocalDate date;

    @Column(columnDefinition = "TEXT")
    private String reference;

    @JsonManagedReference(value = "trosa_payment_ref")
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Payement> payements;

    @Override
    public String toString() {
        return "Trosa{" +
                "id=" + id +
                ", montant=" + montant +
                ", reste=" + reste +
                ", typeTrosa=" + typeTrosa +
                ", description='" + description + '\'' +
                ", modePayement=" + modePayement +
                ", dateEcheance=" + dateEcheance +
                ", date=" + date +
                ", reference='" + reference + '\'' +
                '}';
    }
}
