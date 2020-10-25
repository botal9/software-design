package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.controller.ProductController;
import ru.akirakozov.sd.refactoring.servlet.response.EmptyResponseBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClearProductsServlet extends ProductServletBase {
    public ClearProductsServlet(ProductController productController) {
        super(productController, new EmptyResponseBuilder());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        productController.clear();
        getResponseBuilder().buildEmptyResponse(response);
    }

    private EmptyResponseBuilder getResponseBuilder() {
        return (EmptyResponseBuilder) responseBuilder;
    }
}
