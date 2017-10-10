package com.jumper.angel.home.encyclopedia.service;

import java.util.List;

import com.jumper.angel.home.encyclopedia.entity.HelperQuestions;


/**
 * 孕期百科问题Service
 * @author gyx
 * @time 2016年12月1日
 */
public interface HelperQuestionsService {

	/**
	 * 条件获取常见问题总记录数
	 * @param keywords 关键字
	 * @param typeId 
	 * @param classId 标签id
	 * @return
	 */
	int findCount(String keywords, int classId, int typeId);

	/**
	 * 
	 * @param beginIndex 分页索引
	 * @param everyPage 每页记录数
	 * @param keywords 关键字
	 * @param typeId 标签id
	 * @return
	 */
	List<HelperQuestions> findQuestionList(int beginIndex, int everyPage,
			String keywords, int classId, int typeId);

	/**
	 * 删除常见问题
	 * @param questionId 问题id
	 * @return
	 */
	boolean deleteQuestionClass(int questionId);

	/**
	 * 添加或修改百科内容
	 * @param questions
	 * @return
	 */
	boolean addOrEditQuestion(HelperQuestions questions);

	/**
	 * 操作百科内容
	 * @param questions
	 * @return
	 */
	boolean operateQuestion(HelperQuestions questions);

	
}
