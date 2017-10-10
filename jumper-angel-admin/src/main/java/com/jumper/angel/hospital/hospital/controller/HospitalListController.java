package com.jumper.angel.hospital.hospital.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.home.login.service.MonitorAdminService;
import com.jumper.angel.hospital.hospital.vo.HospitalInfoManageVo;
import com.jumper.angel.hospital.hospital.vo.HospitalInfoVo;
import com.jumper.angel.user.statistics.service.HospitalInfoService;
import com.jumper.angel.user.statistics.vo.VOCityInfo;
import com.jumper.angel.user.statistics.vo.VOProvinceInfo;
import com.jumper.angel.utils.MD5EncryptUtils;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.Result;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.ReturnMsg;
import com.jumper.angel.utils.Status;
import com.jumper.angel.utils.StringUtil;
import com.wordnik.swagger.annotations.Api;

/**
 * 医院列表
 * */
@Controller
@RequestMapping("/hospital/hospitalList")
@Api(value="/hospital/hospitalList", description="医院列表")
public class HospitalListController {
	private final static Logger logger = Logger.getLogger(HospitalHomepageController.class);
	
	@Autowired
	private HospitalInfoService hospitalInfoService;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private MonitorAdminService monitorAdminService;
	
	/**
	 * 医院列表
	 */
	@RequestMapping("/hospitalList")
	public ModelAndView hospitalNotOpenedList(){
		ModelAndView mv = new ModelAndView();
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
			@SuppressWarnings("unchecked")
			List<VOProvinceInfo> proList = mapper.readValue(jsonFile, List.class);
			mv.addObject("proList", proList);
		}catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("/hospital/homepage/hospitalNotOpenedList");
		return mv;
	}
	/**
	 * 医院列表
	 */
	@RequestMapping("/hospitalOpenedList")
	public ModelAndView hospitalOpenedList(){
		ModelAndView mv = new ModelAndView();
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
			@SuppressWarnings("unchecked")
			List<VOProvinceInfo> proList = mapper.readValue(jsonFile, List.class);
			mv.addObject("proList", proList);
		}catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("/hospital/homepage/hospitalOpenedList");
		return mv;
	}
	
	/**医院数量*/
	@RequestMapping(method = RequestMethod.GET,value="/hospitalNumber")
	@ResponseBody
	public ResultMsg hospitalNumber(){
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String,Object> map = new HashMap<>();
		int notOpened = hospitalInfoService.findNumber(0);
		int opened = hospitalInfoService.findNumber(1);
//		int opened = hospitalInfoService.findNumber1();
		map.put("openedNum", opened);
		map.put("notOpenedNum", notOpened);
		list.add(map);
		return new ResultMsg(Status.SUCCESS, "获取成功！",list);
	}
	
	/**医院列表*/
	@RequestMapping(method = RequestMethod.GET,value="/hospitalLists")
	@ResponseBody
	public ResultMsg hospitalLists(
			@RequestParam("pageIndex") int pageIndex, 
			@RequestParam("everyPage") int everyPage,
			@RequestParam("statusType") int statusType,//开通网络诊室状态，0未开通，1开通
			@RequestParam("serviceType") Integer serviceType,//服务类型：1常规监护，2实时监护，3院内监护，4医院问诊，5营养管理，6孕妇学校，7高危管理，8母子手册
			@RequestParam("provinceId")Integer provinceId, 
			@RequestParam("cityId") Integer cityId, 
			@RequestParam("keywords") String keywords,
			@RequestParam("first") boolean first,
			HttpServletRequest request){
		try{
			if (serviceType == null) {
				serviceType = 0;
			}
			if (provinceId == null) {
				provinceId = 0;
			}
			if (cityId == null) {
				cityId = 0;
			}
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
			int count = 0;
			if (statusType != 0) {
				if (serviceType == 7 || serviceType == 8) {
					count = hospitalInfoService.findCount4(keywords, provinceId, cityId,serviceType);
				}else {
					count = hospitalInfoService.findCount3(keywords, provinceId, cityId,serviceType);
				}
			}else {
				count = hospitalInfoService.findCount2(keywords, provinceId, cityId);
			}
			
			//分页工具类，计算分页数
			page = PageUtil.createPage(page, count);
//			System.out.println("count="+count);
			if(first) {
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			List<HospitalInfoVo> list = new ArrayList<>();
			if (serviceType == 7 || serviceType == 8) {
				list = hospitalInfoService.getHosptitalInfoList2(page.getBeginIndex(),page.getEveryPage(),statusType,serviceType,provinceId,cityId,keywords);
			}else {
				list = hospitalInfoService.getHosptitalInfoList(page.getBeginIndex(),page.getEveryPage(),statusType,serviceType,provinceId,cityId,keywords);
			}
			//分页结果
			Result result = new Result(page, list);
			if(result.getContent()!=null && result.getContent().size()>0) {
				return new ResultMsg(Status.SUCCESS, "获取医院列表成功！", result.getContent());
			} else {
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.info("获取医院列表失败！");
			e.printStackTrace();
			return new ResultMsg(Status.FAILED, "获取医院列表失败！");
		}
	}
	/**获取医院信息*/
	@RequestMapping(method = RequestMethod.GET,value="/hospitalinfo")
	@ResponseBody
	public ResultMsg hospitalinfo(
			@RequestParam("hospitalId") Integer hospitalId){
		try{
			if (hospitalId == null) {
				return new ResultMsg(Status.FAILED, "请输入医院ID！");
			}
			HospitalInfoManageVo hospitalInfo = hospitalInfoService.selectHospitalInfoManageVo(hospitalId);
//			HospitalInfoManageVo
			if(hospitalInfo != null) {
				return new ResultMsg(Status.SUCCESS, "获取医院信息成功！", hospitalInfo);
			} else {
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.info("获取医院信息失败！");
			e.printStackTrace();
			return new ResultMsg(Status.FAILED, "获取医院信息失败！");
		}
	}
	
	/**添加/更新医院信息**/
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "hospitalInfoCommit")
//	@ApiOperation(value = "添加/更新医院信息", httpMethod = "POST", response = ReturnMsg.class, notes = "添加/更新医院信息", position = 1)
	public ResultMsg hospitalInfoCommit(@RequestBody HospitalInfoManageVo vo, HttpServletRequest request) {
		logger.info("-------- HospitalController.hospitalInfoCommit,参数:VoHospitalInfoManage:" + vo);
		String type = vo.getType();
		if (type != null && type.equalsIgnoreCase("add")) {// 添加新医院
			ResultMsg result = hospitalInfoService.addHospitalInfo(vo);
			return new ResultMsg(result.getCode(), result.getMsgbox());
		}
		if (type != null && type.equalsIgnoreCase("edit")) {

			ResultMsg result = hospitalInfoService.updateHospitalInfo(vo);
			return new ResultMsg(result.getCode(), result.getMsgbox());
		}
		return new ResultMsg(ReturnMsg.FAIL, "操作失败");
	}

//	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "deleteHospitalInfo")
//	@ApiOperation(value = "删除医院信息", httpMethod = "DELETE", response = ReturnMsg.class, notes = "删除医院信息", position = 1)
	public ResultMsg deleteHospitalInfo(@RequestParam Integer id, HttpServletResponse response) throws IOException {
		logger.info("-------- HospitalController.deleteHospitalInfo,参数:hospitalId:" + id);
		if (id == null) {
			return new ResultMsg(Status.FAILED, "传入的医院 ID不正确！");
//			response.getWriter().write("传入的医院 ID不正确");
		}
		ResultMsg returnMsg = hospitalInfoService.deleteHospitalInfo(id);
//		response.getWriter().write(returnMsg.getMsgbox());
		logger.info("code="+returnMsg.getCode()+"++++++++++msg="+returnMsg.getMsgbox());
		return new ResultMsg(returnMsg.getCode(), returnMsg.getMsgbox());
	}
	/**账号管理*/
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "accountManagement")
	public ResultMsg accountManagement(
			@RequestParam Integer hospitalId, 
			@RequestParam Integer monitorId, 
			@RequestParam String userName, 
			@RequestParam String password, 
			@RequestParam Integer status, //账号状态：0正常状态，1锁定状态，-1不操作
			@RequestParam Integer isEnabled, //是否禁用：1正常，0禁用，-1不操作
			HttpServletResponse response){
		if (hospitalId == null) {
			return new ResultMsg(Status.FAILED, "传入的医院 ID不正确！");
		}
		if (monitorId == null) {
			return new ResultMsg(Status.FAILED, "账户 ID不正确！");
		}
		if (StringUtil.isEmpty(userName)) {
			return new ResultMsg(Status.FAILED, "用户名不能为空！");
		}
		if (!StringUtil.isEmpty(password)) {
			password = MD5EncryptUtils.getMd5Value(password);
		}
		ResultMsg returnMsg = monitorAdminService.accountManagement(monitorId,hospitalId,userName,password,status,isEnabled);
		return new ResultMsg(returnMsg.getCode(), returnMsg.getMsgbox());
	}
	
	/**开通线上支付0.未开通，1已开通*/
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "isPayment")
	public ResultMsg isPayment(
			@RequestParam Integer hospitalId, 
			@RequestParam Integer status, //开通状态：0关闭，1开通
			HttpServletRequest request){
		if (hospitalId == null) {
			return new ResultMsg(Status.FAILED, "传入的医院 ID不正确！");
		}
		//获取当前登陆对象的ID
		CrmAdmin crmAdmin = (CrmAdmin) request.getSession().getAttribute("user");
		if(crmAdmin == null){
			logger.info("当前未登录，请登录！");
			return new ResultMsg(Status.FAILED, "当前未登录，请登录！");
		}
		ResultMsg returnMsg = hospitalInfoService.isPayment(hospitalId,status,crmAdmin);
		logger.info("code="+returnMsg.getCode()+"++++++++++msg="+returnMsg.getMsgbox());
		return new ResultMsg(returnMsg.getCode(), returnMsg.getMsgbox());
	}
	
	
	/**
	 * 获取服务列表
	 * @return
	 */
	@RequestMapping("getService")
	@ResponseBody
	public ResultMsg getService(){
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("id",0);
		map.put("name","全部服务");
		list.add(map);
		Map<String, Object> map1 = new HashMap<>();
		map1.put("id",1);
		map1.put("name","常规监护");
		list.add(map1);
		Map<String, Object> map2 = new HashMap<>();
		map2.put("id",2);
		map2.put("name","实时监护");
		list.add(map2);
		Map<String, Object> map3 = new HashMap<>();
		map3.put("id",3);
		map3.put("name","院内监护");
		list.add(map3);
		Map<String, Object> map4 = new HashMap<>();
		map4.put("id",4);
		map4.put("name","医院问诊");
		list.add(map4);
		Map<String, Object> map5 = new HashMap<>();
		map5.put("id",5);
		map5.put("name","营养管理");
		list.add(map5);
		Map<String, Object> map6 = new HashMap<>();
		map6.put("id",6);
		map6.put("name","孕妇学校");
		list.add(map6);
		Map<String, Object> map7 = new HashMap<>();
		map7.put("id",7);
		map7.put("name","高危管理");
		list.add(map7);
		Map<String, Object> map8 = new HashMap<>();
		map8.put("id",8);
		map8.put("name","母子手册");
		list.add(map8);
		return new ResultMsg(Status.SUCCESS, "获取服务列表数据成功！", list);
	}
	
	/**
	 * 获取省份列表
	 * @return
	 */
	@RequestMapping("getProvince")
	@ResponseBody
	public ResultMsg getProvince(){
		List<VOProvinceInfo> list = hospitalInfoService.getProvince();
		return new ResultMsg(Status.SUCCESS, "获取省份列表数据成功！", list);
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
	
	/**开通网络医院*/
//	@RequestMapping(method = RequestMethod.GET,value="/openHospitalAdmin")
//	@ResponseBody
//	public ResultMsg openHospitalAdmin(
//			@RequestParam("hospitalId") Integer hospitalId,
//			@RequestParam("moduleId") Integer moduleId,
//			@RequestParam("entryStat") Integer entryStat,
//			HttpServletRequest request){
//		try{
//			int msg = hospitalServiceListService.updateHospitalServiceEntryStat(hospitalId,moduleId,entryStat);
//			if(msg == 1){
//				return new ResultMsg(Status.SUCCESS, "修改医院服务入口状态成功！");
//			}
//			return new ResultMsg(Status.NODATA, "修改医院服务入口状态失败！");
//		} catch (Exception e) {
//			logger.info("修改医院服务入口状态失败！");
//			e.printStackTrace();
//			return new ResultMsg(Status.FAILED, "修改医院服务入口状态失败！");
//		}
//	}
}
