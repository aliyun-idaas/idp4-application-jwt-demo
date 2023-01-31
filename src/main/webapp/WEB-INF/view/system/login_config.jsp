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
    <title>系统配置</title>
</head>
<body>

<div class="row">
    <div class="col-md-12">

        <h2 class="page-header">系统配置</h2>
        <div class="alert alert-info">
            此处配置用于登录页面的配置
        </div>
        <form:form modelAttribute="formDto" cssClass="form-horizontal">

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
                <label class="col-sm-2 control-label">全局退出登录</label>

                <div class="col-sm-9">
                    <form:textarea path="spLogoutUrl" cssClass="form-control"
                                   placeholder="http://..."/>
                    <form:errors path="spLogoutUrl" cssClass="label label-warning"/>
                    <p class="help-block">在当前系统(SP)进行SSO成功后, SP应用中若需要全局退出时,可调用SP提供的退出URL进行全局,配置后可在右上角显示"全局退出". 如:
                        http://xxx.com/public/sp/logout/szjwt</p>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">SP SSO 地址</label>

                <div class="col-sm-9">
                    <form:textarea path="spSSoUrl" cssClass="form-control"
                                   placeholder="http://..."/>
                    <form:errors path="spSSoUrl" cssClass="label label-warning"/>
                    <p class="help-block">
                        如果你配置了使用SP发起SSO流程,那么请输入在IDP注册JWT应用后生成的地址,如:https://xxx.com/public/sp/sso/internetjwt1,在下次登录时可选择去IDP认证并SSO.</p>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2"></div>
                <div class="col-sm-10">
                    <button type="submit" class="btn btn-primary"><em class="glyphicon glyphicon-ok"></em> 保存设置
                    </button>
                    <c:if test="${'saveOK' eq param.alert}"><span
                            class="label label-success">保存设置完成</span></c:if>
                </div>
            </div>
        </form:form>
    </div>

</div>
</body>
</html>
