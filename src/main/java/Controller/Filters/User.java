package Controller.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/applicationList", "/settings"})
public class User implements Filter {
    /**
     * Filters requests denying any admin to access the user page
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
        if (httpSession.getAttribute("role")==null || httpSession.getAttribute("role").equals("Admin")) {
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            resp.sendRedirect("/error");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
