package mg.imwa.tenant.model.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InventoryWrapper {
    private Long articleId;
    private Long uniteId;
    private Long magasinId;
    private Double quantite;

    @Override
    public String toString() {
        return "InventoryWrapper{" +
                "articleId=" + articleId +
                ", uniteId=" + uniteId +
                ", magasinId=" + magasinId +
                ", quantite=" + quantite +
                '}';
    }
}
