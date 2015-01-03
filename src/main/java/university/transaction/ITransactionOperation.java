package university.transaction;

/**
 * Created by Andrii_Pashnov on 23.12.2014 19:09.
 */
public interface ITransactionOperation<E> {

    E execute();

}
