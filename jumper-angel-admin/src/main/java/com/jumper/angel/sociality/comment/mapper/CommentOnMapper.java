/** 
 * Project Name:jumper-angel-manage 
 * File Name:CommentOnMapper.java 
 * Package Name:com.jumper.angel.sociality.comment.mapper 
 * Date:2017年1月9日上午11:48:48 
 * Copyright (c) 2017, yangsheng@angeldoctor All Rights Reserved. 
 * 
*/  
package com.jumper.angel.sociality.comment.mapper;
import java.util.List;
import com.jumper.angel.sociality.comment.model.po.CommentOnPO;
import com.jumper.angel.sociality.comment.model.vo.CommentOnVO;

public interface CommentOnMapper {

	int updateById(CommentOnPO commentOnPO);
	
	int updateByFid(CommentOnPO commentOnPO);
	
	CommentOnVO selectById(Long id);
	
	List<CommentOnVO> selectByBean(CommentOnVO commentOnVO);
	
	/**
	 * 
	 * 根据评论内容或者用户昵称查询评论 
	 * @Title: getCommentByContentOrNickName
	 * @param: @param commentOnVO
	 * @param: @return
	 * @return: List<CommentOnVO>
	 */
	List<CommentOnVO> getCommentByContentOrNickName(CommentOnVO commentOnVO);
	/**
	 * 
	 * 查询符合条件的评论数量
	 * @Title: countComment
	 * @param: @param commentOnVO
	 * @param: @return
	 * @return: int
	 */
	int countComment(CommentOnVO commentOnVO);
	
	/**
	 * 
	 * 根据帖子DebatepostId修改该帖子下所有评论的状态
	 * @Title: updateByDebatepostId
	 * @param: @param commentOnPO
	 * @param: @return
	 * @return: int
	 */
	int updateByDebatepostId(CommentOnPO commentOnPO);
	
	/**
	 * 
	 * 根据帖子ID查询该帖子下的所有评论和回复
	 * @Title: getCommentByDebatepostId
	 * @param: @param commentOnVO
	 * @param: @return
	 * @return: List<CommentOnVO>
	 */
	List<CommentOnVO> getCommentByDebatepostId(CommentOnVO commentOnVO);

	//查询帖咋下已删除的评论和回复
	List<CommentOnVO> selectDelByBean(CommentOnVO commentOnVO);
}
