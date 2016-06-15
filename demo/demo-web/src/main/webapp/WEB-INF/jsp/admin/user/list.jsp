<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
  
    var grid = new createTable({
	    path: 'user',
	    autoQuery: false,
	    pageSize: 15,
	  });

</script>
</head>
<div  ng-app="myApp" ng-controller="myController">
  <table>
    <tr ng-repeat="item in data">
       <td>{{item.nickname}}</td>
    </tr>
  </table>
</div>

