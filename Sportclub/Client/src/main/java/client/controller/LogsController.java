package client.controller;

import client.dto.LogsDto;
import client.dto.TariffDto;
import client.entity.Logs;
import client.service.LogsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.swing.undo.CannotUndoException;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
public class LogsController {
    final private ModelMapper mapToDTO;
    private LogsService logsService;

    @GetMapping("/logs/getAll/")
    private List<LogsDto> getAll() throws Exception {
        log.info("Controller /logs/getAll/ started work");
        return logsService.getAll().stream().map(logs -> mapToDTO.map(logs, LogsDto.class)).collect(Collectors.toList());
    }

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

    @PostMapping("/logs/chooseTariff/")
    public LogsDto chooseTariff(@RequestParam String phoneNumb, @RequestParam String tariffName) {
        log.info("Controller client/add/ started work");
        Logs logs = logsService.chooseTariff(phoneNumb, tariffName);
        return mapToDTO.map(logs, LogsDto.class);
    }

    @PostMapping("/logs/postponeTariff/")
    public LogsDto chooseTariff(@RequestParam String phoneNumb, @RequestParam String tariffName, @RequestParam int month) throws Exception {
        log.info("Controller logs/postponeTariff  started work");
        Logs logs = logsService.postponeTariff(phoneNumb, tariffName, month);
        return mapToDTO.map(logs, LogsDto.class);
    }

    @GetMapping("/logs/access/")
    public LogsDto access(@RequestParam String phoneNumb) throws CannotUndoException {
        log.info("Controller logs/postponeTariff  started work");
        Logs logs = logsService.access(phoneNumb);
        return mapToDTO.map(logs, LogsDto.class);
    }

    @GetMapping("/logs/getAllClientFromStartDataToFinishData/")
    public List<String> getAllClientFromStartDataToFinishData(@RequestParam Date startData,
                                                               @RequestParam Date finishData) throws CannotUndoException {
        System.out.println("LLLLLFFFF");
        log.info("Controller logs/getAllClientFromStartDataToFinishData  started work");

      List<String> list = logsService.getAllClientFromStartDataToFinishData(startData, finishData).stream().map(logs -> logs.toString()).collect(Collectors.toList());
      //  List<LogsDto> logsDtoList = list.stream().map(logs -> mapToDTO.map(logs, LogsDto.class)).collect(Collectors.toList());

        return list;
    }
}
