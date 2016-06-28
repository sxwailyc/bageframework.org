<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户列表</title>
    <%@include file="../css.inc.jsp"%>
    <%@include file="../js.inc.jsp"%>
    <script>

        var buttons = '<button type="button" class="btn-xs btn-info" style="margin-left:5px;margin-right:5px;" ng-click=syncFromDb(\'$id\')>同步数据库</button>';
        buttons = buttons + '<a href="/admin/property/list.do?metadataId=$id" ><button type="button" class="btn-xs btn-info" style="margin-left:5px;margin-right:5px;" )>字段</button></a>'

        var config = {
            path: 'metadata',
            keyName: 'id',
            buttons: buttons,
            functions: ['syncFromDb'],
            services: function(service, remote){
                service.syncFromDb = function(id, successCallback){
                    service.saveCall(function(){
                       return remote.syncFromDb(id);
                    }, successCallback)
                }
            },
            controllers: function($scope, service){
                $scope.syncFromDb = function(id){
                    Bage.confirm_call('确认是否从数据库同步表定义?', function(){
                        service.syncFromDb(id, function(res){
                            Bage.successMsg('同步成功');
                        });
                    });
                };
            }
        }
        
        var gridConfig = {
            colNames: ['id', '表名', '类名', '注释', '操作'],
            colModel:[
                {name:'id',index:'id', editable: true, width:10, sorttype:"string", formatter:"string"},
                {name:'table',index:'table', editable: true, width:10},
                {name:'javaClass',index:'java_class', width:10, align:"right", formatter:"string"},
                {name:'remark', editable: true, width:10},
            ]
        }

        var grid = new Grid('bage-table', config, gridConfig);        
        grid.init();
        
      
    </script>
</head>

<body ng-app="app" ng-controller="ctrl">

    <div id="wrapper">
        <%@include file="../left.inc.jsp"%>
        <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
        </div>
        <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            
        </div>
            <ul class="nav navbar-top-links navbar-right">
                <li>
                    <span class="m-r-sm text-muted welcome-message">欢迎您:超级管理员</span>
                </li>
                


                <li>
                    <a href="login.html">
                        <i class="fa fa-sign-out"></i> 退出登陆
                    </a>
                </li>
            </ul>

        </nav>
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2></h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="index.html">主页</a>
                    </li>
                    <li>
                        <a>权限管理</a>
                    </li>
                    <li class="active">
                        <strong>用户列表</strong>
                    </li>
                </ol>
            </div>
        </div>
        <div class="wrapper wrapper-content  animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox ">
                        <div class="ibox-title">
                            <h5>
                               {{ title }}
                            </h5>
                        </div>
                        <div class="ibox-content">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label"> 表名: </label>
                                    <div class="col-md-2">
                                       <input type="text" ng-model="query.table" class="form-control" placeholder="" />
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

        <%@include file="../footer.inc.jsp"%>
        </div>
    </div>
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
                        <label class="col-sm-2 control-label">表名：</label>
                        <div class="col-sm-9">
                            <input type="text" placeholder="" class="form-control" ng-model="item.table">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">类名：</label>
                        <div class="col-sm-9">
                            <input type="text" placeholder="" class="form-control" ng-model="item.javaClass">
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="col-sm-2 control-label">注释：</label>
                        <div class="col-sm-9">
                            <input type="text" placeholder="" class="form-control" ng-model="item.remark">
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
</html>