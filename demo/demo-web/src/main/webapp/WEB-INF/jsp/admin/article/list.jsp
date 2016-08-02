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

            },
            controllers: function($scope, service){
                $scope.add = function(){
                    window.location = "/admin/article/edit.do";
                }
            }
        }
        
        var gridConfig = {
            colModel:[
                {"name":"id","label":"","width":10,"formatter":"string"},
                {"name":"title","label":"标题","width":10,"formatter":"string"},
                {"name":"category","label":"分类","width":10,"formatter":"string"},
                {"name":"keyword","label":"关键字","width":10,"formatter":"string"},
                {"name":"staticName","label":"静态地址","width":10,"formatter":"string"},
                {"name":"isImg","label":"是否图文","width":10,"formatter":"string"},
                {"name":"isRecom","label":"是否推荐","width":10,"formatter":"string"},
                {"name":"commentCount","label":"评论数","width":10,"formatter":"string"},
                {"name":"viewCount","label":"查看数","width":10,"formatter":"string"},
                {"name":"createdTime","label":"创建时间","width":10,"formatter":"string"},
                {"name":"createdUser","label":"创建用户","width":10,"formatter":"string"},
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
                        <label class="col-sm-3 control-label">分类：</label>
                        <div class="col-sm-8">
                            <input type="text" placeholder="" class="form-control" ng-model="item.category">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">关键字：</label>
                        <div class="col-sm-8">
                            <input type="text" placeholder="" class="form-control" ng-model="item.keyword">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">摘要：</label>
                        <div class="col-sm-8">
                            <input type="text" placeholder="" class="form-control" ng-model="item.summary">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">静态地址：</label>
                        <div class="col-sm-8">
                            <input type="text" placeholder="" class="form-control" ng-model="item.static_name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">标签：</label>
                        <div class="col-sm-8">
                            <input type="text" placeholder="" class="form-control" ng-model="item.tags">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">内容：</label>
                        <div class="col-sm-8">
                            <input type="text" placeholder="" class="form-control" ng-model="item.content">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">缩略图：</label>
                        <div class="col-sm-8">
                            <input type="text" placeholder="" class="form-control" ng-model="item.thumbnail">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">是否推荐：</label>
                        <div class="col-sm-8">
                            <input type="text" placeholder="" class="form-control" ng-model="item.is_recom">
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