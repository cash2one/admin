package com.jumper.angel.home.pregnancy.service;

import java.util.List;

import com.jumper.angel.home.pregnancy.entity.PregnantAntenatalExaminationItemInfo;
import com.jumper.angel.home.pregnancy.vo.VOPregAnteExamItemInfo;

/**
 * 孕期产检项目信息Service
 * @Description TODO
 * @author fangxilin
 * @date 2016-11-30
 */
public interface PregnantAntenatalExaminationItemInfoService {
	/**
	 * 通过id查询孕期产检信息
	 * @version 1.0
	 * @createTime 2016-11-30,下午5:10:19
	 * @updateTime 2016-11-30,下午5:10:19
	 * @createAuthor fangxilin
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param paeId
	 * @return
	 */
	public List<PregnantAntenatalExaminationItemInfo> findPreItemsByPaeId(Integer paeId);

	/**
	 * 获取总记录数
	 * @param keywords
	 * @return
	 */
	public int findCount(String keywords, int infoId);

	/**
	 * 查询产检项目信息
	 * @param beginIndex 开始页
	 * @param everyPage 每页记录数
	 * @param keywords 关键字
	 * @return
	 */
	public List<VOPregAnteExamItemInfo> findPregAnteExamItemInfoList(
			int beginIndex, int everyPage, String keywords, int infoId);

	/**
	 * 删除产检项目信息
	 * @param examinationItemInfoId
	 * @return
	 */
	public boolean deleteExaminationItemInfo(int examinationItemInfoId);

	/**
	 * 检查产检项目名是否重复
	 * @param itemId
	 * @param itemName
	 * @return
	 */
	public List<PregnantAntenatalExaminationItemInfo> checkExamNumbers(int itemId, int examId,
			String itemName);

	/**
	 * 添加或修改产检项目信息
	 * @param itemInfo
	 * @return
	 */
	public boolean addOrEditExamItemInfo(
			PregnantAntenatalExaminationItemInfo itemInfo);
}
