package uz.ba.finance.configuration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import uz.ba.finance.response.ResponseData;
import uz.ba.finance.response.ResponseMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AbstractFilter {

    public static void errorResponse(HttpServletResponse response, ResponseEntity<ResponseData<ResponseMessage>> responseData) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
    }

    public static String getTokenFromRequest(HttpServletRequest request) {
        return Objects.requireNonNullElse(request.getHeader("Authorization"), "");
    }

}
