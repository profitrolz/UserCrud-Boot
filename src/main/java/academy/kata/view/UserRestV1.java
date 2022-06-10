package academy.kata.view;

import academy.kata.exception.UserNotFoundException;
import academy.kata.model.User;
import academy.kata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserRestV1 {

    private final UserService userService;

    @Autowired
    public UserRestV1(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void saveUser(@RequestBody User user) {
        userService.save(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping(path = "{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @PostMapping(path = "{userId}")
    public void updateUser(@PathVariable Long userId, @RequestBody User user) {
        user.setId(userId);
        userService.update(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteById(userId);
    }

}
