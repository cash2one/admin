package com.jumper.angel.sociality.group.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.hospital.doctor.entity.UserInfoBean;
import com.jumper.angel.hospital.doctor.mapper.UserInfoBeanMapper;
import com.jumper.angel.sociality.BaseIMService;
import com.jumper.angel.sociality.group.mapper.GroupMapper;
import com.jumper.angel.sociality.group.model.bo.SocialityGroupBo;
import com.jumper.angel.sociality.group.model.po.SocialityGroupPo;
import com.jumper.angel.sociality.group.model.po.UserGroupPo;
import com.jumper.angel.sociality.group.model.vo.QrcInfoBean;
import com.jumper.angel.sociality.group.model.vo.SocialityGroupVo;
import com.jumper.angel.sociality.group.service.GroupService;
import com.jumper.angel.sociality.group.service.UserGroupService;
import com.jumper.angel.sociality.operationrecord.mapper.OperationRecordMapper;
import com.jumper.angel.sociality.operationrecord.model.po.OperationRecordPO;
import com.jumper.angel.sociality.util.Constans;
import com.jumper.angel.sociality.util.HttpPost;
import com.jumper.angel.utils.ConfigProUtils;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.QRCodeUtil;
import com.jumper.angel.utils.RandomUtils;
import com.jumper.angel.utils.Result;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;
import com.jumper.angel.utils.StringUtil;
import com.jumper.angel.utils.TimeUtils;
import com.jumper.angel.utils.file.FastdfsUpload;
import com.jumper.angel.utils.json.JsonUtils;

/** 
 * 社交后台  交流圈ServiceImpl
 * @ClassName: IMGroupServiceImpl  
 * @author huangzg 2016年12月23日 下午6:14:49   
 */
@Service("imGroupServiceImpl")
public class GroupServiceImpl extends BaseIMService<Object>  implements GroupService {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private GroupMapper mapper;
	@Autowired
	private UserInfoBeanMapper userMapper;
	@Autowired 
	private OperationRecordMapper operationRecordMapper;
	
	@Autowired
	private UserInfoBeanMapper userInfoBeanMapper;
	
	@Autowired //设置成员标签的方法（修改到数据库中）
	private GroupService groupService;
	@Autowired
	private UserGroupService service;
	
	/**
	 * 分页检索查询交流圈信息
	 * @author huangzg 2016年12月23日 下午6:05:23  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 */
	@Override
	public ResultMsg sel(SocialityGroupVo vo, Integer pageNo, Integer rows, boolean first){
		try {
			Page page = new Page();			//实例化Page对象
			page.setCurrentPage(pageNo);	//设置第几页
			page.setEveryPage(rows);		//每页显示的记录数
			int counts = mapper.getCounts();	//总记录数
			page = PageUtil.createPage(page, counts);		//分页工具类，计算分页数
			if(first) {
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			if(pageNo<=1){
				vo.setPage(pageNo-1);
			}else{
				vo.setPage((pageNo-1)*15);
			}
			vo.setRows(rows);
			logger.info("pageNo"+pageNo);
			logger.info("rows"+rows);
			List<SocialityGroupBo> list = mapper.sel(vo);	//分页查询
			
			
			List<String> groupIds = new ArrayList<String>(); // 获取交流圈 GROUP_ID
			for(SocialityGroupBo socialityGroupBo : list){
				groupIds.add(socialityGroupBo.getGroupId());
			}
			
			if(groupIds.size() > 0){ // groupIds 中有交流圈ID
				mapper.updateGroupNumber(groupIds); // 更新交流圈人数
			}
			
			Result result = new Result(page, list);
			if(result.getContent()!=null && result.getContent().size()>0) {
				return new ResultMsg(Status.SUCCESS, "获取信息成功成功！", result.getContent());
			} else {
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.error("GroupServiceImpl>GroupServiceImpl error",e);
			return new ResultMsg(Status.FAILED, "失败！");
		}
	}
	
	@Override	   
	public  List<SocialityGroupBo> GroupCreateTime(SocialityGroupVo vo) {
	  List<SocialityGroupBo> list = mapper.sel(vo);
	  if(list !=null  && list.size() >0){
			for(SocialityGroupBo vo1 :list){//时间  
				vo1.setCreateDateTime(TimeUtils.convertLongToTimestampString("yyyy/MM/dd HH:mm:ss",vo1.getCreateUserTime()));
			}
		}
		return list;
	}
	
	
	/**
	 * 新建交流圈
	 * @author huangzg 2016年12月23日 下午6:20:58  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 */
	@Override
	public ResultMsg create(SocialityGroupVo vo,HttpServletRequest request) {
		//获取登录用户
		CrmAdmin monitor = (CrmAdmin)request.getSession().getAttribute("user");
		addOperatorRecord(vo,monitor);
		if (StringUtils.isEmpty(vo.getGroupName())){
			return new ResultMsg(Status.NODATA, "交流圈名称不许为空!", new ArrayList<Object>());
		}
		/** 生成随机交流圈ID */
		String groupId = RandomUtils.randomInt(14);
		vo.setGroupId(groupId);
		/** logo path */
		String logoPath = request.getSession().getServletContext().getRealPath("/assets/images/base/logo90px.png"); 
		/** 二维码 path */
		String imgPath = request.getSession().getServletContext().getRealPath("/assets/images/base/"+new Date().getTime()+".png");//String.valueOf(new Date().getTime())
		QrcInfoBean qrcInfoBean = new QrcInfoBean("1", vo.getGroupId(), vo.getGroupName(), vo.getGroupBriefIntr(), null, null);
		JSONObject jsonObject = JsonUtils.toJSONObject(qrcInfoBean);
		try {
			if (monitor.getId() == 0){
				return new ResultMsg(Status.FAILED, "创建交流圈失败", new ArrayList<Object>());
			}
			vo.setCreateUserId(String.valueOf(monitor.getId()));
			String create_group = "{\"Type\": \"Public\", \"GroupId\": \""+groupId+"\", \"Name\": \""+vo.getGroupName()+"\"}";
			String val = sendPost(Constans.CREATE_GROUP, create_group);
			HashMap<String, Object> param = JsonUtils.toHashMap(val);
			if (!StringUtils.isEmpty(param.get("ErrorCode").toString()) 
					&& "0".equals(param.get("ErrorCode").toString())){
				jsonObject.remove("desc");
				jsonObject.remove("time");
				jsonObject.remove("other");
				jsonObject.remove("name");
				QRCodeUtil.getQrcodeImgByContents(jsonObject.toString(), imgPath, logoPath,7);
				String filePath = FastdfsUpload.upoladFile("jpg", new FileInputStream(new File(imgPath)));
				
				vo.setGroupId(groupId);
				vo.setGroupImgUrl(filePath);
				vo.setCreateUserTime(new Date().getTime());
				vo.setDataStatus(1);
				vo.setOverhead(2);
				vo.setAllowAdd(vo.getAllowAdd());//
				vo.setCreateUserId(String.valueOf(monitor.getId()));
				vo.setCoverUrl(vo.getCoverUrl());
				int i = mapper.save(vo);
				if (i > 0){
					return new ResultMsg(Status.SUCCESS, "创建交流圈成功", new ArrayList<Object>());
				}
			}
		} catch (Exception e) {
			logger.info("新建交流圈 msg "+e.getMessage(), e);
		}
		return new ResultMsg(Status.NODATA, "创建交流圈失败", new ArrayList<Object>());
	}

	/**
	 * 编辑交流圈
	 * @author huangzg 2016年12月23日 下午6:21:11  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 */
	@Override
	public int edit(SocialityGroupPo po,CrmAdmin monitor) {
		try {
			List<UserInfoBean> userInfoBeans = userInfoBeanMapper.selUserInfo();
			if(userInfoBeans.size() > 0){
				for (UserInfoBean userInfo : userInfoBeans){
					userInfo.setNickName("天使用户"+RandomUtils.randomInt(7));
					userInfoBeanMapper.updateByPrimaryKeySelective(userInfo);
				}
			}
			this.addOperatorRecord(po, monitor);
			po.setCoverUrl(po.getCoverUrl());
			int i = mapper.edit(po);
			if (i> 0){
				SocialityGroupPo sgpo = mapper.getDataById(po.getId());
				String groupId = sgpo ==null?"":sgpo.getGroupId();
				String param = "{\"GroupId\": \""+ groupId +"\"}";
				if(!StringUtil.isEmpty(po.getGroupName())){
					param = "{\"GroupId\": \""+ groupId +"\", \"Name\": \""+po.getGroupName()+"\"}";
				}
				
				String updateResult = sendPost(Constans.MODIFY_GROUP_BASE_INFO, param);
				logger.info("修改圈子名称返回结果：updateResult =" + updateResult);
				return i;
			}
		} catch (Exception e) {
			logger.error("groupServiceImpl.editTopic error",e);
		}
		return 0;
	}
	/**
	 * 编辑交流圈
	 * @author huangzg 2016年12月23日 下午6:21:11  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 * 编辑交流圈信息通过id来查询里面的数据
	 */
	@Override
	public SocialityGroupPo getDataById(int id) {
		try{
			return mapper.getDataById(id);
		}catch(Exception e){
			logger.error("TopicManagerServiceImpl.findTopic error",e);
		}
		return null;
	}
	

	/**
	 * 删除交流圈
	 * @author huangzg 2016年12月23日 下午6:21:29  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 */
	@Override
	public ResultMsg del(SocialityGroupVo vo) {
		if (vo.getId() == 0){
			return new ResultMsg(Status.NODATA, "请选择交流圈", new ArrayList<Object>());
		}
		try {
			int i = mapper.edit(vo);
			if (i > 0){
				return new ResultMsg(Status.NODATA, "编辑交流圈成功!", new ArrayList<Object>());
			}
		} catch (Exception e) {
			logger.info("编辑交流圈 Msg "+e.getMessage(), e);
		}
		return new ResultMsg(Status.NODATA, "编辑交流圈失败!", new ArrayList<Object>());
	}

	@Override
	public List<SocialityGroupBo> findTGroupByBean(SocialityGroupVo vo) {
		return mapper.selectGroupByBean(vo);
	}
	
	/**
	 * 话题组成员的操作增加记录
	 * @author yangsheng@angeldoctor 
	 * @param topicPO
	 * @param monitor
	 * @return
	 */
	private int addOperatorRecord(SocialityGroupPo socialityGroupPo,CrmAdmin monitor){
		OperationRecordPO operationRecordPO = new OperationRecordPO();
		String groupName = socialityGroupPo.getGroupName();
		if(groupName ==null || StringUtils.isBlank(groupName)){
			groupName = mapper.getDataById(socialityGroupPo.getId()).getGroupName();
		}
		if(socialityGroupPo.getId() ==null || socialityGroupPo.getId()<=0){
			operationRecordPO.setRecordContent("创建交流圈["+groupName+"]");
			operationRecordPO.setRecordType(15);
		}else if(socialityGroupPo.getDataStatus() !=null && socialityGroupPo.getDataStatus() ==0){
			operationRecordPO.setRecordContent("禁用交流圈["+groupName+"]");
			operationRecordPO.setRecordType(16);
		}else if(socialityGroupPo.getDataStatus() !=null && socialityGroupPo.getDataStatus() ==1){
			operationRecordPO.setRecordContent("启用交流圈["+groupName+"]");
			operationRecordPO.setRecordType(17);
		}else if(socialityGroupPo.getAllowAdd() !=null && socialityGroupPo.getAllowAdd() ==1){
			operationRecordPO.setRecordContent("将交流圈["+groupName+"]设为推荐");
			operationRecordPO.setRecordType(19);
		}else if(socialityGroupPo.getAllowAdd() !=null && socialityGroupPo.getAllowAdd() ==2){
			operationRecordPO.setRecordContent("将交流圈["+groupName+"]取消推荐");
			operationRecordPO.setRecordType(20);
		}else{
			operationRecordPO.setRecordContent("编辑交流圈["+groupName+"]");
			operationRecordPO.setRecordType(18);
		}
		if(monitor ==null){
			operationRecordPO.setRecordUserId("0");
			operationRecordPO.setRecordUserName("admin");
		}else{
			operationRecordPO.setRecordUserId(monitor.getId()+"");
			operationRecordPO.setRecordUserName(monitor.getName());
		}
		operationRecordPO.setRecordTime(new Date().getTime());
		return operationRecordMapper.insert(operationRecordPO);
	} 
	
	/**
	 * 设置交流圈成员管理的标签
	 * @author huangzg 2017年1月12日 下午5:41:43  
	 * @param request
	 * @param groupId 交流圈ID
	 * @param userId 用户ID 
	 * @param accountsType 帐号类型 1：医生 2：患者/用户 3：其他
	 * @param mark 标签	1：孕妈	2：管理员	3：医生	
	 * @return        
	 * @throws
	 */
	public ResultMsg editImGroupUserNameCard(HttpServletRequest request, 
			String groupId, String userId, Integer mark, List<Map<String, Object>> accounts, CrmAdmin monitor){
		UserInfoBean userInfo = userMapper.selectByPrimaryKey(Integer.parseInt(userId));
		String chatId = "101_"+userId;
		String openid = "yh_"+userId;
		String url = ConfigProUtils.get("GetChatIdUrl")+ "common/accounts/sel?openid="+openid+"&userId="+userId;
		try {
			String val = HttpPost.doPost(url, "");
			HashMap<String, Object> paramMap = JsonUtils.toHashMap(val);
			String msg = paramMap.get("msg").toString();
			if ("1".equals(msg)){
				HashMap<String, Object> chatIdMap = JsonUtils.toHashMap(paramMap.get("data"));
				chatId = chatIdMap.get("chatId").toString();
			} else{													
				String post = ConfigProUtils.get("GetChatIdUrl")+"common/accounts/add?userId="+userId+"&openid="+openid+"&"
						+"appid=101&userAvatarImg="+userInfo.getUserImg()+"&userName="+userInfo.getNickName();
				String returnMsg = HttpPost.doPost(post, "");
				HashMap<String, Object> map = JsonUtils.toHashMap(returnMsg);
				String msgStatus = map.get("msg").toString();
				if ("1".equals(msgStatus)){
					HashMap<String, Object> chatIdMap = JsonUtils.toHashMap(map.get("data"));
					chatId = chatIdMap.get("chatId").toString();
				}
			}
			if(!accounts.isEmpty() && accounts.size() > 0){
				for (Map<String, Object> map : accounts){
					if (map.get("Member_Account") != null && 
							!StringUtils.isEmpty(map.get("Member_Account").toString())){
						String account = map.get("Member_Account").toString();
						if (chatId.equals(account)){
							String params = "{\"GroupId\": \""+groupId+"\",\"Member_Account\": \""+chatId+"\", \"NameCard\": \""+mark+"\"}";
							String returnVal = HttpPost.doPost(Constans.MODIFY_GROUP_MEMBER_INFO, params);
							HashMap<String, Object> param = JsonUtils.toHashMap(returnVal);
							if (!StringUtils.isEmpty(param.get("ErrorCode").toString()) 
									&& "0".equals(param.get("ErrorCode").toString())){
								UserGroupPo po = new UserGroupPo();
								po.setUserId(userId);
								po.setGroupId(groupId);
								po.setIsKeeper(Long.parseLong(String.valueOf(mark)));
								//改为医生或者管理员的时候需要更新时间，否则APP医生管理员排序有误
								if(mark==2||mark==3){
									po.setUpdateTime(new Date().getTime());
								}
								service.updateByBean(po, monitor);
								return new ResultMsg(Status.NODATA, "设置成功!", new ArrayList<Object>());
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.info("设置交流圈成员管理的标签 Msg "+e.getMessage(), e);
		}
		return new ResultMsg(Status.FAILED, "设置失败!", new ArrayList<Object>());
	}

	@Override
	public Map<String, Object> numCount() {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		
		int groupCount = 0;		//交流圈总数   除去禁用
		int groupUserCount = 0 ;		//交流圈总人数  除去禁用
		
		
		groupCount = mapper.groupCount();		//交流圈总数   除去禁用
		groupUserCount = mapper.groupUserCount();		//交流圈总人数  除去禁用
		
		map.put("groupCount", groupCount);
		map.put("groupUserCount", groupUserCount);
		return map;
	}
}


