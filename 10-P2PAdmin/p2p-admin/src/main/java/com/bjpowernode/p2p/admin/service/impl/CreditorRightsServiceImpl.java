package com.bjpowernode.p2p.admin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.p2p.admin.config.SystemConfig;
import com.bjpowernode.p2p.admin.constants.Constants;
import com.bjpowernode.p2p.admin.mapper.CreditorRightsMapper;
import com.bjpowernode.p2p.admin.model.CreditorRights;
import com.bjpowernode.p2p.admin.service.CreditorRightsService;
import com.bjpowernode.p2p.admin.util.HttpClientUtils;
import com.bjpowernode.p2p.admin.util.RSAUtils;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 债权相关处理Service接口实现
 * 
 * @author yanglijun
 *
 */
@Service("creditorRightsService")
public class CreditorRightsServiceImpl implements CreditorRightsService {
	
	private static final Logger logger = LogManager.getLogger(CreditorRightsServiceImpl.class);
	
	@Autowired
	private CreditorRightsMapper creditorRightsMapper;

	/**
	 * 分页查询债权信息--获取债权列表
	 * 
	 */
	@Override
	public List<CreditorRights> getCreditorRightsByPage(Map<String, Object> paramMap) {
		return creditorRightsMapper.getCreditorRightsByPage(paramMap);
	}

	/**
	 * 分页查询债权信息--获取债权总数
	 * 
	 */
	@Override
	public int getCreditorRightsByTotal(Map<String, Object> paramMap) {
		return creditorRightsMapper.getCreditorRightsByTotal(paramMap);
	}
	
	/**
	 * 添加债权
	 * 
	 * @param creditorRights
	 * @return
	 */
	@Override
	public int addCreditorRights (CreditorRights creditorRights) {
		return creditorRightsMapper.insertSelective(creditorRights);
	}
	
	/**
	 * 根据主键id查询债权信息
	 * 
	 * @param id
	 * @return
	 */
	public CreditorRights getCreditorRights(int id) {
		return creditorRightsMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 根据主键id删除债权信息
	 * 
	 * @param id
	 * @return
	 */
	public int deleteCreditorRights(int id) {
		return creditorRightsMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据债权状态查询债权信息
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<CreditorRights> getCreditorRightsByMatchStatus(Map<String, Object> paramMap) {
		return creditorRightsMapper.getCreditorRightsByMatchStatus(paramMap);
	}
	
	/**
	 * 接收第三方债权
	 * 
	 */
	public void receiveCreditor () {
		//生成的签名, sortStr 是排序后的请求的参数串，Constants.CLIENT_PRIVATE_KEY 客户端的私钥
		String clientSign = RSAUtils.sign(Constants.API_KEY, Constants.CLIENT_PRIVATE_KEY);
		String url = SystemConfig.getConfigProperty("creditor_api_url");
		
		//封装参数
		Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
		paramMap.put("apiKey", Constants.API_KEY);
		paramMap.put("sign", clientSign);
		
		//调用远程支付接口，获得返回数据
		String json = HttpClientUtils.doPost(url, paramMap);
		System.out.println(json);
		
		JSONObject jsonObject = JSONObject.parseObject(json);
		
		String errorCode = jsonObject.getString("errorCode");
		String errorMessage = jsonObject.getString("errorMessage");
		JSONArray jsonObj = jsonObject.getJSONArray("object");
		
		if (StringUtils.equals(errorCode, "0")) {
			//成功的情况，把接收到的数据插入到数据
			ObjectMapper objectMapper = new ObjectMapper();
			
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(LinkedList.class, CreditorRights.class);   
			try {
				//CreditorRights creditorRights = objectMapper.readValue(jsonObj.toJSONString(), CreditorRights.class);
				
				List<CreditorRights> list =  (List<CreditorRights>)objectMapper.readValue(jsonObj.toJSONString(), javaType); 
				
				for (CreditorRights creditorRights : list) {
					//保存到数据库
					int insertRow = creditorRightsMapper.insertSelective(creditorRights);
					logger.info("接收第三方债权插入数据库结果，insertRow=" + insertRow);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
