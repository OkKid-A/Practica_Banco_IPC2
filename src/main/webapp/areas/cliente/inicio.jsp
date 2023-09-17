<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Perfil</title>
    <jsp:include page="/includes/resources.jsp"/>
    <jsp:include page="/includes/header-cliente.jsp"/>
</head>
<body>
<c:if test="${!empty(alerta)}">
    <div class="row">
        <div class="col-10">
            <div class="alert alert-warning alert-dismissible" id="alert" name="alert">
                <button type="button" class="btn-close" data-bs-dismiss="alert">x</button>
                    ${alerta}
            </div>
        </div>
    </div>
</c:if>
<c:if test="${!empty(cuentas)}">
<div class="pt-5 mt-4">
    <section class="header-main border-bottom bg-white">
        <div class="container">
            <table id="listado" class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>Numero de Cuenta</th>
                    <th>Fecha de Creacion</th>
                    <th>Saldo</th>
                    <th>Transferencias</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="cuenta" items="${cuentas}" varStatus="status" >
                    <tr>
                        <td>${cuenta.codigo}</td>
                        <td>${cuenta.fechaCreacion}</td>
                        <td>$${cuenta.saldo}</td>
                        <td>
                            <form id="form-finalizar" action="${pageContext.request.contextPath}/cliente/inicio-servlet ?solicitud=${solicitud.id}" method="post">
                                <button class="btn btn-dark mb-0" id="finalizar" type="submit" >
                                    Ver </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
</div>
</c:if>
</body>
</html>
