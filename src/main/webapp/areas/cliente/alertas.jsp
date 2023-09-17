<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${!empty(alerta)}">
    <div class="container row">
        <div class="col-10">
            <div class="alert alert-warning alert-dismissible" >
                <a class="close" data-dismiss="alert">x</a>
                    ${alerta}
            </div>
        </div>
    </div>
</c:if>
<c:if test="${!empty(success)}">
    <div class="row">
        <div class="col-10">
            <div class="alert alert-success alert-dismissible">
                <a class="close" data-dismiss="alert" data-bs-close="alert">&times;</a>
                    ${success}
            </div>
        </div>
    </div>
</c:if>