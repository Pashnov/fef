package university.dao;

import university.entity.Lection;

import java.util.List;

public interface LectionDao {

    Lection read(long id);
    Lection create(Lection lection);
    void delete(long id);
    void update(long id, String text);
    List<Lection> findAll();
    String readText(long id);
}
