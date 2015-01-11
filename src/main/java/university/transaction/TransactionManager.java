package university.transaction;

import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Andrii_Pashnov on 23.12.2014 19:08.
 */
public class TransactionManager {

    private static final Logger LOG = Logger.getLogger(TransactionManager.class);

    private final DataSource ds;

    public TransactionManager(DataSource ds) {
        this.ds = ds;
        LOG.info("TransactionManager init");
    }

    public <T> T doTransaction(TransactionOperation<T> op){
        LOG.trace("doTransaction()");
        T res = null;
        Connection conn = null;
        try {
            conn = ds.getConnection();
            LOG.debug("conn = " + conn);
            JdbcConnectionHolder.set(conn);
            res = op.execute();
            conn.commit();
        } catch (Exception e) {
            LOG.warn(e);
            try {
                conn.rollback();
            } catch (SQLException ex) {
                LOG.warn(ex);
                throw new TransactionManagerException(ex);
            }
            throw new TransactionManagerException(e);
        }finally {
            JdbcConnectionHolder.unset();
            try {
                conn.close();
            } catch (SQLException ex) {
                LOG.warn(ex);
                throw new TransactionManagerException(ex);
            }
        }
        LOG.trace("doTransaction, res = " + res);
        return res;
    }
}
