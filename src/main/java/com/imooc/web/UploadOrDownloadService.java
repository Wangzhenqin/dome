package com.imooc.web;

import com.imooc.dao.entity.Area;
import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.req.EnterReq;
import com.imooc.entity.resp.EnterResp;
import com.imooc.manager.UploadOrDownloadServiceManager;
import com.imooc.manager.UserServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzhenqin on 2019/6/13.
 */
@RestController
@RequestMapping("/uploadOrDownload")
public class UploadOrDownloadService {
    @Autowired
    private UploadOrDownloadServiceManager uploadOrDownloadServiceManager;

    @RequestMapping(value = "/uploadUser",method= RequestMethod.GET)
    private SuccessEnum uploadUser(String url){
        try{
            return uploadOrDownloadServiceManager.uploadUser(url);
        }catch (Exception e){
            return SuccessEnum.INNER_ERROR;
        }
    }
}
