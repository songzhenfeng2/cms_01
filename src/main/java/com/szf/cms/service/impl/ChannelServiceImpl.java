package com.szf.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.szf.cms.dao.ChannelDao;
import com.szf.cms.domain.Category;
import com.szf.cms.domain.Channel;
import com.szf.cms.service.ChannelService;
@Service
public class ChannelServiceImpl implements ChannelService{
	@Resource
	private ChannelDao channelDao;
	
	@Override
	public List<Channel> getChannel() {
		// TODO Auto-generated method stub
		return channelDao.getChannel();
	}

	@Override
	public List<Category> getCategoryById(Integer channelId) {
		// TODO Auto-generated method stub
		return channelDao.getCategoryById(channelId);
	}

}
