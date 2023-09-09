package client.controller;

import client.dto.LogsDto;
import client.dto.TariffDto;
import client.entity.Logs;
import client.service.LogsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.undo.CannotUndoException;
import java.sql.Time;

@Slf4j
@RestController
@AllArgsConstructor
public class LogsController {
    final private ModelMapper mapToDTO;
    private LogsService logsService;

    @GetMapping("/tariff/create/")
    public TariffDto create() {
        log.info("Controller /tariff/create/ started work");
        return TariffDto.builder()
                .name("Super")
                .price(100000)
                .monday(true)
                .tuesday(true)
                .wednesday(true)
                .thursday(true)
                .friday(true)
                .saturday(true)
                .sunday(true)
                .startHour(Time.valueOf("00:00:00"))
                .endHour(Time.valueOf("23:59:59"))
                .active(true)
                .duration(24)
                .build();
    }

    @PostMapping("/logs/chooseTariff/client/")
    public LogsDto chooseTariff(@RequestParam String phoneNumb, @RequestParam String tariffName) {
        log.info("Controller client/add/ started work");
        Logs logs = logsService.chooseTariff(phoneNumb, tariffName);
        return mapToDTO.map(logs, LogsDto.class);
    }

    @PostMapping("/logs/postponeTariff/client/")
    public LogsDto chooseTariff(@RequestParam String phoneNumb, @RequestParam String tariffName, @RequestParam int month) throws Exception {
        log.info("Controller logs/postponeTariff  started work");
        Logs logs = logsService.postponeTariff(phoneNumb, tariffName, month);
        return mapToDTO.map(logs, LogsDto.class);
    }

    @GetMapping("/logs/access/client/")
    public LogsDto access(@RequestParam String phoneNumb) throws CannotUndoException {
        log.info("Controller logs/postponeTariff  started work");
        Logs logs = logsService.access(phoneNumb);
        return mapToDTO.map(logs, LogsDto.class);
    }
}
