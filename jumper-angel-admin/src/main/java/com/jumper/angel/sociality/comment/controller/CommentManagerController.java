/** 
 * 评论管理
 * Project Name:jumper-angel-admin 
 * File Name:CommentManagerController.java 
 * Package Name:com.jumper.angel.sociality.comment.controller 
 * Date:2016年12月31日上午11:19:27 
 * Copyright (c) 2016, yangsheng@angeldoctor All Rights Reserved. 
 * 
*/  
package com.jumper.angel.sociality.comment.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.sociality.comment.model.vo.CommentOnVO;
import com.jumper.angel.sociality.comment.service.CommentOnService;
import com.jumper.angel.sociality.topic.model.vo.TopicVO;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.Result;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;

@Controller
@RequestMapping("/sociality/commentManager")
public class CommentManagerController {
	
	private final static Logger logger = Logger.getLogger(CommentManagerController.class);
	
	@Autowired
	private CommentOnService commentOnService;

	@RequestMapping(value="/commentIndex")
	public ModelAndView commentIndex(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sociality/comment/commentManager");
		return mv;
	}
	
	
	/**
	 * 根据帖子ID查询评论详情
	 * @author yangsheng@angeldoctor 
	 * @param debatepostId
	 * @return
	 */
	@RequestMapping(value="/getCommentAndDocommentList",method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg getCommentAndDocommentList(@RequestParam("debatepostId") String debatepostId,
			@RequestParam("topicId") String topicId){
		try{
			CommentOnVO queryVO = new CommentOnVO();
			if(StringUtils.isNotEmpty(debatepostId)){
				queryVO.setDebatepostId(Long.valueOf(debatepostId));
			}
			queryVO.setTopicId(Long.valueOf(topicId));
			List<CommentOnVO> commentList = commentOnService.findByBean(queryVO);
			for(CommentOnVO vo : commentList){
				vo.setTopicId(Long.valueOf(topicId));
			}
			return new ResultMsg(Status.SUCCESS, "获取评论详情成功!", commentList);
		}catch(Exception e){
			logger.error("CommentManagerController>getCommentList error",e);
			return new ResultMsg(Status.FAILED, "获取评论详情信息失败");
		}
	}
	
	/**
	 * 根据帖子ID查询已删除的评论详情
	 * @param debatepostId
	 * @return
	 */
	@RequestMapping(value="/getDelCommentAndDocommentList",method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg getDelCommentAndDocommentList(@RequestParam("debatepostId") String debatepostId,
			@RequestParam("topicId") String topicId){
		try{
			CommentOnVO queryVO = new CommentOnVO();
			if(StringUtils.isNotEmpty(debatepostId)){
				queryVO.setDebatepostId(Long.valueOf(debatepostId));
			}
			queryVO.setTopicId(Long.valueOf(topicId));
			List<CommentOnVO> commentList = commentOnService.findDelByBean(queryVO);
			for(CommentOnVO vo : commentList){
				vo.setTopicId(Long.valueOf(topicId));
			}
			return new ResultMsg(Status.SUCCESS, "获取评论详情成功!", commentList);
		}catch(Exception e){
			logger.error("CommentManagerController>getCommentList error",e);
			return new ResultMsg(Status.FAILED, "获取评论详情信息失败");
		}
	}
	
	/**
	 * 删除评论
	 * @authofr yangsheng@angeldoctor 
	 * @param debatepostId
	 * @return
	 */
	@RequestMapping(value="/deleteCommentById",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg deleteCommentById(@RequestParam("cmtId") String cmtId,
			@RequestParam("debatepostId") long debatepostId,
			@RequestParam("topicId") long topicId,
			HttpServletRequest request){
		
		if(StringUtils.isBlank(cmtId)){
			return new ResultMsg(Status.FAILED, "评论ID不能为空!");
		}
		try{
			CommentOnVO commentOnVO = new CommentOnVO();
			commentOnVO.setId(Long.valueOf(cmtId));
			commentOnVO.setStatus(3);
			commentOnVO.setDebatepostId(debatepostId);
			commentOnVO.setTopicId(topicId);
			//获取登录用户
			CrmAdmin crmAdmin = (CrmAdmin)request.getSession().getAttribute("user");
			int i = commentOnService.deleteCommentById(commentOnVO, crmAdmin);
			if(i > 0){
				return new ResultMsg(Status.SUCCESS, "删除成功");
			}else{
				return new ResultMsg(Status.FAILED, "删除失败");
			}
		}catch(Exception e){
			logger.error("CommentManagerController>deleteCommentById error",e);
			return new ResultMsg(Status.FAILED, "删除失败");
		}
	}
	
	/**
	 * 删除回复
	 * @authofr yangsheng@angeldoctor 
	 * @param debatepostId
	 * @return
	 */
	@RequestMapping(value="/deleteDocommentById",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg deleteDocommentById(@RequestParam("dctId") String dctId,
			@RequestParam("debatepostId") long debatepostId,
			@RequestParam("topicId") long topicId,
			HttpServletRequest request){
		if(StringUtils.isEmpty(dctId)){
			return new ResultMsg(Status.FAILED, "回复ID不能为空!");
		}
		try{
			CommentOnVO commentOnVO = new CommentOnVO();
			commentOnVO.setId(Long.valueOf(dctId));
			commentOnVO.setStatus(3);
			commentOnVO.setDebatepostId(debatepostId);
			commentOnVO.setTopicId(topicId);
			//获取登录用户
			CrmAdmin crmAdmin = (CrmAdmin)request.getSession().getAttribute("user");
			int i = commentOnService.deleteDocommentById(commentOnVO, crmAdmin);
			if(i > 0){
				return new ResultMsg(Status.SUCCESS, "删除成功");
			}else{
				return new ResultMsg(Status.FAILED, "删除失败");
			}
		}catch(Exception e){
			logger.error("CommentManagerController>deleteDocommentById error",e);
			return new ResultMsg(Status.FAILED, "删除失败");
		}
	}
	
	/**
	 * 根据评论内容或用户昵称查询评论列表
	 * @Title: getCommentByContentOrNickName
	 * @param: @param beginIndex
	 * @param: @param everyPage
	 * @param: @param keyword
	 * @param: @return
	 * @return: ResultMsg
	 */
	@RequestMapping(value="/getCommentByContentOrNickName",method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg getCommentByContentOrNickName(@RequestParam("beginIndex") int beginIndex, 
								  @RequestParam("everyPage") int everyPage,
								  @RequestParam("keyword") String keyword){
		if(StringUtils.isEmpty(keyword)){
			return new ResultMsg(Status.FAILED, "查询条件不能为空!");
		}
		try {
			CommentOnVO commentOnVO = new CommentOnVO();
			commentOnVO.setEveryPage(everyPage);
			commentOnVO.setKeyword(keyword);
			Page page = new Page();
			page.setCurrentPage(beginIndex);//设置第几页
			page.setEveryPage(everyPage);	//每页显示的记录数
			logger.info("起始页:"+beginIndex);
			logger.info("每页显示条数:"+everyPage);
			//总记录数
			int count = commentOnService.countComment(commentOnVO);
			logger.info("count:"+count);
			//分页工具类，计算分页数
			page = PageUtil.createPage(page, count);
			if(beginIndex<=1){
				commentOnVO.setBeginIndex(beginIndex-1);
			}else{
				commentOnVO.setBeginIndex((beginIndex-1)*15);
			}
			
			List<CommentOnVO> list = commentOnService.getCommentByContentOrNickName(commentOnVO);
			//分页结果
			Result result = new Result(page, list);
			return new ResultMsg(Status.SUCCESS, "获取评论信息成功！", result);
		}catch(Exception e){
			logger.error("CommentManagerController>getCommentByContentOrNickName error",e);
			return new ResultMsg(Status.FAILED, "获取评论信息失败！");
		}
	}
}
