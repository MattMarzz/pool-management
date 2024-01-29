package main.java.it.uniroma2.dicii.bd.utils;

import main.java.it.uniroma2.dicii.bd.enums.Role;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

    private static Connection conn = null;

    protected DbConnection(){}

    public static Connection getConnection() {
        return conn;
    }

    static {
        try(InputStream in = DbConnection.class.getClassLoader().getResourceAsStream("application.properties")) {

            Properties properties = new Properties();
            properties.load(in);

            String dbUrl = properties.getProperty("db.url");
            String dbUsr = properties.getProperty("db.login.usr");
            String dbPwd = properties.getProperty("db.login.pwd");

            conn = DriverManager.getConnection(dbUrl, dbUsr, dbPwd);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            System.out.println("Errore di connessione");
        }
    }

    public static void changeRole(Role role) throws SQLException{
        conn.close();

        try(InputStream in = DbConnection.class.getClassLoader().getResourceAsStream("application.properties")) {

            Properties properties = new Properties();
            properties.load(in);

            String dbUrl = properties.getProperty("db.url");
            String dbUsr = properties.getProperty("db." + role.name().toLowerCase() + ".usr");
            String dbPwd = properties.getProperty("db." + role.name().toLowerCase()+ ".pwd");

            conn = DriverManager.getConnection(dbUrl, dbUsr, dbPwd);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            System.out.println("Errore di connessione");
        }
    }

}
