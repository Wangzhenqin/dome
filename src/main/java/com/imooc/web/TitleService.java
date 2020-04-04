package com.imooc.web;

import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.req.GetTitleListReq;
import com.imooc.entity.resp.GetTitleListResp;
import com.imooc.manager.TitleServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangzhenqin on 2019/6/13.
 */
@RestController
@RequestMapping("/title")
public class TitleService {
    @Autowired
    private TitleServiceManager titleServiceManager;

    @RequestMapping(value = "/getTitleList",method= RequestMethod.GET)
    private GetTitleListResp getTitleList(GetTitleListReq req){
        GetTitleListResp getTitleListResp =new GetTitleListResp();
        try{
            getTitleListResp =titleServiceManager.getTitleList(req);
            return getTitleListResp;
        }catch (Exception e){
            getTitleListResp.setRet(SuccessEnum.INNER_ERROR);
            return getTitleListResp;
        }
    }
//    @RequestMapping(value = "/addarea",method= RequestMethod.POST)
//    private Map<String,Object> addArea(@RequestBody Area area){
//        Map<String,Object> modelMap=new HashMap<String,Object>();
//        modelMap.put("success",areaService.addArea(area));
//        return modelMap;
//    }
//
}
