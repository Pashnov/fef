package university.service.impl;

import university.dao.MaterialDao;
import university.entity.Material;
import university.service.MaterialService;
import university.transaction.TransactionOperation;
import university.transaction.TransactionManager;

import java.util.List;

/**
 * Created by Andrii_Pashnov on 23.12.2014 20:26.
 */
public class MaterialServiceImpl implements MaterialService {

    private TransactionManager manager;
    private MaterialDao dao;

    public MaterialServiceImpl(TransactionManager manager, MaterialDao dao) {
        this.manager = manager;
        this.dao = dao;
    }

    @Override
    public Material read(final long id) {
        return manager.doTransaction(new TransactionOperation<Material>() {
            @Override
            public Material execute() {
                return dao.read(id);
            }
        });
    }

    @Override
    public List<Material> findAll() {
        return manager.doTransaction(new TransactionOperation<List<Material>>() {
            @Override
            public List<Material> execute() {
                return dao.findAll();
            }
        });
    }

    @Override
    public Material create(final Material material) {
        return manager.doTransaction(new TransactionOperation<Material>() {
            @Override
            public Material execute() {
                return dao.create(material);
            }
        });
    }

    @Override
    public byte[] getPhoto(final long id) {
        return manager.doTransaction(new TransactionOperation<byte[]>() {
            @Override
            public byte[] execute() {
                return dao.getPhoto(id);
            }
        });
    }

    @Override
    public byte[] getFile(final long id) {
        return manager.doTransaction(new TransactionOperation<byte[]>() {
            @Override
            public byte[] execute() {
                return dao.getFile(id);
            }
        });
    }
}
