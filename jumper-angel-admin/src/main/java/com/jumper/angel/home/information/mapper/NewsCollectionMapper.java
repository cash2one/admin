package com.jumper.angel.home.information.mapper;


/**
 * 收藏Mapper
 * @author gyx
 * @time 2017年1月7日
 */
public interface NewsCollectionMapper {

	/**
	 * 根据文章id删除文章收藏
	 * @param newsId 文章id
	 */
	void deleteNewsCollectionByNewsId(int newsId);
	

}
