package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.controller.ProductController;

import javax.servlet.http.HttpServlet;

public class ProductServletBase extends HttpServlet {
    protected final ProductController productController;

    ProductServletBase(ProductController productController) {
        this.productController = productController;
    }
}
