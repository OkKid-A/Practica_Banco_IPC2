<%--
  Created by IntelliJ IDEA.
  User: okkid-a
  Date: 8/27/23
  Time: 7:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/includes/resources.jsp"/>
<header>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-white shadow fixed-top " style="color: #e50d0b">
        <a class="navbar-brand" href="">Where's The Money</a>
        <div class="container-fluid">
            <button
                    class="navbar-toggler"
                    type="button"
                    data-mdb-toggle="collapse"
                    data-mdb-target="#navbarExample01"
                    aria-controls="navbarExample01"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
            >
                <i class="fa fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarExample01">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item active">
                        <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/cliente/inicio-servlet">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/cliente/transferir-servlet">Transferencias</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/cliente/solicitud-servlet">Solicitudes</a>
                    </li>
                </ul>
            </div>
            <div class="d-flex align-items-center">
                <span class="navbar-text mr-1">
                    Cliente: ${cliente.nombre}
                </span>
                <a class="btn btn-dark px-3" href="${pageContext.request.contextPath}/login/inicio-servlet"
                   role="button"><i class="fa fa-sign-out" aria-hidden="true"></i>Sign Out</a>
            </div>
        </div>
    </nav>
    <!-- Navbar -->

</header>
