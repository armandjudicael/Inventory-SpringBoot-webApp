package mg.imwa.tenant.repository;

import mg.imwa.tenant.model.tenantEntityBeans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional(transactionManager = "defaultTenantTxManager")
public interface UserRepository extends JpaRepository<User,Long>{

    @Query(value = "from _user as u where u.username =:name AND u.password = :pass ")
    Optional<User> checkUser(@Param("name") String username,@Param("pass") String password);

    @Query(value = "from _user u join u.filiale f where u.username =:name AND u.password = :pass AND trim(f.nom)=:subsidiaryName")
    Optional<User> checkUser(@Param("name") String username,@Param("pass") String password,@Param("subsidiaryName") String subsidiaryName);

    @Query(value = "from _user u join u.magasin m where m.id=:idMagasin")
    List<User> getAllUserByMagasinId(@Param("idMagasin") Long id);

    @Query(value = "from _user u join u.fonction f where f.id =:id")
    List<User> getUserByFontionId(@Param("id") Long id);

    @Query(value = "from _user u join u.filiale f where f.id=:filialeId and   trim(lower(u.nom)) like concat('%',trim(lower(:name)),'%') ")
    List<User> findByName(@Param("name") String name,@Param("filialeId") Long filialeId);

    @Query(value = "from _user u join u.filiale f where f.id=:filialeId")
    List<User> findAllBySubsidiary(@Param("filialeId") Long filialeId);

    @Query(value = "from _user u join u.filiale f where f.id =:id")
    List<User> getAllByFiliale(@Param("id")Long id);
}
