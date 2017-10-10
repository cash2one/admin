/** 
 * 回收站
 * 
*/  
package com.jumper.angel.sociality.recycle.controller;
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
import com.jumper.angel.sociality.debatepost.model.vo.DebatepostVO;
import com.jumper.angel.sociality.recycle.service.RecycleService;
import com.jumper.angel.sociality.topic.model.vo.TopicVO;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.Result;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;

@Controller
@RequestMapping("/sociality/recycleManager")
public class RecycleManagerController {
	
	private final static Logger logger = Logger.getLogger(RecycleManagerController.class);
	
	@Autowired
	private RecycleService recycleService;

	/**
	 * 
	 * 帖子回收站
	 * @Title: recycleDebatepost
	 * @param: @return
	 * @return: ModelAndView
	 */
	@RequestMapping(value="/recycleDebatepost")
	public ModelAndView recycleDebatepost(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sociality/recycle/recycleDebatepost");
		return mv;
	}
	
	
	/**
	 * 根据用户昵称或者话题标题查询删除的帖子列表
	 * @return
	 */
	@RequestMapping(value="/getDeletedDebatepost",method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg getDeletedDebatepost(@RequestParam("beginIndex") int beginIndex, 
								  @RequestParam("everyPage") int everyPage,
								  @RequestParam("keyword") String keyword){
		/*if(StringUtils.isEmpty(keyword)){
			return new ResultMsg(Status.FAILED, "查询条件不能为空!");
		}*/
		try {
			DebatepostVO debatepostVO = new DebatepostVO();
			debatepostVO.setEveryPage(everyPage);
			debatepostVO.setKeyword(keyword.trim());
			Page page = new Page();
			page.setCurrentPage(beginIndex);//设置第几页
			page.setEveryPage(everyPage);	//每页显示的记录数
			logger.info("起始页:"+beginIndex);
			logger.info("每页显示条数:"+everyPage);
			//总记录数
			int count = recycleService.countDeletedDebatepost(debatepostVO);
			logger.info("count:"+count);
			//分页工具类，计算分页数
			page = PageUtil.createPage(page, count);
			if(beginIndex<=1){
				debatepostVO.setBeginIndex(beginIndex-1);
			}else{
				debatepostVO.setBeginIndex((beginIndex-1)*15);
			}
			
			List<DebatepostVO> list = recycleService.getDeletedDebatepost(debatepostVO);
			//分页结果
			Result result = new Result(page, list);
			return new ResultMsg(Status.SUCCESS, "获取删除帖子信息成功！", result);
		}catch(Exception e){
			logger.error("RecycleManagerController>getDeletedDebatepost error",e);
			return new ResultMsg(Status.FAILED, "获取删除帖子信息失败！");
		}
	}
	
	/**
	 * 评论回收站
	 * @Title: recycleComment
	 * @param: @return
	 * @return: ModelAndView
	 */
	@RequestMapping(value="/recycleComment")
	public ModelAndView recycleComment(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sociality/recycle/recycleComment");
		return mv;
	}
	
	/**
	 * 根据评论内容或用户昵称查询删除的评论列表
	 * @return
	 */
	@RequestMapping(value="/getDeletedComment",method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg getDeletedComment(@RequestParam("beginIndex") int beginIndex, 
								  @RequestParam("everyPage") int everyPage,
								  @RequestParam("keyword") String keyword){
		/*if(StringUtils.isEmpty(keyword)){
			return new ResultMsg(Status.FAILED, "查询条件不能为空!");
		}*/
		try {
			CommentOnVO commentOnVO = new CommentOnVO();
			commentOnVO.setEveryPage(everyPage);
			commentOnVO.setKeyword(keyword.trim());
			Page page = new Page();
			page.setCurrentPage(beginIndex);//设置第几页
			page.setEveryPage(everyPage);	//每页显示的记录数
			logger.info("起始页:"+beginIndex);
			logger.info("每页显示条数:"+everyPage);
			//总记录数
			int count = recycleService.countDeletedComment(commentOnVO);
			logger.info("count:"+count);
			//分页工具类，计算分页数
			page = PageUtil.createPage(page, count);
			if(beginIndex<=1){
				commentOnVO.setBeginIndex(beginIndex-1);
			}else{
				commentOnVO.setBeginIndex((beginIndex-1)*15);
			}
			
			List<CommentOnVO> list = recycleService.getDeletedComment(commentOnVO);
			//分页结果
			Result result = new Result(page, list);
			return new ResultMsg(Status.SUCCESS, "获取删除的评论信息成功！", result);
		}catch(Exception e){
			logger.error("RecycleManagerController>getDeletedComment error",e);
			return new ResultMsg(Status.FAILED, "获取删除的评论信息失败！");
		}
	}
}
