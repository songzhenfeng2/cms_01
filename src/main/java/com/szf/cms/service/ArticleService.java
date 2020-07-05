package com.szf.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.szf.cms.domain.Article;

public interface ArticleService {
	PageInfo<Article> getArticle(Article article,Integer pageSize,Integer pageNum);
	
	Article getArticleById(Integer id);
	
	int insert(Article article);
	
	int update(Article article);
}
