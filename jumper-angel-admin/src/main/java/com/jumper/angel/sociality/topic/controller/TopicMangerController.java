/** 
 * 话题组管理
 * Project Name:jumper-angel-admin 
 * File Name:TopicMangerController.java 
 * Package Name:com.jumper.angel.sociality.topic.controller 
 * Date:2016年12月27日上午10:47:45 
 * Copyright (c) 2016, yangsheng@angeldoctor All Rights Reserved. 
 * 
*/  
package com.jumper.angel.sociality.topic.controller;
import java.io.File;
import java.io.IOException;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.sociality.topic.model.po.TopicPO;
import com.jumper.angel.sociality.topic.model.po.UserTopicPO;
import com.jumper.angel.sociality.topic.model.vo.TopicVO;
import com.jumper.angel.sociality.topic.service.TopicManagerService;
import com.jumper.angel.utils.ConfigProUtils;
import com.jumper.angel.utils.NetUtil;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.Result;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;
import com.jumper.angel.utils.StringUtil;


@Controller
@RequestMapping("/sociality/topicManager")
public class TopicMangerController {
	
	private final static Logger logger = Logger.getLogger(TopicMangerController.class);
	
	@Autowired
	private TopicManagerService topicManagerService;
	/**
	 * 跳转到话题组首页，话题管理
	 * @author yangsheng@angeldoctor 
	 * @return
	 */
	@RequestMapping(value="/topicIndex")
	public ModelAndView topicIndex(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sociality/topic/topicManager");
		return mv;
	}
	
	
	@RequestMapping(value="/getTopicList",method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg getTopicList(@RequestParam("beginIndex") int beginIndex, 
								  @RequestParam("everyPage") int everyPage,
								  @RequestParam("isDelete") String isDelete,
								  @RequestParam("topicName") String topicName){
		try {
			long start1 = System.currentTimeMillis();
			TopicVO topicVO = new TopicVO();
			topicVO.setEveryPage(everyPage);
			topicVO.setIsDelete(isDelete==""?null:Integer.parseInt(isDelete));
			topicVO.setTopicName(topicName);
			logger.info("isDelete:"+isDelete);
			Page page = new Page();
			page.setCurrentPage(beginIndex);//设置第几页
			page.setEveryPage(everyPage);	//每页显示的记录数
			logger.info("pageIndex:"+beginIndex);
			logger.info("everyPage:"+everyPage);
			//总记录数
			long start2 = System.currentTimeMillis();
			int count = topicManagerService.countTopic(topicVO);
			logger.info("countTopic耗时------------>"+(System.currentTimeMillis()-start1)+"ms");
			logger.info("count:"+count);
			//分页工具类，计算分页数
			page = PageUtil.createPage(page, count);
			if(beginIndex<=1){
				topicVO.setBeginIndex(beginIndex-1);
			}else{
				topicVO.setBeginIndex((beginIndex-1)*15);
			}
			long start3 = System.currentTimeMillis();
			List<TopicVO> list = topicManagerService.findTopicByPageBean(topicVO);
			logger.info("findTopicByPageBean耗时------------>"+(System.currentTimeMillis()-start3)+"ms");
			//分页结果
			Result result = new Result(page, list);
			logger.info("getTopicList总耗时------------>"+(System.currentTimeMillis()-start1)+"ms");
			return new ResultMsg(Status.SUCCESS, "获取话题组信息成功！", result);
		}catch(Exception e){
			logger.error("TopicMangerController>getTopicList error",e);
			return new ResultMsg(Status.FAILED, "获取话题组信息失败！");
		}
	}
	
	/**
	 * 添加话题组
	 * @author yangsheng@angeldoctor 
	 * @param topicPO
	 * @return
	 */
	@RequestMapping(value="/addTopic",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg addTopic(@RequestBody TopicPO topicPO,HttpServletRequest request){
		if(StringUtils.isEmpty(topicPO.getTopicName())){
			return new ResultMsg(Status.FAILED, "话题组名称不能为空!");
		}
		if(StringUtils.isEmpty(topicPO.getTopicProfile())){
			return new ResultMsg(Status.FAILED, "话题组简介不能为空!");
		}
		if(StringUtils.isEmpty(topicPO.getThematicImg())){
			return new ResultMsg(Status.FAILED, "话题组主题图不能为空!");
		}
		try{
			//获取登录用户
			CrmAdmin crmAdmin = (CrmAdmin)request.getSession().getAttribute("user");
			int i = topicManagerService.addTopic(topicPO, crmAdmin);
			logger.info("i---------->"+i);
			if(i >0){
				return new ResultMsg(Status.SUCCESS, "添加话题组信息成功!");
			}else{
				return new ResultMsg(Status.FAILED, "添加话题组信息失败!");
			}
		}catch(Exception e){
			logger.error("TopicMangerController>getTopicList error",e);
			return new ResultMsg(Status.FAILED, "添加话题组信息失败!");
		}
	}
	
	/**
	 * 编辑话题组
	 * @author yangsheng@angeldoctor 
	 * @param topicPO
	 * @return
	 */
	@RequestMapping(value="/getTopic",method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg getTopic(@RequestParam("topicId") String topicId){
		if(StringUtils.isEmpty(topicId)){
			return new ResultMsg(Status.FAILED, "话题组ID不能为空!");
		}
		try{
			TopicVO topicVO = topicManagerService.findTopic(Long.parseLong(topicId));
			if(topicVO !=null){
				return new ResultMsg(Status.SUCCESS, "查询话题组信息成功!",JSONObject.toJSON(topicVO));
			}else{
				return new ResultMsg(Status.FAILED, "查询话题组信息失败!");
			}
		}catch(Exception e){
			logger.error("TopicMangerController>getTopicList error",e);
			return new ResultMsg(Status.FAILED, "查询话题组信息失败!");
		}
	} 
	
	
	/**
	 * 编辑话题组
	 * @author yangsheng@angeldoctor 
	 * @param topicPO
	 * @return
	 */
	@RequestMapping(value="/editTopic",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg editTopic(@RequestBody TopicPO topicPO,HttpServletRequest request){
		if(StringUtils.isEmpty(topicPO.getTopicName())){
			return new ResultMsg(Status.FAILED, "话题组名称不能为空!");
		}
		if(StringUtils.isEmpty(topicPO.getTopicProfile())){
			return new ResultMsg(Status.FAILED, "话题组简介不能为空!");
		}
		if(StringUtils.isEmpty(topicPO.getThematicImg())){
			return new ResultMsg(Status.FAILED, "话题组主题图不能为空!");
		}
		if(StringUtils.isEmpty(String.valueOf(topicPO.getTopicId()))){
			return new ResultMsg(Status.FAILED, "话题组ID不能为空!");
		}
		try{
			//获取登录用户
			CrmAdmin crmAdmin = (CrmAdmin)request.getSession().getAttribute("user");
			int i = topicManagerService.editTopic(topicPO, crmAdmin);
			logger.info("i---------->"+i);
			if(i >0){
				return new ResultMsg(Status.SUCCESS, "更新话题组信息成功!");
			}else{
				return new ResultMsg(Status.FAILED, "更新话题组信息失败!");
			}
		}catch(Exception e){
			logger.error("TopicMangerController>getTopicList error",e);
			return new ResultMsg(Status.FAILED, "更新话题组信息失败!");
		}
	}
	
	/**
	 * 启用或禁用话题组
	 * @author yangsheng@angeldoctor 
	 * @param topicPO
	 * @return
	 */
	@RequestMapping(value="/disableOrEnableTopic",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg disableOrEnableTopic(@RequestBody TopicPO topicPO,HttpServletRequest request){
		
		if(StringUtils.isEmpty(String.valueOf(topicPO.getTopicId()))){
			return new ResultMsg(Status.FAILED, "话题ID不能为空!");
		}
		if(StringUtils.isEmpty(String.valueOf(topicPO.getIsDelete()))){
			return new ResultMsg(Status.FAILED, "操作状态不能为空!");
		}
		try{
			//获取登录用户
			CrmAdmin crmAdmin = (CrmAdmin)request.getSession().getAttribute("user");
			int i = topicManagerService.editTopic(topicPO, crmAdmin);
			logger.info("i---------->"+i);
			if(i >0){
				return new ResultMsg(Status.SUCCESS, "启用或禁用话题组信息成功!");
			}else{
				return new ResultMsg(Status.FAILED, "启用或禁用话题组信息失败!");
			}
		}catch(Exception e){
			logger.error("TopicMangerController>getTopicList error",e);
			return new ResultMsg(Status.FAILED, "启用或禁用话题组信息失败!");
		}
	}
	
	/**
	 * 设为推荐或取消推荐话题组
	 * @author yangsheng@angeldoctor 
	 * @param topicPO
	 * @return
	 */
	@RequestMapping(value="/setOrCancelRecommendTopic",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg setOrCancelRecommendTopic(@RequestBody TopicPO topicPO,HttpServletRequest request){
		
		if(StringUtils.isEmpty(String.valueOf(topicPO.getTopicId()))){
			return new ResultMsg(Status.FAILED, "话题ID不能为空!");
		}
		if(StringUtils.isEmpty(String.valueOf(topicPO.getDataState()))){
			return new ResultMsg(Status.FAILED, "推荐状态不能为空!");
		}
		try{
			//获取登录用户
			CrmAdmin crmAdmin = (CrmAdmin)request.getSession().getAttribute("user");
			int i = topicManagerService.editTopic(topicPO, crmAdmin);
			logger.info("i---------->"+i);
			if(i >0){
				return new ResultMsg(Status.SUCCESS, "设置推荐话题组信息成功!");
			}else{
				return new ResultMsg(Status.FAILED, "设置推荐话题组信息失败!");
			}
		}catch(Exception e){
			logger.error("TopicMangerController>getTopicList error",e);
			return new ResultMsg(Status.FAILED, "设置推荐话题组信息失败!");
		}
	}
	
	/**
	 * 
	 * 话题组置上
	 * @Title: climbUp
	 * @param: @param topicPO
	 * @param: @param request
	 * @param: @return
	 * @return: ResultMsg
	 */
	@RequestMapping(value="/climbUp")
	@ResponseBody
	public ResultMsg climbUp(@RequestBody TopicPO topicPO,HttpServletRequest request){
		if(StringUtils.isEmpty(String.valueOf(topicPO.getTopicId()))){
			return new ResultMsg(Status.FAILED, "话题ID不能为空!");
		}
		if(StringUtils.isEmpty(String.valueOf(topicPO.getPositionId()))){
			return new ResultMsg(Status.FAILED, "话题位置不能为空!");
		}
		try{
			//获取登录用户
			CrmAdmin crmAdmin = (CrmAdmin)request.getSession().getAttribute("user");
			
			int i = topicManagerService.climbUp(topicPO, crmAdmin);
			
			logger.info("climbUp result：---------->"+i);
			if(i >0){
				return new ResultMsg(Status.SUCCESS, "话题组置上成功!");
			}else{
				return new ResultMsg(Status.FAILED, "话题组置上失败!");
			}
		}catch(Exception e){
			logger.error("TopicMangerController>climbUp error",e);
			return new ResultMsg(Status.FAILED, "话题组置上失败!");
		}
	}
	
//	@RequestMapping(value="/forbiddenSpeaking")
//	@ResponseBody
//	public ResultMsg forbiddenSpeaking(@RequestBody UserTopicPO userTopicPO,HttpServletRequest request){
////		if(StringUtils.isEmpty(String.valueOf(topicPO.getTopicId()))){
////			return new ResultMsg(Status.FAILED, "话题ID不能为空!");
////		}
//		if(StringUtils.isEmpty(String.valueOf(userTopicPO.getIsBlacklist()))){
//			return new ResultMsg(Status.FAILED, "是否禁言不能为空!");
//		}
//		try{
//			//获取登录用户
//			CrmAdmin crmAdmin = (CrmAdmin)request.getSession().getAttribute("user");
//			
//			int i = topicManagerService.climbUp(userTopicPO, crmAdmin);
//			
//			logger.info("climbUp result：---------->"+i);
//			if(i >0){
//				return new ResultMsg(Status.SUCCESS, "话题组置上成功!");
//			}else{
//				return new ResultMsg(Status.FAILED, "话题组置上失败!");
//			}
//		}catch(Exception e){
//			logger.error("TopicMangerController>climbUp error",e);
//			return new ResultMsg(Status.FAILED, "话题组置上失败!");
//		}
//	}
	
	@RequestMapping(value="/getTopicByBean")
	@ResponseBody
	public ResultMsg getTopicByBean(@RequestParam("topicName") String topicName){
		try{
			TopicVO queryBean = new TopicVO();
			queryBean.setTopicName(topicName);
			List<TopicVO> list = topicManagerService.findTopicByBean(queryBean);
			if(list !=null  && list.size() >0){
				for(TopicVO  vo : list){
					vo.setPingYin(StringUtil.getPingYin(vo.getTopicName()));
				}
				//按首字母排序
				Collections.sort(list, new Comparator<TopicVO>(){
					public int compare(TopicVO o1, TopicVO o2) {
						String str1 = StringUtil.getFirstSpell(o1.getTopicName()).substring(0,1);
						String str2 = StringUtil.getFirstSpell(o2.getTopicName()).substring(0, 1);
						return Collator.getInstance(Locale.CHINESE).compare(str1, str2); 
					}
				});
				return new ResultMsg(Status.SUCCESS,"获取话题组信息成功",list);
			}else{
				return new ResultMsg(Status.FAILED,"没有查询到话题组信息",new JSONArray(0));
			}
		}catch(Exception e){
			logger.error("TopicMangerController>getTopicByBean error",e);
			return new ResultMsg(Status.FAILED, "获取话题组信息失败!");
		}
	}
	
	@RequestMapping(value="/uploadImg",method=RequestMethod.POST)
	@ResponseBody
    public ResultMsg uploadImg(@RequestParam("baseFile") String[] baseFile,
    						   @RequestParam("fileSuffix") String fileSuffix,
    						   HttpServletRequest request){  
		 String tempFileName = UUID.randomUUID().toString().replace("-", "") +"."+fileSuffix;
         logger.debug("生成文件名为："+tempFileName);
         //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
         byte[] bs = Base64Utils.decodeFromString(baseFile[0]);
         try{
             //使用apache提供的工具类操作流
         	String  UPLOAD_FILE_PAHT  = request.getSession().getServletContext().getRealPath("/WEB-INF/jsp/tempuploadimg") + "/";
         	logger.info("UPLOAD_FILE_PAHT:"+UPLOAD_FILE_PAHT);
         	File localFile  = new File(UPLOAD_FILE_PAHT+tempFileName);
         	logger.info("localFile:"+localFile.getPath());
         	FileUtils.writeByteArrayToFile(localFile,bs); 
			String uploadResult = uploadToFileServer(localFile);
			localFile.delete();//删除本地上传的文件
			logger.info(uploadResult);
			if(StringUtils.isBlank(uploadResult)){
				return new ResultMsg(Status.FAILED, "图片上传失败！");
			}else{
				JSONObject uploadJSON = JSONObject.parseObject(uploadResult);
				int code = uploadJSON.getInteger("msg");
				if(code ==1){
					return new ResultMsg(Status.SUCCESS, "上传成功！", uploadJSON);
				}else{
					return new ResultMsg(Status.FAILED, "图片上传失败！");
				}
			}
         }catch(Exception e){
        	 logger.error("TopicMangerController>uploadImg error",e);
        	 return new ResultMsg(Status.FAILED, "图片上传失败！");
         }
    }
	
	/**
	 * 将文件上传到文件服务器上去
	 * 
	 * @param type
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String uploadToFileServer(File file) throws IOException {
		HashMap<String, File> files = new HashMap<String, File>();
		files.put("file", file);
		HashMap<String, String> params = new HashMap<String, String>();
		String response = NetUtil.sendMultyPartRequest(ConfigProUtils.get("file_upload") + "/file/upload_file", params, files);
		file.delete();
		return response;
	}
	
	/**
	 * 初始化页面数据
	 * 小组管理统计值的初始化
	 * @author huangzg 2017年1月14日 下午2:18:32
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/numCount",method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg get() {
		try {
			return new ResultMsg(Status.SUCCESS, "初始化数据成功！", topicManagerService.numCount());
		} catch (Exception e) {
			logger.info("初始化页面数据 msg :"+e.getMessage(), e);
		}
		return new ResultMsg(Status.SUCCESS, "初始化数据成功！");
	}
	

}
