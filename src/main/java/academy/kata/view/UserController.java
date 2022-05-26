package academy.kata.view;

import academy.kata.exception.UserControllerException;
import academy.kata.exception.UserNotFoundException;
import academy.kata.model.User;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import academy.kata.service.UserService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userView(Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser()
                .orElseThrow(UserNotFoundException::new));
        return "user";
    }

}
