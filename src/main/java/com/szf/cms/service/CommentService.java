package com.szf.cms.service;

import com.github.pagehelper.PageInfo;
import com.szf.cms.domain.Comments;

public interface CommentService {
	int insertComment(Comments comments);

	PageInfo<Comments> selectsByArticleId(Integer articleId,Integer pageNum,Integer pageSize);
}
