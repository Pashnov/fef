package university.service.impl;

import university.dao.UserDao;
import university.entity.User;
import university.service.UserService;
import university.transaction.TransactionOperation;
import university.transaction.TransactionManager;

import java.util.List;

/**
 * Created by Andrii_Pashnov on 26.12.2014 11:45.
 */
public class UserServiceImpl implements UserService {

    private TransactionManager manager;
    private UserDao userDao;

    public UserServiceImpl(TransactionManager manager, UserDao userDao) {
        this.manager = manager;
        this.userDao = userDao;
    }

    @Override
    public User read(final long id) {
        return manager.doTransaction(new TransactionOperation<User>() {
            @Override
            public User execute() {
                return userDao.read(id);
            }
        });
    }

    @Override
    public User read(final String firstName, final String lastName) {
        return manager.doTransaction(new TransactionOperation<User>() {
            @Override
            public User execute() {
                return userDao.read(firstName, lastName);
            }
        });
    }

    @Override
    public List<User> findAll() {
        return manager.doTransaction(new TransactionOperation<List<User>>() {
            @Override
            public List<User> execute() {
                return userDao.findAll();
            }
        });
    }

    @Override
    public User create(final User user) {
        return manager.doTransaction(new TransactionOperation<User>() {
            @Override
            public User execute() {
                return userDao.create(user);
            }
        });
    }

    @Override
    public void update(final User user) {
        manager.doTransaction(new TransactionOperation<User>() {
            @Override
            public User execute() {
                userDao.update(user);
                return null;
            }
        });
    }

    @Override
    public List<User> findFreeStudent() {
        return manager.doTransaction(new TransactionOperation<List<User>>() {
            @Override
            public List<User> execute() {
                return userDao.findFreeStudent();
            }
        });
    }
}
