package com.lanlinker.controller;

import com.lanlinker.domain.Attachment;
import com.lanlinker.domain.Msg;
import com.lanlinker.domain.Picture;
import com.lanlinker.domain.Preliminary;
import com.lanlinker.properties.FileLocation;
import com.lanlinker.service.PreliminaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * 初试筛选控制器
 */
@Controller
@RequestMapping("/background")
public class PreliminaryController {
    /**
     * 初试业务层操作对象
     */
    @Autowired
    private PreliminaryService preliminaryService;
    /**
     * 注入文件配置类对象
     */
    @Autowired
    private FileLocation fileLocation;
    /**
     * 访问初试筛选页面
     * @return
     */
    @GetMapping("/preliminary")
    public String preliminary(){
        return "function/preliminary";
    }
    /**
     * 下载附件简历
     */
    @RequestMapping("/downloadAttachment")
    public void downloadAttachment(Integer uid,HttpServletRequest request, HttpServletResponse response) throws Exception {
        Attachment attach = preliminaryService.findAttachmentByUid(uid);
        // path是指欲下载的文件的路径。
        File file = new File(fileLocation.getTotalPath()+fileLocation.getFilePath()+attach.getName());
        // 取得文件名。
        String filename = file.getName();
        // 取得文件的后缀名。
        String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
        // 以流的形式下载文件。
        InputStream fis = new BufferedInputStream(new FileInputStream(file));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        // 清空response
        response.reset();
        // 设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename="
                + getStr(request,attach.getName()));
        response.addHeader("Content-Length", "" + file.length());
        OutputStream toClient = new BufferedOutputStream(
                response.getOutputStream());
        response.setContentType("application/octet-stream");
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
    }

    /**
     * 下载文件乱码转换
     * @param request
     * @param realFileName
     * @return
     * @throws Exception
     */
    private String getStr(HttpServletRequest request, String realFileName)
            throws Exception {
        String browName = null;
        String clientInfo = request.getHeader("User-agent");
        System.out.println(clientInfo);
        if (clientInfo != null && clientInfo.indexOf("MSIE") > 0) {//
            // IE采用URLEncoder方式处理
            if (clientInfo.indexOf("MSIE 6") > 0
                    || clientInfo.indexOf("MSIE 5") > 0) {// IE6，用GBK，此处实现由局限性
                browName = new String(realFileName.getBytes("GBK"),
                        "ISO-8859-1");
            } else {// ie7+用URLEncoder方式
                browName = java.net.URLEncoder.encode(realFileName, "UTF-8");
            }
        } else {//其他浏览器
            browName = new String(realFileName.getBytes("GBK"), "ISO-8859-1");
        }
        return browName;
    }

    /**
     * 根据UID查找图片
     * @param uid 简历编号
     * @return    图片页面
     */
    @RequestMapping("/exam")
    @ResponseBody
    public Msg findPictureByUid(Integer uid){
        return new Msg("OK","/background/initExam?uid="+uid);
    }
    /**
     * 渲染有图片的页面
     * @param uid 简历编号
     * @param map 存放图片的集合
     * @return
     */
    @RequestMapping("/initExam")
    public String initExam(Integer uid,ModelMap map){
        List<Picture> list = preliminaryService.findPictureByUid(uid);
        map.addAttribute("list",list);
        return "function/exam";
    }
    /**
     * 保存初试结果对象
     * @param preliminary
     * @return
     */
    @RequestMapping("/savePreliminary")
    @ResponseBody
    public Msg savePreliminary(Preliminary preliminary){
        System.out.println(preliminary.getId()+"-"+preliminary.getGrade()+"-"+preliminary.getSuggestion());
        preliminaryService.savePreliminary(preliminary);
        return new Msg("OK","初试结果已提交~~");
    }
}
