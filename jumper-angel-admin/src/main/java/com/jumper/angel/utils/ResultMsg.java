package com.jumper.angel.utils;

import java.util.HashMap;

/**
 * 返回结果
 * @Description 
 * @author qinxiaowei
 * @date 2016-6-6
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public class ResultMsg {
	
	private int code;
	
	private String msgbox;
	
	private Object data = new HashMap<String, Object>();
	
	public ResultMsg(){}
	
	public ResultMsg(int code, String msgbox){
		this.code = code;
		this.msgbox = msgbox;
	}
	
	public ResultMsg(int code, String msgbox, Object data){
		this(code,msgbox);
		this.data =data;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsgbox() {
		return msgbox;
	}

	public void setMsgbox(String msgbox) {
		this.msgbox = msgbox;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
