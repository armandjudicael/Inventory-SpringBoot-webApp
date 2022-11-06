package mg.imwa.tenant.model.entityEmbededId;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ApprovisionnementId implements Serializable {
    private Long fournisseurId;
    private Long articleId;
    private Long magasinId;
}
