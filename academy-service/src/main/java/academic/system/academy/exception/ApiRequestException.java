package academic.system.academy.exception;

public class ApiRequestException extends RuntimeException {

    public ApiRequestException( String message ) {
        super( message );
    }
}
