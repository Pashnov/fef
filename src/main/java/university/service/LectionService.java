package university.service;

import university.entity.Lection;

import java.util.List;

public interface LectionService {

    Lection read(long id);
    String readText(long id);
    Lection create(Lection lection);
    List<Lection> findAll();
    void delete(long id);
    void update(long id, String text);

}
