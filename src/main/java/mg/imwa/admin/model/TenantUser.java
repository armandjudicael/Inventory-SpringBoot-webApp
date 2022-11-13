package mg.imwa.admin.model;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TenantUser extends Admin {
    private String key;
    @Enumerated(value = EnumType.ORDINAL)
    private UserType userType;
}
