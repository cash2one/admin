package com.jumper.angel.home.encyclopedia.mapper;

import java.util.List;
import java.util.Map;

import com.jumper.angel.home.encyclopedia.entity.HelperQuestions;

/**
 * 孕期百科问题Mapper
 * @author gyx
 * @time 2016年12月1日
 */
public interface HelperQuestionsMapper {

	/**
	 * 通过类型获取问题列表分页数据
	 * @param map
	 * @return
	 */
	public List<HelperQuestions> findQuestionList(Map<String, Object> map);

	/**
	 * 删除模块对应的标签所对应的文章
	 * @param classId 模块id
	 */
	public void deleteQuestionByClassId(int classId);

	/**
	 * 删除标签下对应的文章
	 * @param typeId 标签
	 */
	public void deleteQuestionById(int typeId);

	/**
	 * 条件获取常见问题总记录数
	 * @param map
	 * @return
	 */
	public int findCount(Map<String, Object> map);

	/**
	 * 删除上常见问题
	 * @param questionId 问题id
	 */
	public void deleteQuestionClass(int questionId);

	/**
	 * 添加百科内容
	 * @param questions
	 */
	public void addQuestion(HelperQuestions questions);

	/**
	 * 修改百科内容
	 * @param questions
	 */
	public void updateQuestion(HelperQuestions questions);
	
}
