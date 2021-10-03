package academic.system.enrollment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler( value = ApiRequestException.class )
    public ResponseEntity<Object> handleApiRequestException( ApiRequestException requestException ) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException exception = new ApiException( requestException.getMessage(), requestException, badRequest, ZonedDateTime.now() );
        return new ResponseEntity<>( exception, badRequest );
    }
}
