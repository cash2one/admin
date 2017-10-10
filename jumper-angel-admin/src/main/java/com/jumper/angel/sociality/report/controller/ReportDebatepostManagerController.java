package com.jumper.angel.sociality.report.controller;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.jumper.angel.sociality.report.model.vo.ReportDebatepostVO;
import com.jumper.angel.sociality.report.service.ReportService;
import com.jumper.angel.utils.Page;
import com.jumper.angel.utils.PageUtil;
import com.jumper.angel.utils.Result;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;

/**
 * 社交 举报中心 Controller
 * 
 * @ClassName: ReportController
 * @author huangzg 2016年12月24日 上午9:58:24
 */
@Controller
@RequestMapping("/sociality/reprot/debatepost")
public class ReportDebatepostManagerController {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	ReportService reportService;
	
	/**
	 * 跳转到内容管理页面
	 * @author yangsheng@angeldoctor 
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sociality/report/reportDebatepostManager");
		return mv;
	} 
	
	@RequestMapping(value="/getReportDebatepostList",method=RequestMethod.GET)
	@ResponseBody
	public ResultMsg getReportDebatepostList(@RequestParam("beginIndex") int beginIndex, 
			   				@RequestParam("everyPage") int everyPage,
			   				@RequestParam("keyword") String keyword,
			   				@RequestParam("status") String status){
		try{
			ReportDebatepostVO reportDebatepostVO = new ReportDebatepostVO();
			reportDebatepostVO.setEveryPage(everyPage);
			if(StringUtils.isNotBlank(keyword)){
				reportDebatepostVO.setKeyword(keyword);
			}
			if(StringUtils.isNotBlank(status)){
				reportDebatepostVO.setStatus(Integer.parseInt(status));
			}
			Page page = new Page();
			page.setCurrentPage(beginIndex);//设置第几页
			page.setEveryPage(everyPage);	//每页显示的记录数
			int  count = reportService.findCountReportDebatepost(reportDebatepostVO);
			page = PageUtil.createPage(page, count);
			if(beginIndex<=1){
				reportDebatepostVO.setBeginIndex(beginIndex-1);
			}else{
				reportDebatepostVO.setBeginIndex((beginIndex-1)*everyPage);
			}
			List<ReportDebatepostVO> list = reportService.findReportDebatepost(reportDebatepostVO);
			//分页结果
			Result result = new Result(page, list);
			if(list !=null && list.size() >0){
				return new ResultMsg(Status.SUCCESS, "获取被举报的帖子集合成功!", result);
			}else{
				return new ResultMsg(Status.FAILED, "没有查询到被举报的帖子!", result);
			}
		}catch(Exception e){
			logger.error("ReportDebatepostManagerController>getReportDebatepostList error",e);
			return new ResultMsg(Status.FAILED, "获取被举报的帖子信息失败");
		}
	}
	
	
}