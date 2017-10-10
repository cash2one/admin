package com.jumper.angel.home.information.service;

import java.util.List;

import com.jumper.angel.home.information.entity.NewsChanels;
import com.jumper.angel.home.information.vo.VONewsChanels;

/**
 * 新闻频道 service
 * @author gyx
 * @time 2016年12月28日
 */
public interface NewsChanelsService {

	/**
	 * 获取频道总记录数
	 * @return
	 */
	public int findCount();

	/**
	 * 获取新闻频道分页数据
	 * @param beginIndex
	 * @param everyPage
	 * @return
	 */
	public List<VONewsChanels> findNewsChanelsList(int beginIndex,
			int everyPage);

	/**
	 * 删除资讯频道信息
	 * @param chanelId 频道id
	 * @return
	 */
	public boolean deleteNewsChanels(int chanelId);

	/**
	 * 通过频道id查找频道
	 * @param chanelId 频道id
	 * @return
	 */
	public NewsChanels findNewsChanelsById(int chanelId);

	/**
	 * 通过排序字段查询频道信息
	 * @param orderBy 排序字段
	 * @return
	 */
	public List<NewsChanels> findNewsChanelsByOrderBy(int orderBy);

	/**
	 * 修改频道信息
	 * @param chanels
	 * @return
	 */
	public boolean updateNewsChanels(NewsChanels chanels);

	/**
	 * 通过名称查询频道信息
	 * @param chanelId 频道id
	 * @param chanelName 频道名称
	 * @return
	 */
	public NewsChanels findNewsChanelByName(int chanelId, String chanelName);

	/**
	 * 添加或修改频道信息
	 * @param newsChanels 频道信息
	 * @param chanelLabelArray 频道标签
	 * @return
	 */
	public boolean addOrEditNewsChanels(NewsChanels newsChanels,
			String chanelLabelArray);

	/**
	 * 获取最大的排序值
	 * @return
	 */
	public Integer getLastRecordByOrderBy();

	/**
	 * 获取所有的频道列表
	 * @return
	 */
	public List<NewsChanels> findAllNewsChanels();
	
}
