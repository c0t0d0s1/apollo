package ru.arthur.webserver.filters;

import ru.arthur.webserver.model.User;
import ru.arthur.webserver.service.imple.UserServiceImpl;
import ru.arthur.webserver.service.interf.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {

//    private final static Logger log = Logger.getLogger(AdminFilter.class.getName());

    private final UserService userService = new UserServiceImpl();


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponce = (HttpServletResponse) response;

        System.out.println("фильтруем");
        if (httpRequest.getSession().getAttribute("role") != null && httpRequest.getSession().getAttribute("role").equals("admin")) {
            chain.doFilter(request, response);
            return;
        } else if (httpRequest.getSession().getAttribute("role") != null && httpRequest.getSession().getAttribute("role").equals("user")) {
            httpRequest.getRequestDispatcher("/user/main").forward(request, response);
            return;
        }




        User user = userService.get(request.getParameter("login"), request.getParameter("pass"));
        if (user != null && user.getRole().equals("user")) {
            httpRequest.getSession().setAttribute("role", "user");
            httpRequest.getRequestDispatcher("/user/main").forward(request, response);
            return;
        } else if (user == null && request.getParameter("login") == null) {
            httpResponce.sendRedirect("/#invalidUser");
//            log.info("У данного пользователя нету прав");

            return;
        }  else if (user == null) {
            httpResponce.sendRedirect("/#error");
//            log.info("Неправильно введен логин или пароль");
            return;
        }
        httpRequest.getSession().setAttribute("role", "admin");
        chain.doFilter(request, response);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
