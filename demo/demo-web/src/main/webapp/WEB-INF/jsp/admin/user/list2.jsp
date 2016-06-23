<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>用户列表</title>

    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.css" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/plugins/jQueryUI/jquery-ui-1.10.4.custom.min.css" rel="stylesheet">
    <link href="/css/plugins/jqGrid/ui.jqgrid.css" rel="stylesheet">
    <link href="/css/plugins/jquery-confirm.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">

    <style>
        /* Additional style to fix warning dialog position */
        #alertmod_table_list_2
            top: 900px !important;
        }
    </style>
    
    
    <!-- Mainly scripts -->
    <script src="/js/jquery-2.1.1.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Peity -->
    <script src="/js/plugins/peity/jquery.peity.min.js"></script>

    <!-- jqGrid -->
    <script src="/js/plugins/jqGrid/i18n/grid.locale-en.js"></script>
    <script src="/js/plugins/jqGrid/jquery.jqGrid.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="/js/inspinia.js"></script>
    <script src="/js/plugins/pace/pace.min.js"></script>

    <script src="/js/plugins/jquery-ui/jquery-ui.min.js"></script>
    <script src="/js/plugins/jquery-confirm.js"></script>
    <script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.js"></script>
    <script src="/js/rpc.js"></script>
     <script src="/js/bage/bage.core.js"></script>
    <script src="/js/angular.bage.grid.js"></script>
    <style>
       .ui-jqgrid-htable tr.ui-jqgrid-labels th { height: 35px; padding-top: 0px;}
       .ui-jqgrid tr.jqgrow td { height: 35px; padding-top: 0px;}
       
    </style>
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
                            <p>欢迎登录本站(⊙o⊙)</p>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">用户名：</label>

                                <div class="col-sm-8">
                                    <input type="email" placeholder="用户名" class="form-control"> <span class="help-block m-b-none">请输入您注册时所填的E-mail</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">密码：</label>

                                <div class="col-sm-8">
                                    <input type="password" placeholder="密码" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-8">
                                    <button class="btn btn-sm btn-white" type="submit">登 录</button>
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