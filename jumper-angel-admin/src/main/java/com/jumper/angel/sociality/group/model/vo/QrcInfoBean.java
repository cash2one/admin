package com.jumper.angel.sociality.group.model.vo;

import java.io.Serializable;

/**
 * 二维码 内容
 * 
 * @ClassName: QrcInfoBean
 * @author huangzg 2016年12月26日 下午2:34:59
 */
public class QrcInfoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2029695734021990626L;
	
	public String type;// 标识是什么类型的二维码

	public String id;// 二维码携带id，比如交流圈二维码就是交流圈的id

	public String name;// 二维码携带的名称字段，比如交流圈二维码就可以是交流圈的名字

	public String desc;// 二维码携带的描述字段，比如交流圈二维码就可以是对交流圈的简单介绍

	public String time;// 二维码携带的时间字段，可以是生成二维码的时间等

	public String other;// 二维码携带的预留消息字段，以防不备之需

	public QrcInfoBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QrcInfoBean(String type, String id, String name, String desc,
			String time, String other) {
		super();
		this.type = type;
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.time = time;
		this.other = other;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}
