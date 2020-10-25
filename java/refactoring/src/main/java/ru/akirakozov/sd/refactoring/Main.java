package ru.akirakozov.sd.refactoring;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.akirakozov.sd.refactoring.controller.ProductController;
import ru.akirakozov.sd.refactoring.servlet.AddProductServlet;
import ru.akirakozov.sd.refactoring.servlet.ClearProductsServlet;
import ru.akirakozov.sd.refactoring.servlet.GetProductsServlet;
import ru.akirakozov.sd.refactoring.servlet.QueryProductsServlet;

/**
 * @author akirakozov
 */
public class Main {
    public static void main(String[] args) throws Exception {
        boolean isRunningInTestEnv = args.length > 0 && "test-env".equals(args[0]);

        ProductController productController =
                new ProductController(isRunningInTestEnv
                        ? ProductController.Config.TEST
                        : ProductController.Config.PRODUCTION);

        Server server = new Server(8081);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(new ServletHolder(new AddProductServlet(productController)), "/add-product");
        context.addServlet(new ServletHolder(new GetProductsServlet(productController)), "/get-products");
        context.addServlet(new ServletHolder(new QueryProductsServlet(productController)), "/query");

        // For testing purposes.
        if (isRunningInTestEnv) {
            System.out.println("Running server in test environment.");
            context.addServlet(new ServletHolder(new ClearProductsServlet(productController)), "/clear");
        }

        server.start();
        server.join();
    }
}
