package client.utils;

import client.service.ClientService;
import client.service.LogsService;
import client.service.TariffService;
import client.service.UserService;
import client.service.impl.ClientServiceImpl;
import client.service.impl.TariffServiceImpl;
import client.service.impl.LogsServiceImpl;
import client.service.impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public LogsService logsService() {
        return new LogsServiceImpl();
    }

    @Bean
    public TariffService tariffService() {

        return new TariffServiceImpl();
    }

    @Bean
    public ClientService clientService() {

        return new ClientServiceImpl();
    }

    @Bean
    public UserService userService() {

        return new UserServiceImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    // Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
