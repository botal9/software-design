package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.controller.ProductController;
import ru.akirakozov.sd.refactoring.model.Product;
import ru.akirakozov.sd.refactoring.servlet.response.QueryProductsResponseBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class QueryProductsServlet extends ProductServletBase {
    public QueryProductsServlet(ProductController productController) {
        super(productController, new QueryProductsResponseBuilder());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        if ("max".equals(command)) {
            Product product = productController.getProductWithMaxPrice();
            getResponseBuilder().buildServletResponse(
                    response, product, QueryProductsResponseBuilder.QueryType.MAX);
        } else if ("min".equals(command)) {
            Product product = productController.getProductWithMinPrice();
            getResponseBuilder().buildServletResponse(
                    response, product, QueryProductsResponseBuilder.QueryType.MIN);
        } else if ("sum".equals(command)) {
            long summaryPrice = productController.getSummaryPrice();
            getResponseBuilder().buildServletResponse(
                    response, summaryPrice, QueryProductsResponseBuilder.QueryType.SUM);
        } else if ("count".equals(command)) {
            int productsCount = productController.getProductCount();
            getResponseBuilder().buildServletResponse(
                    response, productsCount, QueryProductsResponseBuilder.QueryType.COUNT);
        } else {
            response.getWriter().println("Unknown command: " + command);
        }
    }

    private QueryProductsResponseBuilder getResponseBuilder() {
        return (QueryProductsResponseBuilder) responseBuilder;
    }
}
