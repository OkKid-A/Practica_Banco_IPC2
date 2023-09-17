<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Solicitud</title>
    <jsp:include page="/includes/resources.jsp"/>
    <jsp:include page="/includes/header-cliente.jsp"/>
</head>
<body>
<jsp:include page="alertas.jsp"/>
<section>
<div class="container rounded bg-white mt-5 mb-5 justify-content-center">
    <form id="form-nuevaSolicitud" action="${pageContext.request.contextPath}/cliente/solicitud-servlet" method="post">
        <div class="row">
            <div class="col-md-5">
                <div class="p-3 py-5">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h4 class="text-right">Crea un Nuevo Cliente:</h4>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-12"><label class="labels">Nombre</label>
                            <input type="text" class="form-control" name="name" id="name" value="" maxlength="60">
                        </div>
                        <div class="col-md-12"><label class="labels">Nombre de usuario</label>
                            <input type="text" class="form-control" name="username" id="username" value="" maxlength="45">
                        </div>
                        <div class="col-md-12"><label class="labels">Email</label>
                            <input type="text" class="form-control" name="email" id="email" value="">
                        </div>
                        <div class="col-md-12"><label class="labels">Contraseña</label>
                            <input type="password" class="form-control" name="password" id="password" value="" maxlength="32">
                        </div>
                        <div class="col-md-12"><label class="labels">Saldo Inicial</label>
                            <input class="form-control" name="saldo" id ="saldo" type="text" inputmode="decimal" pattern="[0-9]+([\.,][0-9][0-9])?">
                        </div>
                    </div>
                    <div class="mt-5 text-center">
                        <button class="btn btn-primary" type="submit">Guardar Cambios</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</section>
<section class="header-main border-bottom bg-white pt-5">
    <h5 class="text-center" style="color: #000000"> Cuentas Asociadas de Terceros </h5>
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
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>
</body>
</html>
