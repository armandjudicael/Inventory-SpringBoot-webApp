package mg.imwa.tenant.repository;

import mg.imwa.tenant.model.tenantEntityBeans.ClientFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional(transactionManager = "defaultTenantTxManager")
public interface ClientFournisseurRepository extends JpaRepository<ClientFournisseur,Long> {
    @Query(value = "from ClientFournisseur cf join cf.filiale f where f.id=:filialeId and cf.id=:id and cf.type =:typeCf")
    Optional<ClientFournisseur> getOne(@Param("id") Long id, @Param("filialeId") Long fId,@Param("typeCf") Integer typeCf);

    @Query("from  ClientFournisseur cf join cf.filiale f where cf.type =:typeCf and f.id=:filialeId ")
    List<ClientFournisseur> getAllExternalEntities(@Param("typeCf") Integer typeCf,@Param("filialeId") Long filialeId);

    @Query("from  ClientFournisseur cf join cf.Trosas tr join cf.filiale f where cf.type =:typeCf and f.id=:filialeId")
    List<ClientFournisseur> getAllExternalEntitiesWithCredit(@Param("typeCf") Integer typeCf,@Param("filialeId") Long filialeId);

    @Query("from  ClientFournisseur cf join cf.filiale f where cf.type =0 and f.id=:filialeId ")
    List<ClientFournisseur> getAllClient(@Param("filialeId") Long filialeId );

    @Query("from  ClientFournisseur cf join cf.filiale f where cf.type =1 and f.id=:filialeId")
    List<ClientFournisseur> getAllCustomer(@Param("filialeId") Long filialeId );

    @Query("from  ClientFournisseur cf join cf.filiale f where cf.type =:type and f.id=:filialeId and lower(trim(cf.nom)) like concat('%',trim(lower(:name)),'%')")
    List<ClientFournisseur> getAllCustomerByName(@Param("type") Integer type,@Param("filialeId") Long filialeId, @Param("name") String name);
}
