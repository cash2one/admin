package com.jumper.angel.home.login.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.home.login.entity.MonitorAdmin;
import com.jumper.angel.home.login.service.MonitorAdminService;
import com.jumper.angel.user.statistics.entity.HospitalInfo;
import com.jumper.angel.user.statistics.service.HospitalInfoService;
import com.jumper.angel.utils.MD5EncryptUtils;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;

/**
 * 用户登录
 * @Description TODO
 * @author qinxiaowei
 * @date 2017-1-6
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
@Controller
@RequestMapping("login")
public class MonitorAdminController {
	
	private final static Logger logger = Logger.getLogger(MonitorAdminController.class);
	
	@Autowired
	private MonitorAdminService monitorAdminService;
	@Autowired
	private HospitalInfoService hospitalInfoService;
	
	/**
	 * 用户登录
	 * @version 1.0
	 * @createTime 2017-1-6,下午4:12:20
	 * @updateTime 2017-1-6,下午4:12:20
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param admin
	 * @return
	 */
	@RequestMapping(value="userLogin", method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg userLogin(@RequestBody MonitorAdmin admin, HttpServletRequest request) {
		try {
			if(StringUtils.isEmpty(admin.getUsername())) {
				return new ResultMsg(Status.FAILED, "用户名不能为空！");
			}
			if(StringUtils.isEmpty(admin.getPassword())) {
				return new ResultMsg(Status.FAILED, "密码失败！");
			}
			//查询用户信息
			CrmAdmin monitor = monitorAdminService.findMonitorAdmin(admin.getUsername());
			if(monitor == null) {
				return new ResultMsg(Status.FAILED, "用户不存在！");
			}
			//密码MD5加密
			String password = MD5EncryptUtils.getMd5Value(admin.getPassword());
			//密码不匹配
			if(!password.equals(monitor.getPassword())) {
				return new ResultMsg(Status.FAILED, "密码错误！");
			}
			//该账号被禁用
			if(monitor.getIsEnabled() == false) {
				return new ResultMsg(Status.FAILED, "该账号被禁用，请联系管理员！");
			}
			//该账号被锁住
			if(monitor.getIsLocked() == true) {
				return new ResultMsg(Status.FAILED, "该账号被锁住，请联系管理员！");
			}
			//把用户信息保存到session中
			request.getSession().setAttribute("user", monitor);
			return new ResultMsg(Status.SUCCESS, "登录成功！");
		} catch (Exception e) {
			logger.error("userLogin()", e);
			return new ResultMsg(Status.FAILED, "登录失败！");
		}
	}
	
	/**
	 * 用户注册
	 */
	@RequestMapping(value="userAdd", method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg userAdd(Integer hospitalId, String userName, String passWord) {
		try {
			
			if(StringUtils.isEmpty(userName)) {
				return new ResultMsg(Status.FAILED, "用户名不能为空！");
			}
			MonitorAdmin monitorAdmin = monitorAdminService.selectByUserName(userName);
			if (monitorAdmin != null) {
				return new ResultMsg(Status.FAILED, "用户名已经存在！");
			}
			if(StringUtils.isEmpty(passWord)) {
				return new ResultMsg(Status.FAILED, "密码失败！");
			}
//			if(StringUtils.isEmpty(name)) {
//				return new ResultMsg(Status.FAILED, "用户名称不能为空！");
//			}
//			if(!"".equals(name)&&null!=name){
//				name = new String(name.getBytes("iso-8859-1"), "utf-8"); 
//			}
//			if(StringUtils.isEmpty(mobile)) {
//				return new ResultMsg(Status.FAILED, "手机号码不能为空！");
//			}
//			MonitorAdmin monitorAdmins = monitorAdminService.selectByMobile(mobile);
//			if (monitorAdmins != null) {
//				return new ResultMsg(Status.FAILED, "手机号码已经存在！");
//			}
//			if(hospitalId == null) {
//				return new ResultMsg(Status.FAILED, "医院ID不能为空！");
//			}
			
			HospitalInfo hospitalInfo = hospitalInfoService.selectByPrimaryKey(hospitalId);
			if (hospitalInfo == null) {
				return new ResultMsg(Status.FAILED, "不存在该医院！");
			}
			List<MonitorAdmin> admins = monitorAdminService.selectByHospitalId(hospitalId);
			if (admins.size() > 0) {
				return new ResultMsg(Status.FAILED, "该医院已经存在管理员账户！");
			}
			MonitorAdmin admin = new MonitorAdmin();
			admin.setAddTime(new Date());
			admin.setLoginDate(new Date());
			admin.setHospitalId(hospitalId);
			admin.setUsername(userName);
			admin.setPassword(MD5EncryptUtils.getMd5Value(passWord));
//			admin.setMobile(mobile);
			admin.setName("admin");
			admin.setIsFather(true);
			admin.setIsHospitalNst(true);
			int msg = monitorAdminService.addMonitorAdmin(admin);
			if (msg == 1) {
				return new ResultMsg(Status.SUCCESS, "注册成功！");
			}
			return new ResultMsg(Status.FAILED, "注册失败！");
		} catch (Exception e) {
			logger.error("userLogin()", e);
			return new ResultMsg(Status.FAILED, "注册失败！");
		}
	}
	
	/**
	 * 注销
	 * @version 1.0
	 * @createTime 2017-1-17,下午5:15:44
	 * @updateTime 2017-1-17,下午5:15:44
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//清除session
		request.getSession().invalidate();
		//跳转到登录页面
		response.getWriter().println("<script>window.parent.location.href='"+request.getContextPath()+"/login.jsp'</script>");
	}
}
