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

@WebServlet("/login")
public class Login extends HttpServlet {
    /**
     * Removes a redundant info from the session. Opens the login jsp file
     *
     * @param req - servlet request
     * @param resp - servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession()!=null){
            HttpSession httpSession = req.getSession();
            httpSession.removeAttribute("duration");
            httpSession.removeAttribute("date");
            httpSession.removeAttribute("id");
            httpSession.removeAttribute("name");
            httpSession.removeAttribute("surname");
            httpSession.removeAttribute("email");
            httpSession.removeAttribute("role");
        }
        req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
    }

    /**
     * Sends info to model to check whether the user exists.
     * If exists - writes the user data to the session
     *
     * @param req - servlet request
     * @param resp - servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user;
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserService userService = new UserServiceImpl();
        HttpSession httpSession = req.getSession();
        if(!Validator.emailCheck(email) && !Validator.passwordCheck(password)){
            httpSession.setAttribute("ok", "false");
            resp.sendRedirect("/login");
        }
        user = userService.getUserByEmailAndPassword(email,password);

        if (user != null) {
            httpSession.setAttribute("role", user.getRole().getType());
            httpSession.setAttribute("id", user.getId());
            httpSession.setAttribute("name", user.getName());
            httpSession.setAttribute("surname", user.getSurname());
            httpSession.setAttribute("email", user.getEmail());
            httpSession.setAttribute("lang", user.getLanguage().getId());
            if(req.getSession().getAttribute("role").equals("Admin")) {
                resp.sendRedirect("/admin/main");
            }else resp.sendRedirect("/?lang=" + user.getLanguage().getId());
        } else {
            httpSession.setAttribute("ok", "false");
            resp.sendRedirect("/login");
        }
    }
}
