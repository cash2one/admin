/**
 * 
 */
package com.jumper.angel.sociality.group.model.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @ClassName: ForbidSendMsg
 * @author huangzg 2017年2月9日 上午9:38:54
 */
public class ForbidSendMsg {

	@JSONField(name = "GroupId")
	private String groupId;

	@JSONField(name = "Members_Account")
	private String[] membersAccount;

	@JSONField(name = "ShutUpTime")
	private Long shutUpTime;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String[] getMembersAccount() {
		return membersAccount;
	}

	public void setMembersAccount(String[] membersAccount) {
		this.membersAccount = membersAccount;
	}

	public Long getShutUpTime() {
		return shutUpTime;
	}

	public void setShutUpTime(Long shutUpTime) {
		this.shutUpTime = shutUpTime;
	}
	
	public ForbidSendMsg() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ForbidSendMsg(String groupId, String[] membersAccount,
			Long shutUpTime) {
		super();
		this.groupId = groupId;
		this.membersAccount = membersAccount;
		this.shutUpTime = shutUpTime;
	}

	public static void main(String[] args) {
		String[] account = {"peter"};
		ForbidSendMsg msg = new ForbidSendMsg("@TGS#2C5SZEAEF", account, 123456l);
		System.out.println(JSONObject.toJSON(msg));
	}
}
