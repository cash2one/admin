package com.jumper.angel.sociality.group.mapper;

import java.util.List;

import com.jumper.angel.sociality.group.model.bo.SocialityGroupBo;
import com.jumper.angel.sociality.group.model.po.SocialityGroupPo;
import com.jumper.angel.sociality.group.model.vo.SocialityGroupVo;

/** 
 * @ClassName: IMGroupMapper  
 * @author huangzg 2016年12月23日 下午6:18:19   
 */
public interface GroupMapper {

	/**
	 * 分页检索查询交流圈信息
	 * @author huangzg 2016年12月23日 下午6:05:23  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 */
	public List<SocialityGroupBo> sel(SocialityGroupVo vo);
	
	public List<SocialityGroupBo> selGroupInfoByName(SocialityGroupVo vo);
	
	/**
	 * 新建交流圈
	 * @author huangzg 2016年12月23日 下午6:20:58  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 */
	public int save(SocialityGroupVo vo);
	
	/**
	 * 编辑交流圈
	 * @author huangzg 2016年12月23日 下午6:21:11  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 */
	public int edit(SocialityGroupPo po);
	
	/**
	 * 编辑交流圈
	 * @author huangzg 2016年12月23日 下午6:21:11  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 * 编辑
	 */
	public SocialityGroupPo getDataById(int id);
	
	/**
	 * 删除交流圈
	 * @author huangzg 2016年12月23日 下午6:21:29  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 */
	public int del(SocialityGroupVo vo);
	

	public int delForList(List<Integer> idItem);

	public int getCounts(); 
		
	public List<SocialityGroupBo> selectGroupByBean(SocialityGroupVo vo);
	
	/**
	 * 根据交流圈ID 更新交流圈人数
	 * @param groupId
	 */
	public int updateGroupNumber(List groupIds);
	
	/**
	 * 交流圈总数	除掉禁用
	 * @return
	 */
	public int groupCount();
	
	/**
	 * 交流圈总人数	除掉禁用
	 * @return
	 */
	public int groupUserCount();

	
	
	
}