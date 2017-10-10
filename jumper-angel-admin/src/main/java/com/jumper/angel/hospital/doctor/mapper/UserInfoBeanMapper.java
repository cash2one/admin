package com.jumper.angel.hospital.doctor.mapper;

import java.util.List;

import com.jumper.angel.hospital.doctor.entity.UserInfoBean;
/**用户信息相关Mapper*/
public interface UserInfoBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfoBean record);

    int insertSelective(UserInfoBean record);

    UserInfoBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfoBean record);

    int updateByPrimaryKey(UserInfoBean record);

    List<UserInfoBean> selUserInfo();
    
    List<UserInfoBean> selUserList();
}