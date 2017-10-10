package com.jumper.angel.home.information.service;

import java.util.List;

import com.jumper.angel.home.information.entity.NewsInformation;
import com.jumper.angel.home.information.vo.VONewsInformation;


/**
 * 资讯信息Service
 * @author gyx
 * @time 2017年1月5日
 */
public interface NewsInformationService {

	/**
	 * 获取已发布和未发布的文章总数
	 * @param isPublish 是否发布 0：未发布，1：已发布
	 * @return
	 */
	int findCount(int isPublish, String keywords, int chanels, int periods);

	/**
	 * 获取文章列表
	 * @param beginIndex 开始页
	 * @param everyPage 每页记录数
	 * @param isPublish 是否发布
	 * @return
	 */
	List<VONewsInformation> findNewsInformationList(int beginIndex,
			int everyPage, int isPublish, String keywords,int chanels, int periods);

	/**
	 * 修改文章信息
	 * @param news
	 * @return
	 */
	boolean updateNewsInformation(NewsInformation news);

	/**
	 * 删除文章
	 * @param newsId
	 * @return
	 */
	boolean deleteNewsInformation(int newsId);

	/**
	 * 查询文章信息
	 * @param newsId
	 * @return
	 */
	NewsInformation findNewsInformationById(int newsId);

	/**
	 * 添加或修改文章信息
	 * @param news
	 * @return
	 */
	boolean addOrEditNewsInformation(NewsInformation news);
	

}
