package com.jumper.angel.hospital.hospital.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumper.angel.hospital.hospital.entity.HospitalDoctorMajor;
import com.jumper.angel.hospital.hospital.service.IHospitalDoctorMajorService;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.Result;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;
import com.jumper.angel.utils.StringUtil;
import com.wordnik.swagger.annotations.Api;

/**
 * 科室列表
 * */
@Controller
@RequestMapping("/hospital/majorList")
@Api(value="/hospital/majorList", description="科室列表")
public class MajorListController {
	private final static Logger logger = Logger.getLogger(HospitalHomepageController.class);
	
	@Autowired
	private IHospitalDoctorMajorService doctorMajorService;
	
	/**
	 * 科室列表
	 */
	@RequestMapping("/majorList")
	public ModelAndView hospitalNotOpenedList(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/hospital/homepage/majorList");
		return mv;
	}
	
	/**科室列表*/
	@RequestMapping(method = RequestMethod.GET,value="/majorLists")
	@ResponseBody
	public ResultMsg majorLists(
			@RequestParam("pageIndex") int pageIndex, 
			@RequestParam("everyPage") int everyPage,
			@RequestParam("keywords") String keywords,
			@RequestParam("first") boolean first,
			HttpServletRequest request){
		try{
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
			int count = doctorMajorService.findCount(keywords);
			//分页工具类，计算分页数
			page = PageUtil.createPage(page, count);
			if(first) {
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			List<HospitalDoctorMajor> list = doctorMajorService.getHospitalDoctorMajor(page.getBeginIndex(),page.getEveryPage(),keywords);
			//分页结果
			Result result = new Result(page, list);
			if(result.getContent()!=null && result.getContent().size()>0) {
				return new ResultMsg(Status.SUCCESS, "获取科室列表成功！", result.getContent());
			} else {
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.info("获取科室列表失败！");
			e.printStackTrace();
			return new ResultMsg(Status.FAILED, "获取科室列表失败！");
		}
	}
	/**添加或修改科室*/
	@RequestMapping(method = RequestMethod.POST,value="/addOrUpdateMajor")
	@ResponseBody
	public ResultMsg addOrUpdateMajor(
			@RequestParam("majorId") int majorId, 
			@RequestParam("major") String major,
			@RequestParam("imageUrl") String imageUrl,
			HttpServletRequest request){
		try{
			if (majorId < 0) {
				return new ResultMsg(Status.FAILED,"请输入正确的科室id");
			}
			if (StringUtil.isEmpty(major)) {
				return new ResultMsg(Status.FAILED,"科室名称不能为空");
			}
//			HospitalDoctorMajor doctorMajor = doctorMajorService.getByMajor(major);
//			if (doctorMajor != null) {
//				return new ResultMsg(Status.FAILED,"科室名称已经存在");
//			}
			ResultMsg resultMsg = doctorMajorService.addOrUpdateMajor(major,majorId,imageUrl);
			return new ResultMsg(resultMsg.getCode(), resultMsg.getMsgbox());
			//分页结果
//			if(msg == 1) {
//				return new ResultMsg(Status.SUCCESS, "操作成功！");
//			} else {
//				return new ResultMsg(Status.FAILED, "添加或修改科室失败！");
//			}
		} catch (Exception e) {
			logger.info("添加或修改科室失败！");
			e.printStackTrace();
			return new ResultMsg(Status.FAILED, "添加或修改科室失败！");
		}
	}
	/**删除科室*/
	@RequestMapping(method = RequestMethod.GET,value="/deleteMajor")
	@ResponseBody
	public ResultMsg deleteMajor(
			@RequestParam("majorId") int majorId, 
			HttpServletRequest request){
		try{
			if (majorId < 0) {
				return new ResultMsg(Status.FAILED,"请输入正确的科室id");
			}
			int msg = doctorMajorService.deleteMajor(majorId);
			//分页结果
			if(msg == 1) {
				return new ResultMsg(Status.SUCCESS, "删除成功！");
			} else {
				return new ResultMsg(Status.FAILED, "删除失败！");
			}
		} catch (Exception e) {
			logger.info("删除失败！");
			e.printStackTrace();
			return new ResultMsg(Status.FAILED, "删除失败！");
		}
	}
}