package com.jumper.angel.user.statistics.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
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
import com.jumper.angel.user.statistics.entity.HospitalInfo;
import com.jumper.angel.user.statistics.service.HospitalInfoService;
import com.jumper.angel.user.statistics.service.UserInfoService;
import com.jumper.angel.user.statistics.vo.VOAllHospitalStatisticsInfo;
import com.jumper.angel.user.statistics.vo.VOCityInfo;
import com.jumper.angel.user.statistics.vo.VOProvinceInfo;
import com.jumper.angel.user.statistics.vo.VOUserDetailInfo;
import com.jumper.angel.user.statistics.vo.VOUserListInfo;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;
import com.jumper.angel.utils.TimeUtils;
import com.jumper.angel.utils.excel.ExportExcel;
import com.jumper.angel.utils.json.JsonUtils;

/**
 * 推广统计 控制器
 * @author gyx
 * @time 2017年3月13日
 */
@Controller
@RequestMapping("promotStatistics")
public class PromotStatisticsController {
	private final static Logger logger = Logger.getLogger(PromotStatisticsController.class);
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private HospitalInfoService hospitalInfoService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * 跳转到医院总表页面
	 * @return
	 */
	@RequestMapping("forwardPromotStatistics")
	public ModelAndView forwardPromotStatistics(){
		ModelAndView mv = new ModelAndView();
		//读取省份城市json文件返回
		try {
			String path = servletContext.getRealPath("/");
			logger.info("the real context path:"+path);
			String cnJson = path + "json" + File.separator + "china.province.json";
			logger.info("the china province and city path:"+cnJson);
			File jsonFile = new File(cnJson);
			if(!jsonFile.exists()){
				logger.info("forwardUserStatistics():get.china.province.fail");
			}
			ObjectMapper mapper = new ObjectMapper();
			List<VOProvinceInfo> proList = mapper.readValue(jsonFile, List.class);
			mv.addObject("proList", proList);
		}catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("statistics/allHospitalStatistics");
		return mv;
	}
	
	/**
	 * 获取医院总表数据
	 * @return
	 */
	@RequestMapping("findAllHospitalStatisticsList")
	@ResponseBody
	public ResultMsg findAllHospitalStatisticsList(@RequestParam("pageIndex") int pageIndex, @RequestParam("everyPage") int everyPage, 
			@RequestParam("first") boolean first, @RequestParam("keywords") String keywords, @RequestParam("province") int province, 
			@RequestParam("city") int city, @RequestParam("pointTime") String pointTime){
		try {
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示记录数
			page.setEveryPage(everyPage);
			//查询总记录数
//			keywords = new String(keywords.getBytes("ISO-8859-1"),"UTF-8");
			int count = hospitalInfoService.findCount(keywords, province, city);
			//计算分页
			page = PageUtil.createPage(page, count);
			if(first){
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			List<VOAllHospitalStatisticsInfo> statisticsList = hospitalInfoService.findAllHospitalStatisticsList(page.getBeginIndex(), page.getEveryPage(), keywords, province, city, pointTime);
			if(statisticsList != null && statisticsList.size() > 0){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("list", statisticsList);
				map.put("num", page.getBeginIndex());
				return new ResultMsg(Status.SUCCESS, "获取医院总表数据成功！", map);
			}else{
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.error("findAllHospitalStatisticsList():"+e.getMessage());
			return new ResultMsg(Status.FAILED, "获取医院总表数据失败！");
		}
	}
	
	
	
	/**
	 * 根据省份获取所含城市列表
	 * @return
	 */
	@RequestMapping("getCityByProvince")
	@ResponseBody
	public ResultMsg getCityByProvince(@RequestParam("id") int id){
		List<VOCityInfo> list = hospitalInfoService.getCityByProvince(id);
		return new ResultMsg(Status.SUCCESS, "获取城市列表数据成功！", list);
	}
	
	/**
	 * 跳转到院区查询空白页面
	 */
	@RequestMapping("forwardHopsitalBindPage")
	public ModelAndView forwardHopsitalBindPage(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("statistics/hospitalDetailStatistics");
		return mv;
	}
	
	/**
	 * 院区查询详情
	 */
	@RequestMapping("findHospitalDetailStatistics")
	@ResponseBody
	public ResultMsg findHospitalDetailStatistics(@RequestParam("pageIndex") int pageIndex, @RequestParam("everyPage") int everyPage, 
			@RequestParam("first") boolean first, @RequestParam("keywords") String keywords, @RequestParam("startTime") String startTime, 
			@RequestParam("endTime") String endTime, @RequestParam("currentIdentity") int currentIdentity){
		try {
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示记录数
			page.setEveryPage(everyPage);
			//查询总记录数
//			keywords = new String(keywords.getBytes("ISO-8859-1"),"UTF-8");
			if(keywords == null || "".equals(keywords)){
				return new ResultMsg(Status.NODATA, "请输入医院名称");
			}
			//根据医院名称查询医院id
			HospitalInfo hospitalInfo = hospitalInfoService.findHospitalInfoByName(keywords);
			if(hospitalInfo == null){
				return new ResultMsg(Status.NODATA, "平台上不存在此医院");
			}
			if(startTime != null && !"".equals(startTime)){
				startTime = startTime + " 00:00:00";
			}
			if(endTime != null && !"".equals(endTime)){
				endTime = endTime + " 23:59:59";
			}
			if((startTime == null || "".equals(startTime))&&(endTime == null || "".equals(endTime))){
				String day = TimeUtils.converStringDate(new Date(), "yyyy-MM-dd HH:mm:ss").substring(0,10);
				startTime = TimeUtils.getDateFormat(TimeUtils.getBeforeDayByDate(1, TimeUtils.convertToDate(day))).substring(0,10)+" 00:00:00";
				endTime = TimeUtils.getDateFormat(TimeUtils.getBeforeDayByDate(1, TimeUtils.convertToDate(day))).substring(0,10)+" 23:59:59";
			}
			int count = userInfoService.findCount(hospitalInfo.getId(), startTime, endTime, currentIdentity);
			//计算分页
			page = PageUtil.createPage(page, count);
			if(first){
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			//统计数据
			Map<String, Object> map = new HashMap<String, Object>();
			map = hospitalInfoService.countHospitalStatistics(hospitalInfo.getId(), startTime, endTime);
			//用户列表
			List<VOUserListInfo> userListInfo = userInfoService.findHospitalDetailStatistics(page.getBeginIndex(), page.getEveryPage(),hospitalInfo.getId(), startTime, endTime, currentIdentity);
			if(userListInfo != null && userListInfo.size() > 0){
				map.put("list", userListInfo);
				map.put("num", page.getBeginIndex());
				return new ResultMsg(Status.SUCCESS, "获取院区列表数据成功！", map);
			}else{
				return new ResultMsg(Status.NODATA, "该时段内医院无数据", map);
			}
		} catch (Exception e) {
			logger.error("findHospitalDetailStatistics():"+e.getMessage());
			return new ResultMsg(Status.FAILED, "获取院区数据失败！");
		}
	}
	
	/**
	 * 查看用户详情
	 */
	@RequestMapping("findUserInfoDetail")
	@ResponseBody
	public ResultMsg findUserInfoDetail(@RequestParam("userId") int userId){
		try {
			VOUserDetailInfo voDetailInfo = userInfoService.findUserInfoDetail(userId);
			return new ResultMsg(Status.SUCCESS, "获取用户详情数据成功！", voDetailInfo);
		} catch (ParseException e) {
			e.printStackTrace();
			return new ResultMsg(Status.FAILED, "获取用户详情数据失败！");
		}
	}
	
	/**
	 * 导出数据
	 */
	@RequestMapping("exportHospitalStatisticsData")
	public String exportHospitalStatisticsData(@RequestParam("keywords") String keywords, @RequestParam("province") int province, 
			@RequestParam("city") int city, @RequestParam("pointTime") String pointTime, HttpServletResponse response){
		try {
//			keywords = new String(keywords.getBytes("ISO-8859-1"),"UTF-8");
			int count = hospitalInfoService.findCount(keywords, province, city);
			List<VOAllHospitalStatisticsInfo> statisticsList = hospitalInfoService.findAllHospitalStatisticsList(0, count, keywords, province, city, pointTime);
			logger.info("开始导出数据========================");
			ExportExcel.ExportPromotionData(statisticsList, response);
			logger.info("导出数据完毕========================");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("导出数据失败！");
			return null;
		}
	}
	
	/**
	 * 自动补全医院名称
	 */
	@RequestMapping("searchHospital")
	@ResponseBody
	public void searchHospital(String q, HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
//			String keywords = new String(q.getBytes("ISO-8859-1"),"UTF-8");
			List<HospitalInfo> hosList = hospitalInfoService.searchHospitalByName(q);
			if(hosList != null && hosList.size() > 0){
				Writer writer = response.getWriter();
				writer.write(JsonUtils.toJSONString(hosList));
			}
//			return new ResultMsg(Status.SUCCESS, "获取医院列表数据成功！", hosList);
		} catch (Exception e) {
			e.printStackTrace();
//			return new ResultMsg(Status.FAILED, "获取医院列表数据失败！");
		}
	}
	
	
	
}
