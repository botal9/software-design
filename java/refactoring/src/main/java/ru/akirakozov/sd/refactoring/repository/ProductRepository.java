package ru.akirakozov.sd.refactoring.repository;

import ru.akirakozov.sd.refactoring.model.Product;

import java.util.List;

public interface ProductRepository {
    void clear();

    void addProduct(String name, long price);

    List<Product> getAllProducts();

    Product getProductWithMinPrice();

    Product getProductWithMaxPrice();

    int getProductCount();

    long getSummaryPrice();
}
