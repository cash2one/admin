package com.jumper.angel.home.encyclopedia.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumper.angel.home.encyclopedia.entity.HelperQuestionClass;
import com.jumper.angel.home.encyclopedia.entity.HelperQuestionType;
import com.jumper.angel.home.encyclopedia.entity.HelperQuestions;
import com.jumper.angel.home.encyclopedia.service.HelperQuestionClassService;
import com.jumper.angel.home.encyclopedia.service.HelperQuestionTypeService;
import com.jumper.angel.home.encyclopedia.service.HelperQuestionsService;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;
import com.jumper.angel.utils.TimeUtils;

/**
 * 孕期百科控制器
 * @author gyx
 * @time 2016年12月23日
 */
@Controller
@RequestMapping("encyclopedia")
public class EncyclopediaController {
	private final static Logger logger = Logger.getLogger(EncyclopediaController.class);
	
	@Autowired
	private HelperQuestionClassService classService;
	
	@Autowired
	private HelperQuestionTypeService typeService;
	
	@Autowired
	private HelperQuestionsService questionsService;

	/**
	 * 跳转模块标签列表页面
	 * @return
	 */
	@RequestMapping("forwardQuestionClassOrTypeList")
	public ModelAndView forwardQuestionClassList(@RequestParam("type") String type){
		ModelAndView mv = new ModelAndView();
		//当前总内容模块数
		int classCount = classService.findCount("");
		//总内容标签数
		int typeCount = typeService.findCount("",0);
		mv.addObject("classCount",classCount);
		mv.addObject("typeCount", typeCount);
		mv.addObject("type", type);
		if("questionClass".equals(type)){
			mv.setViewName("encyclopedia/questionClassList");
		}else if("questionType".equals(type)){
			List<HelperQuestionClass> classList = classService.getAllQuestionClass();
			mv.addObject("classList", classList);
			mv.setViewName("encyclopedia/questionTypeList");
		}
		return mv;
	}
	
	
	/**
	 * 获取模块分页数据
	 */
	@RequestMapping("findQuestionClassList")
	@ResponseBody
	public ResultMsg findQuestionClassList(@RequestParam("pageIndex") int pageIndex, @RequestParam("everyPage") int everyPage, 
			@RequestParam("first") boolean first, @RequestParam("keywords") String keywords){
		try {
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示记录数
			page.setEveryPage(everyPage);
			//查询总记录数
//			keywords = new String(keywords.getBytes("ISO-8859-1"),"UTF-8");
			int count = classService.findCount(keywords);
			//计算分页
			page = PageUtil.createPage(page, count);
			if(first){
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			List<HelperQuestionClass> classList = classService.findQuestionClassList(page.getBeginIndex(), page.getEveryPage(), keywords);
			if(classList != null && classList.size() > 0){
				return new ResultMsg(Status.SUCCESS, "获取模块数据成功！", classList);
			}else{
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.error("findQuestionClassList():"+e.getMessage());
			return new ResultMsg(Status.FAILED, "获取模块数据失败！");
		}
	}
	
	/**
	 * 获取标签分页数据
	 */
	@RequestMapping("findQuestionTypeList")
	@ResponseBody
	public ResultMsg findQuestionTypeList(@RequestParam("pageIndex") int pageIndex, @RequestParam("everyPage") int everyPage, 
			@RequestParam("first") boolean first, @RequestParam("keywords") String keywords, @RequestParam("classId") int classId){
		try {
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示记录数
			page.setEveryPage(everyPage);
			//查询总记录数
//			keywords = new String(keywords.getBytes("ISO-8859-1"),"UTF-8");
			int count = typeService.findCount(keywords, classId);
			//计算分页
			page = PageUtil.createPage(page, count);
			if(first){
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			List<HelperQuestionType> typeList = typeService.findQuestionTypeList(page.getBeginIndex(), page.getEveryPage(), keywords, classId);
			if(typeList != null && typeList.size() > 0){
				return new ResultMsg(Status.SUCCESS, "获取标签数据成功！", typeList);
			}else{
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.error("findQuestionClassList():"+e.getMessage());
			return new ResultMsg(Status.FAILED, "获取标签数据失败！");
		}
	}
	
	/**
	 * 模块名查询是否重名
	 */
	@RequestMapping("checkQuestionClassName")
	@ResponseBody
	public ResultMsg checkQuestionClassName(@RequestParam("classId") int classId, @RequestParam("className") String className){
		HelperQuestionClass questionClass = new HelperQuestionClass();
		questionClass.setId(classId);
		questionClass.setName(className);
		HelperQuestionClass helperQuestionClass = classService.findQuestionClassByName(questionClass);
		if(helperQuestionClass != null){
			return new ResultMsg(Status.SUCCESS, "模块名已存在！");
		}else{
			return new ResultMsg(Status.FAILED, "模块名未被使用！");
		}
	}
	
	
	/**
	 * 添加或修改模块
	 */
	@RequestMapping("addOrEditQuestionClass")
	@ResponseBody
	public ResultMsg addOrEditQuestionClass(@RequestParam("classId") int classId, @RequestParam("className") String className, 
			@RequestParam("isVisible") int isVisible){
		HelperQuestionClass questionClass = new HelperQuestionClass();
		questionClass.setId(classId);
		questionClass.setName(className);
		questionClass.setIsVisible(isVisible);
		questionClass.setAddTime(TimeUtils.getCurrentTime());
		boolean b = classService.addOrEditQuestionClass(questionClass);
		if(b){
			if(classId == 0){
				return new ResultMsg(Status.SUCCESS, "添加模块信息成功！", null);
			}else{
				return new ResultMsg(Status.SUCCESS, "修改模块信息成功！", null);
			}
		}else{
			if(classId == 0){
				return new ResultMsg(Status.FAILED, "添加模块信息失败！", null);
			}else{
				return new ResultMsg(Status.FAILED, "修改模块信息失败！", null);
			}
		}
	}
	
	
	/**
	 * 删除模块
	 */
	@RequestMapping("deleteQuestionClass")
	@ResponseBody
	public ResultMsg deleteQuestionClass(@RequestParam("classId") int classId){
		boolean b = classService.deleteQuestionClass(classId);
		if(b){
			return new ResultMsg(Status.SUCCESS, "删除模块成功！");
		}else{
			return new ResultMsg(Status.FAILED, "删除模块失败，请稍后重试！");
		}
	}
	
	/**
	 * 删除标签
	 */
	@RequestMapping("deleteQuestionType")
	@ResponseBody
	public ResultMsg deleteQuestionType(@RequestParam("typeId") int typeId){
		boolean b = typeService.deleteQuestionType(typeId);
		if(b){
			return new ResultMsg(Status.SUCCESS, "删除标签成功！");
		}else{
			return new ResultMsg(Status.FAILED, "删除标签失败，请稍后重试！");
		}
	}
	
	/**
	 * 获取模块列表所有数据
	 */
	@RequestMapping("findAllQuestionClassList")
	@ResponseBody
	public ResultMsg findAllQuestionClassList(){
		try {
			List<HelperQuestionClass> classList = classService.getAllQuestionClass();
			if(classList != null && classList.size() > 0){
				return new ResultMsg(Status.SUCCESS, "获取所有模块数据成功！", classList);
			}else{
				return new ResultMsg(Status.NODATA, "模块数据为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(Status.FAILED, "获取所有模块数据失败！");
		}
	}
	
	/**
	 * 标签名查询标签是否重复
	 */
	@RequestMapping("checkQuestionType")
	@ResponseBody
	public ResultMsg checkQuestionType(@RequestParam("typeId") int typeId, @RequestParam("typeName") String typeName, @RequestParam("classId") int classId){
		HelperQuestionType questionType = new HelperQuestionType();
		questionType.setId(typeId);
		questionType.setName(typeName);
		questionType.setParentId(classId);
		HelperQuestionType helperQuestionType = typeService.findQuestionTypeByName(questionType);
		if(helperQuestionType != null){
			return new ResultMsg(Status.SUCCESS, "标签名已存在！");
		}else{
			return new ResultMsg(Status.FAILED, "标签名未被使用！");
		}
	}
	
	
	
	/**
	 * 添加或修改标签信息
	 */
	@RequestMapping("addOrEditQuestionType")
	@ResponseBody
	public ResultMsg addOrEditQuestionType(@RequestParam("typeId") int typeId, @RequestParam("typeName") String typeName,
			@RequestParam("isVisible") int isVisible, @RequestParam("classId") int classId){
		HelperQuestionType questionType = new HelperQuestionType();
		questionType.setId(typeId);
		questionType.setName(typeName);
		questionType.setParentId(classId);
		questionType.setIsVisible(isVisible);
		questionType.setAddTime(TimeUtils.getCurrentTime());
		boolean b = typeService.addOrEditQuestionType(questionType);
		if(b){
			if(typeId == 0){
				return new ResultMsg(Status.SUCCESS, "添加标签信息成功！", null);
			}else{
				return new ResultMsg(Status.SUCCESS, "修改标签信息成功！", null);
			}
		}else{
			if(typeId == 0){
				return new ResultMsg(Status.FAILED, "添加标签信息失败！", null);
			}else{
				return new ResultMsg(Status.FAILED, "修改标签信息失败！", null);
			}
		}
		
	}
	
	/**
	 * 跳转到百科文章列表
	 */
	@RequestMapping("forwardQuestionList")
	@ResponseBody
	public ModelAndView forwardQuestionList(){
		ModelAndView mv = new ModelAndView();
		//模块
		List<HelperQuestionClass> classList = classService.getAllQuestionClass();
		//标签
//		List<HelperQuestionType> typeList = typeService.getAllQuestionType();
		mv.addObject("classList", classList);
		mv.setViewName("encyclopedia/questionList");
		return mv;
	}
	
	
	/**
	 * 通过模块查询模块下的标签
	 */
	@RequestMapping("findQuestionTypeByClass")
	@ResponseBody
	public ResultMsg findQuestionTypeByClass(@RequestParam("classId") int classId){
		try {
			List<HelperQuestionType> typeList = typeService.findQuestionTypeByClass(classId);
			if(typeList != null && typeList.size() > 0){
				return new ResultMsg(Status.SUCCESS, "获取标签列表成功！", typeList);
			}else{
				return new ResultMsg(Status.NODATA, "标签列表为空！", typeList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(Status.NODATA, "获取标签列表失败！");
		}
	}
	
	/**
	 * 获取常见问题分页数据
	 */
	@RequestMapping("findQuestionList")
	@ResponseBody
	public ResultMsg findQuestionList(@RequestParam("pageIndex") int pageIndex, @RequestParam("everyPage") int everyPage, 
			@RequestParam("first") boolean first, @RequestParam("keywords") String keywords,  @RequestParam("classId") int classId, @RequestParam("typeId") int typeId){
		try {
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示记录数
			page.setEveryPage(everyPage);
			//查询总记录数
//			keywords = new String(keywords.getBytes("ISO-8859-1"),"UTF-8");
			int count = questionsService.findCount(keywords,classId, typeId);
			//计算分页
			page = PageUtil.createPage(page, count);
			if(first){
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			List<HelperQuestions> questionList = questionsService.findQuestionList(page.getBeginIndex(), page.getEveryPage(), keywords, classId, typeId);
			if(questionList != null && questionList.size() > 0){
				return new ResultMsg(Status.SUCCESS, "获取常见问题数据成功！", questionList);
			}else{
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.error("findQuestionList():"+e.getMessage());
			return new ResultMsg(Status.FAILED, "获取常见问题数据失败！");
		}
	}
	
	
	/**
	 * 删除常见问题
	 */
	@RequestMapping("deleteQuestion")
	@ResponseBody
	public ResultMsg deleteQuestion(@RequestParam("questionId") int questionId){
		boolean b = questionsService.deleteQuestionClass(questionId);
		if(b){
			return new ResultMsg(Status.SUCCESS, "删除常见问题成功！");
		}else{
			return new ResultMsg(Status.FAILED, "删除常见问题失败，请稍后重试！");
		}
	}
	
	
	/**
	 * 添加或修改常见问题
	 */
	@RequestMapping("addOrEditQuestion")
	@ResponseBody
	public ResultMsg addOrEditQuestion(@RequestParam("questionId") int questionId, @RequestParam("typeId") int typeId, 
			@RequestParam("classId") int classId, @RequestParam("status") int status, @RequestParam("title") String title,
			@RequestParam("introduction") String introduction,@RequestParam("answer") String answer,@RequestParam("imgUrl") String imgUrl){
		HelperQuestions questions = new HelperQuestions();
		questions.setId(questionId);
		questions.setQuestionTypeId(typeId);
		questions.setStatus(status);
		questions.setTitle(title);
		questions.setQuestionIntorduction(introduction);
		questions.setAnswer(answer);
		questions.setImgUrl(imgUrl);
		questions.setAddTime(TimeUtils.getCurrentTime());
		boolean b = questionsService.addOrEditQuestion(questions);
		if(b){
			if(questionId == 0){
				return new ResultMsg(Status.SUCCESS, "添加百科内容信息成功！", null);
			}else{
				return new ResultMsg(Status.SUCCESS, "修改百科内容信息成功！", null);
			}
		}else{
			if(questionId == 0){
				return new ResultMsg(Status.FAILED, "添加百科内容信息失败！", null);
			}else{
				return new ResultMsg(Status.FAILED, "修改百科内容信息失败！", null);
			}
		}
	}
	
	/**
	 * 公开或隐藏百科内容
	 */
	@RequestMapping("operateQuestion")
	@ResponseBody
	public ResultMsg operateQuestion(@RequestParam("questionId") int questionId, @RequestParam("status") int status){
		HelperQuestions questions = new HelperQuestions();
		questions.setId(questionId);
		questions.setStatus(status==0?1:0);
		boolean b = questionsService.operateQuestion(questions);
		String str = status==0?"隐藏":"公开";
		if(b){
			return new ResultMsg(Status.SUCCESS, str+"常见问题成功！");
		}else{
			return new ResultMsg(Status.FAILED, str+"常见问题失败，请稍后重试！");
		}
		
	}
	
	
}
