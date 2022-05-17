package academy.kata.view;

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
@RequestMapping(path = "admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService crud) {
        this.userService = crud;
    }

    @GetMapping("/")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/createUser")
    public String showForm(Map<String, Object> model) {
        model.put("user", new User());
        model.put("add", true);
        return "user_action";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam Optional<Integer> id, Map<String, Object> model) {
        id.ifPresent(userService::deleteById);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String showFormUpdateUser(@RequestParam Optional<Long> id, Model model) throws ChangeSetPersister.NotFoundException {
        model.addAttribute("user", userService.findById(id.orElseThrow(IllegalArgumentException::new)).orElseThrow(ChangeSetPersister.NotFoundException::new));
        model.addAttribute("add", false);
        return "user_action";
    }

    @GetMapping("/viewUser")
    public String showUserForm(@RequestParam Optional<Long> id, Model model) {
        model.addAttribute("user", userService.findById(id.orElseThrow(IllegalArgumentException::new))
                .orElseThrow(UserNotFoundException::new));
        return "user";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/";
    }
}
