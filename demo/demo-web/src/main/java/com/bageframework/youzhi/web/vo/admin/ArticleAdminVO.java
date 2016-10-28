package com.bageframework.youzhi.web.vo.admin;

import com.bageframework.dao.annotation.AutoDate;
import java.util.Date;
import com.bageframework.dao.annotation.PrimaryKey;
import com.bageframework.youzhi.web.model.Article;

import org.springframework.beans.BeanUtils;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public class ArticleAdminVO {

	@PrimaryKey
	private Integer id;

	private String title;

	private Integer category;

	private String keyword;

	private String summary;

	private String staticName;

	private String tags;

	private String content;

	private String thumbnail;

	private Integer isImg;

	private Integer isRecom;

	private Integer commentCount;

	private Integer viewCount;

	@AutoDate
	private Date createdTime;

	private String createdUser;

	private String publisher;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getStaticName() {
		return staticName;
	}

	public void setStaticName(String staticName) {
		this.staticName = staticName;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Integer getIsImg() {
		return isImg;
	}

	public void setIsImg(Integer isImg) {
		this.isImg = isImg;
	}

	public Integer getIsRecom() {
		return isRecom;
	}

	public void setIsRecom(Integer isRecom) {
		this.isRecom = isRecom;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public static ArticleAdminVO create(Article bean) {
		ArticleAdminVO vo = new ArticleAdminVO();
		BeanUtils.copyProperties(bean, vo);
		return vo;
	}

}