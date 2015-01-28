package university.web.filter;

import org.apache.log4j.Logger;
import university.entity.User;
import university.util.access.SaxHandler;
import university.util.access.entity.Constraint;
import university.util.access.entity.Security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AccessFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(AccessFilter.class);
    private static final String LOGIN_PAGE = "/login";

    private final String xmlFileName = "user_access.xml";
    private Security security;

    public void init(FilterConfig config) throws ServletException {
        InputStream is = AccessFilter.class.getClassLoader().getResourceAsStream(xmlFileName);
        SaxHandler controller = new SaxHandler(is);
        try{
            controller.parse(true);
        } catch (Exception e) {
            LOG.error("check file 'user_access.xml'", e);
            throw new RuntimeException("check file 'user_access.xml'", e);
        }
        security = controller.getSecurity();
        LOG.info("security = " + security);
        LOG.info("AccessFilter init");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        User user = (User)request.getSession().getAttribute("user");
        String urlRequest = request.getServletPath();
        LOG.debug("1 = " + urlRequest);
        AccessType accessType = getAccessType(security, urlRequest, user);
        switch (accessType){
            case REDIRECT:
                response.sendRedirect("login");
                return;
            case TRUE:
                chain.doFilter(req, resp);
                return;
            case FALSE:
                request.getRequestDispatcher("/WEB-INF/jsp/errorAccess.jsp").forward(request, response);
        }
    }

    public void destroy() {
    }

    private AccessType getAccessType(Security security, String urlRequest, User user){
        for (Constraint constraint : security.getConstraints()) {
            String regexp = constraint.getUrlPattern();
            if(urlRequest.matches(regexp)){
                if(user != null) {
                    List<String> roles = constraint.getRoles();
                    String role = user.getRole().name().toLowerCase();
                    if (roles.contains(role)) {
                        return AccessType.TRUE;
                    } else {
                        return AccessType.FALSE;
                    }
                }else {
                    if(urlRequest.equals(LOGIN_PAGE)){
                        return AccessType.TRUE;
                    }
                    return AccessType.REDIRECT;
                }
            }
        }
        return AccessType.TRUE;
    }

    private enum AccessType{
        TRUE, FALSE, REDIRECT;
    }
}
