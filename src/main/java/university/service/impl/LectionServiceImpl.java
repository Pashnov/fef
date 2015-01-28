package university.service.impl;

import university.dao.LectionDao;
import university.entity.Lection;
import university.service.LectionService;
import university.transaction.TransactionOperation;
import university.transaction.TransactionManager;

import java.util.List;

public class LectionServiceImpl implements LectionService {

    private TransactionManager manager;
    private LectionDao lectionDao;

    public LectionServiceImpl(TransactionManager manager, LectionDao lectionDao) {
        this.manager = manager;
        this.lectionDao = lectionDao;
    }

    @Override
    public Lection read(final long id) {
        return manager.doTransaction(new TransactionOperation<Lection>() {
            @Override
            public Lection execute() {
                return lectionDao.read(id);
            }
        });
    }

    @Override
    public String readText(final long id) {
        return manager.doTransaction(new TransactionOperation<String>() {
            @Override
            public String execute() {
                return lectionDao.readText(id);
            }
        });
    }

    @Override
    public Lection create(final Lection lection) {
        return manager.doTransaction(new TransactionOperation<Lection>() {
            @Override
            public Lection execute() {
                String defaultValue = "<math xmlns='http://www.w3.org/1998/Math/MathML'/>";
                Lection lection1 =  lectionDao.create(lection);
                lectionDao.update(lection1.getId(), defaultValue);
                return lection1;
            }
        });
    }

    @Override
    public void delete(final long id) {
        manager.doTransaction(new TransactionOperation<Object>() {
            @Override
            public Object execute() {
                lectionDao.delete(id);
                return null;
            }
        });
    }

    @Override
    public void update(final long id, final String text) {
        manager.doTransaction(new TransactionOperation<Object>() {
            @Override
            public Object execute() {
                lectionDao.update(id, text);
                return null;
            }
        });
    }

    @Override
    public List<Lection> findAll() {
        return manager.doTransaction(new TransactionOperation<List<Lection>>() {
            @Override
            public List<Lection> execute() {
                return lectionDao.findAll();
            }
        });
    }
}
