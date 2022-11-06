package mg.imwa.tenant.model.tenantEntityBeans;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Data
public class ArticleUnite{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "article_id", foreignKey = @ForeignKey(name = "FK_ARTICLE_ID", foreignKeyDefinition = "FOREIGN KEY (article_id) REFERENCES article(article_id) ON DELETE CASCADE ON UPDATE NO ACTION"))
    private Article article;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "unite_id", foreignKey = @ForeignKey(name = "FK_UNITE_ID", foreignKeyDefinition = "FOREIGN KEY (unite_id) REFERENCES unite(id) ON DELETE CASCADE ON UPDATE NO ACTION"))
    private Unite unite;

    private int niveau;
    private double quantiteNiveau;
    private double poids;
    private String barcode;
}
