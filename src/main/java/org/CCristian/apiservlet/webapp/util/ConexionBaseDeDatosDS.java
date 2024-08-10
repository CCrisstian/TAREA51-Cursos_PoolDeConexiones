package org.CCristian.apiservlet.webapp.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionBaseDeDatosDS {
    /*----MÉTODO CONEXIÓN A LA BASE DE DATOS----*/
    public static Connection getConection() throws SQLException, NamingException {
        Context initContext = null;
        initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/mysqlDB");
        return ds.getConnection();
    }
}
