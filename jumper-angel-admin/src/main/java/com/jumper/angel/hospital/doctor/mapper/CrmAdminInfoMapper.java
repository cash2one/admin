package com.jumper.angel.hospital.doctor.mapper;

import com.jumper.angel.hospital.doctor.entity.CrmAdminInfo;
/**运营人员相关Mapper*/
public interface CrmAdminInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CrmAdminInfo record);

    int insertSelective(CrmAdminInfo record);

    CrmAdminInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrmAdminInfo record);

    int updateByPrimaryKey(CrmAdminInfo record);
}