package client.service;

import client.entity.Client;
import client.exeptions.UserAlreadyExistException;
import client.exeptions.UserNotFoundException;


import java.util.List;
import java.util.Optional;

public interface ClientService {

    Client payment(String phoneNumb, float payment) throws UserNotFoundException;

    Optional<Client> getByPhoneNumb(String phoneNumber) throws UserNotFoundException;
}
