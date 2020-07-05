package com.szf.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szf.cms.domain.User;
import com.szf.cms.service.UserService;
import com.szf.cms.util.CMSException;
import com.szf.cms.util.CMSResult;

@RequestMapping("passport")
@Controller
public class PassportController {

	@Resource
	private UserService userService;

	@GetMapping("reg")
	public String reg() {

		return "passport/reg";
	}

	@PostMapping("reg")
	@ResponseBody
	public CMSResult<User> reg(User user) {
		// 创建封装结果对象
		CMSResult<User> result = new CMSResult<User>();
		try {
			
			 
			// 执行添加
			userService.insertUser(user);
			result.setCode(200);
			result.setMsg("注册成功,请登录");

		} catch (CMSException e) {//自定义的消息
			result.setCode(500);//注册失败
			result.setMsg("注册失败"+e.getMessage());
		}catch (Exception e) {
			result.setCode(500);//注册失败
			result.setMsg("系统错误,请联系管理员");
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 跳转登录页面
	 * @param username
	 * @param model
	 * @return
	 * @return: String
	 */
	@GetMapping("login")
	public String login(String username, Model model) {
		model.addAttribute("username", username);
		return "passport/login";
	}
	
	/**
	 * 
	 * @Title: checkUsername 
	 * @Description: 检查输入的用户名是否重复
	 * @param username
	 * @return
	 * @return: boolean
	 */
	@RequestMapping("checkusername")
	@ResponseBody
	public boolean checkUsername(String username) {
		return userService.getUserByName(username)==null;
	}
	
	@PostMapping("login")
	@ResponseBody
	public CMSResult<User> login(User user,HttpSession session){
		CMSResult<User> result = new CMSResult<User>();
		User u = userService.login(user);
		try {
			// 执行添加
			if(u.getRole()==1)
				session.setAttribute("admin", u);
			else
				session.setAttribute("user", u);
			result.setCode(200);
			result.setMsg("登录成功");
			
		} catch (CMSException e) {//自定义的消息
			result.setCode(500);//注册失败
			result.setMsg("登录失败"+e.getMessage());
		}catch (Exception e) {
			result.setCode(500);//注册失败
			result.setMsg("系统错误,请联系管理员");
		}
		return result;
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
