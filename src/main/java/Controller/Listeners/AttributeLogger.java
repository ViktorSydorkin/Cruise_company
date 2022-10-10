package Controller.Listeners;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class AttributeLogger implements HttpSessionAttributeListener {
    private final static Logger logger = Logger.getLogger(AttributeLogger.class);
    private final static String ADD = "Adding: ";
    private final static String REPLACE = "Replacing: ";
    private final static String REMOVE = "Removing: ";
    private final static String SEPARATE = " : ";

    /**
     * Receives notification that an attribute has been added to a
     * session
     *
     * @param event - the HttpSessionBindingEvent containing the session
     *              and the name and value of the attribute that was added
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        loggingCheck(event, ADD);
    }

    /**
     * Receives notification that an attribute has been replaced in a
     * session
     *
     * @param event - the HttpSessionBindingEvent containing the session
     *              and the name and value of the attribute that was removed
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        loggingCheck(event, REPLACE);
    }


    /**
     * Receives notification that an attribute has been removed from a
     * session
     *
     * @param event -  the HttpSessionBindingEvent containing the session
     *              and the name and value of the attribute that was removed
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        loggingCheck(event, REMOVE);
    }

    /**
     * Logs an information about the added/replaced/removed attribute
     *
     * @param event - the HttpSessionBindingEvent containing the session
     *              and the name and value of the attribute that was removed
     * @param value - the word that should be pasted into lof information
     */
    private void loggingCheck(HttpSessionBindingEvent event, String value) {
        if (event.getSession().getAttribute("role") != null && event.getSession().getAttribute("role").equals("Admin")) {
            StringBuilder builder = new StringBuilder();
            builder.append(value).append(event.getClass().getSimpleName()).append(SEPARATE).append(event.getName()).
                    append(SEPARATE).append(event.getValue());
            logger.info(builder);
        }
    }
}
