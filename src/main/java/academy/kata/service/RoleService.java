package academy.kata.service;

import academy.kata.dao.RoleRepo;
import academy.kata.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepo roleRepo;

    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public Role save(Role role) {
        return roleRepo.save(role);
    }

    public Optional<Role> findRoleByName(String name) {
        return roleRepo.findRoleByName(name);
    }

    public List<Role> findAll(){
        return roleRepo.findAll();
    }
}
