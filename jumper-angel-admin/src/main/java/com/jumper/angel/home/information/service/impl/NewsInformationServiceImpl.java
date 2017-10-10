package com.jumper.angel.home.information.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumper.angel.home.information.entity.NewsChanels;
import com.jumper.angel.home.information.entity.NewsInformation;
import com.jumper.angel.home.information.mapper.NewsChanelsMapper;
import com.jumper.angel.home.information.mapper.NewsClickMapper;
import com.jumper.angel.home.information.mapper.NewsCollectionMapper;
import com.jumper.angel.home.information.mapper.NewsInformationCommentsMapper;
import com.jumper.angel.home.information.mapper.NewsInformationMapper;
import com.jumper.angel.home.information.mapper.NewsShareMapper;
import com.jumper.angel.home.information.service.NewsInformationService;
import com.jumper.angel.home.information.vo.VONewsInformation;
import com.jumper.angel.utils.ConfigProUtils;
import com.jumper.angel.utils.TimeUtils;
import com.jumper.angel.utils.createHtml;


/**
 * 资讯信息ServiceImpl
 * @author gyx
 * @time 2017年1月5日
 */
@Service
@Transactional
public class NewsInformationServiceImpl implements NewsInformationService {
	
	@Autowired
	private NewsInformationMapper newsInformationMapper;
	
	@Autowired
	private NewsChanelsMapper newsChanelsMapper;
	
	@Autowired
	private NewsClickMapper newsClickMapper;
	
	@Autowired
	private NewsInformationCommentsMapper newsInformationCommentsMapper;
	
	@Autowired
	private NewsCollectionMapper collectionMapper;
	
	@Autowired
	private NewsShareMapper newsShareMapper;

	/**
	 * 获取已发布和未发布文章总数
	 */
	@Override
	public int findCount(int isPublish, String keywords, int chanels, int periods) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isPublish", isPublish);
		map.put("keywords", keywords);
		map.put("chanels", chanels);
		map.put("periods", periods);
		int count = newsInformationMapper.findCount(map);
		return count;
	}

	/**
	 * 获取文章列表
	 */
	@Override
	public List<VONewsInformation> findNewsInformationList(int beginIndex,
			int everyPage, int isPublish, String keywords,int chanels, int periods) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginIndex", beginIndex);
		map.put("everyPage", everyPage);
		map.put("isPublish", isPublish);
		map.put("keywords", keywords);
		map.put("chanels", chanels);
		map.put("periods", periods);
		List<NewsInformation> informationList = newsInformationMapper.findNewsInformationList(map);
		if(informationList != null && informationList.size() > 0){
			List<VONewsInformation> voList = getVONewsInformationList(informationList);
			return voList;
		}
		return null;
	}

	/**
	 * 封装文章列表数据
	 * @param informationList
	 * @return
	 */
	private List<VONewsInformation> getVONewsInformationList(
			List<NewsInformation> informationList) {
		List<VONewsInformation> voList = new ArrayList<VONewsInformation>();
		if(informationList !=null && informationList.size() > 0){
			for (NewsInformation newsInformation : informationList) {
				VONewsInformation voNewsInformation = new VONewsInformation();
				voNewsInformation.setId(newsInformation.getId());
				//标题
				if(StringUtils.isNotEmpty(newsInformation.getTitle())){
					voNewsInformation.setTitle(newsInformation.getTitle());
				}
				
				//频道
				voNewsInformation.setChannelId(newsInformation.getChannelId());
				NewsChanels newsChanels = newsChanelsMapper.findNewsChanelsById(newsInformation.getChannelId());
				if(newsChanels != null && StringUtils.isNotEmpty(newsChanels.getChanelName())){
					voNewsInformation.setChannelName(newsChanels.getChanelName());
				}
				
				//孕期阶段
				if(StringUtils.isNotEmpty(newsInformation.getPeriod())){
					voNewsInformation.setPeriod(newsInformation.getPeriod());
				}
				
				//图片
				if(StringUtils.isNotEmpty(newsInformation.getImageUrl())){
					if(newsInformation.getImageUrl().contains("http")){
						voNewsInformation.setImageUrl(newsInformation.getImageUrl());
					}else{
						voNewsInformation.setImageUrl(ConfigProUtils.get("file_path")+newsInformation.getImageUrl());
					}
				}
				
				//详情URL
				if(StringUtils.isNotEmpty(newsInformation.getNewsUrl())){
					if(newsInformation.getNewsUrl().contains("http")){
						voNewsInformation.setNewsUrl(newsInformation.getNewsUrl());
					}else{
						voNewsInformation.setNewsUrl(ConfigProUtils.get("file_path")+newsInformation.getNewsUrl());
					}
				}
				
				
				//总阅读量
				if(StringUtils.isNotEmpty(newsInformation.getClicks().toString())){
					voNewsInformation.setClicks(newsInformation.getClicks());
				}
				
				//昨日阅读量
				Date beginDate = TimeUtils.getCurrentStartTime(-1, new Date());
				Date endDate = TimeUtils.getCurrentStartTime(-1, new Date());
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("newsId", newsInformation.getId());
				map.put("beginDate", beginDate);
				map.put("endDate", endDate);
				Integer yesterdayClicks = newsClickMapper.getBetweenDateClicks(map);
				voNewsInformation.setYesterdayClicks(yesterdayClicks);
				
				//上周阅读量
				beginDate = TimeUtils.getLastWeekMonday();
				endDate = TimeUtils.getLastWeekSunday();
				map.put("beginDate", beginDate);
				map.put("endDate", endDate);
				Integer lastWeekClicks = newsClickMapper.getBetweenDateClicks(map);
				voNewsInformation.setLastWeekClicks(lastWeekClicks);
				
				//上月阅读量
				beginDate = TimeUtils.getFirstDayOfLastMonth();
				endDate = TimeUtils.getLastDayOfLastMonth();
				map.put("beginDate", beginDate);
				map.put("endDate", endDate);
				Integer lastMonthClicks = newsClickMapper.getBetweenDateClicks(map);
				voNewsInformation.setLastMonthClicks(lastMonthClicks);
				
				//分享数
				if(newsInformation.getShareNum() != null && !"".equals(newsInformation.getShareNum())){
					voNewsInformation.setShareNum(newsInformation.getShareNum());
				}else{
					voNewsInformation.setShareNum(0);
				}
				
				//评论数
				Integer commentNum = newsInformationCommentsMapper.findCount(newsInformation.getId());
				voNewsInformation.setCommentNum(commentNum);
				//添加时间
				if(newsInformation.getAddTime() != null){
					voNewsInformation.setAddTime(TimeUtils.getDateFormat(newsInformation.getAddTime()));
				}
				voList.add(voNewsInformation);
			}
		}
		return voList;
	}

	/**
	 * 修改文章信息
	 */
	@Override
	public boolean updateNewsInformation(NewsInformation news) {
		boolean b = false;
		try {
			newsInformationMapper.updateNewsInformation(news);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 删除文章
	 */
	@Override
	public boolean deleteNewsInformation(int newsId) {
		boolean b = false;
		try {
			//删除文章
			newsInformationMapper.deleteNewsInformation(newsId);
			//删除文章评论
			newsInformationCommentsMapper.deleteNewsCommentsByNewsIds(newsId);
			//删除文章点击量
			newsClickMapper.deleteNewsClick(newsId);
			//删除文章收藏
			collectionMapper.deleteNewsCollectionByNewsId(newsId);
			//删除文章分享
			newsShareMapper.deleteNewsShareByNewsId(newsId);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 查询文章信息
	 */
	@Override
	public NewsInformation findNewsInformationById(int newsId) {
		NewsInformation news = newsInformationMapper.findNewsInformationById(newsId);
		if(news != null){
			return news;
		}
		return null;
	}
	
	/**
	 * 添加或修改文章信息
	 */
	@Override
	public boolean addOrEditNewsInformation(NewsInformation news) {
		boolean b = false;
		try {
			String filepath = ConfigProUtils.get("BASE_DIR")+ConfigProUtils.get("NEWS_HTML_MB_URL");					//模板路径
			String path = ConfigProUtils.get("BASE_DIR")+ConfigProUtils.get("QUESTION_HTML_URL");					//生成HTML路径
			
			String content = news.getContent().replaceAll("<img", "<img style='width:100%!important'");
			news.setContent(content);
			if(news.getId()==0){//添加 
				news.setNewsUrl("");
				if(news.getIsPush() == null){
					news.setIsPush(0);
				}
				if(StringUtils.isBlank(news.getPeriod())){
					news.setPeriod("0");
				}
				if(news.getNewsUrl() == null){
					news.setNewsUrl("");
				}
				news.setPraise(0);
				news.setClicks(0);
				news.setShareNum(0);
				news.setHospitalId(49);
				newsInformationMapper.addNewsInformation(news);
				String url = createHtml.CreateHtmlFile(filepath, path, content,news.getId().toString(),news.getSourceFrom(),news.getFromLogoUrl(),news.getTitle());
				//解析上传成功后html路径
				JSONObject jsonObject = JSONObject.fromObject(url);
				if(jsonObject.getInt("msg") == 1) {
					JSONObject json = JSONObject.fromObject(jsonObject.getString("data"));
					//地址
					url = json.getString("fileUrl");
					news.setNewsUrl("/"+url);
				}
				newsInformationMapper.updateNewsInformation(news);
			}else{//修改
				String url = createHtml.CreateHtmlFile(filepath, path, content,news.getId().toString(),news.getSourceFrom(),news.getFromLogoUrl(),news.getTitle());
				//解析上传成功后html路径
				JSONObject jsonObject = JSONObject.fromObject(url);
				if(jsonObject.getInt("msg") == 1) {
					JSONObject json = JSONObject.fromObject(jsonObject.getString("data"));
					//地址
					url = json.getString("fileUrl");
					news.setNewsUrl("/"+url);
				}
				newsInformationMapper.updateNewsInformation(news);
			}
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	
}
