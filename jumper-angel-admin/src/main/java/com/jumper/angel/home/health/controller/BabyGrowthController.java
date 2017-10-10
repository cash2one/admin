package com.jumper.angel.home.health.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.jumper.angel.home.health.entity.PregnancyInfo;
import com.jumper.angel.home.health.service.PregnancyInfoService;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.Result;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;

/**
 * 宝宝发育情况
 * @Description TODO
 * @author qinxiaowei
 * @date 2016-12-23
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
@Controller
@RequestMapping("baby")
public class BabyGrowthController {
	
	private final static Logger logger = Logger.getLogger(BabyGrowthController.class);
	
	@Autowired
	private PregnancyInfoService pregnancyInfoService;
	
	/**
	 * 跳转到宝宝发育页面
	 * @version 1.0
	 * @createTime 2016-12-23,下午5:17:38
	 * @updateTime 2016-12-23,下午5:17:38
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @return
	 */
	@RequestMapping("forwordBaby")
	public ModelAndView forwordBaby() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("health/baby_growth");
		return mv;
	}
	
	/**
	 * 跳转到宝宝状态图
	 * @version 1.0
	 * @createTime 2016-12-27,下午5:32:14
	 * @updateTime 2016-12-27,下午5:32:14
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @return
	 */
	@RequestMapping("forwordBabyStatusImage")
	public ModelAndView forwordBabyStatusImage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("health/baby_status_image");
		return mv;
	}
	
	/**
	 * 查询宝宝发育变化
	 * @version 1.0
	 * @createTime 2016-12-26,上午10:23:49
	 * @updateTime 2016-12-26,上午10:23:49
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param beginIndex 页数
	 * @param everyPage 每页显示的记录数
	 * @param first 是否是首次加载 true首次 false不是
	 * @return
	 */
	@RequestMapping(value="findBaby", method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg findBaby(@RequestParam("pageIndex") int pageIndex, @RequestParam("everyPage") int everyPage, 
			@RequestParam("first") boolean first) {
		try {
			//实例化Page对象
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示的记录数
			page.setEveryPage(everyPage);
			//总记录数
			int count = pregnancyInfoService.findCount();
			//分页工具类，计算分页数
			page = PageUtil.createPage(page, count);
			if(first) {
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			//宝宝发育变化
			List<PregnancyInfo> list = pregnancyInfoService.findAllPrepnancyInfo(page.getBeginIndex(), page.getEveryPage());
			//分页结果
			Result result = new Result(page, list);
			if(result.getContent()!=null && result.getContent().size()>0) {
				return new ResultMsg(Status.SUCCESS, "获取宝宝发育变化成功！", result.getContent());
			} else {
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.error("findBaby()", e);
			return new ResultMsg(Status.FAILED, "获取宝宝发育变化失败！");
		}
	}
	
	/**
	 * 新增孕期信息
	 * @version 1.0
	 * @createTime 2016-12-26,下午5:53:02
	 * @updateTime 2016-12-26,下午5:53:02
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param pregnancyInfo
	 * @return
	 */
	@RequestMapping(value="savePrepnancy", method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg savePrepnancy(@RequestBody PregnancyInfo pregnancyInfo) {
		try {
			//新增或者更新
			pregnancyInfoService.saveAndUpdatePrepnancy(pregnancyInfo);
			return new ResultMsg(Status.SUCCESS, "保存孕期信息成功！");
		} catch (Exception e) {
			logger.error("savePrepnancy()", e);
			return new ResultMsg(Status.FAILED, "保存孕期信息失败！");
		}
	}
	
	/**
	 * 孕期详情
	 * @version 1.0
	 * @createTime 2016-12-27,下午3:16:55
	 * @updateTime 2016-12-27,下午3:16:55
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param week 孕周
	 * @return
	 */
	@RequestMapping(value="findPrepnancyDetail", method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg findPrepnancyDetail(@RequestParam("week") int week) {
		try {
			//孕期详情
			PregnancyInfo pregnancyInfo = pregnancyInfoService.findPrepnancyByWeek(week);
			return new ResultMsg(Status.SUCCESS, "获取孕期信息成功！", pregnancyInfo);
		} catch (Exception e) {
			logger.error("findPrepnancyDetail()", e);
			return new ResultMsg(Status.FAILED, "获取孕期信息失败！");
		}
	}
	
	/**
	 * 删除孕期信息
	 * @version 1.0
	 * @createTime 2016-12-27,下午4:50:54
	 * @updateTime 2016-12-27,下午4:50:54
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param week
	 * @return
	 */
	@RequestMapping(value="deletePrepnancy", method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg deletePrepnancy(@RequestParam("week") int week) {
		try {
			pregnancyInfoService.deletePrepnancy(week);
			return new ResultMsg(Status.SUCCESS, "删除孕期信息成功！");
		} catch (Exception e) {
			logger.error("findPrepnancyDetail()", e);
			return new ResultMsg(Status.FAILED, "删除孕期信息失败！");
		}
	}
}
