package mg.imwa.tenant.repository;

import jdk.jfr.Registered;
import mg.imwa.tenant.model.tenantEntityBeans.InfoArticleVoyage;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(transactionManager = "defaultTenantTxManager")
public interface IavRepository extends JpaRepository<InfoArticleVoyage, Long> {
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from info_article_voyage  iav where iav.id=:id", nativeQuery = true)
    void deleteIavById(@Param("id") Long id);

    @Query(value = "from InfoArticleVoyage  iav join iav.voyage v join iav.article a join iav.unite u join iav.fournisseur f " +
            " where v.id=:id and (iav.numFacture =:value or a.designation=:value or u.designation=:value or f.nom =:value ) ")
    List<InfoArticleVoyage> findAll(@Param("id") Long id, @Param("value") String value);
}
