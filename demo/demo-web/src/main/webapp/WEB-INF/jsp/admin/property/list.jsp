<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>字段列表</title>
    <%@include file="../css.inc.jsp"%>
    <%@include file="../js.inc.jsp"%>
    <script>

        var formTypeOptions = [
               {id: 1, name: 'text'},
               {id: 2, name: 'textarea'},
               {id: 3, name: 'select'},
               {id: 4, name: 'checkbox'},
            ]

        var getFormTypeName = function(k){
            for(var i = formTypeOptions.length; i--;){
                if(formTypeOptions[i].id == k){
                    return formTypeOptions[i].name;
                }
            }
        }

        var config = {
            path: 'property',
            keyName: 'id',
            services: function(service, remote){
                
            },
            controllers: function($scope, service){
                
            },
            datas: function($scope){
                $scope.formTypeOptions = formTypeOptions;
            },
            afterEdit: function(data){
                data.formType = data.formType.toString();
            },
            filters: function(app){
                app.filter('formType', function($sce){
                    return getFormTypeName;
                });
            }
        }

        var booleanFormatter = function(cellvalue, options, rowObject){
            if(cellvalue==1){
                return '<i class="fa fa-check text-navy"></i>';
            }else{
                return '';
            }
        }
        
        var gridConfig = {
            colNames: ['id', '字段名', '类型', '表单类型', '注释', '搜索', '编辑', '显示', '排序', '操作'],
            colModel:[
                {name:'id', width:5},
                {name:'name', width:10},
                {name:'type', width:10},
                {name:'formType', width:5, align: 'center', formatter: 'ng:formType'},
                {name:'remark', width:10},
                {name:'search', width:5, formatter: booleanFormatter, align: 'center'},
                {name:'edit', width:5, formatter: booleanFormatter, align: 'center'},
                {name:'show', width:5, formatter: booleanFormatter, align: 'center'},
                {name:'sort', width:5, align: 'center'},
            ]
        }

        var grid = new Grid('bage-table', config, gridConfig);        
        grid.init();
        
      
    </script>
</head>

<body class="gray-bg" ng-app="app" ng-controller="ctrl" ng-init="query.metadata_id='${metadataId}'">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>property</h5>
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
                        <label class="col-sm-2 control-label">字段名：</label>
                        <div class="col-sm-9">
                            <input type="text" placeholder="" class="form-control" ng-model="item.name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">类型：</label>
                        <div class="col-sm-9">
                            <input type="text" placeholder="" class="form-control" ng-model="item.type">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">表单：</label>
                        <div class="col-sm-9">
                            <select class="form-control m-b" ng-model="item.formType">
                              <option ng-repeat="option in formTypeOptions" value="{{option.id}}">{{option.name}}</option>
                            </select>
                        </div>
                    </div>                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label">注释：</label>
                        <div class="col-sm-9">
                            <input type="text" placeholder="" class="form-control" ng-model="item.remark">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">排序：</label>
                        <div class="col-sm-9">
                            <input type="text" ng-model="item.sort">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">搜索：</label>
                        <div class="col-sm-9">
                            <input type="checkbox" ng-true-value="1" ng-false-value="0" ng-model="item.search">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">编辑：</label>
                        <div class="col-sm-9">
                            <input type="checkbox" ng-true-value="1" ng-false-value="0" ng-model="item.edit">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">显示：</label>
                        <div class="col-sm-9">
                            <input type="checkbox" ng-true-value="1" ng-false-value="0" ng-model="item.show">
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