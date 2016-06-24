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
                {name:'id',index:'id', editable: true, width:90, sorttype:"int", formatter:"int"},
                {name:'userId',index:'user_id', editable: true, width:100},
                {name:'username',index:'username', editable: true, width:80, align:"right",sorttype:"float", formatter:"string"},
                {name:'nickname',index:'nickname', editable: true, width:80, align:"right",sorttype:"string"}
                
            ]
        }

        var grid = new Grid('bage-table', config, gridConfig);
        
      
    </script>
</head>

<body ng-app="app" ng-controller="ctrl">

    <div id="wrapper">

        <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
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
                            <p>
                               用户名: <input type="text" ng-model="query.username"/> 
                               昵称: <input type="text" ng-model="query.nickname"/>
                               <input type="button" value="查找" ng-click="search()" />
                            </p>

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