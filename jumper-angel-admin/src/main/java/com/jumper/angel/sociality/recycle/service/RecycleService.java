package com.jumper.angel.sociality.recycle.service;
import java.util.List;

import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.sociality.comment.model.po.CommentOnPO;
import com.jumper.angel.sociality.comment.model.vo.CommentOnVO;
import com.jumper.angel.sociality.debatepost.model.vo.DebatepostVO;

public interface RecycleService {
	
	/**
	 * 
	 * 根据用户昵称或者话题标题查询删除的帖子列表
	 * @Title: getDeletedDebatepost
	 * @param: @param debatepostVO
	 * @param: @return
	 * @return: List<DebatepostVO>
	 */
	public List<DebatepostVO> getDeletedDebatepost(DebatepostVO debatepostVO);

	/**
	 * 
	 * 根据用户昵称或者话题标题查询删除的帖子的数量
	 * @Title: countDeletedDebatepost
	 * @param: @param debatepostVO
	 * @param: @return
	 * @return: int
	 */
	public int countDeletedDebatepost(DebatepostVO debatepostVO);
	
	/**
	 * 
	 * 根据评论内容和用户昵称查询删除的评论 
	 * @Title: getDeletedComment
	 * @param: @param commentOnVO
	 * @param: @return
	 * @return: List<CommentOnVO>
	 */
	public List<CommentOnVO> getDeletedComment(CommentOnVO commentOnVO);
	
	/**
	 * 
	 * 根据评论内容和用户昵称查询删除的评论数量 
	 * @Title: countDeletedComment
	 * @param: @param commentOnVO
	 * @param: @return
	 * @return: int
	 */
	public int countDeletedComment(CommentOnVO commentOnVO);
}
