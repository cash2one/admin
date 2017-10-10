package com.jumper.angel.hospital.hospital.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.angel.hospital.hospital.entity.HospitalDoctorMajor;
import com.jumper.angel.hospital.hospital.mapper.HospitalDoctorMajorMapper;
import com.jumper.angel.hospital.hospital.service.IHospitalDoctorMajorService;
import com.jumper.angel.utils.ConfigProUtils;
import com.jumper.angel.utils.ResultMsg;
import com.jumper.angel.utils.Status;
@Service
public class HospitalDoctorMajorServiceImpl implements IHospitalDoctorMajorService {

	@Autowired
	private HospitalDoctorMajorMapper doctorMajorMapper;
	
	
	
	@Override
	public int findCount(String keywords) {
		return doctorMajorMapper.findCount(keywords);
	}

	@Override
	public List<HospitalDoctorMajor> getHospitalDoctorMajor(int beginIndex, int everyPage, String keywords) {
		List<HospitalDoctorMajor> hospitalDoctorMajor = doctorMajorMapper.getHospitalDoctorMajor(beginIndex,everyPage,keywords);
		for (HospitalDoctorMajor major : hospitalDoctorMajor) {
			major.setFilePath(ConfigProUtils.get("file_path"));
		}
		return hospitalDoctorMajor;
	}

	@Override
	public ResultMsg addOrUpdateMajor(String major, int majorId, String imageUrl) {
		int msg = 0;
		if (majorId > 0) {//修改
			HospitalDoctorMajor doctorMajor = doctorMajorMapper.selectByPrimaryKey(majorId);
			if (!doctorMajor.getMajor().equals(major)) {
				HospitalDoctorMajor doctorMajors = doctorMajorMapper.getByMajor(major);
				if (doctorMajors != null) {
					return new ResultMsg(Status.FAILED,"科室名称已经存在");
				}
			}
			doctorMajor.setMajor(major);
			doctorMajor.setImageUrl(imageUrl);
			msg = doctorMajorMapper.updateByPrimaryKeySelective(doctorMajor);
		}else {//添加
			HospitalDoctorMajor doctorMajors = doctorMajorMapper.getByMajor(major);
			if (doctorMajors != null) {
				return new ResultMsg(Status.FAILED,"科室名称已经存在");
			}
			HospitalDoctorMajor doctorMajor = new HospitalDoctorMajor();
			doctorMajor.setMajor(major);
			doctorMajor.setImageUrl(imageUrl);
			doctorMajor.setAddTime(new Date());
			msg = doctorMajorMapper.insertSelective(doctorMajor);
		}
		if(msg == 1) {
			return new ResultMsg(Status.SUCCESS, "操作成功！");
		} else {
			return new ResultMsg(Status.FAILED, "添加或修改科室失败！");
		}
	}

	@Override
	public int deleteMajor(int majorId) {
		return doctorMajorMapper.deleteByPrimaryKey(majorId);
	}

	@Override
	public HospitalDoctorMajor getByMajor(String major) {
		return doctorMajorMapper.getByMajor(major);
	}

}
