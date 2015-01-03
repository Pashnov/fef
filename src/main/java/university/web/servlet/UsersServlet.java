package university.web.servlet;

import org.apache.log4j.Logger;
import university.entity.User;
import university.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static university.constant.AppConstant.USER_SERVICE;

/**
 * Created by Andrii_Pashnov on 26.12.2014 10:52.
 */
@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(UsersServlet.class);

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService)getServletContext().getAttribute(USER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LOG.debug("doGet");
        List<User> list = userService.findAll();
        req.setAttribute("users", list);
        req.getRequestDispatcher("/WEB-INF/jsp/users.jsp").forward(req,res);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LOG.debug("doPost");

    }
}