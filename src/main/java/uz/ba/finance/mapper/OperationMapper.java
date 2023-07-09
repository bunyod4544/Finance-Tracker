package uz.ba.finance.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;
import uz.ba.finance.dto.operation.OperationCreateDTO;
import uz.ba.finance.dto.operation.OperationDTO;
import uz.ba.finance.entity.Operation;

/**
 * @author Bunyod on 05 июль 2023 at 23:52
 */

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface OperationMapper {

    OperationDTO fromEntity(Operation operation);

    Operation toEntity(OperationCreateDTO dto);

}
