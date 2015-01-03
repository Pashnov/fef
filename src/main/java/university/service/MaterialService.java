package university.service;

import university.entity.Material;

import java.util.List;

/**
 * Created by Andrii_Pashnov on 23.12.2014 20:24.
 */
public interface MaterialService {

    Material read(long id);
    List<Material> findAll();
    Material create(Material material);
    byte[] getPhoto(long id);
    byte[] getFile(long id);
}
