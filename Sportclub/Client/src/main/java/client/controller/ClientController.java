package client.controller;

import client.dto.ClientDto;
import client.entity.Client;
import client.exeptions.UserAlreadyExistException;
import client.exeptions.UserNotFoundException;
import client.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
public class ClientController {
    private ModelMapper mapToEntity;
    final private ModelMapper mapToDTO;
    private ClientService clientService;

    @GetMapping("/client/getAll/")
    private List<ClientDto> getAll() throws UserNotFoundException {
        log.info("Controller /client/getAll/ started work");
        List<ClientDto> clientDto = clientService.getAll().stream().map(client ->
                mapToDTO.map(client, ClientDto.class)).collect(Collectors.toList());
        return clientDto;
    }

    @PostMapping("/client/add/")
    public String addClient(@RequestBody ClientDto clientDto) throws UserAlreadyExistException {
        log.info("Controller client/add/ started work");
        clientService.addClient(mapToEntity.map(clientDto, Client.class));
        return "You add a new client ";
    }

    @GetMapping("/client/create/")
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
    @GetMapping("/client/getByPhoneNumber/")
    private ClientDto getByPhoneNumber(@RequestParam String phone) throws UserNotFoundException {
        log.info("Controller /client/getByPhoneNumber/ started work");
        return mapToDTO.map(clientService.getByPhoneNumb(phone), ClientDto.class);
    }
    @PostMapping("/client/deleteByPhoneNumber/")
    private String deleteByPhoneNumber(@RequestParam String name, @RequestParam String surname, @RequestParam String phone) throws Exception {
        log.info("Controller /client/deleteByPhoneNumber/ started work");
        return clientService.delete(name, surname, phone);
    }
    @PostMapping("/client/unactivatedByPhoneNumber/")
    private String unactivatedByPhoneNumber(@RequestParam String name, @RequestParam String surname, @RequestParam String phone) throws Exception {
        log.info("Controller /client/unactivatedByPhoneNumber/ started work");
        return clientService.unactivated(name, surname, phone);
    }
    @PostMapping("/client/activatedByPhoneNumber/")
    private String activatedByPhoneNumber(@RequestParam String name, @RequestParam String surname, @RequestParam String phone) throws Exception {
        log.info("Controller /client/activatedByPhoneNumber/ started work");
        return clientService.unactivated(name, surname, phone);
    }
    @PostMapping("/client/payByPhoneNumber/")
    private Client payByPhoneNumber(@RequestParam String phone, @RequestParam float payment) throws Exception {
        log.info("Controller /client/payByPhoneNumber/ started work");
        return clientService.payment(phone, payment);
    }
}
