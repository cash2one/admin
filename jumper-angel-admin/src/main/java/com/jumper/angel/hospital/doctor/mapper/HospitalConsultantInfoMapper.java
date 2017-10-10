package com.jumper.angel.hospital.doctor.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jumper.angel.hospital.doctor.entity.HospitalConsultantInfo;

/**
 * 医院咨询信息Mapper
*
* HospitalConsultantBeanMapper
*Demo
* 2016年12月22日 下午1:46:15
*
* @version 1.0.0
 */
public interface HospitalConsultantInfoMapper {
	/** 根据id删除咨询信息	 */
    int deleteByPrimaryKey(Integer id);
    
    /** 全字段插入	 */
    int insert(HospitalConsultantInfo record);
    
    /** 非空字段插入，为空则不做处理 */
    int insertSelective(HospitalConsultantInfo record);
    
    /** 根据id查询咨询信息	 */
    HospitalConsultantInfo selectByPrimaryKey(Integer id);
    
    /** 根据主键选择性更新，为空字段则不更新 */
    int updateByPrimaryKeySelective(HospitalConsultantInfo record);
    
    /** 根据主键全部更新，对象某个字段为空，则会将数据库字段置空	 */
    int updateByPrimaryKey(HospitalConsultantInfo record);
    
	/** 查询所有的咨询信息	 */
	List<HospitalConsultantInfo> selectConsultantAll();
	
	/**查询问题库的数量*/
	int findProblemBaseCount(@Param("keyword")String keyword);
	
	/**查询所有咨询信息的数量*/
	int findConsultantCount(@Param("keyword")String keyword);
	
	/**查找所有咨询信息并分页*/
	List<Map<String, Object>> findAllHospitalConsultantInfo(@Param("start")int start,@Param("everyPage") int everyPage,@Param("keyword")String keyword);
	
	/**查找所有问题库问题并分页*/
	List<Map<String, Object>> findAllProblemBase(@Param("start")int start,@Param("everyPage") int everyPage,@Param("keyword")String keyword);
	
	/**通过当前用户的id查询所有的解答问题，回复中*/
	List<Map<String, Object>> selectByDoctorIdMyAnswering(@Param("start")int start,@Param("everyPage") int everyPage,@Param("keyword")String keyword,@Param("currentId") Integer doctorId);
	
	/**通过当前用户的id查询所有的解答问题，已结束*/
	List<Map<String, Object>> selectByDoctorIdMyAnswerend(@Param("start")int start,@Param("everyPage") int everyPage,@Param("keyword")String keyword,@Param("currentId") Integer doctorId);
	
	/**查询我的解答的数量,回复中*/
	int findMyAnsweringCount(@Param("keyword")String keyword, @Param("currentId")Integer currentId);
	
	/**查询我的解答的数量，已结束*/
	int findMyAnswerendCount(@Param("keyword")String keyword, @Param("currentId")Integer doctorId);

	/** 修改咨询服务状态 */
	void updateStatusById(String id);
	
}
