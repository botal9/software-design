package ru.akirakozov.sd.refactoring.servlet.response;

import ru.akirakozov.sd.refactoring.model.Product;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetProductsResponseBuilder extends ResponseBuilder {
    @Override
    void setHeader(HttpServletResponse response) {
    }

    @Override
    void setContent(HttpServletResponse response, Object content) throws IOException {
        List<Product> products = (List<Product>) content;
        for (Product product : products) {
            response.getWriter().println(product.getName() + "\t" + product.getPrice() + "</br>");
        }
    }
}
