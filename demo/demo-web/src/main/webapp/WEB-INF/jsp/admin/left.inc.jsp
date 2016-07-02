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
            <c:forEach items="${menus}" var="menu" varStatus="current">
                <li>
                    <a href="${menu.path}<">
                       <i class="fa ${menu.style}"></i> 
                       <span class="nav-label">${menu.title}</span> 
                       <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level collapse">
                         <c:forEach items="${menu.nodes}" var="subMenu" varStatus="current">
                            <li><a class="J_menuItem" href="${subMenu.path}">${subMenu.title}</a></li>
                         </c:forEach>
                    </ul>
                </li>
            </c:forEach>
        </ul>
    </div>
</nav>
<!--左侧导航结束-->

