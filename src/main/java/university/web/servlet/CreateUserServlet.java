package university.web.servlet;

import org.apache.log4j.Logger;
import university.constant.user.UserRole;
import university.entity.User;
import university.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static university.constant.AppConstant.USER_SERVICE;

@WebServlet("/createUser")
public class CreateUserServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CreateUserServlet.class);

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService)getServletContext().getAttribute(USER_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("doPost");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        UserRole role = UserRole.getRole(request.getParameter("role"));
        userService.create(new User(firstName, lastName, role));
        response.sendRedirect("users");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("doGet");
        request.getRequestDispatcher("/WEB-INF/jsp/createUser.jsp").forward(request, response);
    }
}
