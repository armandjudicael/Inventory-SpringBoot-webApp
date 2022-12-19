package mg.imwa.admin.model;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
public class TenantUser extends Admin{
    private String key;
    @Enumerated(value = EnumType.ORDINAL)
    private UserType userType;

    @Override
    public String toString() {
        return "TenantUser{" +
                "key='" + key + '\'' +
                ", userType=" + userType +super.toString()+
                '}';
    }
}
