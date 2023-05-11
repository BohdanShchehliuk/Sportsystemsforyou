package client.service.impl;

import client.entity.Client;
import client.exeptions.CustomException;
import client.exeptions.UserAlreadyExistException;
import client.exeptions.UserNotFoundException;
import client.reposetory.ClientRepository;
import client.reposetory.LogsRepository;
import client.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Slf4j
public class ClientServiceImpl implements ClientService {
    @Autowired
    public ClientRepository clientRepository;
    @Autowired
    public LogsRepository logsRepository;

    @Override
    public List<Client> getAll() throws UserNotFoundException {
        log.debug("Service / List<Client> getAll() / started work");
        return clientRepository.findAll();
    }

    @Override
    public Client addClient(Client client) throws UserAlreadyExistException {
        log.debug("Service / Client addClient/ started work");
        if (client.equals(null)) throw new CustomException("Type client again");
        if (client.getName().equals(null) || client.getSurname().equals(null) || client.getPhone() == null) {
            throw new CustomException("Can't be added. Check name, surname and phoneNumb again");
        }
        Optional<Client> clientNew = Optional.ofNullable(clientRepository.findClientByPhone(client.getPhone()));
        if (!clientNew.isEmpty()) {
            throw new UserAlreadyExistException(client.getId(), "Client is already existed");
        }
        return clientRepository.save(client);
    }

    @Override
    public Client payment(String phoneNumb, float payment) throws UserNotFoundException {
        log.debug("Service / Client payment/ started work");
        Optional<Client> clientNew = Optional.ofNullable(clientRepository.findClientByPhone(phoneNumb));
        if (clientNew.isEmpty()) {
            throw new UserNotFoundException("There is no client with such phoneNumber   " + phoneNumb);
        }
        Client client = clientNew.get();
        client.setWallet(clientNew.get().getWallet() + payment);
        clientRepository.save(clientNew.get());
        log.debug(clientNew.get().getName() + " made a payment " + payment);
        return client;
    }

    public String delete(String name, String surname, String phoneNumber) throws UserNotFoundException {
        log.debug("Service / Client delete/ started work");
        Optional<Client> clientNew = getByPhoneNumb(phoneNumber);
        Client client = clientNew.get();
        if (!logsRepository.findById(client.getId()).isEmpty()) {
            return "Client couldn't be deleted";
        }
        if (client.equals(null)) throw new CustomException("Type client again");
        if (client.getName().equals(name) || client.getSurname().equals(surname) || client.getPhone().equals(phoneNumber)) {
            clientRepository.delete(client);
            return "Client was deleted";
        } else return "There is no such client";
    }

    @Override
    public String unactivated(String name, String surname, String phoneNumber) throws UserNotFoundException {
        log.debug("Service / Client unactivated/ started work");
        Optional<Client> clientNew = getByPhoneNumb(phoneNumber);
        Client client = clientNew.get();
        if (client.equals(null)) new CustomException("Type client again");

        if (client.getName().equals(name) || client.getSurname().equals(surname) ||
                client.getPhone().equals(phoneNumber)) {
            if (client.isActive() == false) {
                return "Client is not active";
            } else {
                client.setActive(false);
                clientRepository.save(client);
                return "Client was unactivated";
            }
        }
        return "Client information was wrong";
    }

    @Override
    public String activated(String name, String surname, String phoneNumber) throws UserNotFoundException {
        log.debug("Service / Client unactivated/ started work");
        Optional<Client> clientNew = getByPhoneNumb(phoneNumber);
        Client client = clientNew.get();
        if (client.equals(null)) new CustomException("Type client again");

        if (client.getName().equals(name) || client.getSurname().equals(surname) ||
                client.getPhone().equals(phoneNumber)) {
            if (client.isActive() == true) {
                return "Client is not active";
            } else {
                client.setActive(true);
                clientRepository.save(client);
                return "Client was activated";
            }
        }
        return "Client information was wrong";
    }

    @Override
    public Optional<Client> getByPhoneNumb(String phoneNumber) throws UserNotFoundException {
        log.debug("Service /getByPhoneNumb/ started work");
        Optional<Client> clientNew = Optional.ofNullable(clientRepository.findClientByPhone(phoneNumber));
        if (clientNew.isEmpty()) {
            throw new UserNotFoundException("There is no client with such phoneNumber   " + phoneNumber);
        }
        return clientNew;
    }
}
