package client.service.impl;

import client.entity.Tariff;
import client.exeptions.UserNotFoundException;
import client.reposetory.LogsRepository;
import client.reposetory.TariffRepository;
import client.service.TariffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Slf4j
public class TariffServiceImpl implements TariffService {
    @Autowired
    public LogsRepository logsRepository;
    @Autowired
    public TariffRepository tariffRepository;


    @Override
    public List<Tariff> getAll() throws UserNotFoundException {
        log.debug("Service /List<Tariff> getAll()/ started work");
        return tariffRepository.findAll();
    }

    @Override
    public Optional<Tariff> findByName(String name) throws UserNotFoundException {
        log.debug("Service / Optional<Tariff> findByName / started work");
        Optional<Tariff> tariffOptional = Optional.ofNullable(tariffRepository.findTariffByName(name));
        if (tariffOptional.isEmpty()) throw new UserNotFoundException("Tariff " + name + "  dose not existed");
        return tariffOptional;
    }
}



