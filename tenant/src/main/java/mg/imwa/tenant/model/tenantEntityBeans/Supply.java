package mg.imwa.tenant.model.tenantEntityBeans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity(name = "approv")
@Table(name = "approv")
@Data
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "approv.all", query = "from approv")
})
public class Supply implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "approvisionement_fournisseur")
    private ClientFournisseur fournisseur;

    @OneToOne(cascade = CascadeType.PERSIST)
    private InfoArticleMagasin infoArticleMagasin;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double montantApprov;

    private LocalDate datePeremption;

    private Double quantitePeremption;
}
