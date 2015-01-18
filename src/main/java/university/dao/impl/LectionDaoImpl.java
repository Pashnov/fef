package university.dao.impl;

import org.apache.log4j.Logger;
import university.dao.LectionDao;
import university.dao.exception.DaoStatementException;
import university.entity.Lection;
import university.transaction.JdbcConnectionHolder;

import java.sql.*;

public class LectionDaoImpl implements LectionDao {

    private static final Logger LOG = Logger.getLogger(LectionDaoImpl.class);

    public static final String SQL_READ_BY_ID = "SELECT * FROM lection WHERE id = ?";

    public static final String SQL_CREATE_LECTION = "INSERT INTO lection (name, idLecture, idSubject) " +
            "VALUES (?, ?, ?)";

    public static final String SQL_DELETE_BY_ID = "DELETE FROM lection WHERE id = ?";

    public static final String SQL_UPDATE_TEXT_BY_ID = "UPDATE lection SET text = ? WHERE id = ?";

    public static final String SQL_READ_TEXT_BY_ID = "SELECT text FROM lection WHERE id = ?";

    @Override
    public Lection read(long id) {
        LOG.debug("read, id = " + id);
        Lection lection = null;
        Connection connection = JdbcConnectionHolder.get();
        try(PreparedStatement ps = connection.prepareStatement(SQL_READ_BY_ID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                lection = extractLection(rs);
            }
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);
        }
        return lection;
    }

    private Lection extractLection(ResultSet rs) throws SQLException {
        Lection lection = null;
        if(rs.getLong("id") != 0){
            lection = new Lection();
            lection.setId(rs.getLong("id"));
            lection.setName(rs.getString("name"));
            lection.setText(rs.getString("text"));
            //todo: add public extract
        }
        return lection;
    }

    @Override
    public String readText(long id) {
        LOG.debug("readText, id = " + id);
        Connection connection = JdbcConnectionHolder.get();
        try(PreparedStatement ps = connection.prepareStatement(SQL_READ_TEXT_BY_ID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);
        }
        return "";
    }

    @Override
    public Lection create(Lection lection) {
        LOG.debug("create, lection = " + lection);
        Connection connection = JdbcConnectionHolder.get();
        try(PreparedStatement ps = connection.prepareStatement(SQL_CREATE_LECTION, Statement.RETURN_GENERATED_KEYS)) {
            int k = 1;
            ps.setString(k++, lection.getName());
            ps.setLong(k++, lection.getLecture().getId());
            ps.setLong(k, lection.getSubject().getId());
            if(ps.executeUpdate() > 0){
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    lection.setId(rs.getLong(1));
                    return lection;
                }
            }
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        LOG.debug("delete, id = " + id);
        Connection connection = JdbcConnectionHolder.get();
        try(PreparedStatement ps = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);
        }
    }

    @Override
    public void update(long id, String text) {
        LOG.debug("updateText, id = " + id + ", text = " + text);
        Connection connection = JdbcConnectionHolder.get();
        try(PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_TEXT_BY_ID)) {
            ps.setString(1, text);
            ps.setLong(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);
        }
    }
}
