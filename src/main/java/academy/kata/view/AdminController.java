package academy.kata.view;

import academy.kata.dao.RoleRepo;
import academy.kata.exception.UserNotFoundException;
import academy.kata.model.User;
import academy.kata.service.UserService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class AdminController {
    private final UserService userService;
    private final RoleRepo roleRepo;

    public AdminController(UserService userService, RoleRepo roleRepo) {
        this.userService = userService;
        this.roleRepo = roleRepo;
    }

    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleRepo.findAll());
        model.addAttribute("user", new User());
        model.addAttribute("currentUser", userService.getCurrentUser().orElseThrow(UserNotFoundException::new));
        return "admin";
    }


    @GetMapping("/createUser")
    public String showForm(Map<String, Object> model) {
        model.put("user", new User());
        model.put("add", true);
        return "user_action";
    }

    @GetMapping("/updateUser")
    public String showFormUpdateUser(@RequestParam Optional<Long> id, Model model) throws ChangeSetPersister.NotFoundException {
        model.addAttribute("user", userService.findById(id.orElseThrow(IllegalArgumentException::new)).orElseThrow(ChangeSetPersister.NotFoundException::new));
        model.addAttribute("add", false);
        return "user_action";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam Optional<Integer> id, Map<String, Object> model) {
        id.ifPresent(userService::deleteById);
        return "redirect:/admin";
    }

    @GetMapping("/viewUser")
    public String showUserForm(@RequestParam Optional<Long> id, Model model) {
        model.addAttribute("user", userService.findById(id.orElseThrow(IllegalArgumentException::new))
                .orElseThrow(UserNotFoundException::new));
        return "user";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin";
    }
}
