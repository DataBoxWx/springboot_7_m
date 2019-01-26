package com.bjpowernode.p2p.admin.mapper;

import com.bjpowernode.p2p.admin.model.PermissionInfo;

import java.util.List;
import java.util.Map;

/**
 * 权限信息Mapper接口
 * 
 * @author yanglijun
 *
 */
public interface PermissionInfoMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(PermissionInfo record);

    int insertSelective(PermissionInfo record);

    PermissionInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PermissionInfo record);

    int updateByPrimaryKey(PermissionInfo record);
    
    List<PermissionInfo> selectPermissionInfoByUserIdAndType (Map<String, Object> paramMap);
}