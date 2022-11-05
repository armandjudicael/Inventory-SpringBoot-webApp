package mg.imwa.tenant.model.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import mg.imwa.tenant.model.tenantEntityBeans.InfoFilialeCaisse;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class CashWrapper {
    private List<InfoFilialeCaisse> infoCaisseList;
    private Map<String, Double> dashboardInfoMap;

    public CashWrapper(List<InfoFilialeCaisse> infoCaisseList) {
        this.infoCaisseList = infoCaisseList;
    }
}
