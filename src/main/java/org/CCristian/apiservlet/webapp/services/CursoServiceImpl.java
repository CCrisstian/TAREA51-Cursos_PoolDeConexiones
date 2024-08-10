package org.CCristian.apiservlet.webapp.services;

import org.CCristian.apiservlet.webapp.models.Curso;
import org.CCristian.apiservlet.webapp.repositories.CursoRepositorioImpl;
import org.CCristian.apiservlet.webapp.repositories.Repositorio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CursoServiceImpl implements CursoService {

    private Repositorio<Curso> repositoryJBDC;

    public CursoServiceImpl(Connection connection) {
        this.repositoryJBDC = new CursoRepositorioImpl(connection);
    }

    @Override
    public List<Curso> listar() throws SecurityException {
        try {
            return repositoryJBDC.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<Curso> porNombre(String nombre) {
        try {
            return repositoryJBDC.porNombre(nombre);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void guardar(Curso curso) {
        try {
            repositoryJBDC.guardar(curso);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }


    @Override
    public Optional<Curso> porId(int id) {
        try {
            return Optional.ofNullable(repositoryJBDC.porId(id));
        } catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void eliminar(int id) {
        try {
            repositoryJBDC.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
