package ru.akirakozov.sd.refactoring.servlet.response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddProductResponseBuilder extends ResponseBuilder {
    @Override
    public void buildServletResponse(HttpServletResponse response, Object content) throws IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);

        setHeader(response);
    }

    @Override
    void setHeader(HttpServletResponse response) throws IOException {
        response.getWriter().println("OK");
    }

    @Override
    void setContent(HttpServletResponse response, Object content) {
    }
}
