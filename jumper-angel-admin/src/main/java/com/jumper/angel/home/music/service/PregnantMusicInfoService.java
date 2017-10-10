package com.jumper.angel.home.music.service;

import java.util.List;
import com.jumper.angel.home.music.entity.PregnantMusicInfo;
import com.jumper.angel.home.music.entity.PregnantMusicLabel;

/**
 * 孕期电台
 * @Description TODO
 * @author qinxiaowei
 * @date 2016-12-29
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public interface PregnantMusicInfoService {
	
	/**
	 * 查询电台信息
	 * @version 1.0
	 * @createTime 2016-12-29,下午6:06:10
	 * @updateTime 2016-12-29,下午6:06:10
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param category 音乐分类(3：电台)
	 * @param beginIndex 页数
	 * @param everyPage 每页显示的记录数
	 * @return
	 */
	public List<PregnantMusicInfo> findPregnantMusicByCategory(int category, int beginIndex, int everyPage);
	
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
	 * 添加、修改电台信息
	 * @version 1.0
	 * @createTime 2017-1-4,上午11:47:15
	 * @updateTime 2017-1-4,上午11:47:15
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param param
	 */
	public void saveAndUpdatePregnantMusicInfo(PregnantMusicInfo param);
	
	/**
	 * 置顶
	 * @version 1.0
	 * @createTime 2017-1-11,下午2:13:59
	 * @updateTime 2017-1-11,下午2:13:59
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param id
	 */
	public void firstMusic(int id);
	
	/**
	 * 新增标签
	 * @version 1.0
	 * @createTime 2017-1-7,上午11:48:51
	 * @updateTime 2017-1-7,上午11:48:51
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param label
	 */
	public int insertLabel(PregnantMusicLabel label);
	
	/**
	 * 查询所有标签
	 * @version 1.0
	 * @createTime 2017-1-7,上午11:49:11
	 * @updateTime 2017-1-7,上午11:49:11
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @return
	 */
	public List<PregnantMusicLabel> findLabelAll();
	
	/**
	 * 删除标签
	 * @version 1.0
	 * @createTime 2017-1-9,上午11:03:40
	 * @updateTime 2017-1-9,上午11:03:40
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param id
	 */
	public void deleteLabel(int id);
	
	/**
	 * 通过标签名查询是否存在
	 * @version 1.0
	 * @createTime 2017-1-9,上午11:31:56
	 * @updateTime 2017-1-9,上午11:31:56
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param labelName
	 * @return
	 */
	public boolean findLabelByName(String labelName);
}
