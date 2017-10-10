package com.jumper.angel.hospital.doctor.controller;

import java.util.ArrayList;
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
import com.jumper.angel.hospital.doctor.entity.HospitalDoctorInfo;
import com.jumper.angel.hospital.doctor.entity.ImMsgPo;
import com.jumper.angel.hospital.doctor.entity.UserInfoBean;
import com.jumper.angel.hospital.doctor.mapper.HospitalDoctorInfoMapper;
import com.jumper.angel.hospital.doctor.mapper.UserInfoBeanMapper;
import com.jumper.angel.hospital.doctor.service.IHospitalConsultantInfoService;
import com.jumper.angel.hospital.doctor.service.IImMsgPoService;
import com.jumper.angel.utils.BusCodeUtil;
import com.jumper.angel.utils.ConfigProUtils;
import com.jumper.angel.utils.HttpPost;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.PhoneUtil;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.ReturnMsg;
import com.jumper.angel.utils.Status;
import com.jumper.angel.utils.TimeUtils;
import com.jumper.angel.utils.json.JsonUtils;
import com.wordnik.swagger.annotations.Api;

import net.sf.json.JSONObject;
/**
 * 我的解答
 * */
@Controller
@RequestMapping("/hospital/doctor")
@Api(value="/hospital/doctor", description="我的解答")
public class MyAnswerContorller {
	private final static Logger logger = Logger.getLogger(HospitalConsultantInfo.class);
	@Autowired 
	private IHospitalConsultantInfoService hospitalConsultantInfoService;
//	@Autowired
//	private IImMessagePoService imMessagePoService;
	@Autowired
	private IImMsgPoService imMsgPoService;
	@Autowired
	private UserInfoBeanMapper userInfoBeanMapper;
	@Autowired
	private HospitalDoctorInfoMapper doctorInfoMapper;

	/**
	 * 我的解答（回复中）
	 */
	@RequestMapping("/myAnswering")
	public ModelAndView myAnswering(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/hospital/doctor/myAnswering");
		return mv;
	}
	
	/**我的解答,回复中*/
	@RequestMapping(method = RequestMethod.GET,value="/myAnswerings")
	@ResponseBody
	public ResultMsg myAnswering(@RequestParam("pageIndex") int pageIndex, @RequestParam("everyPage") int everyPage,
			@RequestParam("chatUrl")String chatUrl, @RequestParam("first") boolean first, 
			@RequestParam("url")String url,	@RequestParam("keywords") String keywords,HttpServletRequest request){
		try{
			//实例化Page对象
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示的记录数
			page.setEveryPage(everyPage);
//			if(!"".equals(keywords)&&null!=keywords){
//				keywords = new String(keywords.getBytes("iso-8859-1"), "utf-8"); 
//			}
			//获取当前登陆对象的ID
			CrmAdmin crmAdmin = (CrmAdmin) request.getSession().getAttribute("user");
			if(crmAdmin == null){
				logger.info("当前未登录，请登录！");
				throw new Exception("当前未登录，请登录！");
			}
			Integer currentId = crmAdmin.getId();
			//获取当前用户的chatIDCRM_OPENID
			logger.info("++++++++++++++++++++获取我的解答咨询中当前用户的chatID开始+++id="+currentId+"+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
			String chatId = addChatId(chatUrl, String.valueOf(currentId), ConfigProUtils.get("TSYY_APPID"), ConfigProUtils.get("CRM_OPENID")+currentId);
			logger.info("++++++++++++++++++++获取我的解答咨询中当前用户的chatID结束+++++chatId:"+chatId+"+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
			//总记录数
			logger.info("++++++++++++++++++++获取我的解答咨询中总记录数开始+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
			int count = hospitalConsultantInfoService.findMyAnsweringCount(keywords,currentId);
			logger.info("++++++++++++++++++++获取我的解答咨询中总记录数结束++count="+count+"+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
			//分页工具类，计算分页数
			page = PageUtil.createPage(page, count);
			if(first){
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			logger.info("++++++++++++++++++++获取获取我的解答最新排序开始++++URL:"+url+"+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
			List<Map<String, Object>> selLatestQuestion = selLatestQuestion(url, chatId, "10154");
			if (selLatestQuestion==null) {
				logger.info("++++++++++++++++++++获取获取我的解答最新排序失败+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
				return new ResultMsg(Status.SUCCESS, "获取获取我的解答最新排序失败！");
			}
			logger.info("++++++++++++++++++++获取获取我的解答最新排序结束selLatestQuestion.size="+selLatestQuestion.size()+"+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
			logger.info("++++++++++++++++++++获取获取我的解答开始+++++++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
			//通过当前用户的id查询我的解答，回复中
			List<Map<String, Object>> listing = hospitalConsultantInfoService.findMyAnswering(page.getCurrentPage(), page.getEveryPage(),keywords,currentId);
			logger.info("++++++++++++++++++++获取获取我的解答结束+++++++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
			List<Map<String, Object>> list = new ArrayList<>();
//			for (ImMessagePo imMessagePo : selLatestQuestion) {
//				for (Map<String, Object> map : listing) {
//					
//					String id = map.get("id").toString();
//					String consultantId = imMessagePo.getConsultantId();
//					if (id==consultantId||id.equals(consultantId)) {
//						map.put("commonPath", ConfigProUtils.get("COMMON_PROJECT_CHAT_PATH"));
//						list.add(map);
//					}
//				}
//			}
//			Integer length = count - pageIndex * everyPage > everyPage ? pageIndex * everyPage : count - pageIndex * everyPage + everyPage;
//			if (selLatestQuestion.size()<length) {
//				length = selLatestQuestion.size();
//			}
//			logger.info("++++++++++++++++++++length="+length);
			
			int countNum = 0;
			a:for (int index = ( pageIndex - 1 ) * everyPage; index < selLatestQuestion.size(); index++) {
				for (Map<String, Object> map : listing) {
					Map<String, Object> selLatestQuestionMap = selLatestQuestion.get(index);
					if (selLatestQuestionMap != null){
						String consultantId = selLatestQuestionMap.get("consultantId").toString(); 
						String id = map.get("id").toString();
						if (id.equals(consultantId)) {
							String status = map.get("status").toString();
							String statusIm = selLatestQuestionMap.get("yesOrNo").toString(); 
							logger.info("++++++++++++++++++++status="+status);
							logger.info("++++++++++++++++++++statusIm="+statusIm);
							if ("2".equals(status)&&"1".equals(statusIm)) {
								//修改回复状态
								hospitalConsultantInfoService.updateStatusById(id);
							}
							map.put("commonPath", ConfigProUtils.get("COMMON_PROJECT_CHAT_PATH"));
							list.add(map);
							countNum++;
							if (countNum>=15) {
								break a;
							}
						}
					}
				}
			}
			if(list != null && list.size() > 0){
				return new ResultMsg(Status.SUCCESS, "获取我的解答列表成功！", list);
			}else{
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
//			model.addAttribute("commonPath", ConfigProUtils.get("COMMON_PROJECT_PATH"));
		} catch (Exception e) {
			logger.info("获取我的解答列表失败！");
			e.printStackTrace();
			return new ResultMsg(Status.FAILED, "获取我的解答列表失败！");
		}
	}
	
	/**
	 * 我的解答（已结束）
	 */
	@RequestMapping("/myAnswerend")
	public ModelAndView myAnswerend(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/hospital/doctor/myAnswerend");
		return mv;
	}
	
	/**我的解答，已结束*/
	@RequestMapping(method = RequestMethod.GET,value="/myAnswerends")
	@ResponseBody
	public ResultMsg myAnswerend(@RequestParam("pageIndex") int pageIndex, @RequestParam("everyPage") int everyPage, 
			@RequestParam("first") boolean first, @RequestParam("keywords") String keywords,HttpServletRequest request){
		try{
			//实例化Page对象
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示的记录数
			page.setEveryPage(everyPage);
//			if(!"".equals(keywords)&&null!=keywords){
//				keywords = new String(keywords.getBytes("iso-8859-1"), "utf-8"); 
//			}
			//获取当前登陆对象的ID
			CrmAdmin crmAdmin = (CrmAdmin) request.getSession().getAttribute("user");
			if(crmAdmin == null){
				logger.info("当前未登录，请登录！");
				throw new Exception("当前未登录，请登录！");
			}
			Integer currentId = crmAdmin.getId();
			//总记录数
			int count = hospitalConsultantInfoService.findMyAnswerendCount(keywords,currentId);
			page = PageUtil.createPage(page, count);
			if(first){
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			//通过当前用户的id查询我的解答，已结束
			List<Map<String, Object>> listend = hospitalConsultantInfoService.findMyAnswerend(page.getCurrentPage(), page.getEveryPage(),keywords,currentId);
			if(listend != null && listend.size() > 0){
				return new ResultMsg(Status.SUCCESS, "获取我的解答列表成功！", listend);
			}else{
				return new ResultMsg(Status.NODATA, "暂无数据！",new ArrayList<>());
			}
		} catch (Exception e) {
			logger.info("获取我的解答列表失败！");
			e.printStackTrace();
			return new ResultMsg(Status.FAILED, "获取我的解答列表失败！",new ArrayList<>());
		}
	}
	/**
	 * 查询聊天记录
	 * @author huangzg 2016年12月2日 下午7:48:27  
	 * @param busCode 业务代码
	 * @param sender 发送者IM帐号          yf_userid
	 * @param recevrer 接收者IM帐号        ys_doctorid
	 * @param startTime 查询开始时间
	 * @param endTime 查询结束时间
	 * @param pageNo 页码
	 * @param pageSize 每页条数
	 * @return        
	 * @throws
	 */
	@RequestMapping("showComments")
	@ResponseBody
	public ResultMsg selMessage(Page page,Integer serviceType, Integer userId,Integer consultantId,String url,Integer isAdmin,Integer doctor_id,
			String chatUrl,String messageUrl,HttpServletRequest request){
		try {
			if (serviceType==null) {
				serviceType=-1;
			}
			String busCode = BusCodeUtil.getBusCode(serviceType);
			UserInfoBean userInfo = userInfoBeanMapper.selectByPrimaryKey(userId);
			if(userInfo == null){
				logger.info("用户不存在！");
				throw new Exception("用户不存在!");
			}
			String userName = "";
			if (null!=userInfo.getNickName()&&!"".equals(userInfo.getNickName())) {
				userName = userInfo.getNickName();
			}else {
				userName = PhoneUtil.format4(userInfo.getMobile());
			}
			//获取发送者chatID
			logger.info("++++++++++++++++++++获取获取发送者chatID开始+++++chatUrl："+chatUrl+"+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
			String sender = addChatId(chatUrl, String.valueOf(userId),ConfigProUtils.get("TSYY_APPID"), ConfigProUtils.get("USER_OPENID")+userId);
			logger.info("++++++++++++++++++++获取获取发送者chatID结束+++++chatId:"+sender+"+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
			
			String recevrer ="";
			String docName ="";
			if (isAdmin==0) {
				HospitalDoctorInfo doctorInfo = doctorInfoMapper.selectByPrimaryKey(doctor_id);
				docName = doctorInfo.getName();
				logger.info("++++++++++++++++++++获取接收者chatID开始+++++chatUrl："+chatUrl+"+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
				recevrer = addChatId(chatUrl, String.valueOf(doctor_id),ConfigProUtils.get("TSYY_APPID"), ConfigProUtils.get("DOCTOR_OPENID")+doctor_id);
				logger.info("++++++++++++++++++++获取接收者chatID结束+++++chatId:"+recevrer+"+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
			}else {
				//获取接收者chatID
				logger.info("++++++++++++++++++++获取接收者chatID开始+++++chatUrl："+chatUrl+"+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
				recevrer = addChatId(chatUrl, String.valueOf(doctor_id),ConfigProUtils.get("TSYY_APPID"), ConfigProUtils.get("CRM_OPENID")+doctor_id);
				logger.info("++++++++++++++++++++获取接收者chatID结束+++++chatId:"+recevrer+"+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
			}
			logger.info("++++++++++++++++++++获取聊天信息开始++++consultantId="+consultantId+"++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
			List<ImMsgPo> messages = imMsgPoService.findMsg(page, busCode, sender, recevrer, null, null,consultantId);
//			List<ImMessagePo> messages = imMessagePoService.findMessage(page, busCode, sender, recevrer, null, null,consultantId);
			logger.info("++++++++++++++++++++获取聊天信息结束+++++messages:"+messages+"+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
			for (ImMsgPo imMsgPo : messages) {
				
				if (imMsgPo.getMsgType() == 14){
					JSONObject jsonObject = JsonUtils.toJSONObject(imMsgPo.getMsgContent());
//					List<Map<String, Object>> list = JsonUtils.toList(imMsgPo.getMsgContent());
//					Map<String, Object> dataMap = list.get(0);
//					String imgUrl = dataMap.get("original").toString();
//					Integer height = (Integer)dataMap.get("height");
//					Integer width = (Integer)dataMap.get("width");
//					imMsgPo.setMsgContent(imgUrl);
//					imMsgPo.setHeight(height);
//					imMsgPo.setWidth(width);
					@SuppressWarnings("unchecked")
					Map<String, Object> dataMap = jsonObject.getJSONObject("data");
					String imgUrl = dataMap.get("content").toString();
//					Integer height = (Integer)dataMap.get("height");
//					Integer width = (Integer)dataMap.get("width");
					imMsgPo.setMsgContent(imgUrl);
					imMsgPo.setSize(dataMap.get("size").toString());
//					imMsgPo.setHeight(height);
//					imMsgPo.setWidth(width);
				} 
				if (imMsgPo.getMsgType() == 2){
//					JSONObject jsonObject = JsonUtils.toJSONObject(imMsgPo.getMsgContent());
					List<Map<String, Object>> list = JsonUtils.toList(imMsgPo.getMsgContent());
					Map<String, Object> dataMap = list.get(0);
					String imgUrl = dataMap.get("original").toString();
					Integer height = (Integer)dataMap.get("height");
					Integer width = (Integer)dataMap.get("width");
					imMsgPo.setMsgContent(imgUrl);
					imMsgPo.setHeight(height);
					imMsgPo.setWidth(width);
				} 
				if (imMsgPo.getSendChatId().contains(ConfigProUtils.get("DOCTOR_OPENID"))) {
					imMsgPo.setSendChatName(docName);
				}else if (imMsgPo.getSendChatId().contains(ConfigProUtils.get("USER_OPENID"))) {
					imMsgPo.setSendChatName(userName);
				}else {
					imMsgPo.setSendChatName("【天使医生】");
				}
				imMsgPo.setFilePath(ConfigProUtils.get("file_path"));
			}
			return new ResultMsg(ReturnMsg.SUCCESS, null, messages);
		} catch (Exception e) {
			logger.info("查看咨询详情失败！");
			e.printStackTrace();
			return new ResultMsg();
		}
	}
	/**获取chatID*/
	private String addChatId(String url,String userId,String appid,String openid) {
		String chatId = "";
		try {
//			String path = "http://v4-huidu.tsys91.com:8081/common" + url;
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
	/**获取我的解答最新排序*/
	private List<Map<String, Object>> selLatestQuestion(String url,String chatId,String busCode) {
		try {
//			String path = "http://v4-huidu.tsys91.com:8081/common" + url;
			String path = ConfigProUtils.get("COMMON_PROJECT_PATH") + url;
			String postUrl = path+"?busCode="+busCode+"&recevrer="+chatId;
			logger.info("++++++++++++++++++++获取我的解答最新排序+++++postUrl:"+postUrl+"+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
			String val = HttpPost.doPost(postUrl, "");
			HashMap<String, Object> dataMap = JsonUtils.toHashMap(val);
//			List<Object> idList = new ArrayList<Object>();
			if (dataMap!=null) {
				@SuppressWarnings("unchecked")
				List<Map<String, Object>> list = (List<Map<String, Object>>) dataMap.get("data");
//				Object obj = dataMap.get("data");
				logger.info("++++++++++++++++++++获取我的解答最新排序结果+++++list.size():"+list.size()+"+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
				logger.info("++++++++++++++++++++获取我的解答最新排序结果+++++list:"+list+"+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
//				if (obj != null){
//			        JSONArray jsonArray = JSONArray.fromObject(obj);
//			        Iterator<?> it = jsonArray.iterator();
//			        while (it.hasNext()){
//			        	idList.add(it.next());
//			        }
//				}
				return list;
			}
			return null;
		} catch (Exception e) {
			logger.info("获取chatID失败！");
			e.printStackTrace();
			return null;
		}
	}
//	/**获取我的解答最新排序*/
//	private List<Object> selLatestQuestion(String url,String chatId,String busCode) {
//		try {
//			String path = ConfigProUtils.get("COMMON_PROJECT_PATH") + url;
//			String postUrl = path+"?busCode="+busCode+"&recevrer="+chatId;
//			logger.info("++++++++++++++++++++获取我的解答最新排序+++++postUrl:"+postUrl+"+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
//			String val = HttpPost.doPost(postUrl, "");
//			HashMap<String, Object> dataMap = JsonUtils.toHashMap(val);
//			List<Object> idList = new ArrayList<Object>();
//			if (dataMap!=null) {
//				Object obj = dataMap.get("data");
//				if (obj != null){
//					JSONArray jsonArray = JSONArray.fromObject(obj);
//					Iterator<?> it = jsonArray.iterator();
//					while (it.hasNext()){
//						idList.add(it.next());
//					}
//				}
//				return idList;
//			}
//			return null;
//		} catch (Exception e) {
//			logger.info("获取chatID失败！");
//			e.printStackTrace();
//			return null;
//		}
//	}
	/**关闭咨询*/
	@RequestMapping("deletConsult")
	@ResponseBody
	public ResultMsg deletConsult(Integer consultId){
		try {
			if (consultId==null) {
				logger.info("咨询ID不能空！");
				throw new Exception("咨询ID不能空！");
			}
			int count = hospitalConsultantInfoService.deleteConsult(consultId);;
			if (count==1) {
				return new ResultMsg(ReturnMsg.SUCCESS,"关闭咨询成功！");
			}
			return new ResultMsg(ReturnMsg.FAIL,"关闭咨询失败！");
		} catch (Exception e) {
			logger.info("关闭咨询详情失败！");
			e.printStackTrace();
			return new ResultMsg(ReturnMsg.FAIL,"关闭咨询失败！");
		}
	}
}
