package university.web.servlet;

import org.apache.log4j.Logger;
import university.constant.AppConstant;
import university.entity.Material;
import university.service.MaterialService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

/**
 * Created by Andrii_Pashnov on 25.12.2014 21:32.
 */
@WebServlet("/createMaterial")
@MultipartConfig
public class CreateMaterialServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CreateMaterialServlet.class);

    private MaterialService materialService;

    @Override
    public void init() throws ServletException {
        materialService = (MaterialService)getServletContext().getAttribute(AppConstant.MATERIAL_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LOG.debug("doGet");
        req.getRequestDispatcher("/WEB-INF/jsp/createMaterial.jsp").forward(req, res);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LOG.debug("doPost");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        byte[] photo = getFile(req, "photo");
        String fileName = getFileName(req.getPart("file"));
        byte[] file = getFile(req, "file");
        Material material = new Material();
        material.setName(name);
        material.setDescription(description);
        material.setPhoto(photo);
        material.setFileName(fileName);
        material.setFile(file);
        materialService.create(material);
        res.sendRedirect("materials");
    }

    private String getFileName(Part part) {
        LOG.trace("getFileName");
        String partHeader = part.getHeader("content-disposition");
        LOG.trace("Part Header[0] = " + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private byte[] getFile(HttpServletRequest request, String nameParam) throws ServletException, IOException{
        LOG.debug("upload starts");
        InputStream is = request.getPart(nameParam).getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int read;
        while ((read = is.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        out.close();
        return out.toByteArray();
    }
}