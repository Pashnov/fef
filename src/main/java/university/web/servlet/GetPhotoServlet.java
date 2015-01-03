package university.web.servlet;

import org.apache.log4j.Logger;
import university.constant.AppConstant;
import university.service.MaterialService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Andrii_Pashnov on 25.12.2014 21:00.
 */
@WebServlet("/getPhoto")
public class GetPhotoServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(GetPhotoServlet.class);

    private MaterialService materialService;

    @Override
    public void init() throws ServletException {
        materialService = (MaterialService)getServletContext().getAttribute(AppConstant.MATERIAL_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LOG.debug("doGet");
        long id = Long.valueOf(req.getParameter("id"));
        byte[] photo = materialService.getPhoto(id);
        res.setContentType("image/png");
        OutputStream os = res.getOutputStream();
//        ImageIO.write(bufferedImage, "png", os);
        os.write(photo);
        os.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LOG.debug("doPost");

    }
}