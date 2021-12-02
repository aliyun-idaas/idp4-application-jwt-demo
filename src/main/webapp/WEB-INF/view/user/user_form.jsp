<%--
 * 
 * @author Shengzhao Li
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>创建用户</title>
</head>
<body>

<div class="row">
    <div class="col-md-12">

        <h2 class="page-header">创建用户</h2>

        <form:form commandName="formDto" cssClass="form-horizontal">


            <div class="form-group">
                <label for="username" class="col-sm-2 control-label">用户名<em class="text-danger">*</em></label>

                <div class="col-sm-10">
                    <form:input path="username" cssClass="form-control" id="username"
                                placeholder="用户名称，长度大于6"
                                required="required" maxlength="255"/>
                    <form:errors path="username" cssClass="label label-warning"/>
                    <p class="help-block">用户名称是必填的，且唯一，长度大于等于6字节</p>
                </div>
            </div>

            <div class="form-group">
                <label for="username" class="col-sm-2 control-label">昵称<em class="text-danger">*</em></label>

                <div class="col-sm-10">
                    <form:input path="displayName" cssClass="form-control"
                                placeholder="昵称"
                                required="required" maxlength="255"/>
                    <form:errors path="displayName" cssClass="label label-warning"/>
                    <p class="help-block">昵称信息</p>
                </div>
            </div>

            <div class="form-group">
                <label for="username" class="col-sm-2 control-label">邮箱<em class="text-danger">*</em></label>

                <div class="col-sm-10">
                    <form:input path="email" cssClass="form-control" id="email"
                                placeholder="邮箱地址"
                                required="required" maxlength="255"/>
                    <form:errors path="email" cssClass="label label-warning"/>
                    <p class="help-block">邮箱地址, 唯一</p>
                </div>
            </div>

            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">密码<em
                        class="text-danger">*</em></label>

                <div class="col-sm-10">
                    <form:password path="password" cssClass="form-control" id="password"
                                   placeholder="密码，长度大于等于 8" required="required" maxlength="255"/>
                    <form:errors path="password" cssClass="label label-warning"/>
                    <p class="help-block">密码，长度大于等于 8</p>
                </div>
            </div>


            <div class="form-group">
                <div class="col-sm-2"></div>
                <div class="col-sm-10">
                    <button type="submit" class="btn btn-primary">创建</button>
                    <a href="../list">返回</a>&nbsp;
                    <span><em class="text-danger">*</em> 是必填字段</span>
                </div>
            </div>
        </form:form>

    </div>

</div>
<script src="${contextPath}/static/js/jquery.min.js"></script>
<script src="${contextPath}/static/js/jwt-v1.js"></script>


</body>
</html>
