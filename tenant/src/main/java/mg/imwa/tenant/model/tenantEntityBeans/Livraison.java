package mg.imwa.tenant.model.tenantEntityBeans;

import lombok.Data;
import lombok.NoArgsConstructor;
import mg.imwa.tenant.model.entityEnum.StatutVoyage;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Livraison implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "vente_id", foreignKey = @ForeignKey(name = "livraison_vente_key_constraint"))
    private Vente vente;

    @Column(columnDefinition = "TEXT")
    private String numeroBon;

    @Enumerated(EnumType.STRING)
    private StatutVoyage statutVoyage;

    @ManyToOne
    @JoinTable(name = "livraison_materiel_transport", joinColumns = {@JoinColumn(name = "livraison_id", foreignKey = @ForeignKey(name = "lv_mt_livraison_key_constraint"))},
            inverseJoinColumns = {@JoinColumn(name = "mat_trans_id", foreignKey = @ForeignKey(name = "lv_mt_material_key_constraint"))})
    private MaterielTransport materielTransport;
}
