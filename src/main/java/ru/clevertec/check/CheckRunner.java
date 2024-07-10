package ru.clevertec.check;

import ru.clevertec.check.factory.DatabaseServiceFactory;
import ru.clevertec.check.service.CheckService;
import ru.clevertec.check.service.DatabaseService;

import java.sql.SQLException;

public class CheckRunner {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/check";
        String username = "postgres";
        String password = "11111";

        for (String arg : args) {
            if (arg.startsWith("datasource.url=")) {
                url = arg.split("=")[1];
            } else if (arg.startsWith("datasource.username=")) {
                username = arg.split("=")[1];
            } else if (arg.startsWith("datasource.password=")) {
                password = arg.split("=")[1];
            }
        }

        DatabaseService databaseService = DatabaseServiceFactory.createDatabaseService(url, username, password);
        CheckService checkService = new CheckService(databaseService);

        try {
            checkService.process("1-2 discountCard=1 balanceDebitCard=100.0".split(" "));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
