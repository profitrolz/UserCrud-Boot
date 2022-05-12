package academy.kata.view;

import academy.kata.model.User;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import academy.kata.service.UserService;

import java.util.Map;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService crud) {
        this.userService = crud;
    }

    @GetMapping("/")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "index";
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
        System.out.println();
        return "redirect:/";
    }

    @GetMapping("/update")
    public String showFormUpdateUser(@RequestParam Optional<Long> id, Model model) throws ChangeSetPersister.NotFoundException {
        model.addAttribute("user", userService.findById(id.orElseThrow(IllegalArgumentException::new)).orElseThrow(ChangeSetPersister.NotFoundException::new));
        model.addAttribute("add", false);
        return "user_action";
    }

    @GetMapping("/viewUser")
    public String showUserForm(@RequestParam Optional<Long> id, Model model) throws ChangeSetPersister.NotFoundException {
        model.addAttribute("user", userService.findById(id.orElseThrow(IllegalArgumentException::new)).orElseThrow(ChangeSetPersister.NotFoundException::new));
        return "user";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/";
    }
}
