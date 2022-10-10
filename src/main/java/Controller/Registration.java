package Controller;

import Controller.Validator.Validator;
import Model.Entity.Language;
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

@WebServlet("/registration")
public class Registration extends HttpServlet {
    /**
     * Opens the jsp file with the registration page
     *
     * @param req  - servlet request
     * @param resp - servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/registration.jsp").forward(req, resp);
    }

    /**
     * Sends a new user's data to the model where it will be processed
     *
     * @param req  - servlet request
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
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        Language language = new Language();
        language.setId((Long) httpSession.getAttribute("lang"));
        user.setLanguage(language);
        UserService userService = new UserServiceImpl();
        if(!Validator.nameCheck(user.getName()) && !Validator.nameCheck(user.getSurname()) && !Validator.emailCheck(user.getEmail()) && !Validator.passwordCheck(user.getPassword())){
            httpSession.setAttribute("ok", "false");
            resp.sendRedirect("/registration");
        }
        if (userService.addUser(user)) {
            user = userService.getUserByEmail(user.getEmail());
            httpSession.setAttribute("id", user.getId());
            httpSession.setAttribute("name", user.getName());
            httpSession.setAttribute("surname", user.getSurname());
            httpSession.setAttribute("email", user.getEmail());
            httpSession.setAttribute("role", "User");
            httpSession.setAttribute("lang", user.getLanguage().getId());
            httpSession.setAttribute("ok", "true");
            if (req.getSession().getAttribute("role").equals("Admin")) {
                resp.sendRedirect("/admin/main");
            } else resp.sendRedirect("/?lang=" + user.getLanguage().getId());
        } else {
            httpSession.setAttribute("ok", "false");
            resp.sendRedirect("/registration");
        }
    }
}
