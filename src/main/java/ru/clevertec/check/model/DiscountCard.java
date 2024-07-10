package ru.clevertec.check.model;

public class DiscountCard {
    private int number;
    private double discountPercentage;

    public DiscountCard(int number, double discountPercentage) {
        this.number = number;
        this.discountPercentage = discountPercentage;
    }

    public int getNumber() { return number; }
    public double getDiscountPercentage() { return discountPercentage; }

    public void setNumber(int number) { this.number = number; }
    public void setDiscountPercentage(double discountPercentage) { this.discountPercentage = discountPercentage; }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "number=" + number +
                ", discountPercentage=" + discountPercentage +
                '}';
    }
}
