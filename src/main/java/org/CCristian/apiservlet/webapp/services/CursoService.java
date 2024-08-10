package org.CCristian.apiservlet.webapp.services;

import org.CCristian.apiservlet.webapp.models.Curso;
import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> listar() throws SecurityException;
    List<Curso> porNombre(String nombre);
    void  guardar (Curso curso);
    void eliminar (int id);
    Optional<Curso> porId(int id);

}
