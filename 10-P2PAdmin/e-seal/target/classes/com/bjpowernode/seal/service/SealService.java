package com.bjpowernode.seal.service;

import com.bjpowernode.seal.model.CreditorVO;

/**
 * 合同生成及签章的Service接口
 *
 * @author yanglijun
 */
public interface SealService {

    public String pdfSeal (CreditorVO creditorVO);

}