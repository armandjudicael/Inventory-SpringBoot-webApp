package mg.imwa.tenant.model.entityEmbededId;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class TransfertArticleId implements Serializable {
    private Long transfertId;
    private Long articleId;
}
