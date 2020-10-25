package ru.akirakozov.sd.refactoring.servlet.response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddProductResponseBuilder extends ResponseBuilder {
    @Override
    void setHeader(HttpServletResponse response) throws IOException {
        response.getWriter().println("OK");
    }

    @Override
    void setContent(HttpServletResponse response, Object content) {
    }
}
