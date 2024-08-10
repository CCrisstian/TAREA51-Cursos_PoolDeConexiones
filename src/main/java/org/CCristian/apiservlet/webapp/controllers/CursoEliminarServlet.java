package org.CCristian.apiservlet.webapp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.CCristian.apiservlet.webapp.models.Curso;
import org.CCristian.apiservlet.webapp.services.CursoService;
import org.CCristian.apiservlet.webapp.services.CursoServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/cursos/eliminar")
public class CursoEliminarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");    /*Obtiene la conexión a la Base de Datos*/
        CursoService service = new CursoServiceImpl(conn);

        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }
        if (id > 0) {   /*Verifica que se haya enviado el parámetro 'id'*/
            Optional<Curso> cursoOptional = service.porId(id);
            if (cursoOptional.isPresent()) {    /*Verifica que ese 'id' exista en la Base de Datos*/
                service.eliminar(id);   /*Se Elimina el curso al que le corresponde el 'id'*/
                resp.sendRedirect(req.getContextPath() + "/cursos");    /*Direcciona al Servlet /cursos*/
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe ese Curso en la Base de Datos!");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "ERROR el id es null, se debe enviar como parámetro en la url!");
        }
    }
}
