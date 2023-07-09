package uz.ba.finance.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreateDTO {

    @NotBlank(message = "Name should not be empty")
    private String name;

    @NotBlank(message = "Username should not be empty")
    private String username;

    @NotBlank(message = "Password should not be empty")
    private String password;

}
