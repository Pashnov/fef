package university.web.servlet;

import org.apache.log4j.Logger;
import university.constant.user.YearOfStudy;
import university.entity.User;
import university.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static university.constant.AppConstant.USER_SERVICE;

@WebServlet("/myInfo")
public class MyInfoServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(MyInfoServlet.class);

    private UserService userService;

    private final String EMAIL = "email";
    private final String PASSWORD = "password";
    private final String PASSWORD_CONFIRM = "passwordConfirm";
    private final String YEAR_OF_STUDY = "yearOfStudy";


    @Override
    public void init() throws ServletException {
        userService = (UserService)getServletContext().getAttribute(USER_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String password = request.getParameter(PASSWORD);
        String passwordConfirm = request.getParameter(PASSWORD_CONFIRM);
        if(password.equals(passwordConfirm)){
            user.setEmail(request.getParameter(EMAIL));
            user.setPassword(request.getParameter(PASSWORD));
            user.setYearOfStudy(YearOfStudy.getCourse(request.getParameter(YEAR_OF_STUDY)));
            userService.update(user);
        }
        response.sendRedirect("myInfo");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/myInfo.jsp").forward(request, response);
    }
}
