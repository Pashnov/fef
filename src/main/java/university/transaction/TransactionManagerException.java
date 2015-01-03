package university.transaction;

/**
 * Created by Andrii_Pashnov on 23.12.2014 19:11.
 */
public class TransactionManagerException extends RuntimeException {

    public TransactionManagerException(Throwable ex) {
        super(ex);
    }

    public TransactionManagerException() {
    }

    public TransactionManagerException(String message) {
        super(message);
    }
}
