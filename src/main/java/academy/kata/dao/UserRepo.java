package academy.kata.dao;

import academy.kata.model.Role;
import academy.kata.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findUserByLogin(String login);

    List<User> findAll();

    List<User> findAllByRoles(Role role);
}
