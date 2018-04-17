<%--
  Created by IntelliJ IDEA.
  User: Lee
  Date: 2018/4/17
  Time: 18:15
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
<form class="form-horizontal">
    <div class="form-group">
        <label class="col-sm-2 control-label">起始地区</label>
        <div class="col-sm-10">
            <p class="form-control-static">${houseBooking.area}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">所用车型</label>
        <div class="col-sm-10">
            <p class="form-control-static">${houseBooking.cartype}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">搬家时间</label>
        <div class="col-sm-10">
            <p class="form-control-static">${houseBooking.movedate}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">联系人</label>
        <div class="col-sm-10">
            <p class="form-control-static">${houseBooking.contact}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">联系电话</label>
        <div class="col-sm-10">
            <p class="form-control-static">${houseBooking.phone}</p>
        </div>
    </div>
     <div class="form-group">
         <label class="col-sm-2 control-label">状态</label>
         <div class="col-sm-10">
             <p class="form-control-static">
                 <c:if test="${houseBooking.status ==2}">
                     已结束
             </c:if>
             <c:if test="${houseBooking.status ==1}">
                 已派车
             </c:if>
             <c:if test="${houseBooking.status ==0}">
                 未处理
             </c:if></p>
            </div>
     </div>
</form>
<form class="form-horizontal" action="./queryAllHouseBooking.do" method="post">
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">返回</button>
        </div>
    </div>
</form>
</body>
</html>
