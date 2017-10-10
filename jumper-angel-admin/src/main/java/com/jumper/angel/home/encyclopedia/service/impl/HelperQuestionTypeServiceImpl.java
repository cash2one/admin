package com.jumper.angel.home.encyclopedia.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.home.encyclopedia.entity.HelperQuestionType;
import com.jumper.angel.home.encyclopedia.mapper.HelperQuestionTypeMapper;
import com.jumper.angel.home.encyclopedia.mapper.HelperQuestionsMapper;
import com.jumper.angel.home.encyclopedia.service.HelperQuestionTypeService;

/**
 * 常见问题标签 serviceImpl
 * @author gyx
 * @time 2017年1月10日
 */
@Service
public class HelperQuestionTypeServiceImpl implements HelperQuestionTypeService{

	@Autowired
	private HelperQuestionTypeMapper typeMapper;
	
	@Autowired
	private HelperQuestionsMapper questionsMapper;
	/**
	 * 条件获取常见问题标签数
	 */
	@Override
	public int findCount(String keywords, int classId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keywords", keywords);
		map.put("classId", classId);
		int count = typeMapper.findCount(map);
		return count;
	}
	
	/**
	 * 条件获取常见问题标签列表
	 */
	@Override
	public List<HelperQuestionType> findQuestionTypeList(int beginIndex,
			int everyPage, String keywords, int classId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginIndex", beginIndex);
		map.put("everyPage", everyPage);
		map.put("keywords", keywords);
		map.put("classId", classId);
		List<HelperQuestionType> typeList = typeMapper.findQuestionTypeList(map);
		if(typeList != null && typeList.size() > 0){
			return typeList;
		}
		return null;
	}

	/**
	 * 删除标签
	 */
	@Override
	public boolean deleteQuestionType(int typeId) {
		boolean b = false;
		try {
			//删除标签下对应的文章
			questionsMapper.deleteQuestionById(typeId);
			
			//删除标签
			typeMapper.deleteQuestionTypeById(typeId);
			b = true;
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 通过标签名查询标签信息
	 */
	@Override
	public HelperQuestionType findQuestionTypeByName(
			HelperQuestionType questionType) {
		HelperQuestionType questionType2 = typeMapper.findQuestionTypeByName(questionType);
		if(questionType2 != null){
			return questionType2;
		}
		return null;
	}

	/**
	 * 添加或修改标签信息
	 */
	@Override
	public boolean addOrEditQuestionType(HelperQuestionType questionType) {
		boolean b = false;
		try {
			if(questionType.getId()==0){
				//添加
				typeMapper.addQuestionType(questionType);
			}else{
				//修改
				typeMapper.updateQuestionType(questionType);
			}
			b = true;
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 获取所有标签列表
	 */
	@Override
	public List<HelperQuestionType> getAllQuestionType() {
		List<HelperQuestionType> typeList = typeMapper.findAllQuestionType();
		if(typeList != null && typeList.size() > 0){
			return typeList;
		}
		return null;
	}

	/**
	 * 通过模块获取标签列表
	 */
	@Override
	public List<HelperQuestionType> findQuestionTypeByClass(int classId) {
		List<HelperQuestionType> typeList = typeMapper.findQuestionTypeByQuestionClassId(classId);
		if(typeList != null && typeList.size() > 0){
			return typeList;
		}
		return null;
	}
	
	
	
}
