package com.jumper.angel.sociality.diary.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.sociality.diary.mapper.DiaryMapper;
import com.jumper.angel.sociality.diary.model.bo.DiaryBo;
import com.jumper.angel.sociality.diary.model.vo.DiaryVo;
import com.jumper.angel.sociality.diary.service.DiaryService;
import com.jumper.angel.sociality.util.ListUtil;
import com.jumper.angel.utils.ConfigProUtils;
import com.jumper.angel.utils.StringUtil;
import com.jumper.angel.utils.TimeUtils;


@Service("diaryServiceImpl")
public class DiaryServiceImpl implements DiaryService  {
	
	final static Logger logger = Logger.getLogger(DiaryServiceImpl.class);
	
	private final String filePath = ConfigProUtils.get("file_path");
	
	@Autowired
	DiaryMapper mapper;
	
	/**
	 * 初始化页面数据
	 * @author huangzg 2017年1月14日 下午2:06:42  
	 * @return        
	 * @throws
	 */
	@Override
	public HashMap<String, Object> get() {
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		try {
			/** 查询当前日记总数 不包括已删除 */
			DiaryVo dVo = new DiaryVo();
			dVo.setIsDelete(0);
			int diary = mapper.getDiaryMaxUser(dVo);
			/** 查询近30新增日记总数 */
			int latelyDiaryCount = mapper.getLatelyDiaryCount();
			/** 查询日记用户总数 */
			int userCounts = mapper.getDiaryUserCount();
			/** 查询日记总数前五的用户信息 */
			List<DiaryBo> maxUsers = mapper.getMaxDirayUser();
			List<DiaryBo> userList = new ArrayList<DiaryBo>();
			for (DiaryBo bo : maxUsers){
				DiaryVo vo = new DiaryVo();
				vo.setUserId(bo.getUserId());
				vo.setIsDelete(0);
				int counts = mapper.getDiaryMaxUser(vo);	//用户发布日记总数
				DiaryBo LatestDiaryBo = mapper.selLatestDiary(vo);		// 查询最新发表日记信息
				bo.setCreateTime(TimeUtils.converStringDate(LatestDiaryBo.getAddTime(), 
						TimeUtils.YYYY_MM_DD_HH_MM_SS));  //最新发表日记时间
				bo.setCounts(counts);
				userList.add(bo);
			}
			ListUtil.sortList(userList, "counts", "DESC");
			
			/** 查询最新发布的5条日记 */
			List<DiaryBo> maxDirays = mapper.getMaxDiray();
			List<DiaryBo> dirayList = new ArrayList<DiaryBo>();
			for (DiaryBo bo : maxDirays){
				bo.setCreateTime(TimeUtils.converStringDate(bo.getAddTime(), 
						TimeUtils.YYYY_MM_DD_HH_MM_SS));  //最新发表日记时间
				dirayList.add(bo);
			}
			dataMap.put("diary", diary);	// 查询当前日记总数
			dataMap.put("latelyDiaryCount", latelyDiaryCount);	// 查询近30新增日记总数
			dataMap.put("userCounts", userCounts);	//查询日记用户总数
			dataMap.put("maxUsers", userList);		//查询日记总数前五的用户信息
			dataMap.put("maxDirays", dirayList);	//查询最新发布的5条日记
			return dataMap;
		} catch (Exception e) {
			logger.info("初始化页面数据 msg "+e.getMessage(), e);
		}
		return dataMap;
	}

	/**
	 * 检索宝妈日记总条数
	 * @author huangzg 2017年1月14日 下午2:06:55  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 */
	@Override
	public int getCounts(DiaryVo vo) {
		return mapper.getCount(vo);
	}

	/**
	 * 检索宝妈日记数据
	 * @author huangzg 2017年1月14日 下午2:06:55  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 */
	@Override
	public List<DiaryBo> getByKeywords(DiaryVo vo) {
		List<DiaryBo> dataList = new ArrayList<DiaryBo>();
		try {
			List<DiaryBo> bos = mapper.getByKeywords(vo);
			for (DiaryBo bo : bos){
				bo.setCreateTime(TimeUtils.converStringDate(bo.getAddTime(), 
						TimeUtils.YYYY_MM_DD_HH_MM_SS));
				dataList.add(bo);
			}
			return dataList;
		} catch (Exception e) {
			logger.info("检索宝妈日记数据 msg "+e.getMessage(), e);
		}
		return dataList;
	}

	/**
	 * 根据ID查询宝妈日记详情
	 * @author huangzg 2017年1月14日 下午6:15:12  
	 * @param id 数据ID
	 * @return        
	 * @throws
	 */
	@Override
	public DiaryBo getById(Long id) {
		DiaryVo vo = new DiaryVo();
		if (id > 0){
			vo.setId(id);
		}
		DiaryBo bo = mapper.getById(vo);
		bo.setCreateTime(TimeUtils.converStringDate(bo.getAddTime(), 
					TimeUtils.YYYY_MM_DD_HH_MM_SS));  //最新发表日记时间
		List<String> imgs = new ArrayList<String>();
		if (!StringUtil.isEmpty(bo.getImgUrl())){
			String[] imgArray = bo.getImgUrl().split(";");
			for (int i = 0; i < imgArray.length; i++) {
				imgs.add(filePath + imgArray[i]);
			}
			bo.setImgs(imgs);
		}
		return bo;
	}
	
}