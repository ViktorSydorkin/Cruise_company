package Controller;

import Model.Implementation.ApplicationServiceImpl;
import Model.Entity.Application;
import Model.Service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/applicationList")
public class Applications extends HttpServlet {
    /**
     * Opens the jsp file with the applications list that was received from the model
     *
     * @param req  - servlet request
     * @param resp - servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("duration");
        req.getSession().removeAttribute("date");
        ApplicationService applicationService = new ApplicationServiceImpl();
        List<Application> applicationList = applicationService.getApplicationsForUser((Long) req.getSession().getAttribute("lang"), (Long) req.getSession().getAttribute("id"));
        req.setAttribute("applicationList", applicationList);
        req.getRequestDispatcher("/view/my_applications.jsp").forward(req, resp);
    }
}
