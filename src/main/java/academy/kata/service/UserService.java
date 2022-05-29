package academy.kata.service;


import academy.kata.dao.RoleRepo;
import academy.kata.dao.UserRepo;
import academy.kata.exception.LoginAlreadyExistException;
import academy.kata.exception.UserNotFoundException;
import academy.kata.model.Role;
import academy.kata.model.User;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public UserService(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    public void save(User user) {
        if (userRepo.findUserByLogin(user.getLogin()).isPresent()) {
            throw new LoginAlreadyExistException();
        }

        userRepo.save(user);
    }

    public void update(User user) {
        userRepo.save(user);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public List<User> findAllByRole(Role role) {
        return userRepo.findAllByRoles(role);
    }

    public Optional<User> findById(long id) {
        return userRepo.findById(id);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }

    public void deleteById(long id) {
        userRepo.deleteById(id);
    }

    @Transactional
    public User findUserByLogin(String login) {
        User user = userRepo.findUserByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Login not found"));
        Hibernate.initialize(user);
        System.out.println(user.getRoles());
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByLogin(username);
        return user;
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = findUserByLogin(auth.getName());
        return currentUser;
    }

}
