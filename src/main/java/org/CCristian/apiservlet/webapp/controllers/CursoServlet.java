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
import java.util.List;

@WebServlet({"/index.html", "/cursos"})
public class CursoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");    /*Obtiene la conexión a la Base de Datos*/
        CursoService service = new CursoServiceImpl(conn);

        List<Curso> cursos = service.listar();  /*Obtiene una lista con los Productos*/

        req.setAttribute("cursos", cursos);
        req.setAttribute("title", req.getAttribute("title") + "Listado de Cursos");
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}
