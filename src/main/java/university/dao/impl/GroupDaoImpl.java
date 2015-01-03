package university.dao.impl;

import org.apache.log4j.Logger;
import university.dao.GroupDao;
import university.dao.entity.DaoStatementException;
import university.entity.Group;
import university.entity.User;
import university.transaction.JdbcConnectionHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii_Pashnov on 26.12.2014 13:51.
 */
public class GroupDaoImpl implements GroupDao {

    private static final Logger LOG = Logger.getLogger(GroupDaoImpl.class);

    public static final String SQL_CREATE_GROUP = "INSERT INTO group (name, isActive) VALUES (?, ?)";

    public static final String SQL_INSERT_MEMBERS_IN_GROUP = "INSERT INTO group_member (idGroup, idUser) VALUES (?, ?)";

    public static final String SQL_READ_GROUP_BY_ID = "SELECT * FROM `group` WHERE id = ?";

    public static final String SQL_READ_ALL_GROUP_WITH_PARAM_IS_ACTIVE = "SELECT * FROM group WHERE isActive = ?";


    @Override
    public Group create(Group group) {
        LOG.debug("create, group = " + group);
        ResultSet resultSet;
        Connection connection = JdbcConnectionHolder.get();
        try(PreparedStatement ps = connection.prepareStatement(SQL_CREATE_GROUP, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1,group.getName());
            ps.setBoolean(1, group.isActive());
            if(ps.executeUpdate() > 0){
                resultSet = ps.getGeneratedKeys();
                if(resultSet.next()){
                    group.setId(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);
        }
        return null;
    }

    @Override
    public void insertMembers(long idGroup, List<User> users) {
        LOG.debug("insertMembers");
        Connection connection = JdbcConnectionHolder.get();
        try(PreparedStatement ps = connection.prepareStatement(SQL_INSERT_MEMBERS_IN_GROUP)) {
            for (User user : users) {
                ps.setLong(1,idGroup);
                ps.setLong(2, user.getId());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);
        }
    }

    @Override
    public Group read(long id) {
        LOG.debug("read, id = " + id);
        ResultSet resultSet;
        Connection connection = JdbcConnectionHolder.get();
        try(PreparedStatement ps = connection.prepareStatement(SQL_READ_GROUP_BY_ID)) {
//            ps.setLong(1,id);
            ps.setLong(1, id);
            resultSet = ps.executeQuery();
            if(resultSet.next()){
                return extractGroupFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);
        }
        return null;
    }

    private Group extractGroupFromResultSet(ResultSet resultSet) throws SQLException {
        Group group = new Group();
        group.setId(resultSet.getLong("id"));
        group.setName(resultSet.getString("name"));
        group.setActive(resultSet.getBoolean("isActive"));
        group.setCreationDate(resultSet.getDate("creationDate"));
        return group;
    }

    @Override
    public List<Group> findAll(boolean isActive) {
        LOG.debug("findAll, isActive = " + isActive);
        List<Group> list = new ArrayList<>();
        ResultSet resultSet;
        Connection connection = JdbcConnectionHolder.get();
        try(PreparedStatement ps = connection.prepareStatement(SQL_READ_ALL_GROUP_WITH_PARAM_IS_ACTIVE)) {
            ps.setBoolean(1, isActive);
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                list.add(extractGroupFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);
        }
        return list;
    }
}
