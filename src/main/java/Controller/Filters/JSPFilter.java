package Controller.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class JSPFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if(req.getServletPath()!=null && req.getServletPath().endsWith(".jsp")) {
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            resp.sendRedirect("/error");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
