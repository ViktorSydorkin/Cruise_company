package Controller;

import Model.Entity.Liner;
import Model.Implementation.LinerServiceImpl;
import Model.Service.LinerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/linerPhoto")
public class LinerPhoto extends HttpServlet {
    /**
     * Opens a liner photo in a new tab
     *
     * @param req - servlet request
     * @param resp - servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LinerService linerService = new LinerServiceImpl();
        Liner liner = linerService.getLinerById(Long.parseLong(req.getParameter("liner")));
        InputStream photo = liner.getLiner_photo();
        resp.setHeader("Content-Type", "image");
        resp.getOutputStream().write(photo.readAllBytes());
    }
}
