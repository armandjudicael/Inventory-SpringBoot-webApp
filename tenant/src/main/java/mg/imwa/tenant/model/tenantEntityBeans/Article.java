package mg.imwa.tenant.model.tenantEntityBeans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "article")
@Data
@NoArgsConstructor
@NamedQueries(value = {
        @NamedQuery(name = "article.all", query = "from article")
})
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String designation;

    @ManyToOne(targetEntity = Categorie.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "categorieId", foreignKey = @ForeignKey(name = "article_categorie_key_constraint"), nullable = false)
    private Categorie categorie;

    @Lob
    private byte[] image;

    private Boolean isPerishable;

    @Column(length = 15)
    private String status;
}
