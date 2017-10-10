/** 
 * 用户话题组管理
 * Project Name:jumper-angel-admin 
 * File Name:UserTopicManagerController.java 
 * Package Name:com.jumper.angel.sociality.topic.controller 
 * Date:2016年12月29日下午3:43:38 
 * Copyright (c) 2016, yangsheng@angeldoctor All Rights Reserved. 
 * 
*/  
package com.jumper.angel.sociality.topic.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.sociality.topic.model.po.UserTopicPO;
import com.jumper.angel.sociality.topic.model.vo.UserTopicVO;
import com.jumper.angel.sociality.topic.service.UserTopicManagerService;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.Result;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;

@Controller
@RequestMapping("/sociality/userTopicManager")
public class UserTopicManagerController {
	
	private final static Logger logger = Logger.getLogger(UserTopicManagerController.class);
	
	@Autowired
	private UserTopicManagerService userTopicManagerService;
	/**
	 * 跳转到成员管理页面
	 * @author yangsheng@angeldoctor 
	 * @return
	 */
	@RequestMapping("/userTopicIndex")
	public ModelAndView userTopicIndex(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sociality/topic/userTopicManager");
		return mv;
	}
	
	//选择话题组的时候在选中话题组内查询
	@RequestMapping("/getUserTopicList")
	@ResponseBody
	public ResultMsg getUserTopicList(@RequestParam("beginIndex") int beginIndex,
									  @RequestParam("everyPage") int everyPage,
									  @RequestParam("topicId") long topicId,
									  @RequestParam("isBlacklist") String isBlacklist,
									  @RequestParam("nickName") String nickName,
									  @RequestParam("mobile") String mobile){
		try{
//			if(StringUtils.isEmpty(String.valueOf(topicId))){
//				return new ResultMsg(Status.FAILED, "话题组ID不能为空!");
//			}
			long start1 = System.currentTimeMillis();
			UserTopicVO userTopicVO = new UserTopicVO();
			userTopicVO.setBeginIndex(beginIndex);
			userTopicVO.setEveryPage(everyPage);
			userTopicVO.setTopicId(topicId);
			if(StringUtils.isNotEmpty(nickName)){
				userTopicVO.setNickName(nickName);
			}
			if(StringUtils.isNotEmpty(isBlacklist)){
				userTopicVO.setIsBlacklist(Integer.parseInt(isBlacklist));
			}
			if(StringUtils.isNotEmpty(nickName)){
				userTopicVO.setNickName(nickName);
			}
			if(StringUtils.isNotEmpty(mobile)){
				userTopicVO.setMobile(mobile);
			}
			Page page = new Page();
			page.setCurrentPage(beginIndex);//设置第几页
			page.setEveryPage(everyPage);	//每页显示的记录数
			logger.info("pageIndex:"+beginIndex);
			logger.info("everyPage:"+everyPage);
			long start2 = System.currentTimeMillis();
			int count =  userTopicManagerService.findCountUserTopicByBean(userTopicVO);
			logger.info("count:"+count);
			logger.info("findCountUserTopicByBean耗时------------>"+(System.currentTimeMillis()-start2)+"ms");
			page = PageUtil.createPage(page, count);
			if(beginIndex<=1){
				userTopicVO.setBeginIndex(beginIndex-1);
			}else{
				userTopicVO.setBeginIndex((beginIndex-1)*15);
			}
			long start3 = System.currentTimeMillis();
			List<UserTopicVO> list = userTopicManagerService.findUserTopicByPageBean(userTopicVO);
			logger.info("findUserTopicByPageBean耗时------------>"+(System.currentTimeMillis()-start3)+"ms");
			//分页结果
			Result result = new Result(page, list);
			if(list !=null && list.size() >0){
				logger.info("getUserTopicList总耗时------------>"+(System.currentTimeMillis()-start1)+"ms");
				return new ResultMsg(Status.SUCCESS, "获取用户话题组信息成功!", result);
			}else{
				return new ResultMsg(Status.FAILED, "没有查询到用户话题组信息!", result);
			}
		}catch(Exception e){
			logger.error("UserTopicManagerController>getUserTopicList error",e);
			return new ResultMsg(Status.FAILED, "获取用户话题组信息失败!");
		}
	}
	
	//没选择话题组的时候全局查询
	@RequestMapping("/getUserAllTopicList")
	@ResponseBody
	public ResultMsg getUserAllTopicList(@RequestParam("beginIndex") int beginIndex,
									  @RequestParam("everyPage") int everyPage,
									  @RequestParam("isBlacklist") String isBlacklist,
									  @RequestParam("nickName") String nickName,
									  @RequestParam("mobile") String mobile){
		try{
			long start1 = System.currentTimeMillis();
			UserTopicVO userTopicVO = new UserTopicVO();
			userTopicVO.setBeginIndex(beginIndex);
			userTopicVO.setEveryPage(everyPage);
			if(StringUtils.isNotEmpty(nickName)){
				userTopicVO.setNickName(nickName);
			}
			if(StringUtils.isNotEmpty(isBlacklist)){
				userTopicVO.setIsBlacklist(Integer.parseInt(isBlacklist));
			}
			if(StringUtils.isNotEmpty(nickName)){
				userTopicVO.setNickName(nickName);
			}
			if(StringUtils.isNotEmpty(mobile)){
				userTopicVO.setMobile(mobile);
			}
			Page page = new Page();
			page.setCurrentPage(beginIndex);//设置第几页
			page.setEveryPage(everyPage);	//每页显示的记录数
			logger.info("pageIndex:"+beginIndex);
			logger.info("everyPage:"+everyPage);
			long start2 = System.currentTimeMillis();
			int count =  userTopicManagerService.findCountUserAllTopicByBean(userTopicVO);
			logger.info("count:"+count);
			logger.info("findCountUserAllTopicByBean耗时------------>"+(System.currentTimeMillis()-start2)+"ms");
			page = PageUtil.createPage(page, count);
			if(beginIndex<=1){
				userTopicVO.setBeginIndex(beginIndex-1);
			}else{
				userTopicVO.setBeginIndex((beginIndex-1)*15);
			}
			long start3 = System.currentTimeMillis();
			List<UserTopicVO> list = userTopicManagerService.findUserAllTopicByPageBean(userTopicVO);
			logger.info("findUserAllTopicByPageBean耗时------------>"+(System.currentTimeMillis()-start3)+"ms");
			//分页结果
			Result result = new Result(page, list);
			if(list !=null && list.size() >0){
				logger.info("getUserAllTopicList耗时------------>"+(System.currentTimeMillis()-start1)+"ms");
				return new ResultMsg(Status.SUCCESS, "获取用户话题组信息成功!", result);
			}else{
				return new ResultMsg(Status.FAILED, "没有查询到用户话题组信息!", result);
			}
		}catch(Exception e){
			logger.error("UserTopicManagerController>getUserAllTopicList error",e);
			return new ResultMsg(Status.FAILED, "获取用户话题组信息失败!");
		}
	}
	
	/**
	 * 启用或禁用用户所在的话题组
	 * @author yangsheng@angeldoctor 
	 * @param userTopicVO
	 * @return
	 */
	@RequestMapping("/disableOrEnableUserTopic")
	@ResponseBody
	public ResultMsg disableOrEnableUserTopic(@RequestBody UserTopicVO userTopicVO,HttpServletRequest request){
		
		try{
			if(StringUtils.isEmpty(String.valueOf(userTopicVO.getId()))){
				return new ResultMsg(Status.FAILED, "用户话题组ID不能为空!");
			}
			if(StringUtils.isEmpty(String.valueOf(userTopicVO.getIsBlacklist()))){
				return new ResultMsg(Status.FAILED, "启用或禁用状态不能为空!");
			}
			//获取登录用户
			CrmAdmin crmAdmin = (CrmAdmin)request.getSession().getAttribute("user");
			
			int i = userTopicManagerService.edit(userTopicVO, crmAdmin);
			if(i > 0){
				return new ResultMsg(Status.SUCCESS, "操作成功!");
			}else{
				return new ResultMsg(Status.FAILED, "启用或禁用用户加入的话题失败!");
			}
		}catch(Exception e){
			logger.error("UserTopicManagerController>disableOrEnableUserTopic error",e);
			return new ResultMsg(Status.FAILED, "启用或禁用用户加入的话题失败!");
		}
	}
	
	//启用或禁用用户加入的话题
	@RequestMapping(value="/forbiddenSpeaking",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg forbiddenSpeaking(@RequestBody UserTopicVO userTopicVO,HttpServletRequest request){
		if(StringUtils.isEmpty(String.valueOf(userTopicVO.getId()))){
			return new ResultMsg(Status.FAILED, "用户话题组ID不能为空!");
		}
		if(StringUtils.isEmpty(String.valueOf(userTopicVO.getUserId()))){
			return new ResultMsg(Status.FAILED, "用户ID不能为空!");
		}
		if(StringUtils.isEmpty(String.valueOf(userTopicVO.getTopicIds()))){
			return new ResultMsg(Status.FAILED, "话题ID列表不能为空!");
		}
		if(StringUtils.isEmpty(String.valueOf(userTopicVO.getNickName()))){
			return new ResultMsg(Status.FAILED, "用户昵称不能为空!");
		}
		if(StringUtils.isEmpty(String.valueOf(userTopicVO.getIsBlacklist()))){
			return new ResultMsg(Status.FAILED, "是否禁言不能为空!");
		}
		try{
			//获取登录用户
			CrmAdmin crmAdmin = (CrmAdmin)request.getSession().getAttribute("user");
			//启用或禁用用户加入的话题
			int i = userTopicManagerService.forbiddenSpeaking(userTopicVO, crmAdmin);
			
			logger.info("forbiddenSpeaking result：---------->"+i);
			if(i >0){
				if(userTopicVO.getIsBlacklist()==0){
					return new ResultMsg(Status.SUCCESS, "解除禁言成功!");
				}else{
					return new ResultMsg(Status.SUCCESS, "禁言成功!");
				}
			}else{
				if(userTopicVO.getIsBlacklist()==0){
					return new ResultMsg(Status.FAILED, "解除禁言失败!");
				}else{
					return new ResultMsg(Status.FAILED, "禁言失败!");
				}
			}
		}catch(Exception e){
			logger.error("UserTopicManagerController>forbiddenSpeaking error",e);
			return new ResultMsg(Status.FAILED, "禁言失败!");
		}
	}
	
	/**
	 * 初始化页面数据
	 * 当前成员管理统计值的初始化
	 * @author huangzg 2017年1月14日 下午2:18:32
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/numCount",method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg get(@RequestParam("topicId") long topicId) {
		try {
			return new ResultMsg(Status.SUCCESS, "初始化数据成功！", userTopicManagerService.numCount(topicId));
		} catch (Exception e) {
			logger.info("初始化页面数据 msg :"+e.getMessage(), e);
		}
		return new ResultMsg(Status.SUCCESS, "初始化数据成功！");
	}
	
}



