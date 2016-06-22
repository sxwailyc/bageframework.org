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
    <script src="/js/user.list.js"></script>
</head>

<body>

    <div id="wrapper">

        <%@include file="../left.inc.jsp"%>

        <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
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
                        <div class="ibox-title" ng-app="app" ng-controller="ctrl">
                            <h5>{{ title }}</h5>
                        </div>
                        <div class="ibox-content">
                            
                            <h4>Advanced example</h4>
                            <p>

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