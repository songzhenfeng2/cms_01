package com.szf.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.szf.cms.dao.SlideDao;
import com.szf.cms.domain.Slide;
import com.szf.cms.service.SlideService;
@Service
public class SlideServiceImpl implements SlideService{
	@Resource
	private SlideDao slideDao;
	@Override
	public List<Slide> getSlide() {
		// TODO Auto-generated method stub
		return slideDao.getSlide();
	}

}
