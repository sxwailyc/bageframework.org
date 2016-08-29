<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>微信文章</title>
    <%@include file="../css.inc.jsp"%>
    <%@include file="../js.inc.jsp"%>
    <script>
    
	    var getStatus = function(k){
	        if(k==0){
	        	return '未入库';
	        }else if(k==1){
	        	return '已入库';
	        }else if(k==2){
	        	return '已删除';
	        }else{
	        	return '未知';
	        }
	    }

        var statusOptions = [
            {id: 0, name: '未入库'},
            {id: 1, name: '已入库'},
        ]

        var config = {
            path: 'weixinArticle',
            keyName: 'id',
            buttons: '',
            functions: ['syncToArticle'],
            services: function(service, remote){

                service.remoteCategory = new rpc.ServiceProxy("/services/category", {
                   asynchronous: false,
                   methods: ['getList']
                });

                service.syncToArticle = function(id, category, successCallback){
                    service.saveCall(function(){
                       return remote.syncToArticle(id, category);
                    }, successCallback)
                }

                service.getCategorys = function(){
                    return service.remoteCategory.getList();
                }
            },
            controllers: function($scope, service){
 
                $scope.categorys = service.getCategorys();  

                $scope.syncToArticle = function(id){
                    $scope.syncId = id; 
                    var item = $scope.getItem(id);
                    $scope.category = item.categoryId.toString();
                    $('#syncModal').modal();
                };

                $scope.sync = function(){
                    service.syncToArticle($scope.syncId, $scope.category, function(res){
                        $('#syncModal').modal("hide");
                        Bage.successMsg('入库成功');
                        $scope.search();
                    });
                };
            },
            datas: function($scope){
                $scope.query.status = "0";
                $scope.statusOptions = statusOptions;
            },
            filters: function(app){
                app.filter('status', function($sce){
                    return getStatus;
                });
            },
        }
        
        var gridConfig = {
            colModel:[
                {"name":"id","label":"","width":3,"formatter":"string"},
                {"name":"publishDate","label":"发布时间","width":5,"formatter":"string"},
                {"name":"publisher","label":"作者","width":5,"formatter":"string"},
                {"name":"summary","label":"摘要","width":20,"formatter":"string"},
                {"name":"title","label":"标题","width":20,"formatter":"string"},
                {"name":"status","label":"状态","width":4,"formatter":"ng:status", align: "center"},
            ]
        }

        var grid = new Grid('bage-table', config, gridConfig);     
        grid.addButton("入库", "btn-xs btn-primary", "margin-left:5px;margin-right:5px;", "syncToArticle('$id')");    
        grid.init();

    </script>
</head>


<body class="gray-bg" ng-app="app" ng-controller="ctrl">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>微信文章</h5>
                    </div>
                    <div class="ibox-content">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-1 control-label">状态：</label>
                                    <div class="col-sm-2">
                                        <select class="form-control m-b" ng-model="query.status">
                                        <option ng-repeat="option in statusOptions" value="{{option.id}}">{{option.name}}</option>
                                        </select>
                                    </div>
                                    <label class="col-md-1 control-label"> 作者: </label>
                                    <div class="col-md-2">
                                       <input type="text" ng-model="query.publisher" class="form-control" placeholder="" />
                                    </div>
                                    <button type="button" class="btn btn-primary" ng-click="search()">查询</button>
                                </div>
                            </div>
                    </div>
                    <div class="ibox-content">
                        <div class="table_wrapper">
                            <table id="bage-table"></table>
                            <div id="pager"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--end-->
    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" >
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="exampleModalLabel">ADD&EDIT</h4>
                </div>
                <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">标题：</label>
                        <div class="col-sm-8">
                            <input type="text" placeholder="" class="form-control" ng-model="item.title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">摘要：</label>
                        <div class="col-sm-8">
                            <textarea rows="8" cols="120"  class="form-control" ng-model="item.summary"></textarea>
                        </div>
                    </div>
                </form>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-default" data-dismiss="modal" id="close">关闭</button>
                  <button type="button" class="btn btn-primary" ng-click="submit()">保存</button>
                </div>
            </div>
        </div>
    </div>
    <!--edit end-->
    <div class="modal fade" id="syncModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" >
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="exampleModalLabel">入库</h4>
                </div>
                <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">分类：</label>
                        <div class="col-sm-8">
                            <select class="form-control m-b" ng-model="category">
                                <option ng-repeat="option in categorys" value={{option.id}}>{{option.remark}}</option>
                            </select>
                        </div>
                    </div>
                </form>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-default" data-dismiss="modal" id="close">关闭</button>
                  <button type="button" class="btn btn-primary" ng-click="sync()" ng-disabled="!category">确定</button>
                </div>
            </div>
        </div>
    </div>
</body>