package com.jumper.angel.hospital.doctor.mapper;

import org.apache.ibatis.annotations.Param;

import com.jumper.angel.hospital.doctor.entity.UserExtraInfoBean;
/**用户额外信息相关的Mapper*/
public interface UserExtraInfoBeanMapper {
    int deleteByPrimaryKey(@Param("id")Integer id);

    int insert(@Param("record")UserExtraInfoBean record);

    int insertSelective(@Param("record")UserExtraInfoBean record);

    UserExtraInfoBean selectByPrimaryKey(@Param("id")Integer id);

    int updateByPrimaryKeySelective(@Param("record")UserExtraInfoBean record);

    int updateByPrimaryKey(@Param("record")UserExtraInfoBean record);
}