<%--
 * 
 * @author Shaofei Du.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>应用列表</title>
</head>
<body>

<div class="row">
    <div class="col-md-12">

        <h2 class="page-header">授权应用列表</h2>


        <div class="box-body  un-paddingTop">
            <p class="alert alert-info marginLR15">免登应用</p>

            <!--应用开始-->
            <c:if test="${empty application_key}">
                <p class="alert alert-warning marginLR15">尚未获取到免登应用</p>
            </c:if>

            <c:if test="${not empty application_key}">
                <c:forEach items="${application_key}" var="app">
                    <c:if test="${app.supportDeviceTypes.contains('WEB')}">
                        <div class="btn-group" role="group" aria-label="...">
                            <a class="btn btn-success" href="${app.startUrl}" target="_blank">${app.name}</a>
                        </div>
                    </c:if>
                </c:forEach>

            </c:if>
            <p></p>

            <p class="alert alert-info marginLR15">仅支持移动端免登的应用</p>

            <c:if test="${application_key.size() > 0}">
                <!--应用开始-->
                <c:if test="${not empty application_key}">
                    <c:forEach items="${application_key}" var="app">
                        <c:if test="${app.supportDeviceTypes.size() == 1 && app.supportDeviceTypes.contains('MOBILE')}">
                            <div class="btn-group" role="group" aria-label="...">
                                <a class="btn btn-warning">${app.name}</a>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:if>
            <c:if test="${empty application_key}">
                <p class="alert alert-warning marginLR15">尚未获取到仅支持移动端的应用</p>
            </c:if>

            <p></p>

            <p class="alert alert-info marginLR15">网络控制应用</p>

            <c:if test="${application_key.size() > 0}">
                <!--应用开始-->
                <c:if test="${not empty application_key}">
                    <c:forEach items="${application_key}" var="app">
                        <c:if test="${app.supportDeviceTypes.contains('WIFI_AC') || app.supportDeviceTypes.contains('VPN')}">
                            <div class="btn-group" role="group" aria-label="...">
                                <a class="btn btn-warning">${app.name}</a>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:if>
            <c:if test="${empty application_key}">
                <p class="alert alert-warning marginLR15">尚未获取到网络控制应用</p>
            </c:if>

        </div>


    </div>

</div>
<script src="${contextPath}/static/js/jquery.min.js"></script>
<script src="${contextPath}/static/js/jwt-v1.js"></script>
<script>
    $(function () {
    });
</script>

</body>
</html>
