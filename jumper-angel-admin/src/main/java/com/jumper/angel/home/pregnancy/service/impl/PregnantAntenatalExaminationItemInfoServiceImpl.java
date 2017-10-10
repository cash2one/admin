package com.jumper.angel.home.pregnancy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.home.pregnancy.entity.PregnantAntenatalExaminationInfo;
import com.jumper.angel.home.pregnancy.entity.PregnantAntenatalExaminationItemInfo;
import com.jumper.angel.home.pregnancy.mapper.PregnantAntenatalExaminationInfoMapper;
import com.jumper.angel.home.pregnancy.mapper.PregnantAntenatalExaminationItemInfoMapper;
import com.jumper.angel.home.pregnancy.service.PregnantAntenatalExaminationInfoService;
import com.jumper.angel.home.pregnancy.service.PregnantAntenatalExaminationItemInfoService;
import com.jumper.angel.home.pregnancy.vo.VOPregAnteExamItemInfo;
import com.jumper.angel.utils.TimeUtils;

/**
 * 孕期产检项目信息ServiceImpl
 * @Description TODO
 * @author fangxilin
 * @date 2016-11-30
 */
@Service
public class PregnantAntenatalExaminationItemInfoServiceImpl implements PregnantAntenatalExaminationItemInfoService {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private PregnantAntenatalExaminationItemInfoMapper itemInfoMapper;
	
	@Autowired
	private PregnantAntenatalExaminationInfoMapper examinationInfoMapper;
	

	@Override
	public List<PregnantAntenatalExaminationItemInfo> findPreItemsByPaeId(
			Integer paeId) {
		return itemInfoMapper.findPreItemsByPaeId(paeId);
	}

	/**
	 * 查找总记录数
	 */
	@Override
	public int findCount(String keywords, int infoId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keywords", keywords);
		map.put("infoId", infoId);
		int count = itemInfoMapper.findCount(map);
		return count;
	}

	/**
	 * 获取产检项目信息
	 */
	@Override
	public List<VOPregAnteExamItemInfo> findPregAnteExamItemInfoList(
			int beginIndex, int everyPage, String keywords, int infoId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginIndex", beginIndex);
		map.put("everyPage", everyPage);
		map.put("keywords", keywords);
		map.put("infoId", infoId);
		List<PregnantAntenatalExaminationItemInfo> list = itemInfoMapper.findPregAnteExamItemInfoList(map);
		if(list != null && list.size() > 0){
			List<VOPregAnteExamItemInfo> voList = getVOPregAnteExamItemInfo(list);
			return voList;
		}
		return null;
	}

	/**
	 * 拼接产检项目信息
	 * @param list
	 * @return
	 */
	private List<VOPregAnteExamItemInfo> getVOPregAnteExamItemInfo(
			List<PregnantAntenatalExaminationItemInfo> list) {
		List<VOPregAnteExamItemInfo> voList = new ArrayList<VOPregAnteExamItemInfo>();
		if(list != null && list.size() > 0){
			for (PregnantAntenatalExaminationItemInfo itemInfo : list) {
				VOPregAnteExamItemInfo voItemInfo = new VOPregAnteExamItemInfo();
				voItemInfo.setId(itemInfo.getId());
				//产检次数id
				voItemInfo.setExaminationInfoId(itemInfo.getExaminationInfoId());
				//产检项目名称
				voItemInfo.setName(itemInfo.getName());
				//产检项目详细内容
				voItemInfo.setContent(itemInfo.getContent());
				//编辑时间
				voItemInfo.setAddTime(TimeUtils.getDateFormat(itemInfo.getAddTime()));
				PregnantAntenatalExaminationInfo examinationInfo = examinationInfoMapper.findPregAnteExamInfoById(itemInfo.getExaminationInfoId());
				if(examinationInfo != null){
					//产检次数
					voItemInfo.setExaminationNumbers(examinationInfo.getExaminationNumbers());
				}
				voList.add(voItemInfo);
			}
		}
		return voList;
	}

	/**
	 * 删除产检项目信息
	 */
	@Override
	public boolean deleteExaminationItemInfo(int examinationItemInfoId) {
		boolean b = false;
		try {
			itemInfoMapper.deleteExaminationItemInfo(examinationItemInfoId);
			b = true;
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 查询产检项目是否存在
	 */
	@Override
	public List<PregnantAntenatalExaminationItemInfo> checkExamNumbers(int itemId, int examId,
			String itemName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("itemId", itemId);
		map.put("examId", examId);
		map.put("itemName", itemName);
		List<PregnantAntenatalExaminationItemInfo> itemInfos = itemInfoMapper.checkExamNumbersByName(map);
		if(itemInfos != null && itemInfos.size() > 0){
			return itemInfos;
		}
		return null;
	}

	/**
	 * 添加或修改产检项目信息
	 */
	@Override
	public boolean addOrEditExamItemInfo(
			PregnantAntenatalExaminationItemInfo itemInfo) {
		boolean b = false;
		if(itemInfo.getId()==0){
			//添加
			try {
				itemInfoMapper.addExaminationItemInfo(itemInfo);
				b = true;
			} catch (Exception e) {
				b = false;
				e.printStackTrace();
			}
		}else{
			//修改
			try {
				itemInfoMapper.editExaminationItemInfo(itemInfo);
				b = true;
			} catch (Exception e) {
				b = false;
				e.printStackTrace();
			}
		}
		return b;
	}
	
	

}
