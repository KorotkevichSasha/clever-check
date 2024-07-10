package ru.clevertec.check.factory;

import ru.clevertec.check.service.DatabaseService;

public class DatabaseServiceFactory {
    public static DatabaseService createDatabaseService(String url, String username, String password) {
        return new DatabaseService(url, username, password);
    }
}
