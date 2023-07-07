package uz.ba.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.stereotype.Repository;
import uz.ba.finance.dto.operation.AmountDTO;
import uz.ba.finance.entity.Operation;

import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Bunyod on 05 июль 2023 at 23:06
 */


@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

//    @Query(nativeQuery = true,
//            value = "SELECT operation_type AS operationType, SUM(amount) AS totalAmount " +
//                    "FROM operations " +
//                    "WHERE creation_date BETWEEN :startDate AND :endDate " +
//                    "GROUP BY operation_type")
//    List<AmountDTO> getOperationsBetweenDatesGroupByOperationType(@Temporal(TemporalType.TIMESTAMP) LocalDateTime startDateTime,
//                                                                  @Temporal(TemporalType.TIMESTAMP) LocalDateTime endDateTime);

}
