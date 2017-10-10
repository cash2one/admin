package com.jumper.angel.utils;

import java.io.Serializable;

public class ReturnMsg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3936243095361392704L;
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;
	private int msg;
	private String msgbox;
	private Object data;

	public ReturnMsg() {
		super();
	}

	public ReturnMsg(int msg, String msgbox, Object data) {
		super();
		this.msg = msg;
		this.msgbox = msgbox;
		this.data = data;
	}

	public int getMsg() {
		return msg;
	}

	public void setMsg(int msg) {
		this.msg = msg;
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
