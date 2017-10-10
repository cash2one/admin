/** 
 * 被举报的评论
 * Project Name:jumper-angel-manage 
 * File Name:ReportCommentManagerController.java 
 * Package Name:com.jumper.angel.sociality.report.controller 
 * Date:2017年1月8日上午10:24:17 
 * Copyright (c) 2017, yangsheng@angeldoctor All Rights Reserved. 
 * 
*/  
package com.jumper.angel.sociality.report.controller;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.jumper.angel.sociality.report.model.vo.ReportCommentOnVO;
import com.jumper.angel.sociality.report.service.ReportService;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.Result;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;

@Controller
@RequestMapping("/sociality/reprot/comment")
public class ReportCommentManagerController {
	
	private  Logger logger = Logger.getLogger(ReportCommentManagerController.class);
	
	@Autowired
	ReportService reportService;
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sociality/report/reportCommentManager");
		return mav;
	}
	
	@RequestMapping(value="/getReportCommentList",method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg getReportCommentList(@RequestParam("beginIndex") int beginIndex, 
			   				@RequestParam("everyPage") int everyPage,
			   				@RequestParam("keyword") String keyword,
			   				@RequestParam("dataStatus") String dataStatus){
		try{
			ReportCommentOnVO reportCommentOnVO = new ReportCommentOnVO();
			reportCommentOnVO.setEveryPage(everyPage);
			if(StringUtils.isNotBlank(keyword)){
				reportCommentOnVO.setKeyword(keyword);
			}
			if(StringUtils.isNotBlank(dataStatus)){
				reportCommentOnVO.setDataStatus(Integer.parseInt(dataStatus));
			}
			Page page = new Page();
			page.setCurrentPage(beginIndex);//设置第几页
			page.setEveryPage(everyPage);	//每页显示的记录数
			int  count = reportService.findCountReportComment(reportCommentOnVO);
			page = PageUtil.createPage(page, count);
			if(beginIndex<=1){
				reportCommentOnVO.setBeginIndex(beginIndex-1);
			}else{
				reportCommentOnVO.setBeginIndex((beginIndex-1)*everyPage);
			}
			List<ReportCommentOnVO> list = reportService.findReportComment(reportCommentOnVO);
			//分页结果
			Result result = new Result(page, list);
			if(list !=null && list.size() >0){
				return new ResultMsg(Status.SUCCESS, "获取被举报的评论集合成功!", result);
			}else{
				return new ResultMsg(Status.FAILED, "没有查询到被举报的评论!", result);
			}
		}catch(Exception e){
			logger.error("ReportCommentManagerController>getReportCommentList error",e);
			return new ResultMsg(Status.FAILED, "获取被举报的评论信息失败");
		}
	}
	
}
