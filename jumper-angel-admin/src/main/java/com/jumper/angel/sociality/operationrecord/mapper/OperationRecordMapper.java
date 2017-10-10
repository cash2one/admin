package com.jumper.angel.sociality.operationrecord.mapper;

import java.util.List;
import java.util.Map;
import com.jumper.angel.sociality.operationrecord.model.po.OperationRecordPO;
import com.jumper.angel.sociality.operationrecord.model.vo.OperationRecordVO;

public interface OperationRecordMapper {
	
	int deleteById(Long recordId);

	int insert(OperationRecordPO operationRecordPO);

	List<OperationRecordVO> select(OperationRecordVO operationRecordVO);

	int update(OperationRecordPO operationRecordPO);
	
	Map<String,Object> seleclUserInfoById(Integer id);

	int addEntityBatch(List<OperationRecordPO> list);
}