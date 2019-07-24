package ua.edu.npu.util;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {

    public static Connection getConnection() {
        Connection connection = null;
        /*try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/jdbc_db?serverTimezone=UTC",
                            "root",
                            "!NPUpassword2019");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return connection;
    }

    public static void closeQuietly(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollbackQuietly(Connection connection) {
        try {
            connection.rollback();
        } catch (Exception e) {
        }
    }
}
