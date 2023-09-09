package client.service.impl;

import client.entity.Tariff;
import client.exeptions.CustomException;
import client.exeptions.UserAlreadyExistException;
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
    public Tariff add(Tariff tariff) throws UserAlreadyExistException {
        log.debug("Service / Tariff addTariff/ started work");
        if (tariff.equals(null)) throw new CustomException("Type Tariff again");
        if (tariff.getName().equals(null) || tariff.getEndHour().equals(null) || tariff.getStartHour() == null) {
            throw new CustomException("Can't be added. Check time of your tariff");
        }

        Optional<Tariff> tariffNew = Optional.ofNullable(tariffRepository.findTariffByName(tariff.getName()));
        if (!tariffNew.isEmpty()) {
            throw new UserAlreadyExistException(tariff.getId(), "Tariff is already existed");
        }
        return tariffRepository.save(tariff);
    }

    @Override
    public String delete(String name) throws UserNotFoundException {
        log.debug("Service / Tariff delete/ started work");
        Optional<Tariff> tariffOptional = findByName(name);
        if (tariffOptional.isEmpty()) {
            return "There is no such tariff";
        }
        Tariff tariff = tariffOptional.get();

        if (!logsRepository.findById(tariff.getId()).isEmpty()) {
            return "Tariff couldn't be deleted";
        }

        tariffRepository.delete(tariff);
        return "Tariff was deleted";
    }

    @Override
    public Optional<Tariff> findByName(String name) throws UserNotFoundException {
        log.debug("Service / Optional<Tariff> findByName / started work");
        Optional<Tariff> tariffOptional = Optional.ofNullable(tariffRepository.findTariffByName(name));
        if (tariffOptional.isEmpty()) throw new UserNotFoundException("Tariff " + name + "  dose not existed");
        return tariffOptional;
    }

    @Override
    public String unactivated(String name) throws UserNotFoundException {
        log.debug("Service / Tariff unactivated/ started work");
        Optional<Tariff> tariffOptional = findByName(name);
        Tariff tariff = tariffOptional.get();
        if (tariffOptional.isEmpty()) {
            new UserNotFoundException("there is no such tariff");
        } else if (tariff.getName().equals(name)) {
            if (tariff.isActive() == false) {
                return "Tariff is not active";
            } else {
                tariff.setActive(false);
                tariffRepository.save(tariff);
                return "Tariff was unactivated";
            }
        }
        return "Tariff information was wrong";
    }
}



