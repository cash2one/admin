package com.jumper.angel.hospital.hospital.service;

import java.util.List;

import com.jumper.angel.hospital.hospital.entity.HospitalDoctorMajor;
import com.jumper.angel.utils.ResultMsg;

public interface IHospitalDoctorMajorService {

	/**查询总页数**/
	int findCount(String keywords);

	/**查询科室对象**/
	List<HospitalDoctorMajor> getHospitalDoctorMajor(int beginIndex, int everyPage, String keywords);
	/**添加或修改科室*/
	ResultMsg addOrUpdateMajor(String major, int majorId, String imageUrl);

	/**删除科室*/
	int deleteMajor(int majorId);

	/**通过名称查询**/
	HospitalDoctorMajor getByMajor(String major);

}
