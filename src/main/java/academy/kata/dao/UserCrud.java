package academy.kata.dao;

import academy.kata.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserCrud implements Crud<User> {

    private final EntityManager entityManager;

    @Autowired
    public UserCrud(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select u FROM User u order by u.id", User.class).getResultList();
    }

    @Override
    public User findById(Long id) {
        Query query =  entityManager.createQuery("select u from User u where u.id=:id");
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Override
    public void delete(User user) {
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteById(long id) {
        Query query = entityManager.createQuery("delete from User where id=:id");
        query.setParameter("id", id);
        entityManager.getTransaction().begin();
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }
}
