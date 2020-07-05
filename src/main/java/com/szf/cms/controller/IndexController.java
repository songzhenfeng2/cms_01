package com.szf.cms.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.szf.cms.domain.Article;
import com.szf.cms.domain.Category;
import com.szf.cms.domain.Channel;
import com.szf.cms.domain.Comments;
import com.szf.cms.domain.Slide;
import com.szf.cms.domain.User;
import com.szf.cms.service.ArticleService;
import com.szf.cms.service.ChannelService;
import com.szf.cms.service.CommentService;
import com.szf.cms.service.SlideService;
import com.szf.util.DateUtil;

/**
 * 
 * @ClassName: IndexController 
 * @Description: 系统首页
 * @author: 宋圳峰
 * @date: 2020年6月5日 上午11:06:33
 */
@Controller
public class IndexController {
	@Resource
	private ChannelService channelService;
	@Resource
	private ArticleService articleService ;
	@Resource
	private SlideService slideService;
	@Resource
	private CommentService commentService;
	
	@RequestMapping(value = {"","/","index"})
	public String index(Model model,Article article,@RequestParam(defaultValue = "1")Integer pageNum,
			@RequestParam(defaultValue = "6")Integer pageSize) {
		article.setStatus(1);//只显示审核过的文章
		model.addAttribute("article", article);
				//1查询出所有栏目
				List<Channel> channels = channelService.getChannel();
				model.addAttribute("channels", channels);
				//2如果栏目id不为空，则根据栏目查询分类
				if(article.getChannelId()!=null) {
					//2.1查询栏目下分类
					List<Category> categorys = channelService.getCategoryById(article.getChannelId());
					model.addAttribute("categorys", categorys);
					//2.1查询栏目下的文章
					PageInfo<Article> info = articleService.getArticle(article, pageSize, pageNum);
					model.addAttribute("info", info);
				}
				//3 .如果未点栏目或者点击的是热点栏目，则显示热点文章
				if(article.getChannelId()==null) {
					//3.1查询热点文章
					article.setHot(1);//热点文章
					PageInfo<Article> info = articleService.getArticle(article, pageSize, pageNum);
					model.addAttribute("info", info);
					//3.2查询广告
					List<Slide> slides = slideService.getSlide();
					model.addAttribute("slides", slides);
				}
				//4 24小时热文  -只显示4条
				//4.1获取昨日的日期
				Date startDate = DateUtil.addDays(-1, new Date());
				article.setCreated(startDate);
				//4.2 设置热点文章
				article.setHot(1);
				PageInfo<Article> hot24Articles = articleService.getArticle(article, 4, 1);
				model.addAttribute("hot24Articles", hot24Articles);
				
				return "index/index";
	}
	
	/**
	 * 
	 * @Title: detail 
	 * @Description: 查看文章详情
	 * @param id
	 * @return
	 * @return: String
	 */
	@GetMapping("detail")
	public String detail(Model model,Integer id,@RequestParam(defaultValue = "1")Integer pageNum,
			@RequestParam(defaultValue = "6")Integer pageSize) {
		Article article = articleService.getArticleById(id);
		model.addAttribute("article", article);
		
		//查询出该文章的评论
		PageInfo<Comments> info = commentService.selectsByArticleId(id, pageNum, pageSize);
		model.addAttribute("info", info);
		return "index/article";
		
	}
	
	/**
	 * 
	 * @Title: addComments 
	 * Description: 增加评论
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("addComments")
	public boolean addComments(Comments comments,HttpSession session) {
		User user = (User) session.getAttribute("user");
		comments.setUserId(user.getId());
		comments.setCreated(new Date());
		return commentService.insertComment(comments)>0;
	}
}
