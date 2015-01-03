package university.dao.impl;

import org.apache.log4j.Logger;
import university.constant.user.Course;
import university.constant.user.UserRole;
import university.dao.UserDao;
import university.dao.entity.DaoStatementException;
import university.entity.User;
import university.transaction.JdbcConnectionHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii_Pashnov on 26.12.2014 11:47.
 */
public class UserDaoImpl implements UserDao {

    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

    public static final String SQL_READ_USER_BY_ID = "SELECT * FROM user WHERE id = ?";

    public static final String SQL_READ_USER_BY_FIRST_AND_LAST_NAME = "SELECT * FROM user " +
            "WHERE firstName = ? AND lastName = ? ";

    public static final String SQL_READ_ALL_USER = "SELECT * FROM user";

    public static final String SQL_READ_ALL_USER_BY_ID_GROUP = "SELECT * FROM user u " +
            "INNER JOIN group_member gm ON gm.idUser = u.id WHERE gm.idGroup = ?";

    @Override
    public User read(long id) {
        LOG.debug("read, id = " + id);
        ResultSet resultSet;
        Connection connection = JdbcConnectionHolder.get();
        try(PreparedStatement ps = connection.prepareStatement(SQL_READ_USER_BY_ID)) {
            ps.setLong(1, id);
            resultSet = ps.executeQuery();
            if(resultSet.next()){
                return extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);
        }
        return null;
    }

    @Override
    public User read(String firstName, String lastName) {
        LOG.debug("read, firstName = " + firstName + ", lastName = " + lastName);
        ResultSet resultSet;
        Connection connection = JdbcConnectionHolder.get();
        try(PreparedStatement ps = connection.prepareStatement(SQL_READ_USER_BY_FIRST_AND_LAST_NAME)) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            resultSet = ps.executeQuery();
            if(resultSet.next()){
                return extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        LOG.debug("findAll");
        List<User> list = new ArrayList<>();
        ResultSet resultSet;
        Connection connection = JdbcConnectionHolder.get();
        try(Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(SQL_READ_ALL_USER);
            while (resultSet.next()){
                list.add(extractUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);
        }
        return list;
    }

    @Override
    public List<User> findUsersByIdGroup(long idGroup) {
        LOG.debug("findUsersByIdGroup, idGroup = " + idGroup);
        List<User> list = new ArrayList<>();
        ResultSet resultSet;
        Connection connection = JdbcConnectionHolder.get();
        try(PreparedStatement ps = connection.prepareStatement(SQL_READ_ALL_USER_BY_ID_GROUP)) {
            ps.setLong(1, idGroup);
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                list.add(extractUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);
        }
        return list;
    }

    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setEmail(rs.getString("email"));
        user.setRole(UserRole.valueOf(rs.getString("role")));
        user.setActive(rs.getBoolean("isActive"));
        user.setCourse(Course.getCourse(rs.getString("course")));
        return user;
    }
}
