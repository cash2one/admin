package com.jumper.angel.home.information.mapper;
/**
 * 分享Mapper
 * @Description TODO
 * @author qinxiaowei
 * @date 2016-12-2
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public interface NewsShareMapper {

	/**
	 * 删除文章分享
	 * @param newsId 文章id
	 */
	void deleteNewsShareByNewsId(int newsId);

}
