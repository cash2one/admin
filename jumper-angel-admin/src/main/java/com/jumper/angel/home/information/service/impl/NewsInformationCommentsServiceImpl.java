package com.jumper.angel.home.information.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumper.angel.home.information.mapper.NewsCommentPraiseMapper;
import com.jumper.angel.home.information.mapper.NewsInformationCommentsMapper;
import com.jumper.angel.home.information.service.NewsInformationCommentsService;
import com.jumper.angel.home.information.vo.VONewsInformationComments;
/**
 * 评论ServiceImpl
 * @author gyx
 * @time 2017年1月7日
 */
@Service
@Transactional
public class NewsInformationCommentsServiceImpl implements NewsInformationCommentsService {
	
	@Autowired
	private NewsInformationCommentsMapper newsInformationCommentsMapper;
	
	@Autowired
	private NewsCommentPraiseMapper newsCommentPraiseMapper;

	/**
	 * 获取文章评论列表
	 */
	@Override
	public List<VONewsInformationComments> findNewsComments(int newsId) {
		List<VONewsInformationComments> commentsList = newsInformationCommentsMapper.findNewsComments(newsId);
		if(commentsList != null && commentsList.size() > 0){
			return commentsList;
		}
		return null;
	}

	/**
	 * 删除文章评论
	 */
	@Override
	public boolean deleteNewsComments(int commentId) {
		boolean b = false;
		try {
			//删除评论
			newsInformationCommentsMapper.deleteNewsComments(commentId);
			//删除评论点赞
			newsCommentPraiseMapper.deleteNewsCommentPraise(commentId);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	
}
