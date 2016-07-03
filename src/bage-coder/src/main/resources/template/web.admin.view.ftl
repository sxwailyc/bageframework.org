<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <%@include file="../css.inc.jsp"%>
    <%@include file="../js.inc.jsp"%>
    <script>

        var config = {
            path: '${modelObjectName}',
            keyName: 'id',
            buttons: '',
            functions: ['syncFromDb'],
            services: function(service, remote){

            },
            controllers: function($scope, service){

            }
        }
        
        var gridConfig = {
            colModel:[
<#list colModels as colModel>                
                ${colModel},
</#list>
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
                        <h5>${title}</h5>
                    </div>
                    <div class="ibox-content">
                            <div class="form-horizontal">
                                <div class="form-group">
<#list searchForms as searchForm>                           
                                    <label class="col-md-1 control-label"> ${searchForm.label}: </label>
                                    <div class="col-md-2">
                                       <input type="text" ng-model="query.${searchForm.name}" class="form-control" placeholder="" />
                                    </div>
</#list>                                 
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
<#list editForms as editForm> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label">${editForm.label}：</label>
                        <div class="col-sm-8">
                            <input type="text" placeholder="" class="form-control" ng-model="item.${editForm.name}">
                        </div>
                    </div>
</#list>
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