package university.dao.impl;

import org.apache.log4j.Logger;
import university.dao.LectionCommentDao;
import university.dao.exception.DaoStatementException;
import university.entity.LectionComment;
import university.transaction.JdbcConnectionHolder;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class LectionCommentDaoImpl implements LectionCommentDao {

    private static final Logger LOG = Logger.getLogger(LectionCommentDaoImpl.class);

    public static final String SQL_CREATE = "INSERT INTO lection_comment (lection_id, user_id, text) " +
            "VALUES (?, ?, ?)";

    public static final String SQL_UPDATE = "UPDATE lection_comment SET text = ? WHERE lection_id = ?";

    public static final String SQL_FIND_ALL_BY_LECTION_ID = "SELECT * FROM lection_comment lc " +
            "INNER JOIN user u ON u.user_id = lc.user_id WHERE lc.lection_id = ? ORDER BY lc.lection_comment_id DESC ";

    public static final String SQL_DELETE = "DELETE FROM lection_comment WHERE lection_comment_id = ?";

    //*************columns*********
    public static final String ID = "lection_comment_id";
    public static final String LECTION_ID = "lection_id";
    public static final String USER_ID = "user_id";
    public static final String CREATION_DATE = "creation_date";
    public static final String TEXT = "text";


    public static LectionComment extractLectionCommentFromResultSet(ResultSet rs) throws SQLException {
        LectionComment comment = new LectionComment();
        comment.setId(rs.getLong(ID));
        comment.setLectionId(rs.getLong(LECTION_ID));
        comment.setUser(UserDaoImpl.extractUserFromResultSet(rs));
        comment.setCreationDate(rs.getTimestamp(CREATION_DATE));
        comment.setText(rs.getString(TEXT));
        return comment;
    }

    @Override
    public LectionComment create(LectionComment comment) {
        LOG.debug("create, comment = " + comment);
        Connection connection = JdbcConnectionHolder.get();
        try(PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS)) {
            int k = 1;
            ps.setLong(k++, comment.getLectionId());
            ps.setLong(k++, comment.getUser().getId());
            ps.setString(k, comment.getText());
            if(ps.executeUpdate() > 0){
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    comment.setCreationDate(new Date());
                    comment.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);
        }
        return null;
    }

    @Override
    public void updateText(long lectionCommentId, String text) {
        LOG.debug("updateText, lectionCommentId = " + lectionCommentId + ", text = " + text);
        Connection connection = JdbcConnectionHolder.get();
        try(PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)) {
            ps.setString(1, text);
            ps.setLong(2, lectionCommentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);
        }
    }

    @Override
    public List<LectionComment> findAllByLectionId(long lectionId) {
        LOG.debug("findAllByLectionId, lectionId = " + lectionId);
        List<LectionComment> list = new ArrayList<>();
        Connection connection = JdbcConnectionHolder.get();
        try(PreparedStatement ps = connection.prepareStatement(SQL_FIND_ALL_BY_LECTION_ID)) {
            ps.setLong(1, lectionId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                LectionComment comment = extractLectionCommentFromResultSet(rs);
                list.add(comment);
            }
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);
        }
        return list;
    }

    @Override
    public void delete(long lectionCommentId) {
        LOG.debug("delete, lectionCommentId = " + lectionCommentId);
        Connection connection = JdbcConnectionHolder.get();
        try(PreparedStatement ps = connection.prepareStatement(SQL_DELETE)) {
            ps.setLong(1, lectionCommentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);
        }
    }
}
