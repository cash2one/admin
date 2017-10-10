package com.jumper.angel.user.statistics.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jumper.angel.user.statistics.entity.HospitalInfo;

/**
 * 医院信息 mapper
 * @author gyx
 * @time 2017年3月14日
 */
public interface HospitalInfoMapper {

	/**
	 * 获取医院总表统计总数
	 * @param map
	 * @return
	 */
	int findCount(Map<String, Object> map);
	/**
	 * 获取医院总表统计总数
	 * @param map
	 * @return
	 */
	int findCount2(Map<String, Object> map);
	int findCount3(Map<String, Object> map);

	/**
	 * 条件获取医院数据
	 * @param map
	 * @return
	 */
	List<HospitalInfo> findHospitalList(Map<String, Object> map);

	/**
	 * 根据医院名称查询医院信息
	 * @param keywords
	 * @return
	 */
	HospitalInfo findHospitalInfoByName(String keywords);

	/**
	 * 根据医院名称查询医院信息
	 * @param name
	 * @return
	 */
	HospitalInfo getHospitalInfoByName(String name);
	/**
	 * 根据医院名称查询医院列表信息
	 * @param keywords 医院名称关键字
	 * @return
	 */
	List<HospitalInfo> searchHospitalByName(String keywords);
	List<HospitalInfo> selectHospitalByName(String keywords);

	/**根据医院地址和关键字查询医院列表 **/
//	List<Map<String, Object>> getHosptitalInfoList(@Param("statusType")int statusType, 
//			@Param("serviceType")int serviceType,@Param("provinceId") int provinceId, @Param("cityId")int cityId, @Param("keywords")String keywords);

	List<Map<String, Object>> getHosptitalInfoList(@Param("beginIndex")int beginIndex, @Param("everyPage")int everyPage, @Param("statusType")int statusType,
			@Param("serviceType")int serviceType,@Param("provinceId") int provinceId, @Param("cityId")int cityId, @Param("keywords")String keywords);
	/**添加医院**/
	void insertSelective(HospitalInfo hospitalInfo);
	
	HospitalInfo selectByPrimaryKey(Integer id);
	void updateByPrimaryKeySelective(HospitalInfo hospitalInfo);
	void deleteByPrimaryKey(Integer id);
//	HospitalInfo selectByUserName(String userName);
	/**查询医院开通的科室**/
	Integer[] selectMajorByHospitalId(Integer hospitalId);
	List<Integer> selectMonitorSetting(Integer hospitalId);
	/**查询医院数量,0未开通网络医院，1开通网络医院**/
	int findNumber(@Param("status")Integer status);
	/**查询医院数量,开通网络医院**/
	int findNumber1();

}
