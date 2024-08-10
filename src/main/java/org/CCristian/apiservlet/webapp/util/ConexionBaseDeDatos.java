package org.CCristian.apiservlet.webapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDeDatos {

    /*----PARÁMETROS DE LA BASE DE DATOS----*/
    private static String url = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=America/Argentina/Buenos_Aires";
    private static String username = "root";
    private static String password = "sasa";

    /*----MÉTODO CONEXIÓN A LA BASE DE DATOS----*/
    public static Connection getConection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
