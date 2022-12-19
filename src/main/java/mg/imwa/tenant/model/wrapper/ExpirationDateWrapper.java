package mg.imwa.tenant.model.wrapper;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpirationDateWrapper {
    private LocalDate newDate;
    private LocalDate oldDate;
}
