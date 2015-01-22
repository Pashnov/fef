package university.dao;

import university.entity.User;

import java.util.List;

/**
 * Created by Andrii_Pashnov on 26.12.2014 11:44.
 */
public interface UserDao {

    User read(long id);
    User read(String firstName, String lastName);
    List<User> findAll();
    List<User> findUsersByIdGroup(long idGroup);
    List<User> findFreeStudent();
    User create(User user);
    void update(User user);

}
