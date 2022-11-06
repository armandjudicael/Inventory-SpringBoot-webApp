package mg.imwa.tenant.model.tenantEntityBeans;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Trajet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String depart;
    private String destination;
    private Double distance;
}
