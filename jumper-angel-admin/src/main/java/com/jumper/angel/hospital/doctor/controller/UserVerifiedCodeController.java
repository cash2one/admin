package com.jumper.angel.hospital.doctor.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumper.angel.hospital.doctor.entity.UserVerifiedCode;
import com.jumper.angel.hospital.doctor.service.UserVerifiedCodeService;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.Result;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;
/**
 * 验证码
 * @Description TODO
 * @author qinxiaowei
 * @date 2017-2-6
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
@Controller
@RequestMapping("verified")
public class UserVerifiedCodeController {
	
	private final static Logger logger = Logger.getLogger(UserVerifiedCodeController.class);

	@Autowired
	private UserVerifiedCodeService userVerifiedCodeService;
	
	/**
	 * 跳转到用户验证码信息页面
	 * @version 1.0
	 * @createTime 2017-2-6,下午6:30:34
	 * @updateTime 2017-2-6,下午6:30:34
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @return
	 */
	@RequestMapping("forwordUserVerifiedCode")
	public ModelAndView forwordUserVerifiedCode() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/user_verified_code");
		return mv;
	}
	
	/**
	 * 查询用户验证码信息
	 * @version 1.0
	 * @createTime 2017-2-6,下午6:25:29
	 * @updateTime 2017-2-6,下午6:25:29
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param pageIndex 页数
	 * @param everyPage 每页显示的记录数
	 * @param mobile 手机号码
	 * @param first 是否是首次加载 true首次 false不是
	 * @return
	 */
	@RequestMapping(value="findUserVerifiedCode", method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg findUserVerifiedCode(@RequestParam("pageIndex") int pageIndex, @RequestParam("everyPage") int everyPage, 
			@RequestParam("mobile") String mobile, @RequestParam("first") boolean first) {
		try {
			//实例化Page对象
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示的记录数
			page.setEveryPage(everyPage);
			//总记录数
			int count = userVerifiedCodeService.findCount(mobile);
			//分页工具类，计算分页数
			page = PageUtil.createPage(page, count);
			if(first) {
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			//用户验证码信息
			List<UserVerifiedCode> list = userVerifiedCodeService.findUserVerifiedCode(mobile, page.getBeginIndex(), page.getEveryPage());
			//分页结果
			Result result = new Result(page, list);
			if(result.getContent()!=null && result.getContent().size()>0) {
				return new ResultMsg(Status.SUCCESS, "获取用户验证码信息成功！", result.getContent());
			} else {
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.info("findUserVerifiedCode()", e);
			return new ResultMsg(Status.FAILED, "获取用户验证码信息失败！");
		}
	}
}
