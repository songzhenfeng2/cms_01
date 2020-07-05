package com.szf.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szf.cms.dao.UserDao;
import com.szf.cms.domain.User;
import com.szf.cms.service.UserService;
import com.szf.cms.util.CMSException;
import com.szf.cms.util.Md5Util;
import com.szf.cms.vo.UserVO;
import com.szf.util.StringUtil;

@Service
public class userServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	@Override
	public PageInfo<UserVO> getUserList(UserVO userVO, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<UserVO> list = userDao.getUserList(userVO);
		return new PageInfo<UserVO>(list);
	}

	@Override
	public int updateUser(UserVO user) {
		return userDao.updateUser(user);
	}

	@Override
	public int insertUser(User user) {
		// 1.用户名不能不空
		if (!StringUtil.hasText(user.getUsername()))
			throw new CMSException("用户名不能为空");
		// 2用户名长度在5-10之间
		if (!(user.getUsername().length() >= 5 && user.getUsername().length() <= 10))
			throw new CMSException("用户名的长度必须在5-10之间");
		// 3密码不能为空
		if (!StringUtil.hasText(user.getPassword()))
			throw new CMSException("密码不能不空");
		// 4密码在6-10位之间
		if (!(user.getPassword().length() >= 6 && user.getPassword().length() <= 10))
			throw new CMSException("用户名的长度必须在6-10之间");
		// 5两次密码输入要一致
		if (!user.getPassword().equals(user.getRepassword()))
			throw new CMSException("两次密码输入不一致");
		// 6.用户名不能重复注册
		User u = userDao.getUserByName(user.getUsername());
		if (null != u)
			throw new CMSException("用户名已被注册");
		// 7.对密码进行加密
		user.setPassword(Md5Util.Md5Encode(user.getPassword()));
		return userDao.insertUser(user);
	}

	@Override
	public User getUserByName(String username) {
		// TODO Auto-generated method stub
		return userDao.getUserByName(username);
	}

	@Override
	public User login(User user) {

		// 1.用户名不能为空
		if (!StringUtil.hasText(user.getUsername()))
			throw new CMSException("用户名不能为空");
		// 2用户名是否存在
		User u = getUserByName(user.getUsername());
		if(null==u)
			throw new CMSException("用户名不存在");
		// 3.密码不能为空
		 if(!StringUtil.hasText(user.getPassword())) 
			 throw new CMSException("密码不能为空");
		// 4.密码是否正确--需要对传入的密码进行加密后再和数据库的密码进行比较
		 if(!Md5Util.Md5Encode(user.getPassword()).equals(u.getPassword()))
			 throw new CMSException("密码不正确");
		 //5/用户账户被禁用不能登录
		 if(u.getLocked()==1)
			 throw new CMSException("账户被禁用，请联系管理员");
		return u;
	}


}
