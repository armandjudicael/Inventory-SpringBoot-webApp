package mg.imwa.tenant.model.wrapper;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InventoryViewWrapper extends InventoryWrapper {
    private LocalDate datePeremption;
    private String prixAchat;
    private String article;
    private String categorie;
    private String unite;
    private String nomMagasin;

    @Override
    public String toString() {
        return super.toString();
    }
}
