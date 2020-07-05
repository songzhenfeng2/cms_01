package com.szf.cms.service;

import java.util.List;

import com.szf.cms.domain.Category;
import com.szf.cms.domain.Channel;

public interface ChannelService {
	List<Channel> getChannel();
	List<Category> getCategoryById(Integer channelId);
}
