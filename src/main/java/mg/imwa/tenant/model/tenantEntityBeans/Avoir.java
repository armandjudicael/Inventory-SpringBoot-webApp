package mg.imwa.tenant.model.tenantEntityBeans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class Avoir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "vente_id", foreignKey = @ForeignKey(name = "avoir_vente_key_constraint"))
    private Vente vente;

    private Double montant;

    @Column(columnDefinition = "TEXT")
    private String refAvoir;

    private LocalDate date;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "avoir_info_article_magasin")
    private List<InfoArticleMagasin> infoArticleMagasin;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "info_id")
    private InfoFilialeCaisse infoFilialeCaisse;
}
