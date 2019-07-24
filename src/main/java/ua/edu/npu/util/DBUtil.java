package ua.edu.npu.util;

import javax.servlet.ServletRequest;
import java.sql.Connection;

public class DBUtil {
    public static final String ATT_NAME = "MY_CONNECTION_ATTRIBUTE";
    public static void storeConnection(ServletRequest servletRequest, Connection connection) {
        servletRequest.setAttribute(ATT_NAME, connection);
    }

    public static Connection getStoredConnection(ServletRequest servletRequest) {
        Connection connection = (Connection) servletRequest.getAttribute(ATT_NAME);
        return connection;
    }
}
