
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dis" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<style>
    .table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th, .table>thead>tr>td, .table>thead>tr>th {
        table-layout: fixed;
        word-wrap: break-word;
        width: 800px;
    }
</style>
<head>
    <title>SPURL 列表</title>
</head>
<body>
<div class="row box-header-box">
    <tags:system_tab active="5"/>
</div>

<div class="row">
    <h2 class="page-header">SP LOGIN URL设置</h2>
    <div class="alert alert-info">
        用于第三方业务系统单点登录图标配置
    </div>
<div class="pull-right">
    <a href="splogin/creat" class="btn btn-success btn-sm"><em class="glyphicon glyphicon-plus-sign"></em> 添加SP URL配置</a>
</div>

<br/>

<dis:table list="${spLoginDtos}" id="d" form="filterForm" class="table table-striped table-hover">
    <dis:column title="名称">
        ${d.spName}
    </dis:column>
    <dis:column title="SP URL">
        ${d.spLoginUrl}
    </dis:column>
    <dis:column  title="操作">
        <a href="splogin/update_${d.uuid}">修改</a>
        <a href="splogin/delete_${d.uuid}">删除</a>
    </dis:column>
</dis:table>
</div>
</body>
</html>
