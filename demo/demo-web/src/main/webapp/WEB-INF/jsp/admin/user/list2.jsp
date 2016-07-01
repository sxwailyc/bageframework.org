<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户列表</title>
    <%@include file="../css.inc.jsp"%>
    <%@include file="../js.inc.jsp"%>
    <script>
     
        var config = {
            path: 'user',
            keyName: 'userId'
        }
        
        var gridConfig = {
            colNames: ['id', '用户ID', '用户名', '用户昵称', '操作'],
            colModel:[
                {name:'id',index:'id', editable: true, width:10, sorttype:"int", formatter:"int"},
                {name:'userId',index:'user_id', editable: true, width:10},
                {name:'username',index:'username', editable: true, width:10, align:"right",sorttype:"float", formatter:"string"},
                {name:'nickname',index:'nickname', editable: true, width:10, align:"right",sorttype:"string"}
                
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
                        <h5>jQuery Grid Plugin – jqGrid</h5>
                    </div>
                    <div class="ibox-content">

                        <p>&nbsp;</p>
                        <h4 class="m-t">高级用法</h4>


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
                        <label class="col-sm-2 control-label">用户名：</label>
                        <div class="col-sm-9">
                            <input type="text" placeholder="" class="form-control" ng-model="item.username">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">昵称：</label>
                        <div class="col-sm-9">
                            <input type="text" placeholder="" class="form-control" ng-model="item.nickname">
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


<!--body ng-app="app" ng-controller="ctrl">

    <div id="wrapper">

        <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
        </div>
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
                                    <label class="col-md-1 control-label"> 用户名: </label>
                                    <div class="col-md-2">
                                       <input type="text" ng-model="query.username" class="form-control" placeholder="" />
                                    </div>
                                    <label class="col-md-1 control-label"> 昵称: </label>
                                    <div class="col-md-2">
                                       <input type="text" ng-model="query.nickname" class="form-control" placeholder="" />
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

        </div>
    </div>
    
</body>
</html-->