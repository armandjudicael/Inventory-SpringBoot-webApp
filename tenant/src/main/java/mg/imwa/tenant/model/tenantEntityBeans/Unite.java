package mg.imwa.tenant.model.tenantEntityBeans;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "unite")
@Data
@NoArgsConstructor
@Table(name = "unite")
@NamedQueries({
        @NamedQuery(name = "unite.all", query = "from unite")
})
public class Unite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String code;

    @Column(columnDefinition = "TEXT")
    private String designation;
}
