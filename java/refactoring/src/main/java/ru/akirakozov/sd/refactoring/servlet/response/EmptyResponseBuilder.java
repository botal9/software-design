package ru.akirakozov.sd.refactoring.servlet.response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmptyResponseBuilder extends ResponseBuilder {

    public void buildEmptyResponse(HttpServletResponse response) throws IOException {
        buildServletResponse(response, null);
    }

    @Override
    void setHeader(HttpServletResponse response) {
    }

    @Override
    void setContent(HttpServletResponse response, Object content) {
    }
}
