package client.utils;

import client.service.ClientService;
import client.service.LogsService;
import client.service.TariffService;
import client.service.impl.ClientServiceImpl;
//import client.service.impl.LogsServiceImpl;
import client.service.impl.TariffServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public TariffService tariffService() {

        return new TariffServiceImpl();
    }

    @Bean
    public ClientService clientService() {

        return new ClientServiceImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

  //  @Bean
//    public LogsService logsService() {
//        return new LogsServiceImpl();
//    }
}
