package com.jumper.angel.sociality.diary.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumper.angel.sociality.diary.model.bo.DiaryBo;
import com.jumper.angel.sociality.diary.model.vo.DiaryVo;
import com.jumper.angel.sociality.diary.service.DiaryService;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;
import com.jumper.angel.utils.StringUtil;

@Controller
@RequestMapping("/sociality/diary")
public class DiaryController {

	final static Logger logger = Logger.getLogger(DiaryController.class);

	@Autowired
	DiaryService service;

	/**
	 * 初始化页面
	 * 
	 * @author huangzg 2017年1月14日 下午2:15:41
	 * @param mv
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/init",method=RequestMethod.GET)
	public ModelAndView init(ModelAndView mv) {
		mv.setViewName("sociality/diary/diary_list");
		return mv;
	}

	/**
	 * 初始化页面数据
	 * 
	 * @author huangzg 2017年1月14日 下午2:18:32
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/get",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg get() {
		try {
			return new ResultMsg(Status.SUCCESS, "初始化数据成功！", service.get());
		} catch (Exception e) {
			logger.info("初始化页面数据 msg :"+e.getMessage(), e);
		}
		return new ResultMsg(Status.SUCCESS, "初始化数据成功！");
	}

	/**
	 * @throws Exception 
	 * 初始化用户日记页面
	 * 
	 * @author huangzg 2017年1月14日 下午2:18:32
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/init_page",method=RequestMethod.GET)
	public ModelAndView initPage(ModelAndView mv, @RequestParam("id") String id, 
			@RequestParam("name") String name) throws Exception {
		
		System.out.println("------------init_page--------1212------------");
		System.out.println(name);
		
		
		mv.addObject("id", id);
//		name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
		
		
		mv.addObject("name", name);
		mv.setViewName("sociality/diary/diary_details");
		return mv;
	}
	
	/**
	 * 分页查询宝妈日记
	 * @author huangzg 2017年1月14日 下午2:26:02  
	 * @param pageIndex 
	 * @param everyPage
	 * @param first
	 * @param userName 创建人姓名
	 * @return        
	 * @throws
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg list(
			@RequestParam("pageIndex") int pageIndex, 
			@RequestParam("everyPage") int everyPage, 
			@RequestParam("first") boolean first, 
			@RequestParam("keywords") String keywords,
			@RequestParam("name") String name){
		try {
			Page page = new Page();
			//设置第几页
			page.setCurrentPage(pageIndex);
			//每页显示记录数
			page.setEveryPage(everyPage);
			//查询总记录数
			keywords = new String(keywords.getBytes("ISO-8859-1"),"UTF-8");
//			name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println("--------------list-----1-----------------");
			System.out.println(name);
			DiaryVo vo = new DiaryVo();
			if (!StringUtil.isEmpty(keywords)){
				vo.setUserId(Integer.parseInt(keywords));
			}
			vo.setUserName(name);
			int count = service.getCounts(vo);
			//计算分页
			page = PageUtil.createPage(page, count);
			if(first){
				return new ResultMsg(Status.SUCCESS, "获取总分页数成功！", page.getTotalPage());
			}
			vo.setBeginIndex(page.getBeginIndex());
			vo.setEveryPage(page.getEveryPage());
			List<DiaryBo> bos = service.getByKeywords(vo);
			if(bos != null && bos.size() > 0){
				return new ResultMsg(Status.SUCCESS, "获取标签数据成功！", bos);
			}else{
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.info("分页查询宝妈日记 msg "+e.getMessage(), e);
		}
		return new ResultMsg(Status.NODATA, "暂无数据！");
	}
	
	/**
	 * 根据ID查询宝妈日记详情
	 * @author huangzg 2017年1月14日 下午6:13:59  
	 * @param id 数据ID
	 * @return        
	 * @throws
	 */
	@RequestMapping(value="/get_data",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg getById(@RequestParam("id") long id){
		try {	
			DiaryBo bo = service.getById(id);
			if(bo != null){
				return new ResultMsg(Status.SUCCESS, "获取标签数据成功！", bo);
			}else{
				return new ResultMsg(Status.NODATA, "暂无数据！");
			}
		} catch (Exception e) {
			logger.info("分页查询宝妈日记 msg "+e.getMessage(), e);
		}
		return new ResultMsg(Status.NODATA, "暂无数据！");
	}
	
}