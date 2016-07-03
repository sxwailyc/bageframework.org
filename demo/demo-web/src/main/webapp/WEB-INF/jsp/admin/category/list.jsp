<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>文章分类</title>
    <%@include file="../css.inc.jsp"%>
    <%@include file="../js.inc.jsp"%>
    <script>

        var config = {
            path: 'category',
            keyName: 'id',
            functions: ['syncFromDb'],
            services: function(service, remote){

            },
            controllers: function($scope, service){

            }
        }
        
        var gridConfig = {
            colModel:[
                {"name":"id","label":"ID","width":10,"formatter":"string"},
                {"name":"name","label":"分类名称","width":10,"formatter":"string"},
                {"name":"remark","label":"备注","width":10,"formatter":"string"},
                {"name":"sort","label":"排序","width":10,"formatter":"string"},
                {"name":"createdTime","label":"创建时间","width":10,'formatter': 'ng:date:"yyyy-MM-dd hh:mm:ss"'},
            ]
        }

        var grid = new Grid('bage-table', config, gridConfig);   
        grid.addButton("EDIT", "btn-xs btn-primary", "margin-left:5px;margin-right:5px;", "edit($id)", function(row){
            if(row.id==2){
                return true;
            }
            return false;
        });         
        grid.init();

    </script>
</head>


<body class="gray-bg" ng-app="app" ng-controller="ctrl">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>文章分类</h5>
                    </div>
                    <div class="ibox-content">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label"> 分类名称: </label>
                                    <div class="col-md-2">
                                       <input type="text" ng-model="query.name" class="form-control" placeholder="" />
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
                        <label class="col-sm-3 control-label">分类名称：</label>
                        <div class="col-sm-8">
                            <input type="text" placeholder="" class="form-control" ng-model="item.name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注：</label>
                        <div class="col-sm-8">
                            <input type="text" placeholder="" class="form-control" ng-model="item.remark">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">排序：</label>
                        <div class="col-sm-8">
                            <input type="text" placeholder="" class="form-control" ng-model="item.sort">
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