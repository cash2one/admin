/** 
 * Project Name:jumper-angel-manage 
 * File Name:CommentOnServiceImpl.java 
 * Package Name:com.jumper.angel.sociality.comment.service.impl 
 * Date:2017年1月9日下午12:02:30 
 * Copyright (c) 2017, yangsheng@angeldoctor All Rights Reserved. 
 * 
*/  
package com.jumper.angel.sociality.comment.service.impl;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.sociality.comment.mapper.CommentOnMapper;
import com.jumper.angel.sociality.comment.model.po.CommentOnPO;
import com.jumper.angel.sociality.comment.model.vo.CommentOnVO;
import com.jumper.angel.sociality.comment.service.CommentOnService;
import com.jumper.angel.sociality.debatepost.mapper.DebatepostMapper;
import com.jumper.angel.sociality.debatepost.model.vo.DebatepostVO;
import com.jumper.angel.sociality.operationrecord.mapper.OperationRecordMapper;
import com.jumper.angel.sociality.operationrecord.model.po.OperationRecordPO;

@Service
public class CommentOnServiceImpl implements CommentOnService{
	@Autowired
	private CommentOnMapper commentOnMapper;
	@Autowired
	private DebatepostMapper debatepostMapper;
	@Autowired 
	private OperationRecordMapper operationRecordMapper;
	
	@Override
	public CommentOnVO findById(Long id) {
		return commentOnMapper.selectById(id);
	}

	@Override
	public List<CommentOnVO> findByBean(CommentOnVO commentOnVO) {
		return commentOnMapper.selectByBean(commentOnVO);
	}

	@Override
	public int deleteCommentById(CommentOnPO commentOnPO, CrmAdmin crmAdmin) {
		//先删除回复
		CommentOnVO deleteDocomment  = new CommentOnVO();
		deleteDocomment.setFid(commentOnPO.getId());
		deleteDocomment.setStatus(3);
		//删除的回复数
		int delDocommentNum = commentOnMapper.updateByFid(deleteDocomment);
		//删除的评论数
		int delCommentNum = commentOnMapper.updateById(commentOnPO);
		
		CommentOnPO po = commentOnMapper.selectById(commentOnPO.getId());
		DebatepostVO debatepostVO = debatepostMapper.selectById(po.getDebatepostId());
		int commentNum = debatepostVO.getCommentNumber() - (delDocommentNum+delCommentNum);
		//更新帖子的评论
		DebatepostVO updateDebatepostVO = new DebatepostVO();
		updateDebatepostVO.setDebatepostId(debatepostVO.getDebatepostId());
		updateDebatepostVO.setCommentNumber(commentNum);
		debatepostMapper.update(updateDebatepostVO);
		//增加操作记录
		commentOnPO.setContent(po.getContent());
		addOperatorRecord(commentOnPO,debatepostVO, crmAdmin);
		return delCommentNum;
	}

	@Override
	public int deleteDocommentById(CommentOnPO commentOnPO, CrmAdmin crmAdmin) {
		//删除的回复数
		int delDocommentNum = commentOnMapper.updateById(commentOnPO);
		//更新帖子的评论
		CommentOnPO po = commentOnMapper.selectById(commentOnPO.getId());
		DebatepostVO debatepostVO = debatepostMapper.selectById(po.getDebatepostId());
		int commentNum = debatepostVO.getCommentNumber() - delDocommentNum;
		DebatepostVO updateDebatepostVO = new DebatepostVO();
		updateDebatepostVO.setDebatepostId(debatepostVO.getDebatepostId());
		updateDebatepostVO.setCommentNumber(commentNum);
		debatepostMapper.update(updateDebatepostVO);
		//增加操作记录
		commentOnPO.setContent(po.getContent());
		addOperatorRecord(commentOnPO, debatepostVO, crmAdmin);
		return delDocommentNum;
	}
	
	
	/**
	 * 话题组成员的操作增加记录
	 * @author yangsheng@angeldoctor 
	 * @param topicPO
	 * @param monitor
	 * @return
	 */
	private int addOperatorRecord(CommentOnPO commentOnPO,DebatepostVO debatepostVO, CrmAdmin crmAdmin){
		OperationRecordPO operationRecordPO = new OperationRecordPO();
		operationRecordPO.setRecordContent("删除话题 ["+debatepostVO.getDebatepostTitle()+"] 的评论 ["+commentOnPO.getContent()+"]");
		operationRecordPO.setRecordType(14);
		if(crmAdmin ==null){
			operationRecordPO.setRecordUserId("0");
			operationRecordPO.setRecordUserName("admin");
		}else{
			operationRecordPO.setRecordUserId(crmAdmin.getId()==null?"0":crmAdmin.getId()+"");
			operationRecordPO.setRecordUserName(crmAdmin.getName() ==null?"admin":crmAdmin.getName());
		}
		operationRecordPO.setRecordTime(new Date().getTime());
		operationRecordPO.setDebatepostId(commentOnPO.getDebatepostId());
		operationRecordPO.setTopicId(commentOnPO.getTopicId());
		operationRecordPO.setCommentId(commentOnPO.getId());
		return operationRecordMapper.insert(operationRecordPO);
	}

	/**
	 * 
	 * 根据评论内容或者用户昵称查询评论
	 * @Title: getCommentByContentOrNickName
	 * @param commentOnVO
	 * @return
	 */
	@Override
	public List<CommentOnVO> getCommentByContentOrNickName(CommentOnVO commentOnVO) {
		return commentOnMapper.getCommentByContentOrNickName(commentOnVO);
	}

	/**
	 * 查询符合条件的评论数量
	 * @Title: countComment
	 * @param commentOnVO
	 * @return
	 */
	@Override
	public int countComment(CommentOnVO commentOnVO) {
		return commentOnMapper.countComment(commentOnVO);
	}

	@Override
	public List<CommentOnVO> findDelByBean(CommentOnVO commentOnVO) {
		return commentOnMapper.selectDelByBean(commentOnVO);
	} 
}
