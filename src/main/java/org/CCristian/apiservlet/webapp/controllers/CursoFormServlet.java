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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/cursos/form")
public class CursoFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");    /*Obtiene la conexión a la Base de Datos*/
        CursoService service = new CursoServiceImpl(conn);

        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));  /*Obtiene el 'id' del producto que se quiere Editar*/
        } catch (NumberFormatException e) {
            id = 0;
        }
        Curso curso = new Curso();
        if (id > 0) {    /*El curso ya existe y se debe Modificar*/
            Optional<Curso> cursoOptional = service.porId(id);  /*Se busca el curso al que le corresponde la 'id'*/
            if (cursoOptional.isPresent()) { /*Valida que exista ese curso*/
                curso = cursoOptional.get();
            }
        }
        req.setAttribute("curso", curso); /*Pasar el curso y asignarlo como atributo del request */
        req.setAttribute("title", req.getAttribute("title") + "Formulario de Cursos");
        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);   /*Pasar el curso a form.jsp*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");    /*Obtiene la conexión a la Base de Datos*/
        CursoService service = new CursoServiceImpl(conn);

        /*Obteniendo los valores desde el request*/
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        String instructor = req.getParameter("instructor");
        float duracion;
        try {
            duracion = Float.parseFloat(req.getParameter("duracion"));
        } catch (NumberFormatException e) {
            duracion = 0F;
        }

        /*Validando los valores obtenidos*/
        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El Nombre es requerido!");
        }
        if (descripcion == null || descripcion.isBlank()) {
            errores.put("descripcion", "La Descripción es requerida!");
        }
        if (instructor == null || instructor.isBlank()) {
            errores.put("instructor", "El Instructor es requerido!");
        }
        if (duracion == 0F) {
            errores.put("duracion", "La Duración es requerida!");
        }
        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }

        /*Asignando los valores obtenidos al producto*/
        Curso curso = new Curso(id, nombre, descripcion, instructor, duracion);

        /*Cargar en la Base de Datos o Devolver el producto con Errores*/
        if (errores.isEmpty()) {
            service.guardar(curso);  /*Cargando el curso a la Base de Datos*/
            resp.sendRedirect(req.getContextPath() + "/cursos");   /*Redireccionar los valores al Servlet /cursos*/
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("curso", curso); /*Pasar el curso con Errores y asignarlo como atributo del request */
            req.setAttribute("title", req.getAttribute("title") + "Formulario de Cursos");
            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);   /*Enviar el curso con Errores a form.jsp*/
        }
    }
}
