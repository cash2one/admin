package com.jumper.angel.hospital.hospital.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jumper.angel.hospital.hospital.entity.HospitalServiceList;
/**
 * 医院服务相关
*
* HospitalServiceListMapper
*jumper-angel-admin
* 2017年6月8日 下午2:39:53
*
* @version 1.0.0
 */
public interface HospitalServiceListMapper {
    int deleteByPrimaryKey(Integer id);
    int deleteByHospitalId(Integer id);

    int insert(HospitalServiceList record);

    int insertSelective(HospitalServiceList record);

    HospitalServiceList selectByPrimaryKey(Integer id);
    HospitalServiceList selectByKey(@Param("id")Integer id, @Param("hospitalId")Integer hospitalId);

    int updateByPrimaryKeySelective(HospitalServiceList record);

    int updateByPrimaryKey(HospitalServiceList record);
    /** 查询医院基础功能 **/
//	List<HospitalServiceList> queryBasicFunction(Integer hospitalId);
	List<HospitalServiceList> queryBasicFunction(@Param("pageIndex")int pageIndex, @Param("everyPage")int everyPage, @Param("hospitalId")Integer hospitalId);

	/** 查询医院平台功能 **/
	List<HospitalServiceList> queryPlatformFunction(@Param("pageIndex")int pageIndex, @Param("everyPage")int everyPage, @Param("hospitalId")Integer hospitalId);
	
	/** 查询医院院内功能 **/
	List<HospitalServiceList> queryHospitalFunction(@Param("pageIndex")int pageIndex, @Param("everyPage")int everyPage, @Param("hospitalId")Integer hospitalId);

	/** 获取排序列表 **/
	List<HospitalServiceList> getRankList(@Param("hospitalId")Integer hospitalId);

	/**获取医院信息*/
	List<Map<String, Object>> getHospitalInformation(@Param("hospitalId")Integer hospitalId);

	/**修改医院服务入口状态 **/
	int updateHospitalServiceEntryStat(@Param("hospitalId")Integer hospitalId, @Param("moduleId")Integer moduleId, 
			@Param("entryStat")Integer entryStat, @Param("postionOrder")Integer postionOrder);

	/**查询该服务信息**/
	HospitalServiceList getHospitalServiceListByModuleId(@Param("hospitalId")Integer hospitalId, @Param("moduleId")Integer moduleId);

	/**修改医院服务链接状态*/
	int updateHospitalServiceUrlStat(@Param("hospitalId")Integer hospitalId, @Param("moduleId")Integer moduleId, 
			@Param("url")String url, @Param("urlStat")Integer urlStat);

	/**总记录数**/
	int findCount(@Param("hospitalId")Integer hospitalId, @Param("functionGroup")Integer functionGroup);

	/**院内功能总记录数**/
	int findCount2(@Param("hospitalId")Integer hospitalId);
	/**获取排序最大值**/
	Integer getMaxPostionOrder(@Param("hospitalId")Integer hospitalId);

}