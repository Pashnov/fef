package university.dao.exception;

/**
 * Created by Andrii_Pashnov on 23.12.2014 19:42.
 */
public class DaoStatementException extends RuntimeException {
    public DaoStatementException() {
    }

    public DaoStatementException(String message) {
        super(message);
    }

    public DaoStatementException(Throwable cause) {
        super(cause);
    }
}
