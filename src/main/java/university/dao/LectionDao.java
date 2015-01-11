package university.dao;

import university.entity.Lection;

public interface LectionDao {

    Lection read(long id);
    Lection create(Lection lection);
    void delete(long id);
    void update(long id, String text);

    String readText(long id);
}
