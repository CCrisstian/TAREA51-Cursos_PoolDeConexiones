<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp" />

<div class="container text-center mt-4">
<h1>${title}</h1>
<a class="btn btn-secondary" href="${pageContext.request.contextPath}/cursos">Volver</a>
<form action="${pageContext.request.contextPath}/cursos/form" method="post" class="p-3">
    <div class="form-group row">
        <label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="nombre" id="nombre" value="${curso.nombre != null? curso.nombre : ""}">
            <c:if test="${errores != null && errores.containsKey('nombre')}">
                <div class="text-danger">${errores.nombre}</div>
            </c:if>
        </div>
    </div>
    <div class="form-group row">
        <label for="instructor" class="col-sm-2 col-form-label">Instructor</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="instructor" id="instructor" value="${curso.instructor != null? curso.instructor : ""}">
            <c:if test="${errores != null && errores.containsKey('instructor')}">
                <div class="text-danger">${errores.instructor}</div>
            </c:if>
        </div>
    </div>
    <div class="form-group row">
        <label for="duracion" class="col-sm-2 col-form-label">Duración</label>
        <div class="col-sm-10">
            <input type="number" class="form-control" name="duracion" id="duracion" value="${curso.duracion != 0 ? curso.duracion : ""}">
            <c:if test="${errores != null && errores.containsKey('duracion')}">
                <div class="text-danger">${errores.duracion}</div>
            </c:if>
        </div>
    </div>
    <div class="form-group row">
        <label for="descripcion" class="col-sm-2 col-form-label">Descripción</label>
        <div class="col-sm-10">
            <textarea class="form-control" name="descripcion" rows="5" id="descripcion">${curso.descripcion != null ? curso.descripcion : ""}</textarea>
            <c:if test="${errores != null && errores.containsKey('descripcion')}">
                <div class="text-danger">${errores.descripcion}</div>
            </c:if>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-sm-2 offset-sm-2">
            <input class="btn btn-primary my-2" type="submit" value="${curso.id > 0 ? "Editar" : "Crear"}">
        </div>
    </div>
</form>
</div>
<jsp:include page="layout/footer.jsp" />