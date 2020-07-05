package com.szf.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szf.cms.dao.ArticleDao;
import com.szf.cms.domain.Article;
import com.szf.cms.service.ArticleService;
import com.szf.util.DateUtil;
@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Resource
	private ArticleDao articleDao;
	
	@Override
	public PageInfo<Article> getArticle(Article article,Integer pageSize,Integer pageNum) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<Article> list = articleDao.getArticle(article);
		for (Article article2 : list) {
			article2.setDisplayDate(DateUtil.getDisplayTime(article2.getCreated()));
		}
		return new PageInfo<Article>(list);
	}

	@Override
	public Article getArticleById(Integer id) {
		// TODO Auto-generated method stub
		return articleDao.getArticleById(id);
	}

	@Override
	public int insert(Article article) {
		// TODO Auto-generated method stub
		return articleDao.insert(article);
	}

	@Override
	public int update(Article article) {
		// TODO Auto-generated method stub
		return articleDao.update(article);
	}

}
