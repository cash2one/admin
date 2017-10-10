package com.jumper.angel.home.information.mapper;

import java.util.List;

import com.jumper.angel.home.information.vo.VONewsInformationComments;


/**
 * 评论Mapper
 * @Description TODO
 * @author qinxiaowei
 * @date 2016-12-2
 */
public interface NewsInformationCommentsMapper {

	
	/**
	 * 通过新闻ID获取总记录数
	 * @version 1.0
	 * @createTime 2016-12-5,上午10:40:25
	 * @updateTime 2016-12-5,上午10:40:25
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param newsInformationId 新闻ID
	 * @return
	 */
	public int findCount(int newsInformationId);

	/**
	 * 获取文章评论列表
	 * @param newsId 文章id
	 * @return
	 */
	public List<VONewsInformationComments> findNewsComments(int newsId);

	/**
	 * 删除文章评论
	 * @param commentId 评论id
	 */
	public void deleteNewsComments(int commentId);

	/**
	 * 根据文章id删除文章评论
	 * @param newsId 文章id
	 */
	public void deleteNewsCommentsByNewsIds(int newsId);
	
}
