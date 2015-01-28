package university.service.impl;

import university.dao.GroupDao;
import university.dao.UserDao;
import university.entity.Group;
import university.service.GroupService;
import university.transaction.TransactionOperation;
import university.transaction.TransactionManager;

import java.util.List;

/**
 * Created by Andrii_Pashnov on 26.12.2014 13:46.
 */
public class GroupServiceImpl implements GroupService {

    private TransactionManager manager;
    private GroupDao groupDao;
    private UserDao userDao;

    public GroupServiceImpl(TransactionManager manager, GroupDao groupDao, UserDao userDao) {
        this.manager = manager;
        this.groupDao = groupDao;
        this.userDao = userDao;
    }

    @Override
    public Group create(final Group group) {
        return manager.doTransaction(new TransactionOperation<Group>() {
            @Override
            public Group execute() {
                groupDao.create(group);
                groupDao.insertMembers(group.getId(), group.getUsers());
                return group;
            }
        });
    }

    @Override
    public Group read(final long id) {
        return manager.doTransaction(new TransactionOperation<Group>() {
            @Override
            public Group execute() {
                Group group = groupDao.read(id);
                group.setUsers(userDao.findUsersByIdGroup(id));
                return group;
            }
        });
    }

    @Override
    public List<Group> findAll(final boolean isActive) {
        return manager.doTransaction(new TransactionOperation<List<Group>>() {
            @Override
            public List<Group> execute() {
                return groupDao.findAll(isActive);
            }
        });
    }

    @Override
    public Group getGroupByUserId(final Long userId) {
        return manager.doTransaction(new TransactionOperation<Group>() {
            @Override
            public Group execute() {
                Long groupId = groupDao.findGroupIdByUserId(userId);
                if(groupId == null){
                    return null;
                }
                return groupDao.read(groupId);
            }
        });
    }
}
