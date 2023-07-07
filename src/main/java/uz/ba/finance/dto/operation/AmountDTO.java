package uz.ba.finance.dto.operation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Bunyod on 06 июль 2023 at 21:22
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmountDTO {
    private Long amount;
    private String type;

}
