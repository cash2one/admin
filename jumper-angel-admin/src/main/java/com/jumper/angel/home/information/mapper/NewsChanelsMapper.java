package com.jumper.angel.home.information.mapper;

import java.util.List;
import java.util.Map;

import com.jumper.angel.home.information.entity.NewsChanels;

/**
 * 新闻频道mapper
 * @author gyx
 * @time 2016年12月28日
 */
public interface NewsChanelsMapper {

	/**
	 * 获取新闻频道总记录数
	 * @return
	 */
	public int findCount();

	/**
	 * 获取新闻频道分页数据
	 * @param map
	 * @return
	 */
	public List<NewsChanels> findNewsChanelsList(Map<String, Object> map);

	/**
	 * 删除资讯频道
	 * @param chanelId 频道id
	 */
	public void deleteNewsChanels(int chanelId);

	/**
	 * 通过频道id查询资讯频道信息
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
	 */
	public void updateNewsChanels(NewsChanels chanels);

	/**
	 * 根据名称查询频道信息
	 * @param map
	 * @return
	 */
	public NewsChanels findNewsChanelByName(Map<String, Object> map);

	/**
	 * 添加频道信息
	 * @param newsChanels
	 */
	public void addNewsChanels(NewsChanels newsChanels);

	/**
	 * 获取最大的排序值
	 * @return
	 */
	public Integer getLastRecordByOrderBy();

	/**
	 * 获取
	 * @return
	 */
	public List<NewsChanels> findAllNewsChanels();

	/**
	 * 删除用户订阅频道信息
	 * @param chanelId 频道id
	 */
	public void deleteUserSubscribeChannel(int chanelId);
	
}
