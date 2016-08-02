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

            service.remote = new rpc.ServiceProxy("/services/siteConfig", {
                asynchronous: false,
                methods: ['get', 'update']
            });
               
            service.get = function(id){
                return service.remote.get(id);
            }

            service.update = function(item){
                return service.remote.update(item);
            }

            return service;
        }]);

        app.controller('ctrl', ['$scope', 'Service', function($scope, service) {
            
            $scope.siteConfig = service.get(1);

            $scope.update = function(){
               try{
                   service.update($scope.siteConfig);
                   Bage.successMsg('更新成功');
                }catch(error){
                   Bage.successMsg('更新失败:' + error.message);   
                }
            }

            $scope.editor = UE.getEditor('editor');

        }]);
    
    </script>
</head>


<body class="gray-bg" ng-app="app" ng-controller="ctrl">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>网站设置</h5>
                    </div>
                    <div class="ibox-content">
                        <form method="get" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-1 control-label">文章标题</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" ng-model="siteConfig.name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">静态地址</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" ng-model="siteConfig.title">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">分类</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" ng-model="siteConfig.domain">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">关键字</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" ng-model="siteConfig.keyword">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">文章标签</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" ng-model="siteConfig.keyword">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">是否置顶</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" ng-model="siteConfig.keyword">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">文章摘要</label>

                                <div class="col-sm-8">
                                    <textarea rows="6" class="form-control" ng-model="siteConfig.description"></textarea>
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
                                    <button class="btn btn-primary" type="submit" ng-click="update()">保存内容</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>