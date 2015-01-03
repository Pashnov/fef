package university.service;

import university.entity.Group;

import java.util.List;

/**
 * Created by Andrii_Pashnov on 26.12.2014 13:18.
 */
public interface GroupService {

    Group create(Group group);
    Group read(long id);
    List<Group> findAll(boolean isActive);

}
