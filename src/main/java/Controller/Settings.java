package Controller;

import Controller.Validator.Validator;
import Model.Entity.User;
import Model.Implementation.UserServiceImpl;
import Model.Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/settings")
public class Settings extends HttpServlet {
    /**
     * Opens the jsp file with user's settings
     *
     * @param req - servlet request
     * @param resp - servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("duration");
        req.getSession().removeAttribute("date");
        req.getRequestDispatcher("/view/settings.jsp").forward(req, resp);

    }

    /**
     * Sends the inputted data to the model where it will be processed
     *
     * @param req - servlet request
     * @param resp - servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setSurname(req.getParameter("surname"));
        user.setEmail((String) httpSession.getAttribute("email"));
        user.setPassword(req.getParameter("password"));
        if(!Validator.nameCheck(user.getEmail()) && !Validator.nameCheck(user.getSurname()) && !Validator.passwordCheck(user.getPassword())){
            httpSession.setAttribute("ok", "false");
            resp.sendRedirect( "/settings");
        }
        UserService userService = new UserServiceImpl();
        if (userService.updateUser(user)) {
            httpSession.setAttribute("ok", "edited");
            httpSession.setAttribute("name", user.getName());
            httpSession.setAttribute("surname", user.getSurname());
            resp.sendRedirect("/");
        } else{
            httpSession.setAttribute("ok", "false");
            resp.sendRedirect("/settings");
        }
    }
}
