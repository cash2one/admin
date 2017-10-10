/** 
 * Project Name:jumper-angel-admin 
 * File Name:UserTopicManagerService.java 
 * Package Name:com.jumper.angel.sociality.topic.service 
 * Date:2016年12月29日下午6:16:15 
 * Copyright (c) 2016, yangsheng@angeldoctor All Rights Reserved. 
 * 
*/  
package com.jumper.angel.sociality.topic.service;
import java.util.List;
import java.util.Map;

import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.sociality.topic.model.po.UserTopicPO;
import com.jumper.angel.sociality.topic.model.vo.UserTopicVO;

public interface UserTopicManagerService {
	
	/**
	 * 更新用户加入的话题组
	 * @author yangsheng@angeldoctor 
	 * @param userTopicPO
	 * @return
	 */
	public int edit(UserTopicPO userTopicPO, CrmAdmin crmAdmin);
    /**
	 * 
	 */
    public  UserTopicPO findById(Long id);
    /**
     * 查询用户加入的话题组,并且分页
     * @author yangsheng@angeldoctor 
     * @param userTopicPO
     * @return
     */
    public List<UserTopicVO> findUserTopicByPageBean(UserTopicVO userTopicVO);
    /**
     * 统计用户加入的话题组
     * @author yangsheng@angeldoctor 
     * @param userTopicPO
     * @return
     */
    public int findCountUserTopicByBean(UserTopicVO userTopicVO);
	
    
    /**
     * 初始化页面数据
	 * 成员管理统计值的初始化
     * @return
     */
    public Map<String,Object> numCount(long topicId);
    
    /**
     * 
     * 没选择话题组的时候全局查询，该条件下的总数量
     * @Title: findCountUserAllTopicByBean
     * @param: @param userTopicVO
     * @param: @return
     * @return: int
     */
	public int findCountUserAllTopicByBean(UserTopicVO userTopicVO);
	
	/**
	 * 
	 * 没选择话题组的时候全局查询，该条件下的结果
	 * @Title: findUserAllTopicByPageBean
	 * @param: @param userTopicVO
	 * @param: @return
	 * @return: List<UserTopicVO>
	 */
	public List<UserTopicVO> findUserAllTopicByPageBean(UserTopicVO userTopicVO);
	
	//启用或禁用用户加入的话题
	public int forbiddenSpeaking(UserTopicVO userTopicVO, CrmAdmin crmAdmin);
}
