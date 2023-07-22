package client.controller;

import client.dto.UserDto;
import client.entity.User;
import client.exeptions.UserNotFoundException;
import client.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class UserController {


    private UserService userService;
    @PostMapping("/user/")
    private void registerUser (@RequestBody UserDto userDto) throws UserNotFoundException {
        log.info("Controller //user// started work");
        System.out.println("Lalala");

        userService.saveUser(userDto);
    }
    @GetMapping ("/user/register/")
    private String registerAdmin ()  {
        log.info("Controller //user/register// started work");
        return  userService.registerAdmin();
    }
//@GetMapping("/user/getAll/")
//private List<User> getAll() throws UserNotFoundException {
//    log.info("Controller //user/getAll// started work");
//    return  userService.findAllUsers();
//}
}
