package mg.imwa.admin.model;
import lombok.Data;

import javax.persistence.*;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class TenantUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String key;
    @Enumerated(value = EnumType.ORDINAL)
    private UserType userType;
}
