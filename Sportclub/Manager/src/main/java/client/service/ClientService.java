package client.service;

import client.entity.Client;
import client.exeptions.UserAlreadyExistException;
import client.exeptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> getAll() throws UserNotFoundException;

    Client addClient(Client client) throws UserAlreadyExistException;

    String delete(String name, String surname, String phoneNumber) throws UserNotFoundException;

    String unactivated(String name, String surname, String phoneNumber) throws UserNotFoundException;

    String activated(String name, String surname, String phoneNumber) throws UserNotFoundException;

    Optional<Client> getByPhoneNumb(String phoneNumber) throws UserNotFoundException;
}
