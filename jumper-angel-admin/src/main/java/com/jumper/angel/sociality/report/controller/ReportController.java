/** 
 * Project Name:jumper-angel-manage 
 * File Name:ReportController.java 
 * Package Name:com.jumper.angel.sociality.report.controller 
 * Date:2017年1月10日上午10:31:19 
 * Copyright (c) 2017, yangsheng@angeldoctor All Rights Reserved. 
 * 
*/  
package com.jumper.angel.sociality.report.controller;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jumper.angel.sociality.report.model.vo.ReportVO;
import com.jumper.angel.sociality.report.service.ReportService;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;

@Controller
@RequestMapping("/sociality/reprot")
public class ReportController {
	
	private  Logger logger = Logger.getLogger(ReportController.class);
	
	@Autowired
	ReportService reportService;
	
	@RequestMapping(value="/updateReport",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg updateReport(@RequestBody ReportVO reportVO){
		try{
			int i = reportService.update(reportVO);
			if(i <=0){
				return new ResultMsg(Status.FAILED, "更新举报信息失败");
			}else{
				return new ResultMsg(Status.SUCCESS, "更新举报信息成功");
			}
		}catch(Exception e){
			logger.error("ReportDebatepostManagerController>updateReport error",e);
			return new ResultMsg(Status.FAILED, "更新举报信息失败");
		}
	}
	
	@RequestMapping(value="/getReportList",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg getReportList(@RequestBody ReportVO reportVO){
		try{
			List<ReportVO> list = reportService.findReportByBean(reportVO);
			return new ResultMsg(Status.SUCCESS, "获取举报详情信息成功",list);
		}catch(Exception e){
			logger.error("ReportDebatepostManagerController>getReportList error",e);
			return new ResultMsg(Status.FAILED, "获取举报详情信息失败");
		}
	}
	
	public static void main(String args[]){
		System.out.println(new Date().getTime());
	}
}
