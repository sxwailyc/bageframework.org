<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>文章</title>
    <%@include file="../css.inc.jsp"%>
    <%@include file="../js.inc.jsp"%>
    <script>

        var config = {
            path: 'article',
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
            controllers: function($scope, service){
                $scope.add = function(){
                    window.location = "/admin/article/edit.do";
                }

                $scope.edit = function(id){
                    window.open("/admin/article/edit.do?articleId=" + id);
                }

                $scope.getCategoryName = function(k){
                    for(var i = $scope.categorys.length; i--;){
                        if($scope.categorys[i].id == k){
                            return $scope.categorys[i].remark;
                        }
                    } 
                    return 'unknow';
                }

                $scope.categorys = service.getCategorys(); 
            },
            filters: function(app){
                app.filter('category', function($sce){
                    return function(k){
                        return app.$scope.getCategoryName(k);
                    }
                });
            }
        }
        
        var gridConfig = {
            colModel:[
                {"name":"id","label":"","width":3,"formatter":"string"},
                {"name":"title","label":"标题","width":15,"formatter":"string"},
                {"name":"category","label":"分类","width":5,"formatter":"ng:category"},
                {"name":"keyword","label":"关键字","width":10,"formatter":"string"},
                {"name":"staticName","label":"静态地址","width":10,"formatter":"string"},
                {"name":"isImg","label":"是否图文","width":4,"formatter":"string"},
                {"name":"isRecom","label":"是否推荐","width":4,"formatter":"string"},
                {"name":"commentCount","label":"评论数","width":4,"formatter":"string"},
                {"name":"viewCount","label":"查看数","width":4,"formatter":"string"},
                {"name":"createdTime","label":"创建时间","width":10,"formatter":'ng:date:"yyyy-MM-dd hh:mm:ss"'},
                {"name":"publisher","label":"创建用户","width":10,"formatter":"string"},
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
                        <h5>文章</h5>
                    </div>
                    <div class="ibox-content">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label"> 分类: </label>
                                    <div class="col-md-2">
                                        <select class="form-control m-b" ng-model="query.category" ng-change="search()">
                                            <option ng-repeat="option in categorys" value={{option.id}}>{{option.remark}}</option>
                                        </select>
                                    </div>
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
</body>