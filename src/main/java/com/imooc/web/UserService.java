package com.imooc.web;

import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.req.EnterReq;
import com.imooc.entity.req.GetUserReq;
import com.imooc.entity.req.JudgeComeInModuleReq;
import com.imooc.entity.req.UpdateUserDataReq;
import com.imooc.entity.resp.DirectoryResp;
import com.imooc.entity.resp.EnterResp;
import com.imooc.entity.resp.GetUserResp;
import com.imooc.entity.resp.UpdateUserDataResp;
import com.imooc.manager.UserServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wangzhenqin on 2019/6/13.
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserService {
    @Autowired
    private UserServiceManager userServiceManager;

    @RequestMapping(value = "/enter",method= RequestMethod.GET)
    public EnterResp enter(EnterReq req){
        EnterResp resp =new EnterResp();
        try{
            resp =userServiceManager.enter(req);
            return resp;
        }catch (Exception e){
            System.out.println("return:"+e);
            resp.setRet(SuccessEnum.INNER_ERROR);
            return resp;
        }
    }

    @RequestMapping(value = "/updateUserData",method= RequestMethod.POST)
    public UpdateUserDataResp updateUserData(@RequestBody UpdateUserDataReq req){
        UpdateUserDataResp resp =new UpdateUserDataResp();
        try{
            resp =userServiceManager.updateUserData(req);
            return resp;
        }catch (Exception e){
            System.out.println("return:"+e);
            resp.setRet(SuccessEnum.INNER_ERROR);
            return resp;
        }
    }

    @RequestMapping(value = "/delectUser",method= RequestMethod.GET)
    public SuccessEnum delectUser(Long userId){
        try{
            return userServiceManager.delectUser(userId);
        }catch (Exception e){
            System.out.println("return:"+e);
            return SuccessEnum.INNER_ERROR;
        }
    }

    @RequestMapping(value = "/selectUser",method= RequestMethod.POST)
    public GetUserResp selectUser(@RequestBody GetUserReq req){
        GetUserResp resp =new GetUserResp();
        try{
            resp =userServiceManager.selectUser(req);
            return resp;
        }catch (Exception e){
            System.out.println("return:"+e);
            resp.setRet(SuccessEnum.INNER_ERROR);
            return resp;
        }
    }

    @RequestMapping(value = "/judgeComeInModule",method= RequestMethod.GET)
    public SuccessEnum judgeComeInModule(JudgeComeInModuleReq req){
        try{
            return userServiceManager.judgeComeInModule(req);
        }catch (Exception e){
            System.out.println("return:"+e);
            return SuccessEnum.INNER_ERROR;
        }
    }

    @RequestMapping(value = "/getDirectory",method= RequestMethod.GET)
    public DirectoryResp getDirectory(int userType){
        DirectoryResp resp =new DirectoryResp();
        try{
            return userServiceManager.getDirectory(userType);
        }catch (Exception e){
            System.out.println("return:"+e);
            resp.setRet(SuccessEnum.INNER_ERROR);
            return resp;
        }
    }
}
