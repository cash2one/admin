/** 
 * 帖子内同管理
 * Project Name:jumper-angel-admin 
 * File Name:DebatepostManager.java 
 * Package Name:com.jumper.angel.sociality.debatepost.controller 
 * Date:2016年12月29日上午11:18:09 
 * Copyright (c) 2016, yangsheng@angeldoctor All Rights Reserved. 
 * 
*/  
package com.jumper.angel.sociality.debatepost.controller;
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
import com.jumper.angel.sociality.debatepost.model.po.DebatepostPO;
import com.jumper.angel.sociality.debatepost.model.vo.DebatepostVO;
import com.jumper.angel.sociality.debatepost.service.DebatepostService;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.Result;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;

@Controller
@RequestMapping("/sociality/debatepostManager")
public class DebatepostManagerController {
	
	private final static Logger logger = Logger.getLogger(DebatepostManagerController.class);
	
	@Autowired
	private DebatepostService debatepostService;
	
	
	/**
	 * 跳转到内容管理页面
	 * @author yangsheng@angeldoctor 
	 * @return
	 */
	@RequestMapping(value="/debatepostIndex",method=RequestMethod.GET)
	public ModelAndView debatepostIndex(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sociality/debatepost/debatepostManager");
		return mv;
	}
	
	@RequestMapping(value="/getDebatepostList",method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg getDebatepostList(@RequestParam("beginIndex") int beginIndex, 
			  						   @RequestParam("everyPage") int everyPage,
			  						 @RequestParam("topicId") String topicId,
			  						@RequestParam("keyword") String keyword,
			  						@RequestParam("status") String status){
		
		try{
/*			if(StringUtils.isEmpty(topicId)){
				return new ResultMsg(Status.FAILED, "帖子ID不能为空");
			}*/
			DebatepostVO debatepostVO = new DebatepostVO();
			debatepostVO.setTopicId(Long.valueOf(topicId));
			debatepostVO.setEveryPage(everyPage);
			if(StringUtils.isNotEmpty(keyword)){
				debatepostVO.setKeyword(keyword);
			}
			if(StringUtils.isNotEmpty(status)){
				debatepostVO.setStatus(Integer.valueOf(status));
			}
			Page page = new Page();
			page.setCurrentPage(beginIndex);//设置第几页
			page.setEveryPage(everyPage);	//每页显示的记录数
			int count = debatepostService.findCoutDebatepostByBean(debatepostVO);
			page = PageUtil.createPage(page, count);
			if(beginIndex<=1){
				debatepostVO.setBeginIndex(beginIndex-1);
			}else{
				debatepostVO.setBeginIndex((beginIndex-1)*everyPage);
			}
			List<DebatepostVO> list = debatepostService.findDebatepostByPageBean(debatepostVO);
			//分页结果
			Result result = new Result(page, list);
			if(list !=null && list.size() >0){
				return new ResultMsg(Status.SUCCESS, "获取帖子集合成功!", result);
			}else{
				return new ResultMsg(Status.FAILED, "没有查询到帖子息!", result);
			}
		}catch(Exception e){
			logger.error("DebatepostManagerController>getDebatepostList error",e);
			return new ResultMsg(Status.FAILED, "获取帖子信息失败");
		}
	}
	
	@RequestMapping(value="/getAllDebatepostList",method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg getAllDebatepostList(@RequestParam("beginIndex") int beginIndex, 
			  						   @RequestParam("everyPage") int everyPage,
			  						@RequestParam("keyword") String keyword,
			  						@RequestParam("status") String status){
		
		try{
			DebatepostVO debatepostVO = new DebatepostVO();
			debatepostVO.setEveryPage(everyPage);
			if(StringUtils.isNotEmpty(keyword)){
				debatepostVO.setKeyword(keyword);
			}
			if(StringUtils.isNotEmpty(status)){
				debatepostVO.setStatus(Integer.valueOf(status));
			}
			Page page = new Page();
			page.setCurrentPage(beginIndex);//设置第几页
			page.setEveryPage(everyPage);	//每页显示的记录数
			int count = debatepostService.findCoutDebatepostByBean(debatepostVO);
			page = PageUtil.createPage(page, count);
			if(beginIndex<=1){
				debatepostVO.setBeginIndex(beginIndex-1);
			}else{
				debatepostVO.setBeginIndex((beginIndex-1)*everyPage);
			}
			List<DebatepostVO> list = debatepostService.findDebatepostByPageBean(debatepostVO);
			//分页结果
			Result result = new Result(page, list);
			if(list !=null && list.size() >0){
				return new ResultMsg(Status.SUCCESS, "获取帖子集合成功!", result);
			}else{
				return new ResultMsg(Status.FAILED, "没有查询到帖子息!", result);
			}
		}catch(Exception e){
			logger.error("DebatepostManagerController>getAllDebatepostList error",e);
			return new ResultMsg(Status.FAILED, "获取帖子信息失败");
		}
	}
	
	/**
	 * 查询帖子详情
	 * @author yangsheng@angeldoctor 
	 * @param debatepostId
	 * @return
	 */
	@RequestMapping(value="/showDetebapostDetail",method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg showDetebapostDetail(@RequestParam("debatepostId") String debatepostId){
		try{
			if(StringUtils.isEmpty(debatepostId)){
				return new ResultMsg(Status.FAILED, "帖子ID不能为空");
			}
			DebatepostVO debatepostVO = debatepostService.findById(Long.valueOf(debatepostId));
			return new ResultMsg(Status.SUCCESS, "获取帖子详情成功!", debatepostVO);
		}catch(Exception e){
			logger.error("DebatepostManagerController>getDebatepostList error",e);
			return new ResultMsg(Status.FAILED, "获取帖子详情信息失败");
		}
	}
	
	/**
	 * 显示或隐藏或删除帖子
	 * @author yangsheng@angeldoctor 
	 * @param debatepostId
	 * @param isDelete
	 * @return
	 */
	@RequestMapping(value="/disableOrEnableOrDeleteDetebapost",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg disableOrEnableOrDeleteDetebapost(@RequestBody DebatepostPO debatepostPO,HttpServletRequest request){
		
		try{
			if(debatepostPO.getDebatepostId() ==null || debatepostPO.getDebatepostId() ==0){
				return new ResultMsg(Status.FAILED, "帖子ID不能为空");
			}
			if(debatepostPO.getIsDelete() ==null || debatepostPO.getIsDelete() <0){
				return new ResultMsg(Status.FAILED, "操作状态不能为空");
			}
			//获取登录用户
			CrmAdmin crmAdmin = (CrmAdmin)request.getSession().getAttribute("user");
			int i = debatepostService.edit(debatepostPO, crmAdmin);
			if(i >0){
				return new ResultMsg(Status.SUCCESS, "修改成功!");
			}else{  
				return new ResultMsg(Status.FAILED, "修改失败,没有修改到数据!");
			}
		}catch(Exception e){
			logger.error("DebatepostManagerController>disableOrEnableOrDeleteDetebapost error",e);
			return new ResultMsg(Status.FAILED, "显示或隐藏或删除帖子失败");
		}
	}
	
	
	/**
	 * 设置或取消设置推广帖子
	 * @author yangsheng@angeldoctor 
	 * @param debatepostId
	 * @param isDelete
	 * @return
	 */
	@RequestMapping(value="/setOrCancelwhetherPopularDebatepost",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg setOrCancelwhetherPopularDebatepost(@RequestBody DebatepostPO debatepostPO,HttpServletRequest request){
		
		try{
			if(debatepostPO.getDebatepostId() ==null || debatepostPO.getDebatepostId() ==0){
				return new ResultMsg(Status.FAILED, "帖子ID不能为空");
			}
			if(debatepostPO.getWhetherPopular() ==null || debatepostPO.getWhetherPopular() <0){
				return new ResultMsg(Status.FAILED, "推广状态不能为空");
			}
			//获取登录用户
			CrmAdmin monitor = (CrmAdmin)request.getSession().getAttribute("user");
			int i = debatepostService.edit(debatepostPO, monitor);
			if(i >0){
				return new ResultMsg(Status.SUCCESS, "修改成功!");
			}else{
				return new ResultMsg(Status.FAILED, "修改失败,没有修改到数据!");
			}
		}catch(Exception e){
			logger.error("DebatepostManagerController>setOrCancelwhetherPopularDebatepost error",e);
			return new ResultMsg(Status.FAILED, "设置或取消设置推广帖子失败");
		}
	}
	
	/**
	 * 初始化页面数据
	 * 当前话题帖子管理统计值的初始化
	 * @author huangzg 2017年1月14日 下午2:18:32
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/numCount",method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg get(@RequestParam("topicId") long topicId) {
		try {
			return new ResultMsg(Status.SUCCESS, "初始化数据成功！", debatepostService.numCount(topicId));
		} catch (Exception e) {
			logger.info("初始化页面数据 msg :"+e.getMessage(), e);
		}
		return new ResultMsg(Status.SUCCESS, "初始化数据成功！");
	}
	
}
