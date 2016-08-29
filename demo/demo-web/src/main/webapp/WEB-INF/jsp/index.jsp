<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="inc/taglib.inc.jsp"%>
    <!DOCTYPE HTML>
    <html>

    <head>
        <meta charset="UTF-8">
        <link rel="dns-prefetch" href="//apps.bdimg.com">
        <meta http-equiv="X-UA-Compatible" content="IE=11,IE=10,IE=9,IE=8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
        <meta name="apple-mobile-web-app-title" content="${siteConfig.name}">
        <meta http-equiv="Cache-Control" content="no-siteapp">
        <meta name="keywords" content="${siteConfig.keyword}">
        <meta name="description" content="${siteConfig.description}">
     
        <link rel="shortcut icon" href="http://www.meiladys.com/favicon.ico">
       
        <title>${siteConfig.title}</title>
        <%@include file="inc/css.inc.jsp"%>
        <%@include file="inc/js.inc.jsp"%>
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
        <%@include file="inc/header.inc.jsp"%>
        <%@include file="inc/search.inc.jsp"%>
        <section class="container">
            <div class="content-wrap">
                <div class="content">
                    <div id="focusslide" class="carousel slide" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#focusslide" data-slide-to="0" class="active"></li>
                        </ol>
                        <div class="carousel-inner" role="listbox">
                            <div class="item active">
                                <a href="http://www.meiladys.com/"><img src="http://www.meiladys.com/wp-content/uploads/2015/06/bg01-副本.jpg"></a>
                            </div>
                        </div><a class="left carousel-control" href="#focusslide" role="button" data-slide="prev"><i class="fa fa-angle-left"></i></a><a class="right carousel-control" href="#focusslide" role="button" data-slide="next"><i class="fa fa-angle-right"></i></a></div>
                    <div class="title">
                        <h3>最新发布</h3>
                        <div class="more">
                          <a href='http://www.meiladys.com/'>护肤品排行榜</a>
                          <a href='http://www.meiladys.com/'>日本护肤品</a>
                          <a href='http://www.meiladys.com/'>韩国护肤品</a>
                          <a href='http://www.meiladys.com/'>美丽女人护肤品</a>
                        </div>
                    </div>
                    <c:forEach items="${list}" var="article" varStatus="current">
				    <article class="excerpt excerpt-${current.count}">
				         <a class="focus" href="/article/${article.staticName}">
				           <img data-src="${article.thumbnail}" class="thumb" src="${article.thumbnail}" style="display: inline;">
				         </a>
				         <header><a class="cat" href="/${article.categoryName}.html">${article.categoryName}<i></i></a> <h2>
				           <a href="/article/${article.staticName}" title="${article.title}">${article.title}</a></h2>
				         </header>
				          <p class="meta"><time><i class="fa fa-clock-o"></i><fmt:formatDate value="${item.createdTime}" pattern="yyyy-dd-MM" /></time><span class="author">
				          <i class="fa fa-user"></i>${item.publisher}</span><span class="pv"><i class="fa fa-eye"></i>阅读((${article.viewCount})</span>
				          <a class="pc" href=""><i class="fa fa-comments-o"></i>评论(${article.commentCount})</a></p>
				          <p class="note">${article.summary}</p>
				    </article>
				    </c:forEach>
                    <div class="pagination">
			        <ul>
			            <li class="prev-page">
			                <c:if test="${page.page != 1}">
			                   <c:if test="${page.page != 2}">
				                  <a href="/index-${page.page-1}.html" >上一页</a>
				               </c:if>
				               <c:if test="${page.page == 2}">
				                  <a href="/" >上一页</a>
				               </c:if>
				            </c:if>
			            </li>
			            <c:forEach items="${page.pages}" var="item" varStatus="current">
			                <c:if test="${item == page.page}">
			                   <li class="active"><span>${item}</span></li>
			                </c:if>
			                <c:if test="${item != page.page}">
			                   <li><a href="/index-${item}.html">${item}</a></li>
			                </c:if>
			            </c:forEach>
			            <li class="next-page">
				            <c:if test="${page.page < page.totalPage}">
				             <a href="/index-${page.page+1}.html" >下一页</a>
				            </c:if>
			            </li>
			            <li><span>共 ${page.totalPage} 页</span></li>
			        </ul>
    </div>		
                </div>
            </div>
            <aside class="sidebar">
                <%@include file="inc/top.inc.jsp"%>
                <%@include file="inc/hot.inc.jsp"%>
                <%@include file="inc/new.inc.jsp"%>
            </aside>
        </section>
         <%@include file="inc/footer.inc.jsp"%>
    </body>
</html>
   