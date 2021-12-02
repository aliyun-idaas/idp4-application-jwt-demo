<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="taglib-header.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>
    <script>
        <%--JS gloable varilible--%>
        var contextPath = "${contextPath}";
    </script>


    <meta name="viewport" content="width=device-width,user-scalable=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="${_csrf.headerName}" content="${_csrf.token}"/>
    <link rel="shortcut icon" href="${contextPath}/static/favicon.ico"/>

    <title><decorator:title default=""/> | Welcome</title>

    <link href="${contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <decorator:head/>

</head>
<style>
    h3{
        float: left;
        margin-left: 1em;
        line-height: 0.5em;
    }
    /*1*/
    .blue{
        color:#009fff9c;
    }
    .blue:focus, .blue:hover{
        color:#009fff9c
    }
    .btn-success{
        color: #fff;
    }
    .text-info{
        color:#00c2ff;
    }
    .heading-blue{
        color: #00c2ff !important;
        background-color: #00c2ff2e !important;
        border-color: #00c2ff2e !important;
    }
    .panel-info{
        border-color: #00c2ff2e
    }
    .panel-success{
        border-color: #00c2ff2e
    }
    .bg-blue{
        color: #fff;
        background:#009fff9c !important;
        border-color: #009fff9c;
    }
    /*2*/
    .navy{
        color:navy;
    }
    .navy:focus, .navy:hover{
        color:navy
    }
    .btn-success{
        color: #fff;
    }
    .text-info{
        color:navy;
    }
    .heading-navy{
        color: navy !important;
        background-color: #00008029 !important;
        border-color: #00008029 !important;
    }
    .panel-info{
        border-color: #00008029
    }
    .panel-success{
        border-color: #00008029
    }
    .bg-navy{
        color: #fff;
        background:navy !important;
        border-color: navy;
    }
    /*3*/
    .green{
        color:green;
    }
    .green:focus, .green:hover{
        color:green
    }
    .btn-success{
        color: #fff;
    }
    .text-info{
        color:green;
    }
    .heading-green{
        color: green !important;
        background-color: #00800036 !important;
        border-color: #00800036 !important;
    }
    .panel-info{
        border-color: #00800036
    }
    .panel-success{
        border-color: #00800036
    }
    .bg-green{
        color: #fff;
        background:green !important;
        border-color: green;
    }
    /*4*/
    .deepblue{
        color:#337ab7;
    }
    .deepblue:focus, .deepblue:hover{
        color:#337ab7
    }
    .btn-success{
        color: #fff;
    }
    .bg-deepblue{
        color: #fff;
        background:#337ab7 !important;
        border-color: #337ab7;
    }
    /*5*/
    .orange{
        color:orange;
    }
    .orange:focus, .orange:hover{
        color:orange
    }
    .btn-success{
        color: #fff;
    }
    .text-info{
        color:orange;
    }
    .heading-orange{
        color: orange !important;
        background-color: #ffa50038 !important;
        border-color: #ffa50038 !important;
    }
    .panel-info{
        border-color: #ffa50038
    }
    .panel-success{
        border-color: #ffa50038
    }
    .bg-orange{
        color: #fff;
        background:orange !important;
        border-color: orange;
    }
    /*6*/
    .lightgreen{
        color:#1eb51e;
    }
    .lightgreen:focus, .lightgreen:hover{
        color:#1eb51e
    }
    .btn-success{
        color: #fff;
    }
    .text-info{
        color:#1eb51e;
    }
    .heading-lightgreen{
        color: #1eb51e !important;
        background-color: #90ee903b !important;
        border-color: #90ee903b !important;
    }
    .panel-info{
        border-color: #90ee903b
    }
    .panel-success{
        border-color: #90ee903b
    }
    .bg-lightgreen{
        color: #fff;
        background:#1eb51e !important;
        border-color: #1eb51e;
    }
    /*7*/
    .red{
        color:#8D121D;
    }
    .red:focus, .red:hover{
        color:#8D121D
    }
    .btn-success{
        color: #fff;
    }
    .text-info{
        color:#8D121D;
    }
    .heading-red{
        color: #8D121D !important;
        background-color: #8d121d36 !important;
        border-color: #8d121d36 !important;
    }
    .panel-info{
        border-color: #8d121d36
    }
    .panel-success{
        border-color: #8d121d36
    }
    .bg-red{
        color: #fff;
        background:#8D121D !important;
        border-color: #8D121D;
    }
</style>

<body>
<nav class="navbar navbar-default navbar-static-top" id="${sessionScope.get("themeColor")}">
    <div class="container-fluid">

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <h3 class="color-h3">
                欢迎来到
                <c:choose>
                    <c:when test="${sessionScope.get('appName') =='' || sessionScope.get('appName') == null }">
                        ${sessionScope.get('title')}
                    </c:when>
                    <c:otherwise>
                        ${sessionScope.get('appName')}
                    </c:otherwise>
                </c:choose>
            </h3>

            <form action="${contextPath}/signout" class="navbar-form navbar-right" role="search" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-danger">退出</button>
                <c:if test="${null != sessionScope.get('logoutUrl')}">
                    <a class="btn btn-success" href="${sessionScope.get('logoutUrl')}">全局退出</a>
                </c:if>
            </form>
            <p class="navbar-text pull-right"><a
                    href="">${SPRING_SECURITY_CONTEXT.authentication.principal.username}</a></p>
        </div>
    </div>
</nav>

<div class="container-fluid">


    <div class="row">
        <div class="col-md-2">
            <div class="panel panel-success">
                <div class="panel-body">
                    <ul class="nav nav-pills nav-stacked" id="mainMenu">
                        <li role="presentation"><a href="${contextPath}/index"><em
                                class="glyphicon glyphicon-home"></em> <strong>首页</strong></a></li>


                        <li role="presentation" id="usersMenu"
                            class="${pageContext.request.requestURI.contains('user/')?'active':''}">
                            <a href="${contextPath}/user/management/list"><em class="glyphicon glyphicon-user"></em>
                                <strong>用户</strong></a>
                        </li>

                        <sec:authorize access="hasRole('ROLE_SYSTEM_ACCOUNT')">
                            <li role="presentation" id="systemSetting"
                                class="${pageContext.request.requestURI.contains('system')?'active':''}">
                                <a href="${contextPath}/system/login"><em class="glyphicon glyphicon-certificate"></em>
                                    <strong>系统配置</strong></a>
                            </li>
                        </sec:authorize>
                        <li role="presentation" id="ssoSetting"
                            class="${pageContext.request.requestURI.contains('sso')?'active':''}">
                            <a href="${contextPath}/sso/config/list"><em class="glyphicon glyphicon-certificate"></em>
                                <strong>单点登录配置</strong></a>
                        </li>

                        <%--</sec:authorize>--%>


                    </ul>
                </div>
            </div>
        </div>
        <div class="col-md-10">
            <div>
                <decorator:body/>
            </div>
        </div>
    </div>


    <div>
        <hr/>
        <p class="text-muted text-center">
            &copy; ${not empty sessionScope.copyright?sessionScope.copyright:'2014-2019 IDsManager.com'} &nbsp;Ver. ${jwtVersion}
        </p>
    </div>
</div>



<script src="${contextPath}/static/js/jquery.min.js"></script>
<script src="${contextPath}/static/js/jwt-v1.js"></script>
<script>
    /*BLUE("蓝色","blue"), //默认蓝色
    NAVYBLUE("海军蓝","navy"),
    GREEN("绿色","green"),
    DEEPBLUE("深蓝","#337ab7"),
    ORANGE("橘黄色","orange"),
    WINRED("酒红","#8D121D"),
    LIGHTGREEN("浅绿色","lightgreen");*/
    var  colors = $('.navbar-static-top')[0].id;
    console.log(colors)
    $(function(){

        if(colors == 'blue'){
            addClassFun('blue')

        }else if(colors == 'navy'){
            addClassFun('navy')

        }else if(colors == 'green'){
            addClassFun('green')

        }else if(colors == '#337ab7'){
            addClassFun('deepblue')

        }else if(colors == 'orange'){
            addClassFun('orange')

        }else if(colors == 'lightgreen'){
            addClassFun('lightgreen')

        }else if(colors == '#8D121D'){
            addClassFun('red')

        }
        function addClassFun(c){
            var b = c + ' heading-' + c;
            var d = 'bg-'+c;
            $('a,.color-h3').addClass(c);
            $('.alert-info').addClass(b);

            $('.pagination>.active>a,.pagination .previous a,.pagination .next a').addClass(b);
            $('.panel-info>.panel-heading').addClass(b);
            $('.nav-pills>li.active>a,.btn-primary').addClass(d);
        }
    })

</script>
</body>
</html>