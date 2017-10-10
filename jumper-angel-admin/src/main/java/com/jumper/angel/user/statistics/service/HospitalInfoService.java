package com.jumper.angel.user.statistics.service;

import java.util.List;
import java.util.Map;

import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.hospital.hospital.vo.HospitalInfoManageVo;
import com.jumper.angel.hospital.hospital.vo.HospitalInfoVo;
import com.jumper.angel.user.statistics.entity.HospitalInfo;
import com.jumper.angel.user.statistics.vo.VOAllHospitalStatisticsInfo;
import com.jumper.angel.user.statistics.vo.VOCityInfo;
import com.jumper.angel.user.statistics.vo.VOProvinceInfo;
import com.jumper.angel.utils.ResultMsg;

/**
 * 医院信息Service
 * @author gyx
 * @time 2017年3月14日
 */
public interface HospitalInfoService {

	/**
	 * 获取医院总表 展示数据总数
	 * @param keywords 医院名称
	 * @param provinceId 省份
	 * @param cityId 城市
	 * @return
	 */
	int findCount(String keywords, int provinceId, int cityId);
	/**
	 * 获取医院总表 展示数据总数
	 * @param keywords 医院名称
	 * @param provinceId 省份
	 * @param cityId 城市
	 * @return
	 */
	int findCount2(String keywords, int provinceId, int cityId);
	/**
	 * 获取医院总表 展示数据总数
	 * @param keywords 医院名称
	 * @param provinceId 省份
	 * @param cityId 城市
	 * @param serviceType 服务类型
	 * @return
	 */
	int findCount3(String keywords, Integer provinceId, Integer cityId, Integer serviceType);
	int findCount4(String keywords, Integer provinceId, Integer cityId, Integer serviceType);

	/**
	 * 通过省份获取城市列表
	 * @param id 省份id
	 * @return
	 */
	List<VOCityInfo> getCityByProvince(int id);

	/**
	 * 获取医院总表统计数据
	 * @param beginIndex 分页索引
	 * @param everyPage 每页记录数
	 * @param keywords 医院名称
	 * @param province 省份
	 * @param city 城市
	 * @param pointTime 指定日期
	 * @return
	 */
	List<VOAllHospitalStatisticsInfo> findAllHospitalStatisticsList(
			int beginIndex, int everyPage, String keywords, int province,
			int city, String pointTime);

	/**
	 * 根据医院名称查询医院信息
	 * @param keywords
	 * @return
	 */
	HospitalInfo findHospitalInfoByName(String keywords);

	/**
	 * 获取某一医院的所有统计数据
	 * @param id 医院id
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	Map<String, Object> countHospitalStatistics(Integer id, String startTime,
			String endTime);

	/**
	 * 根据医院名称查询医院列表信息
	 * @param keywords 医院名称关键字
	 * @return
	 */
	List<HospitalInfo> searchHospitalByName(String keywords);

	/**根据关键字查询医院列表**/
	List<HospitalInfo> getHosptitalList(String keywords);

	/**根据医院地址和关键字查询医院列表 **/
//	List<Map<String, Object>> getHosptitalInfoList(int statusType, int serviceType, int provinceId, int cityId, String keywords);

	List<HospitalInfoVo> getHosptitalInfoList(int beginIndex, int everyPage, int statusType, Integer serviceType,
			Integer provinceId, Integer cityId, String keywords);
	
	/**获取省份列表**/
	List<VOProvinceInfo> getProvince();
	
	/**添加医院**/
	ResultMsg addHospitalInfo(HospitalInfoManageVo vo);
	/**修改医院**/
	ResultMsg updateHospitalInfo(HospitalInfoManageVo vo);
	/**删除医院**/
	ResultMsg deleteHospitalInfo(Integer id);
	HospitalInfo selectByPrimaryKey(Integer id);
//	HospitalInfo selectByUserName(String userName);
	HospitalInfoManageVo selectHospitalInfoManageVo(Integer hospitalId);
	String[] getService(Integer hospitalId);
//	/**账号管理**/
//	ResultMsg accountManagement(Integer hospitalId, String userName, String password, Integer status);
	/**开通线上支付**/
	ResultMsg isPayment(Integer hospitalId, Integer status, CrmAdmin crmAdmin);
	/**获取医院列表（高危管理和母子手册）**/
	List<HospitalInfoVo> getHosptitalInfoList2(int beginIndex, int everyPage, int statusType, Integer serviceType,
			Integer provinceId, Integer cityId, String keywords);
	/**查询医院数量,0未开通网络医院，1开通网络医院**/
	int findNumber(Integer status);
	/**查询医院数量,开通网络医院**/
	int findNumber1();

}
