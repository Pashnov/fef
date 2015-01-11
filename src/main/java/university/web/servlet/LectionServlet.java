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

import static university.constant.AppConstant.LECTION_SERVICE;

@WebServlet("/lection")
public class LectionServlet extends HttpServlet {
    
    private static final Logger LOG = Logger.getLogger(LectionServlet.class);
    
    private LectionService lectionService;

    private static final String LECTION = "lection";

    @Override
    public void init() throws ServletException {
        lectionService = (LectionService)getServletContext().getAttribute(LECTION_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("doPost");
        long id = Long.valueOf(request.getParameter("lectionId"));
        String text = request.getParameter("text");
        lectionService.update(id,text);
        response.setContentType("json");
        response.getWriter().print("{ \"param\":\"true\"");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("doGet");
        long id = Long.valueOf(request.getParameter("lectionId"));
        Lection lection = lectionService.read(id);
        request.setAttribute(LECTION, lection);
        request.getRequestDispatcher("/WEB-INF/jsp/lection.jsp").forward(request, response);
    }
}
