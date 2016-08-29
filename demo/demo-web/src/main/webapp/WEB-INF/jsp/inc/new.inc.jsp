<div class="widget widget_ui_posts">
    <h3>最新文章</h3>
    <ul>
    <c:forEach items="${newList}" var="item" varStatus="current">     
        <li><a target="_blank" href="/article/${item.staticName}">
          <span class="thumbnail"><img data-src="${item.thumbnail}" class="thumb"></span>
          <span class="text">${item.title}</span><span class="muted"><fmt:formatDate value="${item.createdTime}" pattern="yyyy-dd-MM" /></span>
          <span class="muted">评论(<span class="ds-thread-count" data-thread-key="${item.id}" data-replace="1">${item.commentCount}</span>)</span></a>
        </li>
    </c:forEach>
    </ul>
</div>