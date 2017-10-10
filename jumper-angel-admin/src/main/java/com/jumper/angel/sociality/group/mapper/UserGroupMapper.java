package com.jumper.angel.sociality.group.mapper;

import java.util.List;
import com.jumper.angel.sociality.group.model.bo.UserGroupBo;
import com.jumper.angel.sociality.group.model.po.SocialityGroupPo;
import com.jumper.angel.sociality.group.model.po.UserGroupPo;
import com.jumper.angel.sociality.group.model.vo.UserGroupVo;
import com.jumper.angel.sociality.group.model.vo.UserinfoVo;

/** 
 * @ClassName: UserGroupMapper  
 * @author huangzg 2016年12月24日 上午10:08:02   
 */
public interface UserGroupMapper {

	/**
	 * 分页检索查询交流圈信息
	 * @author huangzg 2016年12月24日 上午11:00:33  
	 * @param vo 参数集
	 * @param page 页码
	 * @param rows 每页条数
	 * @return        
	 * @throws
	 */
	public List<UserGroupBo> sel(UserGroupVo vo);
	/**
	 * 编辑交流圈成员信息
	 * @author huangzg 2016年12月24日 上午11:00:17  
	 * @param vo
	 * @return        
	 * @throws
	 */
	public int edit(UserGroupVo vo);
	
    public int selectCountUserGroupByBean(UserGroupVo vo);//4
    /**
     * 查询用户加入的话题组,并且分页
     * @author yangsheng@angeldoctor 
     * @param userTopicPO
     * @return
     */
    public List<UserGroupVo> selectUserGroupByPageBean(UserGroupVo vo);
    /**
     * 更新用户加入的交流圈
     * @author yangsheng@angeldoctor 
     * @param vo
     * @return
     */
    public int updateByBean(UserGroupPo userGroupPo);
    
    
    /***
     * jin yan 
     * @author TSL
     * @param userTopicPO
     * @return
     */
	public int updates(UserGroupPo po );
	
	
	/**
	 * 更新交流圈信息
	 * @param po
	 * @return
	 */
	public int updateBU(UserGroupPo po);
	
	
	/**
	 * 获取到用户id
	 * @param userId
	 * @return
	 */
	public UserGroupPo selectById(int id);
	
	
	/**
	 * 查询到UserInfo所有的数据
	 */
	public List<UserinfoVo> selUserinfo(UserGroupPo userGroupPo);
	
	
	public List<SocialityGroupPo> selUserInfovague(SocialityGroupPo groupName);
	
	public int updateSpeakingTime(UserGroupPo userGroupPo);

	
	public int add(UserGroupPo userGroupPo);
	
	/**
	 * 当前交流圈人数
	 * @param groupId
	 * @return
	 */
	public int currentGroupCount(String groupId);
	
	/**
	 * 今日当前交流圈新增人数
	 * @param groupId
	 * @return
	 */
	public int currentGroupIncreaseCount(String groupId);
	
	/**
	 * 今日当前交流圈发言人数
	 * @param groupId
	 * @return
	 */
	public int currentGroupSpeakCount(String groupId);
	
	/** 
	 * 批量更新
	 * @param userGroupVos
	 */
	public int updateSpeakingTimes(List<UserGroupVo> userGroupVos);
	
	/**
	 * 查询user_extra_info表current_identity字段
	 * @Title: getIdentity
	 * @param: @param userId
	 * @param: @return
	 * @return: int
	 */
	public int getIdentity(Integer userId);
	
	public int selectCountUserGroupByBean2(UserGroupVo vo);
	
	public List<UserGroupVo> selectUserGroupByPageBean2(UserGroupVo vo);
}
