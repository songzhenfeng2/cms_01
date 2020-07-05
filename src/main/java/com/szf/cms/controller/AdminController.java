package com.szf.cms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.szf.cms.domain.Article;
import com.szf.cms.service.ArticleService;
import com.szf.cms.service.UserService;
import com.szf.cms.vo.UserVO;
/**
 * 
 * @ClassName: AdminController 
 * @Description: 管理员中心
 * @author: 宋圳峰
 * @date: 2020年6月5日 上午11:37:37
 */
@RequestMapping("admin")
@Controller
public class AdminController {
	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private UserService userService;
	/**
	 * 
	 * @Title: index 
	 * @Description: 管理员页面
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = {"","/","index"})
	public String index() {
		return "admin/index";
	}
	
	
	
	/**
	 * 
	 * @Title: articles 
	 * @Description: 查询文章页面信息 并分页
	 * @param model
	 * @param article
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@RequestMapping("articles")
	public String articles(Model model,Article article,@RequestParam(defaultValue = "1")Integer pageNum,
			@RequestParam(defaultValue = "10")Integer pageSize) {
		
		PageInfo<Article> info = articleService.getArticle(article, pageSize, pageNum);
		model.addAttribute("info", info);
		model.addAttribute("article", article);
		
		return "admin/article";
	}
	
	/**
	 * 
	 * @Title: getArticleById 
	 * @Description: 文章详情
	 * @param id
	 * @return
	 * @return: Article
	 */
	@RequestMapping("article")
	@ResponseBody
	public Article getArticleById(Integer id){
		return articleService.getArticleById(id);
	} 
	
	/**
	 * 
	 * @Title: update 
	 * @Description: 对文章的状态进行修改
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@RequestMapping("update")
	@ResponseBody
	public boolean update(Article article) {
		return articleService.update(article)>0;
	} 

	/**
	 * 
	 * @Title: users 
	 * @Description: 显示所有用户
	 * @param model
	 * @param userVO
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@RequestMapping("users")
	public String users(Model model,UserVO userVO,@RequestParam(defaultValue = "1")Integer pageNum,
			@RequestParam(defaultValue = "7")Integer pageSize) {
		PageInfo<UserVO> info = userService.getUserList(userVO, pageNum, pageSize);
		
		model.addAttribute("info", info);
		model.addAttribute("userVO", userVO);
		return "admin/users";
	}
	
	@RequestMapping("updateUser")
	@ResponseBody
	public boolean updateUSer(UserVO userVO) {
		
		return userService.updateUser(userVO)>0;
	}
}
