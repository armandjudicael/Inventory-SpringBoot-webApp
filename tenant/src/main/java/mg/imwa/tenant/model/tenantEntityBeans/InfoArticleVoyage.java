package mg.imwa.tenant.model.tenantEntityBeans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class InfoArticleVoyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    private ClientFournisseur fournisseur;

    @ManyToOne(cascade = CascadeType.MERGE)
    private MaterielTransport materielTransport;
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "voyage_id")
    private Voyage voyage;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "article_id", foreignKey = @ForeignKey(name = "FK_ARTICLE_ID", foreignKeyDefinition = "FOREIGN KEY (article_id) REFERENCES article(article_id) ON DELETE CASCADE ON UPDATE NO ACTION"))
    private Article article;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "unite_id", foreignKey = @ForeignKey(name = "FK_UNITE_ID", foreignKeyDefinition = "FOREIGN KEY (unite_id) REFERENCES unite(id) ON DELETE CASCADE ON UPDATE NO ACTION"))
    private Unite unite;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;
    private String reference;
    private String numFacture;
    @Column(name = "quantite_ajout")
    private Double quantite;

    @Column
    private Double prixVente;
    @Column
    private Double prixAchat;
    @Column
    private Double prixTransport;
    @Transient
    private Double marge;

    @PostLoad
    @Transient
    @JsonIgnore
    public void initMarge() {
        if (prixVente != null && prixAchat != null && prixTransport != null && poids != null)
            this.marge = prixVente - (prixAchat + (prixTransport * poids));
        else this.marge = 0D;
    }

    @Override
    public String toString() {
        return "InfoArticleVoyage{" +
                "id=" + id +
                ", fournisseur=" + fournisseur +
                ", article=" + article +
                ", unite=" + unite +
                ", user=" + user +
                ", reference='" + reference + '\'' +
                ", numFacture='" + numFacture + '\'' +
                ", quantite=" + quantite +
                ", prixVente=" + prixVente +
                ", prixAchat=" + prixAchat +
                ", poids=" + poids +
                ", description='" + description + '\'' +
                '}';
    }

    private Double poids;

    private LocalDate date;

    private LocalDate datePeremption;

    private Boolean isTransfered;
    @Column(columnDefinition = "TEXT")
    private String description;
}
