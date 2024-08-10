<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp"/>

<div class="container text-center mt-4">

    <h1>${title}</h1>
    <a class="btn btn-primary my-2" href="${pageContext.request.contextPath}/cursos/form">Crear [+]</a>

    <div class="form-container my-4">
        <form action="${pageContext.request.contextPath}/buscar" method="post" class="d-flex justify-content-center">
            <input type="text" id="nombre" name="nombre" class="form-control me-2" required>
            <input type="submit" value="Buscar" class="btn btn-secondary">
        </form>
    </div>

    <table class="table table-hover table-striped my-4">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Instructor</th>
                <th>Duración</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${cursos}" var="c">
            <tr>
                <td>${c.id}</td>
                <td>${c.nombre}</td>
                <td>${c.instructor}</td>
                <td>${c.duracion}</td>
                <td><a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/cursos/form?id=${c.id}">Editar</a></td>
                <td><a class="btn btn-sm btn-danger" onclick="return confirm('¿Está seguro que desea Eliminar?');"
                href="${pageContext.request.contextPath}/cursos/eliminar?id=${c.id}">Eliminar</a></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>

</div>

<jsp:include page="layout/footer.jsp"/>
