package com.jumper.angel.home.login.mapper;

import com.jumper.angel.home.login.entity.CrmAdmin;

/**
 * 运营后台用户
 * @Description TODO
 * @author qinxiaowei
 * @date 2017-1-12
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public interface CrmAdminMapper {
	
	/**
	 * 查询用户信息
	 * @version 1.0
	 * @createTime 2017-1-12,下午5:06:26
	 * @updateTime 2017-1-12,下午5:06:26
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param username
	 * @return
	 */
	public CrmAdmin findCrmAdmin(String username);
}
