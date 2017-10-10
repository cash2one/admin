package com.jumper.angel.home.encyclopedia.service;

import java.util.List;

import com.jumper.angel.home.encyclopedia.entity.HelperQuestionClass;

/**
 * 常见问题类型Service
 * @author gyx
 * @time 2016年12月1日
 */
public interface HelperQuestionClassService {

	/**
	 * 获取孕期百科类型列表
	 * @return
	 */
	List<HelperQuestionClass> getAllQuestionClass();

	/**
	 * 关键字查询总记录数
	 * @param keywords 关键字
	 * @return 记录数
	 */
	int findCount(String keywords);

	/**
	 * 条件查询模块分页数据
	 * @param beginIndex 分页索引
	 * @param everyPage 每页记录数
	 * @param keywords 关键字
	 * @return
	 */
	List<HelperQuestionClass> findQuestionClassList(int beginIndex,
			int everyPage, String keywords);

	/**
	 * 通过模块名查询模块信息
	 * @param questionClass
	 * @return
	 */
	HelperQuestionClass findQuestionClassByName(
			HelperQuestionClass questionClass);

	/**
	 * 添加或修改模块信息
	 * @param questionClass
	 * @return
	 */
	boolean addOrEditQuestionClass(HelperQuestionClass questionClass);

	/**
	 * 删除模块信息
	 * @param classId 模块id
	 * @return
	 */
	boolean deleteQuestionClass(int classId);


}
