package mg.imwa.tenant.model.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import mg.imwa.tenant.model.tenantEntityBeans.PrixArticleFiliale;
import mg.imwa.tenant.model.tenantEntityBeans.Supply;

import java.util.List;

@Data
@AllArgsConstructor
public class SupplyWrapper {
    private List<Supply> supplies;
    private List<PrixArticleFiliale> prixArticleFiliales;
}
