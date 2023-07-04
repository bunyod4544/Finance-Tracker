package uz.ba.finance.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseError {
    private String message;
    private String reason;
    private String path;

    private ResponseError(String message) {
        this.message = message;
    }

    private ResponseError(String message, String reason) {
        this(message);
        this.reason = reason;
    }

    private ResponseError(String message, String reason, String path) {
        this(message, reason);
        this.path = path;
    }

    public ResponseError(String message, String reason, @Nullable HttpServletRequest request) {
        this(message, reason);
        if (Objects.nonNull(request)) this.path = request.getRequestURI();
    }

    public static ResponseError response(String message) {
        return response(message, null);
    }


    public static ResponseError response(String message, String reason) {
        return response(message, reason, null);
    }

    public static ResponseError response(String message, String reason, @Nullable HttpServletRequest request) {
        return new ResponseError(message, reason, request);
    }

}
