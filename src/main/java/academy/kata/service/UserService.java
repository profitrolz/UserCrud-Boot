package academy.kata.service;


import academy.kata.dao.UserDao;
import academy.kata.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;

    public UserService(@Qualifier("userDao") UserDao userDao) {
        this.userDao = userDao;
    }

    public void save(User user) {
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
        return Optional.ofNullable(userDao.findUserByLogin(login));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(userDao.findUserByLogin(username)).orElseThrow(() -> new UsernameNotFoundException("Login not found"));
    }
}
