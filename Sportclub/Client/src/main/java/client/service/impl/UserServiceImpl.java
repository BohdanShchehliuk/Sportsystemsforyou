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
import java.util.List;
import java.util.Set;

@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper mapperToEnt;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(UserDto userDto) throws UserNotFoundException {
        User user = mapperToEnt.map(userDto, User.class);
        user.setPw(passwordEncoder.encode("123"));
        user.setRole(new HashSet<>());
        user.getRole().add(Role.builder()
                .role("USER")
                .build());
        User save = userRepository.save(user);
        log.info("new User added to database", save.getName());
    }

    @Override
    public User getUserByName(String name) throws UserNotFoundException {
        User userByName = userRepository.getUserByName(name);
        if (userByName == null) {
            throw new UserNotFoundException("there is no user with name  " + name);
        }
        return userByName;
    }

    public String registerAdmin() {

        User user = User
                .builder()
                .pw("123")
                .name("User")
                .role(Set.of(Role.builder()
                        .role("Admin").build()))
                .build();
        user.setPw(passwordEncoder.encode(user.getPw()));
        return user.toString();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
