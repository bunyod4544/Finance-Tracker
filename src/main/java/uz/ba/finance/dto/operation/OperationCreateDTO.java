package uz.ba.finance.dto.operation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.ba.finance.enums.Category;
import uz.ba.finance.enums.TransactionType;

import javax.validation.constraints.NotBlank;

/**
 * @author Bunyod on 05 июль 2023 at 23:19
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationCreateDTO {

    private String description;

    @NotBlank(message = "Amount should not be empty")
    private Long amount;

    @NotBlank(message = "Category should not be empty")
    private Category category;

    @NotBlank(message = "Transaction type should not be empty")
    private TransactionType type;

}
