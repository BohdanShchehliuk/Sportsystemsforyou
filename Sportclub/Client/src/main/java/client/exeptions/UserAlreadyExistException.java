package client.exeptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAlreadyExistException extends Exception {
    private int id;
    private String exceptionMassage;
}
