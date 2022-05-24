package academy.kata.dao;

import academy.kata.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    @Query("select user from User user join fetch user.roles where user.login = :login")
    Optional<User> findUserByLogin(String login);
}
