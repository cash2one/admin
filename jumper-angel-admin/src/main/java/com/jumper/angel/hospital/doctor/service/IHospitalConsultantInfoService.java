package com.jumper.angel.hospital.doctor.service;

import java.util.List;
import java.util.Map;

import com.jumper.angel.hospital.doctor.entity.HospitalConsultantInfo;

/**
 * 咨询信息相关服务
*
* IConsultantService
*jumper-doctor
* 2016年12月21日 下午7:41:14
*
* @version 1.0.0
 */
public interface IHospitalConsultantInfoService {
	/**查询问题库的数量*/
	int findProblemBaseCount(String keyword);
	
	/**查找所有咨询信息并分页*/
	List<Map<String, Object>> findAllHospitalConsultantInfo(int beginIndex, int everyPage,String keyword);
	
	/**查询所有咨询信息的数量*/
	int findConsultantCount(String keyword);
	
	/**查找所有问题库问题并分页*/
	List<Map<String, Object>> findAllProblemBase(int beginIndex, int everyPage,String keyword);
	
	/**通过id查询到对象,当前用户获取该问题
	 * @param busCode */
	HospitalConsultantInfo getProblem(Integer commentId,Integer currentId, String busCode);
	
	/**通过当前用户的id查询所有我的解答,回复中*/
	List<Map<String, Object>> findMyAnswering(int beginIndex, int everyPage,String keyword,Integer currentId);
	
	/**通过当前用户的id查询所有我的解答,已结束*/
	List<Map<String, Object>> findMyAnswerend(int beginIndex, int everyPage,String keyword,Integer currentId);
	
	/**查询我的解答的数量,回复中*/
	int findMyAnsweringCount(String keyword, Integer currentId);
	
	/**查询我的解答的数量,已结束*/
	int findMyAnswerendCount(String keyword, Integer currentId);
	
	/**通过consultId关闭咨询*/
	int deleteConsult(Integer consultId);

	/**通过当前医生ID查询serviceType*/
	Integer findServiceTypeByDoctorId(Integer currentId);

	/**通过问题ID查询该问题*/
	HospitalConsultantInfo findConsultantInfoById(Integer consultantId);

	/** 修改咨询回复状态 */
	void updateStatusById(String id);

	

}
