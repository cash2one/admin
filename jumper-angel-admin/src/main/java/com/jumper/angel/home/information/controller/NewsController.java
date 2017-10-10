package com.jumper.angel.home.information.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumper.angel.home.information.entity.NewsChanels;
import com.jumper.angel.home.information.entity.NewsInformation;
import com.jumper.angel.home.information.service.NewsChanelsService;
import com.jumper.angel.home.information.service.NewsInformationCommentsService;
import com.jumper.angel.home.information.service.NewsInformationService;
import com.jumper.angel.home.information.vo.VONewsChanels;
import com.jumper.angel.home.information.vo.VONewsInformation;
import com.jumper.angel.home.information.vo.VONewsInformationComments;
import com.jumper.angel.utils.Jpush;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.PregnantStage;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;
import com.jumper.angel.utils.TimeUtils;

@Controller
@RequestMapping("news")
public class NewsController {
	
	private final static Logger logger = Logger.getLogger(NewsController.class);
	
	@Autowired
	private NewsChanelsService newsChanelsService;
	
	@Autowired
	private NewsInformationService newsInformationService;
	
	@Autowired
	private NewsInformationCommentsService commentsService;
	
	
	
	/**
	 * 跳转新闻频道列表页面
	 * @return
	 */
	@RequestMapping("forwardNewsChanelsList")
	public ModelAndView forwardNewsChanelsList(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("information/newsChanelsList");
		return mv;
	}
	
	/**
	 * 获取新闻频道分页数据
	 * @param pageIndex 分页索引
	 * @param everyPage 每页记录数
	 * @param first 是否是第一次
	 * @return
	 */
	@RequestMapping("findNewsChanelsList")
	@ResponseBody
	public ResultMsg findNewsChanelsList(@RequestParam("pageIndex") int pageIndex, @RequestParam("everyPage") int everyPage, 
			@RequestParam("first") boolean first){
		try {
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示记录数
			page.setEveryPage(everyPage);
			//查询总记录数
			int count = newsChanelsService.findCount();
			//计算分页
			page = PageUtil.createPage(page, count);
			if(first){
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			List<VONewsChanels> chanelsList = newsChanelsService.findNewsChanelsList(page.getBeginIndex(), page.getEveryPage());
			if(chanelsList != null && chanelsList.size() > 0){
				return new ResultMsg(Status.SUCCESS, "获取新闻频道数据成功！", chanelsList);
			}else{
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.error("findNewsChanelsList():"+e.getMessage());
			return new ResultMsg(Status.FAILED, "获取新闻频道数据失败！");
		}
		
	}
	
	
	/**
	 * 删除新闻频道
	 */
	@RequestMapping("deleteNewsChanels")
	@ResponseBody
	public ResultMsg deleteNewsChanels(@RequestParam("chanelId") int chanelId){
		boolean b = newsChanelsService.deleteNewsChanels(chanelId);
		if(b){
			return new ResultMsg(Status.SUCCESS, "删除资讯频道信息成功！", null);
		}else{
			return new ResultMsg(Status.FAILED, "删除资讯频道信息失败，请稍后重试！", null);
		}
	}
	
	/**
	 * 频道置上
	 */
	@RequestMapping("topNewsChanels")
	@ResponseBody
	public ResultMsg topNewsChanels(@RequestParam("chanelId") int chanelId){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("information/newsChanelsList");
		//当前的是第一个，就不用置上
		try {
			NewsChanels newsChanels = newsChanelsService.findNewsChanelsById(chanelId);
			if(newsChanels.getOrderBy()==1 || newsChanels.getOrderBy()==0){//正常情况下，第一个应该是排序为1，所以当我们要将第一个置上时，就直接返回;孕期知识永远置顶
				return new ResultMsg(Status.SUCCESS, null);
			}
			//当前的不是第一个，就与后面一个交换位置
			int orderBy = newsChanels.getOrderBy()-1;
			List<NewsChanels> chanelsList = newsChanelsService.findNewsChanelsByOrderBy(orderBy);
			if(chanelsList != null && chanelsList.size() > 0){
				for (NewsChanels chanels : chanelsList) {
					chanels.setOrderBy(newsChanels.getOrderBy());
					boolean b = newsChanelsService.updateNewsChanels(chanels);
					if(!b){
						logger.info("topNewsChanels behind failed. id="+chanels.getId());
						return new ResultMsg(Status.FAILED, null);
					}
				}
			}
			newsChanels.setOrderBy(orderBy);
			boolean c = newsChanelsService.updateNewsChanels(newsChanels);
			if(!c){
				logger.info("topNewsChanels before failed. id="+newsChanels.getId());
				return new ResultMsg(Status.FAILED, null);
			}
		} catch (Exception e) {
			logger.info("topNewsChanels failed."+e.getMessage());
		}
		return new ResultMsg(Status.SUCCESS, null);
	}
	
	/**
	 * 检查频道名称是否已存在
	 */
	@RequestMapping("checkChanelsName")
	@ResponseBody
	public ResultMsg checkChanelsName(@RequestParam("chanelId") int chanelId, @RequestParam("chanelName") String chanelName){
		NewsChanels newsChanels = newsChanelsService.findNewsChanelByName(chanelId, chanelName);
		if(newsChanels != null){
			return new ResultMsg(Status.SUCCESS, null);
		}else{
			return new ResultMsg(Status.FAILED, null);
		}
		
	}
	
	/**
	 * 添加或修改频道信息
	 */
	@RequestMapping("addOrEditNewsChanels")
	@ResponseBody
	public ResultMsg addOrEditNewsChanels(@RequestParam("chanelId") int chanelId, @RequestParam("chanelName") String chanelName, 
			@RequestParam("chanelDesc") String chanelDesc, @RequestParam("isDefaultSub") int isDefaultSub, @RequestParam("imgUrl") String imgUrl, @RequestParam("chanelLabelArray") String chanelLabelArray){
		NewsChanels newsChanels = new NewsChanels();
		newsChanels.setId(chanelId);
		newsChanels.setChanelName(chanelName);
		newsChanels.setChannelDesc(chanelDesc);
		newsChanels.setIsDefaultSub(isDefaultSub==1);
		newsChanels.setImgUrl(imgUrl);
		newsChanels.setAddTime(TimeUtils.getCurrentTime());
		newsChanels.setHospitalId(49);//所用频道都为天使医院的频道
		newsChanels.setState((byte)1);
		//获取最大的排序值
		if(chanelId == 0){
			Integer maxOrder = newsChanelsService.getLastRecordByOrderBy();
			if(maxOrder != null && maxOrder != 0){
				newsChanels.setOrderBy(maxOrder+1);
			}else{
				newsChanels.setOrderBy(1);
			}
		}
		boolean b = newsChanelsService.addOrEditNewsChanels(newsChanels, chanelLabelArray);
		if(b){
			if(chanelId == 0){
				return new ResultMsg(Status.SUCCESS, "添加频道信息成功！", null);
			}else{
				return new ResultMsg(Status.SUCCESS, "修改频道信息成功！", null);
			}
		}else{
			if(chanelId == 0){
				return new ResultMsg(Status.FAILED, "添加频道信息失败！", null);
			}else{
				return new ResultMsg(Status.FAILED, "修改频道信息失败！", null);
			}
		}
	}
	
	
	/**
	 * 跳转资讯列表页面
	 */
	@RequestMapping("forwardNewsInformationList")
	public ModelAndView forwardNewsInformationList(){
		ModelAndView mv = new ModelAndView();
		//频道列表
		List<NewsChanels> newsChanels = newsChanelsService.findAllNewsChanels();
		//孕期阶段
		PregnantStage[] pregnantStage = PregnantStage.values();
		mv.addObject("newsChanels", newsChanels);
		mv.addObject("pregnantStage", pregnantStage);
		mv.addObject("isPublish", 1);
		mv.setViewName("information/newsInformationList");
		return mv;
	}
	
	/**
	 * 获取文章列表
	 * @param pageIndex 分页索引
	 * @param everyPage 每页记录数
	 * @param first 是否第一次
	 * @return
	 */
	@RequestMapping("findNewsInformationList")
	@ResponseBody
	public ResultMsg findNewsInformationList(@RequestParam("pageIndex") int pageIndex, @RequestParam("everyPage") int everyPage, 
			@RequestParam("first") boolean first, @RequestParam("isPublish") int isPublish, @RequestParam("keywords") String keywords,
			@RequestParam("chanels") int chanels, @RequestParam("periods") int periods){
		try {
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示记录数
			page.setEveryPage(everyPage);
			//查询总记录数
//			keywords = new String(keywords.getBytes("ISO-8859-1"),"UTF-8");
			int count = newsInformationService.findCount(isPublish, keywords, chanels, periods);
			//计算分页
			page = PageUtil.createPage(page, count);
			if(first){
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			List<VONewsInformation> voInformationList = newsInformationService.findNewsInformationList(page.getBeginIndex(), page.getEveryPage(), isPublish, keywords, chanels, periods);
			if(voInformationList != null && voInformationList.size() > 0){
				return new ResultMsg(Status.SUCCESS, "获取新闻文章数据成功！", voInformationList);
			}else{
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.error("findNewsInformationList():"+e.getMessage());
			return new ResultMsg(Status.FAILED, "获取新闻文章数据失败！");
		}
		
	}
	
	/**
	 * 文章下线操作
	 */
	@RequestMapping("offLineNewsInformation")
	@ResponseBody
	public ResultMsg offLineNewsInformation(@RequestParam("newsId") int newsId){
		NewsInformation news = new NewsInformation();
		news.setId(newsId);
		news.setIsPublish(0);//下线
		boolean b = newsInformationService.updateNewsInformation(news);
		if(b){
			return new ResultMsg(Status.SUCCESS, "文章下线成功！");
		}else{
			return new ResultMsg(Status.FAILED, "文章下线失败，请稍后重试！");
		}
	}
	
	
	//查看评论
	@RequestMapping("showNewsComments")
	@ResponseBody
	public ResultMsg showNewsComments(@RequestParam("newsId") int newsId){
		try {
			List<VONewsInformationComments> commentsList = commentsService.findNewsComments(newsId);
			if(commentsList != null && commentsList.size() > 0){
				return new ResultMsg(Status.SUCCESS, "获取评论列表成功！", commentsList);
			}else{
				return new ResultMsg(Status.NODATA, "评论列表为空！", null);
			}
		} catch (Exception e) {
			logger.error("showNewsComments():"+e.getMessage());
			return new ResultMsg(Status.FAILED, "获取评论列表失败！", null);
		}
	}
	
	//删除评论
	@RequestMapping("deleteNewsComments")
	@ResponseBody
	public ResultMsg deleteNewsComments(@RequestParam("commentId") int commentId){
		boolean b = commentsService.deleteNewsComments(commentId);
		if(b){
			return new ResultMsg(Status.SUCCESS, "删除评论成功！");
		}else{
			return new ResultMsg(Status.FAILED, "删除评论失败！");
		}
	}
	
	
	//删除文章（未发布的）
	@RequestMapping("deleteNewsInformation")
	@ResponseBody
	public ResultMsg deleteNewsInformation(@RequestParam("newsId") int newsId){
		boolean b = newsInformationService.deleteNewsInformation(newsId);
		if(b){
			return new ResultMsg(Status.SUCCESS, "删除文章成功！");
		}else{
			return new ResultMsg(Status.FAILED, "删除文章失败！");
		}
	}
	
	//发布文章
	@RequestMapping("publishNewsInformation")
	@ResponseBody
	public ResultMsg publishNewsInformation(@RequestParam("newsId") int newsId){
		NewsInformation news = new NewsInformation();
		news.setId(newsId);
		news.setIsPublish(1);//发布
		news.setTop(TimeUtils.getCurrentTime());
		news.setPublishTime(TimeUtils.getCurrentTime());
		boolean b = newsInformationService.updateNewsInformation(news);
		if(b){
			return new ResultMsg(Status.SUCCESS, "文章发布成功！");
		}else{
			return new ResultMsg(Status.FAILED, "文章发布失败，请稍后重试！");
		}
	}
	
	
	//修改文章时获取文章信息
	@RequestMapping("findNewsInformation")
	@ResponseBody
	public ResultMsg findNewsInformation(@RequestParam("newsId") int newsId){
		//文章信息
		NewsInformation news = new NewsInformation();
		if(newsId != 0){
			news = newsInformationService.findNewsInformationById(newsId);
		}
		//频道列表
		List<NewsChanels> newsChanels = newsChanelsService.findAllNewsChanels();
		//孕期阶段
		PregnantStage[] pregnantStage = PregnantStage.values();
		List<Map<String, Object>> stages = new ArrayList<Map<String,Object>>();
		for (PregnantStage stage : pregnantStage) {
			Map<String, Object> stageMap = new HashMap<String, Object>();
			stageMap.put("type", stage.getType());
			stageMap.put("desc", stage.getDesc());
			stages.add(stageMap);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if(news != null){
			map.put("news", news);
		}
		map.put("newsChanels", newsChanels);
		map.put("pregnantStage", stages);
		if(news != null){
			return new ResultMsg(Status.SUCCESS, "获取文章信息成功！", map);
		}else{
			return new ResultMsg(Status.FAILED, "获取文章信息失败！");
		}
	}
	
	//添加或修改文章（添加到未发布列表）
	@RequestMapping("addOrEditNewsInformation")
	@ResponseBody
	public ResultMsg addOrEditNewsInformation(@RequestParam("newsId") int newsId, @RequestParam("title") String title,
			@RequestParam("channelId") int channelId, @RequestParam("sourceFrom") String sourceFrom,
			@RequestParam("fromLogoUrl") String fromLogoUrl, @RequestParam("introduct") String introduct,
			@RequestParam("content") String content, @RequestParam("imageUrl") String imageUrl,  
			@RequestParam("stages") String stages, @RequestParam("isPublish") int isPublish, @RequestParam("btnStatus") String btnStatus){
		NewsInformation news = new NewsInformation();
		news.setId(newsId);
		news.setChannelId(channelId);
		news.setTitle(title);
		news.setSourceFrom(sourceFrom);
		news.setFromLogoUrl(fromLogoUrl);
		news.setIsPublish(isPublish);
		news.setIntroduct(introduct);
		news.setContent(content);
		news.setImageUrl(imageUrl);
		String period = stages.replace(",", "|");
		news.setPeriod(period);
		news.setAddTime(TimeUtils.getCurrentTime());
		if("publish".equals(btnStatus)){
			news.setPublishTime(TimeUtils.getCurrentTime());
			news.setTop(TimeUtils.getCurrentTime());
		}
		boolean b = newsInformationService.addOrEditNewsInformation(news);
		String status = "操作";
		if("save".equals(btnStatus)){
			status = "保存文章";
		}else if("publish".equals(btnStatus)){
			status = "发布文章";
		}
		if(b){
			/*if(newsId == 0){
				return new ResultMsg(Status.SUCCESS, "添加文章成功！", null);
			}else{
				return new ResultMsg(Status.SUCCESS, "修改文章成功！", null);
			}*/
			return new ResultMsg(Status.SUCCESS, status+"成功！", null);
		}else{
			/*if(newsId == 0){
				return new ResultMsg(Status.FAILED, "添加文章失败！", null);
			}else{
				return new ResultMsg(Status.FAILED, "修改文章失败！", null);
			}*/
			return new ResultMsg(Status.FAILED, status+"失败！", null);
		}
		
	}
	
	
	/**
	 * 推送通知消息
	 */
	@RequestMapping("pushNewsMessage")
	@ResponseBody
	public ResultMsg pushNewsMessage(@RequestParam("newsId") int newsId, @RequestParam("title") String title,
			@RequestParam("description") String description){
		//推送消息（所有用户）(测试环境使用单推)
		try {
//		Jpush.sendPush("10048test",title, description ,2, String.valueOf(newsId));//information.getIntroduct()
			Jpush.sendPush(title, description, 2, String.valueOf(newsId));
			return new ResultMsg(Status.SUCCESS, "推送通知消息成功！", null);
		} catch (Exception e) {
			return new ResultMsg(Status.FAILED, "推送通知消息失败，请稍后重试！", null);
		}
	}
	
}
