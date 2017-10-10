package com.jumper.angel.sociality.debatepost.mapper;

import java.util.List;
import com.jumper.angel.sociality.debatepost.model.po.DebatepostPO;
import com.jumper.angel.sociality.debatepost.model.vo.DebatepostVO;
/**
 * 
 * @ClassName: DebatepostMapper 
 * @Description: 帖子
 * @author caishiming
 * @date 2016年10月24日 下午3:32:20
 */
public interface DebatepostMapper {
	
	
	public List<DebatepostVO> selectDebatepostByPageBean(DebatepostVO debatepostVO);
	
	public int selectCoutDebatepostByBean(DebatepostVO debatepostVO);
	
	/**
	 * 查询小组管理话题列表
	 */
	public int selectCoutDebatepostByTopicId(DebatepostVO debatepostVO);
	
	public DebatepostVO selectById(Long id);
	
	public int update(DebatepostPO debatepostPO);
	
	public DebatepostVO selDebatepostInfo(DebatepostVO vo);
	
	/**
	 * 当前话题帖子总数
	 * @param topicId
	 * @return
	 */
	public int currentTopDebatePostCount(long topicId);
	
	/**
	 * 今日当前话题新增帖子数
	 * @param topicId
	 * @return
	 */
	
	public int currentTopIncreaseDebatePostCount(long topicId);
	
	/**
	 * 今日当前话题帖子评论数
	 * @param topicId
	 * @return
	 */
	public int currentTopDebatePostComCount(long topicId);
	
}