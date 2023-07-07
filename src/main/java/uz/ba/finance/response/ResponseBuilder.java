package uz.ba.finance.response;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author Doston Bokhodirov | 09.05.2023 09:34
 */


@Component("customResponseBuilder")
@RequiredArgsConstructor
public class ResponseBuilder {


    public ResponseEntity<ResponseData<ResponseMessage>> unauthorized() {
        return prepareResponse("Invalid token", HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<ResponseData<ResponseMessage>> prepareResponse(String message, HttpStatus httpStatus) {
        return isSuccessStatus(httpStatus)
                ? ResponseData.ok(new ResponseMessage(message))
                : ResponseData.error(ResponseError.response(message), httpStatus);
    }

    private boolean isSuccessStatus(HttpStatus status) {
        return status.value() >= 200 && status.value() < 300;
    }

    public ResponseEntity<ResponseData<ResponseMessage>> success(String message) {
        return prepareResponse(message, HttpStatus.OK);
    }

    public ResponseEntity<ResponseData<ResponseMessage>> accessDenied() {
        return prepareResponse("Access denied", HttpStatus.FORBIDDEN);
    }

}
