package ru.clevertec.check.factory;

import ru.clevertec.check.service.*;

public class ServiceFactory {
    public static IService<?> getService(String serviceType) {
        switch (serviceType) {
            case "product":
                return new ProductService();
            case "discount":
                return new DiscountService();
            default:
                throw new IllegalArgumentException("Invalid service type");
        }
    }
}
