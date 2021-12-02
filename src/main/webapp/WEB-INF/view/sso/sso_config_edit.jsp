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

        <h2 class="page-header">Update SSO Config</h2>

        <form:form commandName="formDto" cssClass="form-horizontal">

            <input type="hidden" name="ssoUrlId" value="${formDto.ssoUrlId}"/>
            <input type="hidden" name="ssoUrl" value="${formDto.ssoUrl}"/>
            <input type="hidden" name="oldName" value="${formDto.oldName}"/>

            <div class="form-group">
                <label class="control-label col-sm-2">SSO URL</label>
                <div class="col-sm-10">
                    <form:input path="name" cssClass="form-control"
                                placeholder="Type name,唯一" value="${formDto.ssoUrl}"
                                required="required" maxlength="255" disabled="true"/>
                    <p class="help-block">地址为单点登录地址，不可修改</p>
                </div>
            </div>

            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">名称<em class="text-danger">*</em></label>

                <div class="col-sm-10">
                    <form:input path="name" cssClass="form-control"
                                placeholder="Type name,唯一" value="${formDto.name}"
                                required="required" maxlength="255"/>
                    <form:errors path="name" cssClass="label label-warning"/>
                    <p class="help-block">名称是唯一的，必填</p>
                </div>
            </div>

            <div class="form-group">
                <label for="publicKey" class="col-sm-2 control-label">publicKey<em class="text-danger">*</em></label>
                <div class="col-sm-10">
                    <form:textarea path="publicKey" cssClass="form-control" id="publicKey"
                                   placeholder="Type publicKey,get from SERVER,unique" value="${formDto.publicKey}"
                                   required="required"/>
                    <form:errors path="publicKey" cssClass="label label-warning"/>
                    <p class="help-block">publicKey必填，请去IDP中应用详情中复制并粘贴</p>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-2"></div>
                <div class="col-sm-10">

                    <button type="submit" class="btn btn-primary">Save</button>
                    <a href="./list">Back</a>&nbsp;

                </div>
            </div>
        </form:form>

    </div>

</div>
<script src="${contextPath}/static/js/jquery.min.js"></script>
<script src="${contextPath}/static/js/jwt-v1.js"></script>

</body>
</html>
