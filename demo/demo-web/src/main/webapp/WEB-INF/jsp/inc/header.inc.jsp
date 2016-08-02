<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header class="header">
    <div class="container">
        <h1 class="logo"><a href="http://${siteConfig.domain}" title="${siteConfig.title}">
          <img src="/img/logo.png">美丽女人</a>
        </h1>
        <div class="brand">让信息
            <br>更权威
        </div>
        <ul class="site-nav site-navbar">
            <li id="menu-item-7" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-7"><a href="http://${siteConfig.domain}/">首页</a></li>
            <c:forEach items="${categorys}" var="item" varStatus="current">
                <li id="menu-item-9" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-9"><a href="${host}/${item.remark}.html">${item.remark}</a></li>
            </c:forEach>
            <li class="navto-search"><a href="javascript:;" class="search-show active"><i class="fa fa-search"></i></a></li>
        </ul>
        <i class="fa fa-bars m-icon-nav"></i>
    </div>
</header>