package com.bjpowernode.fastdfs.service;

import com.bjpowernode.fastdfs.model.CreditorInfo;

import java.util.List;

/**
 * ClassName:CreditorInfoService
 * package:com.bjpowernode.fastdfs.service
 * Descrption:
 *
 * @Date:2018/7/30 17:31
 * @Author:724班
 */
public interface CreditorInfoService {

    public List<CreditorInfo> getAllCreditorInfo();

    public CreditorInfo getCreditorInfoById(Integer id);

    public int updateCreditorInfo(CreditorInfo creditorInfo);

    public int deleteCreditorInfoContract(Integer id);
}
