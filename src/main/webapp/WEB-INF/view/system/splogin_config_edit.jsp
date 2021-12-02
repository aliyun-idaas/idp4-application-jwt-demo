<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>New SSO Config</title>
</head>
<body>

<div class="row">
    <div class="col-md-12">

        <h2 class="page-header">Update SP LOGIN URL Config</h2>

        <form:form commandName="formDto" cssClass="form-horizontal">

            <%--<input type="hidden" name="ssoUrlId" value="${formDto.ssoUrlId}"/>--%>
            <%--<input type="hidden" name="ssoUrl" value="${formDto.ssoUrl}"/>--%>

            <div class="form-group">
                <label for="spName" class="col-sm-2 control-label">名称<em class="text-danger">*</em></label>

                <div class="col-sm-10">
                    <form:input path="spName" cssClass="form-control"
                                placeholder="sp name"
                                required="required" maxlength="255"/>
                    <form:errors path="spName" cssClass="label label-warning"/>
                    <p class="help-block">业务系统名称</p>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2">SP URL</label>
                <div class="col-sm-10">
                    <form:input path="spLoginUrl" cssClass="form-control"
                                placeholder="http://..." value=""
                                required="required" maxlength="255" />
                    <form:errors path="spLoginUrl" cssClass="label label-warning"/>
                    <p class="help-block">地址为单点登录地址</p>
                </div>
            </div>




            <div class="form-group">
                <div class="col-sm-2"></div>
                <div class="col-sm-10">

                    <button type="submit" class="btn btn-primary">Save</button>
                    <a href="../splogin">Back</a>&nbsp;

                </div>
            </div>
        </form:form>

    </div>

</div>
<script src="${contextPath}/static/js/jquery.min.js"></script>
<script src="${contextPath}/static/js/jwt-v1.js"></script>

</body>
</html>
