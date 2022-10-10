package Controller.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class Admin implements Filter {
    /**
     * Filters requests denying any user to access the admin page
     *
     * @param servletRequest - servlet request
     * @param servletResponse - servlet response
     * @param filterChain - chain of filters
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("role")==null || httpSession.getAttribute("role").equals("User")) {
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            resp.sendRedirect("/error");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
