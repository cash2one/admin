package com.jumper.angel.hospital.hospital.service;

import java.util.List;
import java.util.Map;

import com.jumper.angel.hospital.hospital.entity.HospitalServiceList;
import com.jumper.angel.hospital.hospital.entity.HospitalServiceModule;
import com.jumper.angel.utils.ResultMsg;

public interface IHospitalServiceListService {

	/** 查询医院基础功能  **/
	List<HospitalServiceList> queryBasicFunction(int pageIndex, int everyPage, Integer hospitalId);

	/** 查询医院平台功能 **/
	List<HospitalServiceList> queryPlatformFunction(int pageIndex, int everyPage,Integer hospitalId);

	/** 查询医院院内功能 **/
	List<HospitalServiceList> queryHospitalFunction(int pageIndex, int everyPage,Integer hospitalId);

	/** 获取排序列表 **/
	List<HospitalServiceList> getRankList(Integer hospitalId);

	/**获取医院信息*/
	List<Map<String, Object>> getHospitalInformation(Integer hospitalId);

	/**修改医院服务入口状态**/
	int updateHospitalServiceEntryStat(Integer hospitalId, Integer moduleId, Integer entryStat);

	/**查询该服务信息**/
	HospitalServiceList getHospitalServiceListByModuleId(Integer hospitalId,Integer moduleId);

	/**修改医院服务链接状态*/
	int updateHospitalServiceUrlStat(Integer hospitalId, Integer moduleId, String url, Integer urlStat);

	/**修改医院服务位置排序*/
	int updateHospitalServicePostionOrder(List<Map<String, Object>> listMap);

	/**添加或修改自定义功能**/
	ResultMsg addOrUpdateHospitalService(Integer hospitalId, Integer moduleId, Integer functionGroup, String title,
			String url);

	/**通过名称查询模块**/
	HospitalServiceModule getByTitle(String title);

	/**总记录数**/
	int findCount(Integer hospitalId, Integer functionGroup);

	/**院内功能总记录数**/
	int findCount2(Integer hospitalId);

}
