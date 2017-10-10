package com.jumper.angel.sociality.group.service;

import java.util.List;
import java.util.Map;

import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.sociality.group.model.bo.UserGroupBo;
import com.jumper.angel.sociality.group.model.po.SocialityGroupPo;
import com.jumper.angel.sociality.group.model.po.UserGroupPo;
import com.jumper.angel.sociality.group.model.vo.UserGroupVo;
import com.jumper.angel.sociality.group.model.vo.UserinfoVo;
import com.jumper.angel.utils.page.PagedResult;

/**
 * 交流圈成员Service
 * 
 * @ClassName: UserGroupService  
 * @author huangzg 2016年12月24日 上午10:04:33
 */
public interface UserGroupService {
	
	/**
	 * 分页检索查询交流圈信息
	 * @author huangzg 2016年12月24日 上午11:00:33  
	 * @param vo 参数集
	 * @param page 页码
	 * @param rows 每页条数
	 * @return        
	 * @throws
	 */
	public PagedResult<UserGroupBo> sel(UserGroupVo vo);
	
	/**
	 * 编辑交流圈成员信息
	 * @author huangzg 2016年12月24日 上午11:00:17  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 */
	public int edit(UserGroupVo vo);
	
    /**
     * 用户加入的圈子
     * @author TSL
     * @param userTopicPO
     * @return
     */
	public int selectCountUserGroupByBean(UserGroupVo vo);//2
	
	
    /**
     * 查询用户加入的圈子,并且分页
     * @author TSL 
     * @param userTopicPO
     * @return
     */

    public List<UserGroupVo> findUserGroupByPageBean(UserGroupVo vo);

    
    /**
     * 禁言
     * @param po
     * Tsl
     * @return
     */
	public int update(UserGroupPo po,CrmAdmin monitor);
	/**
	 * 查询userId
	 * @param po
	 * @return
	 */
	public UserGroupPo selectById(int id);
	/**
	 * 查询
	 * user_info里面所有的数据
	 */
	public List<UserinfoVo> selUserInfo(String groupId, String userId);
	
	/**
	 * 根据expected_date_of_confinement值去模糊查询交流圈ID
	 */
	public List<SocialityGroupPo> selUserInfoData(SocialityGroupPo groupName);
	
	
	public int updateBU(UserGroupPo po,CrmAdmin monitor);
	
	/*
	 * 拿到userId
	 * 拿到圈子id 进行禁言   
	 */
	public int updateByBean(UserGroupPo po,CrmAdmin monitor);
	
	/**
	 *  统计 当前交流圈 （交流圈人数，今日交流圈新增人数，今日交流圈发言人数）
	 * @param groupId
	 * @return
	 */
	public Map<String,Object> numCount(String groupId);

	public int selectCountUserGroupByBean2(UserGroupVo vo);

	public List<UserGroupVo> findUserGroupByPageBean2(UserGroupVo vo);
	
}