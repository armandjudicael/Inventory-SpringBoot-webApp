package mg.imwa.tenant.model.tenantEntityBeans;
import lombok.Data;
import mg.imwa.tenant.model.entityEmbededId.StockId;

import javax.persistence.*;

@Entity
@Data
public class Stock {

    @EmbeddedId
    private StockId stockId;

    @ManyToOne
    @JoinColumn(name = "magasin_id",
            foreignKey = @ForeignKey(name = "FK_STOCK_MAGASIN_ID",
                    foreignKeyDefinition = "FOREIGN KEY (magasin_id) REFERENCES magasin(id_magasin) on delete cascade on update no action "))
    @MapsId(value = "magasinId")
    private Magasin magasin;

    @ManyToOne
    @JoinColumn(name = "unite_id", foreignKey = @ForeignKey(name = "FK_STOCK_UNITE_ID",
            foreignKeyDefinition = "FOREIGN KEY (unite_id) REFERENCES unite(id) ON DELETE CASCADE ON UPDATE NO ACTION"))
    @MapsId(value = "uniteId")
    private Unite unite;

    @ManyToOne
    @JoinColumn(name = "article_id", foreignKey = @ForeignKey(name = "FK_STOCK_ARTICLE_ID",
            foreignKeyDefinition = "FOREIGN KEY (article_id) REFERENCES article(article_id) ON DELETE CASCADE ON UPDATE NO ACTION"))
    @MapsId(value = "articleId")
    private Article article;

    private Double count;
}
