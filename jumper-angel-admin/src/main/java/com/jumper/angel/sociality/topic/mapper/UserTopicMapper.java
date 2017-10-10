package com.jumper.angel.sociality.topic.mapper;
import java.util.List;
import com.jumper.angel.sociality.topic.model.po.UserTopicPO;
import com.jumper.angel.sociality.topic.model.vo.UserTopicVO;

/**
 * 
* @ClassName: UserTopicMapper 
* @Description: 用户关注
* @author caishiming
* @date 2016年10月22日 下午1:58:41
 */
public interface UserTopicMapper {
	/**
	 * 更新用户加入的话题组
	 * @author yangsheng@angeldoctor 
	 * @param userTopicPO
	 * @return
	 */
	public int update(UserTopicPO userTopicPO);
    /**
	 * 
	 */
    public  UserTopicPO selectById(Long id);
    /**
     * 查询用户加入的话题组,并且分页
     * @author yangsheng@angeldoctor 
     * @param userTopicPO
     * @return
     */
    public List<UserTopicVO> selectUserTopicByPageBean(UserTopicVO userTopicVO);
    /**
     * 统计用户加入的话题组
     * @author yangsheng@angeldoctor 
     * @param userTopicPO
     * @return
     */
    public int selectCountUserTopicByBean(UserTopicVO userTopicVO);
    /**
     * 更新最后一次发言时间
     * @param vo
     * @return
     */
	public UserTopicVO updateUserTopicLastSpeaking(UserTopicVO vo);
	
	/**
	 * 当前话题组总人数  	所有
	 * @param topicId
	 * @return
	 */
	public int currentUserCount(long topicId);
	
	/**
	 * 今日当前话题新增人数   按天统计
	 * @param topicId
	 * @return
	 */
	public int currentIncreaseCount(long topicId);
}