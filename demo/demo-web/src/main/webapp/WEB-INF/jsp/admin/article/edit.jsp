<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>文章编辑</title>
    <%@include file="../css.inc.jsp"%>
    <%@include file="../js.inc.jsp"%>
    <script src="/plugin/ueditor/ueditor.config.js"></script>
    <script src="/plugin/ueditor/ueditor.all.min.js"></script>
    <script>

        var app = angular.module('app', []);

        app.factory('Service', [function() {
            
            var service = {};

            service.remote = new rpc.ServiceProxy("/services/article", {
                asynchronous: false,
                methods: ['get', 'update', 'add', 'getStaticName']
            });

            service.remoteCategory = new rpc.ServiceProxy("/services/category", {
                asynchronous: false,
                methods: ['getList']
            });
               
            service.get = function(id){
                return service.remote.get(id);
            }

            service.add = function(item){
                return service.remote.add(item);
            }

            service.update = function(item){
                return service.remote.update(item);
            }

            service.getStaticName = function(date, name){
                return service.remote.getStaticName(date, name);
            }

            service.getCategorys = function(){
                return service.remoteCategory.getList();
            }

            return service;
        }]);

        app.controller('ctrl', ['$scope', '$filter', 'Service', function($scope, $filter, service) {
            
            $scope.articleId = ${articleId};

            $scope.categorys = service.getCategorys();  

            $scope.init = function() {
                if($scope.articleId > 0){
                    $scope.article = service.get($scope.articleId);
                    $scope.article.category = $scope.article.category.toString();
                }else{
                    $scope.article = {};
                }  
            };

            $scope.init();
            
            $scope.update = function(){
               try{
                   service.update($scope.article);
                   Bage.successMsg('更新成功');
                }catch(error){
                   Bage.successMsg('更新失败:' + error.message);   
                }
            }
              
            $scope.add = function(){
                try{
                   service.add($scope.article);
                   Bage.successMsg('添加成功');
                }catch(error){
                   Bage.successMsg('添加失败:' + error.message);   
                }
            }

               
            $scope.getStaticName = function(){
                try{
                   var date = $filter("date")($scope.article.createdTime, "yyyy-MM-dd HH:mm:ss")
                   var staticName = service.getStaticName(date, $scope.article.title);
                   $scope.article.staticName = staticName;
                }catch(error){
                   Bage.successMsg('获取静态名称失败:' + error.message);   
                }
            }

            $scope.submit = function(){
                $scope.article.content = $scope.editor.getContent();
                if($scope.article.id){
                    $scope.update();
                }else{
                    $scope.add();     
                }
            }
            
            $scope.editor = UE.getEditor('editor'); 
            $scope.editor.ready(function() {
                $scope.editor.setContent($scope.article.content);
            });

        }]);
    
    </script>
</head>


<body class="gray-bg" ng-app="app" ng-controller="ctrl">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>文章编辑</h5>
                    </div>
                    <div class="ibox-content">
                        <form method="get" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-1 control-label">文章标题</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" ng-model="article.title" ng-change="getStaticName()">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">静态地址</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" ng-model="article.staticName">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">分类</label>

                                <div class="col-sm-8">
                                    <select class="form-control m-b" ng-model="article.category">
                                        <option ng-repeat="option in categorys" value={{option.id}}>{{option.remark}}</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">关键字</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" ng-model="article.keyword">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">文章标签</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" ng-model="article.tags">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">是否置顶</label>

                                <div class="col-sm-8">
                                    <input type="checkbox" ng-true-value="1" ng-false-value="0" ng-model="article.isRecom">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">文章摘要</label>

                                <div class="col-sm-8">
                                    <textarea rows="6" class="form-control" ng-model="article.summary"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">文章正文</label>
                                <div class="col-sm-8">
                                    <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" type="submit" ng-click="submit()">保存内容</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>