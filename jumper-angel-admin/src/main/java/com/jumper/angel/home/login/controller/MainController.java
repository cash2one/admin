package com.jumper.angel.home.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * 主页面
 * @Description TODO
 * @author qinxiaowei
 * @date 2017-1-13
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
@Controller
@RequestMapping("main")
public class MainController {

	/**
	 * 跳转主页面
	 * @version 1.0
	 * @createTime 2016-12-29,下午5:32:43
	 * @updateTime 2016-12-29,下午5:32:43
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @return
	 */
	@RequestMapping("main")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("common/main");
		return mv;
	}
	
	/**
	 * 左边菜单
	 * @version 1.0
	 * @createTime 2017-1-13,下午6:55:02
	 * @updateTime 2017-1-13,下午6:55:02
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @return
	 */
	@RequestMapping("left")
	public ModelAndView left() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("common/left");
		return mv;
	}
	
	/**
	 * 头部菜单
	 * @version 1.0
	 * @createTime 2017-1-13,下午6:55:20
	 * @updateTime 2017-1-13,下午6:55:20
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @return
	 */
	@RequestMapping("top")
	public ModelAndView top() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("common/top");
		return mv;
	}
}
