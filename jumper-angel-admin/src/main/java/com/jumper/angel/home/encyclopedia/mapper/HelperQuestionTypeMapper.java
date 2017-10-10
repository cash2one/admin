package com.jumper.angel.home.encyclopedia.mapper;

import java.util.List;
import java.util.Map;

import com.jumper.angel.home.encyclopedia.entity.HelperQuestionType;
/**
 * 孕期百科类型Mapper
 * @author gyx
 * @time 2016年12月1日
 */
public interface HelperQuestionTypeMapper {

	/**
	 * 通过父类型id获取子类型列表
	 * @param id
	 * @return
	 */
	List<HelperQuestionType> findQuestionTypeByQuestionClassId(Integer id);

	/**
	 * 条件获取常见问题标签数
	 * @param map
	 * @return
	 */
	int findCount(Map<String, Object> map);

	/**
	 * 条件获取常见问题标签列表
	 * @param map
	 * @return
	 */
	List<HelperQuestionType> findQuestionTypeList(Map<String, Object> map);

	/**
	 * 删除模块对应的标签
	 * @param classId 模块id
	 */
	void deleteQuestionTypeByClassId(int classId);

	/**
	 * 删除标签
	 * @param typeId 标签id
	 */
	void deleteQuestionTypeById(int typeId);

	/**
	 * 通过标签名查询标签信息
	 * @param questionType
	 * @return
	 */
	HelperQuestionType findQuestionTypeByName(HelperQuestionType questionType);

	/**
	 * 添加标签信息
	 * @param questionType
	 */
	void addQuestionType(HelperQuestionType questionType);

	/**
	 * 修改标签信息
	 * @param questionType
	 */
	void updateQuestionType(HelperQuestionType questionType);

	/**
	 * 获取所有的标签列表
	 * @return
	 */
	List<HelperQuestionType> findAllQuestionType();

	
	
}
