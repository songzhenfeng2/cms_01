package com.szf.cms.dao;

import java.util.List;

import com.szf.cms.domain.Article;

public interface ArticleDao {
	/**
	 * 
	 * @Title: getArticle 
	 * @Description: 文章的模糊查询
	 * @param article
	 * @return
	 * @return: List<Article>
	 */
	List<Article> getArticle(Article article);
	/**
	 * 
	 * @Title: getArticleById 
	 * @Description: 文章详情的查询
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article getArticleById(Integer id);
	
	/**
	 * 
	 * @Title: insert 
	 * @Description: 文章添加
	 * @param article
	 * @return
	 * @return: int
	 */
	int insert(Article article);
	
	/**
	 * 
	 * @Title: update 
	 * @Description: 更新文章的状态
	 * @param id
	 * @param hot
	 * @return: void
	 */
	int update(Article article);
}
