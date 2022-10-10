package Controller;

import Controller.Validator.Validator;
import Model.Entity.*;
import Model.Implementation.*;
import Model.Service.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/admin/*")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class Admin_index extends HttpServlet {
    /**
     * Implements the switch that chooses accordingly to the request path
     * what page should pe displayed.
     * Implements the realization of all admin's pages
     *
     * @param req  - servlet request
     * @param resp - servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getPathInfo()) {
            case "/main" -> {
                ApplicationService applicationService = new ApplicationServiceImpl();
                req.setAttribute("applicationList", applicationService.getAllApplications((Long) req.getSession().getAttribute("lang")));
                req.getRequestDispatcher("/view/admin_index.jsp").forward(req, resp);
            }
            case "/create_cruise" -> {
                routeCruiseSelection(req);
                req.getRequestDispatcher("/view/create_cruise.jsp").forward(req, resp);
            }
            case "/validate" -> {
                ApplicationService applicationService = new ApplicationServiceImpl();
                Application application = applicationService.getApplicationById((Long) req.getSession().getAttribute("lang"), Long.parseLong(req.getParameter("app_id")));
                req.setAttribute("app", application);
                req.getRequestDispatcher("/view/validation.jsp").forward(req, resp);
            }
            case "/editCruise" -> {
                CruiseService  cruiseService = new CruiseServiceImpl();
                List<CruiseTranslation> cruiseTranslationList =  cruiseService.getAllCruiseTranslations(Long.parseLong(req.getParameter("cruise")));
                req.setAttribute("translationENG", cruiseTranslationList.get(0));
                req.setAttribute("translationUA", cruiseTranslationList.get(1));
                routeCruiseSelection(req);
                req.getSession().setAttribute("cruise", Long.parseLong(req.getParameter("cruise")));
                req.getRequestDispatcher("/view/editCruise.jsp").forward(req, resp);
            }
            case "/validateImage" -> {
                ApplicationService applicationService = new ApplicationServiceImpl();
                Application application = applicationService.getApplicationById((Long) req.getSession().getAttribute("lang"), Long.parseLong(req.getParameter("app_id")));
                InputStream photo = application.getPass_photo();
                resp.setHeader("Content-Type", "image");
                resp.getOutputStream().write(photo.readAllBytes());
            }
            case "/userList" -> {
                ServletContext servletContext = getServletContext();
                req.setAttribute("total", servletContext.getAttribute("total"));
                req.setAttribute("current", servletContext.getAttribute("current"));
                UserService userService = new UserServiceImpl();
                req.setAttribute("userList", userService.getAllUsers());
                req.getRequestDispatcher("/view/users.jsp").forward(req, resp);
            }
            case "/editUser" -> {
                req.getSession().setAttribute("user", req.getParameter("user"));
                req.getRequestDispatcher("/view/userEdit.jsp").forward(req, resp);
            }
            case "/create_liner" -> {
                CompanyServiceImpl companyServiceImpl = new CompanyServiceImpl();
                req.setAttribute("companyList", companyServiceImpl.geAllCompanies());
                req.getRequestDispatcher("/view/create_liner.jsp").forward(req, resp);
            }
            case "/linerList" -> {
                 LinerService  linerService = new LinerServiceImpl();
                req.setAttribute("linerList",  linerService.getAllLiners((Long) req.getSession().getAttribute("lang")));
                StaffService staffService = new StaffServiceImpl();
                req.setAttribute("staffList", staffService.getAllStaff());
                req.getRequestDispatcher("/view/liners.jsp").forward(req, resp);
            }
            case "/editLiner" -> {
                CompanyServiceImpl companyServiceImpl = new CompanyServiceImpl();
                req.setAttribute("companyList", companyServiceImpl.geAllCompanies());
                req.getSession().setAttribute("liner", Long.parseLong(req.getParameter("liner")));
                req.getRequestDispatcher("/view/linerEdit.jsp").forward(req, resp);
            }
            case "/staffList" -> {
                StaffService staffService = new StaffServiceImpl();
                req.setAttribute("staffList", staffService.getAllStaff());
                req.getRequestDispatcher("/view/staff.jsp").forward(req, resp);
            }
            case "/create_staff" -> {
                 LinerService  linerService = new LinerServiceImpl();
                req.setAttribute("linerList",  linerService.getAllLiners((Long) req.getSession().getAttribute("lang")));
                req.getRequestDispatcher("/view/create_staff.jsp").forward(req, resp);
            }
            case "/editStaff" -> {
                 LinerService  linerService = new LinerServiceImpl();
                req.setAttribute("linerList",  linerService.getAllLiners((Long) req.getSession().getAttribute("lang")));
                req.getSession().setAttribute("staff", Long.parseLong(req.getParameter("staff_id")));
                req.getRequestDispatcher("/view/staffEdit.jsp").forward(req, resp);
            }
            default -> req.getRequestDispatcher("/view/admin_index.jsp").forward(req, resp);
        }

    }

    /**
     * Implements the switch that chooses accordingly to the request path
     * what should be done.
     * Implements the realization of all admin's functions
     *
     * @param req  - servlet request
     * @param resp - servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationService applicationService = new ApplicationServiceImpl();
        CruiseService  cruiseService = new CruiseServiceImpl();
        switch (req.getPathInfo()) {
            case "/validate" -> {
                switch (req.getParameter("v")) {
                    case "true" -> {
                        if (applicationService.approveApplication((Long) req.getSession().getAttribute("lang"), Long.parseLong(req.getParameter("app_id"))))
                            resp.sendRedirect("/admin/main");
                        else resp.sendRedirect("/error");
                    }
                    case "false" -> {
                        if (applicationService.deleteApplication(Long.parseLong(req.getParameter("app_id"))))
                            resp.sendRedirect("/admin/main");
                        else resp.sendRedirect("/error");
                    }
                }
            }
            case "/open" -> {
                if (cruiseService.changeAvailability((Long) req.getSession().getAttribute("lang"), Long.parseLong(req.getParameter("cruise")))) {
                    applicationService.openApplication((Long) req.getSession().getAttribute("lang"), Long.parseLong(req.getParameter("app_id")));
                }
                resp.sendRedirect("/admin/main");
            }
            case "/end" -> {
                if (applicationService.endApplication(1, Long.parseLong(req.getParameter("app_id"))))
                    resp.sendRedirect("/admin/main");
                else resp.sendRedirect("/admin/main");
            }
            case "/create_cruise" -> {
                Cruise cruise = cruiseBuild(req);
                cruise.setAvailable(cruise.getLiner().getCapacity());
                if (Validator.titleENGCheck(cruise.getCruiseTranslationList().get(0).getTitle()) && Validator.titleUACheck(cruise.getCruiseTranslationList().get(1).getTitle()) && Validator.dateCheck(cruise.getStart().toLocalDateTime(), cruise.getEnd().toLocalDateTime()) &&  cruiseService.addCruise(cruise))
                    resp.sendRedirect("/");
                else resp.sendRedirect("/");
            }
            case "/editCruise" -> {
                Cruise cruise = cruiseBuild(req);
                cruise.setId((Long) req.getSession().getAttribute("cruise"));
                cruise.setAvailable(Integer.parseInt(req.getParameter("available")));
                req.getSession().removeAttribute("cruise");
                if (Validator.titleENGCheck(cruise.getCruiseTranslationList().get(0).getTitle()) && Validator.titleUACheck(cruise.getCruiseTranslationList().get(1).getTitle()) && Validator.dateCheck(cruise.getStart().toLocalDateTime(), cruise.getEnd().toLocalDateTime()) &&  cruiseService.updateCruise((Long) req.getSession().getAttribute("lang"), cruise )) {
                    resp.sendRedirect("/");
                } else resp.sendRedirect("/");
            }
            case "/deleteCruise" -> {
                if (cruiseService.deleteCruise(Long.parseLong(req.getParameter("cruise")))) resp.sendRedirect("/");
                else resp.sendRedirect("/");
            }
            case "/editUser" -> {
                UserService userService = new UserServiceImpl();
                User user = new User();
                user.setName(req.getParameter("name"));
                user.setSurname(req.getParameter("surname"));
                user.setEmail((String) req.getSession().getAttribute("user"));
                req.getSession().removeAttribute("user");
                user.setPassword(req.getParameter("password"));
                Language language = new Language();
                language.setId(Long.parseLong(req.getParameter("language")));
                user.setLanguage(language);
                if (Validator.nameCheck(user.getName()) && Validator.nameCheck(user.getSurname()) && language.getId() < 3 && language.getId() > 0 && userService.updateUser(user))
                    resp.sendRedirect("/admin/userList");
                else resp.sendRedirect("/admin/userList");
            }
            case "/deleteUser" -> {
                UserService userService = new UserServiceImpl();
                if (userService.deleteUser(Long.parseLong(req.getParameter("user"))))
                    resp.sendRedirect("/admin/userList");
                else resp.sendRedirect("/admin/userList");
            }
            case "/create_liner" -> {
                 LinerService  linerService = new LinerServiceImpl();
                Liner liner = linerBuild(req);
                Part filePart = req.getPart("file");
                InputStream inputStream = filePart.getInputStream();
                liner.setLiner_photo(inputStream);
                if (Validator.titleENGCheck(req.getParameter("liner_name")) &&  linerService.addLiner(liner))
                    resp.sendRedirect("/admin/linerList");
                else resp.sendRedirect("/admin/linerList");
            }
            case "/editLiner" -> {
                 LinerService  linerService = new LinerServiceImpl();
                Liner liner = linerBuild(req);
                liner.setId((Long) req.getSession().getAttribute("liner"));
                req.getSession().removeAttribute("liner"); Part filePart = req.getPart("file");
                InputStream inputStream = filePart.getInputStream();
                liner.setLiner_photo(inputStream);
                if (Validator.titleENGCheck(req.getParameter("liner_name")) &&  linerService.updateLiner(liner))
                    resp.sendRedirect("/admin/linerList");
                else resp.sendRedirect("/admin/linerList");
            }
            case "/deleteLiner" -> {
                 LinerService  linerService = new LinerServiceImpl();
                if ( linerService.deleteLiner(Long.parseLong(req.getParameter("liner"))))
                    resp.sendRedirect("/admin/linerList");
                else resp.sendRedirect("/admin/linerList");
            }
            case "/create_staff" -> {
                StaffService staffService = new StaffServiceImpl();
                Staff staff = staffBuild(req);
                if (Validator.nameCheck(staff.getName()) && Validator.nameCheck(staff.getSurname()) && Validator.titleENGCheck(staff.getPost()) && staffService.addStaff(staff))
                    resp.sendRedirect("/admin/staffList");
                else resp.sendRedirect("/admin/staffList");
            }
            case "/editStaff" -> {
                StaffService staffService = new StaffServiceImpl();
                Staff staff = staffBuild(req);
                staff.setId((Long) req.getSession().getAttribute("staff"));
                req.getSession().removeAttribute("staff");
                if (Validator.nameCheck(staff.getName()) && Validator.nameCheck(staff.getSurname()) && Validator.titleENGCheck(staff.getPost()) && staffService.updateStaff(staff))
                    resp.sendRedirect("/admin/staffList");
                else resp.sendRedirect("/admin/staffList");
            }
            case "/deleteStaff" -> {
                StaffService staffService = new StaffServiceImpl();
                if (staffService.deleteStaff(Long.parseLong(req.getParameter("staff_id"))))
                    resp.sendRedirect("/admin/staffList");
                else resp.sendRedirect("/admin/staffList");
            }
        }

    }

    /**
     * Gets all cruises and routes from model and puts it into hte request
     *
     * @param req - servlet request
     */
    private void routeCruiseSelection(HttpServletRequest req) {
         LinerService  linerService = new LinerServiceImpl();
        req.setAttribute("linerList",  linerService.getAllLiners((Long) req.getSession().getAttribute("lang")));
        RouteService routeService = new RouteServiceImpl();
        req.setAttribute("routeList", routeService.getAllRoutes((Long) req.getSession().getAttribute("lang")));
    }

    /**
     * Creates a staff instance according to the data it received
     * from the request
     *
     * @param req - servlet request
     * @return - the created staff
     * @see Staff
     */
    private Staff staffBuild(HttpServletRequest req) {
        Staff staff = new Staff();
        staff.setName(req.getParameter("name"));
        staff.setSurname(req.getParameter("surname"));
        staff.setPost(req.getParameter("post"));
        Liner liner = new Liner();
        liner.setId(Long.parseLong(req.getParameter("liner")));
        staff.setLiner(liner);
        return staff;
    }

    /**
     * Creates a liner instance according to the data it received
     * from the request
     *
     * @param req - servlet request
     * @return - the created liner
     * @see Liner
     */
    private Liner linerBuild(HttpServletRequest req) {
        Liner liner = new Liner();
        liner.setCapacity(Integer.parseInt(req.getParameter("capacity")));
        liner.setName(req.getParameter("liner_name"));
        liner.setDeck_amount(Integer.parseInt(req.getParameter("deck_amount")));
        Company company = new Company();
        company.setId(Long.parseLong(req.getParameter("company")));
        liner.setCompany(company);
        return liner;
    }

    /**
     * Creates a cruise instance according to the data it received
     * from the request
     *
     * @param req - servlet request
     * @return - the created cruise
     * @see Cruise
     */
    private Cruise cruiseBuild(HttpServletRequest req) {
        Cruise cruise = new Cruise();
         LinerService  linerService = new LinerServiceImpl();
        Liner liner =  linerService.getLinerById(Long.parseLong(req.getParameter("liner")));
        Route route = new Route();
        route.setId(Long.parseLong(req.getParameter("route")));
        cruise.setStart(Timestamp.valueOf(req.getParameter("start").replace("T", " ") + ":00"));
        cruise.setEnd(Timestamp.valueOf(req.getParameter("end").replace("T", " ") + ":00"));
        cruise.setPrice(Integer.parseInt(req.getParameter("price")));
        cruise.setLiner(liner);
        cruise.setRoute(route);
        cruise.getCruiseTranslationList().add(createCruiseTranslation(1, req.getParameter("cruiseEng")));
        cruise.getCruiseTranslationList().add(createCruiseTranslation(2, req.getParameter("cruiseUa")));
        return cruise;
    }

    /**
     * Creates a cruise translations instance for the specific cruise
     *
     * @param lang_id - id of language
     * @param title   - cruise's title
     * @return - cruises translations
     * @see CruiseTranslation
     */
    private CruiseTranslation createCruiseTranslation(long lang_id, String title) {
        CruiseTranslation cruiseTranslation = new CruiseTranslation();
        Language language = new Language();
        language.setId(lang_id);
        cruiseTranslation.setLanguage(language);
        cruiseTranslation.setTitle(title);
        return cruiseTranslation;
    }
}
