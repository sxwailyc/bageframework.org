<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--左侧导航开始-->
<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="nav-close"><i class="fa fa-times-circle"></i>
    </div>
    <div class="sidebar-collapse">
        <ul class="nav" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element">
                    <span><img alt="image" class="img-circle" src="/img/logo.jpeg" /></span>
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <span class="clear">
                       <span class="block m-t-xs"><strong class="font-bold">Beaut-zihan</strong></span>
                        <span class="text-muted text-xs block">超级管理员<b class="caret"></b></span>
                        </span>
                    </a>
                    <ul class="dropdown-menu animated fadeInRight m-t-xs">
                        <li><a class="J_menuItem" href="form_avatar.html">修改头像</a>
                        </li>
                        <li><a class="J_menuItem" href="profile.html">个人资料</a>
                        </li>
                        <li><a class="J_menuItem" href="contacts.html">联系我们</a>
                        </li>
                        <li><a class="J_menuItem" href="mailbox.html">信箱</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="login.html">安全退出</a>
                        </li>
                    </ul>
                </div>
                <div class="logo-element">H+
                </div>
            </li>
            <li>
                <a href="#">
                    <i class="fa fa-home"></i>
                    <span class="nav-label">主页</span>
                    <span class="fa arrow"></span>
                </a>
                <ul class="nav nav-second-level">
                    <li>
                        <a class="J_menuItem" href="/admin/empty.do" data-index="0">主页示例一</a>
                    </li>
                    <li>
                        <a class="J_menuItem" href="index_v2.html">主页示例二</a>
                    </li>
                    <li>
                        <a class="J_menuItem" href="index_v3.html">主页示例三</a>
                    </li>
                    <li>
                        <a class="J_menuItem" href="index_v4.html">主页示例四</a>
                    </li>
                    <li>
                        <a href="index_v5.html" target="_blank">主页示例五</a>
                    </li>
                </ul>

            </li>
            <c:forEach items="${menus}" var="menu" varStatus="current">
                <li>
                    <a href="${menu.path}<">
                       <i class="fa ${menu.style}"></i> 
                       <span class="nav-label">${menu.title}</span> 
                       <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level collapse">
                         <c:forEach items="${menu.nodes}" var="subMenu" varStatus="current">
                            <li><a href="${subMenu.path}">${subMenu.title}</a></li>
                         </c:forEach>
                    </ul>
                </li>
            </c:forEach>
            <li>
                <a class="J_menuItem" href="layouts.html"><i class="fa fa-columns"></i> <span class="nav-label">布局</span></a>
            </li>
            <li>
                <a href="#">
                    <i class="fa fa fa-bar-chart-o"></i>
                    <span class="nav-label">统计图表</span>
                    <span class="fa arrow"></span>
                </a>
                <ul class="nav nav-second-level">
                    <li>
                        <a class="J_menuItem" href="graph_echarts.html">百度ECharts</a>
                    </li>
                    <li>
                        <a class="J_menuItem" href="graph_flot.html">Flot</a>
                    </li>
                    <li>
                        <a class="J_menuItem" href="graph_morris.html">Morris.js</a>
                    </li>
                    <li>
                        <a class="J_menuItem" href="graph_rickshaw.html">Rickshaw</a>
                    </li>
                    <li>
                        <a class="J_menuItem" href="graph_peity.html">Peity</a>
                    </li>
                    <li>
                        <a class="J_menuItem" href="graph_sparkline.html">Sparkline</a>
                    </li>
                    <li>
                        <a class="J_menuItem" href="graph_metrics.html">图表组合</a>
                    </li>
                </ul>
            </li>

            <li>
                <a href="mailbox.html"><i class="fa fa-envelope"></i> <span class="nav-label">信箱 </span><span class="label label-warning pull-right">16</span></a>
                <ul class="nav nav-second-level">
                    <li><a class="J_menuItem" href="mailbox.html">收件箱</a>
                    </li>
                    <li><a class="J_menuItem" href="mail_detail.html">查看邮件</a>
                    </li>
                    <li><a class="J_menuItem" href="mail_compose.html">写信</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#"><i class="fa fa-edit"></i> <span class="nav-label">表单</span><span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li><a class="J_menuItem" href="form_basic.html">基本表单</a>
                    </li>
                    <li><a class="J_menuItem" href="form_validate.html">表单验证</a>
                    </li>
                    <li><a class="J_menuItem" href="form_advanced.html">高级插件</a>
                    </li>
                    <li><a class="J_menuItem" href="form_wizard.html">表单向导</a>
                    </li>
                    <li>
                        <a href="#">文件上传 <span class="fa arrow"></span></a>
                        <ul class="nav nav-third-level">
                            <li><a class="J_menuItem" href="form_webuploader.html">百度WebUploader</a>
                            </li>
                            <li><a class="J_menuItem" href="form_file_upload.html">DropzoneJS</a>
                            </li>
                            <li><a class="J_menuItem" href="form_avatar.html">头像裁剪上传</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">编辑器 <span class="fa arrow"></span></a>
                        <ul class="nav nav-third-level">
                            <li><a class="J_menuItem" href="form_editors.html">富文本编辑器</a>
                            </li>
                            <li><a class="J_menuItem" href="form_simditor.html">simditor</a>
                            </li>
                            <li><a class="J_menuItem" href="form_markdown.html">MarkDown编辑器</a>
                            </li>
                            <li><a class="J_menuItem" href="code_editor.html">代码编辑器</a>
                            </li>
                        </ul>
                    </li>
                    <li><a class="J_menuItem" href="suggest.html">搜索自动补全</a>
                    </li>
                    <li><a class="J_menuItem" href="layerdate.html">日期选择器layerDate</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#"><i class="fa fa-desktop"></i> <span class="nav-label">权限管理</span><span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li><a class="J_menuItem" href="/admin/menu/list.do">菜单管理</a></li>
                    <li><a class="J_menuItem" href="/admin/user/list2.do">用户管理</a></li>
                </ul>
            </li>
            <li><a href="#"><i class="fa fa-flask"></i> <span class="nav-label">代码生成</span><span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li><a class="J_menuItem" href="/admin">全局配置</a></li>
                    <li><a class="J_menuItem" href="/admin/metadata/list.do">代码生成</a></li>
                    
                </ul>
            </li>
            <li>
                <a href="#"><i class="fa fa-table"></i> <span class="nav-label">表格</span><span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li><a class="J_menuItem" href="table_basic.html">基本表格</a>
                    </li>
                    <li><a class="J_menuItem" href="table_data_tables.html">DataTables</a>
                    </li>
                    <li><a class="J_menuItem" href="/admin/user/list2.do">jqGrid</a>
                    </li>
                    <li><a class="J_menuItem" href="table_foo_table.html">Foo Tables</a>
                    </li>
                    <li><a class="J_menuItem" href="table_bootstrap.html">Bootstrap Table
                        <span class="label label-danger pull-right">推荐</span></a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#"><i class="fa fa-picture-o"></i> <span class="nav-label">相册</span><span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li><a class="J_menuItem" href="basic_gallery.html">基本图库</a>
                    </li>
                    <li><a class="J_menuItem" href="carousel.html">图片切换</a>
                    </li>
                    <li><a class="J_menuItem" href="blueimp.html">Blueimp相册</a>
                    </li>
                </ul>
            </li>
            <li>
                <a class="J_menuItem" href="css_animation.html"><i class="fa fa-magic"></i> <span class="nav-label">CSS动画</span></a>
            </li>
            <li>
                <a href="#"><i class="fa fa-cutlery"></i> <span class="nav-label">工具 </span><span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li><a class="J_menuItem" href="form_builder.html">表单构建器</a>
                    </li>
                </ul>
            </li>

        </ul>
    </div>
</nav>
<!--左侧导航结束-->

