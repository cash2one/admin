package com.jumper.angel.home.music.mapper;

import java.util.List;

import com.jumper.angel.home.music.entity.PregnantMusicLabel;

/**
 * 电台标签
 * @Description TODO
 * @author qinxiaowei
 * @date 2017-1-7
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public interface PregnantMusicLabelMapper {

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
	public void insertLabel(PregnantMusicLabel label);
	
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
	public PregnantMusicLabel findLabelByName(String labelName);
}
