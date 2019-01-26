package com.bjpowernode.fastdfs.controller;

import com.bjpowernode.fastdfs.fastdfs.FastDFS;
import com.bjpowernode.fastdfs.model.CreditorInfo;
import com.bjpowernode.fastdfs.service.CreditorInfoService;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * ClassName:CreditorInfoController
 * package:com.bjpowernode.fastdfs.controller
 * Descrption:
 *
 * @Date:2018/7/30 17:35
 * @Author:724班
 */
@Controller
public class CreditorInfoController {

    @Autowired
    private CreditorInfoService creditorInfoService;

    @Value("${contract_url_prefix}")
    private String contractURLPrefix;

    /**
     * 债权信息列表
     *
     * @param model
     * @return
     */
    @GetMapping("/fdfs/creditors")
    public String creditors(Model model) {

        List<CreditorInfo> creditorInfoList = creditorInfoService.getAllCreditorInfo();

        model.addAttribute("creditorInfoList", creditorInfoList);

        return "creditors";
    }

    /**
     * 去上传债权合同文件
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/fdfs/creditor/{id}")
    public String toUpload(Model model, @PathVariable("id") Integer id) {

        CreditorInfo creditorInfo = creditorInfoService.getCreditorInfoById(id);

        model.addAttribute("creditorInfo", creditorInfo);

        return "creditor";
    }

    /**
     * 上传合同文件
     *
     * @param id
     * @param file
     * @return
     */
    @PostMapping("/fdfs/creditor")
    public @ResponseBody String uploadFile(@RequestParam("id") Integer id, @RequestParam("fileContract") MultipartFile file) {

        //1表示失败，0表示成功
        int result = 1;

        //原来我们上传文件，是将文件上传到一个具体的指定的目录下 /upload

        //现在我们上传文件，是将文件上传到FastDFS分布式文件系统上 （storage）
        try {

            //文件的字节数组
            byte[] fileBytes = file.getBytes();

            //文件扩展名
            // abc.pdf --> pdf
            String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

            //我们封装一个上传的方法
            String[] strArray = FastDFS.getStorageClient().upload_file(fileBytes, fileExt, null);

            //更新债权表的合同路径信息
            if (null != strArray && strArray.length == 2) {
                //文件上传成功
                CreditorInfo creditorInfo = new CreditorInfo();
                creditorInfo.setId(id);
                creditorInfo.setGroupname(strArray[0]);
                creditorInfo.setRemotefilename(strArray[1]);
                // http://192.168.160.128/group1/M00/00/00/XXXX.pdf
                String contractURL = contractURLPrefix + strArray[0] + "/" + strArray[1];
                creditorInfo.setContracturl(contractURL);
                int update = creditorInfoService.updateCreditorInfo(creditorInfo);

                if (update > 0) {
                    //真正成功了，两个步骤都成功，告诉前台结果
                    result = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        } finally {
            FastDFS.closeFastDFS();
        }
        return "<script>window.parent.uploadOK('"+result+"');</script>";
    }

    /**
     * 下载债权合同文件
     *
     * @param id
     * @return
     */
    @GetMapping("/fdfs/creditor/{id}/contract")
    public ResponseEntity<byte[]> downloadFile (@PathVariable("id") Integer id) {

        ResponseEntity<byte[]> responseEntity = null;

        CreditorInfo creditorInfo = creditorInfoService.getCreditorInfoById(id);

        //文件的后缀
        String fileExt = creditorInfo.getRemotefilename().substring(creditorInfo.getRemotefilename().lastIndexOf("."));

        //构造http头部信息对象
        HttpHeaders httpHeader = new HttpHeaders();
        //设置http响应头的内容类型是 流 类型
        httpHeader.setContentType(MediaType.APPLICATION_OCTET_STREAM);//流类型
        //设置http响应头的内容形式是 附件
        httpHeader.setContentDispositionFormData("atattachment", System.currentTimeMillis() + fileExt);

        //访问fastdfs文件系统，进行文件下载，得到下载后的文件字节数组
        try {
            byte[] filesBytes = FastDFS.getStorageClient().download_file(creditorInfo.getGroupname(), creditorInfo.getRemotefilename());

            responseEntity = new ResponseEntity<byte[]>(filesBytes, httpHeader, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        } finally {
            FastDFS.closeFastDFS();
        }
        return responseEntity;
    }

    /**
     * 删除合同
     *
     * @param id
     * @return
     */
    @DeleteMapping("/fdfs/creditor/{id}")
    public String deleteFile(@PathVariable("id") Integer id) {

        int delete = creditorInfoService.deleteCreditorInfoContract(id);

        return "redirect:/fdfs/creditors";
    }
}