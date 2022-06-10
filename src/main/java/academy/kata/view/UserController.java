package academy.kata.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import academy.kata.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userView(Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "user";
    }

}
