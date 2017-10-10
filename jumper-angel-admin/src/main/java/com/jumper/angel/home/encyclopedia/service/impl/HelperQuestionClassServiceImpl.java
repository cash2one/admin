package com.jumper.angel.home.encyclopedia.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumper.angel.home.encyclopedia.entity.HelperQuestionClass;
import com.jumper.angel.home.encyclopedia.mapper.HelperQuestionClassMapper;
import com.jumper.angel.home.encyclopedia.mapper.HelperQuestionTypeMapper;
import com.jumper.angel.home.encyclopedia.mapper.HelperQuestionsMapper;
import com.jumper.angel.home.encyclopedia.service.HelperQuestionClassService;
import com.jumper.angel.home.information.entity.NewsChanelsLabel;
import com.jumper.angel.utils.TimeUtils;

/**
 * 孕期百科类型ServiceImpl
 * @author gyx
 * @time 2016年12月1日
 */
@Service
@Transactional
public class HelperQuestionClassServiceImpl implements
		HelperQuestionClassService {
	@Autowired
	private HelperQuestionClassMapper helperQuestionClassMapper;
	
	@Autowired
	private HelperQuestionTypeMapper helperQuestionTypeMapper;
	
	@Autowired
	private HelperQuestionsMapper questionsMapper;

	/**
	 * 获取孕期百科常见问题类型列表
	 */
	@Override
	public List<HelperQuestionClass> getAllQuestionClass() {
		List<HelperQuestionClass> helperQuestionClassList = helperQuestionClassMapper.getAllQuestionClass();
		if(helperQuestionClassList != null && helperQuestionClassList.size() > 0){
			return helperQuestionClassList;
		}
		return null;
	}

	/**
	 * 条件获取总记录数
	 */
	@Override
	public int findCount(String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keywords", keywords);
		int count = helperQuestionClassMapper.findCount(map);
		return count;
	}

	/**
	 * 条件获取模块分页数据
	 */
	@Override
	public List<HelperQuestionClass> findQuestionClassList(int beginIndex,
			int everyPage, String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginIndex", beginIndex);
		map.put("everyPage", everyPage);
		map.put("keywords", keywords);
		List<HelperQuestionClass> questionClassList = helperQuestionClassMapper.findQuestionClassList(map);
		if(questionClassList != null && questionClassList.size() > 0){
			return questionClassList;
		}
		return null;
	}

	/**
	 * 根据模块名查询模块是否已存在
	 */
	@Override
	public HelperQuestionClass findQuestionClassByName(
			HelperQuestionClass questionClass) {
		HelperQuestionClass questionClass2 = helperQuestionClassMapper.findQuestionClassByName(questionClass);
		if(questionClass2 != null){
			return questionClass2;
		}
		return null;
	}

	/**
	 * 添加或修改模块信息
	 */
	@Override
	public boolean addOrEditQuestionClass(HelperQuestionClass questionClass) {
		boolean b = false;
		try {
			if(questionClass.getId()==0){
				//添加
				helperQuestionClassMapper.addQuestionClass(questionClass);
			}else{
				//修改
				helperQuestionClassMapper.updateQuestionClass(questionClass);
			}
			b = true;
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 删除模块信息
	 */
	@Override
	public boolean deleteQuestionClass(int classId) {
		boolean b = false;
		try {
			
			//删除标签对应的文章
			questionsMapper.deleteQuestionByClassId(classId);
			
			//删除模块对应的标签
			helperQuestionTypeMapper.deleteQuestionTypeByClassId(classId);
			
			//删除模块
			helperQuestionClassMapper.deleteQuestionClass(classId);
			
			b = true;
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	
}
