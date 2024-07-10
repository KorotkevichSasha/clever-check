package ru.clevertec.check.model;

public class Product {
    private int id;
    private String name;
    private double price;
    private boolean isWholesale;

    public Product(int id, String name, double price, boolean isWholesale) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isWholesale = isWholesale;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public boolean isWholesale() { return isWholesale; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setWholesale(boolean wholesale) { isWholesale = wholesale; }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", isWholesale=" + isWholesale +
                '}';
    }
}
