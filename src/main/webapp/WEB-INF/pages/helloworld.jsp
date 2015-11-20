<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>欢迎</title>
</head>
<script src="http://apps.bdimg.com/libs/angular.js/1.3.9/angular.min.js"></script>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<body>
<h2>欢迎访问</h2>

<div ng-app="myApp" ng-controller="customersCtrl">
    <button ng-click="getjson()">获取json aa</button>
    <%--<ul>--%>
        <%--<li ng-repeat="item in items">--%>
            <%--{{ item.key }}--%>
        <%--</li>--%>
    <%--</ul>--%>
    <table class="table">
        <%--<caption>基本的表格布局</caption>--%>
        <thead>
        <tr>
            <th>key</th>
            <th>value</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="item in items">
            <td>Tanmay</td>
            <td>{{ item.key }}</td>
        </tr>
        </tbody>
    </table>

</div>
<script>
    var app = angular.module('myApp', []);
    app.controller('customersCtrl', function ($scope, $http) {
        $scope.getjson = function () {
            alert("11111111");
            $http.get("getjson")
                    .success(function (response) {
                        $scope.items = response;
                    });
        }
    });
</script>
</body>
</html>
