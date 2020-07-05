package com.szf.cms.dao;

import java.util.List;

import com.szf.cms.domain.User;
import com.szf.cms.vo.UserVO;

public interface UserDao {
	List<UserVO> getUserList(UserVO userVO);
	
	/**
	 * 
	 * @Title: updateUser 
	 * @Description: 修改用户
	 * @param userVO
	 * @return
	 * @return: int
	 */
	int updateUser(UserVO userVO);
	
	/**
	 * 
	 * @Title: insertUser 
	 * @Description: 注册用户
	 * @param user
	 * @return
	 * @return: int
	 */
	int insertUser(User user);
	
	/**
	 * 
	 * @Title: getUserByName 
	 * @Description: 检查用户名是否重复
	 * @param username
	 * @return
	 * @return: User
	 */
	User getUserByName(String username);
	
	User login(User user);
}
