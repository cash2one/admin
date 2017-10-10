package com.jumper.angel.home.login.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.home.login.entity.MonitorAdmin;
import com.jumper.angel.home.login.mapper.CrmAdminMapper;
import com.jumper.angel.home.login.mapper.MonitorAdminMapper;
import com.jumper.angel.home.login.service.MonitorAdminService;
import com.jumper.angel.hospital.hospital.entity.MonitorAdminRoleKey;
import com.jumper.angel.user.statistics.entity.HospitalInfo;
import com.jumper.angel.user.statistics.mapper.HospitalInfoMapper;
import com.jumper.angel.utils.ConfigProUtils;
import com.jumper.angel.utils.HttpPost;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.ReturnMsg;
import com.jumper.angel.utils.json.JsonUtils;
/**
 * 用户登录
 * @Description TODO
 * @author qinxiaowei
 * @date 2017-1-6
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
@Service
public class MonitorAdminServiceImpl implements MonitorAdminService {
	
	@Autowired
	private CrmAdminMapper crmAdminMapper;
	@Autowired
	private MonitorAdminMapper monitorAdminMapper;
	@Autowired
	private HospitalInfoMapper hospitalInfoMapper;

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
	public CrmAdmin findMonitorAdmin(String username) {
		return crmAdminMapper.findCrmAdmin(username);
	}

	@Override
	public int addMonitorAdmin(MonitorAdmin admin) {
		admin.setEmail("admin@jumper.com");
		admin.setMobile("");;
		admin.setIsEnabled(true);
		admin.setIsLocked(false);
		int msg = monitorAdminMapper.insertSelective(admin);
		if (msg == 1) {
			HospitalInfo hospitalInfo = hospitalInfoMapper.selectByPrimaryKey(admin.getHospitalId());
			hospitalInfo.setIsNetwork((byte)1);
			hospitalInfoMapper.updateByPrimaryKeySelective(hospitalInfo);
			MonitorAdmin monitorAdmin = monitorAdminMapper.findMonitorAdmin(admin.getUsername());
			//添加角色
			MonitorAdminRoleKey roleKey  = new MonitorAdminRoleKey();
			roleKey.setAdmins(monitorAdmin.getId());
			roleKey.setRoles(2);
			monitorAdminMapper.insertRole(roleKey);
			
			
			//注册IM信息
			String postUrl = ConfigProUtils.get("COMMON_PROJECT_PATH")+"/accounts/add1?appid=101&openid=yy_"+hospitalInfo.getId()+"&nick="+hospitalInfo.getName()+"&faceUrl="+hospitalInfo.getImgUrl();
			String val = HttpPost.doPost(postUrl, "");
			HashMap<String, Object> param = JsonUtils.toHashMap(val);
			msg =  (Integer)param.get("msg");
			if (msg != 1){
				System.out.println("注册IM信息失败!");
			}
		}
		return msg;
	}

	@Override
	public MonitorAdmin selectByUserName(String userName) {
		return monitorAdminMapper.selectByUserName(userName);
	}

	@Override
	public MonitorAdmin selectByMobile(String mobile) {
		return monitorAdminMapper.selectByMobile(mobile);
	}

	@Override
	public ResultMsg accountManagement(Integer monitorId,Integer hospitalId, String userName, 
			String password, Integer status, Integer isEnabled) {
		try {
//			Integer isEnabled = 0;
//			if (isForbidden == 0) {
//				isEnabled = 1;
//			}
			int msg = monitorAdminMapper.updateAccount(monitorId,userName,password,status,isEnabled);
			if (msg != 1) {
				return new ResultMsg(ReturnMsg.FAIL, "修改账户信息失败!");
			}
//			HospitalInfo hospitalInfo = hospitalInfoMapper.selectByPrimaryKey(hospitalId);
//			if (status == 1) {//如果该医院开通了问诊服务，就将其关闭
//				hospitalInfo.setIsConsultant(0);
//				hospitalInfo.setIsRemote(0);
//				hospitalInfoMapper.updateByPrimaryKeySelective(hospitalInfo);
//			}
//			if (status == 0) {
//				msgs =	monitorAdminMapper.updateStatus(hospitalId,status);
//			}
			if (isEnabled == 0) {
				int msgs =	monitorAdminMapper.updateIsEnabled(hospitalId,isEnabled);
				if (msgs == 0) {
					return new ResultMsg(ReturnMsg.FAIL, "修改账户状态失败!");
				}
			}
			return new ResultMsg(ReturnMsg.SUCCESS, "账号管理成功!");
		} catch (Exception e) {
			return new ResultMsg(ReturnMsg.FAIL, "账号管理异常!");
		}
	}

	@Override
	public List<MonitorAdmin> selectByHospitalId(Integer hospitalId) {
		return monitorAdminMapper.selectByHospitalId(hospitalId);
	}
}
