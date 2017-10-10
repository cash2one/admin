/** 
 * 话题组管理业务层
 * Project Name:jumper-angel-admin 
 * File Name:TopicManagerService.java 
 * Package Name:com.jumper.angel.sociality.topic.service.impl 
 * Date:2016年12月27日上午11:30:03 
 * Copyright (c) 2016, yangsheng@angeldoctor All Rights Reserved. 
 * 
*/  
package com.jumper.angel.sociality.topic.service;
import java.util.List;
import java.util.Map;

import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.sociality.topic.model.po.TopicPO;
import com.jumper.angel.sociality.topic.model.vo.TopicVO;

public interface TopicManagerService {
	
	public int countTopic(TopicVO queryBean);
	
	public List<TopicVO> findTopicByPageBean(TopicVO queryBean);
	
	public List<TopicVO> findTopicByBean(TopicVO queryBean);
	/**
	 * 添加话题组
	 * @author yangsheng@angeldoctor 
	 * @param topicPO
	 * @return
	 */
	public int addTopic(TopicPO topicPO, CrmAdmin crmAdmin);
	/**
	 * 更新话题组
	 * @author yangsheng@angeldoctor 
	 * @param topicPO
	 * @return
	 */
	public int editTopic(TopicPO topicPO, CrmAdmin crmAdmin);
	
	public TopicVO findTopic(long topId);
	
	/**
	 * 获取页面统计数据
	 * 话题组总数
	 * 话题组总人数
	 * 今日新增帖子总数
	 * @return
	 */
	public Map<String, Object> numCount();

	/**
	 * 
	 * 话题组置上操作
	 * @Title: climbUp
	 * @param: @param topicPO
	 * @param: @param crmAdmin
	 * @param: @return
	 * @return: int
	 */
	public int climbUp(TopicPO topicPO, CrmAdmin crmAdmin);
}
