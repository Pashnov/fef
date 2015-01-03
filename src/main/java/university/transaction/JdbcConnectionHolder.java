package university.transaction;

import org.apache.log4j.Logger;

import java.sql.Connection;

/**
 * Created by Andrii_Pashnov on 23.12.2014 19:12.
 */
public class JdbcConnectionHolder {

    private static final Logger LOG = Logger.getLogger(JdbcConnectionHolder.class);

    private JdbcConnectionHolder() {
    }

    private static final ThreadLocal<Connection> stored = new ThreadLocal<>();

    public static Connection get(){
        LOG.trace("get");
        return stored.get();
    }

    public static void set(Connection conn){
        LOG.trace("set , con = " + conn);
        stored.set(conn);
    }

    public static void unset(){
        LOG.trace("unset");
        stored.remove();
    }
}
