/**
 * 被举报的用户 
 * Project Name:jumper-angel-manage 
 * File Name:ReportUserManagerController.java 
 * Package Name:com.jumper.angel.sociality.report.controller 
 * Date:2017年1月8日上午10:27:57 
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
import com.jumper.angel.sociality.report.model.vo.ReportUserVO;
import com.jumper.angel.sociality.report.service.ReportService;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.Result;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;

@Controller
@RequestMapping("/sociality/reprot/user")
public class ReportUserManagerController {
	
	private  Logger logger = Logger.getLogger(ReportUserManagerController.class);
	
	@Autowired
	ReportService reportService;
	
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sociality/report/reportUserManager");
		return mav;
	}
	
	@RequestMapping(value="/getReportUserList",method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg getReportUserList(@RequestParam("beginIndex") int beginIndex, 
			   				@RequestParam("everyPage") int everyPage,
			   				@RequestParam("keyword") String keyword,
			   				@RequestParam("status") String status){
		try{
			ReportUserVO reportUserVO = new ReportUserVO();
			reportUserVO.setEveryPage(everyPage);
			if(StringUtils.isNotBlank(keyword)){
				reportUserVO.setKeyword(keyword);
			}
			if(StringUtils.isNotBlank(status)){
				reportUserVO.setStatus(Integer.parseInt(status));
			}
			Page page = new Page();
			page.setCurrentPage(beginIndex);//设置第几页
			page.setEveryPage(everyPage);	//每页显示的记录数
			int  count = reportService.findCountReportUser(reportUserVO);
			page = PageUtil.createPage(page, count);
			if(beginIndex<=1){
				reportUserVO.setBeginIndex(beginIndex-1);
			}else{
				reportUserVO.setBeginIndex((beginIndex-1)*everyPage);
			}
			List<ReportUserVO> list = reportService.findReportUser(reportUserVO);
			//分页结果
			Result result = new Result(page, list);
			if(list !=null && list.size() >0){
				return new ResultMsg(Status.SUCCESS, "获取被举报的用户集合成功!", result);
			}else{
				return new ResultMsg(Status.FAILED, "没有查询到被举报的用户!", result);
			}
		}catch(Exception e){
			logger.error("ReportUserManagerController>getReportUserList error",e);
			return new ResultMsg(Status.FAILED, "获取被举报的用户信息失败");
		}
	}
}
