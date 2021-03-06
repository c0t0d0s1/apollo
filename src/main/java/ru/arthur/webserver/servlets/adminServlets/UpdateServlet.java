package ru.arthur.webserver.servlets.adminServlets;

import ru.arthur.webserver.model.User;
import ru.arthur.webserver.service.imple.UserServiceImpl;
import ru.arthur.webserver.service.interf.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/update"})
public class UpdateServlet extends HttpServlet {

//    private final static Logger log = Logger.getLogger(UpdateServlet.class.getName());

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
//            log.info("update");
            userService.update(new User(Integer.parseInt(req.getParameter("id")),
                        req.getParameter("name"),
                        req.getParameter("login"),
                        req.getParameter("pass"),
                        req.getParameter("role")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/admin/list");
    }
}
