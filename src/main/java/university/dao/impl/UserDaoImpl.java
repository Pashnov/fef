package university.dao.impl;

import org.apache.log4j.Logger;
import university.constant.user.YearOfStudy;
import university.constant.user.UserRole;
import university.dao.UserDao;
import university.dao.exception.DaoStatementException;
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

    public static final String SQL_CREATE_USER = "INSERT INTO user (firstName, lastName, " +
            "email, password, role, yearOfStudy) VALUES (?, ?, ?, ?, ?, ?)";

    public static final String SQL_FIND_ALL_FREE_STUDENT = "SELECT * FROM user u WHERE u.id NOT IN " +
            "(SELECT gm.idUser FROM group_member gm) AND u.role = 'STUDENT' ";

    //**************columns***************

    public static final String ID = "user_id";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String IS_ACTIVE = "isActive";
    public static final String YEAR_OF_STUDY = "yearOfStudy";

    public static User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(ID));
        user.setFirstName(rs.getString(FIRST_NAME));
        user.setLastName(rs.getString(LAST_NAME));
        user.setEmail(rs.getString(EMAIL));
        user.setPassword(rs.getString(PASSWORD));
        user.setRole(UserRole.valueOf(rs.getString(ROLE)));
        user.setActive(rs.getBoolean(IS_ACTIVE));
        user.setYearOfStudy(YearOfStudy.getCourse(rs.getString(YEAR_OF_STUDY)));
        return user;
    }

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

    @Override
    public User create(User user){
        LOG.debug("create, user = " + user);
        ResultSet resultSet;
        Connection conn = JdbcConnectionHolder.get();
        try(PreparedStatement ps = conn.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
            initPrepareStatement(ps, user);
            if(ps.executeUpdate() > 0){
                resultSet = ps.getGeneratedKeys();
                if(resultSet.next()){
                    user.setId(resultSet.getLong(1));
                    return user;
                }
            }
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);
        }
        return null;
    }

    @Override
    public List<User> findFreeStudent() {
        LOG.debug("findFreeStudent");
        List<User> list = new ArrayList<>();
        ResultSet resultSet;
        Connection connection = JdbcConnectionHolder.get();
        try(Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(SQL_FIND_ALL_FREE_STUDENT);
            while(resultSet.next()){
                list.add(extractUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);
        }
        return list;
    }

    private void initPrepareStatement(PreparedStatement ps, User user) throws SQLException {
        int k = 1;
        ps.setString(k++, user.getFirstName());
        ps.setString(k++, user.getLastName());
        ps.setString(k++, user.getEmail());
        ps.setString(k++, user.getPassword());
        ps.setString(k++, user.getRole().name());
        ps.setString(k, user.getYearOfStudy() == null ? null :user.getYearOfStudy().name());
    }
}
