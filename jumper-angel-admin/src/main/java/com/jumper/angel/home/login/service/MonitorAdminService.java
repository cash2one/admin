package com.jumper.angel.home.login.service;

import java.util.List;

import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.home.login.entity.MonitorAdmin;
import com.jumper.angel.utils.ResultMsg;

/**
 * 用户登录
 * @Description TODO
 * @author qinxiaowei
 * @date 2017-1-6
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public interface MonitorAdminService {
	
	/**
	 * 查询用户信息
	 * @version 1.0
	 * @createTime 2017-1-6,下午3:59:10
	 * @updateTime 2017-1-6,下午3:59:10
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param username
	 * @return
	 */
	public CrmAdmin findMonitorAdmin(String username);

	/**注册医院管理员**/
	public int addMonitorAdmin(MonitorAdmin admin);

	public MonitorAdmin selectByUserName(String userName);

	public MonitorAdmin selectByMobile(String mobile);
	/**账号管理**/
	public ResultMsg accountManagement(Integer monitorId, Integer hospitalId, String userName, String password,
			Integer status, Integer isEnabled);

	public List<MonitorAdmin> selectByHospitalId(Integer hospitalId);
}
