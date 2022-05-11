package academy.kata.service;


import academy.kata.dao.UserDao;
import academy.kata.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userCrud;

    public UserService(@Qualifier("userDao") UserDao userCrud) {
        this.userCrud = userCrud;
    }

    public void save(User user) {
        userCrud.save(user);
    }

    public void update(User user) {
        userCrud.save(user);
    }

    public List<User> findAll() {
        return userCrud.findAll();
    }

    public Optional<User> findById(long id) {
        return userCrud.findById(id);
    }

    public void delete(User user) {
        userCrud.delete(user);
    }

    public void deleteById(long id) {
        userCrud.deleteById(id);
    }
}
