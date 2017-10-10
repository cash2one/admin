package com.jumper.angel.home.pregnancy.mapper;

import java.util.List;
import java.util.Map;

import com.jumper.angel.home.pregnancy.entity.PregnantAntenatalExaminationInfo;

/**
 * 孕期产检信息mapper
 * @author gyx
 * @time 2016年12月1日
 */
public interface PregnantAntenatalExaminationInfoMapper {

	/**
	 * 通过孕周获取孕期产检信息
	 * @param pregnantWeek
	 * @return
	 */
	public PregnantAntenatalExaminationInfo findPrenatalRemind(int pregnantWeek);

	/**
	 * 获取产检信息列表
	 * @return
	 */
	public List<PregnantAntenatalExaminationInfo> findPregAnteExaminationInfoList();

	/**
	 * 删除产检信息
	 * @param examinationInfoId
	 */
	public void deleteExaminationInfo(int examinationInfoId);

	/**
	 * 添加产检信息
	 * @param examinationInfo
	 */
	public void addExaminationInfo(
			PregnantAntenatalExaminationInfo examinationInfo);

	/**
	 * 修改产检信息
	 * @param examinationInfo
	 */
	public void editExaminationInfo(
			PregnantAntenatalExaminationInfo examinationInfo);

	/**
	 * 查询产检次数是否存在
	 * @param examNumbers
	 * @return
	 */
	public PregnantAntenatalExaminationInfo checkExamNumbers(Map<String, Object> map);

	/**
	 * 根据id查询产检次数
	 * @param examinationInfoId 产检id
	 * @return
	 */
	public PregnantAntenatalExaminationInfo findPregAnteExamInfoById(
			Integer examinationInfoId);

	/**
	 * 查询产检项目信息对应的产检次数信息
	 * @return
	 */
	public List<PregnantAntenatalExaminationInfo> findPregAnteExamItems();
	
}
