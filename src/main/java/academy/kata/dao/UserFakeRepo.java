package academy.kata.dao;

import academy.kata.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class UserFakeRepo implements UserDao {


    private final UserDao userDao;

    public UserFakeRepo(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<User> entities) {
        userDao.deleteInBatch(entities);
    }

    @Override
    @Deprecated
    public User getOne(Long aLong) {
        return userDao.getOne(aLong);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> findAll(Sort sort) {
        return userDao.findAll(sort);
    }

    @Override
    public List<User> findAllById(Iterable<Long> longs) {
        return userDao.findAllById(longs);
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        return userDao.saveAll(entities);
    }

    @Override
    public void flush() {
        userDao.flush();
    }

    @Override
    public <S extends User> S saveAndFlush(S entity) {
        return userDao.saveAndFlush(entity);
    }

    @Override
    public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
        return userDao.saveAllAndFlush(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<User> entities) {
        userDao.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        userDao.deleteAllByIdInBatch(longs);
    }

    @Override
    public void deleteAllInBatch() {
        userDao.deleteAllInBatch();
    }

    @Override
    public User getById(Long aLong) {
        return userDao.getById(aLong);
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return userDao.findAll(example);
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return userDao.findAll(example, sort);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    @Override
    public <S extends User> S save(S entity) {
        return userDao.save(entity);
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return userDao.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return userDao.existsById(aLong);
    }

    @Override
    public long count() {
        return userDao.count();
    }

    @Override
    public void deleteById(Long aLong) {
        userDao.deleteById(aLong);
    }

    @Override
    public void delete(User entity) {
        userDao.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        userDao.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        userDao.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        userDao.deleteAll();
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return userDao.findOne(example);
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return userDao.findAll(example, pageable);
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return userDao.count(example);
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        return userDao.exists(example);
    }

    @Override
    public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return userDao.findBy(example, queryFunction);
    }
}
