package com.jumper.angel.hospital.hospital.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumper.angel.hospital.hospital.entity.HospitalServiceList;
import com.jumper.angel.hospital.hospital.service.IHospitalServiceListService;
import com.jumper.angel.user.statistics.entity.HospitalInfo;
import com.jumper.angel.user.statistics.service.HospitalInfoService;
import com.jumper.angel.utils.ConfigProUtils;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.Result;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;
import com.jumper.angel.utils.StringUtil;
import com.wordnik.swagger.annotations.Api;

/**
 * 医院主页配置
 * */
@Controller
@RequestMapping("/hospital/homepage")
@Api(value="/hospital/homepage", description="医院主页配置")
public class HospitalHomepageController {
	private final static Logger logger = Logger.getLogger(HospitalHomepageController.class);
	@Autowired
	private IHospitalServiceListService hospitalServiceListService;
	@Autowired
	private HospitalInfoService hospitalInfoService;
//	@Autowired
//	private HospitalServiceListMapper serviceListMapper;
	
	/**
	 * 基础功能
	 */
	@RequestMapping("/basicFunction")
	public ModelAndView basicFunction(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/hospital/homepage/basicFunction");
		return mv;
	}
	
	/**基础功能*/
	@RequestMapping(method = RequestMethod.GET,value="/basicFunctions")
	@ResponseBody
	public ResultMsg basicFunction(
			@RequestParam("pageIndex") int pageIndex, 
			@RequestParam("everyPage") int everyPage,
			@RequestParam("hospitalId") Integer hospitalId,
			@RequestParam("first") boolean first,
			HttpServletRequest request){
		try{
			System.out.println("id= "+hospitalId);
			if ("".equals(hospitalId) && null == hospitalId) {
				logger.info("医院ID为空！");
				throw new Exception("医院ID为空！");
			}
			if(0 >= hospitalId){
				//天使医院
				hospitalId = Integer.valueOf(ConfigProUtils.get("ANGELSOUND_HOPSPITAL_ID"));
			}
			//实例化Page对象
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示的记录数
			page.setEveryPage(everyPage);
			//总记录数
			int count = hospitalServiceListService.findCount(hospitalId,0);
			//分页工具类，计算分页数
			page = PageUtil.createPage(page, count);
//			System.out.println(count);
			if(first) {
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			List<HospitalServiceList> list = hospitalServiceListService.queryBasicFunction(page.getBeginIndex(),page.getEveryPage(),hospitalId);
			//分页结果
			Result result = new Result(page, list);
			if(result.getContent()!=null && result.getContent().size()>0) {
				return new ResultMsg(Status.SUCCESS, "获取医院基础功能列表成功！", result.getContent());
			} else {
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.info("获取基础功能列表失败！");
			e.printStackTrace();
			return new ResultMsg(Status.FAILED, "获取医院基础功能列表失败！");
		}
	}
	/**
	 * 平台功能
	 */
	@RequestMapping("/platformFunction")
	public ModelAndView platformFunction(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/hospital/homepage/platformFunction");
		return mv;
	}
	
	/**平台功能*/
	@RequestMapping(method = RequestMethod.GET,value="/platformFunctions")
	@ResponseBody
	public ResultMsg platformFunction(
			@RequestParam("pageIndex") int pageIndex, 
			@RequestParam("everyPage") int everyPage,
			@RequestParam("hospitalId") Integer hospitalId,
			@RequestParam("first") boolean first,
			HttpServletRequest request){
		try{
			if ("".equals(hospitalId) && null == hospitalId) {
				logger.info("医院ID为空！");
				throw new Exception("医院ID为空！");
			}
			if(0 >= hospitalId){
				//天使医院
				hospitalId = Integer.valueOf(ConfigProUtils.get("ANGELSOUND_HOPSPITAL_ID"));
			}
			//实例化Page对象
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示的记录数
			page.setEveryPage(everyPage);
			//总记录数
			int count = hospitalServiceListService.findCount(hospitalId,1);
			//分页工具类，计算分页数
			page = PageUtil.createPage(page, count);
			if(first) {
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			List<HospitalServiceList> list = hospitalServiceListService.queryPlatformFunction(page.getBeginIndex(),page.getEveryPage(),hospitalId);
			//分页结果
			Result result = new Result(page, list);
			if(result.getContent()!=null && result.getContent().size()>0) {
				return new ResultMsg(Status.SUCCESS, "获取医院平台功能列表成功！", result.getContent());
			} else {
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.info("获取医院平台功能列表失败！");
			e.printStackTrace();
			return new ResultMsg(Status.FAILED, "获取医院平台功能列表失败！");
		}
	}
	/**
	 * 院内功能
	 */
	@RequestMapping("/hospitalFunction")
	public ModelAndView hospitalFunction(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/hospital/homepage/hospitalFunction");
		return mv;
	}
	
	/**院内功能*/
	@RequestMapping(method = RequestMethod.GET,value="/hospitalFunctions")
	@ResponseBody
	public ResultMsg hospitalFunction(
			@RequestParam("pageIndex") int pageIndex, 
			@RequestParam("everyPage") int everyPage,
			@RequestParam("hospitalId") Integer hospitalId,
			@RequestParam("first") boolean first,
			HttpServletRequest request){
		try{
			if ("".equals(hospitalId) && null == hospitalId) {
				logger.info("医院ID为空！");
				throw new Exception("医院ID为空！");
			}
			if(0 >= hospitalId){
				//天使医院
				hospitalId = Integer.valueOf(ConfigProUtils.get("ANGELSOUND_HOPSPITAL_ID"));
			}
			//实例化Page对象
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示的记录数
			page.setEveryPage(everyPage);
			//总记录数
			int count = hospitalServiceListService.findCount2(hospitalId);
			count += 1;
			//分页工具类，计算分页数
			page = PageUtil.createPage(page, count);
			if(first) {
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			//判断是否是最后一页
			boolean lastPage = false;
			int countPage = count / page.getEveryPage();
			if (count % page.getEveryPage() > 0) {
				countPage += 1;
			}
			if (countPage == page.getCurrentPage()) {
				lastPage = true;
			}
			List<HospitalServiceList> list = hospitalServiceListService.queryHospitalFunction(page.getBeginIndex(),page.getEveryPage(),hospitalId);
			for (HospitalServiceList hospitalServiceList : list) {
				hospitalServiceList.setLastPage(lastPage);
			}
			//分页结果
			Result result = new Result(page, list);
			if(result.getContent()!=null && result.getContent().size()>0) {
				return new ResultMsg(Status.SUCCESS, "获取医院院内功能列表成功！", result.getContent());
			} else {
				return new ResultMsg(Status.SUCCESS, "暂无数据！",new ArrayList<>());
			}
		} catch (Exception e) {
			logger.info("获取医院院内功能列表失败！");
			e.printStackTrace();
			return new ResultMsg(Status.FAILED, "获取医院院内功能列表失败！");
		}
	}
	
	/**模糊查询医院列表*/
	@RequestMapping(method = RequestMethod.POST,value="/getHospitalList")
	@ResponseBody
	public ResultMsg hospitalList(@RequestParam("keywords") String keywords,HttpServletRequest request){
		try{
			List<HospitalInfo> list = hospitalInfoService.getHosptitalList(keywords);
			if(list != null && list.size() > 0){
				return new ResultMsg(Status.SUCCESS, "查询医院列表成功！", list);
			}else{
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.info("查询医院列表失败！");
			e.printStackTrace();
			return new ResultMsg(Status.FAILED, "查询医院列表失败！");
		}
	}
	
	/**获取排序列表*/
	@RequestMapping(method = RequestMethod.GET,value="/getRankList")
	@ResponseBody
	public ResultMsg getRankList(@RequestParam("hospitalId") Integer hospitalId,HttpServletRequest request){
		try{
			List<HospitalServiceList> list = hospitalServiceListService.getRankList(hospitalId);
			if(list != null && list.size() > 0){
				return new ResultMsg(Status.SUCCESS, "获取排序列表成功！", list);
			}else{
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.info("获取排序列表失败！");
			e.printStackTrace();
			return new ResultMsg(Status.FAILED, "获取排序列表失败！");
		}
	}
	/**获取医院信息*/
	@RequestMapping(method = RequestMethod.GET,value="/getHospitalInformation")
	@ResponseBody
	public ResultMsg getHospitalInformation(
			@RequestParam("hospitalId") Integer hospitalId,
			HttpServletRequest request){
		try{
			List<Map<String, Object>> list = hospitalServiceListService.getHospitalInformation(hospitalId);
			if(list != null && list.size() > 0){
				return new ResultMsg(Status.SUCCESS, "获取医院信息成功！", list);
			}else{
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.info("获取医院信息失败！");
			e.printStackTrace();
			return new ResultMsg(Status.FAILED, "获取医院信息失败！");
		}
	}
	/**修改医院服务入口状态*/
	@RequestMapping(method = RequestMethod.GET,value="/updateHospitalServiceEntryStat")
	@ResponseBody
	public ResultMsg updateHospitalServiceEntryStat(
			@RequestParam("hospitalId") Integer hospitalId,
			@RequestParam("moduleId") Integer moduleId,
			@RequestParam("entryStat") Integer entryStat,
			HttpServletRequest request){
		try{
			int msg = hospitalServiceListService.updateHospitalServiceEntryStat(hospitalId,moduleId,entryStat);
			if(msg == 1){
				return new ResultMsg(Status.SUCCESS, "修改医院服务入口状态成功！");
			}
			return new ResultMsg(Status.NODATA, "修改医院服务入口状态失败！");
		} catch (Exception e) {
			logger.info("修改医院服务入口状态失败！");
			e.printStackTrace();
			return new ResultMsg(Status.FAILED, "修改医院服务入口状态失败！");
		}
	}
	/**修改医院服务链接状态*/
	@RequestMapping(method = RequestMethod.GET,value="/updateHospitalServiceUrlStat")
	@ResponseBody
	public ResultMsg updateHospitalServiceUrlStat(
			@RequestParam("hospitalId") Integer hospitalId,
			@RequestParam("moduleId") Integer moduleId,
			@RequestParam("url") String url,
			@RequestParam("urlStat") Integer urlStat,
			HttpServletRequest request){
		try{
			//查询该服务信息
			HospitalServiceList hospitalServiceList = hospitalServiceListService.getHospitalServiceListByModuleId(hospitalId,moduleId);
			if (hospitalServiceList == null) {
				return new ResultMsg(Status.NODATA, "不存在该服务！");
			}
			if (StringUtil.isEmpty(url)) {
				url = hospitalServiceList.getUrl();
			}
			if (urlStat == 1) {
				if (StringUtil.isEmpty(url)) {
					return new ResultMsg(Status.NODATA, "链接不能为空！");
				}
			}
			int msg = hospitalServiceListService.updateHospitalServiceUrlStat(hospitalId,moduleId,url,urlStat);
			if(msg == 1){
				return new ResultMsg(Status.SUCCESS, "修改医院服务链接状态成功！");
			}
			return new ResultMsg(Status.NODATA, "修改医院服务链接状态失败！");
		} catch (Exception e) {
			logger.info("修改医院服务链接状态失败！");
			e.printStackTrace();
			return new ResultMsg(Status.FAILED, "修改医院服务链接状态失败！");
		}
	}
	/**修改医院服务位置排序*/
	@RequestMapping(method = RequestMethod.POST,value="/updateHospitalServicePostionOrder")
	@ResponseBody
	public ResultMsg updateHospitalServicePostionOrder(
			@RequestBody List<Map<String,Object>> listMap){
		try{
			if (listMap == null || listMap.size() <= 0) {
				return new ResultMsg(Status.NODATA, "参数不正确！");
			}
			int msg = hospitalServiceListService.updateHospitalServicePostionOrder(listMap);
			if(msg == 1){
				return new ResultMsg(Status.SUCCESS, "修改医院服务位置排序成功！");
			}
			return new ResultMsg(Status.NODATA, "修改医院服务位置排序失败！");
		} catch (Exception e) {
			logger.info("修改医院服务位置排序失败！");
			e.printStackTrace();
			return new ResultMsg(Status.FAILED, "修改医院服务位置排序失败！");
		}
	}
	/**添加或修改自定义功能*/
	@RequestMapping(method = RequestMethod.POST,value="/addOrUpdateHospitalService")
	@ResponseBody
	public ResultMsg addOrUpdateHospitalService(
			@RequestParam("hospitalId") Integer hospitalId,
			@RequestParam("moduleId") Integer moduleId,
			@RequestParam("functionGroup") Integer functionGroup,
			@RequestParam("title") String title,
			@RequestParam("url") String url,
			HttpServletRequest request){
		try{
			if (hospitalId == null) {
				return new ResultMsg(Status.FAILED, "医院ID不能为空");
			}
			if (moduleId == null) {
				return new ResultMsg(Status.FAILED, "模块ID不能为空");
			}
			if (functionGroup == null) {
				return new ResultMsg(Status.FAILED, "模块分组不能为空");
			}
			if (StringUtil.isEmpty(title)) {
				return new ResultMsg(Status.FAILED, "模块名称不能为空");
			}
//			if(!"".equals(title)&&null!=title){
//				title = new String(title.getBytes("iso-8859-1"), "utf-8"); 
//			}
//			HospitalServiceModule module = hospitalServiceListService.getByTitle(title);
//			if (module != null) {
//				return new ResultMsg(Status.FAILED, "模块名称已经存在");
//			}
			if (StringUtil.isEmpty(url)) {
				return new ResultMsg(Status.FAILED, "链接不能为空");
			}
			ResultMsg resultMsg = hospitalServiceListService.addOrUpdateHospitalService(hospitalId,moduleId,functionGroup,title,url);
//			if(msg == 1){
//				return new ResultMsg(Status.SUCCESS, "添加或修改自定义功能成功！");
//			}
			return new ResultMsg(resultMsg.getCode(), resultMsg.getMsgbox());
		} catch (Exception e) {
			logger.info("添加或修改自定义功能失败！");
			e.printStackTrace();
			return new ResultMsg(Status.FAILED, "添加或修改自定义功能失败！");
		}
	}
	/**获取医院服务信息*/
	@RequestMapping(method = RequestMethod.GET,value="/getHospitalService")
	@ResponseBody
	public ResultMsg getHospitalService(
			@RequestParam("hospitalId") Integer hospitalId,
			@RequestParam("moduleId") Integer moduleId,
			HttpServletRequest request){
		try{
			if (hospitalId == null) {
				return new ResultMsg(Status.FAILED, "医院ID不能为空");
			}
			if (moduleId == null) {
				return new ResultMsg(Status.FAILED, "模块ID不能为空");
			}
			HospitalServiceList list = hospitalServiceListService.getHospitalServiceListByModuleId(hospitalId, moduleId);
			if(list != null){
				return new ResultMsg(Status.SUCCESS, "获取医院服务信息成功！", list);
			}
			return new ResultMsg(Status.NODATA, "暂无数据！");
		} catch (Exception e) {
			logger.info("获取医院服务信息失败！");
			e.printStackTrace();
			return new ResultMsg(Status.FAILED, "获取医院服务信息失败！");
		}
	}
}
