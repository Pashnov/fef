package university.service;

import university.entity.LectionComment;

import java.util.List;

public interface LectionCommentService {

    LectionComment create(LectionComment comment);
    void update(long lectionCommentId, String text);
    List<LectionComment> findAllByLectionId(long lectionId);
    void delete(long lectionCommentId);
}
