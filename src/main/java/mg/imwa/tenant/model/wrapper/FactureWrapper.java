package mg.imwa.tenant.model.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FactureWrapper {
    private String client;
    private String operateur;
    private String montantTotal;
    private String reference;
    private String date;
}
