package com.jumper.angel.sociality.debatepost.mapper;

import java.util.List;

import com.jumper.angel.sociality.debatepost.model.po.DebatepostPO;
import com.jumper.angel.sociality.debatepost.model.po.UserCollectionPO;
/**
 * @ClassName: UserCollectionMapper 
 * @Description: 收藏数据库服务
 * @author caishiming
 * @date 2016年10月24日 下午6:27:07
 */
public interface UserCollectionMapper {
	/**
	 * 删除收藏
	 */
	public int deleteByPrimaryKey(Long collecId);
	
	/**
	 * 收藏
	 */
	public int insertUserCollection(UserCollectionPO record);

	/**
	 * 查询收藏帖子信息
	 */
	public List<DebatepostPO> getUserCollectionLst(String userId);
	
	/**
	 * 查询收藏
	 */
	public UserCollectionPO getUserCollection(UserCollectionPO record); 

}