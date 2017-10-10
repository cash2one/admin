package com.jumper.angel.home.pregnancy.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumper.angel.home.pregnancy.entity.PregnantAntenatalExaminationInfo;
import com.jumper.angel.home.pregnancy.entity.PregnantAntenatalExaminationItemInfo;
import com.jumper.angel.home.pregnancy.service.PregnantAntenatalExaminationInfoService;
import com.jumper.angel.home.pregnancy.service.PregnantAntenatalExaminationItemInfoService;
import com.jumper.angel.home.pregnancy.vo.VOPregAnteExamItemInfo;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;
import com.jumper.angel.utils.TimeUtils;

/**
 * 孕期管理控制器
 * @author gyx
 * @time 2016年12月23日
 */
@Controller
@RequestMapping("pregnancy")
public class PregnancyController {
	private final static Logger logger = Logger.getLogger(PregnancyController.class);
	
	@Autowired
	private PregnantAntenatalExaminationInfoService examinationInfoService;
	
	@Autowired
	private PregnantAntenatalExaminationItemInfoService itemInfoService;
	
	/**
	 * 产检管理列表
	 * @return
	 */
	@RequestMapping("findPregAnteExaminationInfoList")
	public ModelAndView findPregAnteExaminationInfoList(){
		ModelAndView mv = new ModelAndView();
		List<PregnantAntenatalExaminationInfo> pregAnteExaminationInfoList = examinationInfoService.findPregAnteExaminationInfoList();
		mv.addObject("pregAnteExaminationInfoList", pregAnteExaminationInfoList);
		mv.setViewName("pregnancy/pregAnteExaminationInfoList");
		return mv;
	}
	
	/**
	 * 删除产检管理信息
	 */
	@RequestMapping("deleteExaminationInfo")
	@ResponseBody
	public ResultMsg deleteExaminationInfo(@RequestParam("examinationInfoId") int examinationInfoId){
		boolean b = examinationInfoService.deleteExaminationInfo(examinationInfoId);
		if(b){
			return new ResultMsg(Status.SUCCESS, "删除产检信息成功！", null);
		}else{
			return new ResultMsg(Status.FAILED, "删除产检信息失败，请稍后重试！", null);
		}
	}
	
	/**
	 * 检查产检次数是否存在
	 */
	@RequestMapping("checkExamNumbers")
	@ResponseBody
	public ResultMsg checkExamNumbers(@RequestParam("infoId") int infoId, @RequestParam("examNumbers") int examNumbers){
		PregnantAntenatalExaminationInfo examinationInfo = examinationInfoService.checkExamNumbers(infoId,examNumbers);
		if(examinationInfo != null){
			return new ResultMsg(Status.SUCCESS, null, null);
		}else{
			return new ResultMsg(Status.FAILED, null, null);
		}
	}
	
	/**
	 * 添加或修改产检管理信息
	 */
	@RequestMapping("addOrEditExaminationInfo")
	@ResponseBody
	public ResultMsg addOrEditExaminationInfo(@RequestParam("infoId") int infoId, 
			@RequestParam("examNumbers") int examNumbers, @RequestParam("startWeek") int startWeek, 
			@RequestParam("endWeek") int endWeek, @RequestParam("remindWeek") int remindWeek, 
			@RequestParam("remind") String remind){
		PregnantAntenatalExaminationInfo examinationInfo = new PregnantAntenatalExaminationInfo();
		examinationInfo.setId(infoId);//产检信息id
		examinationInfo.setExaminationNumbers(examNumbers);//产检次数
		examinationInfo.setStartWeek(startWeek);//开始周
		examinationInfo.setEndWeek(endWeek);//结束周
		examinationInfo.setRemindWeek(remindWeek);//提醒周
		examinationInfo.setRemind(remind);//关爱小提醒
		examinationInfo.setAddTime(TimeUtils.getCurrentTime());//修改时间
		boolean b = examinationInfoService.addOrEditExaminationInfo(examinationInfo);
		if(b){
			if(infoId == 0){
				return new ResultMsg(Status.SUCCESS, "添加产检信息成功！", null);
			}else{
				return new ResultMsg(Status.SUCCESS, "修改产检信息成功！", null);
			}
		}else{
			if(infoId == 0){
				return new ResultMsg(Status.FAILED, "添加产检信息失败！", null);
			}else{
				return new ResultMsg(Status.FAILED, "修改产检信息失败！", null);
			}
		}
	}
	
	/**
	 * 产检项目管理
	 * 跳转到产检项目管理页面
	 */
	@RequestMapping("forwardPregAnteExamItemInfo")
	public ModelAndView forwardPregAnteExamItemInfo(){
		ModelAndView mv = new ModelAndView();
		List<PregnantAntenatalExaminationInfo> list = examinationInfoService.findPregAnteExamItems();
		mv.addObject("examList", list);
		mv.setViewName("pregnancy/pregAnteExamItemInfoList");
		return mv;
	}
	
	/**
	 * 获取产检项目信息
	 * @param pageIndex 第几页
	 * @param everyPage 每页记录数
	 * @param first 是否第一次请求
	 * @param keywords 搜索关键字
	 * @param infoId 产检次数id
	 * @return
	 */
	@RequestMapping("findPregAnteExamItemInfoList")
	@ResponseBody
	public ResultMsg findPregAnteExamItemInfoList(@RequestParam("pageIndex") int pageIndex, @RequestParam("everyPage") int everyPage, 
			@RequestParam("first") boolean first, @RequestParam("keywords") String keywords, @RequestParam("infoId") int infoId){
		try {
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示记录数
			page.setEveryPage(everyPage);
			//查询总记录数
//			keywords = new String(keywords.getBytes("ISO-8859-1"),"UTF-8");
			int count = itemInfoService.findCount(keywords,infoId);
			//计算分页
			page = PageUtil.createPage(page, count);
			if(first){
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			List<VOPregAnteExamItemInfo> itemInfoList = itemInfoService.findPregAnteExamItemInfoList(page.getBeginIndex(), page.getEveryPage(), keywords, infoId);
			if(itemInfoList != null && itemInfoList.size() > 0){
				return new ResultMsg(Status.SUCCESS, "获取产检项目数据成功！", itemInfoList);
			}else{
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.error("findPregAnteExamItemInfoList():"+e.getMessage());
			return new ResultMsg(Status.FAILED, "获取产检项目信息失败！");
		}
	}
	
	/**
	 * 删除产检项目
	 */
	@RequestMapping("deleteExaminationItemInfo")
	@ResponseBody
	public ResultMsg deleteExaminationItemInfo(@RequestParam("examinationItemInfoId") int examinationItemInfoId){
		boolean b = itemInfoService.deleteExaminationItemInfo(examinationItemInfoId);
		if(b){
			return new ResultMsg(Status.SUCCESS, "删除产检项目信息成功！", null);
		}else{
			return new ResultMsg(Status.FAILED, "删除产检项目信息失败，请稍后重试！", null);
		}
	}
	
	/**
	 * 获取产检次数信息
	 * @return
	 */
	@RequestMapping("findExaminationInfos")
	@ResponseBody
	public ResultMsg findExaminationInfos(){
		List<PregnantAntenatalExaminationInfo> pregAnteExaminationInfoList = examinationInfoService.findPregAnteExaminationInfoList();
		if(pregAnteExaminationInfoList != null && pregAnteExaminationInfoList.size() > 0){
			return new ResultMsg(Status.SUCCESS, "获取产检次数信息成功！", pregAnteExaminationInfoList);
		}else{
			return new ResultMsg(Status.FAILED, "获取产检次数信息失败！", null);
		}
	}
	
	/**
	 * 检查产检项目是否存在
	 */
	@RequestMapping("checkExamItemInfo")
	@ResponseBody
	public ResultMsg checkExamItemInfo(@RequestParam("itemId") int itemId, @RequestParam("examId") int examId, @RequestParam("itemName") String itemName){
		List<PregnantAntenatalExaminationItemInfo> itemInfos = itemInfoService.checkExamNumbers(itemId,examId,itemName);
		if(itemInfos != null && itemInfos.size() > 0){
			return new ResultMsg(Status.SUCCESS, null, null);
		}else{
			return new ResultMsg(Status.FAILED, null, null);
		}
	}
	
	/**
	 * 添加或修改产检项目信息
	 */
	@RequestMapping("addOrEditExamItemInfo")
	@ResponseBody
	public ResultMsg addOrEditExamItemInfo(@RequestParam("itemId") int itemId, 
			@RequestParam("examId") int examId, @RequestParam("itemName") String itemName, 
			@RequestParam("itemContent") String itemContent){
		PregnantAntenatalExaminationItemInfo itemInfo = new PregnantAntenatalExaminationItemInfo();
		itemInfo.setId(itemId);//项目id
		itemInfo.setExaminationInfoId(examId);//次数id
		itemInfo.setName(itemName);//项目名称
		itemInfo.setContent(itemContent);//详细描述
		itemInfo.setAddTime(TimeUtils.getCurrentTime());//修改时间
		boolean b = itemInfoService.addOrEditExamItemInfo(itemInfo);
		if(b){
			if(itemId == 0){
				return new ResultMsg(Status.SUCCESS, "添加产检项目信息成功！", null);
			}else{
				return new ResultMsg(Status.SUCCESS, "修改产检项目信息成功！", null);
			}
		}else{
			if(itemId == 0){
				return new ResultMsg(Status.FAILED, "添加产检项目信息失败！", null);
			}else{
				return new ResultMsg(Status.FAILED, "修改产检项目信息失败！", null);
			}
		}
	}
	
}
