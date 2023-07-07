package uz.ba.finance.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Bunyod on 16 November 2022 at 11:54 PM
 */
@Data
@AllArgsConstructor
public class SessionDTO {
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiry;
    private Long refreshTokenExpiry;

    public SessionDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
