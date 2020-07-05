package com.szf.cms.dao;

import java.util.List;

import com.szf.cms.domain.Comments;

public interface CommentDao {
	/**
	 * 
	 * @Title: insertComment 
	 * @Description: 添加评论
	 * @param comments
	 * @return
	 * @return: int
	 */
	int insertComment(Comments comments);

	/**
	 * 
	 * @Title: selectsByArticleId 
	 * @Description: 根据文章id查询文章评论
	 * @param articleId
	 * @return
	 * @return: List<Comments>
	 */
	List<Comments> selectsByArticleId(Integer articleId);
}
