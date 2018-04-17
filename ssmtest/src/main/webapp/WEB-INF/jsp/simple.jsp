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
    <link  rel="stylesheet" href="../css/bootstrap.min.css" type="text/css">
</head>
<body>

<table class="table table-striped">
    <tr>
        <td>起始地区</td>
        <td>车型</td>
        <td>搬家时间</td>
        <td>联系人</td>
        <td>电话</td>
        <td>处理状态</td>
        <td>操作</td>

    </tr>
    <c:forEach items="${houseBookingList}" var="item">
    <tr>
        <td>${item.area}</td>
        <td>${item.cartype}</td>
        <td>${item.movedate}</td>
        <td>${item.contact}</td>
        <td>${item.phone}</td>
        <c:if test="${item.status ==2}">
            <td>已结束</td>
        </c:if>
        <c:if test="${item.status ==1}">
            <td>已派车</td>
        </c:if>
        <c:if test="${item.status ==0}">
            <td>未处理</td>
        </c:if>
        <td>
            <c:if test="${item.status !=2}">
                <input class="btn btn-default" type="button" value="处理" onclick="window.location.href='./queryHouseBooking.do?id=${item.id}&flag=1'">
            </c:if>
            <c:if test="${item.status ==2}">
                <input class="btn btn-default" type="button" value="处理" disabled="disabled">
            </c:if>
            <input class="btn btn-default" type="button" value="详情"
                   onclick="window.location.href='./queryHouseBooking.do?id=${item.id}&flag=0'"></td>
    </tr>
</c:forEach>
</table>
</body>
</html>
