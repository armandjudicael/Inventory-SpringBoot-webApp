package mg.imwa.tenant.model.tenantEntityBeans;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class PrixArticleFiliale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "filiale_id",
            foreignKey = @ForeignKey(name = "FK_FILIALE_ID", foreignKeyDefinition = "FOREIGN KEY (filiale_id) REFERENCES filiale(id) ON DELETE CASCADE ON UPDATE NO ACTION"))
    private Filiale filiale;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "unite_id",
            foreignKey = @ForeignKey(name = "FK_UNITE_ID",
                    foreignKeyDefinition = "FOREIGN KEY (unite_id) REFERENCES unite(id) ON DELETE CASCADE ON UPDATE NO ACTION"))
    private Unite unite;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "article_id",
            foreignKey = @ForeignKey(name = "FK_ARTICLE_ID",
                    foreignKeyDefinition = "FOREIGN KEY (article_id) REFERENCES article(article_id) ON DELETE CASCADE ON UPDATE NO ACTION"))
    private Article article;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "FK_USER_ID",
                    foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES _user(id) ON DELETE NO ACTION ON UPDATE NO ACTION"))
    private User user;

    private Double prixVente;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnregistrement;
}
