package mg.imwa.admin.repository;

import mg.imwa.admin.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
@Transactional(transactionManager = "adminTransactionManager")
public interface CompanyRepository extends JpaRepository<Company,Long>{

    @Query(value = "from Company c where c.nom=:name")
    Optional<Company> findByName(@Param("name")String name);

    @Query(value = "SELECT count(datname) FROM pg_database WHERE datistemplate = false and  datname =:name",nativeQuery = true)
    Long databaseDontExist(@Param("name") String databaseName);


}
