package com.szf.cms.service;

import com.github.pagehelper.PageInfo;
import com.szf.cms.domain.User;
import com.szf.cms.vo.UserVO;

public interface UserService {
	PageInfo<UserVO> getUserList(UserVO userVO,Integer pageNum,Integer pageSize);
	
	int updateUser(UserVO userVO);
	
	int insertUser(User user);
	
	User getUserByName(String username);
	
	User login(User user);
}
