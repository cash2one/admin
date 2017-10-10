package com.jumper.angel.hospital.business.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.jumper.angel.hospital.business.entity.Device;
import com.jumper.angel.hospital.business.service.BusinessService;
import com.jumper.angel.utils.ConfigProUtils;
import com.jumper.angel.utils.EcgUtils;
import com.jumper.angel.utils.HttpPostUtils;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.Result;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;
import com.jumper.angel.utils.excel.ImportExcelUtil;
import com.jumper.core.service.impl.IdWorkerImpl;

/**
 * 商户信息
 * @Description TODO
 * @author qinxiaowei
 * @date 2017年3月14日
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
@Controller
@RequestMapping("business")
public class BusinessController {

	private final static Logger logger = Logger.getLogger(BusinessController.class);
	
	@Autowired
	private BusinessService businessService;
	
	/**
	 * 用户列表页面
	 * @version 1.0
	 * @createTime 2017年3月14日,下午1:52:11
	 * @updateTime 2017年3月14日,下午1:52:11
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @return
	 */
	@RequestMapping(value="businessFromJsp", method=RequestMethod.GET)
	public ModelAndView businessFromJsp(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("business/business_info");
		return mv;
	}
	
	/**
	 * 查询所有商户信息
	 * @version 1.0
	 * @createTime 2017年3月14日,上午11:42:53
	 * @updateTime 2017年3月14日,上午11:42:53
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param businessName 商户名字
	 * @param pageIndex 页数
	 * @param everyPage 每页显示的记录数
	 * @param first 是否是首次加载 true首次 false不是
	 * @return
	 */
	@RequestMapping(value="findAllBusiness", method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg findAllBusiness(@RequestParam("businessName") String businessName, @RequestParam("pageIndex") int pageIndex, 
			@RequestParam("everyPage") int everyPage, @RequestParam("first") boolean first) {
		try {
			//businessName = new String(businessName.getBytes("ISO-8859-1"), "UTF-8");
			//实例化Page对象
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示的记录数
			page.setEveryPage(everyPage);
			//总记录数
			int count = businessService.findCount(businessName);
			//分页工具类，计算分页数
			page = PageUtil.createPage(page, count);
			if(first) {
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			//商户信息
			List<Map<String, Object>> list = businessService.findAllBusiness(businessName, page.getBeginIndex(), page.getEveryPage());
			//分页结果
			Result result = new Result(page, list);
			if(result.getContent()!=null && result.getContent().size()>0) {
				return new ResultMsg(Status.SUCCESS, "获取商户信息成功！", result.getContent());
			} else {
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.error("findAllBusiness()", e);
			return new ResultMsg(Status.FAILED, "获取商户信息失败！");
		}
	}
	
	/**
	 * 详情页面
	 * @version 1.0
	 * @createTime 2017年3月14日,下午3:01:52
	 * @updateTime 2017年3月14日,下午3:01:52
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @return
	 */
	@RequestMapping(value="deviceFromJsp", method=RequestMethod.GET)
	public ModelAndView deviceFromJsp(@RequestParam("id") long id){
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", id);
		mv.setViewName("business/device_info");
		return mv;
	}
	
	/**
	 * 查询商户设备信息
	 * @version 1.0
	 * @createTime 2017年3月14日,上午11:42:53
	 * @updateTime 2017年3月14日,上午11:42:53
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param businessName 商户名字
	 * @param pageIndex 页数
	 * @param everyPage 每页显示的记录数
	 * @param first 是否是首次加载 true首次 false不是
	 * @return
	 */
	@RequestMapping(value="findBusinessDevice", method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg findBusinessDevice(@RequestParam("businessId") String businessId, 
			@RequestParam("mac") String mac,
			@RequestParam("deviceType") int deviceType,
			@RequestParam("pageIndex") int pageIndex, 
			@RequestParam("everyPage") int everyPage, 
			@RequestParam("first") boolean first) {
		try {
			//实例化Page对象
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示的记录数
			page.setEveryPage(everyPage);
			//总记录数
			int count = businessService.findDeviceCount(businessId, mac, deviceType);
			//分页工具类，计算分页数
			page = PageUtil.createPage(page, count);
			if(first) {
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			//商户设备信息
			List<Device> list = businessService.findBusinessDevice(businessId, mac, deviceType, page.getBeginIndex(), page.getEveryPage());
			//分页结果
			Result result = new Result(page, list);
			if(result.getContent()!=null && result.getContent().size()>0) {
				return new ResultMsg(Status.SUCCESS, "获取设备信息成功！", result.getContent());
			} else {
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.error("findBusinessDevice()", e);
			return new ResultMsg(Status.FAILED, "获取设备信息失败！");
		}
	}
	
	/**
	 * 删除设备
	 * @version 1.0
	 * @createTime 2017年3月14日,下午5:10:28
	 * @updateTime 2017年3月14日,下午5:10:28
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param id 设备ID
	 * @return
	 */
	@RequestMapping(value="deleteDevice", method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg deleteDevice(@RequestParam("id") long id) {
		try {
			businessService.deleteDevice(id);
			return new ResultMsg(Status.SUCCESS, "删除成功！");
		} catch (Exception e) {
			logger.error("deleteDevice()", e);
			return new ResultMsg(Status.FAILED, "删除失败！");
		}
	}
	
	/**
	 * 导入excel文件
	 * @version 1.0
	 * @createTime 2017年3月15日,上午9:40:53
	 * @updateTime 2017年3月15日,上午9:40:53
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="importExcel", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResultMsg importExcel(HttpServletRequest request) {
		try {
			//导入时已存在的数据
			List<Device> exist = new ArrayList<Device>();
			//导入成功条数
			int successNum = 0;
			String businessId = request.getParameter("businessId");
			MultipartHttpServletRequest multiparRequest = (MultipartHttpServletRequest) request;
			//获取文件
			MultipartFile file = multiparRequest.getFile("file");
			if(file.isEmpty()) {
				return new ResultMsg(Status.FAILED, "文件不存在！");
			}
			//获取文件名
			String fileName = file.getOriginalFilename();
			//格式是否正确
			if(fileName.indexOf("xls")>0 || fileName.indexOf("xlsx")>0) {
				//获取输入流
				InputStream in = file.getInputStream();
				//解析excel
				List<List<Object>> list = new ImportExcelUtil().getBankListByExcel(in, fileName);
				for(int i=0; i<list.size(); i++) {
					List<Object> row = list.get(i);
					Device device = new Device();
					device.setId(new IdWorkerImpl(2).nextId());
					device.setSeriesNumber(row.get(0).toString());
					device.setMac(row.get(1).toString());
					device.setBusinessId(businessId);
					int deviceType = 0;
					if(row.get(3).toString().equals("心电仪")) {
						deviceType = 1;
					} else if(row.get(3).toString().equals("胎心仪")) {
						deviceType = 2;
					} else if(row.get(3).toString().equals("血糖仪")) {
						deviceType = 3;
					} else if(row.get(3).toString().equals("血压仪")) {
						deviceType = 4;
					} else if(row.get(3).toString().equals("体温计")) {
						deviceType = 5;
					} else if(row.get(3).toString().equals("血氧仪")) {
						deviceType = 6;
					} else if(row.get(3).toString().equals("体重秤")) {
						deviceType = 7;
					}
					device.setDeviceType(deviceType);
					device.setStat("0");
					Date date = new Date();
					device.setCreateDate(date);
					device.setOutdate(date);
					device.setManufacturer(row.get(4).toString());
					device.setDeviceName(row.get(2).toString());
					int connectType = 0;
					if(row.get(5).toString().equalsIgnoreCase("GPRS")) {
						connectType = 1;
					} else if(row.get(5).toString().equalsIgnoreCase("WIFI")) {
						connectType = 2;
					} else if(row.get(5).toString().equals("蓝牙")) {
						connectType = 3;
					}
					device.setConnectType(connectType);
					device.setDebugDevice(0);
					//查询商户设备是否存在
					boolean bool = false;
					if(device.getSeriesNumber()!="") {
						bool = businessService.findBusinessDeviceBySeriesNumber(device.getBusinessId(), device.getSeriesNumber());
					}
					//查询MAC是否存在
					boolean b = false;
					if(device.getMac()!="") {
						b = businessService.findBusinessDeviceByMAC(device.getBusinessId(), device.getMac());
					}
					if(!bool && !b) {
						if(device.getDeviceType() == 1) {
							//获取token
							String token = EcgUtils.getToken();
							//绑定设备
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("token", token);
							map.put("devSerial", row.get(0).toString());
							//访问绑定设备序列号接口
							String result = HttpPostUtils.doPost(ConfigProUtils.get("kanjia_auth_url") + "/AddDev.html", map);
							if(!("succeed").equals(result)) {
								exist.add(device);
								continue;
							}
						}
						successNum ++;
						//新增数据
						businessService.insert(device);
					} else {
						exist.add(device);
					}
				}
			} else {
				return new ResultMsg(Status.FAILED, "请添加相应的表格导入数据");
			}
			//封装返回结果
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("sucessNum", successNum);
			result.put("existResult", exist);
			return new ResultMsg(Status.SUCCESS, "导入excel成功！", result);
		} catch (Exception e) {
			logger.error("importExcel()", e);
			return new ResultMsg(Status.FAILED, "导入excel文件错误！");
		}
	}
}
