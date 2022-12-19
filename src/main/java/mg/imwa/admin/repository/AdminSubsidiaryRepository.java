package mg.imwa.admin.repository;

import mg.imwa.admin.model.Filiale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(transactionManager = "adminTransactionManager")
public interface AdminSubsidiaryRepository extends JpaRepository<Filiale,Long>{

}
