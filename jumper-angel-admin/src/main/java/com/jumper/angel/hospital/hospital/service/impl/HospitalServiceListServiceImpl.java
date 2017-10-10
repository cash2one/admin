package com.jumper.angel.hospital.hospital.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.hospital.hospital.entity.HospitalServiceList;
import com.jumper.angel.hospital.hospital.entity.HospitalServiceModule;
import com.jumper.angel.hospital.hospital.mapper.HospitalServiceListMapper;
import com.jumper.angel.hospital.hospital.mapper.HospitalServiceModuleMapper;
import com.jumper.angel.hospital.hospital.service.IHospitalServiceListService;
import com.jumper.angel.utils.ConfigProUtils;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;
import com.jumper.angel.utils.StringUtil;
@Service
public class HospitalServiceListServiceImpl implements IHospitalServiceListService {
	@Autowired
	private HospitalServiceListMapper hospitalServiceListMapper;
	@Autowired
	private HospitalServiceModuleMapper hospitalServiceModuleMapper;

	@Override
	public List<HospitalServiceList> queryBasicFunction(int pageIndex, int everyPage,Integer hospitalId) {
		List<HospitalServiceList> returnList = hospitalServiceListMapper.queryBasicFunction(pageIndex,everyPage,hospitalId);
		for (HospitalServiceList hospitalServiceList : returnList) {
			if (!StringUtil.isEmpty(hospitalServiceList.getImgUrl())) {
				hospitalServiceList.setImgUrl(ConfigProUtils.get("ADMIN_URL") + hospitalServiceList.getImgUrl());
			}
			if (!StringUtil.isEmpty(hospitalServiceList.getIconImg())) {
				hospitalServiceList.setIconImg(ConfigProUtils.get("ADMIN_URL") + hospitalServiceList.getIconImg());
			}
		}
		return returnList;
	}

	@Override
	public List<HospitalServiceList> queryPlatformFunction(int pageIndex, int everyPage,Integer hospitalId) {
		List<HospitalServiceList> returnList = hospitalServiceListMapper.queryPlatformFunction(pageIndex,everyPage,hospitalId);
		for (HospitalServiceList hospitalServiceList : returnList) {
			if (!StringUtil.isEmpty(hospitalServiceList.getImgUrl())) {
				hospitalServiceList.setImgUrl(ConfigProUtils.get("ADMIN_URL") + hospitalServiceList.getImgUrl());
			}
			if (!StringUtil.isEmpty(hospitalServiceList.getIconImg())) {
				hospitalServiceList.setIconImg(ConfigProUtils.get("ADMIN_URL") + hospitalServiceList.getIconImg());
			}
		}
		return returnList;
	}

	@Override
	public List<HospitalServiceList> queryHospitalFunction(int pageIndex, int everyPage,Integer hospitalId) {
		List<HospitalServiceList> returnList = hospitalServiceListMapper.queryHospitalFunction(pageIndex,everyPage,hospitalId);
		for (HospitalServiceList hospitalServiceList : returnList) {
			if (!StringUtil.isEmpty(hospitalServiceList.getImgUrl())) {
				hospitalServiceList.setImgUrl(ConfigProUtils.get("ADMIN_URL") + hospitalServiceList.getImgUrl());
			}
			if (!StringUtil.isEmpty(hospitalServiceList.getIconImg())) {
				hospitalServiceList.setIconImg(ConfigProUtils.get("ADMIN_URL") + hospitalServiceList.getIconImg());
			}
		}
		return returnList;
	}

	@Override
	public List<HospitalServiceList> getRankList(Integer hospitalId) {
		return hospitalServiceListMapper.getRankList(hospitalId);
	}

	@Override
	public List<Map<String, Object>> getHospitalInformation(Integer hospitalId) {
		return hospitalServiceListMapper.getHospitalInformation(hospitalId);
	}

	@Override
	public int updateHospitalServiceEntryStat(Integer hospitalId, Integer moduleId, Integer entryStat) {
		
		Integer MaxPostionOrder = hospitalServiceListMapper.getMaxPostionOrder(hospitalId);
		Integer postionOrder = MaxPostionOrder + 1;
		if (entryStat == 1) {
			postionOrder = 99;
		}
		return hospitalServiceListMapper.updateHospitalServiceEntryStat(hospitalId,moduleId,entryStat,postionOrder);
	}

	@Override
	public HospitalServiceList getHospitalServiceListByModuleId(Integer hospitalId,Integer moduleId) {
		return hospitalServiceListMapper.getHospitalServiceListByModuleId(hospitalId,moduleId);
	}

	@Override
	public int updateHospitalServiceUrlStat(Integer hospitalId, Integer moduleId, String url, Integer urlStat) {
		return hospitalServiceListMapper.updateHospitalServiceUrlStat(hospitalId,moduleId,url,urlStat);
	}

	@Override
	public int updateHospitalServicePostionOrder(List<Map<String, Object>> listMap) {
		int msg = 0;
		for (Map<String, Object> map : listMap) {
			Integer hospitalId = (Integer)map.get("hospitalId");
			Integer moduleId = (Integer)map.get("moduleId");
			Integer postionOrder = (Integer)map.get("postionOrder");
			HospitalServiceList hospitalServiceList = hospitalServiceListMapper.getHospitalServiceListByModuleId(hospitalId,moduleId);
			if (hospitalServiceList == null) {
				return 0;
			}
			msg = hospitalServiceListMapper.updateHospitalServiceEntryStat(hospitalId,moduleId,hospitalServiceList.getEntryStat(),postionOrder);
		}
		return msg;
	}

	@Override
	public ResultMsg addOrUpdateHospitalService(Integer hospitalId, Integer moduleId, Integer functionGroup, String title,
			String url) {
		int msg = 0;
		HospitalServiceList hospitalServiceList = hospitalServiceListMapper.getHospitalServiceListByModuleId(hospitalId,moduleId);
		if (hospitalServiceList != null) {
			HospitalServiceModule module = getByTitle(title);
			if (module != null) {
				HospitalServiceList serviceList = hospitalServiceListMapper.selectByKey(module.getId(),hospitalId);
				if (serviceList != null) {//该医院存在该服务
					HospitalServiceModule serviceModule = hospitalServiceModuleMapper.selectByPrimaryKey(serviceList.getModuleId());
					if (title.equals(serviceModule.getTitle())) {
						if (moduleId != serviceList.getModuleId()) {
							return new ResultMsg(Status.FAILED, "服务名称已经存在！");
						}
					}
				}else {//该医院不存在该服务
					hospitalServiceList.setModuleId(module.getId());
				}
			}else {
				HospitalServiceModule serviceModule = hospitalServiceModuleMapper.selectByPrimaryKey(moduleId);
				serviceModule.setTitle(title);
				int ms = hospitalServiceModuleMapper.updateByPrimaryKeySelective(serviceModule);
				if (ms != 1) {
					return new ResultMsg(Status.FAILED, "服务名称保存失败！");
				}
			}
			hospitalServiceList.setUrl(url);
			msg = hospitalServiceListMapper.updateByPrimaryKeySelective(hospitalServiceList);
		}else {
			HospitalServiceModule module = getByTitle(title);
			if (module != null && hospitalServiceListMapper.selectByKey(module.getId(),hospitalId)!=null) {
//			if (getByTitle(title) != null) {
				return new ResultMsg(Status.FAILED, "模块名称已经存在");
			}
			if (module == null) {
				HospitalServiceModule serviceModule = new HospitalServiceModule();
				serviceModule.setTitle(title);
				serviceModule.setCreatedDate(new Date().getTime());
				serviceModule.setComment("");
				serviceModule.setDisabled(0);
				serviceModule.setImgUrl("/sfile/image/home_features_icon32.png");
				int insert = hospitalServiceModuleMapper.insertSelective(serviceModule);
				if (insert != 1) {
					return new ResultMsg(Status.FAILED, "服务功能添加失败！");
				}
			}
			HospitalServiceModule serviceModules = hospitalServiceModuleMapper.selectByTitle(title);
			Integer MaxPostionOrder = hospitalServiceListMapper.getMaxPostionOrder(hospitalId);
			HospitalServiceList serviceList = new HospitalServiceList();
			serviceList.setCreatedDate(new Date().getTime());
			serviceList.setHospitalId(hospitalId);
			serviceList.setModuleId(serviceModules.getId());
			serviceList.setComment("");
			serviceList.setFunctionGroup(functionGroup);
			serviceList.setClosed(0);
			serviceList.setPostionOrder(MaxPostionOrder+1);
			serviceList.setEntryStat(0);
			serviceList.setDefaultUrl("");
			serviceList.setUrl(url);
			serviceList.setNativeApp(0);
			serviceList.setIconImg("");
			serviceList.setUrlStat(1);
			msg = hospitalServiceListMapper.insert(serviceList);
		}
		if(msg == 1){
			return new ResultMsg(Status.SUCCESS, "添加或修改自定义功能成功！");
		}
		return new ResultMsg(Status.FAILED, "添加或修改自定义功能成功！");
	}

	@Override
	public HospitalServiceModule getByTitle(String title) {
		return hospitalServiceModuleMapper.selectByTitle(title);
	}

	@Override
	public int findCount(Integer hospitalId, Integer functionGroup) {
		return hospitalServiceListMapper.findCount(hospitalId,functionGroup);
	}

	@Override
	public int findCount2(Integer hospitalId) {
		return hospitalServiceListMapper.findCount2(hospitalId);
	}
	

}
