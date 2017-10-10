package com.jumper.angel.user.statistics.service;

import java.util.Map;

public interface BindHospitalLogService {

	/**
	 * 获取总用户数
	 * @return
	 */
	int countAllUsers();

	/**
	 * 获取今日注册量
	 * @param map
	 * @return
	 */
	int countTodayRegUser(Map<String, Object> map);
	
	/**
	 * 今日活跃量
	 * @param map
	 * @return
	 */
	int countTodayLoginUser(Map<String, Object> map);

	/**
	 * 获取开通网络医院的医院数量
	 * @return
	 */
	int countMonitorHospital();

	/**
	 * 获取医院所有医生数量
	 * @return
	 */
	int countHospitalDoctor();

	/**
	 * 获取免费咨询服务数
	 * @return
	 */
	int countFreeConsultant();

	/**
	 * 获取图文咨询服务数
	 * @return
	 */
	int countTuWenConsultant();

	/**
	 * 获取私人医生服务数
	 * @return
	 */
	int countPrivateDoctor();

	/**
	 * 胎心判读服务次数
	 * @return
	 */
	int countAllHeartRead();

	/**
	 * 胎心监护服务次数
	 * @return
	 */
	int countAllHeartMonitor();

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

	

}
