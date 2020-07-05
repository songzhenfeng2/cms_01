package com.szf.cms.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @ClassName: Comments 
 * @Description: 评论表
 * @author: 宋圳峰
 * @date: 2020年6月8日 下午7:51:14
 */
public class Comments implements Serializable{
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 8334547230255038115L;
	private Integer id;
	private Integer userId;
	private Integer articleId;
	private String content;
	private Date created;
	private User user;
	private Article article;
	private String disPlayDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public String getDisPlayDate() {
		return disPlayDate;
	}
	public void setDisPlayDate(String disPlayDate) {
		this.disPlayDate = disPlayDate;
	}
	
	
	
	
}
