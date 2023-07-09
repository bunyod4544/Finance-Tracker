package uz.ba.finance.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.ba.finance.response.ResponseData;
import uz.ba.finance.response.ResponseError;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseData<Object>> handleUserNameNotFoundException(final UsernameNotFoundException ex, HttpServletRequest request) {
        return getResponseEntity(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseData<Object>> handleBadRequestException(final BadRequestException ex, HttpServletRequest request) {
        return getResponseEntity(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseData<Object>> handle(final Exception ex, HttpServletRequest request) {
        return getResponseEntity(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private <E extends Exception> ResponseEntity<ResponseData<Object>> getResponseEntity(E ex, HttpServletRequest request, HttpStatus status) {
        return ResponseData.error(
                ResponseError.response(ex.getMessage(), status.getReasonPhrase(), request),
                status
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorMessages = ex
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(Objects::nonNull)
                .toList();
        return new ResponseEntity<>(
                new ResponseData<>(
                        ResponseError.response(
                                String.join(", ", errorMessages),
                                status.getReasonPhrase()
                        )
                ),
                status
        );
    }

}
