package com.bjpowernode.seal.service.impl;

import com.bjpowernode.seal.config.SystemConfig;
import com.bjpowernode.seal.constants.Constants;
import com.bjpowernode.seal.fastdfs.FastdfsClient;
import com.bjpowernode.seal.model.BidInfoVO;
import com.bjpowernode.seal.model.CreditorVO;
import com.bjpowernode.seal.pdf.PdfProcesser;
import com.bjpowernode.seal.service.SealService;
import com.bjpowernode.seal.utils.MyDateUtils;
import com.bjpowernode.seal.utils.MySealUtils;
import com.bjpowernode.seal.utils.MyStringUtils;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 合同生成及签章的Service接口实现
 *
 * @author yanglijun
 *
 */
@Service("sealServiceImpl")
@WebService
public class SealServiceImpl implements SealService {

    private static final String LOAN_CONTRACT_TEMPLATE = "loan_contract.pdf";

    /**
     * 公布webservice方法，提供合同签章
     *
     * @param creditorVO 债权信息对象
     * @return
     */
    @WebMethod
    public String pdfSeal (CreditorVO creditorVO) {
        try {
            //获取合同模板文件所在路径
            String fileName = SealService.class.getClassLoader().getResource(LOAN_CONTRACT_TEMPLATE).getFile();

            //读取借款合同模板文件并设置数据
            PdfReader pdfReader = new PdfReader(fileName);

            //获取合同生成后存放路径
            String outFile_template = SystemConfig.getConfigProperty("loan_contract_path") + creditorVO.getApplyNo() + "_template.pdf";
            //文件输出流，输出合同文件到指定的路径
            FileOutputStream fos = new FileOutputStream(outFile_template);

            //获取一个向pdf模板写数据的对象
            PdfStamper stamper = new PdfStamper(pdfReader, fos);
            stamper.setFormFlattening(true);

            //将数据一一设置到pdf合同模板中
            stamper.getAcroFields().setField("protocolNumber", creditorVO.getApplyNo());
            stamper.getAcroFields().setField("borrowerRealname", creditorVO.getBorrowerRealname());
            stamper.getAcroFields().setField("borrowerIdcard", creditorVO.getBorrowerIdcard());
            stamper.getAcroFields().setField("borrowerSex", creditorVO.getBorrowerSex());
            stamper.getAcroFields().setField("borrowerPresentAddress", creditorVO.getBorrowerPresentAddress());
            stamper.getAcroFields().setField("applyPurpose", creditorVO.getApplyPurpose());
            stamper.getAcroFields().setField("returnWay", "等额本息");
            stamper.getAcroFields().setField("returnTerm", String.valueOf(creditorVO.getAuditLoanTerm()));
            stamper.getAcroFields().setField("returnDay", "15");

            //募资完成时间
            Date collectFinishTime = creditorVO.getCollectFinishTime();

            //计算还款开始时间
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(collectFinishTime);
            startCalendar.add(Calendar.DAY_OF_MONTH, 1);
            Date startDate = startCalendar.getTime();

            //计算还款结束时间
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(startDate);
            endCalendar.add(Calendar.MONTH, creditorVO.getAuditLoanTerm());//募资完成时间第二天 + 批借月数
            Date endDate = endCalendar.getTime();

            //设置还款开始和结束时间
            stamper.getAcroFields().setField("returnStartAndEndDate", MyDateUtils.getCurrentDateByFormatCN(startDate) + " 至 " + MyDateUtils.getCurrentDateByFormatCN(endDate));

            //借款金额（大写）
            Double borrowAmount = creditorVO.getAuditLoanMoney().doubleValue();
            //人民币数字转换为大写汉字
            String strMoney = MyStringUtils.numToUpper((int)(MyStringUtils.mul(borrowAmount, 100)));
            for (int ii=8; ii>=0; ii--) {
                String text = "jk_0"+ii;
                if (strMoney.length()>=(ii+1)) {
                    String resuleText = ""+strMoney.charAt(strMoney.length()-1-ii);
                    stamper.getAcroFields().setField(text, resuleText);
                } else {
                    stamper.getAcroFields().setField(text, "");
                }
            }

            //提取pdf中的表单输入域字段
            AcroFields form = stamper.getAcroFields();
            //通过域字段名获取所在页和坐标，左下角为起点
            int pageNo = form.getFieldPositions("Chapter").get(0).page;
            Rectangle signRect = form.getFieldPositions("Chapter").get(0).position;
            float x = signRect.getLeft();
            float y = signRect.getBottom();

            //画公章，并得到公章图片的路径
            String imgPath = MySealUtils.drawCircularChapter(Constants.CHAPTER_NAME, Constants.CHAPTER_COMPANY_NAME, Constants.CHAPTER_SOCIAL_NUMBER);
            //读图片
            Image image = Image.getInstance(imgPath);
            //获取操作的页面
            PdfContentByte under = stamper.getOverContent(pageNo);
            //根据域字段的大小缩放图片
            image.scaleToFit(signRect.getWidth(), signRect.getHeight());
            //设置位置
            image.setAbsolutePosition(x, y);
            //添加图片
            under.addImage(image);

            //关闭操作
            stamper.close();
            pdfReader.close();
            fos.flush();
            fos.close();

            //将图章图片删除一下
            File file = new File(imgPath);
            if (file.isFile()) {
                file.delete();
            }
            //模板文件生成结束

            //2、生成出借人列表pdf文件
            String outFile_table = SystemConfig.getConfigProperty("loan_contract_path") + creditorVO.getApplyNo() + "_table.pdf";

            //表格的表头数据
            List<String> tableHeaderList = new ArrayList<String>();
            tableHeaderList.add("出借编号");
            tableHeaderList.add("出借人姓名");
            tableHeaderList.add("出借人身份证");
            tableHeaderList.add("出借人手机号");
            tableHeaderList.add("出借金额");
            tableHeaderList.add("出借期限");

            //3、根据债权ID获取匹配完成且募资完成的债权下对应产品的所有投资记录
            List<BidInfoVO> bidInfoVOList = creditorVO.getBidInfoVOList();

            //表格的具体表格数据
            List<String> tableDataList = new ArrayList<String>();
            for (BidInfoVO bidInfoVO : bidInfoVOList) {
                //投资ID
                Integer bidId = bidInfoVO.getBidId();
                //用户姓名
                String name = bidInfoVO.getName();
                //用户身份证号码
                String idCard = bidInfoVO.getIdCard();
                //用户手机号
                String phone = bidInfoVO.getPhone();
                //出借金额
                BigDecimal bidMoney = bidInfoVO.getBidMoney();
                //出借期限
                Integer lifeOfLoan = creditorVO.getAuditLoanTerm();

                tableDataList.add(String.valueOf(bidId));
                tableDataList.add(name);
                tableDataList.add(idCard);
                tableDataList.add(phone);
                tableDataList.add(String.valueOf(bidMoney) + " 元");
                tableDataList.add(String.valueOf(lifeOfLoan) + " 个月");
            }
            //创建PDF表格
            PdfProcesser.createPdfTable(outFile_table, tableHeaderList, tableDataList);

            //3、模版生成的pdf文件 与 出借人表格pdf文件 合并
            String outFile_final = SystemConfig.getConfigProperty("loan_contract_path") + creditorVO.getApplyNo() + ".pdf";

            //pdf文件合并
            List<String> pdfList = new ArrayList<String>();
            pdfList.add(outFile_template);
            pdfList.add(outFile_table);
            PdfProcesser.mergePDFs(pdfList, outFile_final, true);

            //将pdf合同文件上传到fastdfs文件系统
            String[] strArray = FastdfsClient.updateFileToFastdfs(outFile_final, "pdf");

            //删除 模版生成的pdf文件 和 出借人表格pdf文件 以及最终的pdf文件
            File templateFile = new File(outFile_template);
            templateFile.delete();

            File tableFile = new File(outFile_table);
            tableFile.delete();

            File finalFile = new File(outFile_final);
            finalFile.delete();

            //返回生成的合同文件完整访问路径
            String loan_access_url_prefix = SystemConfig.getConfigProperty("loan_access_url_prefix");
            return loan_access_url_prefix + strArray[0] + "/" + strArray[1];

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}