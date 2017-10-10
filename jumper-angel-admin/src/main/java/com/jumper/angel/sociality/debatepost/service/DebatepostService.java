/** 
 * Project Name:jumper-angel-admin 
 * File Name:DebatepostService.java 
 * Package Name:com.jumper.angel.sociality.debatepost.service 
 * Date:2016年12月30日下午6:14:09 
 * Copyright (c) 2016, yangsheng@angeldoctor All Rights Reserved. 
 * 
*/  
package com.jumper.angel.sociality.debatepost.service;
import java.util.List;
import java.util.Map;

import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.sociality.debatepost.model.po.DebatepostPO;
import com.jumper.angel.sociality.debatepost.model.vo.DebatepostVO;

public interface DebatepostService {
	
public List<DebatepostVO> findDebatepostByPageBean(DebatepostVO debatepostVO);
	
	public int findCoutDebatepostByBean(DebatepostVO debatepostVO);
	
	public DebatepostVO findById(Long id);
	
	public int edit(DebatepostPO debatepostPO, CrmAdmin crmAdmin);
	
	/**
	 * 
	 * 初始化页面数据
	 * 当前话题帖子管理统计值的初始化
	 * 
	 * @param topicId
	 * @return
	 */
	public Map<String,Object> numCount(long topicId);
	
}
