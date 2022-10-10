package Controller.Listeners;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class OnlineAmount implements HttpSessionListener {
    ServletContext ctx = null;
    static long total = -2, current = -2;

    @Override
    public void sessionCreated(HttpSessionEvent e) {
        total++;
        current++;

        ctx = e.getSession().getServletContext();
        ctx.setAttribute("total", total);
        ctx.setAttribute("current", current);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent e) {
        current--;
        ctx.setAttribute("current", current);
    }


}
