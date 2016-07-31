<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="dns-prefetch" href="//apps.bdimg.com">
    <meta http-equiv="X-UA-Compatible" content="IE=11,IE=10,IE=9,IE=8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-title" content="美丽女人">
    <meta http-equiv="Cache-Control" content="no-siteapp">
    <meta name="keywords" content="护肤品排行榜前十名，护肤品排行榜，日本护肤品推荐、韩国护肤品排行榜，护肤品十大排行榜，护肤品，美丽女人">
    <meta name="description" content="美丽女人化妆品排行榜为您展示各类品牌化妆品排行榜和护肤品排行榜信息,在这里您可以了解大家都爱用的化妆品有哪些好的牌子,日本、韩国最好用的护肤品有哪些品牌">
    
    <link rel="shortcut icon" href="http://www.meiladys.com/favicon.ico">
    
    <title>美丽女人-护肤品排行榜前十名 | 日本、韩国护肤品品牌推荐</title>
    <%@include file="../inc/css.inc.jsp"%>
    <%@include file="../inc/js.inc.jsp"%>
    <script type="text/javascript">
        var duoshuoQuery = {
            "short_name": "meiladys",
            "sso": {
                "login": "http:\/\/www.meiladys.com\/wp-login.php?action=duoshuo_login",
                "logout": "http:\/\/www.meiladys.com\/wp-login.php?action=logout&_wpnonce=f535ed5e75"
            },
            "theme": "default",
            "stylePatch": "wordpress\/DUX"
        };
        duoshuoQuery.sso.login += '&redirect_to=' + encodeURIComponent(window.location.href);
        duoshuoQuery.sso.logout += '&redirect_to=' + encodeURIComponent(window.location.href);
    </script> 
</head>

<body class="home blog site-layout-2">
    <%@include file="../inc/header.inc.jsp"%>
    <div class="site-search">
        <div class="container">
            <form method="get" class="site-search-form" action="http://www.meiladys.com/"><input class="search-input" name="s" type="text" placeholder="输入关键字" value=""><button class="search-btn" type="submit"><i class="fa fa-search"></i></button></form>
        </div>
    </div>
    <section class="container">
        <div class="content-wrap">
            <div class="content">
                <%@include file="article.list.inc.jsp"%>
            </div>
        </div>
        <aside class="sidebar">
            <%@include file="../inc/top.inc.jsp"%>
            <%@include file="../inc/hot.inc.jsp"%>
            <%@include file="../inc/new.inc.jsp"%>
        </aside>
    </section>
        <%@include file="../inc/footer.inc.jsp"%>
    <script>
        window.jsui = {
            www: 'http://www.meiladys.com',
            uri: 'http://www.meiladys.com/wp-content/themes/dux',
            ver: 'THEME_VERSION',
            roll: 0,
            ajaxpager: '',
            url_rp: 'http://www.meiladys.com/sample-page'
        };
    </script>
    <script type='text/javascript' src='http://www.meiladys.com/wp-content/themes/dux/js/libs/bootstrap.min.js?ver=THEME_VERSION'></script>
    <script type='text/javascript' src='http://www.meiladys.com/wp-content/themes/dux/js/loader.js?ver=THEME_VERSION'></script>
</body>

</html>