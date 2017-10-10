package com.jumper.angel.sociality.report.service;
import java.util.List;

import com.jumper.angel.sociality.report.model.vo.ReportCommentOnVO;
import com.jumper.angel.sociality.report.model.vo.ReportDebatepostVO;
import com.jumper.angel.sociality.report.model.vo.ReportUserVO;
import com.jumper.angel.sociality.report.model.vo.ReportVO;

/**
 * 社交 举报中心 Service
 * 
 * @ClassName: ReportService 
 * @author huangzg 2016年12月24日 上午9:58:50
 */
public interface ReportService {
	
	/**
	 * 查询被举报的帖子
	 * @author yangsheng@angeldoctor 
	 * @param reportDebatepostVO
	 * @return
	 */
	List<ReportDebatepostVO> findReportDebatepost(ReportDebatepostVO reportDebatepostVO);
	/**
	 * 分页统计被举报的帖子
	 * @author yangsheng@angeldoctor 
	 * @param reportDebatepostVO
	 * @return
	 */
	int findCountReportDebatepost(ReportDebatepostVO reportDebatepostVO);
	/**
	 * 查询被举报的评论
	 * @author yangsheng@angeldoctor 
	 * @param reportCommentOnVO
	 * @return
	 */
	List<ReportCommentOnVO> findReportComment(ReportCommentOnVO reportCommentOnVO);
	/**
	 * 查询统计举报的评论
	 * @author yangsheng@angeldoctor 
	 * @param reportCommentOnVO
	 * @return
	 */
	int findCountReportComment(ReportCommentOnVO reportCommentOnVO);
	/**
	 * 查询被举报的用户
	 * @author yangsheng@angeldoctor 
	 * @param reportCommentOnVO
	 * @return
	 */
	List<ReportUserVO> findReportUser(ReportUserVO reportUserVO);
	/**
	 * 查询统计被举报的用户
	 * @author yangsheng@angeldoctor 
	 * @return
	 */
	int findCountReportUser(ReportUserVO reportUserVO);
	/**
	 * 更新被举报的帖子
	 * @author yangsheng@angeldoctor 
	 * @param reportVO
	 * @return
	 */
	int update(ReportVO reportVO);
	
	/**
	 * 查询举报
	 * @author yangsheng@angeldoctor 
	 * @param reportVO
	 * @return
	 */
	List<ReportVO> findReportByBean(ReportVO reportVO);
	
}