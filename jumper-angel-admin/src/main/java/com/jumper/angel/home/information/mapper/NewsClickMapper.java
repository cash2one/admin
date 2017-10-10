package com.jumper.angel.home.information.mapper;

import java.util.Map;


/**
 * 文章阅读量mapper
 * @author gyx
 * @time 2017年1月6日
 */
public interface NewsClickMapper {
	/**
	 * 获取文章某个时间段的阅读量
	 * @param id
	 * @param date
	 * @param date2
	 * @return
	 */
	Integer getBetweenDateClicks(Map<String, Object> map);

	/**
	 * 根据文章id删除文章点击量
	 * @param newsId 文章id
	 */
	void deleteNewsClick(int newsId);

}
