package uz.ba.finance.dto.operation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import uz.ba.finance.enums.Category;
import uz.ba.finance.enums.TransactionType;

/**
 * @author Bunyod on 05 июль 2023 at 23:19
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationDTO {

    private String description;
    private Long amount;
    private Category category;
    private TransactionType type;
}
