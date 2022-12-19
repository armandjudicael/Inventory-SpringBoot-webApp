package mg.imwa.tenant.model.tenantEntityBeans;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "transfert")
@Table(name = "transfert")
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "transfert.all", query = "from transfert")
})
public class Transfert implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "magasin_source_id", foreignKey = @ForeignKey(name = "transfert_magasin_origine_key_constraint"))
    private Magasin magasinSource;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "magasin_dest_id", foreignKey = @ForeignKey(name = "transfert_magasin_receveur_key_constraint"))
    private Magasin magasinDest;

    @Column(columnDefinition = "TEXT")
    private String chauffeur;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "Transfert_info")
    private List<InfoArticleMagasin> infoArticleMagasinList;
}
