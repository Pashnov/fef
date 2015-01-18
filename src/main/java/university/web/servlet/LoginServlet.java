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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(LoginServlet.class);

    private UserService userService;

    private static final String MESSAGE_FOR_LOGIN = "messageForLogin";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String PASSWORD = "password";


    @Override
    public void init() throws ServletException {
        userService = (UserService)getServletContext().getAttribute(USER_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("doPost");
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        User user = userService.read(firstName, lastName);
        if(user != null){
            String password = request.getParameter(PASSWORD);
            if(checkPassword(password, user)){
                request.getSession().setAttribute("user", user);
                response.sendRedirect("");
                return;
            }
        }
        request.setAttribute(MESSAGE_FOR_LOGIN, true);
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("doGet");
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    private boolean checkPassword(String password, User user){
        boolean isCorrectPassword = false;
        if(password == null && user.getPassword() == null){
            isCorrectPassword = true;
        }
        if(password != null && password.isEmpty() && user.getPassword() == null){
            isCorrectPassword = true;
        }
        if(user.getPassword() != null && user.getPassword().isEmpty() && password == null){
            isCorrectPassword = true;
        }
        if(password != null && password.equals(user.getPassword())){
            isCorrectPassword = true;
        }
        return isCorrectPassword;
    }
}
