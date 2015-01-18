package university.web.servlet;

import org.apache.log4j.Logger;
import university.entity.Lection;
import university.entity.LectionComment;
import university.service.LectionCommentService;
import university.service.LectionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static university.constant.AppConstant.LECTION_COMMENT_SERVICE;
import static university.constant.AppConstant.LECTION_SERVICE;

@WebServlet("/lection")
public class LectionServlet extends HttpServlet {
    
    private static final Logger LOG = Logger.getLogger(LectionServlet.class);
    
    private LectionService lectionService;
    private LectionCommentService commentService;

    private final String LECTION = "lection";
    private final String LIST_COMMENT = "listComment";

    @Override
    public void init() throws ServletException {
        lectionService = (LectionService)getServletContext().getAttribute(LECTION_SERVICE);
        commentService = (LectionCommentService)getServletContext().getAttribute(LECTION_COMMENT_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("doPost");
        long id = Long.valueOf(request.getParameter("lectionId"));
        String text = request.getParameter("text");
        text = text.replace("\"", "'");
        lectionService.update(id,text);
        response.setContentType("json");
        response.getWriter().print("{ \"param\":\"true\"");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("doGet");
        long lectionId = Long.valueOf(request.getParameter("lectionId"));
        Lection lection = lectionService.read(lectionId);
        List<LectionComment> listComment = commentService.findAllByLectionId(lectionId);
        request.setAttribute(LECTION, lection);
        request.setAttribute(LIST_COMMENT, listComment);
        request.getRequestDispatcher("/WEB-INF/jsp/lection.jsp").forward(request, response);
    }
}
