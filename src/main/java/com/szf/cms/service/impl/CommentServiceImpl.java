package com.szf.cms.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szf.cms.dao.CommentDao;
import com.szf.cms.domain.Comments;
import com.szf.cms.service.CommentService;
import com.szf.util.DateUtil;
@Service
public class CommentServiceImpl implements CommentService {
	
	@Resource
	private CommentDao commentDao;
	@Override
	public int insertComment(Comments comments) {
		// TODO Auto-generated method stub
		return commentDao.insertComment(comments);
	}

	@Override
	public PageInfo<Comments> selectsByArticleId(Integer articleId,Integer pageNum,Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<Comments> list = commentDao.selectsByArticleId(articleId);
		for (Comments comments : list) {
			comments.setDisPlayDate(DateUtil.getDisplayTime(comments.getCreated()));
		}
		return new PageInfo<Comments>(list);
	}

}
