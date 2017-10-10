/** 
 * Project Name:jumper-angel-admin 
 * File Name:DebatepostServiceImpl.java 
 * Package Name:com.jumper.angel.sociality.debatepost.service.impl 
 * Date:2016年12月30日下午6:14:27 
 * Copyright (c) 2016, yangsheng@angeldoctor All Rights Reserved. 
 * 
*/  
package com.jumper.angel.sociality.debatepost.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.sociality.comment.mapper.CommentOnMapper;
import com.jumper.angel.sociality.comment.model.po.CommentOnPO;
import com.jumper.angel.sociality.comment.model.vo.CommentOnVO;
import com.jumper.angel.sociality.debatepost.mapper.DebatepostMapper;
import com.jumper.angel.sociality.debatepost.model.po.DebatepostPO;
import com.jumper.angel.sociality.debatepost.model.vo.DebatepostVO;
import com.jumper.angel.sociality.debatepost.service.DebatepostService;
import com.jumper.angel.sociality.operationrecord.mapper.OperationRecordMapper;
import com.jumper.angel.sociality.operationrecord.model.po.OperationRecordPO;
import com.jumper.angel.utils.ConfigProUtils;
import com.jumper.angel.utils.StringUtil;
import com.jumper.angel.utils.TimeUtils;
import com.jumper.angel.sociality.topic.mapper.TopicMapper;
import com.jumper.angel.sociality.topic.model.po.TopicPO;

@Service
public class DebatepostServiceImpl implements DebatepostService{
	
	@Autowired
	private DebatepostMapper debatepostMapper;
	@Autowired 
	private OperationRecordMapper operationRecordMapper;
	@Autowired
	private TopicMapper  topicMapper;
	@Autowired 
	private CommentOnMapper commentOnMapper;
	
	@Override
	public List<DebatepostVO> findDebatepostByPageBean(DebatepostVO debatepostVO) {
		List<DebatepostVO> list = debatepostMapper.selectDebatepostByPageBean(debatepostVO);
		if(list !=null && list.size() > 0){
			for(DebatepostVO vo : list){
				if(vo.getReadNumber() ==null){
					vo.setReadNumber(0);
				}
				if(vo.getCommentNumber() ==null){
					vo.setCommentNumber(0);
				}
				if(vo.getPointPraise() ==null){
					vo.setPointPraise(0);
				}
				vo.setDebatepostDateTime(TimeUtils.convertLongToTimestampString("yyyy/MM/dd HH:mm", vo.getCreateTime()));
			}
		}
		return list;
	}

	@Override
	public int findCoutDebatepostByBean(DebatepostVO debatepostVO) {
		return debatepostMapper.selectCoutDebatepostByBean(debatepostVO);
	}

	@Override
	public DebatepostVO findById(Long id) {
		DebatepostVO vo = debatepostMapper.selectById(id);
		if (!StringUtil.isEmpty(vo.getImg())){
			String[] imgs = vo.getImg().split(";");
			String imgUrl = "";
			for (int i=0; i<imgs.length; i++){	
				imgUrl += ConfigProUtils.get("BASE_FILE_URL") + imgs[i] + ";";
			}
			vo.setImg(imgUrl);
		}
		return vo;
	}

	@Override
	public int edit(DebatepostPO debatepostPO, CrmAdmin crmAdmin){
		int i = 0;
		DebatepostPO  queryDebatepostPO =  debatepostMapper.selectById(debatepostPO.getDebatepostId());
		List<OperationRecordPO> recordList = new ArrayList<OperationRecordPO>();
		if(debatepostPO.getIsDelete() == 2){//删除
			i = debatepostMapper.update(debatepostPO);
			//如果删除帖子，那么话题组的帖子数相应减1
			TopicPO topicPO =topicMapper.selectTopicById(queryDebatepostPO.getTopicId());
			topicPO.setTotalposts(topicPO.getTotalposts()-1);
			topicMapper.update(topicPO);
			//2017-06-19添加
			//删除帖子的同时，需要把帖子评论记录表T_IM_COMMENT_ON中该帖子的评论和回复全部删除
			CommentOnPO commentOnPO = new CommentOnPO();
			commentOnPO.setDebatepostId(debatepostPO.getDebatepostId());
			commentOnPO.setStatus(3);//将评论设置为删除
			commentOnMapper.updateByDebatepostId(commentOnPO);
			//增加删除帖子的操作记录
			addOperatorRecord(debatepostPO, crmAdmin);
			//同时增加关于删除该帖子下的评论和回复的操作记录
			//先查询出该帖子下的所有评论和回复
			CommentOnVO commentOnVO = new CommentOnVO();
			commentOnVO.setDebatepostId(debatepostPO.getDebatepostId());
			List<CommentOnVO> list = commentOnMapper.getCommentByDebatepostId(commentOnVO);
			if(list!=null&&list.size()>0){
				for(CommentOnVO vo:list){
					OperationRecordPO operationRecordPO = new OperationRecordPO();
					operationRecordPO.setTopicId(queryDebatepostPO.getTopicId());
					operationRecordPO.setDebatepostId(vo.getDebatepostId());
					operationRecordPO.setCommentId(vo.getId());
					operationRecordPO.setRecordType(14);//删除评论
					operationRecordPO.setRecordContent("删除话题 ["+queryDebatepostPO.getDebatepostTitle()+"] 的评论 ["+vo.getContent()+"]");
					operationRecordPO.setRecordTime(new Date().getTime());
					if(crmAdmin ==null){
						operationRecordPO.setRecordUserId("0");
						operationRecordPO.setRecordUserName("admin");
					}else{
						operationRecordPO.setRecordUserId(crmAdmin.getId()==null?"0":crmAdmin.getId()+"");
						operationRecordPO.setRecordUserName(crmAdmin.getUsername() ==null?"admin":crmAdmin.getUsername());
					}
					recordList.add(operationRecordPO);
				}
				addOperatorRecordByBatch(recordList);
			}
		}else if(debatepostPO.getIsDelete() == 1){//隐藏
			i = debatepostMapper.update(debatepostPO);
			//如果隐藏帖子，同时隐藏帖子下所有的评论和回复
			CommentOnPO commentOnPO = new CommentOnPO();
			commentOnPO.setDebatepostId(debatepostPO.getDebatepostId());
			commentOnPO.setStatus(2);//将评论设置为隐藏
			commentOnMapper.updateByDebatepostId(commentOnPO);
			//增加隐藏帖子的操作记录
			addOperatorRecord(debatepostPO, crmAdmin);
			//同时增加关于隐藏该帖子下的评论和回复的操作记录
			//先查询出该帖子下的所有评论和回复
			CommentOnVO commentOnVO = new CommentOnVO();
			commentOnVO.setDebatepostId(debatepostPO.getDebatepostId());
			List<CommentOnVO> list = commentOnMapper.getCommentByDebatepostId(commentOnVO);
	//		List<OperationRecordPO> recordList = null;
			if(list!=null&&list.size()>0){
				for(CommentOnVO vo:list){
					OperationRecordPO operationRecordPO = new OperationRecordPO();
					operationRecordPO.setTopicId(queryDebatepostPO.getTopicId());
					operationRecordPO.setDebatepostId(vo.getDebatepostId());
					operationRecordPO.setCommentId(vo.getId());
					operationRecordPO.setRecordType(30);//隐藏评论
					operationRecordPO.setRecordContent("隐藏话题 ["+queryDebatepostPO.getDebatepostTitle()+"] 的评论 ["+vo.getContent()+"]");
					operationRecordPO.setRecordTime(new Date().getTime());
					if(crmAdmin ==null){
						operationRecordPO.setRecordUserId("0");
						operationRecordPO.setRecordUserName("admin");
					}else{
						operationRecordPO.setRecordUserId(crmAdmin.getId()==null?"0":crmAdmin.getId()+"");
						operationRecordPO.setRecordUserName(crmAdmin.getUsername() ==null?"admin":crmAdmin.getUsername());
					}
					recordList.add(operationRecordPO);
				}
				addOperatorRecordByBatch(recordList);
			}
		}else if(debatepostPO.getIsDelete() == 0){//恢复显示
			i = debatepostMapper.update(debatepostPO);
			//如果恢复显示帖子，同时恢复显示帖子下所有的评论和回复
			CommentOnPO commentOnPO = new CommentOnPO();
			commentOnPO.setDebatepostId(debatepostPO.getDebatepostId());
			commentOnPO.setStatus(1);//将评论设置为正常
			commentOnMapper.updateByDebatepostId(commentOnPO);
			//增加恢复显示帖子的操作记录
			addOperatorRecord(debatepostPO, crmAdmin);
			//同时增加关于恢复显示该帖子下的评论和回复的操作记录
			//先查询出该帖子下的所有评论和回复
			CommentOnVO commentOnVO = new CommentOnVO();
			commentOnVO.setDebatepostId(debatepostPO.getDebatepostId());
			List<CommentOnVO> list = commentOnMapper.getCommentByDebatepostId(commentOnVO);
	//		List<OperationRecordPO> recordList = null;
			if(list!=null&&list.size()>0){
				for(CommentOnVO vo:list){
					OperationRecordPO operationRecordPO = new OperationRecordPO();
					operationRecordPO.setTopicId(queryDebatepostPO.getTopicId());
					operationRecordPO.setDebatepostId(vo.getDebatepostId());
					operationRecordPO.setCommentId(vo.getId());
					operationRecordPO.setRecordType(31);//恢复显示评论
					operationRecordPO.setRecordContent("恢复显示话题 ["+queryDebatepostPO.getDebatepostTitle()+"] 的评论 ["+vo.getContent()+"]");
					operationRecordPO.setRecordTime(new Date().getTime());
					if(crmAdmin ==null){
						operationRecordPO.setRecordUserId("0");
						operationRecordPO.setRecordUserName("admin");
					}else{
						operationRecordPO.setRecordUserId(crmAdmin.getId()==null?"0":crmAdmin.getId()+"");
						operationRecordPO.setRecordUserName(crmAdmin.getUsername() ==null?"admin":crmAdmin.getUsername());
					}
					recordList.add(operationRecordPO);
				}
				addOperatorRecordByBatch(recordList);
			}
		}
		return i;
	}
	
	/**
	 * 评论和回复的批量操作记录
	 */
	public int  addOperatorRecordByBatch(List<OperationRecordPO> list) {
		int i=20;
		int a =20;
		int re=0;
		if(list != null && list.size()>i){
			for(;i<list.size();i=i+a){
				List<OperationRecordPO> list1 = list.subList(i-a, i);
				if(list1 != null && list1.size()>0){
					 re = this.addEntityBatch(list1);
				}
			}
		}
		if(list != null && list.size()>0){
			List<OperationRecordPO> list2 = list.subList(i-a, list.size());
			if(list2 != null && list2.size()>0){
				 re = this.addEntityBatch(list2);
			}
		}
		if(re<=0){
			throw new RuntimeException();
		}
		return 1;
	}

	/**
	 * 
	 * 批量插入操作记录
	 * @Title: addEntityBatch
	 * @param: @param list
	 * @param: @return
	 * @return: int
	 */
	public int addEntityBatch(List<OperationRecordPO> list) {
		int i = operationRecordMapper.addEntityBatch(list);
		return i;
	}

	/**
	 * 帖子的操作增加记录
	 * @author yangsheng@angeldoctor 
	 * @param topicPO
	 * @param monitor
	 * @return
	 */
	private int addOperatorRecord(DebatepostPO tempPO, CrmAdmin crmAdmin){
		DebatepostPO debatepostPO = debatepostMapper.selectById(tempPO.getDebatepostId());
		OperationRecordPO operationRecordPO = new OperationRecordPO();
		if(tempPO.getIsDelete() !=null && tempPO.getIsDelete() ==2){
			operationRecordPO.setRecordContent("删除帖子id  ["+debatepostPO.getDebatepostId()+"]" + "话题id [" + debatepostPO.getTopicId() +"]");
			operationRecordPO.setRecordType(9);
			operationRecordPO.setTopicId(debatepostPO.getTopicId());
			operationRecordPO.setDebatepostId(debatepostPO.getDebatepostId());
		}else if(tempPO.getIsDelete() !=null && tempPO.getIsDelete() ==1){
			operationRecordPO.setRecordContent("隐藏帖子 id ["+debatepostPO.getDebatepostTitle()+"]" + "话题id [" + debatepostPO.getTopicId() +"]");
			operationRecordPO.setRecordType(10);
			operationRecordPO.setTopicId(debatepostPO.getTopicId());
			operationRecordPO.setDebatepostId(debatepostPO.getDebatepostId());
		}else if(tempPO.getIsDelete() !=null && tempPO.getIsDelete() ==0){
			operationRecordPO.setRecordContent("恢复显示帖子 id  ["+debatepostPO.getDebatepostTitle()+"]" + "话题id [" + debatepostPO.getTopicId() +"]");
			operationRecordPO.setRecordType(11);
			operationRecordPO.setTopicId(debatepostPO.getTopicId());
			operationRecordPO.setDebatepostId(debatepostPO.getDebatepostId());
		}else if(tempPO.getWhetherPopular() !=null && tempPO.getWhetherPopular() ==1){
			operationRecordPO.setRecordContent("推广帖子  id ["+debatepostPO.getDebatepostTitle()+"]" + "话题id [" + debatepostPO.getTopicId() +"]");
			operationRecordPO.setRecordType(12);
			operationRecordPO.setTopicId(debatepostPO.getTopicId());
			operationRecordPO.setDebatepostId(debatepostPO.getDebatepostId());
		}else if(tempPO.getWhetherPopular() !=null && tempPO.getWhetherPopular() ==0){
			operationRecordPO.setRecordContent("取消推广帖子  id ["+debatepostPO.getDebatepostTitle()+"]" + "话题id [" + debatepostPO.getTopicId() +"]");
			operationRecordPO.setRecordType(13);
			operationRecordPO.setTopicId(debatepostPO.getTopicId());
			operationRecordPO.setDebatepostId(debatepostPO.getDebatepostId());
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
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		
		int currentTopDebatePostCount = 0;				//当前话题帖子总数
		int currentTopIncreaseDebatePostCount = 0;	//今日话题新增帖子数
		int currentTopDebatePostComCount = 0;			//今日话题帖子评论数
		
		currentTopDebatePostCount = debatepostMapper.currentTopDebatePostCount(topicId);				//当前话题帖子总数
		currentTopIncreaseDebatePostCount = debatepostMapper.currentTopIncreaseDebatePostCount(topicId);	//今日当前话题新增帖子数
		currentTopDebatePostComCount = debatepostMapper.currentTopDebatePostComCount(topicId);			//今日当前话题帖子评论数
		
		
		
		map.put("currentTopDebatePostCount", currentTopDebatePostCount);
		map.put("currentTopIncreaseDebatePostCount", currentTopIncreaseDebatePostCount);
		map.put("currentTopDebatePostComCount", currentTopDebatePostComCount);
		return map;
	} 
}
