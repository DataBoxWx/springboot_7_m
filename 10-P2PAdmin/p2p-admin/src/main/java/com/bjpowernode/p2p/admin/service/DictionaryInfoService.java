package com.bjpowernode.p2p.admin.service;

import com.bjpowernode.p2p.admin.model.DictionaryInfo;

import java.util.List;

/**
 * 数据字典service接口
 *
 * @author yanglijun
 *
 */
public interface DictionaryInfoService {

	/**
	 * 查询所有的字典表数据
	 * 
	 * @return
	 */
	public List<DictionaryInfo> getAllDictionaryInfo ();
	
	public List<DictionaryInfo> getDictionaryInfoByType (int type);
}
