package com.jumper.angel.sociality.group.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.sociality.group.model.bo.SocialityGroupBo;
import com.jumper.angel.sociality.group.model.po.SocialityGroupPo;
import com.jumper.angel.sociality.group.model.vo.SocialityGroupVo;
import com.jumper.angel.utils.ResultMsg;
/** 
 * 社交后台  交流圈Service
 * @ClassName: IMGroupService  
 * @author huangzg 2016年12月23日 下午6:04:15   
 */
public interface GroupService {

	/**
	 * @param dataStatus 
	 * 分页检索查询交流圈信息
	 * @author huangzg 2016年12月23日 下午6:05:23  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 */
	public ResultMsg sel(SocialityGroupVo vo, Integer page, Integer rows, boolean first);
	
	/**
	 * 新建交流圈
	 * @author huangzg 2016年12月23日 下午6:20:58  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 */
	public ResultMsg create(SocialityGroupVo vo,HttpServletRequest request);
	
	/**
	 * 编辑交流圈
	 * @author huangzg 2016年12月23日 下午6:21:11  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 */
	public int edit(SocialityGroupPo po, CrmAdmin monitor);
	/**
	 * 编辑交流圈
	 * @author huangzg 2016年12月23日 下午6:21:11  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 * 通过放置一个id来查询来查询编辑里面的数据
	 */
	public SocialityGroupPo getDataById(int id);
	
	/**
	 * 删除交流圈
	 * @author huangzg 2016年12月23日 下午6:21:29  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 */
	public ResultMsg del(SocialityGroupVo vo);
	
	/**
	 * 查询圈子
	 * @param queryBean
	 * @return
	 */
	public List<SocialityGroupBo> findTGroupByBean(SocialityGroupVo vo);
	
	
	/**
	 * 设置交流圈成员管理的标签
	 * @author huangzg 2017年1月12日 下午5:41:43  
	 * @param request
	 * @param groupId 交流圈ID
	 * @param userId 用户ID 
	 * @param accountsType 帐号类型 1：医生 2：患者/用户 3：其他
	 * @param mark 标签	1：孕妈	2：管理员	3：医生	
	 * @return        
	 * @throws
	 */
	public ResultMsg editImGroupUserNameCard(HttpServletRequest request, 
			String groupId, String userId, Integer mark, 
			List<Map<String, Object>> accounts, CrmAdmin monitor);

	//用于转换时间！
	List<SocialityGroupBo> GroupCreateTime(SocialityGroupVo vo);
	
	/**
	 * 初始化页面数据
	 * 交流圈	统计值的初始化
	 * 交流圈总数	除掉禁用
	 * 交流圈总人数	除掉禁用
	 */
	public Map<String,Object> numCount();
	
/*	*//**
	 * 禁言解除禁言
	 * @author Tsl2017年2月8日 下午5:41:43  
	 * @param request
	 * @param groupId 交流圈ID(String groupId, String userId, String chatId,String operation)
	 * @param userId 用户ID 
	 * @param accounts
	 * @param mark 
	 * @return        
	 * @throws
	 *//*
	public ResultMsg gagGroupMember(String groupId, String userId,String chatId,Integer operation);*/
	
	
}
