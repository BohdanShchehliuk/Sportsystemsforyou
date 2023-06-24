package client.controller;

import client.dto.ClientDto;
import client.entity.Client;
import client.exeptions.UserNotFoundException;
import client.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
@Slf4j
@RestController
@AllArgsConstructor
public class ClientController {
    private ModelMapper mapToEntity;
    final private ModelMapper mapToDTO;
    private ClientService clientService;
    @GetMapping("/client/create/client/")
    public ClientDto create() {
        log.info("Controller /client/create/ started work");
        return ClientDto.builder()
                .name("Oleksiy")
                .surname("Khoma")
                .birthdate(LocalDate.ofEpochDay(1988 - 10 - 28))
                .wallet(0)
                .phone("380289933")
                .additionalInfo("Пє пиво під час тренування")
                .build();
    }
    @GetMapping("/client/getByPhoneNumber/client/")
    private ClientDto getByPhoneNumber(@RequestParam String phone) throws UserNotFoundException {
        log.info("Controller /client/getByPhoneNumber/ started work");
        return mapToDTO.map(clientService.getByPhoneNumb(phone), ClientDto.class);
    }
    @PostMapping("/client/payByPhoneNumber/client/")
    private Client payByPhoneNumber(@RequestParam String phone, @RequestParam float payment) throws Exception {
        log.info("Controller /client/payByPhoneNumber/ started work");
        return clientService.payment(phone, payment);
    }
}
