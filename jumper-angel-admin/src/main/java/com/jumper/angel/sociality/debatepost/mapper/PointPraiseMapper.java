package com.jumper.angel.sociality.debatepost.mapper;
import com.jumper.angel.sociality.debatepost.model.po.PointPraisePO;

public interface PointPraiseMapper {
	/**
	 * 点赞
	 */
    public int insert(PointPraisePO record);
    /**
	 * 取消赞
	 */
    public int delete(PointPraisePO record);
    /**
	 * 查询是否点赞
	 */
    public int selectByUser(PointPraisePO record);

}