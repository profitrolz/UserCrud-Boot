package academy.kata.view;

import academy.kata.model.Role;
import academy.kata.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/roles")
public class RoleRestV1 {
    private final RoleService roleService;

    public RoleRestV1(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Role> getRoles() {
        return roleService.findAll();
    }
}
