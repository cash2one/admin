/** 
 * Project Name:jumper-angel-admin 
 * File Name:TopicManagerServiceImpl.java 
 * Package Name:com.jumper.angel.sociality.topic.service.impl 
 * Date:2016年12月27日下午3:44:36 
 * Copyright (c) 2016, yangsheng@angeldoctor All Rights Reserved. 
 * 
*/  
package com.jumper.angel.sociality.topic.service.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.sociality.debatepost.mapper.DebatepostMapper;
import com.jumper.angel.sociality.debatepost.model.vo.DebatepostVO;
import com.jumper.angel.sociality.operationrecord.mapper.OperationRecordMapper;
import com.jumper.angel.sociality.operationrecord.model.po.OperationRecordPO;
import com.jumper.angel.sociality.topic.mapper.TopicMapper;
import com.jumper.angel.sociality.topic.mapper.UserTopicMapper;
import com.jumper.angel.sociality.topic.model.po.TopicPO;
import com.jumper.angel.sociality.topic.model.vo.TopicVO;
import com.jumper.angel.sociality.topic.model.vo.UserTopicVO;
import com.jumper.angel.sociality.topic.service.TopicManagerService;
import com.jumper.angel.utils.TimeUtils;

@Service
public class TopicManagerServiceImpl implements TopicManagerService{
	
	private final static Logger logger = Logger.getLogger(TopicManagerServiceImpl.class);
	
	@Autowired
	private TopicMapper topicMapper;
	
	@Autowired
	private UserTopicMapper userTopicMapper;
	
	@Autowired 
	private OperationRecordMapper operationRecordMapper;
	
	@Autowired
	private DebatepostMapper debatepostMapper;
	
	@Override
	public int countTopic(TopicVO queryBean) {
		return topicMapper.selectCountTopicByBean(queryBean);
	}

	@Override
	public List<TopicVO> findTopicByPageBean(TopicVO queryBean) {
		long start1 = System.currentTimeMillis();
		List<TopicVO> list = topicMapper.selectTopicByPageBean(queryBean);
		logger.info("list.size------------>"+list.size());
		logger.info("selectTopicByPageBean耗时------------>"+(System.currentTimeMillis()-start1)+"ms");
		if(list !=null && list.size() > 0){
			long start2 = System.currentTimeMillis();
			for(TopicVO vo  :list){
				UserTopicVO userTopicVO = new UserTopicVO();
				userTopicVO.setTopicId(vo.getTopicId());
				userTopicVO.setIsDelete(0);
				// 查询话题人员数
				long start3 = System.currentTimeMillis();
				int counts = userTopicMapper.selectCountUserTopicByBean(userTopicVO);
				logger.info("selectCountUserTopicByBean查询话题人员数耗时------------>"+(System.currentTimeMillis()-start3)+"ms");
				DebatepostVO debatepostVO = new DebatepostVO();
				debatepostVO.setTopicId(vo.getTopicId());
				//debatepostVO.setStatus(vo.getDataState() == null?0:vo.getDataState());
				
				// 查询话题帖子数量
				long start4 = System.currentTimeMillis();
				int debatepostCounts = debatepostMapper.selectCoutDebatepostByTopicId(debatepostVO);
				logger.info("selectCoutDebatepostByTopicId查询话题帖子数量耗时------------>"+(System.currentTimeMillis()-start4)+"ms");
				vo.setTotalposts(debatepostCounts);
				vo.setTopicMembership(counts);
				vo.setCreateDateTime(TimeUtils.convertLongToTimestampString("yyyy/MM/dd HH:mm", vo.getCreateTime()));
			}
			logger.info("循环总耗时------------>"+(System.currentTimeMillis()-start2)+"ms");
		}
		logger.info("findTopicByPageBean总耗时------------>"+(System.currentTimeMillis()-start1)+"ms");
		return list;
	}

	@Override
	public List<TopicVO> findTopicByBean(TopicVO queryBean) {
		return topicMapper.selectTopicByBean(queryBean);
	}
	
	@Override
	public int addTopic(TopicPO topicPO, CrmAdmin crmAdmin){
		try{
			topicPO.setTopicMembership(0);
			topicPO.setTotalposts(0);
			topicPO.setIsDelete(0);
			topicPO.setDataState(0);
			topicPO.setCreateTime(new Date().getTime());
			if(crmAdmin == null){
				topicPO.setCreateUserId("admin");
			}else{
				topicPO.setCreateUserId(crmAdmin.getId()+"");
			}
			
			//设置该话题组的位置
			Integer positionId = topicMapper.getLargestPositionId();
			topicPO.setPositionId(positionId+1);
			int i =  topicMapper.insert(topicPO);
		//	topicPO.setPositionId((Integer)topicPO.getTopicId());
		//	topicPO.setPositionId(Integer.valueOf(topicPO.getTopicId().toString()));
		//	topicMapper.update(topicPO);
			//增加操作记录
			this.addOperatorRecord(topicPO, crmAdmin);
			 return i;
		}catch(Exception e){
			logger.error("TopicManagerServiceImpl.addTopic error",e);
		}
		return 0;
	}

	@Override
	public int editTopic(TopicPO topicPO, CrmAdmin crmAdmin) {
		try{
			//增加操作记录
			addOperatorRecord(topicPO, crmAdmin);
			return topicMapper.update(topicPO);
		}catch(Exception e){
			logger.error("TopicManagerServiceImpl.editTopic error",e);
		}
		return 0;
	}

	@Override
	public TopicVO findTopic(long topId) {
		try{
			return topicMapper.selectTopicById(topId);
		}catch(Exception e){
			logger.error("TopicManagerServiceImpl.findTopic error",e);
		}
		return null;
	}
	
	/**
	 * 话题组的操作增加记录
	 * @author yangsheng@angeldoctor 
	 * @param topicPO
	 * @param monitor
	 * @return
	 */
	private int addOperatorRecord(TopicPO topicPO, CrmAdmin crmAdmin){
		OperationRecordPO operationRecordPO = new OperationRecordPO();
		String topicName = topicPO.getTopicName();
		if(topicName ==null || StringUtils.isBlank(topicPO.getTopicName())){
			topicName = topicMapper.selectTopicById(topicPO.getTopicId()).getTopicName();
		}
		if(topicPO.getTopicId() ==null || topicPO.getTopicId()<=0){
			operationRecordPO.setRecordContent("创建话题组 ["+topicName+"]");
			operationRecordPO.setRecordType(1);
		}else if(topicPO.getIsDelete() !=null && topicPO.getIsDelete() ==0){
			operationRecordPO.setRecordContent("启用话题组  ["+topicName+"]");
			operationRecordPO.setRecordType(3);
		}else if(topicPO.getIsDelete() !=null && topicPO.getIsDelete() ==1){
			operationRecordPO.setRecordContent("禁用话题组  ["+topicName+"]");
			operationRecordPO.setRecordType(2);
		}else if(topicPO.getDataState() !=null && topicPO.getDataState() ==0){
			operationRecordPO.setRecordContent("将话题组  [ "+topicName+"] 取消推荐");
			operationRecordPO.setRecordType(6);
		}else if(topicPO.getDataState() !=null && topicPO.getDataState() ==1){
			operationRecordPO.setRecordContent("将话题组  ["+topicName+"] 设为推荐");
			operationRecordPO.setRecordType(5);
		}else{
			operationRecordPO.setRecordContent("编辑话题组  ["+topicName+"]");
			operationRecordPO.setRecordType(4);
		}
		if(crmAdmin ==null){
			operationRecordPO.setRecordUserId("0");
			operationRecordPO.setRecordUserName("admin");
		}else{
			operationRecordPO.setRecordUserId(crmAdmin.getId()==null?"0":crmAdmin.getId()+"");
			operationRecordPO.setRecordUserName(crmAdmin.getName() ==null?"admin":crmAdmin.getName());
		}
		operationRecordPO.setRecordTime(new Date().getTime());
		return operationRecordMapper.insert(operationRecordPO);
	}

	@Override
	public Map<String, Object> numCount() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int topCount = 0; //话题组总数
		int userCount = 0;  //话题组总人数
		int debatepostCount = 0; // 帖子总数
		int increaseCount = 0; //今日新增帖子总数
		
		// 对应Mapper 查询数据
		topCount = topicMapper.topCount(); // 计算话题组总数
		userCount = topicMapper.userCount();  //话题组总人数
		debatepostCount = topicMapper.debatepostCount(); // 帖子总数
		increaseCount = topicMapper.increaseCount();; //今日新增帖子总数
		
		map.put("topCount", topCount);
		map.put("userCount", userCount);
		map.put("increaseCount", increaseCount);
		map.put("debatepostCount", debatepostCount);
		return map;
	}

	@Override
	public int climbUp(TopicPO topicPO, CrmAdmin crmAdmin) {
		//当前话题组位置
		int temp = topicPO.getPositionId();
		//上一个话题组(SELECT * FROM T_IM_TOPIC WHERE POSITION_ID<6 ORDER BY POSITION_ID DESC LIMIT 0,1)
		TopicVO vo = topicMapper.getAboveTopic(topicPO.getPositionId());
		//将当前话题组与上一个话题组置换位置
		topicPO.setPositionId(vo.getPositionId());
		vo.setPositionId(temp);
		topicMapper.update(topicPO);
		topicMapper.update(vo);
		//增加操作记录
		addOperatorRecord(topicPO, crmAdmin);
		addOperatorRecord(vo, crmAdmin);
		return 1;
	}
}
