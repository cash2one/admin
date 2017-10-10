package com.jumper.angel.home.encyclopedia.service;

import java.util.List;

import com.jumper.angel.home.encyclopedia.entity.HelperQuestionType;

/**
 * 常见问题标签 service
 * @author gyx
 * @time 2017年1月10日
 */
public interface HelperQuestionTypeService {

	/**
	 * 条件获取常见问题标签数
	 * @param keywords 关键字
	 * @param classId 模块id
	 * @return 标签数
	 */
	int findCount(String keywords, int classId);

	/**
	 * 条件获取常见问题标签
	 * @param beginIndex 分页索引
	 * @param everyPage 每页记录数
	 * @param keywords 关键字
	 * @param classId 模块id
	 * @return
	 */
	List<HelperQuestionType> findQuestionTypeList(int beginIndex,
			int everyPage, String keywords, int classId);

	/**
	 * 删除标签
	 * @param typeId 标签id
	 * @return
	 */
	boolean deleteQuestionType(int typeId);

	/**
	 * 通过标签名查询标签信息
	 * @param questionType
	 * @return
	 */
	HelperQuestionType findQuestionTypeByName(HelperQuestionType questionType);

	
	/**
	 * 添加或修改标签信息
	 * @param questionType
	 * @return
	 */
	boolean addOrEditQuestionType(HelperQuestionType questionType);

	/**
	 * 获取所有标签列表
	 * @return
	 */
	List<HelperQuestionType> getAllQuestionType();

	/**
	 * 通过模块获取标签
	 * @param classId 模块id
	 * @return
	 */
	List<HelperQuestionType> findQuestionTypeByClass(int classId);
	
}
