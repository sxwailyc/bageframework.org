<div class="pagetitle">
     <h1>${categoryName}</h1>
</div>
    <c:forEach items="${list}" var="article" varStatus="current">
    <article class="excerpt excerpt-${current.count}">
         <a class="focus" href="/article/${article.staticName}">
           <img data-src="${article.thumbnail}" class="thumb" src="${article.thumbnail}" style="display: inline;">
         </a>
         <header><a class="cat" href="/${categoryName}.html">${categoryName}<i></i></a> <h2>
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
	                  <a href="/${page.category}-${page.page-1}.html" >上一页</a>
	               </c:if>
	               <c:if test="${page.page == 2}">
	                  <a href="/${page.category}.html" >上一页</a>
	               </c:if>
	            </c:if>
            </li>
            <c:forEach items="${page.pages}" var="item" varStatus="current">
                <c:if test="${item == page.page}">
                   <li class="active"><span>${item}</span></li>
                </c:if>
                <c:if test="${item != page.page}">
                   <li><a href="/${page.category}-${item}.html">${item}</a></li>
                </c:if>
            </c:forEach>
            <li class="next-page">
	            <c:if test="${page.page < page.totalPage}">
	             <a href="/${page.category}-${page.page+1}.html" >下一页</a>
	            </c:if>
            </li>
            <li><span>共 ${page.totalPage} 页</span></li>
        </ul>
    </div>		