package client.service.impl;

import client.entity.Client;
import client.exeptions.UserNotFoundException;
import client.reposetory.ClientRepository;
import client.reposetory.LogsRepository;
import client.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Slf4j
public class ClientServiceImpl implements ClientService {
    @Autowired
    public ClientRepository clientRepository;
    @Autowired
    public LogsRepository logsRepository;

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
