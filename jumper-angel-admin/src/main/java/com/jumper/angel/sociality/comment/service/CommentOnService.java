/** 
 * Project Name:jumper-angel-manage 
 * File Name:CommentOnService.java 
 * Package Name:com.jumper.angel.sociality.comment.service 
 * Date:2017年1月9日上午11:48:25 
 * Copyright (c) 2017, yangsheng@angeldoctor All Rights Reserved. 
 * 
*/  
package com.jumper.angel.sociality.comment.service;
import java.util.List;

import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.sociality.comment.model.po.CommentOnPO;
import com.jumper.angel.sociality.comment.model.vo.CommentOnVO;

public interface CommentOnService {
	//删除评论
	public int deleteCommentById(CommentOnPO commentOnPO, CrmAdmin crmAdmin );
	//删除回复
	public int deleteDocommentById(CommentOnPO commentOnPO, CrmAdmin crmAdmin );
	//根据ID查询评论
	public CommentOnVO findById(Long id);
	//更新
	public List<CommentOnVO> findByBean(CommentOnVO commentOnVO);
	//查询符合条件的评论数量
	public int countComment(CommentOnVO commentOnVO);
	//根据评论内容或者用户昵称查询评论
	public List<CommentOnVO> getCommentByContentOrNickName(CommentOnVO commentOnVO);
	//查询已删除的评论回复
	public List<CommentOnVO> findDelByBean(CommentOnVO queryVO);
	
}
