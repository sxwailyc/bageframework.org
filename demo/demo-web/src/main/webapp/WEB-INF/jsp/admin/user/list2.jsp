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
    <script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.js"></script>
     <script src="/js/rpc.js"></script>
    <script src="/js/angular.bage.grid.js"></script>
    <style>
       .ui-jqgrid-htable tr.ui-jqgrid-labels th { height: 35px; padding-top: 0px;}
       .ui-jqgrid tr.jqgrow td { height: 35px; padding-top: 0px;}
       
    </style>
    <script>
    

        var config = {
            path: 'user'
        }

        var jqGridConfig = {
            colNames: ['id', '用户ID', '用户名', '用户昵称', '操作'],
            colModel:[
                {name:'id',index:'id', editable: true, width:90, sorttype:"int", formatter:"int"},
                {name:'userId',index:'user_id', editable: true, width:100},
                {name:'username',index:'username', editable: true, width:80, align:"right",sorttype:"float", formatter:"string"},
                {name:'nickname',index:'nickname', editable: true, width:80, align:"right",sorttype:"string"},
                {width:100}
            ]
        }

        var grid = new Grid('table_list_2', config, jqGridConfig);
        
      
    </script>
</head>

<body>

    <div id="wrapper" ng-app="app" ng-controller="ctrl">

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

                            <div class="jqGrid_wrapper">
                                <table id="table_list_2"></table>
                                <div id="pager_list_2"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="../footer.inc.jsp"%>
        </div>
    </div>

</body>
</html>