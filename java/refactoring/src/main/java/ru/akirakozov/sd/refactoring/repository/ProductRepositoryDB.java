package ru.akirakozov.sd.refactoring.repository;

import ru.akirakozov.sd.refactoring.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryDB implements ProductRepository {
    private static final String DB_URL_PREFIX = "jdbc:sqlite:";
    private final String databaseUrl;

    private enum AggregationType {
        INT,
        LONG,
    }

    public ProductRepositoryDB(String databaseName) {
        databaseUrl = DB_URL_PREFIX + databaseName;

        String createProductTableIfNotExists =
                "CREATE TABLE IF NOT EXISTS PRODUCT" +
                "(ID     INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " NAME   TEXT                              NOT NULL, " +
                " PRICE  INTEGER                           NOT NULL)";
        executeUpdate(createProductTableIfNotExists);
    }

    @Override
    public void clear() {
        executeUpdate("DELETE FROM PRODUCT");
    }

    @Override
    public void addProduct(String name, long price) {
        String sql = "INSERT INTO PRODUCT " +
                "(NAME, PRICE) VALUES (\"" +
                name + "\"," + price + ")";
        executeUpdate(sql);
    }

    @Override
    public List<Product> getAllProducts() {
        return executeQuery("SELECT * FROM PRODUCT");
    }

    @Override
    public Product getProductWithMinPrice() {
        List<Product> products = executeQuery("SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1");
        if (products.isEmpty())
            return null;
        return products.get(0);
    }

    @Override
    public Product getProductWithMaxPrice() {
        List<Product> products = executeQuery("SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1");
        if (products.isEmpty())
            return null;
        return products.get(0);
    }

    @Override
    public int getProductCount() {
        return (int) executeAggregationQuery("SELECT COUNT(*) FROM PRODUCT", AggregationType.INT);
    }

    @Override
    public long getSummaryPrice() {
        return (long) executeAggregationQuery("SELECT SUM(price) FROM PRODUCT", AggregationType.LONG);
    }


    private void executeUpdate(String sql) {
        try (Connection c = DriverManager.getConnection(databaseUrl)) {
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Product> executeQuery(String sql) {
        try (Connection c = DriverManager.getConnection(databaseUrl)) {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name");
                long price = rs.getLong("price");
                products.add(new Product(name, price));
            }

            rs.close();
            stmt.close();

            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Number executeAggregationQuery(String sql, AggregationType type) {
        try (Connection c = DriverManager.getConnection(databaseUrl)) {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            Number result;
            if (rs.next()) {
                switch (type) {
                    case INT:
                        result = rs.getInt(1);
                        break;
                    case LONG:
                        result = rs.getLong(1);
                        break;
                    default:
                        throw new RuntimeException("Undefined AggregationType: " + type.toString());
                }
            } else {
                throw new RuntimeException("Aggregation query must return a value");
            }

            rs.close();
            stmt.close();

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
