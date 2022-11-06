package mg.imwa.tenant.model.tenantEntityBeans;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
public class EmbarquementArticle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "voyage_id", foreignKey = @ForeignKey(name = "ea_voyage_key_constraint"))
    private Voyage voyage;

    @ManyToOne
    @JoinColumn(name = "article_id", foreignKey = @ForeignKey(name = "ea_article_key_constraint"))
    private Article article;

    @ManyToOne
    @JoinTable(name = "voyage_article_fournisseur"
            , joinColumns = @JoinColumn(name = "va_id", foreignKey = @ForeignKey(name = "vaf_va_key_constraint")),
            inverseJoinColumns = @JoinColumn(name = "fournisseur_id", foreignKey = @ForeignKey(name = "af_fournisseur_key_constraint")))
    private ClientFournisseur clientFournisseur;

    @ManyToOne
    @JoinTable(name = "voyage_article_magasin"
            , joinColumns = @JoinColumn(name = "va_id", foreignKey = @ForeignKey(name = "vam_va_key_constraint")),
            inverseJoinColumns = @JoinColumn(name = "magasin_id", foreignKey = @ForeignKey(name = "vam_magasin_key_constraint")))
    private Magasin magasin;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "voyage_responsable_key_constraint"))
    private User user;

    private Double quantite;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}
