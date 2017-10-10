package com.jumper.angel.home.login.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jumper.angel.home.login.entity.MonitorAdmin;
import com.jumper.angel.hospital.hospital.entity.MonitorAdminRoleKey;

/**
 * 用户登录
 * @Description TODO
 * @author qinxiaowei
 * @date 2017-1-6
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public interface MonitorAdminMapper {
	
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
	public MonitorAdmin findMonitorAdmin(String username);
	
	int insertSelective(MonitorAdmin record);

	public MonitorAdmin selectByUserName(String userName);

	public MonitorAdmin selectByMobile(String mobile);
	/**修改账户**/
	public int updateAccount(@Param("monitorId")Integer monitorId, @Param("userName")String userName, 
			@Param("password")String password, @Param("status")Integer status, @Param("isEnabled")Integer isEnabled);

	/**修改账户状态**/
//	public int updateStatus(@Param("hospitalId")Integer hospitalId, @Param("status")Integer status);
	public int updateIsEnabled(@Param("hospitalId")Integer hospitalId, @Param("isEnabled")Integer isEnabled);

	public List<MonitorAdmin> selectByHospitalId(Integer hospitalId);

	/**添加角色**/
	public void insertRole(MonitorAdminRoleKey roleKey);

//	public MonitorAdmin findMonitorAdmin(String username);


}
