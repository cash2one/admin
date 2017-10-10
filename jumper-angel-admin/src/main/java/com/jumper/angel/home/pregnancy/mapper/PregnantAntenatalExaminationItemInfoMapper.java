package com.jumper.angel.home.pregnancy.mapper;

import java.util.List;
import java.util.Map;

import com.jumper.angel.home.pregnancy.entity.PregnantAntenatalExaminationItemInfo;

/**
 * 孕期产检项目信息Mapper
 * @Description TODO
 * @author fangxilin
 * @date 2016-11-30
 */
public interface PregnantAntenatalExaminationItemInfoMapper {
	/**
	 * 通过产检信息id查询孕期产检项目信息列表
	 * @version 1.0
	 * @createTime 2016-11-30,下午5:00:19
	 * @updateTime 2016-11-30,下午5:00:19
	 * @createAuthor fangxilin
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param paeId
	 * @return
	 */
	public List<PregnantAntenatalExaminationItemInfo> findPreItemsByPaeId(Integer paeId);

	/**
	 * 查询总记录数
	 * @param map
	 * @return
	 */
	public int findCount(Map<String, Object> map);

	/**
	 * 获取产检项目信息
	 * @param map
	 * @return
	 */
	public List<PregnantAntenatalExaminationItemInfo> findPregAnteExamItemInfoList(
			Map<String, Object> map);

	/**
	 * 删除产检项目信息
	 */
	public void deleteExaminationItemInfo(int examinationItemInfoId);

	/**
	 * 查询产检项目是否存在
	 * @param map
	 * @return
	 */
	public List<PregnantAntenatalExaminationItemInfo> checkExamNumbersByName(
			Map<String, Object> map);

	/**
	 * 添加产检项目信息
	 * @param itemInfo
	 */
	public void addExaminationItemInfo(
			PregnantAntenatalExaminationItemInfo itemInfo);

	/**
	 * 修改产检项目信息
	 * @param itemInfo
	 */
	public void editExaminationItemInfo(
			PregnantAntenatalExaminationItemInfo itemInfo);
}
