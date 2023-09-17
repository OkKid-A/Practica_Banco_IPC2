<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transferencias</title>
    <jsp:include page="/includes/resources.jsp"/>
    <jsp:include page="/includes/header-cliente.jsp"/>
<body>
<div class="pt-5 mt-4">
    <jsp:include page="alertas.jsp"/>
    <c:choose>
    <c:when test="${empty(cuenta)}">
        <div id="selectCuenta">
            <h3 class="text-center" style="color: #000000"> Selecciona la cuenta de origen: </h3>
            <section class="header-main border-bottom bg-white">
                <div class="container">
                    <table id="listCuenta" class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>ID Cuenta</th>
                            <th>Fecha de Creacion</th>
                            <th>Saldo</th>
                            <th>Seleccionar</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="cuentaP" items="${cuentasPropias}" varStatus="status" >
                            <tr>
                                <td>${cuentaP.codigo}</td>
                                <td>${cuentaP.fechaCreacion}</td>
                                <td>${cuentaP.saldo}</td>
                                <td>
                                    <form id="form-finalizar" action="${pageContext.request.contextPath}/cliente/transferir-servlet?cuenta=${cuentaP.codigo}" method="post">
                                        <button class="btn btn-dark mb-0" id="finalizar" type="submit" >
                                            Seleccionar </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </section>
        </div>
    </c:when>
    <c:when test="${!empty(cuenta)}">
        <h4 class="text-center" style="color: #000000"> Cuenta seleccionada: </h4>
        <section class="header-main border-bottom bg-white">
            <div class="container">
                <table id="cuenta" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>ID Cuenta</th>
                        <th>Fecha de Creacion</th>
                        <th>Saldo</th>
                        <th>Cambiar</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${cuenta.codigo}</td>
                            <td>${cuenta.fechaCreacion}</td>
                            <td>${cuenta.saldo}</td>
                            <td>
                                <form id="form-change" action="${pageContext.request.contextPath}/cliente/transferir-servlet" method="get">
                                    <button class="btn btn-dark mb-0" id="change" type="submit" >
                                        Cambiar </button>
                                </form>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </section>
        <c:choose>
            <c:when test="${empty(receptor)}">
                <h4 class="text-center" style="color: #000000"> Selecciona la cuenta receptora: </h4>
                <section class="header-main border-bottom bg-white">
                    <h5 class="text-center" style="color: #000000"> Cuentas Propias </h5>
                    <div class="container">
                        <table id="listSelecPropia" class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>ID Cuenta</th>
                                <th>Fecha de Creacion</th>
                                <th>Saldo</th>
                                <th>Seleccionar</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="cuentaP" items="${cuentasPropias}" varStatus="status" >
                                <tr>
                                    <td>${cuentaP.codigo}</td>
                                    <td>${cuentaP.fechaCreacion}</td>
                                    <td>${cuentaP.saldo}</td>
                                    <td>
                                        <form id="form-selecCuenta" action="${pageContext.request.contextPath}/cliente/transferir-servlet?cuenta=${cuenta.codigo}&receptora=${cuentaP.codigo}" method="post">
                                            <button class="btn btn-dark mb-0" id="fin" type="submit" >
                                                Seleccionar </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </section>
                <section class="header-main border-bottom bg-white">
                    <h5 class="text-center" style="color: #000000"> Cuentas de Terceros </h5>
                    <div class="container">
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
                                    <td>${cuentaP.nombreCliente}</td>
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
                    </div>
                </section>
            </c:when>
            <c:when test="${!empty(receptor)}">
                <h5 class="text-center" style="color: #000000"> Cuenta receptora: </h5>
                <section class="header-main border-bottom bg-white">
                    <div class="container">
                        <table id="cuentaReceptora" class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>ID Cuenta</th>
                                <th>Fecha de Creacion</th>
                                <th>Saldo</th>
                                <th>Cambiar</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${receptor.codigo}</td>
                                <td>${receptor.fechaCreacion}</td>
                                <td>${receptor.saldo}</td>
                                <td>
                                    <form id="form-receptor" action="${pageContext.request.contextPath}/cliente/transferir-servlet?cuenta=${cuenta.codigo}" method="post">
                                        <button class="btn btn-dark mb-0" id="changeReceptor" type="submit" >
                                            Cambiar </button>
                                    </form>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </section>
                <section>
                    <div class="container justify-content-center">
                        <form id="form-transferir" action="${pageContext.request.contextPath}/cliente/transferir-servlet?cuenta=${cuenta.codigo}&receptora=${receptor.codigo}&action=transferir" method="POST">
                                    <div class="modal-body">
                                        <p>Saldo Actual en la cuenta de origen: $${cuenta.saldo}</p>
                                        <label for="nuevoSaldo">Ingresa la cantidad que deseas transferir</label>
                                        <input class="form-control" name="nuevoSaldo" id="nuevoSaldo" max="${cuenta.saldo}"  pattern="[0-9]+([\.,][0-9][0-9])?" type="number" inputmode="decimal" step="0.01">
                                    </div>
                                    <div class="modal-footer justify-content-center">
                                        <button type="submit" class="btn btn-primary" form="form-transferir">Aceptar</button>
                                    </div>
                        </form>
                    </div>
                </section>
            </c:when>
        </c:choose>
    </c:when>
</c:choose>
</div>
</body>
</html>
