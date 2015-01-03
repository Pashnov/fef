package university.web.servlet;

import org.apache.log4j.Logger;
import university.entity.User;
import university.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static university.constant.AppConstant.USER_SERVICE;

/**
 * Created by Andrii_Pashnov on 26.12.2014 12:38.
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(UserServlet.class);

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService)getServletContext().getAttribute(USER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LOG.debug("doGet");
        long idUser = Long.valueOf(req.getParameter("id"));
        User user = userService.read(idUser);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/jsp/user.jsp").forward(req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LOG.debug("doPost");

    }
}