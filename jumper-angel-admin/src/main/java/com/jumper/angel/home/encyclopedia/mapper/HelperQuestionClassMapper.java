package com.jumper.angel.home.encyclopedia.mapper;

import java.util.List;
import java.util.Map;

import com.jumper.angel.home.encyclopedia.entity.HelperQuestionClass;
/**
 * 孕期百科类型Mapper
 * @author gyx
 * @time 2016年12月1日
 */
public interface HelperQuestionClassMapper {

	/**
	 * 获取孕期百科常见问题列表
	 * @return
	 */
	List<HelperQuestionClass> getAllQuestionClass();

	/**
	 * 关键字查询总记录数
	 * @param keywords 关键字
	 * @return 记录数
	 */
	int findCount(Map<String, Object> map);

	/**
	 * 条件获取模块分页数据
	 * @param map
	 * @return
	 */
	List<HelperQuestionClass> findQuestionClassList(Map<String, Object> map);

	/**
	 * 根据模块名查询模块是否已存在
	 * @param questionClass
	 * @return
	 */
	HelperQuestionClass findQuestionClassByName(
			HelperQuestionClass questionClass);

	/**
	 * 添加模块信息
	 * @param questionClass
	 */
	void addQuestionClass(HelperQuestionClass questionClass);

	/**
	 * 修改模块信息
	 * @param questionClass
	 */
	void updateQuestionClass(HelperQuestionClass questionClass);

	/**
	 * 删除模块信息
	 * @param classId 模块id
	 */
	void deleteQuestionClass(int classId);
	
}
