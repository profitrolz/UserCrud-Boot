package academy.kata.view;

import academy.kata.model.User;
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

    @GetMapping("/user")
    public String showForm(Map<String, Object> model) {
        model.put("user", new User());
        model.put("add", true);
        return "user";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam Optional<Integer> id, Map<String, Object> model) {
        id.ifPresent(userService::deleteById);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String showFormUpdateUser(@RequestParam Optional<Integer> id, Model model) {
        model.addAttribute("user", userService.findById(id.orElseThrow(IllegalArgumentException::new)));
        return "user_update";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/";
    }
}
