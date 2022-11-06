package mg.imwa.tenant.model.entityEmbededId;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class StockId implements Serializable {
    private Long articleId;
    private Long uniteId;
    private Long magasinId;
}
