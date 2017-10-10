package com.jumper.angel.home.music.mapper;

import java.util.List;
import java.util.Map;

import com.jumper.angel.home.music.entity.PregnantMusicInfo;

/**
 * 孕期音乐
 * @Description TODO
 * @author qinxiaowei
 * @date 2016-12-29
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public interface PregnantMusicInfoMapper {
	
	/**
	 * 查询电台信息
	 * @version 1.0
	 * @createTime 2016-12-29,下午6:02:57
	 * @updateTime 2016-12-29,下午6:02:57
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param param
	 * @return
	 */
	public List<PregnantMusicInfo> findPregnantMusicByCategory(Map<String, Object> param);
	
	/**
	 * 查询最大sort号
	 * @version 1.0
	 * @createTime 2017-1-10,上午10:48:40
	 * @updateTime 2017-1-10,上午10:48:40
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @return
	 */
	public int findMaxSort();
	
	/**
	 * 查询电台总记录数
	 * @version 1.0
	 * @createTime 2016-12-29,下午6:03:39
	 * @updateTime 2016-12-29,下午6:03:39
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param category 音乐分类(3：电台)
	 * @return
	 */
	public int findCount(int category);
	
	/**
	 * 查询电台信息详情
	 * @version 1.0
	 * @createTime 2017-1-4,上午11:46:15
	 * @updateTime 2017-1-4,上午11:46:15
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param id
	 * @return
	 */
	public PregnantMusicInfo findPregnantMusicInfoDetail(int id);
	
	/**
	 * 修改电台信息
	 * @version 1.0
	 * @createTime 2017-1-4,上午11:46:50
	 * @updateTime 2017-1-4,上午11:46:50
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param param
	 */
	public void updatePregnantMusicInfo(PregnantMusicInfo param);
	
	/**
	 * 添加电台信息
	 * @version 1.0
	 * @createTime 2017-1-4,上午11:47:15
	 * @updateTime 2017-1-4,上午11:47:15
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param param
	 */
	public void insertPregnantMusicInfo(PregnantMusicInfo param);
}
