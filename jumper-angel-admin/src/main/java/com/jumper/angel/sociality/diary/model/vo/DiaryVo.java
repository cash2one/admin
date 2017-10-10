package com.jumper.angel.sociality.diary.model.vo;

import com.jumper.angel.sociality.diary.model.po.DiaryPo;

/**
 * 宝妈日记实体类
 * 
 * @ClassName: DiaryVo
 * @author huangzg 2017年1月14日 下午1:53:02
 */
public class DiaryVo extends DiaryPo implements java.io.Serializable {

	/** 类的版本号 */
	private static final long serialVersionUID = 2008989486450690L;

	private int beginIndex;

	private int everyPage;

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getEveryPage() {
		return everyPage;
	}

	public void setEveryPage(int everyPage) {
		this.everyPage = everyPage;
	}

}