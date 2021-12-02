<%--
  Created by IntelliJ IDEA.
  User: zy
  Date: 2018/8/9
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>配色方案</title>
</head>
<body>

<div class="row">
    <div class="col-md-12">

        <h2 class="page-header">创建配色方案</h2>

        <form:form commandName="formDto" cssClass="form-horizontal">

            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">名称<em class="text-danger">*</em></label>

                <div class="col-sm-10">
                    <form:input path="name" cssClass="form-control" id="name"
                                required="required" maxlength="255"/>
                    <form:errors path="name" cssClass="label label-warning"/>
                    <p class="help-block">名称用来命名配色方案</p>
                </div>
            </div>

            <div class="form-group">
                <label for="backgroundColor" class="col-sm-2 control-label">页面背景颜色<em class="text-danger">*</em></label>

                <div class="col-sm-10">
                    <form:input path="backgroundColor" cssClass="form-control" id="backgroundColor"
                                placeholder="输入颜色，如：#ffffff"
                                required="required" maxlength="255"/>
                    <form:errors path="backgroundColor" cssClass="label label-warning"/>
                    <p class="help-block">页面背景颜色</p>
                </div>
            </div>

            <div class="form-group">
                <label for="formColor" class="col-sm-2 control-label">登录窗口背景颜色<em class="text-danger">*</em></label>

                <div class="col-sm-10">
                    <form:input path="formColor" cssClass="form-control" id="formColor"
                                placeholder="输入颜色，如：#ffffff"
                                required="required" maxlength="255"/>
                    <form:errors path="formColor" cssClass="label label-warning"/>
                    <p class="help-block">登录窗口背景颜色</p>
                </div>
            </div>

            <div class="form-group">
                <label for="fontColor" class="col-sm-2 control-label">字体颜色<em class="text-danger">*</em></label>

                <div class="col-sm-10">
                    <form:input path="fontColor" cssClass="form-control" id="fontColor"
                                placeholder="输入颜色，如：#ffffff"
                                required="required" maxlength="255"/>
                    <form:errors path="fontColor" cssClass="label label-warning"/>
                    <p class="help-block">字体颜色</p>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-2"></div>
                <div class="col-sm-10">
                    <button type="submit" class="btn btn-primary">创建</button>
                    <a href="../">返回</a>&nbsp;
                    <span><em class="text-danger">*</em> 是必填字段</span>
                </div>
            </div>
        </form:form>

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

