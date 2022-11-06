package mg.imwa.tenant.model.tenantEntityBeans;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class PersonneMorale extends Personne {
    @Column(columnDefinition = "TEXT")
    private String nif;
    @Column(columnDefinition = "TEXT")
    private String stat;
    @Column(columnDefinition = "TEXT")
    private String cif;
    @Column(columnDefinition = "TEXT")
    private String rcs;
}
