package mg.imwa.tenant.model.tenantEntityBeans;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Peremption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "magasin_id", foreignKey = @ForeignKey(name = "FK_MAGASIN_ID", foreignKeyDefinition = "FOREIGN KEY (magasin_id) REFERENCES magasin(id_magasin) ON DELETE CASCADE ON UPDATE NO ACTION"))
    private Magasin magasin;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "article_id", foreignKey = @ForeignKey(name = "FK_ARTICLE_ID", foreignKeyDefinition = "FOREIGN KEY (article_id) REFERENCES article(article_id) ON DELETE CASCADE ON UPDATE NO ACTION"))
    private Article article;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "unite_id", foreignKey = @ForeignKey(name = "FK_UNITE_ID", foreignKeyDefinition = "FOREIGN KEY (unite_id) REFERENCES unite(id) ON DELETE CASCADE ON UPDATE NO ACTION"))
    private Unite unite;

    private LocalDate datePeremption;

    private Double quantitePeremption;
}
