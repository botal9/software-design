package ru.akirakozov.sd.refactoring.controller;

import ru.akirakozov.sd.refactoring.model.Product;
import ru.akirakozov.sd.refactoring.repository.ProductRepository;
import ru.akirakozov.sd.refactoring.repository.ProductRepositoryDB;

import java.util.List;

public class ProductController {
    public enum Config {
        PRODUCTION,
        TEST,
    }

    private final ProductRepository repository;

    public ProductController(Config config) {
        switch (config) {
            case PRODUCTION:
                repository = new ProductRepositoryDB("production.db");
                break;
            case TEST:
                repository = new ProductRepositoryDB("test.db");
                break;
            default:
                throw new RuntimeException("Undefined config: " + config.toString());
        }
    }

    public void addProduct(String name, long price) {
        repository.addProduct(name, price);
    }

    public void clear() {
        repository.clear();
    }

    public List<Product> getAllProducts() {
        return repository.getAllProducts();
    }

    public Product getProductWithMinPrice() {
        return repository.getProductWithMinPrice();
    }

    public Product getProductWithMaxPrice() {
        return repository.getProductWithMaxPrice();
    }

    public int getProductCount() {
        return repository.getProductCount();
    }

    public long getSummaryPrice() {
        return repository.getSummaryPrice();
    }
}
