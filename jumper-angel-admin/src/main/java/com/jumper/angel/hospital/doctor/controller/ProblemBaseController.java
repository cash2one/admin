package com.jumper.angel.hospital.doctor.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.hospital.doctor.entity.HospitalConsultantInfo;
import com.jumper.angel.hospital.doctor.entity.UserInfoBean;
import com.jumper.angel.hospital.doctor.mapper.UserInfoBeanMapper;
import com.jumper.angel.hospital.doctor.service.IHospitalConsultantInfoService;
import com.jumper.angel.utils.BusCodeUtil;
import com.jumper.angel.utils.ConfigProUtils;
import com.jumper.angel.utils.HttpPost;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;
import com.jumper.angel.utils.TimeUtils;
import com.jumper.angel.utils.json.JsonUtils;
import com.wordnik.swagger.annotations.Api;

/**问题库controller*/
@Controller
@RequestMapping("/hospital/doctor")
@Api(value="/hospital/doctor", description="问题库")
public class ProblemBaseController {
	private final static Logger logger = Logger.getLogger(HospitalConsultantInfo.class);
	@Autowired 
	private IHospitalConsultantInfoService hospitalConsultantInfoService;
	@Autowired
	private UserInfoBeanMapper userInfoBeanMapper;
	/**
	 * 问题库
	* hospitalConsultantQuery()
	 */
	@RequestMapping(value="/problemBase")
	public ModelAndView topicIndex(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/hospital/doctor/problemBase");
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/problemBases")
	@ResponseBody
	public ResultMsg problemBase(@RequestParam("pageIndex") int pageIndex, @RequestParam("everyPage") int everyPage, 
			@RequestParam("first") boolean first, @RequestParam("keywords") String keywords) {
		try{
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示记录数
			page.setEveryPage(everyPage);
			//查询总记录数
//			keywords = new String(keywords.getBytes("ISO-8859-1"),"UTF-8");
			//总记录数
			int count = hospitalConsultantInfoService.findProblemBaseCount(keywords);
			//计算分页
			page = PageUtil.createPage(page, count);
			if(first){
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			//查找所有问题库问题并分页
			List<Map<String, Object>> classList = hospitalConsultantInfoService.findAllProblemBase(page.getCurrentPage(), page.getEveryPage(),keywords);
			if(classList != null && classList.size() > 0){
				return new ResultMsg(Status.SUCCESS, "获取问题库数据成功！", classList);
			}else{
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.info("findQuestionClassList():"+e.getMessage());
			return new ResultMsg(Status.FAILED, "获取问题库数据失败！");
		}
	}
	
	/**领取问题*/
	@RequestMapping(method = RequestMethod.GET,value="/getProblem")
	public String getProblem(Integer consultantId,Integer userId,Integer serviceType,
			String chatUrl,String updateUrl,HttpServletRequest request){
		try {
			synchronized(this) {
				CrmAdmin crmAdmin = (CrmAdmin) request.getSession().getAttribute("user");
				if(crmAdmin == null){
					logger.info("当前未登录，请登录！");
					throw new Exception("当前未登录，请登录！");
				}
				Integer currentId = crmAdmin.getId();
				if (serviceType == null) {
					serviceType = -1;
				}
				String busCode = BusCodeUtil.getBusCode(serviceType);
				HospitalConsultantInfo consultantInfo = hospitalConsultantInfoService.findConsultantInfoById(consultantId);
				if (consultantInfo.getStatus()==2) {
					logger.info("问题已领取！");
					return "hospital/doctor/problemBase";
				}
				UserInfoBean userInfo = userInfoBeanMapper.selectByPrimaryKey(userId);
				if(userInfo == null){
					logger.info("用户不存在！");
					throw new Exception("用户不存在!");
				}
				logger.info("++++++++++++++++++++开始领取问题+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
				//获取发送者chatID
				logger.info("++++++++++++++++++++获取发送者chatID+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
				String sender = addChatId(chatUrl, String.valueOf(userId),ConfigProUtils.get("TSYY_APPID"), ConfigProUtils.get("USER_OPENID")+userId);
				if (sender==null||"".equals(sender)) {
					logger.info("发送者不能为空！");
					throw new Exception("发送者不能为空！");
				}
				if (busCode==null||"".equals(busCode)) {
					logger.info("业务代码不能为空！");
					throw new Exception("业务代码不能为空！");
				}
				//获取接收者chatID
				logger.info("++++++++++++++++++++获取接收者chatID+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
				String recevrer = addChatId(chatUrl, String.valueOf(currentId),ConfigProUtils.get("TSYY_APPID"), ConfigProUtils.get("CRM_OPENID")+currentId);
				if (recevrer==null||"".equals(recevrer)) {
					logger.info("接收者不能为空！");
					throw new Exception("接收者不能为空！");
				}
//				//发送消息
//				logger.info("++++++++++++++++++++发送消息+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
//				updateQuestion(busCode, updateUrl, sender, recevrer, String.valueOf(consultantId));
//				logger.info("++++++++++++++++++++发送成功+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
				//通过id查询到对象,当前用户获取该问题
				logger.info("++++++++++++++++++++开始改变状态+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
				HospitalConsultantInfo problem = hospitalConsultantInfoService.getProblem(consultantId,currentId,busCode);
				logger.info("++++++++++++++++++++问题领取完成+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
				if (problem==null) {
					logger.info("问题领取失败！");
					throw new Exception("问题领取失败!");
				}
				return "hospital/doctor/problemBase";
			}
		} catch (Exception e) {
			logger.info("领取问题失败！");
			e.printStackTrace();
			return "hospital/doctor/problemBase";
		}
	}
	
//	/**修改用户提出问题*/
//	private void updateQuestion(String busCode,String url,String sender,String recevrer,String consultantId) {
//		try {
//			
//			String path = ConfigProUtils.get("COMMON_PROJECT_PATH") + url;
//			String postUrl = path +"?busCode="+busCode+"&sender="+sender+"&recevrer="+recevrer+"&consultantId="+consultantId;
//			HttpPost.doPost(postUrl, "");			
//		} catch (Exception e) {
//			logger.info("发送问题失败！");
//			e.printStackTrace();
//		}
//	}
	
	/**获取chatID*/
	private String addChatId(String url,String userId,String appid,String openid) {
		String chatId = "";
		try {
			String path = ConfigProUtils.get("COMMON_PROJECT_PATH") + url;
			String postUrl = path+"?userId="+userId+"&appid="+appid+"&openid="+openid;
			String val = HttpPost.doPost(postUrl, "");
			HashMap<String, Object> param = JsonUtils.toHashMap(val);
			String msg =  param.get("msg").toString();
			if ("1".equals(msg)){
				HashMap<String, Object> dataMap = JsonUtils.toHashMap(param.get("data"));
				chatId = dataMap.get("chatId").toString();
			}
		} catch (Exception e) {
			logger.info("获取chatID失败！");
			e.printStackTrace();
		}
		return chatId;
	}
}
