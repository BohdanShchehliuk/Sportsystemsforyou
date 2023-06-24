package client.exeptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class Handler {
    public static final String ANSI_RED = "\u001B[31m";

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseEntity<Object> customException(CustomException ex) {
        log.error(ANSI_RED + " " + ex.getMessage() + " ");
        return new ResponseEntity(ex.getExceptionMassage(), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> userNotFoundException(UserNotFoundException ex) {
        log.error(ANSI_RED + " " + ex.getExceptionMassage() + " ");

        return new ResponseEntity(ex.getExceptionMassage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseBody
    public ResponseEntity<Object> userAlreadyExistException(UserAlreadyExistException ex) {
        log.error(ANSI_RED + " " + ex.getExceptionMassage() + " ");
        return new ResponseEntity(ex.getExceptionMassage(), HttpStatus.CONFLICT);
    }

}
