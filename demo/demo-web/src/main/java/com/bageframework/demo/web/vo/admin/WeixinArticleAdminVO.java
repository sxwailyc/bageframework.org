package com.bageframework.demo.web.vo.admin;

import java.util.Date;
import com.bageframework.dao.annotation.PrimaryKey;
import com.bageframework.demo.web.model.WeixinArticle;
import org.springframework.beans.BeanUtils;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public class WeixinArticleAdminVO {

	@PrimaryKey
	private Integer id;

	private String publishDate;

	private String publisher;

	private String articleUrl;

	private String html;

	private String summary;

	private String queryWord;

	private String url;

	private Date crawledTime;

	private String title;

	private String content;

	private Integer status;

	private Integer articleId;

	private int categoryId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getArticleUrl() {
		return articleUrl;
	}

	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getQueryWord() {
		return queryWord;
	}

	public void setQueryWord(String queryWord) {
		this.queryWord = queryWord;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCrawledTime() {
		return crawledTime;
	}

	public void setCrawledTime(Date crawledTime) {
		this.crawledTime = crawledTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public static WeixinArticleAdminVO create(WeixinArticle bean) {
		WeixinArticleAdminVO vo = new WeixinArticleAdminVO();
		BeanUtils.copyProperties(bean, vo);
		return vo;
	}

}