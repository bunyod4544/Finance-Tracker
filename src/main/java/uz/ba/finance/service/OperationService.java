package uz.ba.finance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.ba.finance.dto.login.PeriodDTO;
import uz.ba.finance.dto.operation.OperationCreateDTO;
import uz.ba.finance.dto.operation.OperationDTO;
import uz.ba.finance.entity.Operation;
import uz.ba.finance.mapper.OperationMapper;
import uz.ba.finance.repository.OperationRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
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

    public OperationDTO create(OperationCreateDTO dto) {
        Operation operation = mapper.toEntity(dto);
        return mapper.fromEntity(repository.save(operation));
    }

//    public List<Map<String, Object>> betweenTwoDates(PeriodDTO dto) {
//            LocalDateTime endDateTime = LocalDateTime.of(dto.getEndDate(), LocalTime.MAX);
//            LocalDateTime startDateTime = LocalDateTime.of(dto.getStartDate(), LocalTime.MIN);
//            return repository.getOperationsBetweenDatesGroupByOperationType(startDateTime, endDateTime);
//    }

}
