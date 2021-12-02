<%--
  Created by IntelliJ IDEA.
  User: zy
  Date: 2018/8/9
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<html>
<head>
    <title>登录页面设置</title>
</head>
<body>

<div class="row box-header-box">
    <tags:system_tab active="4"/>
</div>

<div class="row">
    <div class="col-md-12">

        <h2 class="page-header">登录页面设置</h2>
        <div class="alert alert-info">
            此处配置用于登录页面的配置
        </div>
        <form:form commandName="formDto" cssClass="form-horizontal">

            <div class="form-group">
                <label class="control-label col-sm-2">标题<em class="text-danger">*</em></label>

                <div class="col-sm-9">
                    <form:input path="title" cssClass="form-control" required="true"
                                placeholder="Login JWT DEMO"/>
                    <form:errors path="title" cssClass="label label-warning"/>
                    <p class="help-block">登录界面的标题设置，如"Login JWT DEMO"</p>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2">配色方案<em class="text-danger">*</em></label>
                <div class="col-sm-9">
                    <form:select path="themeId" cssClass="form-control" required="true">
                        <form:options items="${themes}"/>
                    </form:select>
                    <a href="login/theme/create" class="btn btn-sm btn-success"><em class="glyphicon glyphicon-plus-sign"></em> 新建配色方案置</a>
                    <%--<form:input path="loginTheme" cssClass="form-control" required="true"--%>
                                <%--placeholder="Login JWT DEMO"/>--%>
                    <%--<form:errors path="loginTheme" cssClass="label label-warning"/>--%>
                    <p class="help-block">开启SCIM同步后，会自动同步账户</p>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-2"></div>
                <div class="col-sm-10">
                    <button type="submit" class="btn btn-primary"><em class="glyphicon glyphicon-ok"></em> 保存设置</button>
                    <c:if test="${'saveOK' eq param.alert}"><span
                            class="label label-success">保存设置完成</span></c:if>
                </div>
            </div>
        </form:form>
    </div>

</div>
</body>
</html>
