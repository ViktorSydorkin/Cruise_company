package Controller;

import Model.Implementation.CruiseServiceImpl;
import Model.Entity.Cruise;
import Model.Entity.Language;
import Model.Entity.User;
import Model.Implementation.UserServiceImpl;
import Model.Service.CruiseService;
import Model.Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/")
public class Index extends HttpServlet {
    /**
     * Clears the search's attributes if they were saved in session.
     * According to the lang attribute or its emptiness calls
     * the function with the exact arguments
     *
     * @param req  - servlet request
     * @param resp - servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        if (req.getParameter("clear") != null) {
            httpSession.removeAttribute("duration");
            httpSession.removeAttribute("date");
        }
        if (req.getParameter("lang") != null)
            httpSession.setAttribute("lang", Long.parseLong(req.getParameter("lang")));
        if (httpSession.getAttribute("name") != null) {
            index(req, resp, (Long) httpSession.getAttribute("lang"));
        } else if (httpSession.getAttribute("lang") == null || (Long) httpSession.getAttribute("lang") == 1) {
            index(req, resp, 1);
        } else {
            index(req, resp, 2);
        }
    }

    /**
     * Implements the pagination realization.
     *
     * @param req     - servlet request
     * @param resp    - servlet response
     * @param lang_id - id of language the selected cruises should be
     * @throws ServletException
     * @throws IOException
     */
    private void index(HttpServletRequest req, HttpServletResponse resp, long lang_id) throws ServletException, IOException {
        final int AMOUNT_OF_ITEMS_ON_PAGE = 4;
        HttpSession httpSession = req.getSession();
        if (req.getParameter("duration") != null)
            if (!req.getParameter("duration").equals(""))
                httpSession.setAttribute("duration", Integer.parseInt(req.getParameter("duration")));
        if (req.getParameter("date") != null)
            if (!req.getParameter("date").equals(""))
                httpSession.setAttribute("date", req.getParameter("date").replace("T", " ") + ":00");
        CruiseService cruiseService = new CruiseServiceImpl();
        httpSession.setAttribute("lang", lang_id);
        int page;
        if (req.getParameter("page") == null)
            page = 0;
        else
            page = Integer.parseInt(req.getParameter("page"));
        int start = page * AMOUNT_OF_ITEMS_ON_PAGE;
        if (httpSession.getAttribute("duration") != null) {
            List<Cruise> cruiseList = cruiseService.getCruiseByDuration(lang_id, start, AMOUNT_OF_ITEMS_ON_PAGE, (Integer) httpSession.getAttribute("duration"));
            if (cruiseList.size() == 0)
                httpSession.setAttribute("ok", "nothing");
            req.setAttribute("cruiseList", cruiseList);
            req.setAttribute("count", cruiseService.getCruiseAmountDuration(lang_id, (Integer) httpSession.getAttribute("duration")));
        } else if (httpSession.getAttribute("date") != null) {
            List<Cruise> cruiseList = cruiseService.getCruiseByStartDate(lang_id, start, AMOUNT_OF_ITEMS_ON_PAGE, Timestamp.valueOf((String) httpSession.getAttribute("date")));
            if (cruiseList.size() == 0)
                httpSession.setAttribute("ok", "nothing");
            req.setAttribute("cruiseList", cruiseList);
            req.setAttribute("count", cruiseService.getCruiseAmountDate(Timestamp.valueOf((String) httpSession.getAttribute("date"))));
        } else {
            req.setAttribute("count", cruiseService.getCruiseAmount());
            req.setAttribute("cruiseList", cruiseService.getAllCruise(lang_id, start, AMOUNT_OF_ITEMS_ON_PAGE));
        }
        req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
    }

    /**
     * Changes the language of the cruises on the page calling the
     * model with the exact language parameter
     *
     * @param req  - servlet request
     * @param resp - servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("lang", Long.parseLong(req.getParameter("lang")));
        UserService userService = new UserServiceImpl();
        User user = new User();
        user.setEmail((String) httpSession.getAttribute("email"));
        Language language = new Language();
        language.setId((Long) httpSession.getAttribute("lang"));
        user.setLanguage(language);
        userService.updateUser(user);
        resp.sendRedirect("/");
    }
}
