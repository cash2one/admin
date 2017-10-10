package com.jumper.angel.user.statistics.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.sociality.topic.mapper.TopicMapper;
import com.jumper.angel.user.statistics.mapper.BindHospitalLogMapper;
import com.jumper.angel.user.statistics.service.BindHospitalLogService;
/**
 * 绑定医院统计表 serviceImpl
 * @author gyx
 * @time 2017年3月13日
 */
@Service
public class BindHospitalLogServiceImpl implements BindHospitalLogService {

	@Autowired
	private BindHospitalLogMapper bindHospitalLogMapper;
	
	@Autowired
	private TopicMapper topicMapper;
	
	/**
	 * 统计总用户数
	 */
	@Override
	public int countAllUsers() {
		int totalCount = bindHospitalLogMapper.countAllUsers();
		return totalCount;
	}

	/**
	 * 统计今日注册量
	 */
	@Override
	public int countTodayRegUser(Map<String, Object> map) {
		int todayRegCount = bindHospitalLogMapper.countTodayRegUser(map);
		return todayRegCount;
	}
	
	/**
	 * 今日活跃量
	 */
	@Override
	public int countTodayLoginUser(Map<String, Object> map) {
		int todayLoginCount = bindHospitalLogMapper.countTodayLoginUser(map);
		return todayLoginCount;
	}

	/**
	 * 获取网络医院数量
	 */
	@Override
	public int countMonitorHospital() {
		int monitorHospitalCount = bindHospitalLogMapper.countMonitorHospital();
		return monitorHospitalCount;
	}

	/**
	 * 获取所有医生数量
	 */
	@Override
	public int countHospitalDoctor() {
		int hospitalDoctorCount = bindHospitalLogMapper.countHospitalDoctor();
		return hospitalDoctorCount;
	}

	/**
	 * 免费咨询服务次数
	 */
	@Override
	public int countFreeConsultant() {
		int freeConsultantCount = bindHospitalLogMapper.countFreeConsultant();
		return freeConsultantCount;
	}

	/**
	 * 图文咨询服务次数
	 */
	@Override
	public int countTuWenConsultant() {
		int tuWenConsultantCount = bindHospitalLogMapper.countTuWenConsultant();
		return tuWenConsultantCount;
	}

	/**
	 * 私人医生服务次数
	 */
	@Override
	public int countPrivateDoctor() {
		int privateDoctorCount = bindHospitalLogMapper.countPrivateDoctor();
		return privateDoctorCount;
	}

	/**
	 * 胎心判读服务次数
	 */
	@Override
	public int countAllHeartRead() {
		int heartReadCount = bindHospitalLogMapper.countAllHeartRead();
		return heartReadCount;
	}

	/**
	 * 胎心监护服务次数
	 */
	@Override
	public int countAllHeartMonitor() {
		int heartMonitorCount = bindHospitalLogMapper.countAllHeartMonitor();
		return heartMonitorCount;
	}

	/**
	 * 话题组总数
	 */
	@Override
	public int countTopicGroup() {
		int topicGroupCount = bindHospitalLogMapper.countTopicGroup();
		return topicGroupCount;
	}

	/**
	 * 话题总数
	 */
	@Override
	public int countUserTopic() {
//		int userTopicCount = bindHospitalLogMapper.countUserTopic();
		int userTopicCount = topicMapper.debatepostCount();
		return userTopicCount;
	}

	/**
	 * 交流圈总数
	 */
	@Override
	public int countCommunicationGroup() {
		int communicationGroupCount = bindHospitalLogMapper.countCommunicationGroup();
		return communicationGroupCount;
	}

	/**
	 * 发言用户总数
	 */
	@Override
	public int countSpeakUser() {
		int speakUserCount = bindHospitalLogMapper.countSpeakUser();
		return speakUserCount;
	}

	/**
	 * 宝妈日记篇数
	 */
	@Override
	public int countInteractionMoodDiary() {
		int diaryCount = bindHospitalLogMapper.countInteractionMoodDiary();
		return diaryCount;
	}

	
}
