package client.controller;

import client.dto.ClientDto;
import client.entity.Client;
import client.service.impl.ClientServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {
    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private ModelMapper mapperToDTO;
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getByPhoneNumber() throws Exception {
        Client client = clientService.getAll().get(0);
        String param = client.getPhone();
        RequestBuilder request = MockMvcRequestBuilders.get("/client/getByPhoneNumber/admin/")
                .param("phone", param);
        Optional<Client> client1 = clientService.getByPhoneNumb(param);
        System.out.println("CCC" + client1.toString());


        MvcResult result = mockMvc.perform(request).andReturn();
        String actualResponseBody = result.getResponse().getContentAsString();

        System.out.println("AAA " + actualResponseBody);
        System.out.println("BBB " + mapperToDTO.map(client, ClientDto.class));
        assertEquals(actualResponseBody, mapperToDTO.map(client, ClientDto.class).toString());
    }
}