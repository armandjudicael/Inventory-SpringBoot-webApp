package mg.imwa.tenant.model.tenantEntityBeans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity(name = "fonctionnalite")
@Data
@NoArgsConstructor
@Table(name = "fonctionnalite")
@NamedQueries({
        @NamedQuery(name = "fonctionnalite.all", query = "from fonctionnalite")
})
@AllArgsConstructor
public class Fonctionnalite {
    public Fonctionnalite(String nom) {
        this.nom = nom;
    }

    public Fonctionnalite(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String nom;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fonctionnalite that = (Fonctionnalite) o;
        return nom.equals(that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}
