package com.jumper.angel.sociality.diary.service;

import java.util.HashMap;
import java.util.List;

import com.jumper.angel.sociality.diary.model.bo.DiaryBo;
import com.jumper.angel.sociality.diary.model.vo.DiaryVo;

/**
 * 宝妈日记 Service
 * 
 * @ClassName: DiaryService
 * @author huangzg 2017年1月14日 下午1:53:34
 */
public interface DiaryService {

	/**
	 * 初始化页面数据
	 * @author huangzg 2017年1月14日 下午2:06:42  
	 * @return        
	 * @throws
	 */
	public HashMap<String, Object> get();
	
	/**
	 * 检索宝妈日记总条数
	 * @author huangzg 2017年1月14日 下午2:06:55  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 */
	public int getCounts(DiaryVo vo);
	
	/**
	 * 检索宝妈日记数据
	 * @author huangzg 2017年1月14日 下午2:06:55  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 */
	public List<DiaryBo> getByKeywords(DiaryVo vo);
	
	/**
	 * 根据ID查询宝妈日记详情
	 * @author huangzg 2017年1月14日 下午6:15:12  
	 * @param id 数据ID
	 * @return        
	 * @throws
	 */
	public DiaryBo getById(Long id);
	
}