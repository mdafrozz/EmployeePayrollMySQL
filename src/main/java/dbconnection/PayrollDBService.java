package dbconnection;

import common.Constants;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class PayrollDBService {
    private static PayrollDBService payrollDbService;
    private Connection connection;

    public PayrollDBService() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver class is not found");
            e.printStackTrace();
        }
        listDrivers();
    }

    private void listDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            System.out.println(driver.getClass().getName());
        }

    }

    public static PayrollDBService init() {
        if (payrollDbService == null) {
            payrollDbService = new PayrollDBService();
        }
        return payrollDbService;
    }

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(Constants.JDBC_STR, Constants.USERNAME, Constants.PASSWORD);
            System.out.println("Connection is established.");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection cannot be established.");
            return null;
        }
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
