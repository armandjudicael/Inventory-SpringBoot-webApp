package mg.imwa.tenant.model.tenantEntityBeans;

import lombok.*;
import mg.imwa.tenant.model.entityEnum.TypeMateriel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaterielTransport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public String toString() {
        return "MaterielTransport{" +
                "id=" + id +
                ", typeMateriel=" + typeMateriel +
                ", reference='" + reference + '\'' +
                '}';
    }

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "materiel_responsable")
    private PersonnePhysique responsable;

    @Enumerated(EnumType.STRING)
    private TypeMateriel typeMateriel;

    @Column(columnDefinition = "TEXT")
    private String reference;

    private Boolean isLocation;
}
