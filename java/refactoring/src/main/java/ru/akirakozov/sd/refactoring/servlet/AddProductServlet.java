package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.controller.ProductController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class AddProductServlet extends ProductServletBase {
    public AddProductServlet(ProductController productController) {
        super(productController);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        long price = Long.parseLong(request.getParameter("price"));
        productController.addProduct(name, price);

        response.getWriter().println("OK");
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
