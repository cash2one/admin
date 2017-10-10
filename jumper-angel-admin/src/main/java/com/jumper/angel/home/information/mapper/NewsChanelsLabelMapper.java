package com.jumper.angel.home.information.mapper;

import java.util.List;

import com.jumper.angel.home.information.entity.NewsChanelsLabel;

/**
 * 频道标签 mapper
 * @author gyx
 * @time 2016年12月29日
 */
public interface NewsChanelsLabelMapper {

	/**
	 * 获取频道标签列表
	 * @param id
	 * @return
	 */
	List<NewsChanelsLabel> findNewsChanelsLabel(Integer chanelId);

	/**
	 * 删除频道标签
	 * @param chanelId 频道id
	 */
	void deleteNewsChanelsLabel(int chanelId);

	/**
	 * 添加频道标签
	 * @param newLabel
	 */
	void addNewsChanelsLabel(NewsChanelsLabel newLabel);
	
}
