package com.jumper.angel.hospital.doctor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumper.angel.hospital.doctor.entity.HospitalConsultantInfo;
import com.jumper.angel.hospital.doctor.service.IDoctorCommentsInfoService;
import com.jumper.angel.hospital.doctor.service.IHospitalConsultantInfoService;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.ReturnMsg;
import com.jumper.angel.utils.Status;
import com.wordnik.swagger.annotations.Api;

/**
 * 咨询管理
*
* ConsultantController
*jumper-doctor
* 2016年12月21日 上午10:15:37
*
* @version 1.0.0
 */
@Controller
@RequestMapping("/hospital/doctor")
@Api(value="/hospital/doctor", description="咨询管理")
public class ConsultantController{
	private final static Logger logger = Logger.getLogger(HospitalConsultantInfo.class);
	@Autowired 
	private IHospitalConsultantInfoService hospitalConsultantInfoService;
	@Autowired
	private IDoctorCommentsInfoService commentsInfoService;
	
	/**
	 * 全部咨询
	* hospitalConsultantAll()
	 */
	@RequestMapping("/consultantAll")
	public ModelAndView consultant(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/hospital/doctor/consultantAll");
		return mv;
	}
	
//	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.GET, value = "/consultant")
	@ResponseBody
//	@ApiOperation(value="获取咨询列表", httpMethod="GET", response=ReturnMsg.class, notes="获取咨询列表", position=1)
	public ResultMsg hospitalConsultantAll(@RequestParam("pageIndex") int pageIndex, @RequestParam("everyPage") int everyPage, 
			@RequestParam("first") boolean first, @RequestParam("keywords") String keywords) {
		
		try {
			//实例化Page对象
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示的记录数
			page.setEveryPage(everyPage);
//			if(!"".equals(keywords)&&null!=keywords){
//				keywords = new String(keywords.getBytes("iso-8859-1"), "utf-8"); 
//			}
			//总记录数
//			logger.info("++++++++++++++++++++获取全部咨询总记录数开始+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
			int count = hospitalConsultantInfoService.findConsultantCount(keywords);
//			logger.info("++++++++++++++++++++获取全部咨询总记录数结束+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
			//分页工具类，计算分页数
			page = PageUtil.createPage(page, count);
			if(first){
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
//			logger.info("++++++++++++++++++++获取全部咨询列表开始+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
			List<Map<String, Object>> returnList = hospitalConsultantInfoService.findAllHospitalConsultantInfo(page.getCurrentPage(), page.getEveryPage(),keywords);
//			logger.info("++++++++++++++++++++获取全部咨询列表结束+++++++++时间:"+TimeUtils.convertLongToTimestampString(TimeUtils.YYYY_MM_DD_HH_MM_SS, new Date().getTime()));
			if(returnList != null && returnList.size() > 0){
//				logger.info("**************************获取咨询列表成功！");
				return new ResultMsg(Status.SUCCESS, "获取咨询列表成功！", returnList);
			}else{
//				logger.info("**************************暂无数据！");
				return new ResultMsg(Status.NODATA, "暂无数据！",new ArrayList<>());
			}
		} catch (Exception e) {
//			logger.info("获取咨询列表失败！:"+e.getMessage());
			logger.info("获取咨询列表失败！:"+e);
			return new ResultMsg(Status.FAILED, "获取咨询列表失败！",new ArrayList<>());
		}
	}
	
	/**关闭评论*/
	@RequestMapping("deletComment")
	@ResponseBody
	public ResultMsg deletComment(Integer commentId){
		try {
			synchronized(this) {
				if (commentId==null) {
					logger.info("评论ID不能空！");
					throw new Exception("评论ID不能空！");
				}
				int count = commentsInfoService.deletComment(commentId);
				if (count==1) {
					return new ResultMsg(ReturnMsg.SUCCESS,"关闭评论详情成功！");
				}
				return new ResultMsg(ReturnMsg.FAIL,"关闭评论详情失败！");
			}
		} catch (Exception e) {
			logger.info("关闭咨询详情失败！");
			e.printStackTrace();
			return new ResultMsg(ReturnMsg.FAIL,"关闭评论详情失败！");
		}
	}
	
}
