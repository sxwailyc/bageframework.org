<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>爬虫任务</title>
    <%@include file="../css.inc.jsp"%>
    <%@include file="../js.inc.jsp"%>
    <script>

        var typeOptions = [
               {id: 1, name: '公众号'},
               {id: 2, name: '关键字'}
            ]

        var statusOptions = [
               {id: 0, name: '新建'},
               {id: 1, name: '排队中'},
               {id: 2, name: '已完成'}
            ]

        var getTypeName = function(k){
             for(var i = typeOptions.length; i--;){
                if(typeOptions[i].id == k){
                    return typeOptions[i].name;
                }
            } 
        }

        var getStatusName = function(k){
             for(var i = statusOptions.length; i--;){
                if(statusOptions[i].id == k){
                    return statusOptions[i].name;
                }
            } 
        }

        var config = {
            path: 'spiderTask',
            keyName: 'id',
            buttons: '',
            functions: ['syncFromDb'],
            services: function(service, remote){
                service.remoteCategory = new rpc.ServiceProxy("/services/category", {
                   asynchronous: false,
                   methods: ['getList']
                });

                service.getCategorys = function(){
                    return service.remoteCategory.getList();
                }
            },
            afterEdit: function(data){
                data.type = data.type.toString();
                data.status = data.status.toString();   
                data.categoryId = data.categoryId.toString();             
            },
            controllers: function($scope, service){
                $scope.categorys = service.getCategorys(); 

                $scope.getCategoryName = function(k){
                    for(var i = $scope.categorys.length; i--;){
                        if($scope.categorys[i].id == k){
                            return $scope.categorys[i].remark;
                        }
                    } 
                    return 'unknow';
                }
            },
            datas: function($scope){
                $scope.typeOptions = typeOptions;
                $scope.statusOptions = statusOptions;
            },
            filters: function(app){
                app.filter('type', function($sce){
                    return getTypeName;
                });
                app.filter('status', function($sce){
                    return getStatusName;
                });
                app.filter('category', function($sce){
                    return function(k){
                        return app.$scope.getCategoryName(k);
                    }
                });
            }
        }
        
        var gridConfig = {
            colModel:[
                {"name":"id","label":"ID","width":10,"formatter":"string"},
                {"name":"type","label":"类型","width":10,"formatter":"ng:type"},
                {"name":"param","label":"参数","width":10,"formatter":"string"},
                {"name":"categoryId","label":"默认分类","width":10,"formatter":"ng:category"},
                {"name":"createdTime","label":"创建时间","width":10,"formatter":'ng:date:"yyyy-MM-dd hh:mm:ss"'},
                {"name":"lastSpiderTime","label":"最后爬取时间","width":10,"formatter":'ng:date:"yyyy-MM-dd hh:mm:ss"'},
                {"name":"status","label":"状态","width":10,"formatter":"ng:status"},
            ]
        }

        var grid = new Grid('bage-table', config, gridConfig);       
         
        grid.init();

    </script>
</head>


<body class="gray-bg" ng-app="app" ng-controller="ctrl">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>爬虫任务</h5>
                    </div>
                    <div class="ibox-content">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label"> 参数: </label>
                                    <div class="col-md-2">
                                       <input type="text" ng-model="query.param" class="form-control" placeholder="" />
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
                        <label class="col-sm-3 control-label">类型：</label>
                        <div class="col-sm-8">
                             <select class="form-control m-b" ng-model="item.type">
                              <option ng-repeat="option in typeOptions" value="{{option.id}}">{{option.name}}</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">参数：</label>
                        <div class="col-sm-8">
                            <input type="text" placeholder="" class="form-control" ng-model="item.param">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">分类：</label>
                        <div class="col-sm-8">
                            <select class="form-control m-b" ng-model="item.categoryId">
                                <option ng-repeat="option in categorys" value={{option.id}}>{{option.remark}}</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">状态：</label>
                        <div class="col-sm-8">
                             <select class="form-control m-b" ng-model="item.status">
                              <option ng-repeat="option in statusOptions" value="{{option.id}}">{{option.name}}</option>
                            </select>
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
</body>