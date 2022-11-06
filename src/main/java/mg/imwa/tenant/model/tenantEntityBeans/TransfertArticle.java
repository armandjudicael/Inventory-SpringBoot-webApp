package mg.imwa.tenant.model.tenantEntityBeans;

import lombok.Data;
import lombok.NoArgsConstructor;
import mg.imwa.tenant.model.entityEmbededId.TransfertArticleId;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "transfertArticle")
@Data
@NoArgsConstructor
public class TransfertArticle {

    @EmbeddedId
    private TransfertArticleId transfertArticleId;

    @ManyToOne
    @JoinColumn(name = "transfert_id", foreignKey = @ForeignKey(name = "ta_transfert_key_constraint"))
    @MapsId("transfertId")
    private Transfert transfert;

    @ManyToOne
    @JoinColumn(name = "article_id", foreignKey = @ForeignKey(name = "ta_article_key_constraint"))
    @MapsId("articleId")
    private Article article;

    private Double quantite;

    private Date date;
}
