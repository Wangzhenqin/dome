package com.imooc.web;

import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.Page;
import com.imooc.entity.req.*;
import com.imooc.entity.resp.GetStudentTitleResp;
import com.imooc.entity.resp.GetTeacherTitleResp;
import com.imooc.entity.resp.GetTimeListResp;
import com.imooc.entity.resp.GetTitleListResp;
import com.imooc.manager.TimeServiceManager;
import com.imooc.manager.TitleServiceManager;
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
@RequestMapping("/time")
public class TimeService {
    @Autowired
    public TimeServiceManager timeServiceManager;

    @RequestMapping(value = "/getimeList",method= RequestMethod.GET)
    public GetTimeListResp getimeList(Page req){
        GetTimeListResp resp =new GetTimeListResp();
        try{
            resp =timeServiceManager.getimeList(req);
            return resp;
        }catch (Exception e){
            System.out.println("return:"+e);
            resp.setRet(SuccessEnum.INNER_ERROR);
            return resp;
        }
    }


    @RequestMapping(value = "/updateTime",method= RequestMethod.GET)
    public SuccessEnum updateTime(UpdateTimeReq updateTimeReq){
        try{
            return timeServiceManager.updateTime(updateTimeReq);
        }catch (Exception e){
            System.out.println("return:"+e);
            return SuccessEnum.INNER_ERROR;
        }
    }

//    @RequestMapping(value = "/addarea",method= RequestMethod.POST)
//    public Map<String,Object> addArea(@RequestBody Area area){
//        Map<String,Object> modelMap=new HashMap<String,Object>();
//        modelMap.put("success",areaService.addArea(area));
//        return modelMap;
//    }
//
}
