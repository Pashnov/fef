package university.service;

import university.entity.User;

import java.util.List;

/**
 * Created by Andrii_Pashnov on 26.12.2014 11:23.
 */
public interface UserService {

    User read(long id);
    User read(String firstName, String lastName);
    List<User> findAll();

}
