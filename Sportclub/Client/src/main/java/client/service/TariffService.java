package client.service;

import client.entity.Tariff;
import client.exeptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TariffService {
    List<Tariff> getAll() throws UserNotFoundException;

    Optional<Tariff> findByName(String name) throws UserNotFoundException;

}
