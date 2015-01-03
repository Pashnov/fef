package university.dao;

import university.entity.Material;

import java.util.List;

/**
 * Created by Andrii_Pashnov on 23.12.2014 19:33.
 */
public interface MaterialDao {

    Material read(long id);
    List<Material> findAll();
    Material create(Material material);
    byte[] getPhoto(long id);
    byte[] getFile(long id);

}
