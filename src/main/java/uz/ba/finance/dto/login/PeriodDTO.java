package uz.ba.finance.dto.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * @author Bunyod on 06 июль 2023 at 20:43
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeriodDTO {

    @NotBlank(message = "Start date cannot be empty")
    private LocalDate startDate;

    @NotBlank(message = "End date cannot be empty")
    private LocalDate endDate;

}
