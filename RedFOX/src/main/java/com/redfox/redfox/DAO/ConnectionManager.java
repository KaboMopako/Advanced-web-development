package com.redfox.redfox.DAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
    public static Connection getConnection() throws SQLException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource dataSource = (DataSource) envContext.lookup("jdbc/redfox");
            return dataSource.getConnection();
        } catch (NamingException e) {
            throw new RuntimeException("Error obtaining a database connection", e);
        }
    }
}
