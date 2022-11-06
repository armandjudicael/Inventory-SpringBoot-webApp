package mg.imwa.tenant.model.tenantEntityBeans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.imwa.tenant.model.entityEnum.StatutVoyage;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "voyage")
@Table(name = "voyage")
@Data
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "voyage.all", query = "from voyage")
})
public class Voyage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "materiel_de_transport_id", foreignKey = @ForeignKey(name = "voyage_materiel_transport_key_contraint"))
    private MaterielTransport materielTransport;

    @Column(columnDefinition = "TEXT")
    private String reference;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "voyage")
    private List<InfoArticleVoyage> infoArticleVoyages;

    @Enumerated(EnumType.ORDINAL)
    @Column(length = 20)
    private StatutVoyage statutVoyage;
    @Transient
    private Double poidsTotal;
    @Transient
    private Double montantMarge;

    @PostLoad
    @Transient
    @JsonIgnore
    public void initPoidTotalAndMarge() {
        if (infoArticleVoyages != null && !infoArticleVoyages.isEmpty()) {
            this.poidsTotal = infoArticleVoyages.stream().mapToDouble(InfoArticleVoyage::getPoids).sum();
            this.montantMarge = infoArticleVoyages.stream().mapToDouble(InfoArticleVoyage::getMarge).sum();
        } else {
            this.poidsTotal = 0D;
            this.montantMarge = 0D;
        }
    }

    @JoinColumn(name = "trajet_id")
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Trajet trajet;

    private LocalDate dateDepart;

    private LocalDate dateArrive;

    private LocalDate dateCreation;

    private String description;

    @Override
    public String toString() {
        return "Voyage{" +
                "id=" + id +
                ", materielTransport=" + materielTransport +
                ", reference='" + reference + '\'' +
                ", statutVoyage=" + statutVoyage +
                ", trajet=" + trajet +
                ", dateDepart=" + dateDepart +
                ", dateArrive=" + dateArrive +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
