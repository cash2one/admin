package com.jumper.angel.hospital.hospital.mapper;

import com.jumper.angel.hospital.hospital.entity.OperationRecords;

public interface OperationRecordsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OperationRecords record);

    int insertSelective(OperationRecords record);

    OperationRecords selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OperationRecords record);

    int updateByPrimaryKey(OperationRecords record);
}