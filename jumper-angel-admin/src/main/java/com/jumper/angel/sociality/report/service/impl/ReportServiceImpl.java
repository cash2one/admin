package com.jumper.angel.sociality.report.service.impl;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.sociality.comment.mapper.CommentOnMapper;
import com.jumper.angel.sociality.comment.model.po.CommentOnPO;
import com.jumper.angel.sociality.debatepost.mapper.DebatepostMapper;
import com.jumper.angel.sociality.debatepost.model.vo.DebatepostVO;
import com.jumper.angel.sociality.group.mapper.UserGroupMapper;
import com.jumper.angel.sociality.group.model.po.UserGroupPo;
import com.jumper.angel.sociality.group.service.UserGroupService;
import com.jumper.angel.sociality.report.mapper.ReportMapper;
import com.jumper.angel.sociality.report.model.vo.ReportCommentOnVO;
import com.jumper.angel.sociality.report.model.vo.ReportDebatepostVO;
import com.jumper.angel.sociality.report.model.vo.ReportUserVO;
import com.jumper.angel.sociality.report.model.vo.ReportVO;
import com.jumper.angel.sociality.report.service.ReportService;
import com.jumper.angel.sociality.topic.mapper.TopicMapper;
import com.jumper.angel.sociality.topic.model.vo.TopicVO;
import com.jumper.angel.utils.TimeUtils;


/**
 * 社交 举报中心 ServiceImpl
 * @ClassName: ReportServiceImpl  
 * @author huangzg 2016年12月24日 上午9:58:50
 */
@Service
public class ReportServiceImpl implements ReportService  {
	
	@Autowired
	private ReportMapper mapper;
	@Autowired 
	DebatepostMapper debatepostMapper;
	@Autowired
	CommentOnMapper commentOnMapper;
	@Autowired
	private UserGroupMapper userGroupMapper;
	@Autowired
	private TopicMapper topicMapper;
	@Autowired
	private UserGroupService userGroupService;
	
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public List<ReportDebatepostVO> findReportDebatepost(ReportDebatepostVO reportDebatepostVO) {
		return mapper.selectReportDebatepost(reportDebatepostVO);
	}


	@Override
	public int findCountReportDebatepost(ReportDebatepostVO reportDebatepostVO) {
		return mapper.selectCountReportDebatepost(reportDebatepostVO);
	}
	
	@Override
	public int update(ReportVO reportVO) {
		if(reportVO.getDataType() ==1 && reportVO.getOperatorType() ==1){
			//恢复隐藏帖子
			DebatepostVO updateDebatepost = new DebatepostVO();
			updateDebatepost.setDebatepostId(Long.parseLong(reportVO.getBusId()));
			updateDebatepost.setIsDelete(0);
			debatepostMapper.update(updateDebatepost);
		}
		if(reportVO.getDataType() ==1 && reportVO.getOperatorType() ==2){
			//删除帖子
			DebatepostVO updateDebatepost = new DebatepostVO();
			updateDebatepost.setDebatepostId(Long.parseLong(reportVO.getBusId()));
			updateDebatepost.setIsDelete(2);
			debatepostMapper.update(updateDebatepost);
			//更新话题组表的帖子数
			updateDebatepost =debatepostMapper.selectById(updateDebatepost.getDebatepostId());
			TopicVO topicVO = topicMapper.selectTopicById(updateDebatepost.getTopicId());
			topicVO.setTotalposts(topicVO.getTotalposts()-1);
			topicMapper.update(topicVO);
		}
		if(reportVO.getDataType() ==2 && reportVO.getOperatorType() ==1){
			//恢复隐藏评论
			CommentOnPO commentOnPO = new CommentOnPO();
			commentOnPO.setId(Long.parseLong(reportVO.getBusId()));
			commentOnPO.setStatus(1);
			commentOnMapper.updateById(commentOnPO);
		}
		if(reportVO.getDataType() ==2 && reportVO.getOperatorType() ==2){
			//删除评论
			int commentNum =0;
			CommentOnPO commentOnPO = new CommentOnPO();
			commentOnPO.setId(Long.parseLong(reportVO.getBusId()));
			commentOnPO.setStatus(3);
			commentNum =commentOnMapper.updateById(commentOnPO);
			commentOnPO =commentOnMapper.selectById(commentOnPO.getId());
			if(commentOnPO.getMessageType().equals("commen")){
				//需要删除评论下的回复
				CommentOnPO docommentOnPO = new CommentOnPO();
				docommentOnPO.setFid(commentOnPO.getId());
				docommentOnPO.setStatus(3);
				int i =commentOnMapper.updateByFid(docommentOnPO);
				commentNum = commentNum+i;
			}
			//更新帖子的评论数
			DebatepostVO debatepostVO = debatepostMapper.selectById(commentOnPO.getDebatepostId());
			DebatepostVO updateDebatepostVO = new DebatepostVO();
			updateDebatepostVO.setDebatepostId(debatepostVO.getDebatepostId());
			updateDebatepostVO.setCommentNumber(debatepostVO.getCommentNumber() - commentNum);
			debatepostMapper.update(updateDebatepostVO);
		}
		if(reportVO.getDataType() ==3 && reportVO.getOperatorType() ==3){
			//禁止用户发言
			UserGroupPo UserGroupPo = new UserGroupPo();
			UserGroupPo.setUserId(reportVO.getBusId());
			UserGroupPo.setGroupId(reportVO.getGroupId());
			UserGroupPo.setIsBlacklist(1l);
			int a = userGroupService.update(UserGroupPo,null);
			logger.info("===========update===  " + "   "+a);
			//userGroupMapper.updateByBean(UserGroupPo);
		}
		if(reportVO.getDataType() ==3 && reportVO.getOperatorType() ==4){
			//解除用户发言
			UserGroupPo UserGroupPo = new UserGroupPo();
			UserGroupPo.setUserId(reportVO.getBusId());
			UserGroupPo.setGroupId(reportVO.getGroupId());
			UserGroupPo.setIsBlacklist(0l);
			int a = userGroupService.update(UserGroupPo,null);
			logger.info("===========update===  " + "   "+a);
			//userGroupMapper.updateByBean(UserGroupPo);
		}
		return mapper.update(reportVO);
	}
	
	@Override
	public List<ReportVO> findReportByBean(ReportVO reportVO) {
		List<ReportVO> list = mapper.selectReportByBean(reportVO);
		if(list !=null && list.size()>0){
			for(ReportVO vo : list){
				vo.setStrDateTime(TimeUtils.convertLongToTimestampString("yyyy/MM/dd HH:mm", vo.getReportTime()));
			}
		}
		return list;
	}

	@Override
	public List<ReportCommentOnVO> findReportComment(ReportCommentOnVO reportCommentOnVO) {
		return mapper.selectReportComment(reportCommentOnVO);
	}

	@Override
	public int findCountReportComment(ReportCommentOnVO reportCommentOnVO) {
		return mapper.selectCountReportComment(reportCommentOnVO);
	}


	@Override
	public List<ReportUserVO> findReportUser(ReportUserVO eportUserVO) {
		return mapper.selectReportUser(eportUserVO);
	}


	@Override
	public int findCountReportUser(ReportUserVO reportUserVO) {
		return mapper.selectCountReportUser(reportUserVO);
	}

	
}