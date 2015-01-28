package university.web.servlet;

import org.apache.log4j.Logger;
import university.entity.Group;
import university.entity.User;
import university.service.GroupService;
import university.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static university.constant.AppConstant.GROUP_SERVICE;
import static university.constant.AppConstant.USER_SERVICE;

@WebServlet("/createGroup")
public class CreateGroupServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CreateGroupServlet.class);

    private UserService userService;
    private GroupService groupService;

    @Override
    public void init() throws ServletException {
        userService = (UserService)getServletContext().getAttribute(USER_SERVICE);
        groupService = (GroupService)getServletContext().getAttribute(GROUP_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("doPost");
        String name = request.getParameter("name");
        List<User> students = getSimpleListUser(request);
        Group group = new Group(name, students);
        groupService.create(group);
        response.sendRedirect("groups");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("doGet");
        List<User> freeStudents = userService.findFreeStudent();
        request.setAttribute("freeStudents", freeStudents);
        request.getRequestDispatcher("/WEB-INF/jsp/createGroup.jsp").forward(request, response);
    }

    private List<User> getSimpleListUser(HttpServletRequest request){
        List<User> list = new ArrayList<>();
        String[] strIds = request.getParameterValues("id");
        for (String strId : strIds) {
            long id = 0;
            try{
               id =  Long.valueOf(strId);
            }catch (NumberFormatException e){
                LOG.warn(e);
            }
            if(id != 0){
                User user = new User();
                user.setId(id);
                list.add(user);
            }
        }
        return list;
    }
}
