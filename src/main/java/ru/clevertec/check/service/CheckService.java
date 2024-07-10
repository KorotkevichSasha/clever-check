package ru.clevertec.check.service;

import ru.clevertec.check.model.Product;
import ru.clevertec.check.model.DiscountCard;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckService {
    private DatabaseService databaseService;

    public CheckService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List<Product> loadProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT * FROM product";
            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getBoolean("is_wholesale"));
                    products.add(product);
                }
            }
        }
        System.out.println("Products loaded from DB: " + products);
        return products;
    }

    public List<DiscountCard> loadDiscountCards() throws SQLException {
        List<DiscountCard> discountCards = new ArrayList<>();
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT * FROM discount_card";
            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DiscountCard card = new DiscountCard(rs.getInt("number"), rs.getDouble("discount_percentage"));
                    discountCards.add(card);
                }
            }
        }
        System.out.println("Discount cards loaded from DB: " + discountCards);
        return discountCards;
    }

    public void process(String[] args) throws SQLException {
        List<Product> products = loadProducts();
        List<DiscountCard> discountCards = loadDiscountCards();

        List<Product> selectedProducts = new ArrayList<>();
        DiscountCard appliedCard = null;

        double balanceDebitCard = 0.00;

        for (String arg : args) {
            if (arg.startsWith("discountCard=")) {
                int cardNumber = Integer.parseInt(arg.split("=")[1]);
                for (DiscountCard card : discountCards) {
                    if (card.getNumber() == cardNumber) {
                        appliedCard = card;
                        break;
                    }
                }
            } else if (arg.startsWith("balanceDebitCard=")) {
                balanceDebitCard = Double.parseDouble(arg.split("=")[1]);
            } else {
                String[] productInfo = arg.split("-");
                if (productInfo.length == 2) {
                    try {
                        int productId = Integer.parseInt(productInfo[0]);
                        int quantity = Integer.parseInt(productInfo[1]);

                        for (Product product : products) {
                            if (product.getId() == productId) {
                                for (int i = 0; i < quantity; i++) {
                                    selectedProducts.add(product);
                                }
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid product or quantity format: " + arg);
                    }
                } else {
                    System.err.println("Invalid argument format: " + arg);
                }
            }
        }

        System.out.println("Selected products: " + selectedProducts);
        System.out.println("Applied discount card: " + (appliedCard != null ? appliedCard : "None"));

    }
}