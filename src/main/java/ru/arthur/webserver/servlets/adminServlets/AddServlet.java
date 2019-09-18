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

@WebServlet(urlPatterns = {"/admin/add"})
public class AddServlet extends HttpServlet {

//    private final static Logger log = Logger.getLogger(AddServlet.class.getName());

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            userService.add(new User(req.getParameter("name"),
                    req.getParameter("login"),
                    req.getParameter("pass"),
                    "user"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/admin/list");
    }

}
