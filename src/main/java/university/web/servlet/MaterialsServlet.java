package university.web.servlet;

import org.apache.log4j.Logger;
import university.constant.AppConstant;
import university.entity.Material;
import university.service.MaterialService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Andrii_Pashnov on 23.12.2014 19:32.
 */
@WebServlet("/materials")
public class MaterialsServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(MaterialsServlet.class);

    private MaterialService materialService;

    @Override
    public void init() throws ServletException {
        materialService = (MaterialService)getServletContext().getAttribute(AppConstant.MATERIAL_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LOG.debug("doGet");
        LOG.debug("materialService = " + materialService);
        List<Material> materials = materialService.findAll();
        req.setAttribute("materials", materials);
        req.getRequestDispatcher("/WEB-INF/jsp/materials.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LOG.debug("doPost");

    }
}