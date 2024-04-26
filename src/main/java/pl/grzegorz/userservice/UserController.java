package pl.grzegorz.userservice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    UserDto getUserById(@PathVariable long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping
    List<UserDto> getAllUsers() {
        return userService.getUsers();
    }

    @PostMapping
    void createUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }

    @DeleteMapping("/{userId}")
    void removeUserById(@PathVariable long userId) {
        userService.removeUserById(userId);
    }
}