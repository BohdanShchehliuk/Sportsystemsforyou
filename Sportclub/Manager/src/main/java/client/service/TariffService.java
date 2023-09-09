package client.service;

import client.entity.Tariff;
import client.exeptions.UserAlreadyExistException;
import client.exeptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TariffService {
    List<Tariff> getAll() throws UserNotFoundException;

    Tariff add(Tariff tariff) throws UserAlreadyExistException;

    String delete(String name) throws UserNotFoundException;

    Optional<Tariff> findByName(String name) throws UserNotFoundException;

    String unactivated(String name) throws UserNotFoundException;
}
