package university.web.listener;

/**
 * Created by Andrii_Pashnov on 23.12.2014 19:23.
 */

import org.apache.log4j.Logger;
import university.dao.GroupDao;
import university.dao.LectionDao;
import university.dao.MaterialDao;
import university.dao.UserDao;
import university.dao.impl.GroupDaoImpl;
import university.dao.impl.LectionDaoImpl;
import university.dao.impl.MaterialDaoImpl;
import university.dao.impl.UserDaoImpl;
import university.service.GroupService;
import university.service.LectionService;
import university.service.MaterialService;
import university.service.UserService;
import university.service.impl.GroupServiceImpl;
import university.service.impl.LectionServiceImpl;
import university.service.impl.MaterialServiceImpl;
import university.service.impl.UserServiceImpl;
import university.transaction.TransactionManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import static university.constant.AppConstant.*;


/**
 * In this app listener init FactoryCaptcha and running Captcha Collection.
 * If in web.xml set not correct param, then load default FactoryCaptcha
 */
public class AppServletContextListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(AppServletContextListener.class);

    private TransactionManager manager;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOG.info("start init AppServletContextListener");

        manager = new TransactionManager(initDataSource());

        MaterialDao materialDao = new MaterialDaoImpl();
        MaterialService materialService = new MaterialServiceImpl(manager, materialDao);
        servletContextEvent.getServletContext().setAttribute(MATERIAL_SERVICE, materialService);

        UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceImpl(manager, userDao);
        servletContextEvent.getServletContext().setAttribute(USER_SERVICE, userService);

        GroupDao groupDao = new GroupDaoImpl();
        GroupService groupService = new GroupServiceImpl(manager, groupDao, userDao);
        servletContextEvent.getServletContext().setAttribute(GROUP_SERVICE, groupService);

        LectionDao lectionDao = new LectionDaoImpl();
        LectionService lectionService = new LectionServiceImpl(manager, lectionDao);
        servletContextEvent.getServletContext().setAttribute(LECTION_SERVICE, lectionService);

        LOG.info("finish init AppServletContextListener");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOG.info("start destroy AppServletContextListener");
        LOG.info("finish destroy AppServletContextListener");
    }

    private DataSource initDataSource(){
        LOG.info("initDataSource");
        DataSource ds;
        try {
            InitialContext initialContext = new InitialContext();
            ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/fef");
        } catch (NamingException e) {
            LOG.warn(e);
            throw new Error(e);
        }
        return ds;
    }



}

