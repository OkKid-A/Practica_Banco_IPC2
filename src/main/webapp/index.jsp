<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <jsp:include page="/includes/resources.jsp"/>
</head>
<style>
    body{
        background-color: #4c9fc4;
    }
</style>
<body>
<div class="pt-5" >
    <div class="container blue" >
        <h1 class="text-center" style="color: #ffffff"> Where's The Money </h1>
        <div class="row">
            <div class="col-md-5 mx-auto">
                <div class="card-transparent card-body" style="background-color: #ffffff">
                    <form id="submitForm" action="${pageContext.request.contextPath}/login/inicio-servlet" method="POST" data-parsley-validate=""
                          data-parsley-errors-messages-disabled="true">
                        <div class="form-group required">
                            <label class="d-flex flex-row align-items-center" for="password"> Ingresa tu Codigo Unico</label>
                            <input type="password" class="form-control" required id="password" name="password" style=
                                    "color: black" placeholder="Contraseña">
                        </div>
                        <div class="form-group pt-0 justify-content-center">
                            <button class="btn btn-primary btn-block" type="submit"> Iniciar Sesion</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="pb-5 text-center">
    <label class="text-align-center copyright" style="color: #ffffff"> @ 2023 Gomu Gomu no Code</label>
</div>
</body>
</html>