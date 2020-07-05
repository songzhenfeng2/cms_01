package com.szf.cms.dao;

import java.util.List;

import com.szf.cms.domain.Category;
import com.szf.cms.domain.Channel;
/**
 * 
 * @ClassName: ChannelDao 
 * @Description: 栏目及分类查询
 * @author: 宋圳峰
 * @date: 2020年5月31日 下午12:57:16
 */
public interface ChannelDao {
	/**
	 * 
	 * @Title: getChannel 
	 * @Description: 查询栏目
	 * @return
	 * @return: List<Channel>
	 */
	List<Channel> getChannel();
	/**
	 * 
	 * @Title: getCategoryById 
	 * @Description: 根据栏目id查询分类
	 * @param id
	 * @return
	 * @return: List<Category>
	 */
	List<Category> getCategoryById(Integer channelId);
}
