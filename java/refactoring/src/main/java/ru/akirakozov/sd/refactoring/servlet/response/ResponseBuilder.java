package ru.akirakozov.sd.refactoring.servlet.response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

abstract public class ResponseBuilder {
    public void buildServletResponse(HttpServletResponse response, Object content) throws IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);

        setHtmlBodyPre(response);
        setHeader(response);
        setContent(response, content);
        setHtmlBodyPost(response);
    }

    void setHtmlBodyPre(HttpServletResponse response) throws IOException {
        response.getWriter().println("<html><body>");
    }

    void setHtmlBodyPost(HttpServletResponse response) throws IOException {
        response.getWriter().println("</body></html>");
    }

    abstract void setHeader(HttpServletResponse response) throws IOException;

    abstract void setContent(HttpServletResponse response, Object content) throws IOException;
}
