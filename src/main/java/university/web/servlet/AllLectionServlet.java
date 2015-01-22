package university.web.servlet;

import org.apache.log4j.Logger;
import university.entity.Lection;
import university.service.LectionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static university.constant.AppConstant.LECTION_SERVICE;

/**
 * Created by Andrii_Pashnov on 19.01.2015 19:05.
 */
@WebServlet("/lections")
public class AllLectionServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(AllLectionServlet.class);

    private LectionService lectionService;

    @Override
    public void init() throws ServletException {
        lectionService = (LectionService)getServletContext().getAttribute(LECTION_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LOG.debug("doGet");
        List<Lection> list = lectionService.findAll();
        req.setAttribute("lections", list);
        req.getRequestDispatcher("/WEB-INF/jsp/lections.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LOG.debug("doPost");

    }
}