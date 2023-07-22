package client.service;

import client.dto.UserDto;
import client.entity.User;
import client.exeptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    void saveUser (UserDto userDto) throws UserNotFoundException;

    User getUserByName (String name) throws UserNotFoundException;
     String  registerAdmin ();
    List <User> findAll ();

}
