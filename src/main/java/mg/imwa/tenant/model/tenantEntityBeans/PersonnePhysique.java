package mg.imwa.tenant.model.tenantEntityBeans;

import lombok.Data;
import lombok.NoArgsConstructor;
import mg.imwa.tenant.model.entityEnum.Sexe;
import mg.imwa.tenant.model.entityEnum.SituationMatrimoniale;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "personne_physique")
public class PersonnePhysique extends Personne implements Serializable {

    @Column(columnDefinition = "TEXT")
    private String cin;

    @Column(columnDefinition = "TEXT")
    private String lieuDelivrance;

    @Temporal(TemporalType.DATE)
    private Date dateDelivrance;

    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fonction_id", foreignKey = @ForeignKey(name = "user_fonction_key_constraint"))
    private Fonction fonction;

    @Enumerated(EnumType.STRING)
    private SituationMatrimoniale situationMatrimoniale;
}
