<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>菜单管理</title>
    <%@include file="../css.inc.jsp"%>
    <%@include file="../js.inc.jsp"%>
    <link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.2.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/angular-ui-tree.css">
    <link rel="stylesheet" href="/css/tree.app.css">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <script src="http://cdn.bootcss.com/angular-ui-bootstrap/0.11.2/ui-bootstrap-tpls.js"></script>
    <script src="/js/angular-ui-tree.js"></script>
  
  <script type="text/javascript">
    
      //'use strict';
      
        var content = '<form class="form-horizontal">' + 
              '<div class="form-group">' + 
                '<label for="name" class="col-sm-2 control-label">名称</label>' + 
                '<div class="col-sm-8">' + 
                  '<input type="text" class="form-control" id="name" value="{name}" placeholder="">' + 
                '</div>' + 
              '</div>' + 
              '<div class="form-group">' + 
                '<label for="path" class="col-sm-2 control-label">路径</label>' + 
                '<div class="col-sm-8">' + 
                  '<input type="text" class="form-control" id="path" value="{path}" placeholder="">' + 
                '</div>' + 
              '</div>' + 
              '<div class="form-group">' + 
                '<label for="sort" class="col-sm-2 control-label">排序</label>' + 
                '<div class="col-sm-8">' + 
                  '<input type="text" class="form-control" id="sort" value="{sort}" placeholder="">' + 
                '</div>' + 
              '</div>' + 
               '<div class="form-group" style="display:none">' +
                '<label for="sort" class="col-sm-2 control-label">样式</label>' + 
                '<div class="col-sm-8">' + 
                  '<input type="text" class="form-control" id="style" value="{style}" placeholder="">' + 
                '</div>' + 
               '</div>' + 
            '</form>'; 


        var app = angular.module('app', ['ui.tree', 'ui.bootstrap']);
      
        app.controller('ctrl', ['$scope', function ($scope) {

            $scope.delNode = function (scope) {
                $.confirm({
                    title: false,
                    confirmButton: '确定',
                    cancelButton: '取消',
                    confirmButtonClass: 'btn-danger',
                    cancelButtonClass: 'btn-info', 
                    content: '确定要删除该菜单吗?',
                    confirm: function(){
                        $scope.service.delete(scope.$modelValue.id);
                        $scope.$apply(function(){
                            $scope.query();
                        });
                    }
                })
            };

            $scope.service = new rpc.ServiceProxy("/services/menu", {
               asynchronous : false,
               methods : [ 'add', 'delete', 'getList', 'update']
            });

            $scope.submit = function(item){
                if(item.id){
                    return $scope.service.update(item);
                }else{
                    return $scope.service.add(item);
                }
            }

            $scope.query = function(){
                var data = $scope.service.getList();
                $scope.data = data;
            }

            $scope.query();

            $scope.edit = function (scope) {
                var nodeData = scope.$modelValue

                var name = nodeData.title ;
                var path = nodeData.path?nodeData.path:'';
                var style = nodeData.style?nodeData.style:'';
                var sort = nodeData.sort?nodeData.sort:1;
                var s = content.replace('{name}', name).replace('{path}', path).replace('{sort}', sort).replace('{style}', style);
                if(nodeData.parentId==0){
                    s = s.replace('display:none', '');
                }
                $.confirm({
                    title: false,
                    confirmButton: '保存',
                    cancelButton: '取消',
                    confirmButtonClass: 'btn-danger',
                    cancelButtonClass: 'btn-info', 
                    content: s,
                    confirm: function(){
                        var name = angular.element('#name').val();
                        var path = angular.element('#path').val();
                        var sort = angular.element('#sort').val();
                        var style = angular.element('#style').val();
                        $scope.$apply(function(){
                            $scope.submit({
                                id: nodeData.id,
                                name: name,
                                path: path,
                                sort: sort,
                                style:style
                            })
                            $scope.query();
                        });
                    }
                })
            }; 

            $scope.toggle = function (scope) {
                scope.toggle();
            };

            $scope.moveLastToTheBeginning = function () {
                var a = $scope.data.pop();
                $scope.data.splice(0, 0, a);
            };

            $scope.newSubItem = function (scope) {
                var sort ;
                var parentId = 0;
                var style = '';
                var display = 'display:none';
                if(!scope){
                    sort = $scope.data.length + 1;
                    display = '';
                }else{
                    sort = scope.$modelValue.nodes.length + 1;
                    parentId = scope.$modelValue.id;
                }
                var s = content.replace('{name}', '').replace('{path}', '').replace('{sort}', sort);
                s = s.replace('display:none', display).replace('{style}', style);
                $.confirm({
                    title: false,
                    confirmButton: '保存',
                    cancelButton: '取消',
                    confirmButtonClass: 'btn-danger',
                    cancelButtonClass: 'btn-info',
                    content: s,
                    confirm: function(){
                        var name = angular.element('#name').val();
                        var path = angular.element('#path').val();
                        var sort = angular.element('#sort').val();
                        var style = angular.element('#style').val();
                        $scope.$apply(function(){
                            $scope.submit({
                                name: name,
                                parentId: parentId,
                                path: path,
                                sort: sort,
                                style:style
                            })
                            $scope.query();
                        });
                    }
                })
          };

          $scope.collapseAll = function () {
            $scope.$broadcast('angular-ui-tree:collapse-all');
          };

          $scope.expandAll = function () {
            $scope.$broadcast('angular-ui-tree:expand-all');
          };

          

       }]);

</script>
</head>


<body class="gray-bg" ng-app="app" ng-controller="ctrl">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>菜单管理</h5>
                    </div>
                    <div class="ibox-content">
                           <!-- Nested node template -->
                            <script type="text/ng-template" id="nodes_renderer.html">
                              <div ui-tree-handle class="tree-node tree-node-content">
                                <a class="btn btn-success btn-xs" ng-if="node.nodes && node.nodes.length > 0" data-nodrag ng-click="toggle(this)">
                                    <span
                                        class="glyphicon"
                                        ng-class="{
                                           'glyphicon-chevron-right': collapsed,
                                           'glyphicon-chevron-down': !collapsed
                                        }">
                                    </span>
                                </a>
                                <span>{{node.title}}</span><span ng-show=node.path>[{{node.path}}]</span>
                                <a class="pull-right btn btn-danger btn-xs" data-nodrag ng-click="edit(this)">
                                  <span class="glyphicon glyphicon-edit"></span>
                                </a>
                                <a class="pull-right btn btn-danger btn-xs" data-nodrag ng-click="delNode(this)" ng-show="node.nodes.length==0">
                                  <span class="glyphicon glyphicon-remove"></span>
                                </a>
                                <a class="pull-right btn btn-primary btn-xs " data-nodrag ng-click="newSubItem(this)" style="margin-right: 8px;" ng-show="node.depth<2" >
                                  <span class="glyphicon glyphicon-plus">
                                      
                                  </span>
                                </a>
                              </div>
                              <ol ui-tree-nodes="" ng-model="node.nodes" ng-class="{hidden: collapsed}">
                                <li ng-repeat="node in node.nodes" ui-tree-node ng-include="'nodes_renderer.html'">
                                </li>
                              </ol>
                            </script>
                                    
                                    <div class="row">
                                      <div class="col-sm-12">
                                        <button class="btn btn-primary" ng-click="newSubItem()">添加一级菜单</button>
                                      </div>
                                    </div>
                                    
                                    <div class="row">
                                      <div class="col-sm-6">
                                        <div ui-tree id="tree-root" data-drag-enabled=false>
                                          <ol ui-tree-nodes ng-model="data">
                                            <li ng-repeat="node in data" ui-tree-node ng-include="'nodes_renderer.html'"></li>
                                          </ol>
                                        </div>
                                      </div>
                                     
<div class="col-sm-6">
                    <div class="info">
                      {{info}}
                    </div>
                    <pre class="code">{{ data | json }}</pre>
                  </div>
                </div>

                                    </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                </div>
    <!--end-->
</body>
