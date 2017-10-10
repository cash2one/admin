package com.jumper.angel.home.information.service;

import java.util.List;

import com.jumper.angel.home.information.vo.VONewsInformationComments;


/**
 * 评论Service
 * @author gyx
 * @time 2017年1月7日
 */
public interface NewsInformationCommentsService {

	/**
	 * 获取评论列表
	 * @param newsId 文章id
	 * @return
	 */
	List<VONewsInformationComments> findNewsComments(int newsId);

	/**
	 * 删除评论
	 * @param commentId
	 * @return
	 */
	boolean deleteNewsComments(int commentId);
	
}
