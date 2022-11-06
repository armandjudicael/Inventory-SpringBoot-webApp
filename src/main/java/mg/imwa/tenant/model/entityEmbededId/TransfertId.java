package mg.imwa.tenant.model.entityEmbededId;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class TransfertId implements Serializable {
    private Long magasinId;
    private Long articleId;
}
