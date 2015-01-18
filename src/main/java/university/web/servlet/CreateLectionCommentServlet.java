package university.web.servlet;

import org.apache.log4j.Logger;
import university.constant.user.UserRole;
import university.entity.LectionComment;
import university.entity.User;
import university.service.LectionCommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static university.constant.AppConstant.LECTION_COMMENT_SERVICE;

@WebServlet("/createLectionComment")
public class CreateLectionCommentServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CreateLectionCommentServlet.class);

    private LectionCommentService commentService;

    private final String USER = "user";
    private final String LECTION_ID = "lectionId";
    private final String TEXT = "text";

    @Override
    public void init() throws ServletException {
        commentService = (LectionCommentService)getServletContext().getAttribute(LECTION_COMMENT_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("doPost");
        Long lectionId = Long.valueOf(request.getParameter(LECTION_ID));
        LectionComment comment = new LectionComment();
        User user = (User)request.getSession().getAttribute(USER);
        String text = request.getParameter(TEXT);
        comment.setLectionId(lectionId);
        comment.setUser(user);
        comment.setText(text);
        commentService.create(comment);
        String path = "lectionView?lectionId=";
        if(user.getRole().equals(UserRole.LECTURER)){
            path = "lection?lectionId=";
        }
        response.sendRedirect(path+lectionId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
