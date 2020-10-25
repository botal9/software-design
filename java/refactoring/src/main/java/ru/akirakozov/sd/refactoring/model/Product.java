package ru.akirakozov.sd.refactoring.model;

public class Product implements Comparable<Product> {
    private String name;
    private long price;

    @Override
    public int compareTo(Product other) {
        return this.price - other.getPrice() < 0 ? -1 : 1;
    }

    public Product() {
    }

    public Product(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
