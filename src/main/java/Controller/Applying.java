package Controller;

import Model.Implementation.ApplicationServiceImpl;
import Model.Entity.Application;
import Model.Entity.Cruise;
import Model.Entity.User;
import Model.Service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/applying")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class Applying extends HttpServlet {

    /**
     * Removes the redundant search's attributes and opens the jsp file with
     * the application form to fill it with a passport photo
     *
     * @param req  - servlet request
     * @param resp - servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        httpSession.removeAttribute("duration");
        httpSession.removeAttribute("date");
        httpSession.setAttribute("cruise", Long.parseLong(req.getParameter("cruise")));
        req.getRequestDispatcher("/view/application.jsp").forward(req, resp);
    }

    /**
     * Send to the model the photo that was sent by the user and creates a new application
     *
     * @param req  - servlet request
     * @param resp - servlet response
     * @throws ServletException
     * @throws IOException
     * @see Application
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");
        InputStream inputStream = filePart.getInputStream();
        HttpSession httpSession = req.getSession();
        Application application = new Application();
        User user = new User();
        Cruise cruise = new Cruise();
        cruise.setId((Long) httpSession.getAttribute("cruise"));
        user.setId((Long) httpSession.getAttribute("id"));
        application.setUser(user);
        application.setCruise(cruise);
        application.setPass_photo(inputStream);
        ApplicationService applicationService = new ApplicationServiceImpl();
        if (applicationService.addApplication(application)) {
            httpSession.setAttribute("ok", "app_sent");
        } else httpSession.setAttribute("ok", "not_sent");
        httpSession.removeAttribute("cruise");
        resp.sendRedirect("/applicationList");
    }
}
