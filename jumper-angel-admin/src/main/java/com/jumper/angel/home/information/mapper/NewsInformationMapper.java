package com.jumper.angel.home.information.mapper;

import java.util.List;
import java.util.Map;

import com.jumper.angel.home.information.entity.NewsInformation;

/**
 * 新闻mapper
 * @author gyx
 * @time 2017年1月5日
 */
public interface NewsInformationMapper {

	/**
	 * 获取已发布和未发布文章总数
	 * @param map
	 * @return
	 */
	int findCount(Map<String, Object> map);

	/**
	 * 获取文章列表
	 * @param map
	 * @return
	 */
	List<NewsInformation> findNewsInformationList(Map<String, Object> map);

	/**
	 * 修改文章信息
	 * @param news
	 */
	void updateNewsInformation(NewsInformation news);

	/**
	 * 删除文章
	 * @param newsId 文章id
	 */
	void deleteNewsInformation(int newsId);

	/**
	 * 查询文章信息
	 * @param newsId
	 * @return
	 */
	NewsInformation findNewsInformationById(int newsId);

	/**
	 * 添加文章信息
	 * @param news
	 */
	void addNewsInformation(NewsInformation news);

	/**
	 * 修改文章所属的频道为指定频道
	 * @param params
	 */
	void updateNewsInformationChannelId(Map<String, Object> params);

	

}
