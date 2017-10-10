package com.jumper.angel.home.information.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumper.angel.home.information.entity.NewsChanels;
import com.jumper.angel.home.information.entity.NewsChanelsLabel;
import com.jumper.angel.home.information.mapper.NewsChanelsLabelMapper;
import com.jumper.angel.home.information.mapper.NewsChanelsMapper;
import com.jumper.angel.home.information.mapper.NewsInformationMapper;
import com.jumper.angel.home.information.service.NewsChanelsService;
import com.jumper.angel.home.information.vo.VONewsChanels;
import com.jumper.angel.home.pregnancy.controller.PregnancyController;
import com.jumper.angel.utils.TimeUtils;
/**
 * 新闻频道 serviceImpl
 * @author gyx
 * @time 2016年12月28日
 */
@Service
@Transactional
public class NewsChanelsServiceImpl implements NewsChanelsService {
	
	private final static Logger logger = Logger.getLogger(NewsChanelsService.class);
	
	@Autowired
	private NewsChanelsMapper newsChanelsMapper;
	
	@Autowired
	private NewsChanelsLabelMapper newsChanelsLabelMapper;
	
	@Autowired
	private NewsInformationMapper newsInformationMapper;
	

	/**
	 * 获取新闻频道总记录数
	 */
	@Override
	public int findCount() {
		int count = newsChanelsMapper.findCount();
		return count;
	}

	/**
	 * 获取新闻频道分页数据
	 */
	@Override
	public List<VONewsChanels> findNewsChanelsList(int beginIndex,
			int everyPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginIndex", beginIndex);
		map.put("everyPage", everyPage);
		List<NewsChanels> newsChanelsList = newsChanelsMapper.findNewsChanelsList(map);
		if(newsChanelsList != null && newsChanelsList.size() > 0){
			List<VONewsChanels> voList = getVONewsChanelsList(newsChanelsList);
			return voList;
		}
		return null;
	}

	/**
	 * 组装频道列表
	 * @param newsChanelsList
	 * @return
	 */
	private List<VONewsChanels> getVONewsChanelsList(
			List<NewsChanels> newsChanelsList) {
		List<VONewsChanels> voList = new ArrayList<VONewsChanels>();
		if(newsChanelsList != null && newsChanelsList.size() > 0){
			for (NewsChanels newsChanels : newsChanelsList) {
				VONewsChanels voNewsChanels = new VONewsChanels();
				voNewsChanels.setId(newsChanels.getId());
				//频道名称
				if(StringUtils.isNotEmpty(newsChanels.getChanelName())){
					voNewsChanels.setChanelName(newsChanels.getChanelName());
				}
				//频道描述
				if(StringUtils.isNotEmpty(newsChanels.getChannelDesc())){
					voNewsChanels.setChannelDesc(newsChanels.getChannelDesc());
				}else{
					voNewsChanels.setChannelDesc("无");
				}
				//是否默认订阅
				voNewsChanels.setIsDefaultSub(newsChanels.getIsDefaultSub());
				
				//频道图片
				voNewsChanels.setImgUrl(newsChanels.getImgUrl());
				//频道标签
				
				//获取频道标签列表
				List<NewsChanelsLabel> labelList = newsChanelsLabelMapper.findNewsChanelsLabel(newsChanels.getId());
				if(labelList != null && labelList.size() > 0){
					String labels = "";
					for (NewsChanelsLabel newsChanelsLabel : labelList) {
						labels += newsChanelsLabel.getLabel()+"、";
					}
					voNewsChanels.setChanelLabels(labels.substring(0, labels.length()-1));
				}else{
					voNewsChanels.setChanelLabels("无");
				}
				
				//频道位置
				voNewsChanels.setOrderBy(newsChanels.getOrderBy());
				
				voList.add(voNewsChanels);
			}
		}
		return voList;
	}

	/**
	 * 删除资讯频道信息，以及频道标签
	 */
	@Override
	public boolean deleteNewsChanels(int chanelId) {
		boolean b = false;
		try {
			//删除频道标签
			newsChanelsLabelMapper.deleteNewsChanelsLabel(chanelId);
			//删除用户订阅频道信息
			newsChanelsMapper.deleteUserSubscribeChannel(chanelId);
			//删除频道
			newsChanelsMapper.deleteNewsChanels(chanelId);
			//频道下的文章并入孕期知识
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("chanelId", 0);
			map.put("chanelName", "孕期知识");
			NewsChanels chanels = newsChanelsMapper.findNewsChanelByName(map);
			if(chanels != null){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("oldChanelId", chanelId);
				params.put("newChanelId", chanels.getId());
				newsInformationMapper.updateNewsInformationChannelId(params);
			}
			b = true;
		} catch (Exception e) {
			b = false;
			logger.error("deleteNewsChanels failed:"+e.getMessage());
		}
		return b;
	}

	/**
	 * 通过资讯频道id查询频道信息
	 */
	@Override
	public NewsChanels findNewsChanelsById(int chanelId) {
		NewsChanels newsChanels = newsChanelsMapper.findNewsChanelsById(chanelId);
		if(newsChanels != null){
			return newsChanels;
		}
		return null;
	}

	/**
	 * 通过排序字段查询频道信息
	 */
	@Override
	public List<NewsChanels> findNewsChanelsByOrderBy(int orderBy) {
		List<NewsChanels> chanelList = newsChanelsMapper.findNewsChanelsByOrderBy(orderBy);
		if(chanelList != null && chanelList.size() > 0){
			return chanelList;
		}
		return null;
	}

	/**
	 * 修改频道信息
	 */
	@Override
	public boolean updateNewsChanels(NewsChanels chanels) {
		boolean b = false;
		try {
			newsChanelsMapper.updateNewsChanels(chanels);
			b = true;
		} catch (Exception e) {
			logger.error("updateNewsChanels failed:"+e.getMessage());
		}
		return b;
	}

	/**
	 * 根据名称查询频道
	 */
	@Override
	public NewsChanels findNewsChanelByName(int chanelId, String chanelName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("chanelId", chanelId);
		map.put("chanelName", chanelName);
		NewsChanels newsChanels = newsChanelsMapper.findNewsChanelByName(map);
		if(newsChanels != null){
			return newsChanels;
		}
		return null;
	}

	/**
	 * 添加或修改频道信息
	 */
	@Override
	public boolean addOrEditNewsChanels(NewsChanels newsChanels,
			String chanelLabelArray) {
		//添加或修改频道信息
		boolean b = false;
		try {
			if(newsChanels.getId()==0){
				//添加
				newsChanelsMapper.addNewsChanels(newsChanels);
			}else{
				//修改
				newsChanelsMapper.updateNewsChanels(newsChanels);
			}
			//删除频道标签(修改时)
			if(newsChanels.getId()!=0){
				newsChanelsLabelMapper.deleteNewsChanelsLabel(newsChanels.getId());
			}
			//添加频道标签（添加和修改）
			if(chanelLabelArray != null && !"".equals(chanelLabelArray)){
				String labels[] = chanelLabelArray.split(",");
				for(int i=0 ; i<labels.length ; i++){
					NewsChanelsLabel newLabel = new NewsChanelsLabel();
					newLabel.setChanelId(newsChanels.getId());//频道id
					newLabel.setLabel(labels[i]);//频道标签名
					newLabel.setAddTime(TimeUtils.getCurrentTime());//标签添加时间
					//添加标签
					newsChanelsLabelMapper.addNewsChanelsLabel(newLabel);
				}
			}
			b = true;
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 获取最大的排序值
	 */
	@Override
	public Integer getLastRecordByOrderBy() {
		Integer maxOrder = newsChanelsMapper.getLastRecordByOrderBy();
		return maxOrder;
	}

	/**
	 * 获取所有频道列表
	 */
	@Override
	public List<NewsChanels> findAllNewsChanels() {
		List<NewsChanels> newsChanels = newsChanelsMapper.findAllNewsChanels();
		if(newsChanels != null && newsChanels.size() > 0){
			return newsChanels;
		}
		return null;
	}
	
	
	
}
