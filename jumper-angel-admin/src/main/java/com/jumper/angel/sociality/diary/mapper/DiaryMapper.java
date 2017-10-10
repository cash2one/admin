package com.jumper.angel.sociality.diary.mapper;

import java.util.List;

import com.jumper.angel.sociality.diary.model.bo.DiaryBo;
import com.jumper.angel.sociality.diary.model.vo.DiaryVo;

/**
 * 宝妈日记 Mapper
 * 
 * @ClassName: DiaryMapper
 * @author huangzg 2017年1月14日 下午4:28:30
 */
public interface DiaryMapper {

	/**
	 * 查询当前日记总数
	 * 
	 * @author huangzg 2017年1月14日 下午3:03:55
	 * @return
	 * @throws
	 */
	public int getDiaryMaxUser(DiaryVo vo);

	/**
	 * 查询近30新增日记总数
	 * 
	 * @author huangzg 2017年1月14日 下午3:03:55
	 * @return
	 * @throws
	 */
	public int getLatelyDiaryCount();

	/**
	 * 查询日记用户总数
	 * 
	 * @author huangzg 2017年1月14日 下午3:03:55
	 * @return
	 * @throws
	 */
	public int getDiaryUserCount();

	/**
	 * 查询日记总数前五的用户信息
	 * 
	 * @author huangzg 2017年1月14日 下午3:03:55
	 * @return
	 * @throws
	 */
	public List<DiaryBo> getMaxDirayUser();

	/**
	 * 查询最新发布的5条日记
	 * 
	 * @author huangzg 2017年1月14日 下午3:05:17
	 * @return
	 * @throws
	 */
	public List<DiaryBo> getMaxDiray();

	/***
	 * 宝妈日记 列表总数
	 * 
	 * @author huangzg 2017年1月14日 下午3:22:51
	 * @return vo 参数
	 * @throws
	 */
	public int getCount(DiaryVo vo);

	/**
	 * 检索查询宝妈日记数据
	 * 
	 * @author huangzg 2017年1月14日 下午3:26:04
	 * @param vo  参数
	 * @return
	 * @throws
	 */
	public List<DiaryBo> getByKeywords(DiaryVo vo);

	/**
	 * 根据用户ID查询宝妈日记信息
	 * 
	 * @author huangzg 2017年1月14日 下午3:26:04
	 * @param vo 参数
	 * @return
	 * @throws
	 */
	public DiaryBo getDiaryByUserId(DiaryVo vo);
	
	/**
	 * 根据ID查询宝妈日记详情
	 * @author huangzg 2017年1月14日 下午6:15:12  
	 * @param id 数据ID
	 * @return        
	 * @throws
	 */
	public DiaryBo getById(DiaryVo vo);

	/**
	 * 根据用户ID查询最新发表日记时间
	 * @author huangzg 2017年2月5日 上午9:58:56  
	 * @param vo 用户信息
	 * @return        
	 * @throws
	 */
	public DiaryBo selLatestDiary(DiaryVo vo);
	
}
