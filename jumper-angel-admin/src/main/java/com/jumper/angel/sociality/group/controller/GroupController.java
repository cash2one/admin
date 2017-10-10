package com.jumper.angel.sociality.group.controller;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.sociality.group.model.bo.SocialityGroupBo;
import com.jumper.angel.sociality.group.model.po.SocialityGroupPo;
import com.jumper.angel.sociality.group.model.vo.SocialityGroupVo;
import com.jumper.angel.sociality.group.service.GroupService;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;
import com.jumper.angel.utils.StringUtil;
/**
 * 社交 交流圈 PC端Controller
 * 
 * @ClassName: IMGroupController
 * @author huangzg 2016年12月23日 下午4:39:52
 */
@Controller
@RequestMapping("/sociality/group")
public class GroupController {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private GroupService service;

	
	/**
	 * 初始化页面
	 * @author huangzg 2016年12月27日 下午5:31:39  
	 * @param mv
	 * @return        
	 * @throws
	 */
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView init(ModelAndView mv){
		mv.setViewName("sociality/group/group_list");
		return mv;
	}
	
	/**
	 * 分页检索查询交流圈信息
	 * @author huangzg 2016年12月27日 下午5:31:50  
	 * @param vo 参数集
	 * @param page 页码
	 * @param rows 每页条数
	 * @return        
	 * @throws
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg list(
			@RequestParam("pageNo") int pageNo, 
			@RequestParam("rows") int rows, 
			@RequestParam("groupName") String groupName, 
			@RequestParam("first") boolean first,
			@RequestParam("dataStatus") String dataStatus,
			@RequestParam("allowAdd") String allowAdd
			){	
		SocialityGroupVo vo = new SocialityGroupVo();
		if (StringUtils.isNotEmpty(groupName)){
			vo.setGroupName(groupName);
		}if(StringUtils.isNotEmpty(dataStatus)){
			vo.setDataStatus(Integer.parseInt(dataStatus));
		}if(StringUtils.isNotEmpty(allowAdd)){
			vo.setAllowAdd(Integer.parseInt(allowAdd));
		}
		return service.sel(vo, pageNo, rows, first);
	}
	
	/**
	 * 添加用户圈子
	 * @version 1.0
	 * @createTime 2016-12-26,下午5:53:02
	 * @updateTime 2016-12-26,下午5:53:02
	 * @createAuthor TSL
	 * @updateAuthor
	 * @param pregnancyInfo
	 * @return
	 */
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg create(@RequestBody SocialityGroupVo param, HttpServletRequest request){	
		return service.create(param, request);
	}
	
	/**
	 * 编辑圈子(修改)
	 * @version 1.0
	 * @createTime 2016-12-26,下午5:53:02
	 * @updateTime 2016-12-26,下午5:53:02
	 * @createAuthor TSL
	 * @updateAuthor
	 * @param pregnancyInfo
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg edit(@RequestBody SocialityGroupVo param,HttpServletRequest request){
		if(StringUtils.isEmpty(String.valueOf(param.getId()))){
			return new ResultMsg(Status.FAILED, "ID不能为空!");
		}
		if(StringUtils.isEmpty(param.getGroupName())){
			return new ResultMsg(Status.FAILED, "圈子名称不能为空!");
		}
//		if(StringUtils.isEmpty(param.getCoverUrl())){
//			return new ResultMsg(Status.FAILED, "圈子封面不能为空!");
//		}
		if(StringUtils.isEmpty(param.getGroupBriefIntr())){
			return new ResultMsg(Status.FAILED, "圈子简介不能为空!");
		}
		try {
			logger.info(param.getId());
			//获取登录用户
			CrmAdmin monitor = (CrmAdmin)request.getSession().getAttribute("user");
			int i =service.edit(param,monitor);
			if(i>0){
				return new ResultMsg(Status.SUCCESS, "编辑圈子成功!");
			}else{
				return new ResultMsg(Status.FAILED, "编辑圈子失败!");
			}
			
		} catch (Exception e) {
			logger.error("GroupMangerController>getGroupList error",e);
			return new ResultMsg(Status.FAILED, "编辑信息失败-!");
		}
		
		
	}
	/**
	 * 编辑圈子（先查询后修改）
	 * @version 1.0
	 * @createTime 2016-12-26,下午5:53:02
	 * @updateTime 2016-12-26,下午5:53:02
	 * @createAuthor TSL
	 * @updateAuthor
	 * @param pregnancyInfo
	 * @return
	 * 
	 */
	@RequestMapping(value="/getDataById",method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg getDataById(@RequestParam("id") String id){
		if(StringUtils.isEmpty(id)){
			return new ResultMsg(Status.FAILED, "话题组ID不能为空!");
		}
		try {
			SocialityGroupPo po = service.getDataById(Integer.parseInt(id));
			if(po !=null){
				return new ResultMsg(Status.SUCCESS, "查询话题组信息成功!",JSONObject.toJSON(po));
			}else{
				return new ResultMsg(Status.FAILED, "查询话题组信息失败!");
			}
			
		} catch (Exception e) {
			logger.error("TopicMangerController>getTopicList error",e);
			return new ResultMsg(Status.FAILED, "查询话题组信息失败!");
		}

	}	
	/**
	 * 启用禁用   
	 * @version 1.0
	 * @createTime 2016-12-26,下午5:53:02
	 * @updateTime 2016-12-26,下午5:53:02
	 * @createAuthor TSL
	 * @updateAuthor
	 * @param pregnancyInfo
	 * @return
	 * 
	 */
	@RequestMapping(value="/disableOrEnableGroup",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg disableOrEnableGroup(@RequestBody SocialityGroupPo po,HttpServletRequest request){
		if(StringUtils.isEmpty(String.valueOf(po.getId()))){
			return new ResultMsg(Status.FAILED, "话题ID不能为空!");
		}if(StringUtils.isEmpty(String.valueOf(po.getDataStatus()))){
			return new ResultMsg(Status.FAILED, "您的操作选项不能为空!");
		}try {
			//获取登录用户
			CrmAdmin monitor = (CrmAdmin)request.getSession().getAttribute("user");
			int i =service.edit(po,monitor);
			logger.info(po.getId());
			if(i>0){
				return new ResultMsg(Status.SUCCESS, "操作成功!");
			}else{
				return new ResultMsg(Status.FAILED, "操作失败");
			}
		} catch (Exception e) {
			logger.error("TopicMangerController>getTopicList error",e);
			return new ResultMsg(Status.FAILED, "操作失败!");
		}
	}
	
	/**
	 * 置顶
	 * @version 1.0
	 * @createTime 2016-12-26,下午5:53:02
	 * @updateTime 2016-12-26,下午5:53:02
	 * @createAuthor TSL
	 * @updateAuthor
	 * @param pregnancyInfo
	 * @return
	 */
	@RequestMapping(value="/groupStickyPost",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg groupStickyPost(@RequestBody SocialityGroupPo po,HttpServletRequest request){
		if(StringUtils.isEmpty(String.valueOf(po.getId()))){
			return new ResultMsg(Status.FAILED, "ID不能为空!");
		}if(StringUtils.isEmpty(String.valueOf(po.getOverhead()))){
			return new ResultMsg(Status.FAILED, "您的操作不正确");
		}try {
			//获取登录用户
			CrmAdmin monitor = (CrmAdmin)request.getSession().getAttribute("user");
			int i =service.edit(po,monitor);
			logger.info(po.getId());
			if(i>0){
				return new ResultMsg(Status.SUCCESS, "操作成功!");
			}else{
				return new ResultMsg(Status.FAILED, "操作失败");
			}
		} catch (Exception e) {
			logger.error("TopicMangerController>getTopicList error",e);
			return new ResultMsg(Status.FAILED, "操作失败!");
		}
	}
	
	
/**
 * 	排序
 * @RequestMapping(value="/getGroupByBean")
 * @param groupName
 * @return
 */
	@RequestMapping(value="/getGroupByBean",method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg getGroupByBean(@RequestParam("groupName") String groupName){
		
		try {
			SocialityGroupVo groupvo = new SocialityGroupVo ();
			groupvo.setGroupName(groupName);
			List<SocialityGroupBo> list = service.findTGroupByBean(groupvo);
			List<SocialityGroupVo> voList = new ArrayList<SocialityGroupVo>();
			if(list !=null  && list.size() >0){
				SocialityGroupVo vo =null;
				for(SocialityGroupBo  bo : list){
					vo = new SocialityGroupVo();
					vo.setGroupName(bo.getGroupName());
					vo.setAllowAdd(bo.getAllowAdd());
					vo.setCreateUserId(bo.getCreateUserId());
					vo.setCreateUserTime(bo.getCreateUserTime());
					vo.setGroupId(bo.getCreateUserId());
					vo.setPingYin(StringUtil.getPingYin(vo.getGroupName()));
				}
				//按照首字母进行排序
				Collections.sort(voList, new Comparator<SocialityGroupVo>(){
					public int compare(SocialityGroupVo o1, SocialityGroupVo o2) {
						String str1 = StringUtil.getFirstSpell(o1.getGroupName()).substring(0,1);
						String str2 = StringUtil.getFirstSpell(o2.getGroupName()).substring(0, 1);
						return Collator.getInstance(Locale.CHINESE).compare(str1, str2); 
					}
				});
				return new ResultMsg(Status.SUCCESS,"获取圈子信息成功",list);
			}else{
				return new ResultMsg(Status.FAILED,"没有查询到圈子信息",new JSONArray(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(Status.FAILED, "获取圈子信息失败?!");
		}
	}
	
	/**
	 * 初始化页面数据
	 * 交流圈	统计值的初始化
	 * 交流圈总数	除掉禁用
	 * 交流圈总人数	除掉禁用
	 */
	@RequestMapping(value="/numCount",method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg get() {
		try {
			return new ResultMsg(Status.SUCCESS, "初始化数据成功！", service.numCount());
		} catch (Exception e) {
			logger.info("初始化页面数据 msg :"+e.getMessage(), e);
		}
		return new ResultMsg(Status.SUCCESS, "初始化数据成功！");
	}
}


