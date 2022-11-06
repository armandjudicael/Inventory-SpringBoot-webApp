package mg.imwa.tenant.model.tenantEntityBeans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity(name = "_user")
@Data
@NoArgsConstructor
@Table(name = "_user")
@NamedQueries(value = {
        @NamedQuery(name = "_user.All", query = "from _user"),
        @NamedQuery(name = "_user.checkUsernameAndPassword", query = "from _user as u where u.username =?0 AND u.password = ?1")
})
public class User extends PersonnePhysique implements Serializable {

    @Column(columnDefinition = "TEXT")
    private String username;

    @Column(columnDefinition = "TEXT")
    private String password;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "user_magasin",
            joinColumns = {@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_UM_USER_ID", foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES personne_physique(id) ON DELETE NO ACTION"))},
            inverseJoinColumns = {@JoinColumn(name = "magasin_id", foreignKey = @ForeignKey(name = "FK_UM_MAGASIN_ID", foreignKeyDefinition = "FOREIGN KEY (magasin_id) REFERENCES magasin(id_magasin) ON DELETE CASCADE"))})
    private List<Magasin> magasin;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "filialeId", foreignKey = @ForeignKey(name = "user_filiale_key_constraint"))
    private Filiale filiale;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }

    private Boolean enabled;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
