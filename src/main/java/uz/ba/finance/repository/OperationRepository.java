package uz.ba.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import uz.ba.finance.entity.Operation;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Bunyod on 05 июль 2023 at 23:06
 */

public interface OperationRepository extends JpaRepository<Operation, Long>, JpaSpecificationExecutor<Operation> {

    List<Operation> findAllByCreationDateBetween(LocalDateTime startDateTime,
                                                 LocalDateTime endDateTime);

}
