<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>

    <meta charset="utf-8"/>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>

    <meta name="viewport" content="width=device-width,user-scalable=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="${_csrf.headerName}" content="${_csrf.token}"/>
    <link rel="shortcut icon" href="${contextPath}/static/favicon.ico"/>

    <title>登录 | JWT DEMO</title>

    <link href="${contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <style>
        html,body{
            width:100%;
            height:100%;
        }
        body {
            background: url("${contextPath}/static/images/loginbg.png");

        }
        #form {
            <%--background-color: ${loginPageConfig.formColor};--%>
            color: ${loginPageConfig.fontColor};
            height: 380px;
           /* padding: 0 54px;*/
            font-weight: normal;
            border-color: #dcdcdc;
            border: 0;
        }
        .form-control{
            padding:1em;
            border-radius:0;
            height: 45px;
        }
        #footer {
            color: ${loginPageConfig.fontColor};
            opacity: 0.5;
        }
        #title {
            margin: 0;
            color:#fff;
            padding: 1em 0;
        }
        /*#buttons {
            margin-top: 30px;
        }*/
        #loginButton {
            width:100%;
            height: 45px;
            border-radius:0;
            background-color: #092B76;
            border-color: #092B76;
        }
        .form-group {
            margin-bottom: 20px;
        }
        #IdpLoginButton {
            color: #00b0ec;
            background-color: ${loginPageConfig.formColor};
            border-color: #00b0ec;
        }
        .panel-body {
            padding: 15px 6em;
        }
        .row{
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            width:100%;
        }
        .container{
            position: relative;
            height: 100%;
            /*width: 32%;*/
            margin: 0 auto;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6">

            <div class="panel panel-primary" id="form">
                <div style="width:100%;background-color:#074F9C;" class="text-center">
                    <h3 class="form-title text-center" id="title">
                        <c:choose>
                            <c:when test="${sessionScope.get('appName') == null||sessionScope.get('appName') =='' }">
                                ${loginPageConfig.title}
                            </c:when>
                            <c:otherwise>
                                ${sessionScope.get("appName")}
                            </c:otherwise>
                        </c:choose>
                    </h3>
                </div>
                <div class="panel-body">

                    <form action="${pageContext.request.contextPath}/signin" method="post" class="form-horizontal" >
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <br>
                        <br>
                        <div class="form-group">
                            <%--<label for="username" class="col-sm-2 control-label">用户名</label>--%>

                            <div class="col-sm-12">
                                <input type="text" name="username" id="username" class="form-control"
                                       placeholder="请输入账户" required="" value=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <%--<label for="password" class="col-sm-2 control-label">密码</label>--%>

                            <div class="col-sm-12">
                                <input type="password" name="password" id="password" class="form-control"
                                       placeholder="请输入密码" required="" value=""/>
                            </div>
                        </div>

                        <div class="form-group" id="buttons">
                            <%--<label class="col-sm-2 control-label"></label>--%>

                            <div class="col-sm-12">
                                <button type="submit" class="btn btn-success" id="loginButton">
                                    <%--<em class="glyphicon glyphicon-log-in"></em>--%>
                                    登录
                                </button>
                                <c:if test="${null != systemConfig}">
                                    <c:if test="${null != systemConfig.spSsoUrl}">
                                        <a class="link" style="float:right; margin-top:.5em; color:#092B76;" href="${systemConfig.spSsoUrl}" target="_blank" id="IdpLoginButton">去IDP登录并SSO</a>
                                    </c:if>
                                </c:if>
                                &nbsp;
                                <c:if test="${param.authorization_error eq '2'}"><span class="label label-warning">权限不足 !!!</span></c:if>
                                <c:if test="${param.authentication_error eq '1'}"><span class="label label-warning">认证失败</span></c:if>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
            <div class="text-center text-muted" id="footer">&copy; ${copyright} &nbsp;Ver. ${jwtVersion}</div>
        </div>
    </div>
</div>

</body>
</html>