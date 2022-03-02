package security.demo.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleUnwantedException(Exception e) {
        logger.error(e.getMessage());
        return new ResponseEntity<>("Unknown error",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity handleCustomException(CustomException e){
        logger.warn(e.getHttpStatus() + " " + e.getMessage());
        return new ResponseEntity<>(e.getMessage(),e.getHttpStatus());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException e){
        logger.warn(e.getMessage());
        return new ResponseEntity("Wrong input: " + e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
