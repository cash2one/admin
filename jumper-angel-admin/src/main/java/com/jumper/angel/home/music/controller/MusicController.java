package com.jumper.angel.home.music.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.jumper.angel.home.music.entity.PregnantMusicInfo;
import com.jumper.angel.home.music.entity.PregnantMusicLabel;
import com.jumper.angel.home.music.service.PregnantMusicInfoService;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.Result;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;
/**
 * 孕期电台
 * @Description TODO
 * @author qinxiaowei
 * @date 2016-12-29
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
@Controller
@RequestMapping("music")
public class MusicController {
	
	private final static Logger logger = Logger.getLogger(MusicController.class);
	
	@Autowired
	private PregnantMusicInfoService pregnantMusicInfoService;
	
	/**
	 * 跳转到播放器页面
	 * @version 1.0
	 * @createTime 2016-12-29,下午5:32:43
	 * @updateTime 2016-12-29,下午5:32:43
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @return
	 */
	@RequestMapping("forwordMusic")
	public ModelAndView forwordMusic() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("music/music_player");
		return mv;
	}
	
	/**
	 * 获取孕期电台信息
	 * @version 1.0
	 * @createTime 2016-12-29,下午6:13:01
	 * @updateTime 2016-12-29,下午6:13:01
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param pageIndex 页数
	 * @param everyPage 每页显示的记录数
	 * @param first 是否是首次加载 true首次 false不是
	 * @return
	 */
	@RequestMapping(value="findMusic", method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg findMusic(@RequestParam("pageIndex") int pageIndex, @RequestParam("everyPage") int everyPage, 
			@RequestParam("first") boolean first) {
		try {
			//电台
			int category = 3;
			//实例化Page对象
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示的记录数
			page.setEveryPage(everyPage);
			//总记录数
			int count = pregnantMusicInfoService.findCount(category);
			//分页工具类，计算分页数
			page = PageUtil.createPage(page, count);
			if(first) {
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			//孕期电台数据
			List<PregnantMusicInfo> list = pregnantMusicInfoService.findPregnantMusicByCategory(category, page.getBeginIndex(), page.getEveryPage());
			//分页结果
			Result result = new Result(page, list);
			if(result.getContent()!=null && result.getContent().size()>0) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("list", result.getContent());
				map.put("num", page.getBeginIndex());
				return new ResultMsg(Status.SUCCESS, "获取孕期电台成功成功！", map);
			} else {
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.error("findMusic()", e);
			return new ResultMsg(Status.FAILED, "获取孕期电台失败！");
		}
	}
	
	/**
	 * 查询电台信息详情
	 * @version 1.0
	 * @createTime 2017-1-4,上午11:50:20
	 * @updateTime 2017-1-4,上午11:50:20
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param id
	 * @return
	 */
	@RequestMapping(value="findPregnantMusicInfoDetail", method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg findPregnantMusicInfoDetail(@RequestParam("id") int id) {
		try {
			//电台信息
			PregnantMusicInfo music = pregnantMusicInfoService.findPregnantMusicInfoDetail(id);
			return new ResultMsg(Status.SUCCESS, "获取电台详情成功！", music);
		} catch (Exception e) {
			logger.error("findPregnantMusicInfoDetail()", e);
			return new ResultMsg(Status.FAILED, "获取电台详情失败！");
		}
	}
	
	/**
	 * 电台置顶
	 * @version 1.0
	 * @createTime 2017-1-11,下午2:11:59
	 * @updateTime 2017-1-11,下午2:11:59
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param id
	 * @return
	 */
	@RequestMapping(value="firstMusic", method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg firstMusic(@RequestParam("id") int id) {
		try {
			//置顶
			pregnantMusicInfoService.firstMusic(id);
			return new ResultMsg(Status.SUCCESS, "置顶成功！");
		} catch (Exception e) {
			logger.error("firstMusic()", e);
			return new ResultMsg(Status.FAILED, "置顶失败！");
		}
	}
	
	/**
	 * 新增孕期电台
	 * @version 1.0
	 * @createTime 2017-1-10,上午9:54:12
	 * @updateTime 2017-1-10,上午9:54:12
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param param
	 * @return
	 */
	@RequestMapping(value="saveAndUpdatePregnantMusicInfo", method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg saveAndUpdatePregnantMusicInfo(@RequestBody PregnantMusicInfo param) {
		try {
			param.setAddTime(new Date());
			pregnantMusicInfoService.saveAndUpdatePregnantMusicInfo(param);
			return new ResultMsg(Status.SUCCESS, "操作成功！");
		} catch (Exception e) {
			logger.error("saveAndUpdatePregnantMusicInfo()", e);
			return new ResultMsg(Status.FAILED, "操作失败！");
		}
	}
	
	/**
	 * 查询所有电台标签
	 * @version 1.0
	 * @createTime 2017-1-7,上午11:55:13
	 * @updateTime 2017-1-7,上午11:55:13
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @return
	 */
	@RequestMapping(value="findLabelAll", method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg findLabelAll() {
		try {
			//修改
			List<PregnantMusicLabel> list = pregnantMusicInfoService.findLabelAll();
			return new ResultMsg(Status.SUCCESS, "查询电台标签成功！", list);
		} catch (Exception e) {
			logger.error("findLabelAll()", e);
			return new ResultMsg(Status.FAILED, "查询电台标签失败！");
		}
	}
	
	/**
	 * 新增标签
	 * @version 1.0
	 * @createTime 2017-1-7,下午3:29:27
	 * @updateTime 2017-1-7,下午3:29:27
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param label
	 * @return
	 */
	@RequestMapping(value="saveLabel", method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg saveLabel(@RequestBody PregnantMusicLabel label) {
		try {
			boolean bool = pregnantMusicInfoService.findLabelByName(label.getLabelName());
			if(bool) {
				return new ResultMsg(Status.FAILED, "标签已存在！");
			}
			//保存
			int id = pregnantMusicInfoService.insertLabel(label);
			return new ResultMsg(Status.SUCCESS, "保存标签成功！", id);
		} catch (Exception e) {
			logger.error("saveLabel()", e);
			return new ResultMsg(Status.FAILED, "保存标签失败！");
		}
	}
}