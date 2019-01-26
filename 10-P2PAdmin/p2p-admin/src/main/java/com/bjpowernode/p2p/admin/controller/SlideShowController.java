package com.bjpowernode.p2p.admin.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bjpowernode.p2p.admin.config.SystemConfig;
import com.bjpowernode.p2p.admin.constants.Constants;
import com.bjpowernode.p2p.admin.fastdfs.FastdfsClient;
import com.bjpowernode.p2p.admin.model.SlideShow;
import com.bjpowernode.p2p.admin.rto.ResponseObject;
import com.bjpowernode.p2p.admin.service.SlideShowService;

/**
 * 轮播图相关处理Controller
 * 
 * @author yanglijun
 *
 */
@Controller
public class SlideShowController {

	private static final Logger logger = LogManager.getLogger(SlideShowController.class);
	
	@Autowired
	private SlideShowService slideShowService;
	
	/**
	 * 查询轮播图信息列表
	 * 
	 * @param model
	 * @param productType
	 * @return
	 */
	@RequestMapping(value="/admin/slideShow")
	public String slideShow (Model model, 
			@RequestParam(value="currentPage", required=false) Integer currentPage,
			@RequestParam(value="productType", required=false) String productType,
			@RequestParam(value="pageSize", required=false) Integer pageSize
			) {
		
		if (null == currentPage) {
			currentPage = 1;//当前页从1开始
		}
		if (null == pageSize) {
			pageSize = Constants.DEFAULT_PAGESIZE;//当前页从1开始
		}
		
		//分页查询数据库的起始行
		int startRow = (currentPage-1) * pageSize;
		Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
		paramMap.put("currentPage", startRow);
		paramMap.put("pageSize", pageSize);
		if (null != productType) {
			paramMap.put("productType", productType);
		}
		//分页查询轮播图列表
		List<SlideShow> slideShowList = slideShowService.getSlideShowByPage(paramMap);
		
		//符合查询条件的轮播图数据总条数
		int totalRows = slideShowService.getSlideShowByTotal(paramMap);
		
		//计算有多少页
		int totalPage = totalRows / pageSize;
		int mod = totalRows % pageSize;
		if (mod > 0) {
			totalPage = totalPage + 1;
		}
		
		model.addAttribute("productType", productType);//产品类型
		model.addAttribute("slideShowList", slideShowList);//产品列表
		model.addAttribute("totalPage", totalPage);//总页数
		model.addAttribute("currentPage", currentPage);//当前页
		model.addAttribute("startRow", startRow);//分页查询数据库的起始行
		model.addAttribute("pageSize", pageSize);//每页显示多少条数据
		model.addAttribute("totalRows", totalRows);//总数据条数
		
		return "slideShow";
	}
	
	/**
	 * 去添加轮播图
	 * 
	 * @return
	 */
	@RequestMapping(value="/admin/toAddSlideShow")
	public String toAddSlideShow () {
		return "addSlideShow";
	}
	
	/**
	 * 上传轮播图
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/admin/uploadSlideShow", method=RequestMethod.POST)
	public @ResponseBody String uploadSlideShow(HttpServletRequest request,
			@RequestParam (value="fileName", required=false) MultipartFile file) {

		try {
	        String originalFilename = file.getOriginalFilename();//上传的原始文件名
			String fileExtendsName = originalFilename.substring(originalFilename.indexOf(".")+1);//扩展名
			
			String allowImgFormat = "gif,jpg,jpeg,png";
			if (!allowImgFormat.contains(fileExtendsName.toLowerCase())) {
				logger.info("图片类型不匹配->." + fileExtendsName);
				String ret = "<script>window.parent.uploadSuccess('error');</script>";
				return ret;
			}
			
	        //使用fastdfs上传文件
			String[] arry = FastdfsClient.updateFileToFastdfs(file.getBytes(), fileExtendsName);
			if (arry.length >= 2) {
				String imageUrl = SystemConfig.getConfigProperty("slideShow_prefix")+ "/" + arry[0]+"/"+arry[1];
				String ret = "<script>window.parent.uploadSuccess('"+imageUrl+"');</script>";
				return ret;
			} else {
				//参考：http://www.cnblogs.com/zcttxs/archive/2013/07/09/3180509.html 的例子改写的
				String ret = "<script>window.parent.uploadSuccess('error');</script>";
				return ret;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 提交保存轮播图数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/admin/submitSlideShow", method=RequestMethod.POST)
	public @ResponseBody ResponseObject submitSlideShow (HttpServletRequest request,
			@RequestParam (value="slideTitle", required=false) String slideTitle,
			@RequestParam (value="slideUrl", required=false) String slideUrl,
			@RequestParam (value="slideStartTime", required=false) String slideStartTime,
			@RequestParam (value="slideEndTime", required=false) String slideEndTime,
			@RequestParam (value="slideStatus", required=false) Integer slideStatus,
			@RequestParam (value="fileURL", required=false) String fileURL) {

		ResponseObject responseObject = new ResponseObject();
		
        //将数据写入数据库
        SlideShow slideShow = new SlideShow();
        slideShow.setSlideTitle(slideTitle);
        slideShow.setSlideUrl(slideUrl);
        slideShow.setSlideCreateTime(new Date());
        slideShow.setSlideUpdateTime(new Date());
        slideShow.setSlideStartTime(new Date());
        slideShow.setSlideEndTime(new Date());
        slideShow.setSlideStatus(slideStatus);
        slideShow.setSlideImageUrl(fileURL);
        
        int addRows = slideShowService.addSlideShow(slideShow);
        if (addRows <= 0) {
        	responseObject.setErrorCode(Constants.ONE);
        	responseObject.setErrorMessage("轮播图数据保存失败了，请重试~");
        } else {
        	responseObject.setErrorCode(Constants.ZERO);
        	responseObject.setErrorMessage("轮播图数据保存成功");
        }
        return responseObject;
	}
	
	/**
	 * 去编辑修改某个轮播图信息
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/admin/toEditSlideShow")
	public String toEditSlideShow (Model model, @RequestParam(value="id") Integer id) {
		
		SlideShow slideShow = slideShowService.getSlideShowById(id);
		model.addAttribute("slideShow", slideShow);
		return "updateSlideShow";
	}
	
	/**
	 * 删除某个轮播图
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/admin/deleteSlideShow")
	public String deleteSlideShow (@RequestParam(value="id") Integer id,
			@RequestParam(value="currentPage") Integer currentPage,
			@RequestParam(value="pageSize") Integer pageSize) {
		
		slideShowService.deleteSlideShowById(id);
		
		return "redirect:/admin/slideShow?currentPage="+currentPage+"&pageSize="+pageSize;
	}
}