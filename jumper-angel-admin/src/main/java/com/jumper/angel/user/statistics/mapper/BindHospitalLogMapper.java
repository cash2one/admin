package com.jumper.angel.user.statistics.mapper;

import java.util.List;
import java.util.Map;

import com.jumper.angel.user.statistics.vo.VOAllHospitalStatisticsInfo;

/**
 * 绑定医院统计 mapper
 * @author gyx
 * @time 2017年3月13日
 */
public interface BindHospitalLogMapper {

	/**
	 * 获取总用户数
	 * @return
	 */
	int countAllUsers();

	/**
	 * 统计今日注册量
	 * @return
	 */
	int countTodayRegUser(Map<String, Object> map);
	
	/**
	 * 统计今日活跃量
	 * @param map
	 * @return
	 */
	int countTodayLoginUser(Map<String, Object> map);

	/**
	 * 获取网络医院数量
	 */
	int countMonitorHospital();

	/**
	 * 获取所有医生数量
	 */
	int countHospitalDoctor();

	/**
	 * 免费咨询服务次数
	 */
	int countFreeConsultant();

	/**
	 * 图文咨询服务次数
	 */
	int countTuWenConsultant();

	/**
	 * 私人医生服务次数
	 */
	int countPrivateDoctor();
	
	/**
	 * 获取所有胎心判读服务次数
	 * @return
	 */
	int countAllHeartRead();

	/**
	 * 获取所有胎心监护服务次数
	 * @return
	 */
	int countAllHeartMonitor();

	/**
	 * 获取关联该医院的用户数
	 * @param id 医院id
	 * @return
	 */
	int countCommonHospitalUser(Integer id);

	/**
	 * 获取某一时间段内该医院的新增用户数（首次关联）
	 * @param map
	 * @return
	 */
	int countfirstBindUser(Map<String, Object> map);

	/**
	 * 获取体重营养门诊人数
	 * @param map
	 * @return
	 */
	int countWeightOutPatient(Map<String, Object> map);

	/**
	 * 获取医院问诊服务次数
	 * @param map
	 * @return
	 */
	int countHospConsultant(Map<String, Object> map);

	/**
	 * 获取设备租赁服务次数
	 * @param map
	 * @return
	 */
	int countLeaseOrder(Map<String, Object> map);

	/**
	 * 获取胎心监护服务次数
	 * @param map
	 * @return
	 */
	int countHeartMonitor(Map<String, Object> map);

	/**
	 * 获取总计关联行为次数
	 * @param map
	 * @return
	 */
	int countTotalBindUser(Map<String, Object> map);
	
	/**
	 * 查询医院最小绑定时间
	 * @param id 医院id
	 * @return
	 */
	String findMinBindingTime(Integer id);

	/**
	 * 话题组总数
	 * @return
	 */
	int countTopicGroup();

	/**
	 * 话题总数
	 * @return
	 */
	int countUserTopic();

	/**
	 * 交流圈总数
	 * @return
	 */
	int countCommunicationGroup();

	/**
	 * 发言用户总数
	 * @return
	 */
	int countSpeakUser();

	/**
	 * 宝妈日记篇数
	 * @return
	 */
	int countInteractionMoodDiary();

	List<VOAllHospitalStatisticsInfo> findAllHospitalList(
			Map<String, Object> map);

	/**
	 * 统计老版本APP首次绑定医院的用户量
	 * @param map
	 * @return
	 */
	int countOldAppFirstBind(Map<String, Object> map);


}
