package com.jumper.angel.user.statistics.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jumper.angel.user.statistics.service.BindHospitalLogService;
import com.jumper.angel.utils.TimeUtils;

/**
 * 用户统计 控制器
 * @author gyx
 * @time 2017年3月13日
 */
@Controller
@RequestMapping("userStatistics")
public class UserStatisticsController {
	private final static Logger logger = Logger.getLogger(UserStatisticsController.class);
	
	@Autowired
	private BindHospitalLogService bindHospitalLogService;
	
	/**
	 * 数据概览
	 * @return
	 */
	@RequestMapping("forwardUserStatistics")
	public ModelAndView forwardUserStatistics(){
		ModelAndView mv = new ModelAndView();
		//总用户数
		int totalCount = bindHospitalLogService.countAllUsers();
		//今日注册量
		Map<String, Object> map = new HashMap<String, Object>();
		String day = TimeUtils.converStringDate(new Date(), "yyyy-MM-dd HH:mm:ss").substring(0,10);
		String beginDate = day + " 00:00:00";
		String endDate = day + " 23:59:59";
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		int todayRegCount = bindHospitalLogService.countTodayRegUser(map);
		//今日活跃量
		int todayLoginCount = bindHospitalLogService.countTodayLoginUser(map);
		//开通网络医院数量
		int monitorHospitalCount = bindHospitalLogService.countMonitorHospital();
		//医生数量
		int doctorCount = bindHospitalLogService.countHospitalDoctor();
		//免费咨询
		int freeConsultCount = bindHospitalLogService.countFreeConsultant();
		//图文咨询
		int tuwenConsultCount = bindHospitalLogService.countTuWenConsultant();
		//私人医生
		int privateDoctorCount = bindHospitalLogService.countPrivateDoctor();
		//胎心判读
		int heartReadCount = bindHospitalLogService.countAllHeartRead();
		//胎心实时监护
		int heartMonitorCount = bindHospitalLogService.countAllHeartMonitor();
		//话题组总数
		int topicGroupCount = bindHospitalLogService.countTopicGroup();
		//话题总数
		int userTopicCount = bindHospitalLogService.countUserTopic();
		//交流圈总数
		int communicationGroupCount = bindHospitalLogService.countCommunicationGroup();
		//发言用户总数
		int speakUserCount = bindHospitalLogService.countSpeakUser();
		//宝妈日记篇数
		int diaryCount = bindHospitalLogService.countInteractionMoodDiary();
		mv.addObject("totalCount", totalCount);
		mv.addObject("todayRegCount", todayRegCount);
		mv.addObject("todayLoginCount", todayLoginCount);
		mv.addObject("monitorHospitalCount", monitorHospitalCount);
		mv.addObject("doctorCount", doctorCount);
		mv.addObject("freeConsultCount", freeConsultCount);
		mv.addObject("tuwenConsultCount", tuwenConsultCount);
		mv.addObject("privateDoctorCount", privateDoctorCount);
		mv.addObject("heartReadCount", heartReadCount);
		mv.addObject("heartMonitorCount", heartMonitorCount);
		mv.addObject("topicGroupCount", topicGroupCount);
		mv.addObject("userTopicCount", userTopicCount);
		mv.addObject("communicationGroupCount", communicationGroupCount);
		mv.addObject("speakUserCount", speakUserCount);
		mv.addObject("diaryCount", diaryCount);
		
		mv.setViewName("statistics/userStatistics");
		return mv;
	}
	
	
	
}
