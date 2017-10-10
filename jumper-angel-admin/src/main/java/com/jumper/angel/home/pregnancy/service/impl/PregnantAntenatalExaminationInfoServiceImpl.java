package com.jumper.angel.home.pregnancy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumper.angel.home.pregnancy.entity.PregnantAntenatalExaminationInfo;
import com.jumper.angel.home.pregnancy.mapper.PregnantAntenatalExaminationInfoMapper;
import com.jumper.angel.home.pregnancy.service.PregnantAntenatalExaminationInfoService;
/**
 * 孕期产检信息ServiceImpl
 * @author gyx
 *
 */
@Service
@Transactional
public class PregnantAntenatalExaminationInfoServiceImpl implements
		PregnantAntenatalExaminationInfoService {
	@Autowired
	private PregnantAntenatalExaminationInfoMapper pregnantAntenatalExaminationInfoMapper;
	
	/**
	 * 根据孕周获取孕期产检信息
	 */
	@Override
	public PregnantAntenatalExaminationInfo findPrenatalRemind(int pregnantWeek) {
		PregnantAntenatalExaminationInfo pregnantAntenatalExaminationInfo = pregnantAntenatalExaminationInfoMapper.findPrenatalRemind(pregnantWeek);
		if(pregnantAntenatalExaminationInfo!=null){
			return pregnantAntenatalExaminationInfo;
		}
		return null;
	}

	/**
	 * 获取产检信息列表
	 */
	@Override
	public List<PregnantAntenatalExaminationInfo> findPregAnteExaminationInfoList() {
		List<PregnantAntenatalExaminationInfo> list = pregnantAntenatalExaminationInfoMapper.findPregAnteExaminationInfoList();
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	/**
	 * 删除产检信息
	 */
	@Override
	public boolean deleteExaminationInfo(int examinationInfoId) {
		boolean b = false;
		try {
			pregnantAntenatalExaminationInfoMapper.deleteExaminationInfo(examinationInfoId);
			b = true;
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 添加或修改产检信息
	 */
	@Override
	public boolean addOrEditExaminationInfo(
			PregnantAntenatalExaminationInfo examinationInfo) {
		boolean b = false;
		if(examinationInfo.getId()==0){
			//添加
			try {
				pregnantAntenatalExaminationInfoMapper.addExaminationInfo(examinationInfo);
				b = true;
			} catch (Exception e) {
				b = false;
				e.printStackTrace();
			}
		}else{
			//修改
			try {
				pregnantAntenatalExaminationInfoMapper.editExaminationInfo(examinationInfo);
				b = true;
			} catch (Exception e) {
				b = false;
				e.printStackTrace();
			}
		}
		return b;
	}

	/**
	 * 查找产检次数是否存在
	 */
	@Override
	public PregnantAntenatalExaminationInfo checkExamNumbers(int infoId, int examNumbers) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("infoId", infoId);
		map.put("examNumbers", examNumbers);
		PregnantAntenatalExaminationInfo examinationInfo = pregnantAntenatalExaminationInfoMapper.checkExamNumbers(map);
		if(examinationInfo != null){
			return examinationInfo;
		}
		return null;
	}

	/**
	 * 查询产检项目对应的产检次数信息
	 */
	@Override
	public List<PregnantAntenatalExaminationInfo> findPregAnteExamItems() {
		List<PregnantAntenatalExaminationInfo> list = pregnantAntenatalExaminationInfoMapper.findPregAnteExamItems();
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
}
