package university.service.impl;

import university.dao.LectionCommentDao;
import university.entity.LectionComment;
import university.service.LectionCommentService;
import university.transaction.TransactionManager;
import university.transaction.TransactionOperation;

import java.util.List;

public class LectionCommentServiceImpl implements LectionCommentService {

    private TransactionManager manager;
    private LectionCommentDao lectionCommentDao;

    public LectionCommentServiceImpl(TransactionManager manager, LectionCommentDao lectionCommentDao) {
        this.manager = manager;
        this.lectionCommentDao = lectionCommentDao;
    }

    @Override
    public LectionComment create(final LectionComment comment) {
        return manager.doTransaction(new TransactionOperation<LectionComment>() {
            @Override
            public LectionComment execute() {
                return lectionCommentDao.create(comment);
            }
        });
    }

    @Override
    public void update(final long lectionCommentId, final String text) {
        manager.doTransaction(new TransactionOperation<Object>() {
            @Override
            public Object execute() {
                lectionCommentDao.updateText(lectionCommentId, text);
                return null;
            }
        });
    }

    @Override
    public List<LectionComment> findAllByLectionId(final long lectionId) {
        return manager.doTransaction(new TransactionOperation<List<LectionComment>>() {
            @Override
            public List<LectionComment> execute() {
                return lectionCommentDao.findAllByLectionId(lectionId);
            }
        });
    }

    @Override
    public void delete(final long lectionCommentId) {
        manager.doTransaction(new TransactionOperation<Object>() {
            @Override
            public Object execute() {
                lectionCommentDao.delete(lectionCommentId);
                return null;
            }
        });
    }
}
