package academy.kata.configs;

import academy.kata.model.Role;
import academy.kata.model.User;
import academy.kata.service.RoleService;
import academy.kata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import java.util.Set;

public class Listener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Role admin = roleService.findRoleByName("ROLE_ADMIN").orElseGet(() -> roleService.save(new Role("ROLE_ADMIN")));
        roleService.findRoleByName("ROLE_USER").orElseGet(() -> roleService.save(new Role("ROLE_USER")));
        if (userService.findAllByRole(admin).isEmpty()){
            userService.save(User.builder().login("root").password("root").roles(Set.of(admin)).build());
        }
    }
}
