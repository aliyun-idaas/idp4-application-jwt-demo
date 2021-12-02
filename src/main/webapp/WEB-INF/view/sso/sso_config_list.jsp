
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dis" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>SSO 列表</title>
</head>
<body>
<style>
    .table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th, .table>thead>tr>td, .table>thead>tr>th {
        table-layout: fixed;
        word-wrap: break-word;
        max-width: 800px;
    }
</style>
<div class="pull-right">
    <a href="form/create" class="btn btn-success btn-sm"><em class="glyphicon glyphicon-plus-sign"></em> 添加SSO配置</a>
</div>
<form action="" class="form-inline" id="filterForm">
    <div class="form-group">
        <input type="text" class="form-control" id="name" placeholder="name" title="Type name"
               name="name"
               value="${paginated.name}"/>
    </div>
    <button type="submit" class="btn btn-default"><em class="glyphicon glyphicon-search"></em></button>
    &nbsp;<span class="text-info">Total: ${paginated.totalSize} </span>
</form>
<br/>

<dis:table list="${paginated}" id="d" form="filterForm" class="table table-striped table-hover">
    <dis:column title="名称">
        ${d.name}
    </dis:column>
    <dis:column title="SSO URL">
        ${d.ssoUrl}
    </dis:column>
    <dis:column  title="公钥">
        ${d.publicKey}
    </dis:column>
    <dis:column  title="操作">
        <a href="update_${d.uuid}">修改</a>
        <a href="delete_${d.uuid}">删除</a>
    </dis:column>
</dis:table>

</body>
</html>
