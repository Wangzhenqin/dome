package com.imooc.web;

import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.req.UploadStudentFileReq;
import com.imooc.entity.resp.UploadStudentFileResp;
import com.imooc.manager.UploadOrDownloadServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangzhenqin on 2019/6/13.
 */
@RestController
@CrossOrigin
@RequestMapping("/uploadOrDownload")
public class UploadOrDownloadService {
    @Autowired
    public UploadOrDownloadServiceManager uploadOrDownloadServiceManager;

    @RequestMapping(value = "/uploadUser",method= RequestMethod.GET)
    public SuccessEnum uploadUser(String url){
        try{
            return uploadOrDownloadServiceManager.uploadUser(url);
        }catch (Exception e){
            System.out.println("return:"+e);
            return SuccessEnum.INNER_ERROR;
        }
    }

    @RequestMapping(value = "/uploadTitle",method= RequestMethod.GET)
    public SuccessEnum uploadTitle(String url){
        try{
            return uploadOrDownloadServiceManager.uploadTitle(url);
        }catch (Exception e){
            System.out.println("return:"+e);
            return SuccessEnum.INNER_ERROR;
        }
    }

    @RequestMapping(value = "/uploadStudentFile",method= RequestMethod.GET)
    public UploadStudentFileResp uploadStudentFile(UploadStudentFileReq req){
        UploadStudentFileResp resp = new UploadStudentFileResp();
        try{
            return uploadOrDownloadServiceManager.uploadStudentFile(req);
        }catch (Exception e){
            System.out.println("return:"+e);
            resp.setRet(SuccessEnum.INNER_ERROR);
            return resp;
        }
    }
}
