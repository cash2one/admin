/** 
 * Project Name:jumper-angel-admin 
 * File Name:UserTopicManagerServiceImpl.java 
 * Package Name:com.jumper.angel.sociality.topic.service.impl 
 * Date:2016年12月29日下午6:17:20 
 * Copyright (c) 2016, yangsheng@angeldoctor All Rights Reserved. 
 * 
*/  
package com.jumper.angel.sociality.topic.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.sociality.operationrecord.mapper.OperationRecordMapper;
import com.jumper.angel.sociality.operationrecord.model.po.OperationRecordPO;
import com.jumper.angel.sociality.topic.mapper.TopicMapper;
import com.jumper.angel.sociality.topic.mapper.TopicNoSpeakingMapper;
import com.jumper.angel.sociality.topic.mapper.UserTopicMapper;
import com.jumper.angel.sociality.topic.model.po.UserTopicPO;
import com.jumper.angel.sociality.topic.model.vo.TopicVO;
import com.jumper.angel.sociality.topic.model.vo.UserTopicVO;
import com.jumper.angel.sociality.topic.service.UserTopicManagerService;
import com.jumper.angel.utils.TimeUtils;
@Service
public class UserTopicManagerServiceImpl implements  UserTopicManagerService{

	private final static Logger logger = Logger.getLogger(UserTopicManagerServiceImpl.class);
	@Autowired
	private UserTopicMapper userTopicMapper;
	@Autowired
	private TopicMapper topicMapper;
	@Autowired 
	private OperationRecordMapper operationRecordMapper;
	@Autowired
	private TopicNoSpeakingMapper topicNoSpeakingMapper;
	
	@Override
	public int edit(UserTopicPO userTopicPO, CrmAdmin crmAdmin) {
	//	addOperatorRecord(userTopicPO, crmAdmin);
		return userTopicMapper.update(userTopicPO);
	}

	@Override
	public UserTopicPO findById(Long id) {
		return userTopicMapper.selectById(id);
	}

	@Override
	public List<UserTopicVO> findUserTopicByPageBean(UserTopicVO userTopicVO) {
		long start1 = System.currentTimeMillis();
		// 某话题下的所用成员
		List<UserTopicVO> list = userTopicMapper.selectUserTopicByPageBean(userTopicVO);
		logger.info("selectUserTopicByPageBean某话题下的所用成员耗时------------>"+(System.currentTimeMillis()-start1)+"ms");
		if(list !=null  && list.size() >0){
			long start2 = System.currentTimeMillis();
			for(UserTopicVO vo :list){
				vo.setCreateDateTime(TimeUtils.convertLongToTimestampString("yyyy/MM/dd HH:mm:ss", vo.getCreateTime()));
				// 根据成员ID 和 话题ID  更新最后一次发贴时间
				long start3 = System.currentTimeMillis();
				UserTopicVO v1 = userTopicMapper.updateUserTopicLastSpeaking(vo);
				logger.info("updateUserTopicLastSpeaking更新最后一次发贴时间耗时------------>"+(System.currentTimeMillis()-start3)+"ms");
				if( null != v1){
					vo.setDebatepostTime(v1.getDebatepostTime());
				}
				
				if(null == vo.getDebatepostTime() || vo.getDebatepostTime() == 0){
					vo.setDebatepostDateTime("");
				}else{
					vo.setDebatepostDateTime(TimeUtils.convertLongToTimestampString("yyyy/MM/dd HH:mm:ss", vo.getDebatepostTime()));
				}
			}
			logger.info("总循环耗时------------>"+(System.currentTimeMillis()-start2)+"ms");
		}
		return list;
	}

	@Override
	public int findCountUserTopicByBean(UserTopicVO userTopicVO) {
		return userTopicMapper.selectCountUserTopicByBean(userTopicVO);
	}
	
	
	/**
	 * 话题组成员的操作增加记录
	 * @author yangsheng@angeldoctor 
	 * @param topicPO
	 * @param monitor
	 * @return
	 */
	private int addOperatorRecord(UserTopicVO userTopicVO, CrmAdmin crmAdmin){
		OperationRecordPO operationRecordPO = new OperationRecordPO();
		//根据话题ID获取话题名称
		TopicVO vo = topicMapper.selectTopicById(userTopicVO.getTopicId());
//		UserTopicPO userTopicPO = userTopicMapper.selectById(tempPO.getId());
//		TopicVO topicVO =topicMapper.selectTopicById(userTopicPO.getTopicId());
//		Map<String,Object> userinfoMap = operationRecordMapper.seleclUserInfoById(Integer.parseInt(userTopicPO.getUserId()));
		if(userTopicVO.getIsBlacklist() !=null && userTopicVO.getIsBlacklist() == 0){
		//	String nickName = userTopicVO.getNickName() ==null?"":userinfoMap.get("nick_name").toString();
			operationRecordPO.setRecordContent("对话题组 ["+vo.getTopicName()+"] 的成员 ["+userTopicVO.getNickName()+"] 解除禁言");
			operationRecordPO.setRecordType(8);
			operationRecordPO.setTopicId(userTopicVO.getTopicId());
		}else if(userTopicVO.getIsBlacklist() !=null && userTopicVO.getIsBlacklist() == 1){
	//		String nickName = userinfoMap.get("nick_name") ==null?"":userinfoMap.get("nick_name").toString();
			operationRecordPO.setRecordContent("对话题组 ["+vo.getTopicName()+"] 的成员 ["+userTopicVO.getNickName()+"] 禁言");
			operationRecordPO.setRecordType(7);
			operationRecordPO.setTopicId(userTopicVO.getTopicId());
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
	public Map<String, Object> numCount(long topicId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		//	当前话题总人数
		int currentUserCount = (int)topicId; 
		//	今日当前话题新增人数
		int currentIncreaseCount = 19;
		
		// 对应Mapper 查询数据
		
		currentUserCount = userTopicMapper.currentUserCount(topicId);	//	当前话题总人数
		currentIncreaseCount = userTopicMapper.currentIncreaseCount(topicId);//	今日当前话题新增人数
		
		map.put("currentUserCount", currentUserCount);
		map.put("currentIncreaseCount", currentIncreaseCount);
		return map;
	}

	@Override
	public int findCountUserAllTopicByBean(UserTopicVO userTopicVO) {
		return userTopicMapper.selectCountUserTopicByBean(userTopicVO);
	}

	@Override
	public List<UserTopicVO> findUserAllTopicByPageBean(UserTopicVO userTopicVO) {
		long start1 = System.currentTimeMillis();
		// 某话题下的所用成员
		List<UserTopicVO> list = userTopicMapper.selectUserTopicByPageBean(userTopicVO);
		logger.info("selectUserTopicByPageBean某话题下的所用成员耗时------------>"+(System.currentTimeMillis()-start1)+"ms");
		if(list !=null  && list.size() >0){
			long start2 = System.currentTimeMillis();
			for(UserTopicVO vo :list){
				vo.setCreateDateTime(TimeUtils.convertLongToTimestampString("yyyy/MM/dd HH:mm:ss", vo.getCreateTime()));
				// 根据成员ID 和 话题ID  更新最后一次发贴时间
				long start3 = System.currentTimeMillis();
				UserTopicVO v1 = userTopicMapper.updateUserTopicLastSpeaking(vo);
				logger.info("updateUserTopicLastSpeaking更新最后一次发贴时间耗时------------>"+(System.currentTimeMillis()-start3)+"ms");
				if( null != v1){
					vo.setDebatepostTime(v1.getDebatepostTime());
				}
				
				if(null == vo.getDebatepostTime() || vo.getDebatepostTime() == 0){
					vo.setDebatepostDateTime("");
				}else{
					vo.setDebatepostDateTime(TimeUtils.convertLongToTimestampString("yyyy/MM/dd HH:mm:ss", vo.getDebatepostTime()));
				}
			}
			logger.info("总循环耗时------------>"+(System.currentTimeMillis()-start2)+"ms");
		}
		return list;
	}

	@Override
	public int forbiddenSpeaking(UserTopicVO userTopicVO, CrmAdmin crmAdmin) {
		    String topicIds = userTopicVO.getTopicIds();
		    String[] ids = topicIds.split(",");
		    UserTopicVO vo1 = new UserTopicVO();
		    UserTopicVO vo2 = new UserTopicVO();
		    UserTopicPO po = new UserTopicPO();
		    String userId = userTopicVO.getUserId();
		    for (String topicId : ids) {
		    	vo1.setId(userTopicVO.getId());
		    	vo1.setTopicId(Long.valueOf(topicId));
		    	vo1.setNickName(userTopicVO.getNickName());
		    	vo1.setIsBlacklist(userTopicVO.getIsBlacklist());
		    	addOperatorRecord(vo1, crmAdmin);
		    	//根据用户ID和话题ID查询禁言表
		    	vo2.setTopicId(Long.valueOf(topicId));
		    	vo2.setUserId(userId);
		    	List<UserTopicVO> list = topicNoSpeakingMapper.getTopicIsForbidden(vo2);//根据用户id和话题ID查询用户禁言话题信息
		    	if(list!=null&&list.size()>0){//禁言表有记录
		    		vo2.setIsBlacklist(userTopicVO.getIsBlacklist());
		    		vo2.setCreateDate(new Date());
		    		vo2.setIsDelete(0);
		    		topicNoSpeakingMapper.updateTopicNoSpeaking(vo2);
		    	}else{//禁言表没记录
		    		vo2.setIsBlacklist(userTopicVO.getIsBlacklist());
		    		vo2.setCreateDate(new Date());
		    		vo2.setIsDelete(0);
		    		topicNoSpeakingMapper.addTopicNoSpeaking(vo2);
		    	}
	        }
		    return 1;
	}
}
