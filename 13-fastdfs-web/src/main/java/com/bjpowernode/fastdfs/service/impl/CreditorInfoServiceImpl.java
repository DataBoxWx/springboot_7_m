package com.bjpowernode.fastdfs.service.impl;

import com.bjpowernode.fastdfs.fastdfs.FastDFS;
import com.bjpowernode.fastdfs.mapper.CreditorInfoMapper;
import com.bjpowernode.fastdfs.model.CreditorInfo;
import com.bjpowernode.fastdfs.service.CreditorInfoService;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * ClassName:CreditorInfoServiceImpl
 * package:com.bjpowernode.fastdfs.service.impl
 * Descrption:
 *
 * @Date:2018/7/30 17:31
 * @Author:724班
 */
@Service
public class CreditorInfoServiceImpl implements CreditorInfoService {

    @Autowired
    private CreditorInfoMapper creditorInfoMapper;

    @Override
    public List<CreditorInfo> getAllCreditorInfo() {
        return creditorInfoMapper.selectAllCreditorInfo();
    }

    @Override
    public CreditorInfo getCreditorInfoById(Integer id) {
        return creditorInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateCreditorInfo(CreditorInfo creditorInfo) {
        return creditorInfoMapper.updateByPrimaryKeySelective(creditorInfo);
    }

    @Transactional
    @Override
    public int deleteCreditorInfoContract(Integer id) {

        //1表示失败，0表示成功
        int result = 1;

        CreditorInfo creditorInfo = creditorInfoMapper.selectByPrimaryKey(id);

        //1、把数据库的合同路径信息清除掉
        int update = creditorInfoMapper.updateByContract(id);

        if (update > 0 ) {

            try {
                //2、把fastdfs文件系统上的文件删除掉
                int delete = FastDFS.getStorageClient().delete_file(creditorInfo.getGroupname(), creditorInfo.getRemotefilename());

                if (delete != 0) {
                    throw new RuntimeException("合同删除失败");
                } else {
                    result = 0;
                }
            } catch (MyException e) {
                e.printStackTrace();
                throw new RuntimeException("合同删除失败");

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("合同删除失败");
            }
        }
        return result;
    }
}