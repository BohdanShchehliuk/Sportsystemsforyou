package client.service.impl;

import client.dto.UserDto;

import client.entity.Role;
import client.entity.User;
import client.exeptions.UserNotFoundException;
import client.reposetory.UserRepository;
import client.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private ModelMapper mapperToEnt;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(UserDto userDto) throws UserNotFoundException {


        User user = mapperToEnt.map(userDto, User.class);
        user.setPw(passwordEncoder.encode("123"));
        user.setRoles(new HashSet<>());
        user.getRoles().add(Role.builder()
                .role("USER")
                .build());
        User save = userRepository.save(user);
        log.info("new User added to database");
        System.out.println("afasdfadsf");


    }

    @Override
    public User getUserByName(String name) throws UserNotFoundException {
        return null;
    }
}
