package com.jumper.angel.sociality.topic.mapper;
import java.util.List;
import com.jumper.angel.sociality.topic.model.po.TopicPO;
import com.jumper.angel.sociality.topic.model.vo.TopicVO;

public interface TopicMapper {
	
    /**
     * 添加话题组
     */
   public int insert(TopicPO record);
   
   public int update(TopicPO record);
   
   int deleteByPrimaryKey(Long topicId);
   
   public int selectCountTopicByBean(TopicVO queryBean);
	
   public List<TopicVO> selectTopicByPageBean(TopicVO queryBean);
	
   public List<TopicVO> selectTopicByBean(TopicVO queryBean);
   
   public TopicVO selectTopicById(Long topId);

   public int topCount();

   public int userCount();
   
   public int debatepostCount();
   
   public int increaseCount();

   /**
    * 
    * 获取该话题组上面的那一个话题组
    * @Title: getAboveTopic
    * @param: @param positionId
    * @param: @return
    * @return: TopicVO
    */
   public TopicVO getAboveTopic(Integer positionId);

   /**
    * 
    * 获取最大的话题组位置ID
    * @Title: getLargestPositionId
    * @param: @return
    * @return: Integer
    */
   public Integer getLargestPositionId();
   
}