package com.jumper.angel.hospital.business.controller;

import java.io.File;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumper.angel.hospital.business.entity.SmsCharge;
import com.jumper.angel.hospital.business.entity.SmsContent;
import com.jumper.angel.hospital.business.entity.SmsRecord;
import com.jumper.angel.hospital.business.service.SmsRecordService;
import com.jumper.angel.user.statistics.entity.HospitalInfo;
import com.jumper.angel.user.statistics.service.HospitalInfoService;
import com.jumper.angel.user.statistics.vo.VOAllHospitalStatisticsInfo;
import com.jumper.angel.user.statistics.vo.VOProvinceInfo;
import com.jumper.angel.user.statistics.vo.VOUserListInfo;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;
import com.jumper.angel.utils.TimeUtils;
import com.jumper.angel.utils.json.JsonUtils;

/**
* @ClassName: SmsStatisticsController
* @Description: 短信统计
* @author liump
* @date 2017年4月28日 上午10:16:31
* @Modifier:
* @modifydate:
*/
@Controller
@RequestMapping("/sms")
public class SmsStatisticsController {
	private final static Logger logger =  Logger.getLogger(SmsStatisticsController.class);
	
	@Autowired
	private HospitalInfoService hospitalInfoService;
	
	@Autowired
	private SmsRecordService smsRecordService;
	
	@RequestMapping("/forwardSmsStatistics")
	public ModelAndView forwardPromotStatistics(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("business/sms_allStatistics");
		return mv;
	}
	
	/**
	 * 自动补全医院名称
	 */
	@RequestMapping("/searchHospital")//
	@ResponseBody
	public void searchHospital(String q, HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			if(q!=null&&!"".equals(q)){
	//		q = new String(q.getBytes("ISO-8859-1"),"UTF-8");
			List<HospitalInfo> hosList = hospitalInfoService.searchHospitalByName(q);
				if(hosList != null && hosList.size() > 0){
					Writer writer = response.getWriter();
					writer.write(JsonUtils.toJSONString(hosList));
				}
			}
//			return new ResultMsg(Status.SUCCESS, "获取医院列表数据成功！", hosList);
		} catch (Exception e) {
			e.printStackTrace();
//			return new ResultMsg(Status.FAILED, "获取医院列表数据失败！");
		}
	}
	
	
	@RequestMapping("/findAllHospitalSmsStatisticsList")
	@ResponseBody
	public ResultMsg findAllHospitalStatisticsList(//
			@RequestParam(value="pageIndex",defaultValue="1",required=false) int pageIndex, 
			@RequestParam(value="everyPage",defaultValue="10",required=false) int everyPage, 
			@RequestParam(value="first",required=true) boolean first, 
			@RequestParam(value="hospName",required=false) String hospName){
		try {
			Page page = new Page();
			page.setCurrentPage(pageIndex);//设置第几页
			page.setEveryPage(everyPage);//每页显示记录数
			//查询总记录数
//			hospName = new String(hospName.getBytes("ISO-8859-1"),"UTF-8");
			int count = smsRecordService.useSmsCount(hospName);
			//计算分页
			page = PageUtil.createPage(page, count);
			if(first){
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			ResultMsg resultMsg =new ResultMsg();
			if(hospName!=null&&!"".equals(hospName)){ //传入了医院名称
				 resultMsg = smsRecordService.findHospitalSmsStatisticsListByName(page.getBeginIndex(), page.getEveryPage(), hospName);
			}else{ //没有传入医院名称
				 resultMsg = smsRecordService.findAllHospitalSmsStatisticsList(page.getBeginIndex(), page.getEveryPage());
			}
			if(resultMsg.getCode()==1&&count!=0){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("list", resultMsg.getData());
				map.put("num", page.getBeginIndex());
				return new ResultMsg(Status.SUCCESS, "获取医院医院列表及短信統計數據数据成功！", map);
			}else{
				if(hospName!=null&&!"".equals(hospName)){
					return new ResultMsg(Status.NODATA, "你输入的医院，无短信统计数据！");
				}
				return new ResultMsg(Status.FAILED, "获取数据失败！");
			}
		} catch (Exception e) {
			logger.error("findAllHospitalStatisticsList():"+e.getMessage());
			return new ResultMsg(Status.FAILED, "获取医院医院列表及短信統計數據数据失败！");
		}
	}
	

	@RequestMapping("/init_page")//
	@ResponseBody
	public ModelAndView initPage(ModelAndView mv, @RequestParam(value="hospId",required=true) String hospId,
			@RequestParam(value="hospName",required=true) String hospName)throws Exception {
	//	hospName = new String(hospName.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println("hospId:"+hospId+",hospName:"+hospName);
		mv.addObject("hospId", hospId);
		mv.addObject("hospName", hospName);
		mv.setViewName("business/sms_detailStatistics");
		return mv;
	}
	
	@RequestMapping("/searchSmsDetailByHospID")//
	@ResponseBody
	public ResultMsg searchSmsDetailByHospID(@RequestParam(value="hospId",required=true) String hospId,
			@RequestParam(value="pageIndex",defaultValue="1",required=false) int pageIndex, 
			@RequestParam(value="everyPage",defaultValue="10",required=false) int everyPage, 
			@RequestParam(value="hospName",required=false) String hospName){
		try{
			if("".equals(hospId)||hospId==null){
				return new ResultMsg(Status.FAILED,"医院ID不允许为空！");
			}
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示记录数
			page.setEveryPage(everyPage);
			int count = smsRecordService.findCount(hospId);
			//计算分页
			page = PageUtil.createPage(page, count);
			//统计数据
			Map<String, Object> map = new HashMap<String, Object>();
			List<SmsRecord> smsList = smsRecordService.searchSmsDetailByHospID(hospId,page.getBeginIndex(), page.getEveryPage());
			if(smsList!=null&&smsList.size()>0){
				map.put("list", smsList);
				map.put("page", page);
				return new ResultMsg(Status.SUCCESS, "根据医院hospId获取医院各个业务发送短信的统计数据成功！", map);
			}else{
				return new ResultMsg(Status.NODATA, "你输入的医院，无短信统计数据！");
			}
		}catch(Exception e){
			logger.error("searchSmsDetailByHospID():"+e.getMessage());
			return new ResultMsg(Status.FAILED, "根据医院hospId获取医院各个业务发送短信的统计数据失败！");
		}
	}
	
	@RequestMapping("/init_page2")//
	@ResponseBody
	public ModelAndView initPage2(ModelAndView mv, @RequestParam(value="hospId",required=true) String hospId,
			@RequestParam(value="hospName",required=true) String hospName,
			@RequestParam(value="appid",required=true) String appid)throws Exception {
	//	hospName = new String(hospName.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println("hospId:"+hospId+",hospName:"+hospName);
		mv.addObject("hospId", hospId);
		mv.addObject("hospName", hospName);
		mv.addObject("appid", appid);
		mv.setViewName("business/sms_userStatistics");
		return mv;
	}
	
	
	@RequestMapping("findHospiDetailStatistics")//
	@ResponseBody
	public ResultMsg findHospiDetailStatistics(@RequestParam(value="hospId",required=true) String hospId,
			@RequestParam(value="appid",required=true) String appid,
			@RequestParam(value="mobile",required=false) String mobile,
			@RequestParam(value="beginTime",required=false) String beginTime,
			@RequestParam(value="endTime",required=false) String endTime,
			@RequestParam(value="pageIndex",defaultValue="1",required=false) int pageIndex,
			@RequestParam(value="everyPage",defaultValue="10",required=false) int everyPage){
		try {
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示记录数
			page.setEveryPage(everyPage);
			int count = smsRecordService.getCount(hospId,appid,mobile,beginTime, endTime);
			//计算分页
			page = PageUtil.createPage(page, count);
			//统计数据
			Map<String, Object> map = new HashMap<String, Object>();
			map = smsRecordService.countHospitalStatistics(hospId,appid,mobile,beginTime, endTime);
			//发送短信列表
			List<SmsCharge> smsList = smsRecordService.findUserDetailStatistics(page.getBeginIndex(), page.getEveryPage(),hospId,appid,mobile,beginTime, endTime);
			if(smsList != null && smsList.size() > 0){
				map.put("list", smsList);
				map.put("page", page);
				return new ResultMsg(Status.SUCCESS, "获取院区列表数据成功！", map);
			}else{
				if(mobile!=null&&!"".equals(mobile)){
					return new ResultMsg(Status.NODATA, "系统未给该用户发送短信", map);
				}
				return new ResultMsg(Status.NODATA, "查无数据！",map);
			}
		} catch (Exception e) {
			logger.error("findHospitalDetailStatistics():"+e.getMessage());
			return new ResultMsg(Status.FAILED, "获取数据失败！");
		}
	}
}
