package mg.imwa.tenant.model.tenantEntityBeans;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "vente")
@Table(name = "vente")
@Data
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "vente.all", query = "from vente")
})
public class Vente implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "vente_info_article_magasin")
    private List<InfoArticleMagasin> infoArticleMagasin;

    @ManyToOne
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "FK_CLIENT_ID"))
    private ClientFournisseur client;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "filiale_id", nullable = false)
    private Filiale filiale;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference(value = "vente_payment_ref")
    @OneToMany(mappedBy = "vente",cascade = CascadeType.PERSIST)
    private List<Payement> payements;

    private LocalDate date;

    private Double montantAvecRemise;

    @Column
    private Double montantVente;

    @Column
    private String refVente;

    @JsonManagedReference
    @OneToOne(mappedBy = "vente")
    private Avoir avoir;

    private Boolean isPayementModeChanged = false;
    private Boolean isConcernedByInvoiceRegulation = false;
}
