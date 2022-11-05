package mg.imwa.admin.model;
import lombok.Data;
import javax.persistence.*;
import java.util.List;
import java.util.Set;
@Data
@Entity
public class Company{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    @Column(columnDefinition = "TEXT")
    private String nom;

    @Column(columnDefinition = "TEXT")
    private String verset;

    @Column(columnDefinition = "TEXT")
    private String slogan;

    @Enumerated(EnumType.ORDINAL)
    private SocieteStatus societeStatus = SocieteStatus.DISABLED;

    @OneToOne(cascade = CascadeType.PERSIST)
    private CompanyDataSourceConfig companyDataSourceConfig;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Filiale> filiales;

    private String validationKey;

    private boolean isValidated = false;

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", verset='" + verset + '\'' +
                ", slogan='" + slogan + '\'' +
                ", societeStatus=" + societeStatus +
                ", companyDataSourceConfig=" + companyDataSourceConfig +
                ", validationKey='" + validationKey + '\'' +
                ", isValidated=" + isValidated +
                '}';
    }
}