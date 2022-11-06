package mg.imwa.tenant.model.tenantEntityBeans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Getter
public class ClientFournisseur extends Personne {

    private int type;

    @JsonIgnore
    @OneToMany(mappedBy = "clientFournisseur", fetch = FetchType.EAGER)
    private List<Trosa> Trosas;

    @Transient
    private Double totalMontantTrosa;

    @PostLoad
    @Transient
    @JsonIgnore
    public void seTotalMontantTrosa() {
        setTotalMontantTrosa(getTotalTrosa());
    }

    @Transient
    @JsonIgnore
    public Double getTotalTrosa() {
        double sum = this.getTrosas().stream().mapToDouble(Trosa::getReste).sum();
        BigDecimal bigDecimal = BigDecimal.valueOf(sum);
        return bigDecimal.doubleValue();
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "filiale_id", nullable = false)
    private Filiale filiale;

    @Override
    public String toString() {
        return "ClientFournisseur{" +
                "type=" + type +
                ", Trosas=" + Trosas +
                ", totalMontantTrosa=" + totalMontantTrosa +
                ", filiale=" + filiale +
                '}';
    }


}
