package mg.imwa.tenant.model.entityEmbededId;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class InventoryAlertId implements Serializable {
    private Long filialeId;
    private Long articleId;
}
