<div class="widget widget_ui_posts">
    <h3>热门文章</h3>
    <ul class="nopic">
    <c:forEach items="${hotList}" var="item" varStatus="current"> 
        <li>
           <a target="_blank" href="/article/${item.staticName}"><span class="text">${item.title}</span>
                <span class="muted"><fmt:formatDate value="${item.createdTime}" pattern="yyyy-dd-MM" /></span><span class="muted">评论(<span class="ds-thread-count" data-thread-key="224" data-replace="1">${item.commentCount}</span>)</span>
           </a>
        </li>
    </c:forEach>
   </ul>
</div>