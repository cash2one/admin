package com.jumper.angel.home.advertise.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumper.angel.home.advertise.entity.PromotionAdvertisement;
import com.jumper.angel.home.advertise.service.PromotionAdvertisementService;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;

/**
 * 广告控制器
 * @author gyx
 * @time 2017年2月7日
 */
@Controller
@RequestMapping("advertise")
public class AdvertiseController {
	private final static Logger logger = Logger.getLogger(AdvertiseController.class);
	
	@Autowired
	private PromotionAdvertisementService advertiseService;
	
	/**
	 * 跳转到广告列表页面
	 * @return
	 */
	@RequestMapping("forwardAdvertiseList")
	public ModelAndView forwardAdvertiseList(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("advertise/advertiseList");
		return mv;
	}
	
	/**
	 * 获取广告列表
	 * @return
	 */
	@RequestMapping("findAdvertiseList")
	@ResponseBody
	public ResultMsg findAdvertiseList(@RequestParam("pageIndex") int pageIndex, @RequestParam("everyPage") int everyPage, 
			@RequestParam("first") boolean first, @RequestParam("keywords") String keywords){
		try {
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示记录数
			page.setEveryPage(everyPage);
			//查询总记录数
//			keywords = new String(keywords.getBytes("ISO-8859-1"),"UTF-8");
			int count = advertiseService.findCount(keywords);
			//计算分页
			page = PageUtil.createPage(page, count);
			if(first){
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			List<PromotionAdvertisement> advertiseList = advertiseService.findAdvertiseList(page.getBeginIndex(), page.getEveryPage(), keywords);
			if(advertiseList != null && advertiseList.size() > 0){
				return new ResultMsg(Status.SUCCESS, "获取广告列表数据成功！", advertiseList);
			}else{
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.error("findQuestionClassList():"+e.getMessage());
			return new ResultMsg(Status.FAILED, "获取广告列表数据失败！");
		}
	}
	
	/**
	 * 删除广告 
	 * @param advertiseId 广告id
	 * @return
	 */
	@RequestMapping("deleteAdvertise")
	@ResponseBody
	public ResultMsg deleteAdvertise(@RequestParam("advertiseId") int advertiseId){
		boolean b = advertiseService.deleteAdvertise(advertiseId);
		if(b){
			return new ResultMsg(Status.SUCCESS, "删除广告信息成功！", null);
		}else{
			return new ResultMsg(Status.FAILED, "删除广告信息失败，请稍后重试！", null);
		}
	}
	
	/**
	 * 添加或修改广告信息
	 * is_activity
	 * image_url
	 */
	@RequestMapping("addOrEditAdvertise")
	@ResponseBody
	public ResultMsg addOrEditAdvertise(@RequestParam("ad_id") int ad_id, @RequestParam("description") String description, 
			@RequestParam("web_url") String web_url, @RequestParam("image_url") String image_url, @RequestParam("is_activity") int is_activity){
		PromotionAdvertisement advertise = new PromotionAdvertisement();
		advertise.setId(ad_id);
		advertise.setDescription(description);
		advertise.setImageUrl(image_url);
		advertise.setWebUrl(web_url);
		advertise.setIsActivity(is_activity);
		boolean b = advertiseService.addOrEditAdvertise(advertise);
		if(b){
			if(ad_id == 0){
				return new ResultMsg(Status.SUCCESS, "添加广告信息成功！", null);
			}else{
				return new ResultMsg(Status.SUCCESS, "修改广告信息成功！", null);
			}
		}else{
			if(ad_id == 0){
				return new ResultMsg(Status.FAILED, "添加广告信息失败！", null);
			}else{
				return new ResultMsg(Status.FAILED, "修改广告信息失败！", null);
			}
		}
	}
	
	
	/**
	 * 设置或取消banner
	 */
	@RequestMapping("operateAdvertise")
	@ResponseBody
	public ResultMsg operateAdvertise(@RequestParam("ad_id") int ad_id, @RequestParam("is_banner") int is_banner){
		PromotionAdvertisement advertise = new PromotionAdvertisement();
		advertise.setId(ad_id);
		advertise.setIsBanner(is_banner==0?1:0);
		boolean b = advertiseService.addOrEditAdvertise(advertise);
		if(b){
			if(is_banner == 0){
				return new ResultMsg(Status.SUCCESS, "设置成banner成功！", null);
			}else{
				return new ResultMsg(Status.SUCCESS, "取消banner成功！", null);
			}
		}else{
			if(is_banner == 0){
				return new ResultMsg(Status.FAILED, "设置成banner失败！", null);
			}else{
				return new ResultMsg(Status.FAILED, "取消banner失败！", null);
			}
		}
	}
	
	
	
}
