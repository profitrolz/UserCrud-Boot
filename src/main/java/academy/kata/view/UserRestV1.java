package academy.kata.view;

import academy.kata.exception.UserNotFoundException;
import academy.kata.model.User;
import academy.kata.service.RoleService;
import academy.kata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserRestV1 {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserRestV1(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping(path = "{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.findById(userId).orElseThrow(UserNotFoundException::new);
    }


}
