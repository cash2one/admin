package com.jumper.angel.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.jumper.angel.hospital.doctor.entity.UserInfoBean;
import com.jumper.angel.hospital.doctor.mapper.UserInfoBeanMapper;
import com.jumper.angel.sociality.group.mapper.GroupMapper;
import com.jumper.angel.sociality.group.mapper.UserGroupMapper;
import com.jumper.angel.sociality.group.model.bo.SocialityGroupBo;
import com.jumper.angel.sociality.group.model.po.UserGroupPo;
import com.jumper.angel.sociality.group.model.vo.SocialityGroupVo;
import com.jumper.angel.sociality.group.model.vo.UserinfoVo;
import com.jumper.angel.sociality.util.Constans;
import com.jumper.angel.utils.HttpPost;
import com.jumper.angel.utils.TimeUtils;

import net.sf.json.JSONObject;

/**
 * 社交业务  自动加入交流圈
 * @ClassName: UserGroupJob  
 * @author huangzg 2017年2月24日 下午2:16:37
 */
@Component
public class UserGroupJob {
	
	private static Logger logger = Logger.getLogger(UserGroupJob.class);
	
	@Autowired
	private UserInfoBeanMapper userInfoBeanMapper;
	@Autowired
	private GroupMapper groupMapper;
	@Autowired
	private UserGroupMapper userGroupMapper;
	
	@Scheduled(cron="0 0 4 * * ?")
	public void runPrenatalExaminationRemindJob() {
		try {
			System.out.println("============自动拉取交流圈==================");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			logger.info("********* "+format.format(new Date())+" runPrenatalExaminationRemindJob run() *********");
			//查询所有预产期不为空的怀孕用户
			List<UserInfoBean> userInfoBeans = userInfoBeanMapper.selUserList();
			System.out.println("自动拉取交流圈 = "+ userInfoBeans.size());
			
			if(userInfoBeans != null && userInfoBeans.size() > 0){
				for (UserInfoBean userInfoBean : userInfoBeans) {
					if (userInfoBean.getExpectedDateOfConfinement() != null ){	// 如果 预计分娩时间
						// 根据用户的预产期查询该预产期交流圈是否存在
						String dateTime = TimeUtils.converStringDate(userInfoBean.getExpectedDateOfConfinement(), TimeUtils.YY_MM);
						SocialityGroupVo vo = new SocialityGroupVo();
						vo.setGroupName(dateTime);
						logger.info("vo.setGroupName(dateTime);"+dateTime);
						List<SocialityGroupBo> groups = groupMapper.selGroupInfoByName(vo);	//an
						
						for (SocialityGroupBo bo : groups){
							UserGroupPo userGroupPo = new UserGroupPo();
							//userGroupPo.setGroupId(bo.getGroupId());
							userGroupPo.setUserId(String.valueOf(userInfoBean.getId()));
							// 查询用户是否已加入该交流圈，若存在不自动加入，若不存在则自动加入。
							// 若某年某月交流圈存在两个，则用户只要加入其中一个则另一个不加入。
							List<UserinfoVo> userLists = userGroupMapper.selUserinfo(userGroupPo);
							if (userLists == null || userLists.size() == 0){
								String chatId = "101_yh_"+userInfoBean.getId();
								HttpPost.doPost(Constans.ACCOUNT_IMPORT, accountImportJson(chatId, "", ""));
								String add_group_mode = "{\"GroupId\": \""+bo.getGroupId()+"\", \"Silence\": 1,\"MemberList\": [{\"Member_Account\": \""+chatId+"\"}]}";
								String val = HttpPost.doPost(Constans.ADD_GROUP, add_group_mode);
								
								ImReturnEntity i = new Gson().fromJson(val, ImReturnEntity.class); //解析消息返回实体
								logger.info(val);
								if("OK".equals(i.getActionStatus()) && i.getErrorCode() == 0){  // 请求成功
									
									if( i.getMemberList().get(0).getResult() == 1 ){ // 等于 1 表示成功  2表示 已经加过（）
										UserGroupPo userGroup = new UserGroupPo();
										userGroup.setUserId(String.valueOf(userInfoBean.getId()));
										userGroup.setGroupId(bo.getGroupId());
										userGroup.setCreateTime(new Date().getTime());
										userGroupMapper.add(userGroup);		//插入信息
										logger.info("UserId"+String.valueOf(userInfoBean.getId())+"加入成功！");
										break;
									}
								}
							}
						}
					}
				}
			}
			//清空list
		} catch(Exception ex) {
			logger.error("pregnantexamination sendSystemMsg fail!"+ex.getMessage());
		}
	}
	
	/**
	 * @author huangzg 2016-9-3 下午2:44:52 
	 * @Title: accountImportJson 
	 * @param userId IM用户帐号
	 * @param nickName IM帐号昵称
	 * @param imgUrl IM帐号头像Url
	 * @return String    
	 * @throws
	 */
	private static String accountImportJson(String userId, String nickName, String imgUrl){
		JSONObject json = new JSONObject();
		json.put("Identifier", userId);
		json.put("Nick", nickName);
		json.put("FaceUrl", imgUrl);
		return json.toString();
	}

	
	public static void main(String[] args) {
		
		HttpPost.doPost(Constans.ACCOUNT_IMPORT, accountImportJson("101_yh_11561", "", ""));
		String add_group_mode = "{\"GroupId\": \""+"94999452071978"+"\", \"Silence\": 1,\"MemberList\": [{\"Member_Account\": \""+"101_yh_11561"+"\"}]}";
		String val = HttpPost.doPost(Constans.ADD_GROUP, add_group_mode);
		System.out.println(val);
		//String a = "{\"ActionStatus\": \"OK\",   \"ErrorInfo\": \"\",   \"ErrorCode\": 0,   \"MemberList\": [{\"Member_Account\": \"tommy\",  \"Result\": 1  }, {  \"Member_Account\": \"jared\", \"Result\": 1   }]}";
		//HashMap<String, Object> map = JsonUtils.toHashMap(a);
		//Map<String, Object> classMap = new HashMap<String, Object>();  
		//classMap.put("MemberList", T2.class);
		//T1 t = (T1) JsonUtils.getObjectFromJsonString(a, T1.class, classMap);
		//ImReturnEntity i = new Gson().fromJson(a, ImReturnEntity.class);
		//System.out.println(i.getMemberList().get(0).getResult());
		
	}
	
}





// IM返回消息 实体
class ImReturnEntity {
	private String ActionStatus;
	private String ErrorInfo;
	private int ErrorCode;
	private List<Member> MemberList;
	public String getActionStatus() {
		return ActionStatus;
	}
	public void setActionStatus(String actionStatus) {
		ActionStatus = actionStatus;
	}
	public String getErrorInfo() {
		return ErrorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		ErrorInfo = errorInfo;
	}
	public int getErrorCode() {
		return ErrorCode;
	}
	public void setErrorCode(int errorCode) {
		ErrorCode = errorCode;
	}
	public List<Member> getMemberList() {
		return MemberList;
	}
	public void setMemberList(List<Member> memberList) {
		MemberList = memberList;
	}
}
// IM 返回Member消息实体
class Member {
	private String Member_Account;
	private int Result;
	public String getMember_Account() {
		return Member_Account;
	}
	public void setMember_Account(String member_Account) {
		Member_Account = member_Account;
	}
	public int getResult() {
		return Result;
	}
	public void setResult(int result) {
		Result = result;
	}
	
}
