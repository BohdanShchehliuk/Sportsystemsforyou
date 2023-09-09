package client.controller;

import client.dto.TariffDto;
import client.exeptions.UserNotFoundException;
import client.service.TariffService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
