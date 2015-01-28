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

import static university.constant.AppConstant.GROUP_SERVICE;

/**
 * Created by Andrii_Pashnov on 26.12.2014 14:58.
 */
@WebServlet("/group")
public class GroupServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(GroupServlet.class);

    private GroupService groupService;

    @Override
    public void init() throws ServletException {
        groupService = (GroupService)getServletContext().getAttribute(GROUP_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LOG.debug("doGet");
        Long idGroup = Long.valueOf(req.getParameter("groupId"));
        Group group = groupService.read(idGroup);
        req.setAttribute("group", group);
        req.getRequestDispatcher("/WEB-INF/jsp/group.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LOG.debug("doPost");

    }
}