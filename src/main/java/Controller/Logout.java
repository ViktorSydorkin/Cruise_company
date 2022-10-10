package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/logout")
public class Logout extends HttpServlet {
    /**
     * Removes the whole information in the session and forwards to the login servlet
     *
     * @param req - servlet request
     * @param resp - servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession() != null) req.getSession().invalidate();
        resp.sendRedirect("/login");
    }
}
