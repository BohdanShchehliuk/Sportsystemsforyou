package client.service.impl;

import client.entity.Client;
import client.entity.Logs;
import client.entity.Tariff;
import client.exeptions.CustomException;
import client.exeptions.UserNotFoundException;
import client.reposetory.ClientRepository;
import client.reposetory.LogsRepository;
import client.reposetory.TariffRepository;
import client.service.LogsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Slf4j
public class LogsServiceImpl implements LogsService {

    private final float POSTPONEPRISE = 50.0F; // 50 dollar per month

    @Autowired
    public ClientRepository clientRepository;

    @Autowired
    public LogsRepository logsRepository;
    @Autowired
    public TariffRepository tariffRepository;

    @Override
    public List<Logs> getAll() throws UserNotFoundException {
        return logsRepository.findAll();
    }

    @Override
    public Logs chooseTariff(String phoneNumb, String tariffName) throws CustomException {
        log.debug("Service / Logs chooseTariff/ started work");
        Logs logs = new Logs();
        checkClient(phoneNumb, tariffName);
        Tariff tariff = tariffRepository.findTariffByName(tariffName);
        Client client = clientRepository.findClientByPhone(phoneNumb);

        Optional<Logs> logsList = logsRepository.findAll().stream().filter(l -> l.getClient().equals(client) &&
                l.isActive()).findFirst();

        if (!logsList.isEmpty()) {
            Logs logsNew = logsList.get();
            logsNew.setActive(false);
            logsRepository.save(logsNew);
        }

        float budget = client.getWallet() - tariff.getPrice();

        if (budget < 0) {
            throw new CustomException("Client must add " + budget * (-1) + " dollar to his wallet");
        } else {
            logs.setTariff(tariff);
            logs.setClient(client);
            logs.setPayment(tariff.getPrice());
            logs.setStartDay(Date.valueOf(LocalDate.now()));
            logs.setEndDay(Date.valueOf(LocalDate.now().plusMonths(tariff.getDuration())));
            logs.setActive(true);
            client.setWallet(client.getWallet() - tariff.getPrice());
            clientRepository.save(client);
            logsRepository.save(logs);
        }
        return logs;
    }

    @Override
    public Logs postponeTariff(String phoneNumb, String tariffName, int month) throws CustomException {
        log.debug("Service /postponeTariff/ started work");
        checkClient(phoneNumb, tariffName);
        Tariff tariff = tariffRepository.findTariffByName(tariffName);
        Client client = clientRepository.findClientByPhone(phoneNumb);

        Optional<Logs> logsList = logsRepository.findAll().stream().filter(l -> l.getClient().equals(client) &&
                l.isActive()).findFirst();
        if (logsList.isEmpty()) {
            throw new CustomException("You have no active tariff");
        }
        Logs logsNew = logsList.get();

        float budget = client.getWallet() - month * POSTPONEPRISE;

        if (budget < 0) {
            throw new CustomException("Client must add " + budget * (-1) + " dollar to his wallet");
        } else {
            logsNew.setEndDay(Date.valueOf(logsNew.getEndDay().toLocalDate().plusMonths(month)));
            client.setWallet(client.getWallet() - tariff.getPrice());
            logsRepository.save(logsNew);
            clientRepository.save(client);
        }
        return logsNew;
    }

    @Override
    public Logs access(String phoneNumb) throws CustomException {
        log.debug("Service / Client payment/ started work");
        Optional<Client> clientOptional = Optional.ofNullable(clientRepository.findClientByPhone(phoneNumb));
        if (clientOptional.isEmpty()) {
            throw new CustomException("Check client");
        }
        Client client = clientOptional.get();
        Optional<Logs> logsList = logsRepository.findAll().stream().filter(l -> l.getClient().equals(client) &&
                l.isActive()).findFirst();
        if (logsList.isEmpty()) {
            throw new CustomException("You have no active tariff");
        }
        Logs logs = logsList.get();
        checkTime(logs);
        checkDay(logs);
        return logs;
    }

    @Override
    public List<Logs> getAllClientFromStartDataToFinishData(Date startData, Date finishData) throws CustomException {
        log.info("Service /List<Logs> getAllClientFromStartDataToFinishData/ started work");
        List<Logs> list = logsRepository.getAllClientFromStartDataToFinishData(startData, finishData);
        if (startData.after(finishData)) throw new CustomException("StartData is after FinishData");
        if (list.isEmpty()) {
            throw new CustomException("list is empty");
        }
        return list;
    }


    private void checkClient(String phoneNumb, String tariffName) {
        Optional<Client> clientOptional = Optional.ofNullable(clientRepository.findClientByPhone(phoneNumb));
        if (clientOptional.isEmpty()) {
            throw new CustomException("Check client");
        }
        Client client = clientOptional.get();

        Optional<Tariff> tariffOptional = Optional.ofNullable(tariffRepository.findTariffByName(tariffName));
        if (tariffOptional.isEmpty()) {
            throw new CustomException("Check tariff");
        }
        if (!tariffOptional.get().isActive()) {
            throw new CustomException("Tariff is unactivated ");
        }
        if (!client.isActive()) {
            throw new CustomException("Client is unactivated");
        }
    }

    private boolean checkTime(Logs logs) {
        if (logs.getTariff().getStartHour().toLocalTime().isBefore(LocalTime.now())
                && logs.getTariff().getEndHour().toLocalTime().isAfter(LocalTime.now())) {
            return true;
        } else {
            throw new CustomException("Access forbidden. Wrong Time");
        }
    }

    private boolean checkDay(Logs logs) {
        if (!logs.getEndDay().after(Date.valueOf(LocalDate.now()))) {
            throw new CustomException("Access forbidden. Wrong Day");
        }
        String day = String.valueOf(LocalDate.now().getDayOfWeek());

        if (logs.getTariff().isMonday() && day.equals("MONDAY") ||
                logs.getTariff().isTuesday() && day.equals("TUESDAY") ||
                logs.getTariff().isWednesday() && day.equals("WEDNESDAY") ||
                logs.getTariff().isThursday() && day.equals("THURSDAY") ||
                logs.getTariff().isFriday() && day.equals("FRIDAY") ||
                logs.getTariff().isSaturday() && day.equals("SATURDAY") ||
                logs.getTariff().isSunday() && day.equals("SUNDAY")) {
            return true;
        } else {
            throw new CustomException("Access forbidden. Wrong Day of week");
        }
    }
}
