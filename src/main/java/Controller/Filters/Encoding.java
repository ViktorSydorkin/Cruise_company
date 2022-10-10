package Controller.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", initParams = @WebInitParam(name = "encoding", value = "UTF-8"))
public class Encoding implements Filter{
    String encoding;

    /**
     * Inits the encoding
     *
     * @param filterConfig - filter configuration
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    /**
     * Sets the character encoding
     *
     * @param servletRequest - servlet request
     * @param servletResponse - servlet response
     * @param filterChain - chain of filters
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
