package com.bjpowernode.p2p.admin.mapper;

import com.bjpowernode.p2p.admin.model.DictionaryInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据字典处理Mapper
 *
 * @author yanglijun
 *
 */
@Mapper
public interface DictionaryInfoMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(DictionaryInfo record);

    int insertSelective(DictionaryInfo record);

    DictionaryInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DictionaryInfo record);

    int updateByPrimaryKey(DictionaryInfo record);
    
    /**
     * 查询所有的字典表数据
     * 
     * @return
     */
    List<DictionaryInfo> getAllDictionaryInfo();
    
    List<DictionaryInfo> selectDictionaryInfoByType(int type);
}