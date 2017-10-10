package com.jumper.angel.user.statistics.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.jumper.angel.home.login.entity.CrmAdmin;
import com.jumper.angel.home.login.entity.MonitorAdmin;
import com.jumper.angel.home.login.mapper.MonitorAdminMapper;
import com.jumper.angel.hospital.doctor.mapper.HospitalDoctorInfoMapper;
import com.jumper.angel.hospital.hospital.entity.HospitalDoctorMajor;
import com.jumper.angel.hospital.hospital.entity.HospitalMajorInfo;
import com.jumper.angel.hospital.hospital.entity.HospitalModuleHost;
import com.jumper.angel.hospital.hospital.entity.HospitalRelated;
import com.jumper.angel.hospital.hospital.entity.HospitalServiceList;
import com.jumper.angel.hospital.hospital.entity.HospitalServiceModule;
import com.jumper.angel.hospital.hospital.entity.OperationRecords;
import com.jumper.angel.hospital.hospital.mapper.HospitalDoctorMajorMapper;
import com.jumper.angel.hospital.hospital.mapper.HospitalMajorInfoMapper;
import com.jumper.angel.hospital.hospital.mapper.HospitalModuleHostMapper;
import com.jumper.angel.hospital.hospital.mapper.HospitalRelatedMapper;
import com.jumper.angel.hospital.hospital.mapper.HospitalServiceListMapper;
import com.jumper.angel.hospital.hospital.mapper.HospitalServiceModuleMapper;
import com.jumper.angel.hospital.hospital.mapper.OperationRecordsMapper;
import com.jumper.angel.hospital.hospital.vo.HospitalInfoManageVo;
import com.jumper.angel.hospital.hospital.vo.HospitalInfoVo;
import com.jumper.angel.user.statistics.entity.City;
import com.jumper.angel.user.statistics.entity.HospitalInfo;
import com.jumper.angel.user.statistics.entity.Province;
import com.jumper.angel.user.statistics.mapper.BindHospitalLogMapper;
import com.jumper.angel.user.statistics.mapper.CityMapper;
import com.jumper.angel.user.statistics.mapper.HospitalInfoMapper;
import com.jumper.angel.user.statistics.mapper.ProvinceMapper;
import com.jumper.angel.user.statistics.service.HospitalInfoService;
import com.jumper.angel.user.statistics.vo.VOAllHospitalStatisticsInfo;
import com.jumper.angel.user.statistics.vo.VOCityInfo;
import com.jumper.angel.user.statistics.vo.VOProvinceInfo;
import com.jumper.angel.utils.ConfigProUtils;
import com.jumper.angel.utils.HttpPost;
import com.jumper.angel.utils.PinyinAPI;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.ReturnMsg;
import com.jumper.angel.utils.TimeUtils;
import com.jumper.angel.utils.json.JsonUtils;

/**
 * 医院信息 serviceiImpl
 * @author gyx
 * @time 2017年3月14日
 */
@Service
public class HospitalServiceImpl implements HospitalInfoService {
	private Logger logger = Logger.getLogger(HospitalServiceImpl.class);
	
	@Autowired
	private HospitalInfoMapper hospitalInfoMapper;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private BindHospitalLogMapper bindHospitalLogMapper;
	@Autowired
	private ProvinceMapper provinceMapper;
	@Autowired
	private HospitalRelatedMapper hospitalRelatedMapper;
	@Autowired
	private HospitalDoctorMajorMapper doctorMajorMapper;
	@Autowired
	private HospitalMajorInfoMapper hospitalMajorInfoMapper;
	@Autowired
	private HospitalDoctorInfoMapper doctorInfoMapper;
	@Autowired
	private OperationRecordsMapper operationRecordsMapper;
	@Autowired
	private HospitalModuleHostMapper hospitalModuleHostMapper;
	@Autowired
	private HospitalServiceModuleMapper moduleMapper;
	@Autowired
	private HospitalServiceListMapper listMapper;
	@Autowired
	private MonitorAdminMapper monitorAdminMapper;
	
	/**
	 * 获取医院总表 展示数据总数
	 */
	@Override
	public int findCount(String keywords, int province, int city) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keywords", keywords);
		map.put("province", province);
		map.put("city", city);
		int count = hospitalInfoMapper.findCount(map);
		return count;
	}
	/**
	 * 获取医院总表 展示数据总数
	 */
	@Override
	public int findCount2(String keywords, int province, int city) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keywords", keywords);
		map.put("province", province);
		map.put("city", city);
		int count = hospitalInfoMapper.findCount2(map);
		return count;
	}

	/**
	 * 通过省份获取城市列表
	 */
	@Override
	public List<VOCityInfo> getCityByProvince(int id) {
		List<City> cityList = cityMapper.getCityByProvince(id);
		List<VOCityInfo> dataList = convertCity(cityList);
		return dataList;
	}
	@Override
	public List<VOProvinceInfo> getProvince() {
		List<Province> provinceList = provinceMapper.selectProvinceList();
		List<VOProvinceInfo> dataList = convertProvince(provinceList);
		return dataList;
	}
	
	/**
	 * 获取医院总表统计数据
	 */
	@Override
	public List<VOAllHospitalStatisticsInfo> findAllHospitalStatisticsList(
			int beginIndex, int everyPage, String keywords, int province,
			int city, String pointTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keywords", keywords);
		map.put("province", province);
		map.put("city", city);
		map.put("beginIndex", beginIndex);
		map.put("everyPage", everyPage);
		List<HospitalInfo> hospList = hospitalInfoMapper.findHospitalList(map);
		logger.info("读取数据结束，开始组装医院总表数据==========================");
		List<VOAllHospitalStatisticsInfo> statisticsList = convertHospitalStatisticsInfo(hospList, pointTime);
		logger.info("组装医院总表数据结束 ==========================");
		return statisticsList;
	}
	
	/**
	 * 组装城市数据
	 * @param list
	 * @return
	 */
	public List<VOCityInfo> convertCity(List<City> list){
		List<VOCityInfo> dataList = new ArrayList<VOCityInfo>();
		if(list != null && list.size() > 0){
			for(City city : list){
				VOCityInfo info = new VOCityInfo();
				info.setId(city.getId());
				info.setCity_name(city.getCityname());
				info.setAbbrev(city.getAbbrev());
				info.setProvince_id(city.getProid());
				dataList.add(info);
			}
		}
		return dataList;
	}
	/**
	 * 组装省份数据
	 * @param list
	 * @return
	 */
	public List<VOProvinceInfo> convertProvince(List<Province> list){
		List<VOProvinceInfo> dataList = new ArrayList<VOProvinceInfo>();
		if(list != null && list.size() > 0){
			for(Province province : list){
				VOProvinceInfo info = new VOProvinceInfo();
				info.setId(province.getId());
				info.setProvince_name(province.getProname());;
				info.setAbbrev(province.getAbbrevation());
				dataList.add(info);
			}
		}
		return dataList;
	}

	/**
	 * 组装医院总表统计数据
	 * @param hospList
	 * @return
	 */
	public List<VOAllHospitalStatisticsInfo> convertHospitalStatisticsInfo(List<HospitalInfo> hospList, String pointTime){
		List<VOAllHospitalStatisticsInfo> dataList = new ArrayList<VOAllHospitalStatisticsInfo>();
		if(hospList != null && hospList.size() > 0){
			for (HospitalInfo hospitalInfo : hospList) {
				VOAllHospitalStatisticsInfo statisticsInfo = new VOAllHospitalStatisticsInfo();
				//医院名称
				statisticsInfo.setHospitalName(hospitalInfo.getName());
				//总用户数
				int totalCount = bindHospitalLogMapper.countCommonHospitalUser(hospitalInfo.getId());
				statisticsInfo.setTotalCount(totalCount);
				
				//日新增用户数
				Map<String, Object> map = new HashMap<String, Object>();
				String day = TimeUtils.converStringDate(new Date(), "yyyy-MM-dd HH:mm:ss").substring(0,10);
				if(pointTime != null && pointTime != ""){
					day = pointTime;
				}
				String beginDate = day + " 00:00:00";
				String endDate = day + " 23:59:59";
				map.put("hospitalId", hospitalInfo.getId());
				map.put("beginDate", beginDate);
				map.put("endDate", endDate);
				//4.0新版APP注册用户首次绑定医院的数量
				int dailyAddCount = bindHospitalLogMapper.countfirstBindUser(map);
				//3.0老版APP注册用户首次绑定医院的数量
				int oldAppAddCount = bindHospitalLogMapper.countOldAppFirstBind(map);
				statisticsInfo.setDailyAddCount(dailyAddCount+oldAppAddCount);
				
				//体重营养门诊人数
				int weightOutPatientCount = bindHospitalLogMapper.countWeightOutPatient(map);
				statisticsInfo.setWeightOutPatientCount(weightOutPatientCount);
				
				//医院问诊服务次数
				int hospConsultantCount = bindHospitalLogMapper.countHospConsultant(map);
				statisticsInfo.setHospConsultantCount(hospConsultantCount);
				
				//设备租赁服务次数
				int leaseCount = bindHospitalLogMapper.countLeaseOrder(map);
				statisticsInfo.setLeaseCount(leaseCount);
				
				//胎心监护服务次数
				int heartMonitorCount = bindHospitalLogMapper.countHeartMonitor(map);
				statisticsInfo.setHeartMonitorCount(heartMonitorCount);
				
				//近7天新增用户数
				beginDate = TimeUtils.getDateFormat(TimeUtils.getBeforeDayByDate(6, TimeUtils.convertToDate(day))).substring(0,10)+" 00:00:00";
				map.put("beginDate", beginDate);
				int sevenDaysAddCount = bindHospitalLogMapper.countfirstBindUser(map);
				statisticsInfo.setSevenDaysAddCount(sevenDaysAddCount);
				
				//本月新增用户数
				beginDate = TimeUtils.getDateFormat(TimeUtils.getFirstDayOfMonth(TimeUtils.convertToDate(day))).substring(0,10)+" 00:00:00";
				endDate = TimeUtils.getDateFormat(TimeUtils.getLastDayOfMonth(TimeUtils.convertToDate(day))).substring(0,10) + " 23:59:59";
				map.put("beginDate", beginDate);
				map.put("endDate", endDate);
				int currMonthAddCount = bindHospitalLogMapper.countfirstBindUser(map);
				statisticsInfo.setCurrMonthAddCount(currMonthAddCount);
				
				dataList.add(statisticsInfo);
			}
		}
		
		return dataList;
	}

	/**
	 * 根据医院名称查询医院信息
	 */
	@Override
	public HospitalInfo findHospitalInfoByName(String keywords) {
		HospitalInfo hospitalInfo = hospitalInfoMapper.findHospitalInfoByName(keywords);
		if(hospitalInfo != null){
			return hospitalInfo;
		}
		return null;
	}

	/**
	 * 获取某一医院某一时间段内的所有统计数据
	 */
	@Override
	public Map<String, Object> countHospitalStatistics(Integer id,
			String startTime, String endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hospitalId", id);
		map.put("beginDate", startTime);
		map.put("endDate", endTime);
		//总用户量
		int totalCount = bindHospitalLogMapper.countCommonHospitalUser(id);
		//新增用户数（使用新版本）
		int addCount = bindHospitalLogMapper.countfirstBindUser(map);
		//使用旧版本新增用户数
		int oldAddCount = bindHospitalLogMapper.countOldAppFirstBind(map);
		int totalAddCount = addCount + oldAddCount;
		//胎心监护服务次数
		int heartMonitorCount = bindHospitalLogMapper.countHeartMonitor(map);
		//设备租赁服务次数
		int leaseCount = bindHospitalLogMapper.countLeaseOrder(map);
		//体重营养门诊人数
		int weightOutPatientCount = bindHospitalLogMapper.countWeightOutPatient(map);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		//总计关联行为
		int totalBindCount = bindHospitalLogMapper.countTotalBindUser(map);
		
		if((startTime != null && !"".equals(startTime)) || (endTime != null && !"".equals(endTime))){
			if(endTime == null || "".equals(endTime)){
				endTime = TimeUtils.converStringDate(new Date(), "yyyy年MM月dd日");
				startTime = TimeUtils.converStringDate(TimeUtils.convertToDate(startTime), "yyyy年MM月dd日");
			}else if(startTime == null || "".equals(startTime)){
				//查询该医院首次绑定用户的最早时间
				String minTime = bindHospitalLogMapper.findMinBindingTime(id);
				startTime = TimeUtils.converStringDate(TimeUtils.convertToDate(minTime), "yyyy年MM月dd日");
				endTime = TimeUtils.converStringDate(TimeUtils.convertToDate(endTime), "yyyy年MM月dd日");
			}else{
				startTime = TimeUtils.converStringDate(TimeUtils.convertToDate(startTime), "yyyy年MM月dd日");
				endTime = TimeUtils.converStringDate(TimeUtils.convertToDate(endTime), "yyyy年MM月dd日");
			}
			dataMap.put("searchTimePeriod", startTime+"至"+endTime);
		}
		dataMap.put("totalCount", totalCount);
		dataMap.put("addCount", addCount);
		dataMap.put("heartMonitorCount", heartMonitorCount);
		dataMap.put("leaseCount", leaseCount);
		dataMap.put("weightOutPatientCount", weightOutPatientCount);
		dataMap.put("totalBindCount", totalBindCount);
		dataMap.put("oldAddCount", oldAddCount);
		dataMap.put("totalAddCount", totalAddCount);
		return dataMap;
	}

	/**
	 * 根据医院名称查询医院列表信息
	 */
	@Override
	public List<HospitalInfo> searchHospitalByName(String keywords) {
		List<HospitalInfo> hosList = hospitalInfoMapper.searchHospitalByName(keywords);
		if(hosList != null && hosList.size() > 0){
			return hosList;
		}
		return null;
	}

	@Override
	public List<HospitalInfo> getHosptitalList(String keywords) {
		return hospitalInfoMapper.selectHospitalByName(keywords);
	}

	@Override
	public List<HospitalInfoVo> getHosptitalInfoList(int beginIndex, int everyPage, int statusType,
			Integer serviceType, Integer provinceId, Integer cityId, String keywords) {
		List<HospitalInfoVo> voList = new ArrayList<>();
		List<Map<String, Object>> hosptitalInfoList = hospitalInfoMapper.getHosptitalInfoList(beginIndex,everyPage,statusType,serviceType,provinceId,cityId,keywords);
		for (Map<String, Object> map : hosptitalInfoList) {
			HospitalInfoVo hospitalInfoVo = new HospitalInfoVo();
			hospitalInfoVo.setId((Integer)map.get("hospitalId"));
			hospitalInfoVo.setName(map.get("hospitalName").toString());
			hospitalInfoVo.setImgUrl(map.get("img_url")!=null?map.get("img_url").toString():"");
			hospitalInfoVo.setAddTime(map.get("add_time")!=null?TimeUtils.getTimeStampNumberFormat2((Timestamp)map.get("add_time")):"");
			hospitalInfoVo.setCity(map.get("cityName").toString());
			hospitalInfoVo.setCityId((Integer)map.get("city_id"));
			hospitalInfoVo.setProvince(map.get("proName").toString());
			hospitalInfoVo.setProvinceId((Integer)map.get("province_id"));
			
			hospitalInfoVo.setLoginTime(map.get("login_date")!=null?TimeUtils.getTimeStampNumberFormat2((Timestamp)map.get("login_date")):"");
			hospitalInfoVo.setUserName(map.get("userName")!=null?map.get("userName").toString():"");
			hospitalInfoVo.setService(getService((Integer)map.get("hospitalId")));
			if (map.get("is_payment")!=null) {
				if (Integer.valueOf(map.get("is_payment").toString())==1) {
					hospitalInfoVo.setIsPayment(1);;
				}else {
					hospitalInfoVo.setIsPayment(0);
				}
			}
			if (map.get("is_locked")!=null) {
				if ((boolean)map.get("is_locked")) {
					hospitalInfoVo.setIsLocked(1);
				}else {
					hospitalInfoVo.setIsLocked(0);
				}
			}
			if (map.get("is_enabled")!=null) {
				if ((boolean)map.get("is_enabled")) {
					hospitalInfoVo.setIsEnabled(1);
				}else {
					hospitalInfoVo.setIsEnabled(0);
				}
			}
			hospitalInfoVo.setMonitorId(map.get("monitorId")!=null?(Integer)map.get("monitorId"):null);
			hospitalInfoVo.setFilePath(ConfigProUtils.get("file_path"));
			voList.add(hospitalInfoVo);
		}
		return voList;
	}
	

	@Override
	public String[] getService(Integer hospitalId) {
		String[] service = new String[8];
		//服务类型：1常规监护，2实时监护，3院内监护，4医院问诊，5营养管理，6孕妇学校，7高危管理，8母子手册
		int index = 0;
		List<Integer> monitorSetting = hospitalInfoMapper.selectMonitorSetting(hospitalId);
		if (monitorSetting.size()>0) {
			for (Integer integer : monitorSetting) {
				if (integer == 0) {
					service[index]="常规监护";	index ++;
				}
				if (integer == 1) {
					service[index]="实时监护";	index ++;
				}
				if (integer == 2) {
					service[index]="院内监护";	index ++;
				}
			}
		}
		HospitalInfo hospitalInfo = hospitalInfoMapper.selectByPrimaryKey(hospitalId);
		if (hospitalInfo != null) {
			if (hospitalInfo.getIsConsultant()!= null &&hospitalInfo.getIsConsultant() == 1) {
				service[index]="医院问诊";	index ++;
			}
			if (hospitalInfo.getIsWeight() != null && hospitalInfo.getIsWeight() == 1) {
				service[index]="营养管理";index ++;
			}
			if (hospitalInfo.getIsSchool() != null && hospitalInfo.getIsSchool() == 1) {
				service[index]="孕妇学校";index ++;
			}
		}
		List<HospitalModuleHost> hospitalModuleHosts = hospitalModuleHostMapper.selectByHospitalId(hospitalId);
		if (hospitalModuleHosts != null && hospitalModuleHosts.size() > 0) {
			for (HospitalModuleHost hospitalModuleHost : hospitalModuleHosts) {
				if (hospitalModuleHost.getModuleNum() != null && hospitalModuleHost.getModuleNum() == 2) {
					service[index]="高危管理";index ++;
				}
				if (hospitalModuleHost.getModuleNum() != null && hospitalModuleHost.getModuleNum() == 5) {
					service[index]="母子手册";index ++;
				}
			}
		}
		return service;
	}
	@Override
	public int findCount3(String keywords, Integer provinceId, Integer cityId, Integer serviceType) {
		Map<String,Object> map = new HashMap<>();
		map.put("keywords", keywords);
		map.put("provinceId", provinceId);
		map.put("cityId", cityId);
		map.put("serviceType", serviceType);
		return hospitalInfoMapper.findCount3(map);
	}
	@Override
	public int findCount4(String keywords, Integer provinceId, Integer cityId, Integer serviceType) {
		Integer moduleNum = 0;
		if (serviceType == 7) {//高危
			moduleNum = 2;
		}
		if (serviceType == 8) {//母子手册
			moduleNum = 5;
		}
		List<HospitalModuleHost> moduleHosts = hospitalModuleHostMapper.selectByModuleNum(moduleNum);
		int count = moduleHosts.size();
		return count;
	}
	
	public HospitalInfo getHospitalInfoByName(String name) {
		logger.info("------------HospitalServiceImpl.getHospitalInfoByName, name:" + name);
		if (StringUtils.isBlank(name)) {
			return null;
		}
		HospitalInfo hospitalInfo = hospitalInfoMapper.getHospitalInfoByName(name);
		return hospitalInfo;
	}
	
	@Override
	public ResultMsg addHospitalInfo(HospitalInfoManageVo vo) {
		try {
			String name = vo.getName();
			if (StringUtils.isBlank(name)) {
				return new ResultMsg(ReturnMsg.FAIL, "医院名称不能为空!");
			}
			if (getHospitalInfoByName(name) != null) {
				return new ResultMsg(ReturnMsg.FAIL, "该医院已存在!");
			}
			String address = vo.getAddress();
			if (StringUtils.isBlank(address)) {
				return new ResultMsg(ReturnMsg.FAIL, "医院地址不能为空!");
			}
			// 如果医院简介为null,把医院简介设为空字符串
			String introduction = vo.getIntroduction();
			vo.setIntroduction(Strings.nullToEmpty(introduction));
			HospitalInfo hospitalInfo = new HospitalInfo();
			Integer province = vo.getProvince();
			Integer city = vo.getCity();
			if (province == null) {
				return new ResultMsg(ReturnMsg.FAIL, "省不能为空!");
			}
			if (city == null) {
				return new ResultMsg(ReturnMsg.FAIL, "市不能为空!");
			}
			hospitalInfo.setCityId(city);
			hospitalInfo.setProvinceId(province);
			hospitalInfo.setImgUrl(vo.getUploadfile());
			hospitalInfo.setName(name);
			hospitalInfo.setIntroduction(introduction);
			hospitalInfo.setAddress(address);
			hospitalInfo.setIsValid(1);
			hospitalInfo.setAddTime(TimeUtils.getCurrentTime());
			hospitalInfo.setOrderKey(PinyinAPI.getFirstHead(name));
			hospitalInfo.setIsPayment((byte) 0);
			hospitalInfo.setIsBlood( 0);
			hospitalInfoMapper.insertSelective(hospitalInfo);
			
			HospitalInfo hospitalInfo2 = getHospitalInfoByName(name);
			if (hospitalInfo2 == null) {
				return new ResultMsg(ReturnMsg.FAIL, "添加医院信息失败!");
			}
			
//			//注册IM信息
//			String postUrl = ConfigProUtils.get("COMMON_PROJECT_PATH")+"/accounts/add1?appid=101&openid=yh_"+hospitalInfo2.getId()+"&nick="+hospitalInfo2.getName()+"&faceUrl="+hospitalInfo2.getImgUrl();
//			String val = HttpPost.doPost(postUrl, "");
//			HashMap<String, Object> param = JsonUtils.toHashMap(val);
//			String msg =  param.get("msg").toString();
//			if (!"1".equals(msg)){
//				return new ResultMsg(ReturnMsg.FAIL, "注册IM信息失败!");
//			}

			//合作医院
			Integer hospital = vo.getHospital();
			if (hospital != null) {
				HospitalRelated hospitalRelated = new HospitalRelated();
				hospitalRelated.setAddTime(TimeUtils.getCurrentTime());
				hospitalRelated.setHospitalId(hospitalInfo2.getId());
				hospitalRelated.setObjectId(hospital);
				hospitalRelated.setType(1);
				hospitalRelatedMapper.insertSelective(hospitalRelated);
			}

			//设置科室
			Integer[] majors = vo.getMajorId();
			List<Integer> list = new ArrayList<>();
			list.addAll(Arrays.asList(majors));
			for (Integer id : list) {
				HospitalDoctorMajor doctorMajor = doctorMajorMapper.selectByPrimaryKey(id);
				if (doctorMajor != null) {
					HospitalMajorInfo majorInfo = new HospitalMajorInfo();
					majorInfo.setMajorId(doctorMajor.getId());
					majorInfo.setHospitalId(hospitalInfo2.getId());
					majorInfo.setAddTime(TimeUtils.getCurrentTime());
					majorInfo.setIsNetwork(0);
					majorInfo.setIsDelete(0);
					hospitalMajorInfoMapper.insertSelective(majorInfo);
				}
			}
			//添加功能
			//查询前面12个默认功能
			List<HospitalServiceModule> moduleList = moduleMapper.selectByKey(12);
			for (HospitalServiceModule module : moduleList) {
				Integer id = module.getId();
				HospitalServiceList service = new HospitalServiceList();
				service.setModuleId(id);						//模块ID
				service.setCreatedDate(new Date().getTime());	//添加时间
				service.setHospitalId(hospitalInfo2.getId());	//医院ID
				service.setComment("");							//备注
				service.setIconImg("");							//模块图标
				service.setUrl("");								//h5链接地址
				service.setEntryStat(0);						//入口状态:0正常;1隐藏的
				service.setUrlStat(0);							//链接状态:0默认;1自定义
//				//默认开通1	2	4	5	7	8	10	11	12
//				//原生       1	4	5	7	9	10	11	12
				if (id == 1) {//1	医院简介、/hospital/introduce?hospitalId=42	
					service.setFunctionGroup(0);	//功能分组:0基础功能;1平台功能;2院内功能;3自定义功能
					service.setPostionOrder(-4);		//位置排序值
					service.setClosed(0);			//是否关闭
					service.setDefaultUrl("angeldoctor://m.jumper.com/hospital/introduce");//默认h5链接地址
					service.setNativeApp(1);		//是否原生应用:0h5应用，1原生，默认为0
				}else if (id == 2) {//2	医院资讯、
					service.setFunctionGroup(0);	//功能分组:0基础功能;1平台功能;2院内功能;3自定义功能
					service.setPostionOrder(-3);		//位置排序值
					service.setClosed(0);			//是否关闭
					service.setDefaultUrl("/hospital/page/queryHospitalNewsIn");		//默认h5链接地址
					service.setNativeApp(0);		//是否原生应用:0h5应用，1原生，默认为0
				}else if (id == 3) {//3医院建册、
					service.setFunctionGroup(0);	//功能分组:0基础功能;1平台功能;2院内功能;3自定义功能
					service.setPostionOrder(-2);		//位置排序值
					service.setClosed(1);			//是否关闭
					service.setDefaultUrl("");		//默认h5链接地址
					service.setNativeApp(0);		//是否原生应用:0h5应用，1原生，默认为0
				}else if (id == 4) {//4	产检提醒；angeldoctor://m.jumper.com/hospital/checkReminder
					service.setFunctionGroup(0);	//功能分组:0基础功能;1平台功能;2院内功能;3自定义功能
					service.setPostionOrder(-1);		//位置排序值
					service.setClosed(0);			//是否关闭
					service.setDefaultUrl("angeldoctor://m.jumper.com/hospital/checkReminder");//默认h5链接地址
					service.setNativeApp(1);		//是否原生应用:0h5应用，1原生，默认为0
				}else if (id == 5) {//5	胎心监护  angeldoctor://m.jumper.com/monitor/fetalheart
					service.setFunctionGroup(1);	//功能分组:0基础功能;1平台功能;2院内功能;3自定义功能
					service.setPostionOrder(1);		//位置排序值
					service.setClosed(0);			//是否关闭
					service.setDefaultUrl("angeldoctor://m.jumper.com/monitor/fetalheart");//默认h5链接地址
					service.setNativeApp(1);		//是否原生应用:0h5应用，1原生，默认为0
				}else if (id == 6) {//6设备租赁  		/lease/skip/welcome
					service.setFunctionGroup(1);	//功能分组:0基础功能;1平台功能;2院内功能;3自定义功能
					service.setPostionOrder(2);		//位置排序值
					service.setClosed(1);			//是否关闭
					service.setDefaultUrl("/lease/skip/welcome");//默认h5链接地址
					service.setNativeApp(0);		//是否原生应用:0h5应用，1原生，默认为0
				}else if (id == 7) {//7	报告解读  angeldoctor://m.jumper.com/hospital/reportInterpretation
					service.setFunctionGroup(1);	//功能分组:0基础功能;1平台功能;2院内功能;3自定义功能
					service.setPostionOrder(3);		//位置排序值
					service.setClosed(0);			//是否关闭
					service.setDefaultUrl("angeldoctor://m.jumper.com/hospital/reportInterpretation");//默认h5链接地址
					service.setNativeApp(1);		//是否原生应用:0h5应用，1原生，默认为0
				}else if (id == 8) {//8	检查报告	/hospital/page/reportPage
					service.setFunctionGroup(1);	//功能分组:0基础功能;1平台功能;2院内功能;3自定义功能
					service.setPostionOrder(4);		//位置排序值
					service.setClosed(0);			//是否关闭
					service.setDefaultUrl("/hospital/page/reportPage");//默认h5链接地址
					service.setNativeApp(0);		//是否原生应用:0h5应用，1原生，默认为0
				}else if (id == 9) {//9医院问诊  angeldoctor://m.jumper.com/hospital/consultation
					service.setFunctionGroup(1);	//功能分组:0基础功能;1平台功能;2院内功能;3自定义功能
					service.setPostionOrder(5);		//位置排序值
					service.setClosed(1);			//是否关闭
					service.setDefaultUrl("angeldoctor://m.jumper.com/hospital/consultation");//默认h5链接地址
					service.setNativeApp(1);		//是否原生应用:0h5应用，1原生，默认为0
				}else if (id == 10) {//10	找医生    angeldoctor://m.jumper.com/hospital/doctors
					service.setFunctionGroup(1);	//功能分组:0基础功能;1平台功能;2院内功能;3自定义功能
					service.setPostionOrder(6);		//位置排序值
					service.setClosed(0);			//是否关闭
					service.setDefaultUrl("angeldoctor://m.jumper.com/hospital/doctors");//默认h5链接地址
					service.setNativeApp(1);		//是否原生应用:0h5应用，1原生，默认为0
				}else if (id == 11) {//11	体重管理  angeldoctor://m.jumper.com/hospital/weightManagement
					service.setFunctionGroup(1);	//功能分组:0基础功能;1平台功能;2院内功能;3自定义功能
					service.setPostionOrder(7);		//位置排序值
					service.setClosed(0);			//是否关闭
					service.setDefaultUrl("angeldoctor://m.jumper.com/hospital/weightManagement");//默认h5链接地址
					service.setNativeApp(1);		//是否原生应用:0h5应用，1原生，默认为0
				}else if (id == 12) {//12	血糖管理  angeldoctor://m.jumper.com/hospital/bloodGlucoseManagement
					service.setFunctionGroup(1);	//功能分组:0基础功能;1平台功能;2院内功能;3自定义功能
					service.setPostionOrder(8);		//位置排序值
					service.setClosed(0);			//是否关闭
					service.setDefaultUrl("angeldoctor://m.jumper.com/hospital/bloodGlucoseManagement");//默认h5链接地址
					service.setNativeApp(1);		//是否原生应用:0h5应用，1原生，默认为0
				}
				int msg2 = listMapper.insertSelective(service);
				if (msg2 != 1) {
					return new ResultMsg(ReturnMsg.FAIL, "添加医院功能失败!");
				}
			}
			
			
			return new ResultMsg(ReturnMsg.SUCCESS, "添加医院信息成功!");
		} catch (Exception e) {
			logger.error("++++++++++++++HospitalServiceImpl.addHospitalInfo.error", e);
			return new ResultMsg(ReturnMsg.FAIL, "添加医院信息异常!");
		}
	}
	@Override
	public ResultMsg updateHospitalInfo(HospitalInfoManageVo vo) {
		try {
			String name = vo.getName();
			if (StringUtils.isBlank(name)) {
				return new ResultMsg(ReturnMsg.FAIL, "医院名称不能为空!");
			}
			Integer id = vo.getId();
			if (id == null) {
				return new ResultMsg(ReturnMsg.FAIL, "医院ID不能为空!");
			}
			HospitalInfo hospitalInfo = hospitalInfoMapper.selectByPrimaryKey(id);
			if (hospitalInfo == null) {
				return new ResultMsg(ReturnMsg.FAIL, "查询不到此医院!");
			}
			String oldHospitalName = hospitalInfo.getName();
			// 当医院名称发生变化时，查询数据库变化的医院名称是否已经存在了
			if (oldHospitalName != null && !name.equals(oldHospitalName)) {
				// 检查该医院是否已经存在
				if (hospitalInfoMapper.getHospitalInfoByName(name) != null) {
					return new ResultMsg(ReturnMsg.FAIL, "该医院名称已存在!");
				}
			}
			String address = vo.getAddress();
			if (StringUtils.isBlank(address)) {
				return new ResultMsg(ReturnMsg.FAIL, "医院地址不能为空!");
			}
			// 如果医院简介为null,把医院简介设为空字符串
			String introduction = vo.getIntroduction();
			vo.setIntroduction(Strings.nullToEmpty(introduction));
			Integer province = vo.getProvince();
			Integer city = vo.getCity();
			if (province == null) {
				return new ResultMsg(ReturnMsg.FAIL, "省不能为空!");
			}
			if (city == null) {
				return new ResultMsg(ReturnMsg.FAIL, "市不能为空!");
			}
			hospitalInfo.setCityId(city);
			hospitalInfo.setProvinceId(province);
			hospitalInfo.setImgUrl(vo.getUploadfile());
			hospitalInfo.setName(name);
			hospitalInfo.setIntroduction(introduction);
			hospitalInfo.setAddress(address);
			hospitalInfo.setIsValid(1);
			hospitalInfo.setOrderKey(PinyinAPI.getFirstHead(name));
			hospitalInfoMapper.updateByPrimaryKeySelective(hospitalInfo);

			Integer hospital = vo.getHospital();
			if (hospital != null) {
				Integer typeChange = vo.getTypeChange();
				HospitalRelated hospitalRelated = hospitalRelatedMapper.selectByHospId(id);
				if (hospitalRelated != null) {
					hospitalRelated.setObjectId(hospital);
					hospitalRelated.setType(typeChange);
				} else {
					hospitalRelated = new HospitalRelated();
					hospitalRelated.setAddTime(TimeUtils.getCurrentTime());
					hospitalRelated.setHospitalId(hospitalInfo.getId());
					hospitalRelated.setObjectId(hospital);
					hospitalRelated.setType(typeChange);
					hospitalRelatedMapper.insertSelective(hospitalRelated);
				}
			}
			// 先删掉原来的科室
			List<HospitalMajorInfo> HospitalMajorInfoList = hospitalMajorInfoMapper.selectByHospitalId(id);
			// 原有科室
			Map<String, HospitalMajorInfo> oldMajor = new HashMap<>();
			if (HospitalMajorInfoList != null) {
				for (HospitalMajorInfo hospitalMajorInfo : HospitalMajorInfoList) {
					oldMajor.put(hospitalMajorInfo.getMajorId() + "", hospitalMajorInfo);
					hospitalMajorInfo.setIsDelete(1);
					hospitalMajorInfoMapper.updateByPrimaryKeySelective(hospitalMajorInfo);
				}
			}
			Integer[] majors = vo.getMajorId();
			List<Integer> list = new ArrayList<>();
			if (majors != null) {
				list = new ArrayList<>(Arrays.asList(majors));
			}
			// 新保存科室
			for (int i = 0; i < list.size(); i++) {// 循环新保存科室
				HospitalDoctorMajor doctorMajor = doctorMajorMapper.selectByPrimaryKey(list.get(i));
				boolean ishave = false;// 是否已有数据
				if (HospitalMajorInfoList != null) {
					for (HospitalMajorInfo hospitalMajorInfo : HospitalMajorInfoList) {
						if (doctorMajor.getId() == hospitalMajorInfo.getMajorId()) {// 如果新科室id等于旧科室id,就是已有数据,则保存
							ishave = true;
							hospitalMajorInfo.setIsDelete(0);// update
							hospitalMajorInfoMapper.updateByPrimaryKeySelective(hospitalMajorInfo);
						}
					}
				}
				if (!ishave) {// 没有数据则新增
					HospitalMajorInfo majorInfo = new HospitalMajorInfo();
					majorInfo.setMajorId(doctorMajor.getId());
					majorInfo.setHospitalId(id);
					majorInfo.setAddTime(TimeUtils.getCurrentTime());
					majorInfo.setIsDelete(0);
					hospitalMajorInfoMapper.insertSelective(majorInfo);
				}
			}
			
			List<MonitorAdmin> list2 = monitorAdminMapper.selectByHospitalId(hospitalInfo.getId());
			if (list2.size()>0) {
				//修改IM信息
				String postUrl = ConfigProUtils.get("COMMON_PROJECT_PATH")+"/accounts/edit1?appid=101&openid=yh_"+hospitalInfo.getId()+"&nick="+hospitalInfo.getName()+"&faceUrl="+hospitalInfo.getImgUrl();
				String val = HttpPost.doPost(postUrl, "");
				HashMap<String, Object> param = JsonUtils.toHashMap(val);
				String msg =  param.get("msg").toString();
				if (!"1".equals(msg)){
					return new ResultMsg(ReturnMsg.FAIL, "修改IM信息失败!");
				}
			}
			
			
			return new ResultMsg(ReturnMsg.SUCCESS, "更新医院信息成功!");
		} catch (Exception e) {
			logger.error("++++++++++++++HospitalServiceImpl.updateHospitalInfo.error", e);
			return new ResultMsg(ReturnMsg.FAIL, "更新医院信息异常!");
		}
	}
	@Override
	public ResultMsg deleteHospitalInfo(Integer id) {
		try {
			if (doctorInfoMapper.selectByHospitalId(id) > 0) {
				return new ResultMsg(ReturnMsg.FAIL, "删除失败，该医院下存在归属医生，不允许删除！");
			}
			if (hospitalRelatedMapper.selectByHospId(id) != null) {
				return new ResultMsg(ReturnMsg.FAIL, "删除失败，该数据与医院关系表中数据有关联，不允许删除！");
			}
			hospitalInfoMapper.deleteByPrimaryKey(id);
			int msg = listMapper.deleteByHospitalId(id);
			if (msg == 0) {
				return new ResultMsg(ReturnMsg.SUCCESS, "删除服务信息成功");
			}
			return new ResultMsg(ReturnMsg.SUCCESS, "删除成功");
		} catch (Exception e) {
			logger.error("++++++++++++++HospitalServiceImpl.deleteHospitalInfo.error", e);
			return new ResultMsg(ReturnMsg.FAIL, "删除医院信息异常!");
		}
	}
	@Override
	public HospitalInfo selectByPrimaryKey(Integer id) {
		return hospitalInfoMapper.selectByPrimaryKey(id);
	}
	@Override
	public HospitalInfoManageVo selectHospitalInfoManageVo(Integer hospitalId) {
		HospitalInfo hospitalInfo = hospitalInfoMapper.selectByPrimaryKey(hospitalId);
		if (hospitalInfo == null) {
			return null;
		}
		Integer[] majors = hospitalInfoMapper.selectMajorByHospitalId(hospitalId);
		HospitalInfoManageVo vo = new HospitalInfoManageVo();
		vo.setId(hospitalId);
		vo.setName(hospitalInfo.getName());
		vo.setUploadfile(ConfigProUtils.get("file_path") + hospitalInfo.getImgUrl());
		vo.setIntroduction(hospitalInfo.getIntroduction());
		vo.setAddress(hospitalInfo.getAddress());
		vo.setProvince(hospitalInfo.getProvinceId());
		vo.setCity(hospitalInfo.getCityId());
		vo.setMajorId(majors);
		return vo;
	}
	
	@Override
	public ResultMsg isPayment(Integer hospitalId, Integer status, CrmAdmin crmAdmin) {
		try {
			HospitalInfo hospitalInfo = hospitalInfoMapper.selectByPrimaryKey(hospitalId);
			if (hospitalInfo == null) {
				return new ResultMsg(ReturnMsg.FAIL, "不存在该医院！");
			}
			byte isPay;
			if (status == 1) {
				isPay = 1;
			}else {
				isPay = 0;
			}
			hospitalInfo.setIsPayment(isPay);
			hospitalInfoMapper.updateByPrimaryKeySelective(hospitalInfo);
			OperationRecords record = new OperationRecords();
			record.setAddTime(new Date());
			record.setHospitalId(hospitalId);
			record.setPayment(isPay);
			record.setUserName(crmAdmin.getName());
			int msg = operationRecordsMapper.insert(record);
			if (msg != 1) {
				return new ResultMsg(ReturnMsg.FAIL, "操作失败！");
			}
			return new ResultMsg(ReturnMsg.SUCCESS, "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(ReturnMsg.FAIL, "操作异常！");
		}
	}
	@Override
	public List<HospitalInfoVo> getHosptitalInfoList2(int beginIndex, int everyPage, int statusType,
			Integer serviceType, Integer provinceId, Integer cityId, String keywords) {
		int begin = beginIndex*everyPage;
		Integer moduleNum = 0;
		if (serviceType == 7) {//高危
			moduleNum = 2;
		}
		if (serviceType == 8) {//母子手册
			moduleNum = 5;
		}
		int count = 0;
		List<HospitalInfoVo> vos = new ArrayList<>();
		List<HospitalModuleHost> moduleHosts = hospitalModuleHostMapper.selectByModuleNum(moduleNum);
		List<HospitalInfoVo> hosptitalInfoList = getHosptitalInfoList(0, 10000, statusType, serviceType, provinceId, cityId, keywords);
		for (int i = begin; i < ((moduleHosts.size()>(begin+everyPage))?(begin+everyPage):moduleHosts.size()-begin); i++) {
			for (HospitalInfoVo hospitalInfoVo : hosptitalInfoList) {
				int hospitalId= moduleHosts.get(i).getHospitalId();
				int id = hospitalInfoVo.getId();
				if (hospitalId == id) {
					vos.add(hospitalInfoVo);
					count ++;
					if (count == everyPage) {
//						break;
						return vos;
					}
				}
			}
		}
		return vos;
	}
	@Override
	public int findNumber(Integer status) {
		return hospitalInfoMapper.findNumber(status);
	}
	@Override
	public int findNumber1() {
		return hospitalInfoMapper.findNumber1();
	}
	

}
