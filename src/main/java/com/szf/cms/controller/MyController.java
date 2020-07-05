package com.szf.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.szf.cms.domain.Article;
import com.szf.cms.domain.Category;
import com.szf.cms.domain.Channel;
import com.szf.cms.domain.User;
import com.szf.cms.service.ArticleService;
import com.szf.cms.service.ChannelService;
import com.szf.util.RandomUtil;
/**
 * 
 * @ClassName: MyController 
 * @Description: 个人中心
 * @author: 宋圳峰
 * @date: 2020年6月5日 上午11:37:52
 */
@RequestMapping("my")
@Controller
public class MyController {
	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private ChannelService channelService;
	
	
	@RequestMapping(value = {"","/","index"})
	public String index() {
		
		return "my/index";
	}
	
	/**
	 * 
	 * @Title: articles 
	 * @Description: 查询我的文章
	 * @param model
	 * @param article
	 * @param pageSize
	 * @param pageNum
	 * @return
	 * @return: String
	 */
	@RequestMapping("articles")
	public String articles(Model model,HttpSession session,Article article,@RequestParam(defaultValue = "6")Integer pageSize,
			@RequestParam(defaultValue = "1")Integer pageNum) {
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());
		PageInfo<Article> info = articleService.getArticle(article, pageSize, pageNum);
		model.addAttribute("article", article);
		model.addAttribute("info", info);
		return "my/article";
	}
	
	
	/**
	 * 
	 * @Title: article 
	 * @Description: 显示文章详情
	 * @param model
	 * @param id
	 * @return
	 * @return: Article
	 */
	@RequestMapping("article")
	@ResponseBody
	public Article article(Model model,Integer id) {
		return articleService.getArticleById(id);
	}
	
	/**
	 * 
	 * @Title: publish 
	 * @Description: 跳转文章发布页面
	 * @return
	 * @return: String
	 */
	@GetMapping("publish")
	public String publish() {
		
		return "my/publish";
	}
	/**
	 * 
	 * @Title: publish 
	 * @Description: 发布文章
	 * @param article
	 * @param file
	 * @return
	 * @return: boolean
	 */
	@PostMapping("publish")
	@ResponseBody
	public boolean publish(Article article,HttpSession session,MultipartFile file) {
		
		String path = "d:/pic";
		if(file!=null && !file.isEmpty()) {
			
			String oldName = file.getOriginalFilename();
			String fileName = RandomUtil.uuid()+oldName.substring(oldName.lastIndexOf("."));
			File f = new File(path,fileName);
			try {
				file.transferTo(f);
				article.setPicture(fileName);//封装 
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());
		
		article.setCreated(new Date());
		return articleService.insert(article)>0;
	}

	/**
	 * 
	 * @Title: channel 
	 * @Description: 查询栏目
	 * @return
	 * @return: List<Channel>
	 */
	@GetMapping("channel")
	@ResponseBody
	public List<Channel> channel(){
		return channelService.getChannel();
	}
	/**
	 * 
	 * @Title: category 
	 * @Description: 根据栏目id查询分类
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	@RequestMapping("category")
	@ResponseBody
	public List<Category> category(Integer channelId){
		return channelService.getCategoryById(channelId);
	}
	
}
