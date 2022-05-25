package academy.kata.service;


import academy.kata.dao.RoleRepo;
import academy.kata.dao.UserDao;
import academy.kata.exception.LoginAlreadyExistException;
import academy.kata.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;
    private final RoleRepo roleRepo;

    public UserService(UserDao userDao, RoleRepo roleRepo) {
        this.userDao = userDao;
        this.roleRepo = roleRepo;
    }

    public void save(User user) {
        if(userDao.findUserByLogin(user.getLogin()).isPresent()) {
            throw new LoginAlreadyExistException();
        }

        userDao.save(user);
    }

    public void update(User user) {
        userDao.save(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public Optional<User> findById(long id) {
        return userDao.findById(id);
    }

    public void delete(User user) {
        userDao.delete(user);
    }

    public void deleteById(long id) {
        userDao.deleteById(id);
    }

    public Optional<User> findUserByLogin(String login) {
        return userDao.findUserByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userDao.findUserByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Login not found"));
        return user;
    }

    public Optional<User> getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return findUserByLogin(auth.getName());
    }

}
