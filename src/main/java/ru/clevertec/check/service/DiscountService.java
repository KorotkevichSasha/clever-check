package ru.clevertec.check.service;

import ru.clevertec.check.model.DiscountCard;

import java.util.List;

public class DiscountService implements IService<List<DiscountCard>> {
    @Override
    public List<DiscountCard> load(String filePath) {
        // Load discount cards from CSV
        return null;
    }
}
