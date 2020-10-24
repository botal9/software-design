package ru.akirakozov.sd.refactoring.model;

public class Product implements Comparable<Product> {
    private String name;
    private int price;

    @Override
    public int compareTo(Product other) {
        return this.price - other.getPrice();
    }

    public Product() {
    }

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
