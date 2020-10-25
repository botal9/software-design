package ru.akirakozov.sd.refactoring.servlet.response;

import ru.akirakozov.sd.refactoring.model.Product;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QueryProductsResponseBuilder extends ResponseBuilder {
    private QueryType queryType = QueryType.UNTYPED;

    public enum QueryType {
        MAX,
        MIN,
        COUNT,
        SUM,
        UNTYPED,
    }

    public void buildServletResponse(HttpServletResponse response,
                                     Object content, QueryType queryType) throws IOException {
        this.queryType = queryType;
        buildServletResponse(response, content);
        this.queryType = QueryType.UNTYPED;
    }

    @Override
    void setHeader(HttpServletResponse response) throws IOException {
        switch (queryType) {
            case MAX:
                response.getWriter().println("<h1>Product with max price: </h1>");
                break;
            case MIN:
                response.getWriter().println("<h1>Product with min price: </h1>");
                break;
            case COUNT:
                response.getWriter().println("Number of products: ");
                break;
            case SUM:
                response.getWriter().println("Summary price: ");
                break;
            case UNTYPED:
            default:
                throw new RuntimeException("Invalid query type: " + queryType.toString());
        }
    }

    @Override
    void setContent(HttpServletResponse response, Object content) throws IOException {
        switch (queryType) {
            case MAX:
            case MIN:
                Product product = (Product) content;
                if (product != null) {
                    response.getWriter().println(product.getName() + "\t" + product.getPrice() + "</br>");
                }
                break;
            case SUM:
                long summaryPrice = (long) content;
                response.getWriter().println(summaryPrice);
                break;
            case COUNT:
                int count = (int) content;
                response.getWriter().println(count);
                break;
            case UNTYPED:
            default:
                throw new RuntimeException("Invalid query type: " + queryType.toString());
        }
    }
}
