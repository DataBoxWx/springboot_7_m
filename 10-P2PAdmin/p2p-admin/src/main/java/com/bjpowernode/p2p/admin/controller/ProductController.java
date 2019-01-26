package com.bjpowernode.p2p.admin.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjpowernode.p2p.admin.constants.Constants;
import com.bjpowernode.p2p.admin.model.CreditorRights;
import com.bjpowernode.p2p.admin.model.DictionaryInfo;
import com.bjpowernode.p2p.admin.model.LoanInfo;
import com.bjpowernode.p2p.admin.rto.ResponseObject;
import com.bjpowernode.p2p.admin.service.CreditorRightsService;
import com.bjpowernode.p2p.admin.service.DictionaryInfoService;
import com.bjpowernode.p2p.admin.service.LoanInfoService;

/**
 * 产品相关管理Controller
 * 
 * @author yanglijun
 *
 */
@Controller
public class ProductController {
	
	@Autowired
	private LoanInfoService loanInfoService;
	
	@Autowired
	private CreditorRightsService creditorRightsService;
	
	@Autowired
	private DictionaryInfoService dictionaryInfoService;

	/**
	 * 查询产品信息列表
	 * 
	 * @param model
	 * @param productType
	 * @return
	 */
	@RequestMapping(value="/admin/product")
	public String product (Model model, 
			@RequestParam(value="currentPage", required=false) Integer currentPage,
			@RequestParam(value="productType", required=true) Integer productType,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		
		if (null == currentPage) {
			currentPage = 1;//当前页从1开始
		}
		if (null == pageSize) {
			pageSize = Constants.DEFAULT_PAGESIZE;//默认每页显示10条数据
		}
		
		int startRow = (currentPage-1) * pageSize;
		Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
		paramMap.put("startRow", startRow);
		paramMap.put("pageSize", pageSize);
		if (null != productType) {
			paramMap.put("productType", productType);
		}
		//分页查询产品列表数据
		List<LoanInfo> loanInfoList = loanInfoService.getLoanInfoByPage(paramMap);
		
		//符合查询条件的产品数据总条数
		int totalRows = loanInfoService.getLoanInfoByTotal(paramMap);
		
		//计算有多少页
		int totalPage = totalRows / pageSize;
		int mod = totalRows % pageSize;
		if (mod > 0) {
			totalPage = totalPage + 1;
		}
		
		model.addAttribute("productType", productType);//产品类型
		model.addAttribute("loanInfoList", loanInfoList);//产品列表
		model.addAttribute("totalPage", totalPage);//总页数
		model.addAttribute("startRow", startRow);//开始行
		model.addAttribute("currentPage", currentPage);//当前页
		model.addAttribute("totalRows", totalRows);//总数据条数
		model.addAttribute("pageSize", pageSize);//每页显示多少条数据
		
		//跳转到产品列表页
		return "product";
	}
	
	/**
	 * 进入产品发布页面
	 * 
	 * @param model
	 * @param productType
	 * @return
	 */
	@RequestMapping(value="/admin/toAddProduct")
	public String toAaddProduct (Model model, 
			@RequestParam(value="productType") Integer productType,
			@RequestParam(value="creditorRightsId", required=false) Integer creditorRightsId) {
		
		//获取产品类型
		List<DictionaryInfo> dictionaryInfoList = dictionaryInfoService.getDictionaryInfoByType(12);
		
		model.addAttribute("dictionaryInfoList", dictionaryInfoList);
		
		if (productType == 2) {//散标产品
			//根据债权id查询债权信息
			CreditorRights creditorRights = creditorRightsService.getCreditorRights(creditorRightsId);
			model.addAttribute("creditorRights", creditorRights);
		}
		model.addAttribute("productType", productType);
		model.addAttribute("creditorRightsId", creditorRightsId);
		
		return "addProduct";
	}
	
	/**
	 * 添加产品
	 * 
	 * @param model
	 * @param productType
	 * @return
	 */
	@RequestMapping(value="/admin/addProduct")
	public @ResponseBody ResponseObject addProduct (Model model, 
			@RequestParam(value="productName") String productName,//产品名称
			@RequestParam(value="productNo") String productNo,//产品编号
			@RequestParam(value="rate") Double rate,//产品利率
			@RequestParam(value="cycle") Integer cycle,//产品期限
			@RequestParam(value="productType") Integer productType,//产品类型
			@RequestParam(value="productMoney") Double productMoney,//产品金额
			@RequestParam(value="bidMinLimit") Double bidMinLimit,//产品起投金额
			@RequestParam(value="bidMaxLimit") Double bidMaxLimit,//产品单笔投资限额
			@RequestParam(value="productDesc") String productDesc//产品描述
			) {
		
		ResponseObject responseObject = new ResponseObject();
		
		//验证数据的合法性
		LoanInfo loanInfo = new LoanInfo();
		loanInfo.setProductName(productName);
		loanInfo.setProductNo(productNo);
		loanInfo.setRate(rate);
		loanInfo.setCycle(cycle);
		loanInfo.setProductType(productType);
		loanInfo.setProductMoney(productMoney);
		loanInfo.setBidMinLimit(bidMinLimit);
		loanInfo.setBidMaxLimit(bidMaxLimit);
		loanInfo.setProductDesc(productDesc);
		
		loanInfo.setReleaseTime(new Date());
		loanInfo.setLeftProductMoney(productMoney);
		loanInfo.setProductStatus(-1);//-1表示未发布状态
		loanInfo.setVersion(0);//初始版本为0
		
		loanInfoService.addProduct(loanInfo);
		
		responseObject.setErrorCode(Constants.ZERO);
		responseObject.setErrorMessage("添加产品成功");
		return responseObject;
	}
	
	/**
	 * 进入产品修改页面
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/admin/toEditProduct")
	public String toEditProduct (Model model, @RequestParam(value="id") Integer id) {
		
		//根据ID查询产品信息
		LoanInfo loanInfo = loanInfoService.getLoanInfoById(id);
		model.addAttribute("loanInfo", loanInfo);
		//产品类型用于展示左侧导航栏
		model.addAttribute("productType", loanInfo.getProductType());
		
		//获取产品类型
		List<DictionaryInfo> dictionaryInfoList = dictionaryInfoService.getDictionaryInfoByType(12);
		model.addAttribute("dictionaryInfoList", dictionaryInfoList);
		
		return "addProduct";
	}
	
	/**
	 * 根据产品ID删除产品
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/admin/toDeleteProduct")
	public String toDeleteProduct (Model model, 
			@RequestParam(value="id") Integer id,
			@RequestParam(value="productType") Integer productType) {
		
		//根据ID删除产品信息
		loanInfoService.deleteProductById(id);
		
		return "redirect:/admin/product?productType="+productType;
	}
}