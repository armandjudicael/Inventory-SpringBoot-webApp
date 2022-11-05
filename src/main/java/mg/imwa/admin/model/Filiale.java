package mg.imwa.admin.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Filiale{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String nom;
   private String addresse;
   private String numTel;
   private String email;

   @OneToMany(fetch = FetchType.LAZY)
   private List<TenantUser> tenantUsers;
}
