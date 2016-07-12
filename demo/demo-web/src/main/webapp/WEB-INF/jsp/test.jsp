<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
   <script src="/js/jquery-2.1.1.js"></script>
   <script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.js"></script>

<script>
  
   var app = angular.module('myApp', []);

   app.controller('myCtrl', function($scope) {
   
  $scope.district = [//区域代码与名称对应
        {
            "code": "11",
            "name": "北京"
        },
        {
            "code": "12",
            "name": "天津"
        },
        {
            "code": "31",
            "name": "上海"
        },
        {
            "code": "50",
            "name": "重庆"
        },
        {
            "code": "13",
            "name": "河北"
        },
        {
            "code": "41",
            "name": "河南"
        },
        {
            "code": "53",
            "name": "云南"
        },
        {
            "code": "21",
            "name": "辽宁"
        },
        {
            "code": "23",
            "name": "黑龙江"
        },
        {
            "code": "43",
            "name": "湖南"
        },
        {
            "code": "34",
            "name": "安徽"
        },
        {
            "code": "37",
            "name": "山东"
        },
        {
            "code": "65",
            "name": "新疆"
        },
        {
            "code": "32",
            "name": "江苏"
        },
        {
            "code": "33",
            "name": "浙江"
        },
        {
            "code": "36",
            "name": "江西"
        },
        {
            "code": "42",
            "name": "湖北"
        },
        {
            "code": "45",
            "name": "广西"
        },
        {
            "code": "62",
            "name": "甘肃"
        },
        {
            "code": "14",
            "name": "山西"
        },
        {
            "code": "15",
            "name": "内蒙古"
        },
        {
            "code": "61",
            "name": "陕西"
        },
        {
            "code": "22",
            "name": "吉林"
        },
        {
            "code": "35",
            "name": "福建"
        },
        {
            "code": "52",
            "name": "贵州"
        },
        {
            "code": "44",
            "name": "广东"
        },
        {
            "code": "63",
            "name": "青海"
        },
        {
            "code": "54",
            "name": "西藏"
        },
        {
            "code": "51",
            "name": "四川"
        },
        {
            "code": "64",
            "name": "宁夏"
        },
        {
            "code": "46",
            "name": "海南"
        },
        {
            "code": "71",
            "name": "台湾"
        },
        {
            "code": "91",
            "name": "香港"
        },
        {
            "code": "92",
            "name": "澳门"
        }];

    });


</script>



</html>
<body ng-app="myApp" ng-controller="myCtrl">

 <select id='area' multiple="multiple" style="width: 80%;height:50%" ng-model="myselect">
   <option ng-repeat="district in district" value="{{district.code}}">{{district.name}}</option>
 </select>
 <input type="test" value="{{myselect}}"/>
</body>

</html>