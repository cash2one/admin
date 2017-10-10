package com.jumper.angel.home.music.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.home.music.entity.PregnantMusicInfo;
import com.jumper.angel.home.music.entity.PregnantMusicLabel;
import com.jumper.angel.home.music.mapper.PregnantMusicInfoMapper;
import com.jumper.angel.home.music.mapper.PregnantMusicLabelMapper;
import com.jumper.angel.home.music.service.PregnantMusicInfoService;
/**
 * 孕期电台
 * @Description TODO
 * @author qinxiaowei
 * @date 2016-12-29
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
@Service
public class PregnantMusicInfoServiceImpl implements PregnantMusicInfoService {
	
	@Autowired
	private PregnantMusicInfoMapper pregnantMusicInfoMapper;
	
	@Autowired
	private PregnantMusicLabelMapper pregnantMusicLabelMapper;
	
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
	public List<PregnantMusicInfo> findPregnantMusicByCategory(int category, int beginIndex, int everyPage) {
		//参数
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("category", category);
		param.put("beginIndex", beginIndex);
		param.put("everyPage", everyPage);
		return pregnantMusicInfoMapper.findPregnantMusicByCategory(param);
	}
	
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
	public int findCount(int category) {
		return pregnantMusicInfoMapper.findCount(category);
	}
	
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
	public PregnantMusicInfo findPregnantMusicInfoDetail(int id) {
		return pregnantMusicInfoMapper.findPregnantMusicInfoDetail(id);
	}
	
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
	public void firstMusic(int id) {
		PregnantMusicInfo music = new PregnantMusicInfo();
		//查询最大sort号
		int sort = pregnantMusicInfoMapper.findMaxSort();
		music.setId(id);
		music.setSort(sort+1);
		//更新
		pregnantMusicInfoMapper.updatePregnantMusicInfo(music);
	}
	
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
	public void saveAndUpdatePregnantMusicInfo(PregnantMusicInfo param) {
		//新增
		if(param.getId() == null) {
			//查询最大sort号
			int sort = pregnantMusicInfoMapper.findMaxSort();
			param.setSort(sort+1);
			pregnantMusicInfoMapper.insertPregnantMusicInfo(param);
		} else {	//修改
			pregnantMusicInfoMapper.updatePregnantMusicInfo(param);
		}
	}
	
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
	public int insertLabel(PregnantMusicLabel label) {
		pregnantMusicLabelMapper.insertLabel(label);
		return label.getId();
	}
	
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
	public List<PregnantMusicLabel> findLabelAll() {
		return pregnantMusicLabelMapper.findLabelAll();
	}
	
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
	public void deleteLabel(int id) {
		pregnantMusicLabelMapper.deleteLabel(id);
	}
	
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
	public boolean findLabelByName(String labelName) {
		PregnantMusicLabel label = pregnantMusicLabelMapper.findLabelByName(labelName);
		if(label != null) {
			return true;
		}
		return false;
	}
}
