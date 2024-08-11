<h1 align="center">TAREA51: Pool de conexiones en el proyecto "Cursos"</h1>
<p>Configurar pool de conexiones en la penúltima tarea de proyecto cursos</p>
<h2>Instrucciones</h2>
<p>Para este nuevo desafío se requerirá modificar el proyecto de la tarea penúltima para configurar el pool de conexiones de tomcat.</p>
<p>Una vez terminada y probada la tarea deberán enviar el código fuente de todos los archivos involucrados, además de las imágenes screenshot (imprimir pantalla).</p>
<p>Subir sólo los archivos involucrados, que son los siguientes:</p>

- web.xml
- context.xml
- ConexionBaseDatos con DataSource
- ConexionFilter con NamingException

<h1>Resolución del Profesor</h1>

- web.xml
```xml
<web-app xmlns="http://java.sun.com/xml/ns/j2ee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee
http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd"
         version="2.4">
    <resource-ref>
        <description>DB Connection</description>
        <res-ref-name>jdbc/mysqlDB</res-ref-name>
        <res-type>javax.sql.DataSource</res-type>
        <res-auth>Container</res-auth>
    </resource-ref>
</web-app>
```

- context.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Context>
    <Resource name="jdbc/mysqlDB" auth="Container" type="javax.sql.DataSource"
              maxTotal="100" maxIdle="30" maxWaitMillis="10000"
              username="root" password="sasa" driverClassName="com.mysql.cj.jdbc.Driver"
              url="jdbc:mysql://localhost:3306/java_curso?serverTimezone=America/Santiago"/>
</Context>
```

- ConexionBaseDatos
```java
package org.aguzman.apiservlet.webapp.jdbc.tarea.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionBaseDatos {
    public static Connection getConnection() throws SQLException, NamingException {
        Context initContext = null;
        initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/mysqlDB");
        return ds.getConnection();
    }
}
```

- ConexionFilter
```java
package org.aguzman.apiservlet.webapp.jdbc.tarea.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.apiservlet.webapp.jdbc.tarea.services.ServiceJdbcException;
import org.aguzman.apiservlet.webapp.jdbc.tarea.util.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;

@WebFilter("/*")
public class ConexionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try (Connection conn = ConexionBaseDatos.getConnection()) {

            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            try {
                request.setAttribute("conn", conn);
                chain.doFilter(request, response);
                conn.commit();
            } catch (SQLException | ServiceJdbcException e) {
                conn.rollback();
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }
}
```
