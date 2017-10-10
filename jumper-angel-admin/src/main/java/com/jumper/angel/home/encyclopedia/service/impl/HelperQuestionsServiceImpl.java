package com.jumper.angel.home.encyclopedia.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumper.angel.home.encyclopedia.entity.HelperQuestions;
import com.jumper.angel.home.encyclopedia.mapper.HelperQuestionsMapper;
import com.jumper.angel.home.encyclopedia.service.HelperQuestionsService;
import com.jumper.angel.home.information.service.NewsChanelsService;
import com.jumper.angel.utils.ConfigProUtils;
import com.jumper.angel.utils.createHtml;
import com.jumper.angel.utils.file.FastdfsUpload;
/**
 * 孕期百科问题Service
 * @author gyx
 * @time 2016年12月1日
 */
@Service
@Transactional
public class HelperQuestionsServiceImpl implements HelperQuestionsService {
	private final static Logger logger = Logger.getLogger(HelperQuestionsServiceImpl.class);
	
	@Autowired
	private HelperQuestionsMapper helperQuestionsMapper;

	/**
	 * 条件获取常见问题总记录数
	 */
	@Override
	public int findCount(String keywords, int classId, int typeId) {
		int count = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keywords", keywords);
		map.put("classId", classId);
		map.put("typeId", typeId);
		count = helperQuestionsMapper.findCount(map);
		return count;
	}

	/**
	 * 条件获取获取常见问题列表
	 */
	@Override
	public List<HelperQuestions> findQuestionList(int beginIndex,
			int everyPage, String keywords, int classId, int typeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginIndex", beginIndex);
		map.put("everyPage", everyPage);
		map.put("keywords", keywords);
		map.put("classId", classId);
		map.put("typeId", typeId);
		List<HelperQuestions> questionList = helperQuestionsMapper.findQuestionList(map);
		if(questionList != null && questionList.size() > 0){
			return questionList;
		}
		return null;
	}

	/**
	 * 删除常见问题
	 */
	@Override
	public boolean deleteQuestionClass(int questionId) {
		boolean b = false;
		try {
			helperQuestionsMapper.deleteQuestionClass(questionId);
			b = true;
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 添加或修改百科内容
	 */
	@Override
	public boolean addOrEditQuestion(HelperQuestions questions) {
		boolean b = false;
		try {
			String filepath = ConfigProUtils.get("BASE_DIR")+ConfigProUtils.get("HTML_MB_URL");					//模板路径
			String path = ConfigProUtils.get("BASE_DIR")+ConfigProUtils.get("QUESTION_HTML_URL");					//生成HTML路径
			if(questions.getId()==0){//添加 
				questions.setDetailsUrl("");
				helperQuestionsMapper.addQuestion(questions);
				String url=createHtml.CreateHtmlFile(filepath, path, questions.getAnswer());
				//解析上传成功后html路径
				JSONObject jsonObject = JSONObject.fromObject(url);
				if(jsonObject.getInt("msg") == 1) {
					JSONObject json = JSONObject.fromObject(jsonObject.getString("data"));
					//地址
					url = json.getString("fileUrl");
					questions.setDetailsUrl("/"+url);
					questions.setUrl("/"+url);
				}
				helperQuestionsMapper.updateQuestion(questions);
			}else{//修改
				if(StringUtils.isNotEmpty(questions.getDetailsUrl())){
					boolean isOk = FastdfsUpload.deleteFile(questions.getDetailsUrl());
					if(!isOk){
						logger.error("delete file failure in create html!");
					}
				}
				String url = createHtml.CreateHtmlFile(filepath, path, questions.getAnswer());
				//解析上传成功后html路径
				JSONObject jsonObject = JSONObject.fromObject(url);
				if(jsonObject.getInt("msg") == 1) {
					JSONObject json = JSONObject.fromObject(jsonObject.getString("data"));
					//地址
					url = json.getString("fileUrl");
					questions.setDetailsUrl("/"+url);
					questions.setUrl("/"+url);
				}
				helperQuestionsMapper.updateQuestion(questions);
			}
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 公开或隐藏常见问题
	 */
	@Override
	public boolean operateQuestion(HelperQuestions questions) {
		boolean b = false;
		try {
			helperQuestionsMapper.updateQuestion(questions);
			b = true;
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}
	
	
}
