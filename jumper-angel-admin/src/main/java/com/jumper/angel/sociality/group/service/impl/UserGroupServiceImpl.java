package com.jumper.angel.sociality.group.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.json.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.sociality.BaseIMService;
import com.jumper.angel.sociality.group.mapper.UserGroupMapper;
import com.jumper.angel.sociality.group.model.bo.UserGroupBo;
import com.jumper.angel.sociality.group.model.po.ImAccountsBo;
import com.jumper.angel.sociality.group.model.po.SocialityGroupPo;
import com.jumper.angel.sociality.group.model.po.UserGroupPo;
import com.jumper.angel.sociality.group.model.vo.ForbidSendMsg;
import com.jumper.angel.sociality.group.model.vo.UserGroupVo;
import com.jumper.angel.sociality.group.model.vo.UserinfoVo;
import com.jumper.angel.sociality.group.service.UserGroupService;
import com.jumper.angel.sociality.operationrecord.mapper.OperationRecordMapper;
import com.jumper.angel.sociality.util.Constans;
import com.jumper.angel.sociality.util.HttpPost;
import com.jumper.angel.utils.ConfigProUtils;
import com.jumper.angel.utils.StringUtil;
import com.jumper.angel.utils.TimeUtils;
import com.jumper.angel.utils.json.JsonUtils;
import com.jumper.angel.utils.page.PagedResult;

/**
 * 交流圈成员ServiceImpl
 * @ClassName: UserGroupServiceImpl  
 * @author huangzg 2016年12月24日 上午10:05:20
 */
@Service("userGroupServiceImpl")
public class UserGroupServiceImpl extends BaseIMService<Object> implements UserGroupService {
	
	protected Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private UserGroupMapper mapper;
	
	@Autowired
	private OperationRecordMapper operationRecordMapper;
	
	@Autowired
	private UserGroupService service;	
	/**
	 * 分页检索查询交流圈信息
	 * @author huangzg 2016年12月24日 上午11:00:33  
	 * @param vo 参数集
	 * @param page 页码
	 * @param rows 每页条数
	 * @return        
	 * @throws
	 */
	@Override
	public PagedResult<UserGroupBo> sel(UserGroupVo vo) {
		/*try {
			page = page == null?1:page;
			rows = rows == null?10:rows;
			PageHelper.startPage(page, rows);  
			return PageBeanUtil.toPagedResult(mapper.sel(vo));
		} catch (Exception e) {
			logger.info("分页检索查询交流圈信息 msg : "+e.getMessage(), e);
		}
		return null;*/
		return null;
	}
	
	
	/**
	 * 编辑交流圈成员信息
	 * @author huangzg 2016年12月24日 上午11:00:17  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 */
	@Override
	public int edit(UserGroupVo vo) {
		return mapper.edit(vo);
	}
	@Override
	public int selectCountUserGroupByBean(UserGroupVo vo) {//3
		return mapper.selectCountUserGroupByBean(vo);
	}
	
	@Override
	public List<UserGroupVo> findUserGroupByPageBean(UserGroupVo vo) {
		//1.负责查询isKeeper为2管理员,3医生
	    //2.未传递isKeeper时负责查询所有的身份，返回的结果中isKeeper为0的再去查询user_extra_info表current_identity字段匹配
		List<UserGroupVo> list = mapper.selectUserGroupByPageBean(vo);
		
		/*if(list !=null  && list.size() >0){
			// GroupId --> chatId
			List<Map<String,Object>> chatIds = getChatIdsByGroupId(vo.getGroupId());
			logger.info("findUserGroupByPageBean---------> chatIds ："+JsonUtils.toJSONString(chatIds));
			//拼接  chatIsopenidStr  拼接分页中的userId
			StringBuilder openidStr = new StringBuilder();
			for(UserGroupVo vo1 :list){
					openidStr.append("yh_"+vo1.getUserId()+"-");
			}
			// 根据 openid 获取chatId
			List<UserGroupVo> chatIdList = getChatIds(openidStr.toString());
			for(UserGroupVo vo3 :chatIdList){
				if(chatIds!=null){
					for (Map<String, Object> map : chatIds) {
						if(vo3.getChatId().equals(map.get("Member_Account").toString()) 
								&& map.get("LastSendMsgTime").toString().length() > 5){ //如果 chatIds 中的chatId == chatId
								UserGroupPo userGroupPo = new UserGroupPo();
								userGroupPo.setGroupId(vo.getGroupId());
								userGroupPo.setUserId(vo3.getUserId());
								userGroupPo.setSpeakingTime(Long.parseLong(map.get("LastSendMsgTime").toString()+"000"));
							//	logger.info("vo1.getSpeakingTime=========="+vo3.getSpeakingTime());
								// 修改 最新发言时间
								mapper.updateSpeakingTime(userGroupPo);
						}
					}
				}
			}
		}
		List<UserGroupVo> list1=mapper.selectUserGroupByPageBean(vo);
		// 格式化 	getSpeakingTime setCreateDateTime
		for(UserGroupVo vo1 :list1){
			vo1.setCreateDateTime(TimeUtils.convertLongToTimestampString("yyyy/MM/dd HH:mm:ss",vo1.getCreateTime()));
			if(vo1.getSpeakingTime()==null ||vo1.getSpeakingTime()<=0){
				vo1.setSpeakingDateTime("");
			}else{//格式化时间
				vo1.setSpeakingDateTime(TimeUtils.convertLongToTimestampString("yyyy/MM/dd HH:mm:ss",vo1.getSpeakingTime()));
			}
		}*/
		
		List<Map<String,Object>> chatIds = getChatIdsByGroupId(vo.getGroupId());
		logger.info("findUserGroupByPageBean---------> chatIds ："+JsonUtils.toJSONString(chatIds));
		
		// 根据 openid 获取chatId
		// List<UserGroupVo> chatIdList = getChatIds(openidStr.toString());
		List<UserGroupVo> reList = new ArrayList<UserGroupVo>();
		if(list !=null  && list.size() >0){
			//遍历数据，判断isKeeper是否为0，为0则查询user_extra_info表current_identity字段
			for(UserGroupVo ug :list){
				if(ug.getIsKeeper()==0){
					int currentIdentity = mapper.getIdentity(Integer.valueOf(ug.getUserId()));//0：怀孕中  1：已有宝宝  2：备孕期
					ug.setIsKeeper(currentIdentity==0?(long)1:(currentIdentity==1?(long)4:(long)5));//1:孕妈 4:宝妈 5:备孕
				}
			}
			for(UserGroupVo vo1 :list){
				if(chatIds != null){
					String userid = vo1.getUserId();
					String chatId = "101_yh_" + userid;
					for (Map<String, Object> map : chatIds) {
						if(chatId.equals(map.get("Member_Account").toString()) 
								&& map.get("LastSendMsgTime").toString().length() > 5){ //如果 chatIds 中的chatId == chatId
							// vo1.setCreateDateTime(TimeUtils.convertLongToTimestampString("yyyy/MM/dd HH:mm:ss",vo1.getCreateTime()));
							vo1.setSpeakingTime(Long.parseLong(map.get("LastSendMsgTime").toString()+"000"));
							/*if(vo1.getSpeakingTime()==null ||vo1.getSpeakingTime()<=0){
								vo1.setSpeakingDateTime("");
							}else{//格式化时间
								vo1.setSpeakingDateTime(TimeUtils.convertLongToTimestampString("yyyy/MM/dd HH:mm:ss",vo1.getSpeakingTime()));
							}*/
						}
					}
				}
				//格式化时间
				vo1.setCreateDateTime(TimeUtils.convertLongToTimestampString("yyyy/MM/dd HH:mm:ss",vo1.getCreateTime()));
				if(vo1.getSpeakingTime() == null ||vo1.getSpeakingTime()<=0){
					vo1.setSpeakingDateTime("");
				}else{//格式化时间
					vo1.setSpeakingDateTime(TimeUtils.convertLongToTimestampString("yyyy/MM/dd HH:mm:ss",vo1.getSpeakingTime()));
				}
				//存放管理员2
				if(vo1.getIsKeeper()!=null&&vo1.getIsKeeper()==2){//未传入身份，返回结果排序：管理员2，医生3排最前面，其余则以加入时间先后顺序排列
					reList.add(vo1);//存放管理员2
			//		list.remove(vo1);//清除该管理员
				}
			}
			for(UserGroupVo vo2 :list){
				if(vo2.getIsKeeper()!=null&&vo2.getIsKeeper()==3){//未传入身份，返回结果排序：管理员2，医生3排最前面，其余则以加入时间先后顺序排列
					reList.add(vo2);//存放医生3
			//		list.remove(vo2);//清除该医生
				}
			}
			for(UserGroupVo vo3 :list){
				if(vo3.getIsKeeper()!=null&&vo3.getIsKeeper()!=2&&vo3.getIsKeeper()!=3){//未传入身份，返回结果排序：管理员2，医生3排最前面，其余则以加入时间先后顺序排列
					reList.add(vo3);//存放管理员2和医生3以外身份的用户
				}
			} 
		}
		return reList;
	}
	
	// GroupId 组中 的 所有ChatId
	private List<Map<String,Object>> getChatIdsByGroupId(String GroupId){
		String params = "{\"GroupId\":\""+GroupId+"\",\"MemberInfoFilter\":[\"Role\",\"LastSendMsgTime\"]}"; 
		String returnVal = HttpPost.doPost(Constans.GET_GROUP_MEMBER_INFO, params);
		HashMap<String, Object> map = JsonUtils.toHashMap(returnVal);
		try {
			if("OK".equals(map.get("ActionStatus").toString())){
				List<Map<String,Object>> a = JsonUtils.toList(map.get("MemberList"));
				return a;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// userId 获取 ChatId 
	private String getChatIdByUserId(String userId){
		String returnVal1 = HttpPost.doPost(ConfigProUtils.get("GetChatIdUrl")+"common/accounts/sel?openid=yh_"+userId, "");
		System.out.println("returnVal1=" + returnVal1);
		if(!StringUtil.isJsonFormatStr(returnVal1)){
			return "";
		}
		HashMap<String, Object> map = JsonUtils.toHashMap(returnVal1);
		if( !"null".equals(map.get("data").toString())){
			HashMap<String, Object> map1 = JsonUtils.toHashMap(map.get("data"));
			return map1.get("chatId").toString();
		}
		return "";
	}
	
	
	private static List<UserGroupVo> getChatIds(String openidStr){
		// 调用common   查询IM账号
		String returnVal = HttpPost.doPost(ConfigProUtils.get("GetChatIdUrl")+"common/accounts/selChatIds?openidStr="+openidStr, "");
		System.out.println("returnVal=" + returnVal);
		// 接口返回参数
		HashMap<String, Object> dataMap = JsonUtils.toHashMap(returnVal);
		List<Map<String,Object>> list = (List<Map<String, Object>>) dataMap.get("data");
		if (list != null && "1".equals(dataMap.get("msg").toString())){
			List<UserGroupVo> returnList = new ArrayList<UserGroupVo>();
			UserGroupVo vo = null;
			for(Map<String,Object> m:list){
				vo = new UserGroupVo();
				vo.setUserId(m.get("userId").toString());
				vo.setChatId(m.get("chatId").toString());
				returnList.add(vo);
			}
			return returnList;
		}
		return null;
	}
	
	/**
	 * 操作类型是什么生    禁言！
	 * this.点击调用
	 * @author huangzg 2016年12月24日 上午11:00:17  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 */
	@Override
	public int update(UserGroupPo po,CrmAdmin monitor) {
		//this.addOperatorRecord(po, monitor);
		try {
			mapper.updateByBean(po);
				String chatId = "";
				if (po.getIsBlacklist() == 1 ) { // 禁言标示  1表示要禁言该用户
					//UserGroupPo tempPO = mapper.selectById(po.getId());//update
					
					// 调用common   查询IM账号
					String url = ConfigProUtils.get("COMMON_PROJECT_PATH");
					String im_accounts_sel = url + "/accounts/sel?openid=yh_"+po.getUserId();
					String val = sendPost(im_accounts_sel, "");//+"&userId="+tempPO.getUserId()
					
					// 接口返回参数
					HashMap<String, Object> dataMap = JsonUtils.toHashMap(val);
					Object obj = dataMap.get("data");
					if (obj != null && "1".equals(dataMap.get("msg").toString())){
						//ImAccountsBo accountsPo = JsonUtils.toBean(obj, ImAccountsBo.class);
						//chatId = accountsPo.getChatId();
						Map<String,Object> map1 = JsonUtils.toHashMap(obj);
						chatId = map1.get("chatId").toString();
					}else {
						//调用common 新增IM账号
						String im_accounts_add = url + "/accounts/add?appid=101&openid=yh_"+po.getUserId()+"&userId="+po.getUserId();
						String returnVal = sendPost(im_accounts_add, "");
						HashMap<String, Object> map = JsonUtils.toHashMap(returnVal);
						Object data = map.get("data");
						System.out.println(data);
						if (data != null && "1".equals(map.get("msg").toString())){
							Map<String,Object> map1 = JsonUtils.toHashMap(obj);
							chatId = map1.get("chatId").toString();
						}
					}
					String[] account = {chatId};
					ForbidSendMsg msg = new ForbidSendMsg(po.getGroupId(), account, 31536000l);
					String params = JSONObject.toJSON(msg).toString();
					// 调用第三方IM接口 禁言
					String returnInfo = HttpPost.doPost(Constans.FORBID_SEND_MSG, params);
					HashMap<String, Object> hashMap = JsonUtils.toHashMap(returnInfo);
					//	service.updateByBean(po,monitor);
					if ("0".equals(hashMap.get("ErrorCode").toString())){
						return 1;	
					}
				}else{
					UserGroupPo tempPO = mapper.selectById(po.getId());//update
					String url = ConfigProUtils.get("COMMON_PROJECT_PATH");
					String im_accounts_sel = url + "/accounts/sel?openid=yh_"+tempPO.getUserId();
					String val = sendPost(im_accounts_sel, "");
					HashMap<String, Object> dataMap = JsonUtils.toHashMap(val);
					Object obj = dataMap.get("data");
					if (obj != null && "1".equals(dataMap.get("msg").toString())){
						Map<String,Object> map1 = JsonUtils.toHashMap(obj);
						chatId = map1.get("chatId").toString();
					}else {
						String im_accounts_add = url + "/accounts/add?appid=101&openid=yh_"+po.getUserId()+"&userId="+tempPO.getUserId();
						String returnVal = sendPost(im_accounts_add, "");
						HashMap<String, Object> map = JsonUtils.toHashMap(returnVal);
						Object data = map.get("data");
						if (data != null && "1".equals(map.get("msg").toString())){
							Map<String,Object> map1 = JsonUtils.toHashMap(obj);
							chatId = map1.get("chatId").toString();
						}
					}
					String[] account = {chatId};
					ForbidSendMsg msg = new ForbidSendMsg(po.getGroupId(), account, 0l);
					String params = JSONObject.toJSON(msg).toString();
					String returnInfo = HttpPost.doPost(Constans.FORBID_SEND_MSG, params);
					HashMap<String, Object> hashMap = JsonUtils.toHashMap(returnInfo);
					if ("0".equals(hashMap.get("ErrorCode").toString())&&"OK".equals(hashMap.get("ActionStatus").toString())){
						System.out.println("log po.getisblacklist : ---"+po.getIsBlacklist());
						return 1;	
					}
				}

		} catch (Exception e) {
			System.out.println("error"+e);
		} 
		return 0;
	}
	
/*	private int addOperatorRecord(UserGroupPo userGroupPo,CrmAdmin monitor){
		OperationRecordPO operationRecordPO = new OperationRecordPO();
		UserGroupPo tempPO = mapper.selectById(userGroupPo.getId());//update
		String userId = tempPO.getUserId();
		String nickName = "";
		if(userId != null && StringUtils.isNotBlank(userId)){
			Map<String,Object> userMap = operationRecordMapper.seleclUserInfoById(Integer.parseInt(userId));
			nickName = userMap.get("nick_name") ==null?"":(String)userMap.get("nick_name");
		}
		if(userGroupPo.getIsBlacklist() !=null && userGroupPo.getIsBlacklist()==1 ){
			operationRecordPO.setRecordContent("禁用交流圈["+nickName+"]");
			operationRecordPO.setRecordType(21);
		}else if(userGroupPo.getIsBlacklist() !=null && userGroupPo.getIsBlacklist()==0){
			operationRecordPO.setRecordContent("启用交流圈["+nickName+"]");
			operationRecordPO.setRecordType(22);
		}else if(userGroupPo.getIsKeeper() !=null && userGroupPo.getIsKeeper() ==1 ){
			operationRecordPO.setRecordContent("设置了医生["+nickName+"]");
			operationRecordPO.setRecordType(23);
		}else if(userGroupPo.getIsKeeper() !=null && userGroupPo.getIsKeeper() ==0 ){
			operationRecordPO.setRecordContent("设置了孕妈["+nickName+"]");
			operationRecordPO.setRecordType(24);
		}else if(userGroupPo.getIsKeeper() !=null && userGroupPo.getIsKeeper() ==2 ){
			operationRecordPO.setRecordContent("设置了管理员["+nickName+"]");
			operationRecordPO.setRecordType(25);
		}else if(userGroupPo.getIsKeeper() !=null && userGroupPo.getIsKeeper() ==3 ){
			operationRecordPO.setRecordContent("设置了宝妈["+nickName+"]");
			operationRecordPO.setRecordType(26);
		}
		if(monitor ==null){
			operationRecordPO.setRecordUserId("0");
			operationRecordPO.setRecordUserName("admin");
		}else{
			operationRecordPO.setRecordUserId(monitor.getId()==null?"0":monitor.getId()+"");
			operationRecordPO.setRecordUserName(monitor.getName()==null?"admin":monitor.getName());
		}
		operationRecordPO.setRecordTime(new Date().getTime());
		return operationRecordMapper.insert(operationRecordPO);
	}
*/
	/**
	 *通过id来查询
	 */
	@Override
	public UserGroupPo selectById(int id) {
		return mapper.selectById(id);
	}
	/**
	 * 查询userInfo表里面所有的数据
	 * UserinfoVo
	 * TSL
	 */
	@Override
	public List<UserinfoVo> selUserInfo(String groupId, String userId) {
		UserGroupPo userGroupPo = new UserGroupPo();
		userGroupPo.setGroupId(groupId);
		userGroupPo.setUserId(userId);
		return mapper.selUserinfo(userGroupPo);
	}
	
	@Override
	public List<SocialityGroupPo> selUserInfoData(SocialityGroupPo groupName) {
		return mapper.selUserInfovague(groupName);
	}


	@Override
	public int updateBU(UserGroupPo po, CrmAdmin monitor) {
		return mapper.updateBU(po);
	}


	@Override
	public int updateByBean(UserGroupPo po, CrmAdmin monitor) {
		return mapper.updateByBean(po);
	}

	
	/**
	 *  统计 当前交流圈 （交流圈人数，今日交流圈新增人数，今日交流圈发言人数）
	 *  更新最后一次发言时间
	 * @param groupId
	 * @return
	 */
	@Override
	public Map<String, Object> numCount(String groupId) {
		
List<Map<String,Object>> chatIds = getChatIdsByGroupId(groupId);
		
		List<UserGroupVo> userGroupVos = new ArrayList<UserGroupVo>();
		
		if (null != chatIds && chatIds.size() > 0) {
			
			for (int i = 0; i < chatIds.size(); i++) {
				
				if (chatIds.get(i).get("LastSendMsgTime").toString().length() > 5) { // 以此判断有无最后一次发言时间
					UserGroupVo userGroupVo = new UserGroupVo();
					
					String userId = getUserid(chatIds.get(i).get("Member_Account").toString());
					
					if ("".equals(userId)) {
						continue ;
					}
					
					userGroupVo.setUserId(userId);
					userGroupVo.setGroupId(groupId);
					userGroupVo.setSpeakingTime(Long.parseLong(chatIds.get(i).get("LastSendMsgTime").toString()+"000"));
					
					userGroupVos.add(userGroupVo);
				}
				if ( i > 1 && i % 200 == 0 && userGroupVos.size() > 0) { // 没200条次  发送一次请求
					
					// 批量更新
					mapper.updateSpeakingTimes(userGroupVos);
					userGroupVos = new ArrayList<UserGroupVo>();
				}
				if (i+1 == chatIds.size()  && userGroupVos.size() > 0) { // 最后
					// 批量更新
					mapper.updateSpeakingTimes(userGroupVos);
					userGroupVos = new ArrayList<UserGroupVo>();
				}
			}
			
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		int currentGroupCount = 0;				//当前交流圈人数
		int currentGroupIncreaseCount = 0;	//今日当前交流圈新增人数
		int currentGroupSpeakCount = 0;			//今日当前交流圈发言人数
		
		currentGroupCount = mapper.currentGroupCount(groupId);				//当前交流圈人数
		currentGroupIncreaseCount = mapper.currentGroupIncreaseCount(groupId);	//今日当前交流圈新增人数
		currentGroupSpeakCount = mapper.currentGroupSpeakCount(groupId);			//今日当前交流圈发言人数
		
		map.put("currentGroupCount", currentGroupCount);
		map.put("currentGroupIncreaseCount", currentGroupIncreaseCount);
		map.put("currentGroupSpeakCount", currentGroupSpeakCount);
		
		
		
		return map;
	}
	
	/**
	 * 101_yh_134166 --> 134166 
	 * @param chatId
	 * @return
	 */
	private String getUserid(String chatId){
		
		String userId = "";
		
		try {
			userId = chatId.split("_")[2];
			return userId;
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		return userId;
	}


	@Override
	public int selectCountUserGroupByBean2(UserGroupVo vo) {
		return mapper.selectCountUserGroupByBean2(vo);
	}


	@Override
	public List<UserGroupVo> findUserGroupByPageBean2(UserGroupVo vo) {
		//只负责查询isKeeper为1孕妈,4宝妈,5备孕
		List<UserGroupVo> list = mapper.selectUserGroupByPageBean2(vo);
		List<Map<String,Object>> chatIds = getChatIdsByGroupId(vo.getGroupId());
		logger.info("findUserGroupByPageBean---------> chatIds ："+JsonUtils.toJSONString(chatIds));
		if(list !=null  && list.size() >0){
			for(UserGroupVo vo1 :list){
				if(chatIds != null){
					String userid = vo1.getUserId();
					String chatId = "101_yh_" + userid;
					for (Map<String, Object> map : chatIds) {
						if(chatId.equals(map.get("Member_Account").toString()) 
								&& map.get("LastSendMsgTime").toString().length() > 5){ //如果 chatIds 中的chatId == chatId
							vo1.setSpeakingTime(Long.parseLong(map.get("LastSendMsgTime").toString()+"000"));
						}
					}
				}
			}
			for(UserGroupVo vo1 :list){
				vo1.setCreateDateTime(TimeUtils.convertLongToTimestampString("yyyy/MM/dd HH:mm:ss",vo1.getCreateTime()));
				if(vo1.getSpeakingTime() == null ||vo1.getSpeakingTime()<=0){
					vo1.setSpeakingDateTime("");
				}else{//格式化时间
					vo1.setSpeakingDateTime(TimeUtils.convertLongToTimestampString("yyyy/MM/dd HH:mm:ss",vo1.getSpeakingTime()));
				}
			}
		}
		return list;
	}
}