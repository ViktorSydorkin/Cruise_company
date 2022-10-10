package DAO.Util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;

public class TodayDateTime extends TagSupport {
    /**
     * Overridden method that implements the realization of a custom tag
     * @return - the constant that controls the body visibility
     * @throws JspException
     */
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print(LocalDateTime.now().toString().substring(0,16));
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
