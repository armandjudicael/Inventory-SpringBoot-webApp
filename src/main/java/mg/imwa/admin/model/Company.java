package mg.imwa.admin.model;
import lombok.Data;
import mg.imwa.tenant.model.tenantEntityBeans.Personne;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Company extends Personne{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    @Column(columnDefinition = "TEXT")
    private String verset;

    @Column(columnDefinition = "TEXT")
    private String slogan;

    @Enumerated(EnumType.ORDINAL)
    private SocieteStatus societeStatus = SocieteStatus.ENABLED;

    @OneToOne(cascade = CascadeType.PERSIST)
    private CompanyDataSourceConfig companyDataSourceConfig;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Filiale> filiales;

    private String validationKey;

    @OneToOne(cascade = CascadeType.PERSIST)
    private TenantUser admin;

    private boolean isValidated = true;

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", verset='" + verset + '\'' +
                ", slogan='" + slogan + '\'' +
                ", societeStatus=" + societeStatus +
                ", companyDataSourceConfig=" + companyDataSourceConfig +
                ", validationKey='" + validationKey + '\'' +
                ", isValidated=" + isValidated +
                '}';
    }

}