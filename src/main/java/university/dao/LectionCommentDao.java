package university.dao;

import university.entity.LectionComment;

import java.util.List;

public interface LectionCommentDao {

    LectionComment create(LectionComment comment);
    void updateText(long lectionCommentId, String text);
    List<LectionComment> findAllByLectionId(long lectionId);
    void delete(long lectionCommentId);
}
