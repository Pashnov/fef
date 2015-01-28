package university.dao;

import university.entity.Group;
import university.entity.User;

import java.util.List;

/**
 * Created by Andrii_Pashnov on 26.12.2014 13:38.
 */
public interface GroupDao {

    Group create(Group group);
    void insertMembers(long idGroup, List<User> users);
    Group read(long id);
    List<Group> findAll(boolean isActive);
    Long findGroupIdByUserId(Long userId);
}
