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
<form class="form-horizontal" action="./updateHouseBooking.do">
    <div class="form-group">
        <label class="col-sm-2 control-label">起始地区</label>
        <div class="col-sm-10">
            <p class="form-control-static" >${houseBooking.area}</p>
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
    <input type="text" name="id" value="${houseBooking.id}" hidden/>
     <div class="form-group">
         <label class="col-sm-2 control-label">状态修改</label>
         <div class="col-sm-10">
             <p class="form-control-static">
                     <select name="status">
                         <c:if test="${houseBooking.status==0}">
                         <option value="1" >已派车</option>
                         </c:if>
                         <option value="2" >已结束</option>
                     </select>
                 <br>
             </p>
            </div>
     </div>
    <input type="submit" value="修改">
</form>
</body>
</html>
