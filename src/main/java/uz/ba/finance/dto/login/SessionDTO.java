package uz.ba.finance.dto.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Bunyod on 16 November 2022 at 11:54 PM
 */
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionDTO {
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiry;
    private Long refreshTokenExpiry;

    public SessionDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
