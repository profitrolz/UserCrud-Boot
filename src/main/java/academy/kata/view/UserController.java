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

    @GetMapping("/exception")
    public void getException(){
        throw new UserControllerException("Controller exception");
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ModelAndView handleApiRequestException (HttpServletRequest req, Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("timestamp", LocalDate.now());
        mav.addObject("status", HttpStatus.NOT_FOUND);
        mav.addObject("error", "User not found");
        mav.setViewName("errorPage");
        return mav;
    }

}
