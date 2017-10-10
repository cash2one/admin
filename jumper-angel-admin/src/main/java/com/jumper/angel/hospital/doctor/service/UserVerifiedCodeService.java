package com.jumper.angel.hospital.doctor.service;

import java.util.List;
import com.jumper.angel.hospital.doctor.entity.UserVerifiedCode;

/**
 * 验证码
 * @Description TODO
 * @author qinxiaowei
 * @date 2017-2-6
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public interface UserVerifiedCodeService {
	
	/**
	 * 查询用户验证码信息
	 * @version 1.0
	 * @createTime 2017-2-6,下午6:10:24
	 * @updateTime 2017-2-6,下午6:10:24
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param mobile 手机号码
	 * @param beginIndex 页数
	 * @param everyPage 每页显示的记录数
	 * @return
	 */
	public List<UserVerifiedCode> findUserVerifiedCode(String mobile, int beginIndex, int everyPage);
	
	/**
	 * 查询总记录数
	 * @version 1.0
	 * @createTime 2017-2-6,下午6:17:05
	 * @updateTime 2017-2-6,下午6:17:05
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param mobile 手机号码
	 * @return
	 */
	public int findCount(String mobile);
}
