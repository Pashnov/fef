package university.service;

import university.entity.Lection;

public interface LectionService {

    Lection read(long id);
    String readText(long id);
    Lection create(Lection lection);
    void delete(long id);
    void update(long id, String text);

}
