package com.bjpowernode.p2p.admin.service.impl;

import com.bjpowernode.p2p.admin.mapper.DictionaryInfoMapper;
import com.bjpowernode.p2p.admin.model.DictionaryInfo;
import com.bjpowernode.p2p.admin.service.DictionaryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据字典处理Service实现
 *
 * @author yanglijun
 *
 */
@Service("dictionaryInfoService")
public class DictionaryInfoServiceImpl implements DictionaryInfoService {

	@Autowired
	private DictionaryInfoMapper dictionaryInfoMapper;
	
	/**
	 * 查询所有的字典表数据
	 * 
	 */
	@Override
	public List<DictionaryInfo> getAllDictionaryInfo() {
		return dictionaryInfoMapper.getAllDictionaryInfo();
	}
	
	@Override
	public List<DictionaryInfo> getDictionaryInfoByType(int type) {
		return dictionaryInfoMapper.selectDictionaryInfoByType(type);
	}
}