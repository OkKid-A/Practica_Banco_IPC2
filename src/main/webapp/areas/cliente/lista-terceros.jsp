<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="listSelecTerceros" class="table table-bordered table-striped">
    <thead>
    <tr>
        <th>ID Cuenta</th>
        <th>Propietario</th>
        <th>Fecha de Creacion</th>
        <th>Saldo</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="cuentaP" items="${cuentasTerceros}" varStatus="status" >
        <tr>
            <td>${cuentaP.codigo}</td>
            <td>${cuentaP.fechaCreacion}</td>
            <td>${cuentaP.saldo}</td>
            <td>
                <form id="form-selecTerceros" action="${pageContext.request.contextPath}/cliente/transferir-servlet?cuenta=${cuenta.codigo}&receptora=${cuentaP.codigo}" method="post">
                    <button class="btn btn-dark mb-0" id="fin3p" type="submit" >
                        Seleccionar </button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>