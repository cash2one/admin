package com.jumper.angel.sociality.diary.model.bo;

import java.util.List;

import com.jumper.angel.sociality.diary.model.po.DiaryPo;

/**
 * 宝妈日记实体类
 * 
 * @ClassName: DiaryBo
 * @author huangzg 2017年1月14日 下午1:52:03
 */
public class DiaryBo extends DiaryPo implements java.io.Serializable {

	/** 类的版本号 */
	private static final long serialVersionUID = 2008989486450689L;

	private java.lang.Integer counts;

	private java.lang.String createTime;

	private List<String> imgs;

	public java.lang.Integer getCounts() {
		return counts;
	}

	public void setCounts(java.lang.Integer counts) {
		this.counts = counts;
	}

	public java.lang.String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}

	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}

}