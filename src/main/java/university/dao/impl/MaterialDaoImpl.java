package university.dao.impl;


import org.apache.log4j.Logger;
import university.dao.MaterialDao;
import university.dao.entity.DaoStatementException;
import university.entity.Material;
import university.transaction.JdbcConnectionHolder;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii_Pashnov on 23.12.2014 19:38.
 */
public class MaterialDaoImpl implements MaterialDao {

    private static final Logger LOG = Logger.getLogger(MaterialDaoImpl.class);

    public static final String SQL_READ_MATERIAL_BY_ID = "SELECT * FROM material WHERE id = ?";

    public static final String SQL_READ_MATERIAL_PHOTO_BY_ID = "SELECT photo FROM material WHERE id = ?";

    public static final String SQL_READ_MATERIAL_FILE_BY_ID = "SELECT file FROM material WHERE id = ?";

    public static final String SQL_READ_ALL_MATERIALS = "SELECT * FROM material ";

    public static final String SQL_CREATE_MATERIAL = "INSERT INTO material (name, description, file_name, photo, file) VALUES (?, ?, ?, ?, ?)";

    @Override
    public Material read(long id) {
        LOG.debug("read, id = " + id);
        ResultSet resultSet;
        Connection connection = JdbcConnectionHolder.get();
        try(PreparedStatement ps = connection.prepareStatement(SQL_READ_MATERIAL_BY_ID)) {
            ps.setLong(1, id);
            resultSet = ps.executeQuery();
            if(resultSet.next()){
               return extractMaterial(resultSet);
            }
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException();
        }
        return null;
    }

    @Override
    public List<Material> findAll() {
        LOG.debug("findAll");
        List<Material> list = new ArrayList<>();;
        ResultSet resultSet;
        Connection connection = JdbcConnectionHolder.get();
        try(Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(SQL_READ_ALL_MATERIALS);
            while (resultSet.next()){
                list.add(extractMaterial(resultSet));
            }
            return list;
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException();
        }
    }

    @Override
    public Material create(Material material) {
        LOG.debug("create, material = " + material);
        ResultSet rs;
        Connection connection = JdbcConnectionHolder.get();
        try(PreparedStatement ps = connection.prepareStatement(SQL_CREATE_MATERIAL, Statement.RETURN_GENERATED_KEYS)) {
            int k = 1;
            ps.setString(k++, material.getName());
            ps.setString(k++, material.getDescription());
            ps.setString(k++, material.getFileName());
            ps.setBlob(k++, new SerialBlob(material.getPhoto()));
            ps.setBlob(k++, new SerialBlob(material.getFile()));
            if (ps.executeUpdate() > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    material.setId(rs.getInt(1));
                    return material;
                }
            }
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);

        }
        throw new DaoStatementException("don't create material");
    }

    @Override
    public byte[] getPhoto(long id) {
        LOG.debug("getPhoto, id = " + id);
        ResultSet resultSet;
        Connection connection = JdbcConnectionHolder.get();
        try(PreparedStatement ps = connection.prepareStatement(SQL_READ_MATERIAL_PHOTO_BY_ID)) {
            ps.setLong(1, id);
            resultSet = ps.executeQuery();
            if(resultSet.next()){
                return resultSet.getBytes(1);
            }
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);
        }
        return new byte[0];
    }

    @Override
    public byte[] getFile(long id) {
        LOG.debug("getFile, id = " + id);
        ResultSet resultSet;
        Connection connection = JdbcConnectionHolder.get();
        try(PreparedStatement ps = connection.prepareStatement(SQL_READ_MATERIAL_FILE_BY_ID)) {
            ps.setLong(1, id);
            resultSet = ps.executeQuery();
            if(resultSet.next()){
                return resultSet.getBytes(1);
            }
        } catch (SQLException e) {
            LOG.warn(e);
            throw new DaoStatementException(e);
        }
        return new byte[0];
    }

    private Material extractMaterial(ResultSet resultSet) throws SQLException {
        Material material = new Material();
        material.setId(resultSet.getLong("id"));
        material.setName(resultSet.getString("name"));
        material.setDescription(resultSet.getString("description"));
        material.setFileName(resultSet.getString("file_name"));
        return material;
    }
}
