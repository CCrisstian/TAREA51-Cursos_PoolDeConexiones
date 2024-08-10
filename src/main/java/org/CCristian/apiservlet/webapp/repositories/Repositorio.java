package org.CCristian.apiservlet.webapp.repositories;

import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T> {
    List<T> listar() throws SQLException;
    List<T> porNombre(String t) throws SQLException;
    void guardar(T t) throws SQLException;
    void eliminar(int id) throws SQLException;
    T porId(int id) throws SQLException;
}
