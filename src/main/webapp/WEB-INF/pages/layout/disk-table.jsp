<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/24 0024
  Time: 23:53
  To change this template use FileMode | Settings | FileMode Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="table table-striped  table-hover">

    <tr>
        <th width="150">文件名</th>
        <th width="150">大小</th>
        <th width="150">类型</th>
        <th width="150">位置</th>
    </tr>
    <c:forEach items="${fileModes}" var="fileMode">
        <tr>
            <td>${fileMode.fileName}</td>
            <td>${fileMode.fileSize} Kb</td>
            <td>${fileMode.fileType}</td>
            <td>${fileMode.filePath}</td>
        </tr>
    </c:forEach>
</table>
