package com.jumper.angel.sociality.recycle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.sociality.comment.model.vo.CommentOnVO;
import com.jumper.angel.sociality.debatepost.model.vo.DebatepostVO;
import com.jumper.angel.sociality.recycle.mapper.RecycleMapper;
import com.jumper.angel.sociality.recycle.service.RecycleService;
@Service
public class RecycleServiceImpl implements RecycleService{

	@Autowired
	private RecycleMapper recycleMapper;
	
	/**
	 * 根据用户昵称或者话题标题查询删除的帖子列表
	 * @Title: getDeletedDebatepost
	 * @param debatepostVO
	 * @return
	 */
	@Override
	public List<DebatepostVO> getDeletedDebatepost(DebatepostVO debatepostVO) {
		return recycleMapper.getDeletedDebatepost(debatepostVO);
	}

	/**
	 * 根据用户昵称或者话题标题查询删除的帖子的数量
	 * @Title: countDeletedDebatepost
	 * @param debatepostVO
	 * @return
	 */
	@Override
	public int countDeletedDebatepost(DebatepostVO debatepostVO) {
		return recycleMapper.countDeletedDebatepost(debatepostVO);
	}

	/**
	 * 
	 * 根据评论内容和用户昵称查询删除的评论 
	 * @Title: getDeletedComment
	 * @param: @param commentOnVO
	 * @param: @return
	 * @return: List<CommentOnVO>
	 */
	@Override
	public List<CommentOnVO> getDeletedComment(CommentOnVO commentOnVO) {
		return recycleMapper.getDeletedComment(commentOnVO);
	}

	/**
	 * 
	 * 根据评论内容和用户昵称查询删除的评论数量 
	 * @Title: countDeletedComment
	 * @param: @param commentOnVO
	 * @param: @return
	 * @return: int
	 */
	@Override
	public int countDeletedComment(CommentOnVO commentOnVO) {
		return recycleMapper.countDeletedComment(commentOnVO);
	}

}
