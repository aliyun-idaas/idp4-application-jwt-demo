<%--
 * 
 * @author Shengzhao Li
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dis" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>

<div class="pull-right">
    <a href="form/create" class="btn btn-success btn-sm"><em class="glyphicon glyphicon-plus-sign"></em> 创建用户</a>
</div>
<form action="" class="form-inline" id="filterForm">
    <div class="form-group">
        <%--<label for="name">Name</label>--%>
        <input type="text" class="form-control" id="username" placeholder="Type username" title="Type username"
               name="username"
               value="${paginated.username}"/>
    </div>
    <button type="submit" class="btn btn-default"><em class="glyphicon glyphicon-search"></em></button>
    &nbsp;<span class="text-info">共: ${paginated.totalSize} 个用户</span>
</form>
<br/>

<dis:table list="${paginated}" id="d" form="filterForm" class="table table-striped table-hover">
    <dis:column  title="用户名">${d.username} <em title="UUID: ${d.uuid}" class="glyphicon glyphicon-info-sign"></em></dis:column>
    <dis:column property="displayName" title="昵称"/>
    <dis:column title="邮箱">${d.email()}</dis:column>
    <dis:column property="privileges" title="权限"/>
    <dis:column property="createTime" title="创建时间"/>
    <dis:column title="用户类型">
        ${d.type.label}
    </dis:column>
</dis:table>

</body>
</html>
