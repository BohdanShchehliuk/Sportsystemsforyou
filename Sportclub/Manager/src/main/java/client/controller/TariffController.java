package client.controller;

import client.dto.TariffDto;
import client.entity.Tariff;
import client.exeptions.UserAlreadyExistException;
import client.exeptions.UserNotFoundException;
import client.service.TariffService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
public class TariffController {
    private ModelMapper mapToEntity;
    final private ModelMapper mapToDTO;
    private TariffService tariffService;

    @GetMapping("/tariff/getAll/public/")
    private List<TariffDto> getAll() throws Exception {
        log.info("Controller /tariff/getAll/ started work");
        List<TariffDto> tariffDto = tariffService.getAll().stream().map(tariff ->
                mapToDTO.map(tariff, TariffDto.class)).collect(Collectors.toList());
        return tariffDto;
    }

    @GetMapping("/tariff/getByName/public/")
    private TariffDto getByName(@RequestParam String name) throws UserNotFoundException {
        log.info("Controller /tariff/getByName/ started work");
        return mapToDTO.map(tariffService.findByName(name).get(), TariffDto.class);
    }

    @PostMapping("/tariff/add/admin/")
    public String addTariff(@RequestBody TariffDto tariffDto) throws UserAlreadyExistException {
        log.info("Controller tariff/add/ started work");
        tariffService.add(mapToEntity.map(tariffDto, Tariff.class));
        return "You add a new traffic ";
    }

    @PostMapping("/tariff/deleteByName/admin/")
    private String deleteByPhoneNumber(@RequestParam String name) throws UserNotFoundException {
        log.info("Controller /traffic/deleteByName/ started work");
        return tariffService.delete(name);
    }

    @PostMapping("/tariff/unactivatedByName/admin/")
    private String unactivatedByPhoneNumber(@RequestParam String name) throws UserNotFoundException {
        log.info("Controller tariff/unactivatedByName/ started work");
        return tariffService.unactivated(name);
    }
}
