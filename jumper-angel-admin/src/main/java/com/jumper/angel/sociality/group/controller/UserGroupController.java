package com.jumper.angel.sociality.group.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.sociality.group.model.po.UserGroupPo;
import com.jumper.angel.sociality.group.model.vo.UserGroupVo;
import com.jumper.angel.sociality.group.model.vo.UserinfoVo;
import com.jumper.angel.sociality.group.service.GroupService;
import com.jumper.angel.sociality.group.service.UserGroupService;
import com.jumper.angel.sociality.operationrecord.mapper.OperationRecordMapper;
import com.jumper.angel.sociality.util.Constans;
import com.jumper.angel.sociality.util.HttpPost;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.Result;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;
import com.jumper.angel.utils.json.JsonUtils;
/**
 * 社交 交流圈成员管理
 * 
 * @ClassName: UserGroupController
 * @author huangzg 2016年12月24日 上午10:02:03
 */
@Controller
@RequestMapping("/group/user")
public class UserGroupController {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserGroupService service;	
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private OperationRecordMapper operationRecordMapper;
	
	/**
	 * 初始化页面
	 * @author STL 2016年1月9日 下午15:31:39  
	 * @param mv
	 * @return        
	 * @throws
	 */
	@RequestMapping(value = "/groupinit", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView groupinit(ModelAndView mv){
		mv.setViewName("sociality/group/group_listuser");
		return mv;
	}
	/**
	 * 分页检索查询交流圈信息
	 * @author huangzg 2016年12月24日 上午11:00:33  
	 * @param vo 参数集
	 * @param page 页码
	 * @param rows 每页条数
	 * @return        
	 * @throws
	 */
	@RequestMapping(value ="/getUserGroupList",method = RequestMethod.GET)
	@ResponseBody
	public ResultMsg getUserGroupList(
			@RequestParam("beginIndex") int beginIndex,
		    @RequestParam("everyPage") int everyPage,
		    @RequestParam("groupId") String groupId,
		    @RequestParam("isKeeper") String isKeeper,//管理
		    @RequestParam("mobile") String mobile,//电话
		    @RequestParam("nickName") String nickName,//用户昵称
		    @RequestParam("isBlacklist") String isBlacklist,//禁言
		    CrmAdmin monitor,HttpServletRequest request){
			try {
				if(StringUtils.isBlank(groupId)){
					return new ResultMsg(Status.FAILED, "名称不能为空!");
				}
				UserGroupVo vo = new UserGroupVo();
				vo.setBeginIndex(beginIndex);
				vo.setEveryPage(everyPage);
				vo.setGroupId(groupId);
				vo.setIsBlacklist(isBlacklist==""?null:Long.parseLong(isBlacklist));
			//	vo.setIsKeeper(isKeeper==""?null:Long.parseLong(isKeeper));
				if(StringUtils.isNotEmpty(mobile)){
					vo.setMobile(mobile);
				}
				if(StringUtils.isNotEmpty(nickName)){
					vo.setNickName(nickName);
				}
				Page page = new Page();
				page.setCurrentPage(beginIndex);//设置第几页
				page.setEveryPage(everyPage);	//每页显示的记录数s
				int count = 0;
				if(isKeeper==""){//未传用户身份类型
					vo.setIsKeeper(null);
					count = service.selectCountUserGroupByBean(vo);
					page = PageUtil.createPage(page, count);
					if(beginIndex<=1){
						vo.setBeginIndex(beginIndex-1);
					}else{
						vo.setBeginIndex((beginIndex-1)*15);
					}		
					List<UserGroupVo> list = service.findUserGroupByPageBean(vo);
					//分页结果
					Result result = new Result(page, list);
					if(list !=null && list.size() >0){
						return new ResultMsg(Status.SUCCESS, "获取用户成功!", result);
					}else{
						return new ResultMsg(Status.FAILED, "没有查询到信息!", result);
					}
				}else{
					if(Integer.parseInt(isKeeper)==2||Integer.parseInt(isKeeper)==3){//isKeeper医生3和管理员2在本地T_IM_USER_GROUP查询
						vo.setIsKeeper(Long.valueOf(isKeeper));
						count = service.selectCountUserGroupByBean(vo);
						page = PageUtil.createPage(page, count);
						if(beginIndex<=1){
							vo.setBeginIndex(beginIndex-1);
						}else{  
							vo.setBeginIndex((beginIndex-1)*15);
						}		
						List<UserGroupVo> list = service.findUserGroupByPageBean(vo);
						//分页结果
						Result result = new Result(page, list);
						if(list !=null && list.size() >0){
							return new ResultMsg(Status.SUCCESS, "获取用户成功!", result);
						}else{
							return new ResultMsg(Status.FAILED, "没有查询到信息!", result);
						}
					}else{//isKeeper 1孕妈,4宝妈,5备孕 跨表user_extra_info查询 currentIdentity(0：怀孕中  1：已有宝宝  2：备孕期)字段
						vo.setIsKeeper(0l);
						vo.setCurrentIdentity(Integer.parseInt(isKeeper)==1?0:(Integer.parseInt(isKeeper)==4?1:2));
						count = service.selectCountUserGroupByBean2(vo);
						page = PageUtil.createPage(page, count);
						if(beginIndex<=1){
							vo.setBeginIndex(beginIndex-1);
						}else{
							vo.setBeginIndex((beginIndex-1)*15);
						}		
						List<UserGroupVo> list = service.findUserGroupByPageBean2(vo);
						if(list!=null&&list.size()>0){
							for(UserGroupVo ug:list){
								//把user_extra_info中currentIdentity字段值与isKeeper对应起来
								ug.setIsKeeper(ug.getCurrentIdentity()==0?1l:(ug.getCurrentIdentity()==1?4l:5l));
							}
							//分页结果
							Result result = new Result(page, list);
							return new ResultMsg(Status.SUCCESS, "获取用户成功!", result);
						}else{
							return new ResultMsg(Status.FAILED, "没有查询到信息!");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResultMsg(Status.FAILED, "获取用户信息失败!");
			}
	}
	/**
	 * 分页检索查询交流圈信息
	 * @author huangzg 2016年12月24日 上午11:00:33  
	 * @param vo 参数集
	 * @param page 页码
	 * @param rows 每页条数
	 * @return        
	 * @throws
	 */
/*	@RequestMapping(value ="/getUserGroupList",method = RequestMethod.GET)
	@ResponseBody
	public ResultMsg getUserGroupList(
			@RequestParam("beginIndex") int beginIndex,
		    @RequestParam("everyPage") int everyPage,
		    @RequestParam("groupId") String groupId,
		    @RequestParam("isKeeper") String isKeeper,//管理
		    @RequestParam("mobile") String mobile,//电话
		    @RequestParam("nickName") String nickName,//用户昵称
		    @RequestParam("isBlacklist") String isBlacklist,//禁言
		    UserGroupPo po,CrmAdmin monitor,HttpServletRequest request, Integer mark
			){
			try {
				if(StringUtils.isBlank(groupId)){
					return new ResultMsg(Status.FAILED, "名称不能为空!");
				}
				UserGroupVo vo = new UserGroupVo();
				vo.setBeginIndex(beginIndex);
				vo.setEveryPage(everyPage);
				vo.setGroupId(groupId);
				logger.info("isBlacklist:"+isBlacklist);
				logger.info("isKeeper:"+isKeeper);
				vo.setIsBlacklist(isBlacklist==""?null:Long.parseLong(isBlacklist));
				//vo.setIsKeeper(isKeeper==""?null:Long.parseLong(isKeeper));
				if(StringUtils.isNotEmpty(mobile)){
					vo.setMobile(mobile);
				}if(StringUtils.isNotEmpty(nickName)){
					vo.setNickName(nickName);
				}
				Page page = new Page();
				page.setCurrentPage(beginIndex);//设置第几页
				page.setEveryPage(everyPage);	//每页显示的记录数s
				logger.info("pageIndex:"+beginIndex);
				logger.info("everyPage:"+everyPage);
				int count = service.selectCountUserGroupByBean(vo);
				page = PageUtil.createPage(page, count);
				if(beginIndex<=1){
					vo.setBeginIndex(beginIndex-1);
				}else{
				//	vo.setBeginIndex((beginIndex-1)*5);
					vo.setBeginIndex((beginIndex-1)*15);
				}		
				List<UserGroupVo> list = service.findUserGroupByPageBean(vo);
				
				if (!list.isEmpty()){
					for (UserGroupVo userGroupVo : list) {//遍历后台查询到的每个圈子成员
						if(userGroupVo.getIsKeeper() == 1){
							//==========================================
							if(StringUtils.isBlank(String.valueOf(po.getUserId()))){
								return new ResultMsg(Status.FAILED, "用户id不能为空!");
							}if(StringUtils.isEmpty(String.valueOf(po.getGroupId()))){
								return new ResultMsg(Status.FAILED, "圈子id不能为空!");
							}
							List<UserinfoVo> lists =service.selUserInfo(userGroupVo.getGroupId(), userGroupVo.getUserId());
							List<Map<String, Object>> accounts = new ArrayList<Map<String,Object>>();
							String params = "{\"GroupId\":\""+groupId+"\"}";
							// 根据groupId  发送请求获取 圈子中所有成员
							String returnVal = HttpPost.doPost(Constans.GET_GROUP_MEMBER_INFO, params);
							HashMap<String, Object> dataMap = JsonUtils.toHashMap(returnVal);
							
							if (!StringUtils.isEmpty(dataMap.get("ErrorCode").toString()) 
									&& "0".equals(dataMap.get("ErrorCode").toString())){ // 请求结果成功
								Object obj = dataMap.get("MemberList");
								accounts = JsonUtils.toList(obj);
							}
							for (UserinfoVo userInfo : lists){
								long expectedDate = userInfo.getExpectedDateOfConfinement().getTime();
									// 判断用户预产期大于当前时间则为已生宝宝（宝妈）
									if (userInfo.getExpectedDateOfConfinement()!= null){  //判断时间是否为空 1  
												if (expectedDate < new Date().getTime()){ //判断 预产期是否 小于当前时间  
													po.setIsKeeper(1L);
													mark=1;
												}else if(expectedDate < new Date().getTime()) {		//若小于当前时间则为孕妈
													mark=4;
												}
											
									}else{		//若为空则宝妈
										mark=4;
									}			
									po.setUserId(userGroupVo.getUserId());
									po.setGroupId(userGroupVo.getGroupId());
									// po.getIsKeeper().intValue()
									groupService.editImGroupUserNameCard(request, po.getGroupId(), po.getUserId(),mark, accounts, monitor);
							}
						
						}//每次查询是否是孕妇 才进这个方法
						//==========================================
					}
				}
				//分页结果
				Result result = new Result(page, list);
				if(list !=null && list.size() >0){
					return new ResultMsg(Status.SUCCESS, "获取用户成功!", result);
				}else{
					return new ResultMsg(Status.FAILED, "没有查询到信息!", result);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResultMsg(Status.FAILED, "获取用户信息失败!");
			}
	}*/
	/**
	 * 编辑交流圈成员信息
	 * @author huangzg 2016年12月24日 上午11:00:17  
	 * @param vo 参数集
	 * @return        isBlacklistGroup
	 * @throws
	 */
	public ModelAndView edit(@RequestBody UserGroupVo vo) {
		int i = service.edit(vo);
		System.out.println(i);
		return null;
	}

	/**
	 * 设为管理员
	 * @author huangzg 2016年12月24日 上午11:00:17  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 */
	@RequestMapping(value="/showDiv3",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg showDiv3(@RequestBody UserGroupPo po,HttpServletRequest request, 
			String groupId, String userId, Integer mark,CrmAdmin monitor){
		if(StringUtils.isBlank(String.valueOf(po.getUserId()))){
			return new ResultMsg(Status.FAILED, "UserId不能为空!");
		}
		if(StringUtils.isEmpty(String.valueOf(po.getGroupId()))){
			return new ResultMsg(Status.FAILED, "您的操作选项不能为空!");
		}if(StringUtils.isEmpty(String.valueOf(po.getIsKeeper()))){
			return new ResultMsg(Status.FAILED, "操作选项为空！");
		}
		try {
			if(po.getIsKeeper()==1){
				 mark=1;
			}else if(po.getIsKeeper()==2){
				mark=2;
			}else if(po.getIsKeeper()==3){
				mark=3;
			}else if(po.getIsKeeper()==4){
				mark=4;
			}else if(po.getIsKeeper()==5){
				mark=5;
			}
			
			/**
			 * huangzg 2016-01-12 add 
			 * 更新腾讯IM中所在交流圈成员的群名片
			 */
			List<Map<String, Object>> accounts = new ArrayList<Map<String,Object>>();
			String params = "{\"GroupId\":\""+po.getGroupId()+"\"}";
			String returnVal = HttpPost.doPost(Constans.GET_GROUP_MEMBER_INFO, params);
			HashMap<String, Object> dataMap = JsonUtils.toHashMap(returnVal);
			logger.info("showDiv3 设置管理员或医生returnVal----------->"+returnVal);
			if (!StringUtils.isEmpty(dataMap.get("ErrorCode").toString()) 
					&& "0".equals(dataMap.get("ErrorCode").toString())){
				Object obj = dataMap.get("MemberList");
				accounts = JsonUtils.toList(obj);
			}
			System.out.println("mark:"+mark);
			System.out.println("accounts:"+accounts);
			return groupService.editImGroupUserNameCard(request, po.getGroupId(),po.getUserId(), mark, accounts, monitor);
		} catch (Exception e) {
			logger.error("error===>",e);
			return new ResultMsg(Status.FAILED, "操作失败!");
		}
	}
	
	/**
	 * 禁言
	 * @author huangzg 2016年12月24日 上午11:00:17  
	 * @param vo 参数集
	 * @return        
	 * @throws
	 */
	@RequestMapping(value="/isBlacklistGroup",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg isBlacklistGroup(@RequestBody UserGroupPo po,CrmAdmin monitor){

		if(StringUtils.isBlank(String.valueOf(po.getId()))){
			return new ResultMsg(Status.FAILED, "ID不能为空!");
		}
		if(StringUtils.isEmpty(String.valueOf(po.getIsBlacklist()))){
			return new ResultMsg(Status.FAILED, "您的操作选项不能为空!");
		}
		if(StringUtils.isBlank(String.valueOf(po.getUserId()))){
			return new ResultMsg(Status.FAILED, "UserId不能为空!");
		}
		try {
			//?  
			po.setIsBlacklist(po.getIsBlacklist());
			po.setUserId(po.getUserId());;
			po.setGroupId(po.getGroupId());
			int i = service.update(po, monitor);
			if(i>0){
				if(po.getIsBlacklist()==0){
					return new ResultMsg(Status.SUCCESS, "解除禁言成功!");
				}else{
					return new ResultMsg(Status.SUCCESS, "禁言成功!");
				}
			}else{
				if(po.getIsBlacklist()==0){
					return new ResultMsg(Status.FAILED, "解除禁言失败");
				}else{
					return new ResultMsg(Status.FAILED, "禁言失败");
				}
			}
		} catch (Exception e) {
			logger.error("error===>",e);
			return new ResultMsg(Status.FAILED, "禁言或解除禁言失败!");
		}
	}
	
	/**
	 * 初始化页面数据
	 * 交流圈  成员管理	统计值的初始化
	 * @author huangzg 2017年1月14日 下午2:18:32
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/numCount",method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg get(@RequestParam("groupId") String groupId) {
		try {
			return new ResultMsg(Status.SUCCESS, "初始化数据成功！", service.numCount(groupId));
		} catch (Exception e) {
			logger.info("初始化页面数据 msg :"+e.getMessage(), e);
		}
		return new ResultMsg(Status.SUCCESS, "初始化数据成功！");
	}
}