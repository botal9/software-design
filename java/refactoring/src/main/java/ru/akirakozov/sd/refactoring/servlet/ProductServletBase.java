package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.controller.ProductController;
import ru.akirakozov.sd.refactoring.servlet.response.ResponseBuilder;

import javax.servlet.http.HttpServlet;

public class ProductServletBase extends HttpServlet {
    protected final ProductController productController;
    protected final ResponseBuilder responseBuilder;

    ProductServletBase(ProductController productController, ResponseBuilder responseBuilder) {
        this.productController = productController;
        this.responseBuilder = responseBuilder;
    }
}
