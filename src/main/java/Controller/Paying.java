package Controller;

import Model.Implementation.ApplicationServiceImpl;
import Model.Service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pay")
public class Paying extends HttpServlet {
    /**
     * Sends an application to the model where it's state will be changed to "paid"
     *
     * @param req  - servlet request
     * @param resp - servlet esponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationService applicationService = new ApplicationServiceImpl();
        if (applicationService.payApplication((Long) req.getSession().getAttribute("lang"), Long.parseLong(req.getParameter("app_id")))) {
            req.getSession().setAttribute("ok", "app_paid");
        } else req.getSession().setAttribute("ok", "not_paid");
        resp.sendRedirect("/applicationList");
    }
}
