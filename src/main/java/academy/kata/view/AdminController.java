package academy.kata.view;

import academy.kata.dao.RoleRepo;
import academy.kata.exception.UserNotFoundException;
import academy.kata.model.User;
import academy.kata.service.RoleService;
import academy.kata.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user", new User());
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "admin";
    }

    @GetMapping("/viewUser")
    public String showUserForm(@RequestParam Optional<Long> id, Model model) {
        model.addAttribute("user", userService.findById(id.orElseThrow(IllegalArgumentException::new))
                .orElseThrow(UserNotFoundException::new));
        return "user";
    }
}
