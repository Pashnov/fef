package university.web.servlet;

import org.apache.log4j.Logger;
import university.entity.Group;
import university.service.GroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static university.constant.AppConstant.GROUP_SERVICE;
@WebServlet("/groups")
public class GroupsServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(GroupsServlet.class);

    private GroupService groupService;

    private final String IS_ACTIVE = "isActive";
    private final String GROUPS = "groups";

    @Override
    public void init() throws ServletException {
        groupService = (GroupService)getServletContext().getAttribute(GROUP_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("doGet");
        boolean isActive = getBoolean(request, IS_ACTIVE);
        List<Group> groups = groupService.findAll(isActive);
        request.setAttribute(GROUPS, groups);
        request.getRequestDispatcher("WEB-INF/jsp/groups.jsp").forward(request, response);
    }

    private boolean getBoolean(HttpServletRequest request, String nameParam){
        boolean value = true;
        try{
            if("false".equals(request.getParameter(nameParam))){
                value = false;
            }
        } catch (IllegalArgumentException e){
            LOG.warn("error cast param to boolean", e);
        }
        return value;
    }

}
