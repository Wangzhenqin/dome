package com.imooc.web;

import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.req.EnterReq;
import com.imooc.entity.req.UpdateCodeReq;
import com.imooc.entity.resp.EnterResp;
import com.imooc.entity.resp.UpdateCodeResp;
import com.imooc.manager.UserServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangzhenqin on 2019/6/13.
 */
@RestController
@RequestMapping("/user")
public class UserService {
    @Autowired
    private UserServiceManager userServiceManager;

    @RequestMapping(value = "/enter",method= RequestMethod.GET)
    private EnterResp enter(EnterReq req){
        EnterResp resp =new EnterResp();
        try{
            resp =userServiceManager.enter(req);
            return resp;
        }catch (Exception e){
            resp.setRet(SuccessEnum.INNER_ERROR);
            return resp;
        }
    }
    @RequestMapping(value = "/updateCode",method= RequestMethod.GET)
    private UpdateCodeResp updateCode(UpdateCodeReq req){
        UpdateCodeResp resp =new UpdateCodeResp();
        try{
            resp =userServiceManager.updateCode(req);
            return resp;
        }catch (Exception e){
            resp.setRet(SuccessEnum.INNER_ERROR);
            return resp;
        }
    }

    @RequestMapping(value = "/updateCode",method= RequestMethod.GET)
    private UpdateCodeResp updateCode(UpdateCodeReq req){
        UpdateCodeResp resp =new UpdateCodeResp();
        try{
            resp =userServiceManager.updateCode(req);
            return resp;
        }catch (Exception e){
            resp.setRet(SuccessEnum.INNER_ERROR);
            return resp;
        }
    }

    @RequestMapping(value = "/delectUser",method= RequestMethod.GET)
    private SuccessEnum delectUser(Long userId){
        try{
            return userServiceManager.delectUser(userId);
        }catch (Exception e){
            return SuccessEnum.INNER_ERROR;
        }
    }
}
