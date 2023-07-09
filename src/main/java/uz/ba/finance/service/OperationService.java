package uz.ba.finance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.ba.finance.criteria.OperationCriteria;
import uz.ba.finance.dto.login.PeriodDTO;
import uz.ba.finance.dto.operation.OperationCreateDTO;
import uz.ba.finance.dto.operation.OperationDTO;
import uz.ba.finance.entity.Operation;
import uz.ba.finance.enums.TransactionType;
import uz.ba.finance.mapper.OperationMapper;
import uz.ba.finance.repository.OperationRepository;
import uz.ba.finance.specification.OperationSpecification;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bunyod on 05 июль 2023 at 23:05
 */

@Service
@RequiredArgsConstructor
public class OperationService {

    public final OperationMapper mapper;
    public final OperationRepository repository;
    public final OperationSpecification specification;

    public OperationDTO create(OperationCreateDTO dto) {
        Operation operation = mapper.toEntity(dto);
        repository.save(operation);
        return mapper.fromEntity(operation);
    }

    public Map<String, Long> betweenTwoDates(PeriodDTO dto) {
        LocalDateTime endDateTime = LocalDateTime.of(dto.getEndDate(), LocalTime.MAX);
        LocalDateTime startDateTime = LocalDateTime.of(dto.getStartDate(), LocalTime.MIN);
        List<Operation> operations = repository.findAllByCreationDateBetween(startDateTime, endDateTime);

        List<Long> credits = getByTransactionType(operations, TransactionType.CREDIT);
        List<Long> debits = getByTransactionType(operations, TransactionType.DEBIT);

        return Map.of("CREDIT", getSum(credits), "DEBIT", getSum(debits));
    }

    public List<OperationDTO> getAll(OperationCriteria criteria) {
        return repository.findAll(specification.getSpecification(criteria))
                .stream()
                .map(mapper::fromEntity)
                .toList();
    }

    private List<Long> getByTransactionType(List<Operation> operations, TransactionType transactionType) {
        return operations.stream()
                .filter(operation -> operation.getType() == transactionType)
                .map(Operation::getAmount)
                .toList();
    }

    private long getSum(List<Long> credits) {
        return credits.stream().mapToLong(Long::longValue).sum();
    }

}
