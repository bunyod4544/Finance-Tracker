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
        return mapper.fromEntity(repository.save(operation));
    }

    public Map<String, Long> betweenTwoDates(PeriodDTO dto) {
        Map<String, Long> map = new HashMap<>();
        LocalDateTime endDateTime = LocalDateTime.of(dto.getEndDate(), LocalTime.MAX);
        LocalDateTime startDateTime = LocalDateTime.of(dto.getStartDate(), LocalTime.MIN);
        List<Operation> operations = repository.findOperationByCreationDateBetween(startDateTime, endDateTime);

        List<Long> credits = operations.stream().filter(s -> s.getType().name().equals(TransactionType.CREDIT.name())).map(s -> s.getAmount()).toList();
        List<Long> debits = operations.stream().filter(s -> s.getType().name().equals(TransactionType.DEBIT.name())).map(s -> s.getAmount()).toList();

        map.put("CREDIT", credits.stream().mapToLong(Long::longValue).sum());
        map.put("DEBIT", debits.stream().mapToLong(Long::longValue).sum());
        return map;
    }

    public List<OperationDTO> getAll(OperationCriteria criteria) {
        return repository.findAll(specification.getSpecification(criteria))
                .stream()
                .map(mapper::fromEntity)
                .toList();
    }

}
