package com.jumper.angel.home.pregnancy.service;

import java.util.List;

import com.jumper.angel.home.pregnancy.entity.PregnantAntenatalExaminationInfo;



/**
 * 孕期产检信息Service
 * @author gyx
 * @time 2016年12月1日
 */
public interface PregnantAntenatalExaminationInfoService {

	/**
	 * 根据孕周判断是否显示产检提醒入口
	 * @param pregnantWeek
	 * @return
	 */
	PregnantAntenatalExaminationInfo findPrenatalRemind(int pregnantWeek);

	/**
	 * 获取产检信息列表
	 * @return
	 */
	List<PregnantAntenatalExaminationInfo> findPregAnteExaminationInfoList();

	/**
	 * 删除产检信息
	 * @param examinationInfoId
	 * @return
	 */
	boolean deleteExaminationInfo(int examinationInfoId);

	/**
	 * 添加或修改产检信息
	 * @param examinationInfo
	 * @return
	 */
	boolean addOrEditExaminationInfo(
			PregnantAntenatalExaminationInfo examinationInfo);

	/**
	 * 检查产检次数是否存在
	 * @param examNumbers
	 * @return
	 */
	PregnantAntenatalExaminationInfo checkExamNumbers(int infoId, int examNumbers);

	/**
	 * 获取产检项目对应的产检次数信息
	 * @return
	 */
	List<PregnantAntenatalExaminationInfo> findPregAnteExamItems();
	
}
