package com.jumper.angel.sociality.report.mapper;

import java.util.List;

import com.jumper.angel.sociality.report.model.vo.ReportCommentOnVO;
import com.jumper.angel.sociality.report.model.vo.ReportDebatepostVO;
import com.jumper.angel.sociality.report.model.vo.ReportUserVO;
import com.jumper.angel.sociality.report.model.vo.ReportVO;

/**
 * 社交 举报中心Mapper
 * 
 * @ClassName:eportMapper
 * @author huangzg 2016年12月24日 上午10:07:12
 */
public interface ReportMapper {
	/**
	 * 查询被举报的帖子
	 * @author yangsheng@angeldoctor 
	 * @param reportDebatepostVO
	 * @return
	 */
	List<ReportDebatepostVO> selectReportDebatepost(ReportDebatepostVO reportDebatepostVO);
	/**
	 * 分页统计被举报的帖子
	 * @author yangsheng@angeldoctor 
	 * @param reportDebatepostVO
	 * @return
	 */
	int selectCountReportDebatepost(ReportDebatepostVO reportDebatepostVO);
	/**
	 * 查询被举报的评论
	 * @author yangsheng@angeldoctor 
	 * @param reportCommentOnVO
	 * @return
	 */
	List<ReportCommentOnVO> selectReportComment(ReportCommentOnVO reportCommentOnVO);
	/**
	 * 查询统计举报的评论
	 * @author yangsheng@angeldoctor 
	 * @param reportCommentOnVO
	 * @return
	 */
	int selectCountReportComment(ReportCommentOnVO reportCommentOnVO);
	/**
	 * 查询被举报的用户
	 * @author yangsheng@angeldoctor 
	 * @param reportCommentOnVO
	 * @return
	 */
	List<ReportUserVO> selectReportUser(ReportUserVO eportUserVO);
	/**
	 * 查询统计被举报的用户
	 * @author yangsheng@angeldoctor 
	 * @return
	 */
	int selectCountReportUser(ReportUserVO reportUserVO);
	
	/**
	 * 更新举报对象
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
	List<ReportVO> selectReportByBean(ReportVO reportVO);
}
