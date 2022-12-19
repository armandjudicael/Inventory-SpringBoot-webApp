package mg.imwa.tenant.repository;

import mg.imwa.tenant.model.entityEnum.ModePayement;
import mg.imwa.tenant.model.entityEnum.TypeOperationCaisse;
import mg.imwa.tenant.model.tenantEntityBeans.InfoFilialeCaisse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(transactionManager = "defaultTenantTxManager")
public interface IfcRepository extends JpaRepository<InfoFilialeCaisse, Long> {
    @Query(value = "select sum(ifc.montantOperation) from InfoFilialeCaisse ifc join ifc.filiale f where ifc.modePayement=:payement and ifc.date=:date and f.id=:filialeId and ifc.operationCaisse<> 'DECAISSEMENT' and ifc.operationCaisse<> 'AVOIR'")
    Optional<Double> findByTypePayement(@Param("filialeId") Long filialeId, @Param("payement") ModePayement payement, @Param("date") LocalDate now);

    @Query(value = "select sum(ifc.montantOperation) from InfoFilialeCaisse ifc join ifc.filiale f join ifc.magasin m " +
            "where m.id=:storeId and ifc.modePayement=:payement and ifc.date=:date and f.id=:filialeId and ifc.operationCaisse<> 'DECAISSEMENT' and ifc.operationCaisse<> 'AVOIR'")
    Optional<Double> findByTypePayementByStoreId(
            @Param("filialeId") Long filialeId
            , @Param("storeId") Long storeId
            , @Param("payement") ModePayement payement
            , @Param("date") LocalDate now);

    @Query(value = "select sum(ifc.montantOperation) from InfoFilialeCaisse ifc join ifc.filiale f where ifc.modePayement=:payement and ifc.date between :begin and :end and f.id=:filialeId and ifc.operationCaisse<> 'DECAISSEMENT' and ifc.operationCaisse<> 'AVOIR'")
    Optional<Double> findByTypePayementBetweenDate(
            @Param("filialeId") Long filialeId
            , @Param("payement") ModePayement payement
            , @Param("begin") LocalDate beginDate
            , @Param("end") LocalDate endDate);

    @Query(value = "select sum(ifc.montantOperation) from InfoFilialeCaisse ifc join ifc.filiale f join ifc.user u" +
            " where ifc.modePayement=:payement  and u.id =:userId and ifc.date between :begin and :end and f.id=:filialeId and ifc.operationCaisse<> 'DECAISSEMENT' and ifc.operationCaisse<> 'AVOIR'")
    Optional<Double> findByTypePayementBetweenDateAndUserId(
            @Param("filialeId") Long filialeId,
            @Param("userId") Long userId
            , @Param("payement") ModePayement payement
            , @Param("begin") LocalDate beginDate
            , @Param("end") LocalDate endDate);

    @Query(value = "select sum(ifc.montantOperation) from InfoFilialeCaisse ifc join ifc.filiale f where ifc.operationCaisse=:type and f.id=:filialeId and ifc.date=:date ")
    Optional<Double> findByOperationType(@Param("filialeId") Long filialeId, @Param("type") TypeOperationCaisse type, @Param("date") LocalDate now);

    @Query(value = "select sum(ifc.montantOperation) from InfoFilialeCaisse ifc join ifc.magasin m join ifc.filiale f " +
            "where ifc.operationCaisse=:type and f.id=:filialeId and ifc.date=:date and m.id=:storeId")
    Optional<Double> findByOperationTypeAndStoreId(@Param("filialeId") Long filialeId,
                                                   @Param("storeId") Long storeId,
                                                   @Param("type") TypeOperationCaisse type,
                                                   @Param("date") LocalDate now);

    @Query(value = "select sum(ifc.montantOperation) from InfoFilialeCaisse ifc join ifc.filiale f where ifc.operationCaisse=:type and f.id=:filialeId  and  ifc.date between :begin and :end ")
    Optional<Double> findByOperationTypeBetweenDate(@Param("filialeId") Long filialeId, @Param("type") TypeOperationCaisse type, @Param("begin") LocalDate begin, @Param("end") LocalDate end);


    @Query(value = "select sum(ifc.montantOperation) from InfoFilialeCaisse ifc join ifc.filiale f join ifc.user u " +
            " where ifc.operationCaisse=:type and u.id=:userId and f.id=:filialeId  and  ifc.date between :begin and :end ")
    Optional<Double> findByOperationTypeBetweenDateByUserId(
            @Param("filialeId") Long filialeId,
            @Param("userId") Long userId,
            @Param("type") TypeOperationCaisse type,
            @Param("begin") LocalDate begin,
            @Param("end") LocalDate end);


    @Query(value = "select sum(ifc.montantOperation) from InfoFilialeCaisse ifc where ifc.modePayement=:payement and ifc.date between :begin and :end ")
    Optional<Double> findByTypePayementBetweenDate(@Param("payement") ModePayement payement, @Param("begin") LocalDate begin, @Param("end") LocalDate end);


    @Query(value = "from InfoFilialeCaisse  ifc join ifc.filiale f where f.id=:filialeId order by ifc.date asc")
    List<InfoFilialeCaisse> findAll(@Param("filialeId") Long filialeId);

    @Query(value = "from InfoFilialeCaisse  ifc join ifc.filiale f where f.id=:filialeId and ifc.reference=:ref  order by ifc.date asc")
    List<InfoFilialeCaisse> findAllByIfcReference(@Param("filialeId") Long filialeId, @Param("ref") String reference);

    @Query(value = "from InfoFilialeCaisse  ifc join ifc.filiale f join ifc.magasin m" +
            " where m.id =:magasinId and f.id=:filialeId and ( ifc.date between :begin and :end ) order by ifc.date asc")
    List<InfoFilialeCaisse> findAllBetweenDate(@Param("filialeId") Long filialeId, @Param("magasinId") Long magasinId, @Param("begin") LocalDate begin, @Param("end") LocalDate end);

    @Query(value = "from InfoFilialeCaisse  ifc join ifc.filiale f" +
            " where f.id=:filialeId and ( ifc.date between :begin and :end ) order by ifc.date asc")
    List<InfoFilialeCaisse> findAllBetweenDate(@Param("filialeId") Long filialeId, @Param("begin") LocalDate begin, @Param("end") LocalDate end);


    @Query(value = "from InfoFilialeCaisse  ifc join ifc.filiale f where f.id=:filialeId and ifc.date=:begin  order by ifc.date asc")
    List<InfoFilialeCaisse> findAllByDate(@Param("filialeId") Long filialeId, @Param("begin") LocalDate begin);

    @Query(value = "from InfoFilialeCaisse  ifc join ifc.filiale f join ifc.user u " +
            "where f.id=:filialeId and u.id=:userId and ifc.date=:begin  order by ifc.date asc")
    List<InfoFilialeCaisse> findAllByUserIdAndDate(@Param("filialeId") Long filialeId,
                                                   @Param("userId") Long userId,
                                                   @Param("begin") LocalDate begin);

    @Query(value = "from InfoFilialeCaisse  ifc join ifc.filiale f join ifc.user u " +
            "where f.id=:filialeId and u.id=:userId and ( ifc.date between :begin and :end ) order by ifc.date asc")
    List<InfoFilialeCaisse> findAllByUserIdBetweenDate(@Param("filialeId") Long filialeId,
                                                       @Param("userId") Long userId,
                                                       @Param("begin") LocalDate begin,
                                                       @Param("end") LocalDate end);

    @Query(value = "from InfoFilialeCaisse  ifc join ifc.filiale f where f.id=:filialeId and ifc.modePayement=:payement and ifc.date=:begin order by ifc.date asc")
    List<InfoFilialeCaisse> findAllByTypePayement(@Param("filialeId") Long filialeId, @Param("payement") ModePayement payement, @Param("begin") LocalDate begin);

    @Query(value = "from InfoFilialeCaisse  ifc join ifc.filiale f where f.id=:filialeId and ifc.operationCaisse=:operation and ifc.date=:begin order by ifc.date asc")
    List<InfoFilialeCaisse> findAllByTypeOperation(@Param("filialeId") Long filialeId, @Param("operation") TypeOperationCaisse operationCaisse, @Param("begin") LocalDate begin);

    @Query(value = "from InfoFilialeCaisse  ifc join ifc.filiale f join ifc.magasin m where f.id=:filialeId and m.id=:magasinId and ifc.date=:begin  order by ifc.date asc")
    List<InfoFilialeCaisse> getCashInfoByStoreId(@Param("filialeId") Long filialeId, @Param("magasinId") Long magasinId, @Param("begin") LocalDate begin);

    @Query(value = "from InfoFilialeCaisse  ifc join ifc.filiale f join ifc.magasin m where f.id=:filialeId and m.id=:magasinId and ( ifc.date between :begin and :end )  order by ifc.date asc")
    List<InfoFilialeCaisse> getCashInfoByStoreBetweenDate(@Param("filialeId") Long filialeId, @Param("magasinId") Long magasinId, @Param("begin") LocalDate begin, @Param("end") LocalDate end);
}
