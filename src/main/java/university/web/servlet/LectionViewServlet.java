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
import java.io.PrintWriter;

import static university.constant.AppConstant.LECTION_SERVICE;

@WebServlet("/lectionView")
public class LectionViewServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(LectionViewServlet.class);

    private LectionService lectionService;

    private static final String LECTION = "lection";

    @Override
    public void init() throws ServletException {
        lectionService = (LectionService)getServletContext().getAttribute(LECTION_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.valueOf(request.getParameter("lectionId"));
        LOG.debug("id = " + id);
        String text = lectionService.readText(id);
        text = text.replace("xmlns=\"http://www.w3.org/1998/Math/MathML\"", "");
//        LOG.debug("text = " + text);
        String jsonText = createJson(text);
        response.setContentType("json");
        PrintWriter out = response.getWriter();
        out.print(jsonText);
    }

    private String createJson(String text){
        StringBuilder sb = new StringBuilder();
        sb.append("{").append(" ")
                .append("\"text\"").append(":")
                .append("\"").append(text).append("\"")
                .append(" ").append("}");
        return sb.toString();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("doGet");
        long id = Long.valueOf(request.getParameter("lectionId"));
        Lection lection = lectionService.read(id);
        request.setAttribute(LECTION, lection);
        request.getRequestDispatcher("/WEB-INF/jsp/lectionView.jsp").forward(request, response);
    }
}
