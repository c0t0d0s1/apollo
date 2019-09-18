package ru.arthur.webserver.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/user/*"})
public class UserFilter implements Filter {

//    private final static Logger log = Logger.getLogger(UserFilter.class.getName());

//    private final UserService userService = new UserServiceImpl();


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponce = (HttpServletResponse) response;
        try {
            if (httpRequest.getSession().getAttribute("role") != null && httpRequest.getSession().getAttribute("role").equals("user") || httpRequest.getSession().getAttribute("role").equals("admin")) {
                chain.doFilter(request, response);
            }
        } catch (Exception e) {

        } finally {
            httpResponce.sendRedirect("/#invalidUser");
//            log.info("У данного пользователя нету прав");
        }

    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
