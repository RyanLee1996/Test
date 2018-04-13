<%--
  Created by IntelliJ IDEA.
  User: Lee
  Date: 2018/4/11
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>

</head>
<body>
<c:forEach items="studentList" var="student">
<table class="table table-striped">
    <tr>
        <td>${student.id}</td>
        <td>${student.name}</td>
        <td>${student.age}</td>
        <td>${student.profession}</td>
        <td>${student.classId}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
