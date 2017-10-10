package com.jumper.angel.sociality.topic.mapper;

import java.util.List;

import com.jumper.angel.sociality.topic.model.vo.UserTopicVO;
/**
 * 
 * 禁言相关操作
 * @ClassName: TopicNoSpeakingMapper
 * @author liump
 * @date 2017年6月20日 下午7:08:46
 */
public interface TopicNoSpeakingMapper {
		// 根据用户id查询用户禁言话题信息
		List<UserTopicVO> getTopicNoSpeakingByUserId(UserTopicVO vo);
		
		//根据用户id和话题ID查询用户禁言话题信息
		List<UserTopicVO> getTopicIsForbidden(UserTopicVO vo);

		//插入禁言与否记录
		int addTopicNoSpeaking(UserTopicVO vo);
		
		//更新禁言记录
		int updateTopicNoSpeaking(UserTopicVO vo);
}
